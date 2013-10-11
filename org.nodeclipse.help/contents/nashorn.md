

### `jjs` command line util

	C:\Program Files\Java\jdk1.8.0\bin>jjs.exe -v

	nashorn 1.8.0
	
	
	C:\Program Files\Java\jdk1.8.0\bin>jjs.exe -h

	jjs [<options>] <files> [-- <arguments>]
	        -D (-Dname=value. Set a system property. This option can be repeated.)
	
	        -cp, -classpath (-cp path. Specify where to find user class files.)
	
	        -doe, -dump-on-error (Dump a stack trace on errors.)
	                param: [true|false]   default: false
	
	        -fv, -fullversion (Print full version info of Nashorn.)
	                param: [true|false]   default: false
	
	        -fx (Launch script as an fx application.)
	                param: [true|false]   default: false
	
	        -h, -help (Print help for command line flags.)
	                param: [true|false]   default: false
	
	        -scripting (Enable scripting features.)
	                param: [true|false]   default: false
	
	        -strict (Run scripts in strict mode.)
	                param: [true|false]   default: false
	
	        -t, -timezone (Set timezone for script execution.)
	                param: <timezone>   default: Asia/Shanghai
	
	        -v, -version (Print version info of Nashorn.)
	                param: [true|false]   default: false
	
### `jrunscript` command line util	
	
	C:\Program Files\Java\jdk1.8.0\bin>jrunscript.exe  -h
	Usage: jrunscript [options] [arguments...]
	
	where [options] include:
	  -classpath <path>    Specify where to find user class files
	  -cp <path>           Specify where to find user class files
	  -D<name>=<value>     Set a system property
	  -J<flag>             Pass <flag> directly to the runtime system
	  -l <language>        Use specified scripting language
	  -e <script>          Evaluate given script
	  -encoding <encoding> Specify character encoding used by script files
	  -f <script file>     Evaluate given script file
	  -f -                 Interactive mode, read script from standard input
	                       If this is used, this should be the last -f option
	  -help                Print this usage message and exit
	  -?                   Print this usage message and exit
	  -q                   List all scripting engines available and exit
	
	If [arguments..] are present and if no -e or -f option is used, then first
	argument is script file and the rest of the arguments, if any, are passed
	as script arguments. If [arguments..] and -e or -f option is used, then all
	[arguments..] are passed as script arguments. If [arguments..], -e, -f are
	missing, then interactive mode is used.
	
	C:\Program Files\Java\jdk1.8.0\bin>jrunscript.exe  -q
	Language ECMAScript ECMA - 262 Edition 5.1 implemention "Oracle Nashorn" 1.8.0
	