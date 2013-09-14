
# Nodeclipse build

## Build

- Simple build

clone and `mvn package`

- Build with Git Submodules

clone, init git submodules, enable submodules in `pom.xml` and `org.nodeclipse.site/pom.xml`, `mvn package`

### Build with Git Submodules

This one is not used, but may help in other projects

- command line

(Check org.nodeclipse.examples project)

- Eclipse EGit

when importing check "Clone submodules"

![](import-from-git-with-submodules-result.png)

![](import-from-git-with-submodules.png)