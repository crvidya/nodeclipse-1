
var argv = require('optimist')
			.alias('c','create') .describe('c', 'create project folder and prepare it')
			.alias('p','prepare').describe('p', 'prepare for import, i.e. add needed `.project` file (default action)')
			.string('n')
			.alias('n','name')   .describe('h', 'project name (default is folder name)')
			.boolean(['h','v','V'])
			.alias('h','help')   .describe('h', 'lists usage arguments')
			.alias('v','version').describe('v', 'print nodeclipse CLI version')			
			.alias('V','verbose').describe('V', 'be verbose')
			.argv;
require('shelljs/global');
var fs= require('fs');
var path= require('path');

var help = argv.help || argv.h;
var version = argv.version || argv.v;
var create = argv.create || argv.c;
var use = argv.use || argv.u || argv.template || argv.t;
var name = argv.name || argv.n; // could be   create || 
var debug = true;
var verbose = debug || argv.verbose || argv.V;

var projectFileExists = false; 

fs.exists('./.project', function check(exists) {
	projectFileExists = exists;
//	if (exists) {
//		console.log(".project file already exists!");
//		//return;
//	}
	processArguments();
});

var processArguments = function () {
	//if (debug) console.log(require.main.filename); // = __filename
	//if (debug) console.dir(argv);
	//console.log('Passed parameters: name=%s, verbose=%s', argv.name, argv.verbose);

	if (help){
		console.log("Check README");	

// the following code snippet from optimist README.md has error below		
		    
//		var s = fs.createReadStream(argv.file);
//
//		var lines = 0;
//		s.on('data', function (buf) {
//		    lines += buf.toString().match(/\n/g).length;
//		});
//		s.on('end', function () {
//		    console.log(lines);
//		});

//		fs.js:404
//		  binding.open(pathModule._makeLong(path),
//		          ^
//		TypeError: path must be a string
//		    at Object.fs.open (fs.js:404:11)

		
		var helpstr = 
"	Usage: nodeclipse [arguments] \n\
\n\
	Arguments: \n\
	  -c, --create <name>      create project folder and prepare it \n\
	  -u, --use <template>     create project folder (using specified template folder) and prepare it \n\
	  -p, --prepare            prepare for import, i.e. add needed `.project` file (default action)\n\
	  -n, --name [<name>]      project name (default is folder name)\n\
	  -h, --help               Check README\n\
	  -v, --version            print nodeclipse CLI's version\n\
	  -V, --verbose            be verbose\n\
\n\
	Templates are just folders in this project sources:\n\
	  hello-world              The famous hello world HTTP server in 6 lines\n\
	  hello-coffee	           The same server written in CoffeeScript\n\
	  hello-typescript         The same server written in TypeScript\n\
";		
		
	  console.log(helpstr);
		return;
	}
	if (version){
		console.log("Nodeclipse CLI 0.3.0");	// BUG #71	
		return;
	}
	if (projectFileExists) {
		console.log(".project file already exists!");
		return;
	}
	
	//copyFromTemplates
	if (create){
		if (create.toString().length===0){
			console.error("Folder/project name is required for creation!");
			//process.exit(1);
			return;
		}
		mkdir(create);
		cd(create);
		if (verbose) console.log("Created project/folder: " + create);
	}
	
	var templatesfolder = __dirname;
	if (debug) console.log("Templates folder is: " + templatesfolder);

	// common-templates //TODO not copying .files
	var fromfolder = path.join(templatesfolder, 'common-templates', '/*') 
	if (verbose) console.log("Copying from "+ fromfolder );
	cp( '-A', fromfolder, '.' )
	
	
	if (use){
		//cp(__dirname+use+'/*','.');
		var fromfolder = path.join(templatesfolder, use, '/*') 
		if (verbose) console.log("Copying from "+ fromfolder );
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
		if (verbose) console.log('The file "'+destfile+'" was created !');
		console.log('In Eclipse/Enide select File -> Import... -> General / Existing Projects into Workspace');
		console.log('and enter current directory: '+curfolder);
	});
	
	if (debug) console.log(str); // ''.toString()
	
	inviteToSiteAsTheLastLine();
}
var inviteToSiteAsTheLastLine = function () {
	setTimeout(inviteToSite,100);
}
var inviteToSite = function () {
	console.log("\nCheck http://www.nodeclipse.org/ for news.");
}
