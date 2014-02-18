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
	var query '"Q:everything.select(x | x.properties ~= filter(\"(org.eclipse.equinox.p2.type.group=true)\"))"';
	var options = ['-nosplash', '-application', 'org.eclipse.equinox.p2.director', '-repository', repository,
	               command, comma_separated_list, '-tag', comma_separated_list, 
	               query,
	               '-vmargs', '-Declipse.p2.mirrors=false'];
}

var log2console = function (data) {
    console.log(data);
}
var onExitShowCode = function (code) {
    console.log(what+' process exit code ' + code);
}
var spawning = function (what, options, dataHandler, closeHandler) {
	var spawned = spawn(what, options);
	console.log('starting '+what+' '+options.join(' '));
	spawned.stdout.setEncoding('utf8');
	spawned.stdout.on('data', dataHandler);
	spawned.stderr.setEncoding('utf8');
	spawned.stderr.on('data',dataHandler);
	spawned.on('close', closeHandler);
	return spawned;
}
var spawned = spawning(what, options, log2console, onExitShowCode);

