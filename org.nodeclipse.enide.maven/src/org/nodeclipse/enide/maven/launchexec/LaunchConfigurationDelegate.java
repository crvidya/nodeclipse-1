package org.nodeclipse.enide.maven.launchexec;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
//import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.RuntimeProcess;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.common.preferences.CommonDialogs;
import org.nodeclipse.enide.maven.Activator;
//import org.nodeclipse.debug.util.Constants;
//import org.nodeclipse.debug.util.VariablesUtil;
//import org.nodeclipse.ui.Activator;
//import org.nodeclipse.ui.preferences.Dialogs;
//import org.nodeclipse.ui.preferences.PreferenceConstants;
//import org.nodeclipse.ui.util.NodeclipseConsole;
import org.nodeclipse.enide.maven.preferences.MavenConstants;
import org.nodeclipse.enide.maven.util.NodeclipseLogger;
import org.nodeclipse.enide.maven.util.VariablesUtil;

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

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		//{ copy-paste part
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		
		// Using configuration to build command line	
		List<String> cmdLine = new ArrayList<String>();
		
		// Maven installation path should be stored in preference.
		String mavenPath= preferenceStore.getString(MavenConstants.MAVEN_PATH);
		// Check if the maven location is correctly configured
		File mavenFile = new File(mavenPath);
		if(!mavenFile.exists()){
			// If the location is not valid than show a dialog which prompts the user to goto the preferences page
			CommonDialogs.showPreferencesDialog(MavenConstants.PREFERENCES_PAGE,
					"Maven installation is not correctly configured.\n\n"
					+ "Please goto Window -> Preferences -> "+MavenConstants.PREFERENCE_PAGE_NAME
					+" and configure the correct location");
			return;
		}			
		cmdLine.add(mavenPath);
		
		if (preferenceStore.getBoolean(MavenConstants.MAVEN_OPTION_DEBUG))
			cmdLine.add("-X");
		if (preferenceStore.getBoolean(MavenConstants.MAVEN_OPTION_OFFLINE))
			cmdLine.add("-o");
		if (preferenceStore.getBoolean(MavenConstants.MAVEN_OPTION_QUIET))
			cmdLine.add("-q");

		String mavenOptionAlternativeSettings = preferenceStore.getString(MavenConstants.MAVEN_OPTION_ALTERNATIVE_SETTINGS);
		if("".equals(mavenOptionAlternativeSettings)){
		// Check if the settings.xml location is correctly configured
		File mavenOptionAlternativeSettingsFile = new File(mavenOptionAlternativeSettings);
		if(!mavenOptionAlternativeSettingsFile.exists()){
			// If the location is not valid than show a dialog which prompts the user to goto the preferences page
			CommonDialogs.showPreferencesDialog(MavenConstants.PREFERENCES_PAGE,
					"Alternative settings.xml is not correctly configured.\n\n"
					+ "Please goto Window -> Preferences -> "+MavenConstants.PREFERENCE_PAGE_NAME
					+" and configure the correct location");
			return;
		}			
		cmdLine.add("-s "+mavenOptionAlternativeSettings);
		}
		
		//} copy-paste part

		
		//TODO extract
		
//		@Override // compile exec:java -Dexec.mainClass=package.Class
//		protected void specialOptions(ILaunchConfiguration configuration,
//				IPreferenceStore preferenceStore, List<String> cmdLine) throws CoreException {
			
			String mavenOptions= preferenceStore.getString(MavenConstants.MAVEN_OPTIONS);
			
			//mvn compile exec:java -Dexec.mainClass=example.Example
			// WARNING passing all parameters one-by-one fails on Windows
			// for what I saw in JDK source Windows magic =variables
			// i.e. can't pass '=' this way
			//			cmdLine.add("compile");
			//			cmdLine.add("exec:java");

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
//		}
		
		
		
		//workingDirectory is project root - differs
		String workingDirectory = configuration.getAttribute(MavenConstants.ATTR_WORKING_DIRECTORY, "");
		File workingPath = null;
		if(workingDirectory.length() > 0) {
			workingDirectory = VariablesUtil.resolveValue(workingDirectory);
			if(workingDirectory != null) {
				workingPath = new File(workingDirectory);
			}
		}
		if (workingPath == null){
			workingPath = new File(project.getLocation().toOSString());
		}
		
		
		//env
		String[] envp = getEnvironmentVariables(configuration); 

		StringBuilder sb = new StringBuilder(100);
		for(String s : cmdLine) sb.append(s).append(' ');
		NodeclipseLogger.log(sb.append('\n').toString());
		
		String[] cmds = {};
		cmds = cmdLine.toArray(cmds);
		// Launch a process to debug.eg,
		Process p = DebugPlugin.exec(cmds, workingPath, envp);
		RuntimeProcess process = (RuntimeProcess)DebugPlugin.newProcess(launch, p, MavenConstants.PROCESS_MESSAGE);
		
	}

}
