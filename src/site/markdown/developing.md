
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

(see also Maven build in README.md in base)

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