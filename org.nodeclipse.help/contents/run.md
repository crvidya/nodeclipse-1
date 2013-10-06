Title:  Nodeclipse Online Help - Run  


## Running

Right-click `.js` file with Node.js app and select "Run As -> Node Application" (similar to Java applications).

Press <kbd>Ctrl+F11</kbd> to run [again].

### Warning 0.6 behavior

In Nodeclipse version 0.6 add possibility to run Node.js app with monitor (see Monitor page)
 or launch coffee util to compile `*.coffee` files. However these have ran into
 problem <http://stackoverflow.com/questions/19157302/eclipse-plugin-development-saved-launchconfiguration-overrides-launchtype> 
 That will be fixed as soon as solution is found.

### Run Configuration

Right-click `.js` file with Node.js app and select "Run As -> Run Configuration..."

You can configure to pass

- program arguments
- Node arguments
- set working directory

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

### Sources

Check [LaunchConfigurationDelegate.java](https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.debug/src/org/nodeclipse/debug/launch/LaunchConfigurationDelegate.java)
for launching Node.js logic.
