
const { journeysPath, widgetsPath, threesixtiesPath, outputPath, ufePath, canvasPath, shellPath } = require('./paths')
const { spawn } = require('child_process');
const path = require('path');
const fs = require('fs-extra');

const yarnArg = path.normalize(getValuesFrom(process.argv, '-yarnPath'));
const removeModules = getValuesFrom(process.argv, '-skiprm') ==='true' ? false:true;
const installModules = getValuesFrom(process.argv, '-skipim') ==='true' ? false:true;;

const mapUfeReplaces= [
  {"key":/href=".\/assets\/reset.min.css"/,"value":'href=".\/modules\/assets\/reset.min.css"'},
  {"key":/href=".assets\/reset.min.css"/,"value":'href=".\/modules\/assets\/reset.min.css"'},
  {"key":/href=".\/styles\/default.css"/,"value":'href=".\/modules\/styles\/default.css"'},
  {"key":/href=".\/styles\/font.css"/,"value":'href=".\/modules\/styles\/font.css"'},
  {"key":/href=".\/manifest.json"/,"value":'href=".\/modules\/manifest.json"'},
  {"key":/href".\/images\/favicon.ico"/,"value":'href=".\/modules\/images\/favicon.ico"'}, 
  {"key":/<\/head><body>/,"value":'<\/head><body><div id="baseUrl" style="display: none">\${properties.baseUrl}<\/div><div id="runMode" style="display: none">${properties.runMode}<\/div><div id="disableGeoLocation" style="display: none">${properties.disableGeoLocation}<\/div><div id="httpRequestTimeout" style="display: none">${properties.httpRequestTimeout}<\/div><div id="cacheControl" style="display: none">${properties.cacheControl}<\/div>'},
  {"key":/<meta charset="utf-8"\/><title>UFE<\/title>/,"value":'<meta charset="utf-8"/><title>${properties.canvasTitle}<\/title>'},
  {"key":/http:\/\/localhost:4500\/content\/digitaljourney\/foundation\/v3\/deliverables\//gi, "value":'\/content\/digitaljourney\/foundation\/v3\/deliverables/'}
];


const mapJourneyReplaces=[
  {"key":/http:\/\/localhost:4500\/content\/digitaljourney\/foundation\/v3\/deliverables\//gi, "value":'\/content\/digitaljourney\/foundation\/v3\/deliverables/'}
];

entryPoint();

function entryPoint() {
  removeModules&&installModules&&console.log('The build will remove old node_modules folder');
  installModules&&console.log('The build will install dependencies');  
  if (installModules) {
    const commands = [];
    commands.push({
      "goal":"Yarn Cache Clean",
      "commandExec": yarnArg,
      "args": ['cache clean']
    });
    executeSequentialCommands(commands, buildFrontend);
    ;
  } else {
    buildFrontend();
  }
  // console.log(process.argv);
  // console.log(
  //   'yarn path: '+ yarnArg +'\n' +
  //   'removeModules: '+ removeModules +'\n' +
  //   'installModules: '+ installModules +'\n'
  //   // 'removeModules: '+ (removeModules?'true':'false') +'\n' +
  //   // 'installModules: '+ (installModules?'true':'false')+'\n'
  // );
};

function buildFrontend () {
  const projects = getComponentPaths();
  
  if (installModules){
    //install and build projects.
    console.log((removeModules?'Remove node_modules and ':'') + 'Installing Dependencies...');
    installProjectsDependencies(projects)
  } else {
    //only build projects.    
    buildProjects(projects);
    if (fs.existsSync(ufePath)) {
      buildUfe();
    }
  }
};

function getComponentPaths() {
  let dirs = [];
  dirs = [...dirs, ...getComponentPathsFromMainPaths(canvasPath)];
  dirs = [...dirs, ...getComponentPathsFromMainPaths(journeysPath)];
  dirs = [...dirs, ...getComponentPathsFromMainPaths(widgetsPath)];
  dirs = [...dirs, ...getComponentPathsFromMainPaths(threesixtiesPath)];
  return dirs;
};

function getComponentPathsFromMainPaths (mainFolderPath){
  const dirs = [];
  if(fs.existsSync(mainFolderPath)){
    const parentDir = fs.readdirSync(mainFolderPath);
    parentDir.forEach(child => {
      let componentPath = path.normalize( mainFolderPath + '/' + child);
      if (fs.statSync(componentPath).isFile()) {
        return;
      } else {
      // each main folder has many components.
        let rootFolder =""; 
        
        if (mainFolderPath === widgetsPath){
          rootFolder = componentPath;
        } else {
          rootFolder = path.normalize(componentPath +'/web');
        }
        if (isAbleToInstallOrBuild(child, rootFolder)){
          if(mainFolderPath === widgetsPath){          
            dirs.push({
              "componentName":child,
              "rootFolder": rootFolder, 
              "buildFolder": path.normalize(rootFolder +'/dist'),
              "deleteOldDeliveryFolder": false            
            });
          } else {  
            dirs.push({
              "componentName":child,
              "rootFolder": rootFolder, 
              "buildFolder": path.normalize(rootFolder +'/build'),
              "deleteOldDeliveryFolder": true            
            });
          }
        }
      } 
    });
  }
  return dirs;
};

function isAbleToInstallOrBuild(project, rootFolder) {
  //add validations.
   
  if (!fs.existsSync(rootFolder)){
    console.log('Component '+ project +": Doesn't exists the expected root folder: " + rootFolder );
    return false;
  }
  
  const packageJsonPath = path.normalize(rootFolder + '/package.json');
  let packageObj = "";
  try {
    packageObj = fs.readJsonSync(packageJsonPath);
  } catch (error) {
    console.log('Skipping project '+ project + " file package.json is missing.");
    return false;    
  }

  if(!packageObj.homepage){
    console.log(
      'Skipping project '+ project + " because doesn't have the homepage defined in the package.json\n" +
      'Check the package.json in '+ packageJsonPath
    );
    return false;
  }
  return true;
}
/**
 * Recursively calls the method that installs all dependencies and call the build action of each project.
 * In the end calls the method that install and build the ufe canvas.
 * @param {*} projects 
 */
function installProjectsDependencies(projects){
  
  if(projects.length>0){
    const project = projects.pop();
    installProjectDependencies(project, ()=>{installProjectsDependencies(projects)});
  } else if (fs.existsSync(ufePath)) {
    installBuildUfe();    
  }
  // Parallelize Build
  // projects.forEach(project => installProjectDependencies(project));
  // if (fs.existsSync(ufePath)) {
  //   installBuildUfe();    
  // }

};
/**
 * Remove the node_modules, yarn.lock, install the dependencies and call call the build action of the given project.
 * 
 * @param {*} project the current project that needs to install the dependencies and call the build action.
 * @param {*} callback call the parent function to get the next project to install the dependencies and call the build action.
 */
function installProjectDependencies(project, callback){

  // Parallelize Build
  // const cachePath = path.normalize(project.rootFolder +'/.yarn-cache');
  // removeModules && console.log('Deleting local cache from: ' + project.componentName + '...');
  // removeModules && fs.removeSync(cachePath);
  // removeModules && console.log('Deleted local cache from: ' + project.componentName);
  //****************************************************************************** */

  removeModules && console.log('Deleting node_modules from: ' + project.componentName + '...');
  removeModules && fs.removeSync(path.normalize(project.rootFolder + '/node_modules'));
  removeModules && console.log('Deleted node_modules from: ' + project.componentName);

  removeModules && console.log('Deleting yarn.lock from ' + project.componentName + '...');
  removeModules && fs.removeSync(path.normalize(project.rootFolder + '/yarn.lock'));
  removeModules && console.log('Deleted yarn.lock from: ' + project.componentName);         
  // Parallelize Build
  // const installChild = spawn(yarnArg, ['--cwd', project.rootFolder, 'install', '--cache-folder', cachePath ], { shell:true, windowsHide: true});
  const installChild = spawn(yarnArg, ['--cwd', project.rootFolder, 'install'], { shell:true, windowsHide: true});
 
  console.log('Starting Install '+ project.componentName + " dependencies" );
  
  installChild.stderr.on('data', (data) => {           
    console.log(project.componentName + "->stderr : " + data);
  });

  installChild.on('close', (code) =>{     
    if (code !==0) {
      throw project.componentName +' an error occurred at installing the dependencies';
    } else {
      console.log(project.componentName + ' dependencies was successfully installed.');
      buildComponent(project.componentName, project.rootFolder, project.buildFolder, project.deleteOldDeliveryFolder);
      // Parallelize Build comment the next line!!
      callback();
    }
  });
};
/**
 * Just call the action of build on each project.
 * @param {*} projects 
 */
function buildProjects(projects){
  console.log('Building projects...');
  projects.forEach(project => buildComponent(project.componentName, project.rootFolder, project.buildFolder, project.deleteOldDeliveryFolder));
};

function installBuildUfe() {
  
  const rootUfeWebFolder = path.normalize(ufePath +'/web');
  const rootShellCommon = path.normalize(shellPath +'/omni-shell-common' );
  const rootShellWeb = path.normalize(shellPath +'/omni-shell-web' );
  const rootUfeCommon = path.normalize(ufePath + '/common');

  // Parallelize Build
  // const shellCommonCachePath = path.normalize(rootShellCommon +'/.yarn-cache');
  // const shellWebCachePath = path.normalize(rootShellWeb +'/.yarn-cache');
  // const ufeWebCachePath = path.normalize(rootUfeWebFolder +'/.yarn-cache');
  // const ufeCommonCachePath = path.normalize(rootUfeCommon +'/.yarn-cache'); 
  //****************************************************************************** */

  const installUfeCommands = [];

  //1
  installUfeCommands.push({
    "beforeExecution":()=>{

      // Parallelize Build
      // removeModules && console.log('Deleting local cache from Ufe-Web...');
      // removeModules && fs.removeSync(ufeWebCachePath);
      // removeModules && console.log('Deleted local cache from UFE');
      //****************************************************************************** */
      removeModules && console.log('Deleting node_modules from Ufe-Web...');
      removeModules && fs.removeSync(path.normalize(rootUfeWebFolder + '/node_modules'));
      removeModules && console.log('Deleted node_modules from UFE');
      
      removeModules &&console.log('Deleting yarn.lock from Ufe-Web...');
      removeModules && fs.removeSync(path.normalize(rootUfeWebFolder + '/yarn.lock'));
      removeModules &&console.log('Deleted yarn.lock from: UFE');
    },
    "goal":"Install UFE-Web dependencies",
    "commandExec": yarnArg,
    // Parallelize Build 
    // "args": ['--cwd', rootUfeWebFolder, 'install', '--cache-folder', ufeWebCachePath ]
    // Parallelize Build comment the next line
    "args": ['--cwd', rootUfeWebFolder, 'install']

  });

  //2
  // WARNING: Without this the script will fail if no link has been made.
  installUfeCommands.push({
    "goal":"Prevent Error Unlink Shell-Common",
    "commandExec": yarnArg,
    "args": ['--cwd', rootShellCommon, 'link']
  });


  //3
  installUfeCommands.push({
    "goal":"Unlink Shell-Common",
    "commandExec": yarnArg,
    "args": ['--cwd', rootShellCommon, 'unlink']
  });

  //4
  installUfeCommands.push({
    "beforeExecution":()=>{

      // Parallelize Build       
      // removeModules && console.log('Deleting local cache from Shell-Common...');
      // removeModules && fs.removeSync(shellCommonCachePath);
      // removeModules && console.log('Deleted local cache from Shell-Common');
      //****************************************************************************** */

      removeModules && console.log('Deleting node_modules from Shell-Common...');
      removeModules && fs.removeSync(path.normalize(path.normalize(rootShellCommon + '/node_modules')));
      removeModules && console.log('Deleted node_modules from: Shell-Common');
      
      removeModules && console.log('Deleting yarn.lock from Shell-Common...');
      removeModules && fs.removeSync(path.normalize(rootUfeCommon + '/yarn.lock'))
      removeModules && console.log('Deleted yarn.lock from: Shell-Common');
    },
    "goal":"Install Shell-Common Dependencies",
    "commandExec": yarnArg,
    // Parallelize Build    
    // "args": ['--cwd', rootUfeCommon, 'install', '--cache-folder', ufeCommonCachePath]
    // Parallelize Build comment the next line.
    "args": ['--cwd', rootShellCommon, 'install']
  });

  //2
  installUfeCommands.push({
    "goal":"Link Shell-Common",
    "commandExec": yarnArg,
    "args": ['--cwd', rootShellCommon, 'link']
  });

  //5
  // WARNING: Without this the script will fail if no link has been made.
  installUfeCommands.push({
    "goal":"Prevent Error Unlink Shell-Web",
    "commandExec": yarnArg,
    "args": ['--cwd', rootShellWeb, 'link']
  });


  //6
  installUfeCommands.push({
    "goal":"Unlink Shell-Web",
    "commandExec": yarnArg,
    "args": ['--cwd', rootShellWeb, 'unlink']
  });

  //7
  installUfeCommands.push({
    "beforeExecution":()=>{

      // Parallelize Build       
      // removeModules && console.log('Deleting local cache from Shell-Web...');
      // removeModules && fs.removeSync(shellWebCachePath);
      // removeModules && console.log('Deleted local cache from Shell-Web');
      //****************************************************************************** */

      removeModules && console.log('Deleting node_modules from Shell-Web...');
      removeModules && fs.removeSync(path.normalize(path.normalize(rootShellWeb + '/node_modules')));
      removeModules && console.log('Deleted node_modules from: Shell-Web');
      
      removeModules && console.log('Deleting yarn.lock from Shell-Web...');
      removeModules && fs.removeSync(path.normalize(rootShellWeb + '/yarn.lock'))
      removeModules && console.log('Deleted yarn.lock from: Shell-Web');
    },
    "goal":"Install Shell-Web Dependencies",
    "commandExec": yarnArg,
    // Parallelize Build    
    // "args": ['--cwd', rootUfeCommon, 'install', '--cache-folder', ufeCommonCachePath]
    // Parallelize Build comment the next line.
    "args": ['--cwd', rootShellWeb, 'install']
  });

  //8
  installUfeCommands.push({
    "goal":"Link Shell-Web",
    "commandExec": yarnArg,
    "args": ['--cwd', rootShellWeb, 'link']
  });

  //9
  // WARNING: Without this the script will fail if no link has been made.
  installUfeCommands.push({
    "goal":"Prevent Error Unlink Ufe-Common",
    "commandExec": yarnArg,
    "args": ['--cwd', rootUfeCommon, 'link']
  });

  //10
  installUfeCommands.push({
    "goal":"Unlink Ufe-Common",
    "commandExec": yarnArg,
    "args": ['--cwd', rootUfeCommon, 'unlink']
  });


  //11
  installUfeCommands.push({
    "beforeExecution":()=>{

      // Parallelize Build       
      // removeModules && console.log('Deleting local cache from Ufe-Common...');
      // removeModules && fs.removeSync(ufeCommonCachePath);
      // removeModules && console.log('Deleted local cache from UFE common');
      //****************************************************************************** */

      removeModules && console.log('Deleting node_modules from Ufe-Common...');
      removeModules && fs.removeSync(path.normalize(path.normalize(rootUfeCommon + '/node_modules')));
      removeModules && console.log('Deleted node_modules from: UFE common');
      
      removeModules && console.log('Deleting yarn.lock from Ufe-Web...');
      removeModules && fs.removeSync(path.normalize(rootUfeCommon + '/yarn.lock'))
      removeModules && console.log('Deleted yarn.lock from: UFE common');
    },
    "goal":"Install Ufe-Common Dependencies",
    "commandExec": yarnArg,
    // Parallelize Build    
    // "args": ['--cwd', rootUfeCommon, 'install', '--cache-folder', ufeCommonCachePath]
    // Parallelize Build comment the next line.
    "args": ['--cwd', rootUfeWebFolder, 'install']
  });

  //12
  installUfeCommands.push({
    "goal":"Link UFE-Common",
    "commandExec": yarnArg,
    "args": ['--cwd', rootUfeCommon, 'link']
  });

  //13
  installUfeCommands.push({
    "goal":"Link Shell-Common to UFE-WEB",
    "commandExec": yarnArg,
    "args": ['--cwd', rootUfeWebFolder , 'link omni-shell-common' ]
  });

  //14
  installUfeCommands.push({
    "goal":"Link Shell-Web to UFE-WEB",
    "commandExec": yarnArg,
    "args": ['--cwd', rootUfeWebFolder , 'link omni-shell-web' ]
  });

  //15
  installUfeCommands.push({
    "goal":"Link Ufe-Common to UFE-WEB",
    "commandExec": yarnArg,
    "args": ['--cwd', rootUfeWebFolder , 'link omni-ufe-common' ]
  });

  executeSequentialCommands(installUfeCommands, buildUfe);
};

function buildUfe() {
  const rootUfeFolder =  path.normalize(ufePath+ '/web');
  const childProcess = spawn(yarnArg, ['--cwd', rootUfeFolder, 'build'], { shell:true, windowsHide: true});
  console.log('Starting building UFE...');  
  childProcess.stderr.on('data', (data) => {           
    console.log("Build UFE " + "->stderr : " + data);
  });
  childProcess.on('close', (code) =>{     
    if (code !==0) {
      throw 'An error occurred at building UFE';
    } else {
      console.log('UFE was built successfully');
      const packageObj = fs.readJsonSync(rootUfeFolder + '/package.json');
      const deployFolder = outputPath + packageObj.homepage.replace('content', 'libs');

      const readFilePath = path.normalize(ufePath+'/web/build/index.html');
      const writeFilePath = path.normalize(ufePath+'/web/build/modules.html');

      replaceContentFile(readFilePath, writeFilePath, mapUfeReplaces, ()=>{
        
        console.log('UFE: Starting deploying build...');

        console.log('Removing index.html from build folder...');
        fs.removeSync(readFilePath);
        console.log('index.html completely deleted');

        console.log('Removing UFE old content...');
        fs.removeSync(deployFolder);
        console.log('UFE content has been successfully removed');
        
        console.log('Copying build files for UFE'); 
        const buildFolder = path.normalize(rootUfeFolder+ '/build');        
        console.log('Deploying UFE build folder to content server...');

        fs.copySync(buildFolder, deployFolder);
        console.log("UFE: Successfully deployed");  

      });            
    }
  });
};

function getValuesFrom(arguments, key) {
  if (arguments) {
    let value = "";
    let argumentIndex = -1
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
};

/**
 * Build a component and deploy the build folder into the content server.
 * @param {*} componentName 
 * @param {*} rootFolder 
 * @param {*} buildFolder 
 * @param {*} deleteOldDeployFolder 
 */
function buildComponent(componentName, rootFolder, buildFolder, deleteOldDeployFolder){  
  const packageObj = fs.readJsonSync(rootFolder + '/package.json');
  const buildChild = spawn(yarnArg, ['--cwd', rootFolder, 'build'], { shell:true, windowsHide: true});
  console.log('Starting building '+ componentName );
  
  buildChild.stderr.on('data', (data) => {           
    console.log('Build '+componentName + "->stderr : " + data);
  });

  buildChild.on('close', (code) =>{     
    if (code !==0) {
      throw componentName +' an error occurred at building';
    } else {
      if (fs.existsSync(buildFolder)){
        console.log(componentName + ' was built successfully');
        
        //create an exception
        if(componentName === 'omni-journey-dashboard'){
          const contentFilePath = path.normalize(buildFolder +'/index.html');
          replaceContentBuildPages(componentName, packageObj, buildFolder, deleteOldDeployFolder, contentFilePath, contentFilePath, mapJourneyReplaces);
        } else {
          deployBuildFolder(componentName, packageObj, buildFolder, deleteOldDeployFolder);
        }
        
      } else {
        console.log('WARNING: ' + componentName + 'build folder is missing');
      }     
    }
  });  
};


function replaceContentBuildPages (componentName, packageObj, buildFolder, deleteOldDeployFolder, readFilePath, writeFilePath, mapReplaces) {
  
  replaceContentFile(readFilePath, writeFilePath, mapReplaces, () =>{deployBuildFolder (componentName, packageObj, buildFolder, deleteOldDeployFolder);});
  
};

function replaceContentFile(readFilePath, writeFilePath, replaceMap, callback){
  fs.readFile(readFilePath, 'utf8', (err,data) => {
    if (err) {
      return console.log(err);
    }
    let replacement = data ;
    replaceMap.forEach((item)=>{
      replacement = replacement.replace(item.key,item.value);
    });       
    fs.writeFile(writeFilePath,  replacement, 'utf8', (err) => {
       if (err) return console.log(err);
       else if(callback) callback();
    });
  });
};

function deployBuildFolder (componentName, packageObj, buildFolder, deleteOldDeployFolder){
  const finalOutput = packageObj.outputName ? packageObj.outputName : 'apps';      
  const deployFolder = outputPath + packageObj.homepage.replace('content', finalOutput);

  if (deleteOldDeployFolder){
    console.log('Removing ' + componentName + ' old modules content');
    fs.removeSync(deployFolder);
    console.log(componentName + ' old modules content has been successfully removed');        
  }
  console.log('Deploying ' + componentName + "'s" +' build folder to content server');
  fs.copySync(buildFolder, deployFolder);
  console.log(componentName + " has been successfully deployed");
}

function executeSequentialCommands(commands, callback){
  // commands[0]={
  //   'beforeExecution': ()=>{},  //sync function
  //   'goal':'a description'
  //   'commandExec':"shell command",
  //   'args':[arg1,arg2],
  // }
  if(commands.length>0){
    const command = commands.shift();
    executeCommand(command, () => {executeSequentialCommands(commands,callback)})
  } else if (callback){
    callback();    
  }
};

function executeCommand(command, callback){
  
  if(command.beforeExecution){
    command.beforeExecution();
  }
  
  const childProcess = spawn(command.commandExec, command.args, { shell:true, windowsHide: true});
  console.log('Executing goal: '+ command.goal +'...');
    
  childProcess.stderr.on('data', (data) => {           
    console.log(command.goal+ "->stderr : " + data);
  });
  childProcess.on('close', (code) =>{     
    if (code !==0) {
      throw 'An error occurred at executing goal: ' + command.goal;
    } else if (callback){
      console.log('Goal: '+ command.goal +' completed.');
      callback();      
    }
  });
};