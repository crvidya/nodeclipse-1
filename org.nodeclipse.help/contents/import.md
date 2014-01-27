Title:  Nodeclipse Help - Import

## Import

_1. Add `.project` file (3 option a,b,c,d)

a. (via shell) run `nodeclipse -p` from project root. It will add `.project` file and some configuration files.

b. (GUI) You can just select <code>File -> New -> Node.js Project</code>. It will add only new `.project` file.
And you have your project opened in Eclipse / Enide Studio.

c. create new project and learn what files there are.

d. (manualy, minimal) add file `.project` into your project root with following content (change name):

```xml
<?xml version="1.0" encoding="UTF-8"?>
<projectDescription>
	<name>MyNodeProject1</name>
	<comment></comment>
	<projects>
	</projects>
	<buildSpec>
		<buildCommand>
			<name>com.eclipsesource.jshint.ui.builder</name>
			<arguments>
			</arguments>
		</buildCommand>
	</buildSpec>
	<natures>
		<nature>org.nodeclipse.ui.NodeNature</nature>
		<nature>org.eclipse.wst.jsdt.core.jsNature</nature>
	</natures>
</projectDescription>
```

Now your project is Eclipse project :)

_2. Import via standard File -> Import -> General -> Existing Projects into Workspace and select folder where the project is located.
 	
### Contribute

<a href="https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.help/contents/import.md" target="_blank">Edit online on GitHub</a>
 	