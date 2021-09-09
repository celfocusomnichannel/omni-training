'use strict';
const path = require('path');
module.exports = {
    currentDir: path.normalize(__dirname),
    journeysPath: path.normalize(__dirname).replace('omni-utils', 'omni-journey'),
    widgetsPath: path.normalize(__dirname).replace('omni-utils', 'omni-widgets'),
    threesixtiesPath: path.normalize(__dirname).replace('omni-utils', 'omni-threesixty'),
    outputPath: path.normalize(__dirname + 'SLING-INF/content').replace('frontend', 'resources').replace('omni-utils', ''),
    ufePath: path.normalize(__dirname).replace('omni-utils', 'omni-ufe-canvas'),
    canvasPath: path.normalize(__dirname).replace('omni-utils', 'omni-canvas'),
    shellPath: path.normalize(__dirname).replace('omni-utils', 'omni-shell')
};