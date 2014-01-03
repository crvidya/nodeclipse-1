Title:  Nodeclipse Online Help - Configuration  

## Configuration

Some recommended configuration (These steps are needed for every new workspace):

Goto Window->Preferences 

General select Show heap status  
General->Workspace -> Text file encoding select other UTF-8  
(if Eclipse 4.x Chrome Theme installed (Optional, recommended) )  
General->Appearance ->Theme select Jeeeyul's themes - Chrome Theme, restart Eclipse  
Team -> Git -> History : Check "Follow renames"  

Switch to Node perspective (e.g. Window -> Open Perspective -> Other ... -> Node)

Window -> Show View -> Other... -> REST Client -> REST Client Tool  
or Ctrl+3 type `rest`

REST Client Tool is also nice to use in Debug perspective.

#### Optional general Eclipse configuration

General -> Workspace -> Refresh on access
General -> Workspace -> Refresh using native hooks or polling (so that you don't need to press <kbd>F5</kbd>
 after <code>npm install<code> or other changes on file system)
General -> Workspace -> Text file encoding - select UTF-8 

General -> Editors -> Text Editors -> Show print margin, 120

General -> Editors -> Text Editors -> Show whitespace characters (just remeber where is this setting)
 
Take a time to explore other Eclipse setting, you may be rewarded with findings. 

### Nodeclipse Preferences

Check preferences ( Window -> Preferences ) for Node.js, PhantomJS, MongoDB and Nashorn related settings.

<!--
![](images/Nodeclipse-Preferences.png)
-->
 
## In previous versions

### Before 0.6

Window -> Show View -> Other... -> GFM Support -> GFM View  
(Very useful when exploring <code>node_modules</code> folders) 

#### File associations

Window -> Preferences -> General -> Editors -> File Associations <kbd>Add...</kbd>

- *.jade - associate with Scrapbook (from JDT)
- *.ejs - associate with HTML Editor (from WebTools, Aptana, etc)
 or better with Amateras HTML Editor (check on Nodeclipse Plugin List)

#### [NTS](http://www.nodeclipse.org/nts/) Only

Window -> Show View -> Other... -> MiniMap -> MiniMap
  
### Before 0.4
  
Window -> Show View -> Other... -> Markdown -> Markdown HTML Preview  
(Very useful when exploring <code>node_modules</code> folders) 

### Contribute

<a href="https://github.com/Nodeclipse/nodeclipse-1/blob/master/org.nodeclipse.help/contents/configuration.md" target="_blank">Edit online on GitHub</a>
