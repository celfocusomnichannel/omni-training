'use strict';
const path = require('path');
module.exports = {
    currentDir: path.normalize(__dirname),
    journeysPath: path.normalize(__dirname).replace('omni-utils', 'journeys'),
    widgetsPath: path.normalize(__dirname).replace('omni-utils', 'widgets'),
    threesixtiesPath: path.normalize(__dirname).replace('omni-utils', 'threesixties'),
    floatingComponentsPath: path.normalize(__dirname).replace('omni-utils', 'floating-components'),
    microModulesPath: path.normalize(__dirname).replace('omni-utils', 'micro-modules'),
    ufePath: path.normalize(__dirname).replace('omni-utils', 'canvas/omni-ufe-canvas'),
    canvasPath: path.normalize(__dirname).replace('omni-utils', 'omni-canvas'),
    shellPath: path.normalize(__dirname).replace('omni-utils', 'omni-shell'),
    outputPath: path
        .normalize(__dirname + 'SLING-INF/content')
        .replace('frontend', 'resources')
        .replace('omni-utils', '')
};
