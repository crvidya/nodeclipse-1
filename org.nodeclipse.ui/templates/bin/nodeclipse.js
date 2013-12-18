#!/usr/bin/env node
// #101; this file should have UNIX style line endings (\n only) when publishing

var path = require('path');
var fs   = require('fs');

var copierjs  = path.join(path.dirname(fs.realpathSync(__filename)), '../copier.js');
var c = require(copierjs);
