Title:  Nodeclipse Help - Node.js Tutorials

### List of Node.js tutorials

- http://www.nodebeginner.org/ The Node Beginner Book by Manuel Kiessling
- [A Beginner’s Tutorial for Node.js](http://project70.com/nodejs/beginners-tutorial-node-js/)
- http://readwrite.com/2011/04/02/6-free-e-books-on-nodejs
- http://stackoverflow.com/questions/2353818/how-do-i-get-started-with-node-js  

### Books

- [Node: Up and Running](http://chimera.labs.oreilly.com/books/1234000001808) by Tom Hughes-Croucher

#### Sample 1

```javascript
var net = require('net')

var chatServer = net.createServer()

chatServer.on('connection', function(client) {
  client.write('Hi!\n');
  client.write('Bye!\n');

  client.end()
})

chatServer.listen(9000)
```

then run `telnet` to connect:

> $ telnet 127.0.0.1 9000

and then by and by you get

```javascript
var net = require('net')

var chatServer = net.createServer(), clientList = []

chatServer.on('connection', function(client) {
	client.name = client.remoteAddress + ':' + client.remotePort
	client.write('Hi ' + client.name + '!\n');

	clientList.push(client)

	client.on('data', function(data) {
		console.log(data);
		broadcast(data, client)
	})

	client.on('end', function() {
		clientList.splice(clientList.indexOf(client), 1)
	})

	client.on('error', function(e) {
		console.log(e)
	})

})

function broadcast(message, client) {
	var cleanup = []
	for ( var i = 0; i < clientList.length; i += 1) {
		if (client !== clientList[i]) {

			if (clientList[i].writable) {
				clientList[i].write(client.name + " says " + message)
			} else {
				cleanup.push(clientList[i])
				clientList[i].destroy()
			}

		}
	} // Remove dead Nodes out of write loop to avoid trashing loop index
	for (i = 0; i < cleanup.length; i += 1) {
		clientList.splice(clientList.indexOf(cleanup[i]), 1)
	}
}

chatServer.listen(9000)
```

#### Sample 2 Let’s Build Twitter

Example 2-15. Adding a basic API

```javascript
var express = require('express')

var app = express.createServer()
app.listen(8000)

var tweets = []

app.get('/', function(req, res) {
  res.send('Welcome to Node Twitter')
})

app.post('/send', express.bodyParser(), function(req, res) {
  if (req.body && req.body.tweet) {
    tweets.push(req.body.tweet)
    res.send({status:"ok", message:"Tweet received"})
  } else {
    //no tweet?
    res.send({status:"nok", message:"No tweet received"})
  }
})

app.get('/tweets', function(req,res) {
  res.send(tweets)
})
```

Example 2-16. A test for the POST API

```javascript
var http = require('http'),
    assert = require('assert')

var opts = {
  host: 'localhost',
  port: 8000,
  path: '/send',
  method: 'POST',
  headers: {'content-type':'application/x-www-form-urlencoded'}
}

var req = http.request(opts, function(res) {
  res.setEncoding('utf8')

  var data = ""
  res.on('data', function(d) {
    data += d
  })

  res.on('end', function() {
    assert.strictEqual(data, '{"status":"ok","message":"Tweet received"}')
  })
})

req.write('tweet=test')
req.end()
```

### Contribute

<a href="https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.help/contents/tutorials.md" target="_blank">Edit online on GitHub</a>
