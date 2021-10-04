const {
  journeysPath,
  widgetsPath,
  threesixtiesPath,
  floatingComponentsPath,
  outputPath,
  ufePath,
  canvasPath,
  shellPath,
  microModulesPath,
} = require("./paths");
const { spawn } = require("child_process");
const path = require("path");
const fs = require("fs-extra");
const { DefaultSerializer } = require("v8");

const yarnArg =
  path.normalize(getValuesFrom(process.argv, "-yarnPath")) === "."
    ? "yarn"
    : path.normalize(getValuesFrom(process.argv, "-yarnPath"));

const removeModules =
  getValuesFrom(process.argv, "-skiprm") === "true" ? false : true;
const installModules =
  getValuesFrom(process.argv, "-skipim") === "true" ? false : true;

const mapJourneyReplaces = [
  { key: /http:\/\/localhost:4500/gi, value: "" },
  { key: /http:\/\/localhost:4502/gi, value: "" },
  { key: /href="..\/modules\/assets\/reset.min.css"/, value: "" },
  {
    key: /\/foundation\/v3\/deliverables\/react\/16\/react.development.js/,
    value: "/foundation/v3/deliverables/react/16/react.production.min.js",
  },
  {
    key: /\/foundation\/v3\/deliverables\/react\/16\/react-dom.development.js/,
    value: "/foundation/v3/deliverables/react/16/react-dom.production.min.js",
  },
];

/**
 * This omni-utils should be able to ->
 * -Build Journeys
 * -Build Widgets
 * -Build MicroModules
 * -Build Flying Components
 * -Build Canvas
 * -Build Generic Extensions
 * -Build UFE
 *
 * On all those projects the builds should also install the dependencies.
 * The code should be "portable" to allow other scripts with less complexity to be deployed with other apps
 * (that are not ufe).
 */

main();
/**
 * Main function that will start the callstack.
 * It will read the instructions decide wether it should only apply the builds or completly install the modules (and build afterwards).
 */
function main() {
  removeModules &&
    installModules &&
    console.log("The build will remove old node_modules folder");
  installModules && console.log("The build will install dependencies");

  const projects = getComponentPaths();

  if (installModules) {
    installProjectsDependencies(projects);
  } else {
    buildProjects(projects);
  }
}

/**
 * Retrives the paths to build
 */
function getComponentPaths() {
  let dirs = [];
  dirs = [...dirs, ...getComponentsFromPaths(journeysPath, "journeys")];
  dirs = [...dirs, ...getComponentsFromPaths(widgetsPath, "widgets")];
  dirs = [...dirs, ...getComponentsFromPaths(threesixtiesPath, "360")];
  dirs = [
    ...dirs,
    ...getComponentsFromPaths(floatingComponentsPath, "floating-components"),
  ];
  dirs = [
    ...dirs,
    ...getComponentsFromPaths(microModulesPath, "micro-modules"),
  ];
  return dirs;
}

/**
 * Retrieves the list of directories to build from a given path.
 * The types allow to parse the different existing folders:
 * Journeys Folder, contains multiple journeys folders (with their names):
 * -But they can contain or not the web folder, a find for the packaga.json should be done.
 * Widgets Folder, contains multiple widgets folders (with their names):
 * -No web foder should exist
 * Flying Components Folder,
 * @param {} path
 * @param {*} type
 */
function getComponentsFromPaths(path, type) {
  let dirs = [];

  if (!fs.existsSync(path)) {
    return dirs;
  }

  let childrenRootFolder = fs.readdirSync(path);

  if (type === "journeys" || type === "360") {
    dirs = getAppComponents(childrenRootFolder, path, type);
    return dirs;
  } else if (type === "widgets" || type === "floating-components") {
    dirs = getWidgetsComponents(childrenRootFolder, path, type);
    return dirs;
  } else if (type === "micro-modules") {
    dirs = getMicroModulesComponents(childrenRootFolder, path);
    return dirs;
  }

  return dirs;
}

function getAppComponents(childrenRootFolder, modulePath, type) {
  const dirs = [];
  childrenRootFolder.forEach((child) => {
    let componentPath = path.normalize(modulePath + "/" + child);
    if (fs.statSync(componentPath).isFile()) {
      return;
    } else {
      if (isAbleToInstallOrBuild(child, componentPath)) {
        dirs.push({
          componentName: child,
          rootFolder: componentPath,
          buildFolder: path.normalize(componentPath + "/build"),
          deleteOldDeliveryFolder: true,
          type,
        });
      }

      if (
        isAbleToInstallOrBuild(child, path.normalize(componentPath + "/web"))
      ) {
        dirs.push({
          componentName: child,
          rootFolder: path.normalize(componentPath + "/web"),
          buildFolder: path.normalize(
            path.normalize(componentPath + "/web") + "/build"
          ),
          deleteOldDeliveryFolder: true,
          type,
        });
      }
    }
  });
  return dirs;
}

function getWidgetsComponents(childrenRootFolder, modulePath, type) {
  const dirs = [];
  childrenRootFolder.forEach((child) => {
    let componentPath = path.normalize(modulePath + "/" + child);
    if (fs.statSync(componentPath).isFile()) {
      return;
    } else {
      if (isAbleToInstallOrBuild(child, componentPath)) {
        dirs.push({
          componentName: child,
          rootFolder: componentPath,
          buildFolder: path.normalize(componentPath + "/dist"),
          deleteOldDeliveryFolder: false,
          type: type,
        });
      }
    }
  });

  return dirs;
}

function getMicroModulesComponents(childrenRootFolder, modulePath) {
  let dirs = [];
  childrenRootFolder.forEach((child) => {
    let componentPath = path.normalize(modulePath + "/" + child);
    if (fs.statSync(componentPath).isFile()) {
      return;
    } else {
      let microModuleDir = fs.readdirSync(componentPath);
      microModuleDir.forEach((microChild) => {
        let microModulePath = path.normalize(componentPath + "/" + microChild);

        dirs.push({
          componentName: microChild,
          rootFolder: microModulePath,
          buildFolder: path.normalize(microModulePath + "/dist"),
          deleteOldDeliveryFolder: true,
          type: "micro-modules",
          children: {
            child,
            microChild,
          },
        });
      });
    }
  });

  return dirs;
}

function isAbleToInstallOrBuild(project, rootFolder) {
  //add validations.

  if (!fs.existsSync(rootFolder)) {
    console.log(
      "Component " +
        project +
        ": Doesn't exists the expected root folder: " +
        rootFolder
    );
    return false;
  }

  const packageJsonPath = path.normalize(rootFolder + "/package.json");
  let packageObj = "";

  try {
    packageObj = fs.readJsonSync(packageJsonPath);
  } catch (error) {
    console.log(
      "Skipping project " + project + " file package.json is missing."
    );
    return false;
  }

  //if (!packageObj.homepage) {
  //    console.log('Skipping project ' + project + " because doesn't have the homepage defined in the package.json\n" + 'Check the package.json in ' + packageJsonPath);
  //    return false;
  //}
  return true;
}
/**
 * Recursively calls the method that installs all dependencies and call the build action of each project.
 * In the end calls the method that install and build the ufe canvas.
 * @param {*} projects
 */
function installProjectsDependencies(projects) {
  if (projects.length > 0) {
    const project = projects.pop();
    installProjectDependencies(project, () => {
      installProjectsDependencies(projects);
    });
  }
  // Parallelize Build
  // projects.forEach(project => installProjectDependencies(project));
}
/**
 * Remove the node_modules, yarn.lock, install the dependencies and call call the build action of the given project.
 *
 * @param {*} project the current project that needs to install the dependencies and call the build action.
 * @param {*} callback call the parent function to get the next project to install the dependencies and call the build action.
 */
function installProjectDependencies(project, callback) {
  // Parallelize Build
  // const cachePath = path.normalize(project.rootFolder +'/.yarn-cache');
  // removeModules && console.log('Deleting local cache from: ' + project.componentName + '...');
  // removeModules && fs.removeSync(cachePath);
  // removeModules && console.log('Deleted local cache from: ' + project.componentName);
  //****************************************************************************** */

  removeModules &&
    console.log("Deleting node_modules from: " + project.componentName + "...");
  removeModules &&
    fs.removeSync(path.normalize(project.rootFolder + "/node_modules"));
  removeModules &&
    console.log("Deleted node_modules from: " + project.componentName);

  removeModules &&
    console.log("Deleting yarn.lock from " + project.componentName + "...");
  removeModules &&
    fs.removeSync(path.normalize(project.rootFolder + "/yarn.lock"));
  removeModules &&
    console.log("Deleted yarn.lock from: " + project.componentName);

  removeModules &&
    console.log(
      "Deleting package-lock.json from " + project.componentName + "..."
    );
  removeModules &&
    fs.removeSync(path.normalize(project.rootFolder + "/package-lock.json"));
  removeModules &&
    console.log("Deleted package-lock.json from: " + project.componentName);
  // Parallelize Build
  // const installChild = spawn(yarnArg, ['--cwd', project.rootFolder, 'install', '--cache-folder', cachePath ], { shell:true, windowsHide: true});
  const installChild = spawn(
    yarnArg,
    ["--cwd", project.rootFolder, "install"],
    { shell: true, windowsHide: true }
  );

  console.log("Starting Install " + project.componentName + " dependencies");

  installChild.stderr.on("data", (data) => {
    console.log(project.componentName + "->stderr : " + data);
  });

  installChild.on("close", (code) => {
    if (code !== 0) {
      throw (
        project.componentName +
        " an error occurred at installing the dependencies"
      );
    } else {
      console.log(
        project.componentName + " dependencies was successfully installed."
      );
      buildComponent(
        project.componentName,
        project.rootFolder,
        project.buildFolder,
        project.deleteOldDeliveryFolder,
        project.type,
        project.children
      );
      // Parallelize Build comment the next line!!
      callback();
    }
  });
}
/**
 * Just call the action of build on each project.
 * @param {*} projects
 */
function buildProjects(projects) {
  console.log("Building projects...");
  projects.forEach((project) => {
    console.log(JSON.stringify(project));
    buildComponent(
      project.componentName,
      project.rootFolder,
      project.buildFolder,
      project.deleteOldDeployFolder,
      project.type,
      project.children
    );
  });
}

function getValuesFrom(arguments, key) {
  if (arguments) {
    let value = "";
    let argumentIndex = -1;
    for (let index = 0; index < arguments.length; index++) {
      const element = arguments[index];
      if (element === key) {
        argumentIndex = index + 1;
      }
    }
    if (argumentIndex !== -1) {
      return arguments[argumentIndex];
    }
    return value;
  }
}

/**
 * Build a component and deploy the build folder into the content server.
 * @param {*} componentName
 * @param {*} rootFolder
 * @param {*} buildFolder
 * @param {*} deleteOldDeployFolder
 */
function buildComponent(
  componentName,
  rootFolder,
  buildFolder,
  deleteOldDeployFolder,
  type,
  children
) {
  const packageObj = fs.readJsonSync(rootFolder + "/package.json");
  const buildChild = spawn(yarnArg, ["--cwd", rootFolder, "build"], {
    shell: true,
    windowsHide: true,
  });
  console.log("Starting the build process on component: " + componentName);

  buildChild.stderr.on("data", (data) => {
    console.warn("Build " + componentName + "Error: " + data);
  });

  buildChild.stdout.on("data", (data) => {
    console.log("Build " + componentName + "Info: " + data);
  });

  buildChild.on("close", (code) => {
    if (code !== 0) {
      throw componentName + " an error occurred at building";
    } else {
      if (fs.existsSync(buildFolder)) {
        console.log(componentName + " was built successfully");

        let callback = () =>
          deployBuildFolder(
            componentName,
            packageObj,
            buildFolder,
            deleteOldDeployFolder,
            type,
            children
          );

        if (type === "journeys" || type === "360" || type === "canvas") {
          //Replace the content on the index.html - modules.html
          //The usage of the Callback will occurr
          const contentFilePath = path.normalize(buildFolder + "/index.html");
          replaceContentFile(
            contentFilePath,
            contentFilePath,
            mapJourneyReplaces,
            callback
          );
        } else {
          callback();
        }
      } else {
        console.warn("WARNING: " + componentName + "build folder is missing");
      }
    }
  });
}

function replaceContentFile(readFilePath, writeFilePath, replaceMap, callback) {
  fs.readFile(readFilePath, "utf8", (err, data) => {
    if (err) {
      return console.log(err);
    }
    let replacement = data;
    replaceMap.forEach((item) => {
      replacement = replacement.replace(item.key, item.value);
    });
    fs.writeFile(writeFilePath, replacement, "utf8", (err) => {
      if (err) return console.log(err);
      else if (callback) callback();
    });
  });
}

function deployBuildFolder(
  componentName,
  packageObj,
  buildFolder,
  deleteOldDeployFolder,
  type,
  children
) {
  let deployFolder;

  if (type === "micro-modules") {
    deployFolder =
      outputPath +
      "/libs/digitaljourney/foundation/v3/micro-modules/" +
      children.child +
      "/" +
      children.microChild +
      "/latest";
  } else if (type === "floating-components") {
    deployFolder =
      outputPath +
      "/libs/digitaljourney/foundation/v3/floating-components/" +
      componentName;
  } else {
    const finalOutput = packageObj.outputName ? packageObj.outputName : "apps";
    const homepage = packageObj.homepage
      ? packageObj.homepage.replace("content", finalOutput)
      : "";
    deployFolder = outputPath + homepage;
  }

  if (deleteOldDeployFolder) {
    console.log("Removing " + componentName + " old modules content");
    fs.removeSync(deployFolder);
    console.log(
      componentName + " old modules content has been successfully removed"
    );
  }

  console.log(
    "Deploying " + componentName + "'s" + " build folder to content server"
  );
  console.log("Deployes on: " + deployFolder);
  fs.copySync(buildFolder, deployFolder);
  console.log(componentName + " has been successfully deployed");
}

function executeSequentialCommands(commands, callback) {
  // commands[0]={
  //   'beforeExecution': ()=>{},  //sync function
  //   'goal':'a description'
  //   'commandExec':"shell command",
  //   'args':[arg1,arg2],
  // }
  if (commands.length > 0) {
    const command = commands.shift();
    console.log(command);
    executeCommand(command, () => {
      executeSequentialCommands(commands, callback);
    });
  } else if (callback) {
    callback();
  }
}

function executeCommand(command, callback) {
  if (command.beforeExecution) {
    command.beforeExecution();
  }

  const childProcess = spawn(command.commandExec, command.args, {
    shell: true,
    windowsHide: true,
  });
  console.log("Executing goal: " + command.goal);

  childProcess.stderr.on("data", (data) => {
    console.log(command.goal + "->stderr : " + data);
  });

  childProcess.stdout.on("data", (data) => {
    console.log(command.goal + " Info: " + data);
  });

  childProcess.on("close", (code) => {
    if (code !== 0) {
      console.log(`An error occurred at executing goal: ${command.goal}`);
      throw `An error occurred at executing goal: ${command.goal}`;
    } else if (callback) {
      console.log("Goal: " + command.goal + " completed.");
      callback();
    }
  });
}
