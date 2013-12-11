#!/usr/bin/env node

var path = require('path');
var fs   = require('fs');

var copierjs  = path.join(path.dirname(fs.realpathSync(__filename)), '../copier.js');
var c = require(copierjs);
