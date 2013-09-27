
## org.nodeclipse.debug

Running and debugging node applications

### Developing

Open `plugin.xml`, in "Testing" section click "Launch an Eclipse application"

#### New Run/debug definition

1. in `plugin.xml` add `LaunchShortcut`

    <extension   
          point= "org.eclipse.debug.ui.launchShortcuts" >   

2. create new `public class LaunchShortcutName implements ILaunchShortcut`


## notes

debug port 5858 was hard-coded

in 2 classes: [LaunchConfigurationDelegate.java](https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.debug/src/org/nodeclipse/debug/launch/LaunchConfigurationDelegate.java)
 & NodeDebugUtil

org.nodeclipse.debug\src\org\nodeclipse\debug\launch\LaunchConfigurationDelegate.java



--debug-brk=5858

http://stackoverflow.com/questions/11947409/nodejs-eclipse-v8-deb

Port should be set per file basis, e.g. in Run Configuration