
var argv = require('optimist')
			.boolean('V')
			.boolean('verbose')
			.argv;
require('shelljs/global');
var fs= require('fs');
var path= require('path');

fs.exists('./.project', function check(exists) {
	if (exists) {
		console.log(".project file already exists!");
		return;
	}
	copyFromTemplates();
});

function copyFromTemplates() {
	console.log(require.main.filename); // = __filename
	
	console.log('Passed parameters: name=%s, verbose=%s', argv.name, argv.verbose);
	var name = argv.name || argv.n;
	var verbose = true || argv.verbose;
	
	var curfolder = pwd();
	if (verbose) console.log("Current folder is: " + curfolder);
	if (!name)
		name = path.basename(curfolder);
	
	var templatesfolder = __dirname;
	if (verbose) console.log("Templates folder is: " + templatesfolder);
	
	
	// cp('./eclipse/.project','.');
	
	//e.g. var lib  = path.join(path.dirname(fs.realpathSync(__filename)), '../lib');
	
	var str = cat(__dirname+'/eclipse/.project').replace('${projectname}', name);
	var destfile = curfolder + '/.project';
	
	// http://www.nodejs.org/api/fs.html#fs_fs_appendfile_filename_data_options_callback
	
	//console.log(curfolder + '/.project');
	fs.writeFile(destfile, str, function(err) {	
		if (err)
			throw err;
		console.log('The file "'+destfile+'" was created !');
	});
	
	console.log(str); // ''.toString()
	
	console.log("\nCheck http://www.nodeclipse.org/ for news.");
}
