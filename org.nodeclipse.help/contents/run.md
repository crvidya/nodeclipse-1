Title:  Nodeclipse Online Help - Run  


## Running

Right-click `.js` file with Node.js app and select "Run As -> Node Application" (similar to Java applications).

Press <kbd>Ctrl+F11</kbd> to run [again].

### Run Configuration

Right-click `.js` file with Node.js app and select "Run As -> Run Configuration..."

You can configure to pass

- program arguments
- Node arguments
- set working directory

Since Nodeclipse 0.6 it is possible to set environment variables in a launch configuration. The "Environment" tab is not there in the Node launcher.

As a workaround you can also add another JS file that sets the variables before it calls the node app and run that instead.

e.g.

```javascript
setTimeout(function(){
process.env.NODE_ENV="development";
require("./app.js");
},100);
```