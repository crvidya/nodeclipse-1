
Maven generated documentation site

## Maven project site

First check [How to use markdown for maven project site?](http://stackoverflow.com/questions/14829190/how-to-use-markdown-for-maven-project-site)

`name` and `description` fields in pom.xml are usually optonal, but required for `mvn site`
 as it otherwise produces long links with word "Underfined" . 

	<name>org.nodeclipse.ui</name>
	<description>org.nodeclipse.ui</description>

### Generating

	mvn site:site
	mvn site:stage -DstagingDirectory=C:\TEMP\fullsite	
	

### Some snippets	

			<!-- http://maven.apache.org/plugins/maven-project-info-reports-plugin -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-project-info-reports-plugin</artifactId>
				<version>2.6</version>
				<!-- 
				<configuration>
					<webAccessUrl>http://svn.example.org/viewvc/project/trunk</webAccessUrl>
					<anonymousConnection>scm:svn:http://svn.example.org/repo/project/trunk</anonymousConnection>
					<developerConnection>scm:svn:https://svn.example.org/repo/project/trunk</developerConnection>
				</configuration>
				 -->
			</plugin>
			
		</plugins>
	</build>

