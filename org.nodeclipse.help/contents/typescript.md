Title:  Nodeclipse Online Help - TypeScript  



## TypeSctipt Editor

<a href="http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=1060522" class="drag">
<img src="images/installbutton.png"></a>

Current 1.0.0 version is unstable and causes Eclipse freezes.

### Hello world server in TypeScript

```typescript
declare function require(name:string);
var http = require('http');
http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/plain'});
  res.end('Hello TypeScript\n');
}).listen(1337, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1337/');
```	
	
### tsc Util

<pre><code>
$ tsc
Version 0.9.0.1
Syntax:   tsc [options] [file ..]

Examples: tsc hello.ts
          tsc --out foo.js foo.ts
          tsc @args.txt

Options:
  -c, --comments              Emit comments to output
  -d, --declaration           Generates corresponding .d.ts file
  -b, --disallowbool          Throw error for use of deprecated "bool" type
  -m, --disallowimportmodule  Throw error for use of deprecated "module" keyword when referencing an external module. Only allow "require" keyword.
  -e, --exec                  Execute the script after compilation
  -h, --help                  Print this message
  --module KIND               Specify module code generation: "commonjs" (default) or "amd"
  --nolib                     Do not include a default lib.d.ts with global declarations
  --out FILE|DIRECTORY        Concatenate and emit output to single file | Redirect output structure to the directory
  --sourcemap                 Generates corresponding .map file
  --target VER                Specify ECMAScript target version: "ES3" (default), or "ES5"
  -v, --version               Print the compiler's version: 0.9.0.1
  -w, --watch                 Watch input files
  @<file>                     Insert command line options and files from a file.	
</code></pre>
  
### Contribute

<a href="https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.help/contents/typescript.md" target="_blank">Edit online on GitHub</a>
  