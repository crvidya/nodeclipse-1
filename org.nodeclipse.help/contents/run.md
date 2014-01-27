Title:  Nodeclipse Online Help - Run  


## Running

Right-click `.js` file with Node.js app and select "Run As -> Node Application" (similar to Java applications).

Press <kbd>Ctrl+F11</kbd> to run [again].

### Running issues

- [#72 All nodejs apps fail to run (JDK 6 only?)](https://github.com/Nodeclipse/nodeclipse-1/issues/72)
is related to Eclipse and Java version that you use, so updating them would solve. Quote bjm88: 

	Ok working now for me. It does seem you have to uninstall nodeclipse then do Java 7 update ( i tried jre 64 bit and jdk 32 bit, both ok), 
	and set that new java 7 in your eclipse.ini file (format is important). Then reinstall nodeclipse and it will work.
	Example eclipse.ini entry:
	-vm
	C:/dev/java7/bin/javaw.exe
	-vmargs
	-Xms128m
	-Xmx1024m

- [#107 Failed to connect to Standalone V8 VM](https://github.com/Nodeclipse/nodeclipse-1/issues/107) even on JDK 1.6.0_30
was solved by running `node --debug-brk app.js` first.
Quote haroldjiang:

	yes,the solution is to run node --debug-brk app.js first.

see also Debug page.

### Run Configuration

You can configure node and apps parameters workspace wide in `Preferences -> Nodeclipse`
or per file:

Right-click `.js` file with Node.js app and select "Run As -> Run Configuration..."
(Hint: the easiest way to create LaunchConfiguration is actually run the file once.)

You can configure to pass

- program arguments
- Node arguments
- set working directory

To have LaunchConfiguration saved within Project folder, select path in `Shared File` field on `Common` Tab of LaunchConfiguration.
(Yes, dialog string is confusing)
<!--
http://stackoverflow.com/questions/8625060/store-run-configuration-with-project-in-eclipse
-->

Since Nodeclipse 0.5 it is possible to set environment variables in a launch configuration. 
The "Environment" tab is not there in the Node launcher.

As an alternative way you can also add another JS file that sets the variables before it calls the node app and run that instead.

e.g.

```javascript
setTimeout(function(){
  process.env.NODE_ENV="development";
  require("./app.js");
},100);
```

### Warning multiple launch configurations

In Nodeclipse version 0.6 add possibility to run Node.js app with monitor (see Monitor page)
 or launch coffee util to compile `*.coffee` files. However these have ran into
 problem <http://stackoverflow.com/questions/19157302/eclipse-plugin-development-saved-launchconfiguration-overrides-launchtype> 

### Sources

Check [LaunchConfigurationDelegate.java](https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.debug/src/org/nodeclipse/debug/launch/LaunchConfigurationDelegate.java)
for launching Node.js logic.

### Contribute

<a href="https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.help/contents/run.md" target="_blank">Edit online on GitHub</a>
