
# Markdown dependencies

ATTENSION: There is no more using of git submodules. However `pom.xml` shows example of build for that case.

Currently there is no direct code dependencies on markdown modules.

- `Eclipse-Markdown-Editor-Plugin` is expected to be installed together with Nodeclipse-1.
The HTML Preview is added to Node perspective and Makrdown Editor feature is required for update site `org.nodeclipse.site` as follows:
`<feature url="features/markdown.editor.feature_0.2.3.jar" id="markdown.editor.feature" version="0.2.3">`

- `gfm_viewer`  is fully optional.

## Building from source (depricated)

Update site defines Markdown Editor dependency in `org.nodeclipse.site\category.xml`.
 You need build and install in your maven repository before Nodeclipse-1 build.
 (This is one time operation)

1. `git clone https://github.com/Nodeclipse/Eclipse-Markdown-Editor-Plugin.git`
2. `cd Eclipse-Markdown-Editor-Plugin\parent`
3. `mvn install`

Then just run `mvn package`. Run offline when to re-build `mvn clean package -o`. From Nodeclipse-1 base folder:

1. `mvn package`  