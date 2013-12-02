
var fs= require('fs');
fs.exists('./.project', function (exists) {
	if (exists)
		console.log(".project file already exists!");
		return;
	var c = require('./templates/copier.js');
});