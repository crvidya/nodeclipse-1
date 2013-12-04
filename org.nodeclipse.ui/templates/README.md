

# Nodeclipse CLI

## Node.js Development with Eclipse or Enide Studio

Install with `npm install -g nodeclipse`

Usage: just run `nodeclipse` to add needed `.project` file to current directory

In Eclipse `File -> Import -> General / Existing Projects into Workspace`

Check <http://www.nodeclipse.org/> on how to get Nodeclipse or Enide Studio

## Commands

	  -n, --name <name>        project name (default is folder name)


## Other commands (TODO)

	$ nodeclipse -h
	Usage: nodeclipse [arguments]
	
	Arguments:
	  -c, --create <name>      create project folder and prepare it
	  -u, --use <template>     create project folder (using specified template folder) and prepare it
	  -p, --prepare            prepare for import, i.e. add needed `.project` file (default action)
	  -n, --name <name>        project name (default is folder name)
	  -v, --version            print nodeclipse CLI's version
	  -V, --verbose            be verbose
      -f, --force              force on non-empty directory (by default existing files are not updated)

Examples

nodeclipse --prepare project1

nodeclipse --create project2 | cd project2


## Eclipse Workspace and Project

Eclipse Workspace is just folder that contains Eclipse Projects.
Eclipse Projects is just folder with `.project` file.

### Developing CLI

<https://npmjs.org/doc/developers.html>

try with 'npm install . -g' before publishing

Project sources are at https://github.com/Nodeclipse/nodeclipse-1
under https://github.com/Nodeclipse/nodeclipse-1/tree/master/org.nodeclipse.ui/templates

#### Links

> The only feature that differentiates a command-line program from a library is the bin field in the package.json file.

<http://howtonode.org/how-to-module>