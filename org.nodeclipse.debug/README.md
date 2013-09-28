
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

NodeclispeDev console is in .ui package,
no need for 

	Import-Package: org.eclipse.ui.console


## TODO

http://wiki.eclipse.org/FAQ_How_do_I_write_to_the_console_from_a_plug-in%3F

#### How to write a hyperlink to an eclipse console from a plugin

This may be used to show coffee compilation errors as links.

http://stackoverflow.com/questions/591018/how-to-write-a-hyperlink-to-an-eclipse-console-from-a-plugin

	console = new MessageConsole("myconsole", null);
	console.activate();
	ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]{ console });
	
	IPath path = Path.fromOSString(filePath);
	IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
	FileLink fileLink = new FileLink(file, null, -1, -1, -1);
	console.addHyperlink(fileLink, 10, 5); 

### Many node instances running

currecntly `private static RuntimeProcess nodeProcess`, that is only one Node process may be running.

--debug-brk=5858

http://stackoverflow.com/questions/11947409/nodejs-eclipse-v8-deb

Port should be set per file basis, e.g. in Run Configuration

