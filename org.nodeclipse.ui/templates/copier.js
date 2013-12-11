
var argv = require('optimist')
			.alias('c','create') .describe('c', 'create project folder and prepare it')
			.alias('p','prepare').describe('p', 'prepare for import, i.e. add needed `.project` and other `.*` file ')
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
//console.dir(process.argv);

// arguments processing 
var help = argv.help || argv.h || (process.argv.length <= 2);
var version = argv.version || argv.v;
var create = argv.create || argv.c;
var use = argv.use || argv.u || argv.template || argv.t;
//var create = create || use;
if (!create && use){
	console.log("Template usage without folder creation!");
	return;
}
var prepare = create || argv.prepare || argv.p;
var name = argv.name || argv.n; // could be   create || 
var debug = false;
var verbose = debug || argv.verbose || argv.V;

var projectFileExists = false; 

fs.exists('./.project', function check(exists) {
	projectFileExists = exists;
	executeActions();
});

var pathIsDirecory = function (filepath) {
	stats = fs.statSync(filepath);
	return  stats.isDirectory();
	//fs.statSync(filepath).isDirectory()
}

//BUG #71 (on x64bit SDK output is less)

var executeActions = function () {
	//if (debug) console.log(require.main.filename); // = __filename
	//if (debug) console.dir(argv);
	//console.log('Passed parameters: name=%s, verbose=%s', argv.name, argv.verbose);

	if (help){

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
"  Usage: nodeclipse [arguments] \n\
\n\
  Arguments: \n\
    -c, --create <name>      create project folder and prepare it \n\
    -u, --use <template>     use/copy specified template when creating project \n\
    -p, --prepare            prepare for import, i.e. add needed `.project` file and other `.*`\n\
                              files ('.gitignore', '.jshintrc', '.settings/') if there is no `.project` yet\n\
    -n, --name [<name>]      project name (default is folder name)\n\
    -h, --help               this help screen\n\
    -v, --version            print nodeclipse CLI's version\n\
    -V, --verbose            be verbose\n\
\n\
  Templates are just folders in this project sources:\n\
    hello-world              The famous hello world HTTP server in 6 lines\n\
    hello-coffee             The same server written in CoffeeScript\n\
    hello-typescript         The same server written in TypeScript\n\
";

		console.log(helpstr);
		console.log("Check README.md and sources at "+__dirname);	
		return;
	}
	if (version){
		console.log("Nodeclipse CLI "+require('./package.json').version );		
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

	// common-templates 
	if (prepare){
		//var fromfolder = path.join(templatesfolder, 'common-templates', '/*') 
		if (verbose) console.log("Copying from "+ fromfolder );
	
		//cp( '-A', fromfolder, '.' )
		// not copying .files: #79 https://github.com/arturadib/shelljs/issues/79
		
		//console.log( fs.readdirSync(fromfolder) ); --> [ '.gitignore', '.jshintrc', '.settings', 'README.md' ]
		mkdir('.settings'); //HACK1
		//for file in find(fromfolder)
		var fromfolder = path.join(templatesfolder, 'common-templates')
		find(fromfolder).forEach( function copyFile(file) {
			if (verbose) console.log("Copying "+ file );
			// cp(file,'.'); // This does not recreate subfolders - DONE with HACK1
			
			// check and skip if the file is directory
			//if pathIsDirecory(file)
			if ( fs.statSync(file).isDirectory() )
				return true; //continue
			
			//HACK1
			if ( ~ file.indexOf('.settings') )// if contains // returns a truthy value if the substring is found, and a falsy value (0) if it isn’t. (-1 becomes 0)
				cp(file,'.settings/'); 
			else	
				cp(file,'.');   
		});
		
	}
	
	if (use){
		//cp(__dirname+use+'/*','.');
		var fromfolder = path.join(templatesfolder, use, '/*') 
		if (verbose) console.log("Copying from "+ fromfolder );
		cp( fromfolder, '.' )
	}
	
	// .project
	if (prepare){
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
	}
	
	inviteToSiteAsTheLastLine();
}
var inviteToSiteAsTheLastLine = function () {
	setTimeout(inviteToSite,100);
}
var inviteToSite = function () {
	console.log("\nCheck http://www.nodeclipse.org/ for news.");
}
