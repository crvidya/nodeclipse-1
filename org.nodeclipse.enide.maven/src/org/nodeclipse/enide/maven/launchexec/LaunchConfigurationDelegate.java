package org.nodeclipse.enide.maven.launchexec;

import java.io.File;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.enide.maven.preferences.MavenConstants;

/**
 * mvn compile exec:java -Dexec.mainClass=example.Example
 * *.java mvn exec<br>
 * see LaunchConfigurationDelegate in .debug and .phantomjs, .jjs module for comparison.
 * 
 * @since 0.10
 * @author Paul Verest
 */
public class LaunchConfigurationDelegate  
	extends org.nodeclipse.enide.maven.launch.LaunchConfigurationDelegate
	implements ILaunchConfigurationDelegate {

	@Override // compile exec:java -Dexec.mainClass=package.Class
	protected void specialOptions(ILaunchConfiguration configuration,
			IPreferenceStore preferenceStore, List<String> cmdLine) throws CoreException {
		
		String mavenOptions= preferenceStore.getString(MavenConstants.MAVEN_OPTIONS);
		
		//mvn compile exec:java -Dexec.mainClass=example.Example
		// WARNING passing all parameters one-by-one fails on Windows
		// for what I saw in JDK source Windows magic =variables
		// i.e. can't pass '=' this way

		String file = configuration.getAttribute("KEY_FILE_PATH",	"");
		IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(file);
		IProject project = res.getProject();
		IPath relPath = res.getProjectRelativePath(); // =src/main/java/maven/MainClass.java
		String packageClass = ""+relPath;
		// remove maven source folder
		if (packageClass.startsWith("src/main/java/")){
			packageClass = packageClass.substring("src/main/java/".length());
		} else if (packageClass.startsWith("src/")){
			packageClass = packageClass.substring("src/".length());
		} 
		// cut .java
		packageClass = packageClass.substring(0, packageClass.lastIndexOf('.')); 
		packageClass = packageClass.replace('/', '.');
//			cmdLine.add("-Dexec.mainClass="+packageClass );
		
		// as one string
		//cmdLine.add(mavenPath+" "+mavenOptions+" compile exec:java -Dexec.mainClass="+packageClass);
		
		cmdLine.add(mavenOptions+" compile exec:java -Dexec.mainClass="+packageClass);
		
		//workingPath = new File(project.getLocation().toOSString());	
		//setWorkingDirectoryDefault(project.getLocation().toOSString());
		workingDirectoryDefault = new File(project.getLocation().toOSString());
	}
}
