
var argv = require('optimist')
			.boolean('h').boolean('help')
			.boolean('v').boolean('version')
			.boolean('V').boolean('verbose')
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

var copyFromTemplates = function () {
	console.log(require.main.filename); // = __filename
	
	console.log('Passed parameters: name=%s, verbose=%s', argv.name, argv.verbose);
	var help = argv.help || argv.h;
	var version = argv.version || argv.v;
	var create = argv.create || argv.c;
	var use = argv.use || argv.u || argv.template || argv.t;
	var name = argv.name || argv.n; // could be   create || 
	var verbose = true || argv.verbose || argv.V;

	if (help){
		console.log("Check README");		
		return;
	}
	if (version){
		console.log("Nodeclipse CLI 0.3.0");	// BUG #71	
		return;
	}
	if (create){
		if (create.toString().length===0){
			console.error("Folder/project name is required for creation!");
			//process.exit(1);
			return;
		}
		mkdir(create);
		cd(create);
	}
	var templatesfolder = __dirname;
	if (verbose) console.log("Templates folder is: " + templatesfolder);
	if (use){
		//cp(__dirname+use+'/*','.');
		var fromfolder = path.join(templatesfolder, use, '/*') 
		console.log( fromfolder );
		cp( fromfolder, '.' )
	}
	
	var curfolder = pwd();
	if (verbose) console.log("Current folder is: " + curfolder);
	if (!name)
		name = path.basename(curfolder);
	
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
	
	inviteToSiteAsTheLastLine();
}
var inviteToSiteAsTheLastLine = function () {
	setTimeout(inviteToSite,100);
}
var inviteToSite = function () {
	console.log("\nCheck http://www.nodeclipse.org/ for news.");
}
