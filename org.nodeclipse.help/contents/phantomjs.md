Title:  Nodeclipse Help - Phanthom.js


## PhantomJS

PhantomJS is a headless WebKit scriptable with a JavaScript API. It has fast and native support
 for various web standards: DOM handling, CSS selector, JSON, Canvas, and SVG.

Web page <http://phantomjs.org>  

Sources <https://github.com/ariya/phantomjs>  
Examples <https://github.com/ariya/phantomjs/wiki/Examples> are also included into distribution.  

APIs <https://github.com/ariya/phantomjs/wiki/API-Reference#module-api>

## PhantomJS in Nodeclipse

As of v0.7 there is no special project type. (e.g. you can create Node.js project
 to use JSHint validation and `npm` to get some ComonJS moduules)
Use right-click <kbd>Run As -> PhantomJS Script</kbd> . 

<a name="module-api" />
# Module API #
The Module API is modeled after [CommonJS Modules](http://wiki.commonjs.org/wiki/Modules/1.1.1) is available. Up through PhantomJS 1.6, the only supported modules that were built in:

 * [webpage](https://github.com/ariya/phantomjs/wiki/API-Reference-WebPage)
 * [system](https://github.com/ariya/phantomjs/wiki/API-Reference-System)
 * [fs](https://github.com/ariya/phantomjs/wiki/API-Reference-FileSystem)
 * [webserver](https://github.com/ariya/phantomjs/wiki/API-Reference-WebServer)
 * [child_process](https://github.com/ariya/phantomjs/wiki/API-Reference-ChildProcess)

As of PhantomJS 1.7, however, users can reference their own modules from the file system using [`require`](#require) as well.

<a name="require" />
## Function: `require` ##
To support the Module API, a `require` function modeled after [CommonJS Modules' Require](http://wiki.commonjs.org/wiki/Modules/1.1.1#Require) is globally available. General usage:
```js
var server = require('webserver').create();
var Awesome = require('MyAwesomeModule');
Awesome.do();
```

### Render Multiple URLs to file

```javascript
// Render Multiple URLs to file

var RenderUrlsToFile, arrayOfUrls, system;

system = require("system");

/*
Render given urls
@param array of URLs to render
@param callbackPerUrl Function called after finishing each URL, including the last URL
@param callbackFinal Function called after finishing everything
*/
RenderUrlsToFile = function(urls, callbackPerUrl, callbackFinal) {
    var getFilename, next, page, retrieve, urlIndex, webpage;
    urlIndex = 0;
    webpage = require("webpage");
    page = null;
    getFilename = function() {
        return "rendermulti-" + urlIndex + ".png";
    };
    next = function(status, url, file) {
        page.close();
        callbackPerUrl(status, url, file);
        return retrieve();
    };
    retrieve = function() {
        var url;
        if (urls.length > 0) {
            url = urls.shift();
            urlIndex++;
            page = webpage.create();
            page.viewportSize = {
                width: 800,
                height: 600
            };
            page.settings.userAgent = "Phantom.js bot";
            return page.open("http://" + url, function(status) {
                var file;
                file = getFilename();
                if (status === "success") {
                    return window.setTimeout((function() {
                        page.render(file);
                        return next(status, url, file);
                    }), 200);
                } else {
                    return next(status, url, file);
                }
            });
        } else {
            return callbackFinal();
        }
    };
    return retrieve();
};

arrayOfUrls = null;

if (system.args.length > 1) {
    arrayOfUrls = Array.prototype.slice.call(system.args, 1);
} else {
    console.log("Usage: phantomjs render_multi_url.js [domain.name1, domain.name2, ...]");
    arrayOfUrls = ["www.google.com", "www.bbc.co.uk", "www.phantomjs.org"];
}

RenderUrlsToFile(arrayOfUrls, (function(status, url, file) {
    if (status !== "success") {
        return console.log("Unable to render '" + url + "'");
    } else {
        return console.log("Rendered '" + url + "' at '" + file + "'");
    }
}), function() {
    return phantom.exit();
});
```

### Command line options

```
	D:\Progs\phantomjs-1.9.2-windows>phantomjs -h
	Usage:
	   phantomjs [switchs] [options] [script] [argument [argument [...]]]
	
	Options:
	  --cookies-file=<val>                 Sets the file name to store the persistent cookies
	  --config=<val>                       Specifies JSON-formatted configuration file
	  --debug=<val>                        Prints additional warning and debug message: 'true' or 'false' (default)
	  --disk-cache=<val>                   Enables disk cache: 'true' or 'false' (default)
	  --ignore-ssl-errors=<val>            Ignores SSL errors (expired/self-signed certificate errors): 'true' or 'false' (default)
	  --load-images=<val>                  Loads all inlined images: 'true' (default) or 'false'
	  --local-storage-path=<val>           Specifies the location for offline local storage
	  --local-storage-quota=<val>          Sets the maximum size of the offline local storage (in KB)
	  --local-to-remote-url-access=<val>   Allows local content to access remote URL: 'true' or 'false' (default)
	  --max-disk-cache-size=<val>          Limits the size of the disk cache (in KB)
	  --output-encoding=<val>              Sets the encoding for the terminal output, default is 'utf8'
	  --remote-debugger-port=<val>         Starts the script in a debug harness and listens on the specified port
	  --remote-debugger-autorun=<val>      Runs the script in the debugger immediately: 'true' or 'false' (default)
	  --proxy=<val>                        Sets the proxy server, e.g. '--proxy=http://proxy.company.com:8080'
	  --proxy-auth=<val>                   Provides authentication information for the proxy, e.g. ''-proxy-auth=username:password'
	  --proxy-type=<val>                   Specifies the proxy type, 'http' (default), 'none' (disable completely), or 'socks5'
	  --script-encoding=<val>              Sets the encoding used for the starting script, default is 'utf8'
	  --web-security=<val>                 Enables web security, 'true' (default) or 'false'
	  --ssl-protocol=<val>                 Sets the SSL protocol (supported protocols: 'SSLv3' (default), 'SSLv2', 'TLSv1', 'any')
	  --ssl-certificates-path=<val>        Sets the location for custom CA certificates (if none set, uses system default)
	  --webdriver=<val>                    Starts in 'Remote WebDriver mode' (embedded GhostDriver): '[[<IP>:]<PORT>]' (default '127.0.0.1:8910')
	  --webdriver-logfile=<val>            File where to write the WebDriver's Log (default 'none') (NOTE: needs '--webdriver')
	  --webdriver-loglevel=<val>           WebDriver Logging Level: (supported: 'ERROR', 'WARN', 'INFO', 'DEBUG') (default 'INFO') (NOTE: needs '--webdriver')
	  --webdriver-selenium-grid-hub=<val>  URL to the Selenium Grid HUB: 'URL_TO_HUB' (default 'none') (NOTE: needs '--webdriver')
	  -w,--wd                              Equivalent to '--webdriver' option above
	  -h,--help                            Shows this message and quits
	  -v,--version                         Prints out PhantomJS version
	
	Any of the options that accept boolean values ('true'/'false') can also accept 'yes'/'no'.
	
	Without any argument, PhantomJS will launch in interactive mode (REPL).
	
	Documentation can be found at the web site, http://phantomjs.org.
```

### Contribute

<a href="https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.help/contents/phantomjs.md" target="_blank">Edit online on GitHub</a>
