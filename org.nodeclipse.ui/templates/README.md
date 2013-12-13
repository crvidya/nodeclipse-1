

# Nodeclipse CLI 

## Node.js Development with Eclipse or Enide Studio

Install with `npm install -g nodeclipse`

Usage: just run `nodeclipse -p` to add needed `.project` file to current directory

In Eclipse `File -> Import -> General / Existing Projects into Workspace`

Check <http://www.nodeclipse.org/> on how to get Nodeclipse or Enide Studio

## Commands

	Usage: nodeclipse [arguments]

	Arguments:
	  -c, --create <name>      create project folder <name> [using template] and prepare it
	  -u, --use <template>     use/copy specified template when creating project
	  -p, --prepare            prepare for import (default action), i.e. add needed `.project`
	  							and other `.*` files ('.gitignore', '.jshintrc', '.settings/') if there is no `.project` yet
	  -n, --name [<name>]      project name (default is folder name)
	  -h, --help               Check README
	  -v, --version            print nodeclipse CLI's version
	  -V, --verbose            be verbose

	Templates are just folders in this project sources:
	  hello-world              The famous hello world HTTP server in 6 lines
	  hello-coffee	           The same server written in CoffeeScript
	  hello-typescript         The same server written in TypeScript

## Other commands (TODO)

	$ nodeclipse -h
	Usage: nodeclipse [arguments]
	
      -f, --force              force on non-empty directory (by default existing files are not updated)

Examples:

	nodeclipse -p
	
	nodeclipse --prepare project1
	
	nodeclipse --create project2
	
	nodeclipse --create project3 --use hello-coffee
	
	nodeclipse -c project4 -u hello-coffee

## Eclipse Workspace and Project

Eclipse Workspace is just folder that contains Eclipse Projects.
Eclipse Projects is just folder with `.project` file.

## Developing Nodeclipse CLI

[#91](https://github.com/Nodeclipse/nodeclipse-1/issues/91)

Project sources are at https://github.com/Nodeclipse/nodeclipse-1
under https://github.com/Nodeclipse/nodeclipse-1/tree/master/org.nodeclipse.ui/templates

<https://npmjs.org/doc/developers.html>

try with `npm install . -g` before publishing

#### Links

> The only feature that differentiates a command-line program from a library is the bin field in the package.json file.

<http://howtonode.org/how-to-module>

## Ideas

- add link on Wizard Page
- options to refine .jshintrc

## News

Check <http://www.nodeclipse.org/>
