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

### Restart

##### Option 1. Right-click on launched application in Debug View and select <kbd>Terminate and Relaunch</kbd>.
 (In Node perspective Debug View is visible by default) 
 
##### Option 2 by [igor](http://stackoverflow.com/questions/20682485/how-to-autorestart-node-js-application-in-eclipse-aptana-studio-on-source-change):

 1. Right-click on your project in *Project Explorer* > Properties > Builders
 2. New... > Program > OK
 3. Name: Terminate existing node.js process(es)
 4. Location: C:\Windows\System32\taskkill.exe (${env_var:SystemRoot}\System32\taskkill.exe did not work for me, it might for you)
 5. Working Directory: Browse Workspace... > select your project > OK
 6. Arguments: /IM node.exe /F
 7. Switch to *Build Options* tab and tick *During auto builds*, **untick** *Launch in background* > OK
 8. Create another builder: New... > Program > OK
 9. Name: Start &lt;your-project-name&gt;
 10. Location: C:\Program Files\nodejs\node.exe (you can try ${env_var:ProgramFiles}\nodejs\node.exe as well)
 11. Working directory: same as point #5
 12. Arguments: app.js (or any other file for application entry point)
 13. Switch to *Build Options* tab and **tick both** *During auto builds* and *Launch in background* > OK
 14. Turn on project autobuild: Window > Preferences > General > Workspace, tick *Build automatically* > OK
 15. Change default build order: Window > Preferences > General > Workspace > Build Order, untick *Use default build order* and remove all projects except your node.js project > OK
 16. Restart Aptana/Eclipse (There are bugs, so sometimes preference changes are just not saved and get lost. You have to double-check.)
 
<!-- 
Welcome to Nodeclipse. Your solution is added to [Nodeclipse Help Run page](https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.help/contents/run.md#running),
please edit. That is to answer "a better place to post it".

Alternatively, this question should have been broken into problem and solution part to fit stackoverflow.com Q&A format. Also constrains (like without plugin or Node.js modules) should be said in the topic as some percentage of stackoverflow.com users don't really read whole (especially long) question.

*Then my answer is just use Debug View.* (Actually it should be named Launch as it is both for Run and Debug) In your favorite perspective: Window -> Show View -> Others.. -> Debug. (In Nodeclipse Node perspective Debug View is visible by default) 
Then you can right-click on launched application in Debug View and select <kbd>Terminate and Relaunch</kbd>. That is more explicit and allows to run many applications.

I also don't understand why you first say "without plugins" and don't suggest "to install Nodeclipse", but in the end add "I hope Aptana Studio 3 will start supporting". Wouldn't it be another plugin/extension? Nodeclipse as of 0.10 has code completion for base Node.js modules and improved support for black background color themes. [Open an issue](https://github.com/Nodeclipse/nodeclipse-1/issues/new) when ready to discuss/work together. Nodeclipse was started by developers like you who stopped hoping and waiting and just did it. One small piece at a time. Just like you did.
--> 
 
##### Option 3 #57 running app.js with node-dev, forever, supervisor, nodemon etc

A general approach was selected [#57](https://github.com/Nodeclipse/nodeclipse-1/issues/57), however as I don't [yet]
 use any of Node.js module named above, some polishing is needed for this functionality, like [#118 The use case "Nodeclipse with nodemon"](https://github.com/Nodeclipse/nodeclipse-1/issues/118)

Start talking if you [care](https://github.com/Nodeclipse/nodeclipse-1/issues).

### Sources

Check [LaunchConfigurationDelegate.java](https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.debug/src/org/nodeclipse/debug/launch/LaunchConfigurationDelegate.java)
for launching Node.js logic.

### Contribute

<a href="https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.help/contents/run.md" target="_blank">Edit online on GitHub</a>
