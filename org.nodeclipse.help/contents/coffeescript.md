Title:  Nodeclipse Online Help - CoffeeScript  


## CoffeeSctipt Editor

Included in [Enide](nodeclipse.org/enide) since 0.4.20

It has XText dependency, that can usually be automatically resolved by Eclipse (that was downloaded from eclipse.org)

for other Eclipse distributions use Enide or install prior from <http://download.eclipse.org/modeling/tmf/xtext/updates/composite/releases/>

### Hello world server in CoffeeScript

	http = require 'http'
	 
	http.createServer (req, res) ->
	  res.writeHead 200, {'Content-Type': 'text/plain'}
	  res.end 'Hello World\n'
	.listen 1337, '127.0.0.1'
	 
	console.log 'Server running at http://127.0.0.1:1337/'
	