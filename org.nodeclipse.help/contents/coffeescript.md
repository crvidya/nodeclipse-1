Title:  Nodeclipse Online Help - CoffeeScript  

### Hello world server in CoffeeScript

```coffeescript
http = require 'http'
 
http.createServer (req, res) ->
  res.writeHead 200, {'Content-Type': 'text/plain'}
  res.end 'Hello World\n'
.listen 1337, '127.0.0.1'
 
console.log 'Server running at http://127.0.0.1:1337/'
```

### Running/debugging

Nodeclipse since version 0.6 allows to run and debug .coffee file directly.
Node.js command line tool 'coffee' is used.	
Note that when debugging, source map are not implemeted, and execution start from this coffee tool
 that compiles your module.
 Stay in Node perspective until compilation to see your modules appear in V8 virtual project.
 Then open those files and set breakpoints.
 
### coffee Util

```
$coffee --help

Usage: coffee [options] path/to/script.coffee -- [args]

If called without options, `coffee` will run your script.

  -b, --bare         compile without a top-level function wrapper
  -c, --compile      compile to JavaScript and save as .js files
  -e, --eval         pass a string from the command line as input
  -h, --help         display this help message
  -i, --interactive  run an interactive CoffeeScript REPL
  -j, --join         concatenate the source CoffeeScript before compiling
  -m, --map          generate source map and save as .map files
  -n, --nodes        print out the parse tree that the parser produces
      --nodejs       pass options directly to the "node" binary
  -o, --output       set the output directory for compiled JavaScript
  -p, --print        print out the compiled JavaScript
  -s, --stdio        listen for and compile scripts over stdio
  -l, --literate     treat stdio as literate style coffee-script
  -t, --tokens       print out the tokens that the lexer/rewriter produce
  -v, --version      display the version number
  -w, --watch        watch scripts for changes and rerun commands 
```

Run `coffee -c -w .` from project root to compile every time you save .coffee file.

It is very convenient to have this command running in Eclipse integrated Terminal.	 

### CoffeeSctipt Editor

CoffeeSctipt Editor 0.3.0 (as version show) is in early stage, and looking for contributors familiar with XTend
or just brave enough to dive into [code](https://github.com/Nodeclipse/coffeescript-eclipse).

Included in [Enide](www.nodeclipse.org/enide) since 0.4.20

It has XText dependency, that can usually be automatically resolved by Eclipse (that was downloaded from eclipse.org)

for other Eclipse distributions use [CoffeeScript Editor quick installer](http://marketplace.eclipse.org/content/coffeescript-editor-quick-installer) 
or install prior from <http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases/>

### Contribute

<a href="https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.help/contents/coffeescript.md" target="_blank">Edit online on GitHub</a>
	 