
var argv = require('optimist').argv;
require('shelljs/global');
var fs= require('fs');
var path= require('path');

console.log(require.main.filename); // = __filename

console.log('Passed parameters: name=%s, verbose=%s', argv.name, argv.verbose);
var name = argv.name;
var verbose = true || argv.verbose;

var curfolder = pwd();
if (verbose) console.log("Current folder is: " + curfolder);
if (!name)
	name = path.basename(curfolder);

var templatesfolder = __dirname;
if (verbose) console.log("Templates folder is: " + templatesfolder);


// cp('./eclipse/.project','.');

//e.g. var lib  = path.join(path.dirname(fs.realpathSync(__filename)), '../lib');

var str = cat(__dirname+'/eclipse/xml.project').replace('${projectname}', name);
var destfile = curfolder + '/.project_';

// http://www.nodejs.org/api/fs.html#fs_fs_appendfile_filename_data_options_callback

//console.log(curfolder + '/.project');
fs.writeFile(destfile, str, function(err) {	
	if (err)
		throw err;
	console.log('The file "'+destfile+'" was updated !');
});

console.log(str); // ''.toString()
