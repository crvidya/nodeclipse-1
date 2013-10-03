Title:  Nodeclipse Help - Node.js

### Node.js instances

Nodeclipse lets you define exact location of Node.js installations, or with checkbox "just node" let OS to find `node`
on current PATH.

Hint: if you have 2 or more Node.js instances, let the newest be on PATH and select older in "Node path:".
Then by checking/unchecking "just node" you can switch to and from the newest Node.js.

### Node.js sources

If you got Node.js via pre-built installer, visit <http://www.nodejs.org/download/> again to get Node.js sources
and extract archive to a folder. Now you also have local copy of API documentations.

Node.js sources have "Node.js Core Modules" in `lib` folder, that may be useful.

Add following snippet to project `.project` file to reference Node.js sources.

```xml
<linkedResources>
	<link>
		<name>nodejs-sources-lib</name>
		<type>2</type>
		<location>E:/Enide/node-sources/node-v0.10.19/lib/</location>
	</link>
</linkedResources>
``` 

![](images/nodejs-sources-lib.PNG) 


### Node.js modules

Node.js modules (in `node_modules`) usually have `README.md` file.
To see `README.md` as it look like on GitHub, right-click it and select `Show in GFM view`.
Check also "Markdown" and "Github Flavored Markdown (GFM)" help pages.
