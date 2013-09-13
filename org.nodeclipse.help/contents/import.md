Title:  Nodeclipse Online Help - Import

## Import

1. Add file `.project` into your project root with following content (change name):

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

Now your project is Eclipse project :)

2. Import via standard File -> Import -> General -> Existing Projects into Workspace and select folder where the project is located. 	