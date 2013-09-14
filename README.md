# Nodeclipse - Node.js support in Eclipse IDE

[![Build Status](https://secure.travis-ci.org/Nodeclipse/nodeclipse-1.png)](http://travis-ci.org/Nodeclipse/nodeclipse-1)
<a href="http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=759140" title="Drag and drop into a running Eclipse
 to install Nodeclipse"><img src="http://marketplace.eclipse.org/sites/all/modules/custom/marketplace/images/installbutton.png"/></a>

![nodeclipse-logo](Nodeclipse_logo_light.png)

**Table of Contents**  *generated with [DocToc](http://doctoc.herokuapp.com/)*

- [Nodeclipse - Node.js support in Eclipse IDE](#nodeclipse---nodejs-support-in-eclipse-ide)
	- [Vision](#vision)
	- [Features](#features)
	- [Installing](#installing)
	- [Usage](#usage)
	- [What's new](#whats-new)
	- [Roadmap](#roadmap)
		- [1.0](#10)
		- [Next](#next)
		- [Ideas, proposals, work-in-progress etc.](#ideas-proposals-work-in-progress-etc)
		- [Shelved](#shelved)
	- [Developing](#developing)
		- [First tiny steps](#first-tiny-steps)
		- [Before starting development, please do](#before-starting-development-please-do)
		- [Preparing environment](#preparing-environment)
			- [Recommended way](#recommended-way)
				- [Other way, not recommended way](#other-way-not-recommended-way)
		- [Maven build](#maven-build)
		- [Code standard](#code-standard)
		- [Update forked project](#update-forked-project)
		- [Send Pull Request steps](#send-pull-request-steps)
		- [Testing](#testing)
	- [Contributors](#contributors)
	- [Contacts](#contacts)
	- [For Bloggers and Users of Twitter, Flickr, LinkedIn, Weibo etc.](#for-bloggers-and-users-of-twitter-flickr-linkedin-weibo-etc)
	- [Spread the words](#spread-the-words)
		- [Hot requests](#hot-requests)
		- [Interesting and useful Links](#interesting-and-useful-links)
			- [Other Node IDEs](#other-node-ides)
			- [Other Eclipse-based IDEs](#other-eclipse-based-ides)
			- [Hints & Notes](#hints--notes)

Moved from [https://github.com/tomotaro1065/nodeclipse](https://github.com/tomotaro1065/nodeclipse)
 to [https://github.com/Nodeclipse/nodeclipse-1](https://github.com/Nodeclipse/nodeclipse-1) .
 Initially based on <https://github.com/Nodeclipse/nodeclipse> .

> [Nodeclipse](http://www.nodeclipse.org/) ( sources [nodeclipse-1](https://github.com/Nodeclipse/nodeclipse-1))
 is [Eclipse](http://www.eclipse.org/) plugin for the [Node.js](http://www.nodejs.org/). 
The purpose of Nodeclipse is to create environment in 
which Node.js development is easy for any user from beginner to professional. 

Check [developers news](https://github.com/Nodeclipse/nodeclipse-blog#news)

## Vision

One-stop shop for Node.js tools.

We can't develop everything at once, but we let you know what are the best things around for Node.js development with Eclipse.

## Features

* Creating default structure for New Node Project and New Node Source File 
* JavaScript Syntax highlighting
* Content Assistant
* NPM support
* Debugging - Breakpoint, Trace, etc... via [Eclipse debugger plugin for V8](http://code.google.com/p/chromedevtools/)
* Generating Express project
* Support for [JSHint](http://www.jshint.com/).<br/>
	Download site: <code>http://github.eclipsesource.com/jshint-eclipse/updates/</code>
* Support for Juno, Kepler

## Installing
Drag and drop <a href="http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=759140" title="Drag and drop into a running Eclipse
 to install Nodeclipse"><img src="http://marketplace.eclipse.org/sites/all/modules/custom/marketplace/images/installbutton.png"/></a> into a running Eclipse to install Nodeclipse,  
or use Download site : <code>http://www.nodeclipse.org/updates</code>  

## Usage

Check out [website](http://www.nodeclipse.org/) and [watch video](http://www.nodeclipse.org/index2.htm).    
For debugging check [Using-Eclipse-as-Node-Applications-Debugger]( https://github.com/joyent/node/wiki/Using-Eclipse-as-Node-Applications-Debugger)

For [Markdown](http://daringfireball.net/projects/markdown/dingus): 

~~1. Window -> Show View -> Other... -> Markdown~~

2. Click inside "Markdown HTML Preview" view to refresh rendering
3. (Optional, may be useful because double whitespace is hard line break) 
	Show whitespace character via Preferences > General > Editors > Text Editors : checkbox labeled "Show whitespace characters"

	If [Node Express Project] menu does not appear under File->New, reset [Node] perspective by right-clicking on the [Node] button, which is located perspective switching area.<br/>
	![ResetPerspective](ResetPerspective.png)

## What's new 

<http://www.nodeclipse.org/>

See Changelong.md  
and also Changelong.md & History.md at [www.nodeclipse.org repository](https://github.com/Nodeclipse/www.nodeclipse.org).

## Roadmap

Check also [developers news](https://github.com/Nodeclipse/nodeclipse-blog#news).

We use [Semantic Versioning](http://semver.org/). Version is MAJOR.MINOR.PATCH, where  

- MAJOR indicates substantially new release, possibly with API change
- MINOR indicates updates, that are fully backward-compatible
- PATCH indicates that is bug-fix

Please participate in discussion, [propose and vote on new feature](http://nodeclipse.uservoice.com/forums/216804-general),
 on raise bugs on <https://github.com/Nodeclipse/nodeclipse-1/issues>

### 0.x 

- #1 [Kepler 4.3](http://www.eclipse.org/downloads/index-developer.php) support
- Improving Code Assist (new ideas needed)
- #26 Add CoffeeFileWizard
- Nodeclipse/www.nodeclipse.org#2 [Task] merge enide solution (hints, roadmap) and nodeclipse-blog into www.nodeclipse.org
- resource for developers [Nodeclipse.github.io](http://nodeclipse.github.io/)
* [t#4 [Low priority enhancement] Option to Run project default module (taken from package.json), 
 when righ-clicking project folder](https://github.com/tomotaro1065/nodeclipse/issues/4)
- (Optional) Support for Sublime Text
- Improving Code Assist with convention-based <code>completions.json<code>
- JSquared Editor

### 1.0

Is expected together with Node.js 1.0

### Next

### Ideas, proposals, work-in-progress etc.
- Improving Code Assist
- Debugging features without Chrome developer tools.  In other words, debugging on Node Editor
- Obfuscation and source-level debugging features using Source Maps
- Unit test support (how?)
- Easily deployment to Heroku (possibly via [Heroku Eclipse plugin](https://devcenter.heroku.com/articles/getting-started-with-heroku-eclipse))  
	Download site: https://eclipse-plugin.herokuapp.com/install

- Add Jade support	(http://blog.wookets.com/2011/10/how-to-compile-coffeescript-jade-stylus.html Posted 30th October 2011 by Sean Wesenberg)
- Integrate console window (library unknown) -> currently can use StartExplorer (bundled with Enide)
- Add CoffeeScript support via [coffeescript-eclipse plugin](https://github.com/adamschmideg/coffeescript-eclipse)  
	Download site: http://coffeescript-editor.eclipselabs.org.codespot.com/hg/  
	(also requires XText. See CoffeeScriptSet.p2f in Enide for quick install)

### Shelved

- [Eclipse Json Editor Plugin](http://sourceforge.net/p/eclipsejsonedit/wiki/Home/) Last Update: 2012-11-07
 (The JSON Editor is a simple plugin for the Eclipse IDE that provides:
  - Color text highlighting
  - An Outline Tree view
  - JSON validation
  - Text formatting - Text folding for the JSON data format.)	
  No Update site.
	
## Developing

### First tiny steps

1. Register & login within [GitHub](https://github.com/Nodeclipse)
2. Add stars to the projects, that you like
3. Fork to your account, learn the code 

### Before starting development, please do

1. Carefully read materials (this README)
2. Install and give thorough try
3. Contact developers, make friends
4. Say what you are going to do, before you head in. Share and discuss ideas.  
 (We had pull request that we could not merge, because it was not compatible with changes we were going to make.)

### Preparing environment

#### Recommended way

In Eclipse for Java or RCP developers

1. Copy to clipboard git repository URL: <code>https://github.com/Nodeclipse/nodeclipse-1.git</code>
2. File -> Import.. -> Git / Project from git

##### Other way, not recommended way

1. <code>git clone https://github.com/Nodeclipse/nodeclipse-1.git</code>
2. File -> Import -> Existing Projects into Workspace
3. Enter cloned folder path to [Select root directory].
![ImportProjects](ImportProjects.png)
4. Push [Finish] button.

### How to build it

(see also Maven build below)

It will be started building automatically when you import it into your workspace and whenever you save code which is changed.
If you got build error, probably you don't have installed JSDT yet.  Try as follows.

1. Select [Help]-[Install New Software...].
2. Select [Juno - http://download.eclipse.org/releases/juno] in [Work with] drop-down list.
3. Check [Web, XML, Java EE and OSGi Enterprise Development]-[JavaScript Development Tools].

### How to Run or Debug it

1. Select project folder, for instance org.nodeclipse.ui, on Package Explorer.
2. Open context menu by right-clicking.
3. Select [Run As / Debug As]-[Eclipse Application].
![RunOrDebug](RunOrDebug.png)

A new Eclipse workbench starts. Nodeclipse which you started to run or debug has been installed into this runtime Eclipse.

### Maven build

[Maven](http://maven.apache.org/) build (using [Tycho plugin](http://eclipse.org/tycho/)) is headless build, that doesn't require Eclipse.

From Nodeclipse-1 base folder just run `mvn package`. Run offline when to re-build `mvn clean package -o`. 

#### Install site.zip (quick and simple way)

1. Locate zip file under `org.nodeclipse.site\target` in Project Explorer, StartExplore-> Copy Resource Path to Clipboard
2. Help -> Install New Software ...
3. <kbd>Add...</kbd>
4. <kbd>Archive...</kbd>
5. Insert copied string into name and path.  
 E.g. `C:\Users\pverest\git\nodeclipse-056\org.nodeclipse.site\target\org.nodeclipse.site-0.5.0-SNAPSHOT.zip`
 
For the next time just select the zip entry from Work With drop-down list. 

#### Install from freshly built p2 repository (cool and long way)

2. `npm install http-server -g`  
3. `http-server org.nodeclipse.site\target\repository -p 8010`
4. start http://localhost:8010/  
5. // navigate `http://localhost:8010/` in browser to check  
5. Help->Install New Software, enter http://localhost:8010/ in Work With  

### Code standard

1. If you edited or created file, add yourself as @author in JavaDoc or comments
2. Import <code>CodeStyle_codetemplates.xml</code> from
 <a href="https://github.com/Nodeclipse/Nodeclipse.github.io/blob/master/CodeStyle_codetemplates.xml">Nodeclipse.github.io</a></p> 
	Instructions are inside the xml file.   
 	This will add @author in new files automatically. Use <kbd>Ctrl+Alt+J</kbd> to quickly insert in existing files. 	  

### Update forked project

If you forked into your account, after a while it is possible that code at original repository was updated.

With git command line you need to open project base folder, then

<pre><code>
git remote add upstream https://github.com/Nodeclipse/project-you-forked.git  
git fetch upstream  
git merge upstream/master   
git push origin master  
</code></pre>

With EGit you should do the same steps but within GUI.

### Send Pull Request steps

1. Push to your remote <code>origin</code> (your account)
2. Open GitHub page for your fork
3. Press <kbd>Pull Request</kbd>
4. Review Commits on Commits tab
5. Write description, press <kbd>Send pull request</kbd>

### Testing

Developers may try latest unreleased version at update URL:
<code>http://www.tomotaro1065.com/nodeclipse/staging/</code>

## Contributors

- LambGao 魔都 https://github.com/Nodeclipse (original creator v0.1.8)   
- Scott Elcomb https://github.com/psema4  (just forked)
- Tomoyuki Inagaki https://github.com/tomotaro1065 (debugging integration v0.2..0.5+) [blog](http://d.hatena.ne.jp/tomotaro1065/)   
- Paul Verest https://github.com/PaulVI/  (Vision, site, help, docs, all the research, finding plugings, less code, maven build; Enide; making it all to be next level)
 [DemoDays](http://demodays.github.io/), [Eclipse-China](http://eclipse-china.github.io/), [blog](https://github.com/PaulVI/blog)  
- Daniel Winterstein ([Markdown Editor](http://www.winterwell.com/software/markdown-editor.php))
- Pushkar Gupte https://github.com/pushkar85 (together for 0.4)
- Adam Schmideg (CoffeeScript Editor before 0.3)
- Robert Gründler

## Contacts

Do not hesitate to contact developers. 
Create issue or send [email to dev group](mailto:dev@nodeclipse.org).
Or skype by ID pverest, QQ 908781544.  
Visit [Nodeclipse Google Group](https://groups.google.com/forum/?hl=en&fromgroups#!forum/nodeclipse).


## For Bloggers and Users of Twitter, Flickr, LinkedIn, Weibo etc.

In case you plan to blog or tweet about the Nodeclipse plugin, please use the tag "#nodeclipse"
 in order to make it easier to find all the comments and pictures. Thanks a lot for telling the world about the project!  
  | 隸ｷ用#nodeclipse#譬・ｭｾ微博一下。  
  | Por favor, utilizar etiqueta #nodeclipse  
  | ツイートする時は、＃nodeclipseタグを使用してください。  
  | Bitte benutzen Sie tag #nodeclipse um zu twiten.


## Spread the words

Please let others know about this effort. Add links below:  

Sites that reference this project  
http://www.oschina.net/p/nodeclipse  
http://stackoverflow.com/questions/8025825/is-there-a-nodejs-plugin-for-aptana-studio  
http://stackoverflow.com/questions/8179369/debugging-node-js-with-eclipse  
http://stackoverflow.com/questions/7038961/node-js-in-eclipse-which-plugins-are-most-people-using  
https://groups.google.com/forum/#!msg/nodejs/ayLUeUOanzA/et6EEZppVjMJ  
http://stackoverflow.com/questions/15407334/eclipse-rcp-add-optional-dependencies  
http://stackoverflow.com/questions/12641679/nodejs-a-step-by-step-debugger-for-nodejs/15296275#15296275
http://cnodejs.org/topic/5149c795069911196df84af7   

### Hot requests

http://stackoverflow.com/questions/14533885/which-ide-supports-coffeescript-debugging-source-mapping-breakpoints-call-st  
http://stackoverflow.com/questions/10286364/coffeescript-editor-plugin-for-eclipse  
http://stackoverflow.com/questions/7057466/how-to-use-coffeescript-and-eclipse-together-in-windows  
http://stackoverflow.com/questions/3919977/what-ide-to-use-for-node-js-javascript asked Oct 13 '10 [closed] -> Vim, Cloud9 IDE, editors
http://www.iteye.com/news/23933        

### Interesting and useful Links

#### Other Node IDEs

- Nide http://coreh.github.com/nide/ v0.2 Last update 2012-04  
- JetBrains WebStorm or [IntelliJ IDEA](www.jetbrains.com/idea/features/nodejs.html) (commercial products)  
- Microsoft WebMatrix (free) or Visual Studio (commercial product)  
- CloudIDE [c9.io](https://c9.io) (cloud service)  
- Scripted https://github.com/scripted-editor/scripted (no news for 3 months) 
- Eclipse Orion (will it really support Node.js?) 
- komodo-ide http://www.activestate.com/komodo-ide (commercial product that nobody tried)  
- Netbeans have a [NodeJS plugin](http://plugins.netbeans.org/plugin/36653/nodejs)  

#### Other Eclipse-based IDEs

- Adobe Flash Builder
- ADT (Android Development Tools)
- Aptana Studio (PHP, Python, Rubi)
- Erlang IDE
- Groovy & Grails Tool Suite (GGTS)
- IBM Rational product line
- JBoss Developer Studio
- MyEclipse, from Genuitec is a commercial IDE 
- PyDev Python IDE
- SAP NetWeaver Developer Studio
- Scala IDE
- Spring Tool Suite
- Zend Studio (PHP) Commercial

#### Hints & Notes

http://wiki.eclipse.org/Tycho/Reference_Card  
http://stackoverflow.com/questions/10352089/how-do-i-build-an-eclipse-rcp-app-so-that-its-features-can-be-updated-automatica?rq=1  
http://stackoverflow.com/questions/14591472/installing-an-additional-feature-during-product-build-for-some-eclipse-versions  
http://stackoverflow.com/questions/10529859/how-to-include-video-in-jekyll-markdown-blog  
http://stackoverflow.com/questions/2330620/eclipse-i-turned-on-hidden-characters-now-i-cant-turn-off  
[Eclipse JavaScript Development Tools (JSDT)](http://eclipse.org/webtools/jsdt/) has only JS web support.     
add interesting stuff here...

