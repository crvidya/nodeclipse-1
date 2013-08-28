# Nodeclipse Help plugin

This is Eclipse plugin created by wizard.

Online help is set of .md files at <https://github.com/Nodeclipse/org.nodeclipse.help/tree/master/contents>

Content is to be authored in Markdown, then converted to HTML.

Online help URL <https://github.com/Nodeclipse/org.nodeclipse.help/tree/master/contents>

- [ ] TODO convert to Tycho build.

## Developing

get Nodeclipse NTS or SDK, or get all [plugins](http://www.nodeclipse.org/updates/list)

http://javatime.no/blog/inplace-activator-overview/
Works great in first time, then cann't refresh -> Use 'Launch Eclipse application' link from plugin.xml Overview tab.

using Markdown ->HTML conversion

Ideas:
- marked - quick but command line util is dumb
- https://github.com/aponxi/grunt-readme-generator

## Mavenize note

This is not yet maven project.

`mvn org.eclipse.tycho:tycho-pomgenerator-plugin:generate-poms -DgroupId=org.nodeclipse.help`
to generate pom.xml

Mavenized but requires parent to define Tycho and other settings.



 