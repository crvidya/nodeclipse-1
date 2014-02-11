/* 
 * Nodeclipse CLI Installer, Copyright 2014 Paul Verest within Nodeclipse, MIT license http://www.nodeclipse.org/
 */
/*
The Eclipse runtime options
 http://help.eclipse.org/kepler/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Freference%2Fmisc%2Fruntime-options.html
using p2 director
 https://wiki.eclipse.org/Equinox/p2/Director_application
 http://help.eclipse.org/indigo/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Fguide%2Fp2_director.html
 https://wiki.eclipse.org/Equinox_p2_director_application/Examples/Install_into_eclipse_using_SDKProfile

<targetProductFolder>/eclipsec.exe 
   -application org.eclipse.equinox.p2.director 
   -repository http://download.eclipse.org/releases/helios/
   -installIU org.eclipse.cdt.feature.group/<version>

Arguments Description 
    -application org.eclipse.equinox.p2.director: the application ID.
    -metadataRepository: a comma separated list of metadata repository URLs where the software to be installed can be found.
    -artifactRepository: a comma separated list of artifact repository URLs where the software artifacts can be found.
    -repository: a comma separated list of repository URL where each entry denotes colocated meta-data and artifact repositories.
    -installIU: a comma separated list of IUs to install. Each entry in the list is in the form <id> [ '/' <version> ]. If you are looking to install a feature, the identifier of the feature has to be suffixed with ".feature.group".
    -uninstallIU: a comma separated list of IUs to uninstall. Each entry in the list is in the form <id> [ '/' <version> ].
    -revert: Revert the installation to a previous state. The previous state can either be a tag (see -tag / -listtags) or a previous profile state [ the number representing the previous state of the profile as found in p2/org.eclipse.equinox.p2.engine/<profileId>/ ].
    -purgeHistory: Remove the history of the profile registry.
    -destination: the path of a folder in which the targeted product is located.
    -list: lists all IU's found in the given repositories in a property like format. The optional arguments can be an comma separated list of entries where each entry is in the form <id> [ '/' <version> ], or a p2 QL query prefixed with Q:, or Q:group as shortcut for groups.
    -listTags: list all the tags found for the given profile.
    -listInstalledRoots: list all the roots IUs found in the given profile. Each entry is the list in the form id / version
    -profile: the profile id containing the description of the targeted product. This ID is is defined by the eclipse.p2.profile property contained in the config.ini of the targeted product. For the Eclipse SDK the ID is "SDKProfile"
    -profileProperties: a comma separated list of <key>=<value> pair. The property org.eclipse.update.install.features=true will cause the update manager features to be installed.
    -bundlepool: the location of where the plug-ins and features will be stored. This value is only taken into account when a new profile is created. For an application where all the bundles are located into the plugins/ folder of the destination, set it to <destination>.
    -p2.os: the OS to use when the profile is created. This will be used to filter which OS specific installable units need to be installed.
    -p2.ws: the windowing system to use when the profile is created. This will be used to filter which WS specific installable units need to be installed.
    -p2.arch: the architecture to use when the profile is created. This will be used to filter which architecture specific installable units need to be installed.
    -roaming: indicates that the product resulting from the installation can be moved. This property only makes sense when the destination and bundle pool are in the same location. This value is only taken into account when the profile is created.
    -shared: use a shared location for the install. The path defaults to ${user.home}/.p2.
    -tag: a label. This allows to tag the profile state resulting from the operation being executed.
    -verifyOnly: only verify that the actions can be performed. Don't actually install or remove anything.
While doing these operations, you can disable the automatic mirror selection mechanism by setting the VM argument eclipse.p2.mirrors to false.

eclipsec -application org.eclipse.equinox.p2.director -repository http://www.nodeclipse.org/updates/ -list
OK

http://www.vijayp.ca/blog/2011/09/why-eclipses-check-for-updates-is-horribly-slow-and-how-to-fix-it/
 Add it after “eclipse”, or in eclipse.ini
-Declipse.p2.mirrors=false

org.nodeclipse.jjs.feature.feature.group[/0.10.0.201401270634]
Succeded with 
eclipsec -application org.eclipse.equinox.p2.director -repository http://www.nodeclipse.org/updates/ -installIU org.nodeclipse.jjs.feature.feature.group/0.10.0.201401270634 -vmargs -Declipse.p2.mirrors=false 
http://stackoverflow.com/questions/21574010/eclipse-how-to-pass-vm-arguments-from-command-line-without-changing-eclipse-i

eclipsec -application org.eclipse.equinox.p2.director -repository http://www.nodeclipse.org/updates/ -installIU org.nodeclipse.jjs.feature.feature.group/0.10.0.201401270634 -tag org.nodeclipse.jjs.feature.feature.group/0.10.0.201401270634 -vmargs -Declipse.p2.mirrors=false 
-tag works!
*/

var mappings = [
	{alias: 'egit', name: 'org.eclipse.egit.feature.group'}, //TODO check if works without ',org.eclipse.jgit.feature.group' and updates both
	{alias: 'git', name: 'gitaddon.feature.feature.group'},
	{alias: 'gfm', name: 'code.satyagraha.gfm.viewer.feature.feature.group'},
	{alias: 'gradle', name: 'org.nodeclipse.enide.editors.gradle.feature.feature.group'},
	{alias: 'hudson', name: 'org.eclipse.mylyn.hudson.feature.group'},
	{alias: 'icons', name: 'org.eclipse_icons.editor.feature.feature.group'},
	{alias: 'jjs', name: 'org.nodeclipse.jjs.feature.feature.group'},
	{alias: 'markdown', name: 'markdown.editor.feature.feature.group'},
	{alias: 'mongodb', name: 'net.jumperz.app.MMonjaDB.feature.group'},
	{alias: 'mongodb.shell', name: 'org.nodeclipse.mongodb.feature.feature.group'},
	{alias: 'moonrise', name: 'com.github.eclipseuitheme.themes.feature.feature.group'},
	{alias: 'nodejs', name: "org.nodeclipse.feature.group,org.chromium.sdk.feature.group,org.chromium.debug.feature.group,com.eclipsesource.jshint.feature.feature.group"}, //TODO org.nodeclipse.enide.nodejs.feature
	{alias: 'phantomjs', name: 'org.nodeclipse.phantomjs.feature.feature.group'},
	{alias: 'pluginslist', name: 'org.nodeclipse.pluginslist.feature.feature.group'},
	{alias: 'restclient', name: 'code.google.restclient.tool.feature.feature.group'},
	{alias: 'shelled', name: 'net.sourceforge.shelled.feature.group'},
	{alias: 'startexplorer', name: 'de.bastiankrol.startexplorer.feature.feature.group'},
	{alias: 'themes', name: 'net.jeeeyul.eclipse.themes.feature.feature.group'},
	{alias: 'wikitext', name: 'org.eclipse.mylyn.wikitext_feature.feature.group'}, // textile, mediawiki, tracwiki, twiki
	{alias: 'yaml', name: 'org.dadacoalition.yedit.feature.group'},
	
];

console.log('Nodeclipse CLI Installer');
var argv = process.argv; // 0 - node, 1 - app.js
//for (var i=0; i<argv.length; i++){
//	console.log(i + ': ' + argv[i]);
//}
//`===` does not compare strings well
if (argv.length === 2 
	|| argv[2]=='help' || argv[2]=='--help' || argv[2]=='-h' 
	|| ( argv[2]=='list' && !argv[3])
	|| !(argv[2]=='install' || argv[2]=='i' || argv[2]=='list') 
	)
{ 
//	process.argv.forEach(function(val, index, array) {
//		console.log(index + ': ' + val);
//	});
	//console.log("Nodeclipse CLI Installer Help");
	console.log('  Usage (from folder with eclipse): nodeclipse install <aliases> [exact.feature.name.feature.group]');
	//console.log('         nodeclipse list <repository>');
	var mappedAliases = '  Mapped aliases('+mappings.length+'): ';
	for (var mi=0; mi<mappings.length; mi++){
		mappedAliases += mappings[mi].alias+' ';
	}
	console.log(mappedAliases);
	if (argv[2]==='help'){
		console.log('mappings: '+JSON.stringify(mappings,null,2));
	}
	console.log('\n  Visit http://www.nodeclipse.org/ for News, post Shares, Installing details, Features list,' 
			+' Usage (incl Video, Demo) with all shortcuts, Help and Hints,'
			+' Support options, Where Helping needed, How to thank and Contact us, also History page.');
	process.exit();
};

// processing commands logic
var repository = 'http://www.nodeclipse.org/updates/';
if (argv[2]=='list'){
	var command = '-list';	
	var repository = argv[3];
}
var comma_separated_list = '';
for (var i=3; i<argv.length; i++){
	var argi = argv[i];
	var found = false;
	for (var mi=0; mi<mappings.length; mi++){
		if (argi===mappings[mi].alias){
			found = true;
			comma_separated_list += mappings[mi].name+',';
			break;
		}
	}
	if (found) continue;
	comma_separated_list += argi+','; //passing as is
}
// delete last comma
comma_separated_list = comma_separated_list.substring(0, comma_separated_list.length-1);

// executing
//--- section below can be re-used for scripts with hard-coded values // Copyright 2014 ... http://www.nodeclipse.org/
var child_process = require('child_process');
var spawn = child_process.spawn;
var isWin = /^win/.test(process.platform);
var what = isWin ? 'eclipsec' : 'eclipse'; // see [How do I run Eclipse?](https://wiki.eclipse.org/FAQ_How_do_I_run_Eclipse%3F)
//var repository = 'http://www.nodeclipse.org/updates/';

//var command = '-list';
var options = ['-nosplash', '-application', 'org.eclipse.equinox.p2.director', command, '-repository', repository]; //enough for -list

if ( command != '-list'){ // do install
	/*eclipsec -application org.eclipse.equinox.p2.director -repository http://www.nodeclipse.org/updates/ 
	-installIU org.nodeclipse.jjs.feature.feature.group/0.10.0.201401270634 -tag org.nodeclipse.jjs.feature.feature.group/0.10.0.201401270634
	-vmargs -Declipse.p2.mirrors=false 
	*/
	var command = '-installIU';
	//var command = '-uninstallIU';
	var version = '/0.10.0.201401270634';
	//var comma_separated_list = 'org.nodeclipse.enide.nodejs.feature.feature.group';
	var options = ['-nosplash', '-application', 'org.eclipse.equinox.p2.director', '-repository', repository,
	               command, comma_separated_list, '-tag', comma_separated_list, '-vmargs', '-Declipse.p2.mirrors=false'];
}

var spawned = spawn(what, options);

console.log('starting '+what+JSON.stringify(options));

spawned.stdout.setEncoding('utf8');
spawned.stdout.on('data', function (data) {
    console.log(data);
});
spawned.stderr.setEncoding('utf8');
spawned.stderr.on('data', function (data) {
    console.log(data);
});

spawned.on('close', function (code) {
    console.log(what+' process exit code ' + code);
});
