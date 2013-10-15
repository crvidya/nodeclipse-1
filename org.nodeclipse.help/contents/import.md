Title:  Nodeclipse Online Help - Import

## Import

You can just select File -> New -> Node.js Project. It will add only new `.project` file.

Or do it in longer way:

1. Add file `.project` into your project root with following content (change name):

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

2. Import via standard File -> Import -> General -> Existing Projects into Workspace and select folder where the project is located.
 	
### Contribute

<a href="https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.help/contents/import.md" target="_blank">Edit online on GitHub</a>
 	