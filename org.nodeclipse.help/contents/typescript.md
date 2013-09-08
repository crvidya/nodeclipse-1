Title:  Nodeclipse Online Help - TypeScript  



## TypeSctipt Editor

<a href="http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=1060522" class="drag">
<img src="images/installbutton.png"></a>

Current 1.0.0 version is unstable and causes Eclipse freezes.

### Hello world server in TypeScript

	declare function require(name:string);
	var http = require('http');
	http.createServer(function (req, res) {
	  res.writeHead(200, {'Content-Type': 'text/plain'});
	  res.end('Hello TypeScript\n');
	}).listen(1337, '127.0.0.1');
	console.log('Server running at http://127.0.0.1:1337/');
	
	
	