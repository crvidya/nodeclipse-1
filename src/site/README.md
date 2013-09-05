
## Maven project site

First check [How to use markdown for maven project site?](http://stackoverflow.com/questions/14829190/how-to-use-markdown-for-maven-project-site)

`name` and `description` fields in pom.xml are usually optonal, but required for `mvn site`
 as it otherwise produces long links with word "Underfined" . 

	<name>org.nodeclipse.ui</name>
	<description>org.nodeclipse.ui</description>
