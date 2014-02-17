package org.nodeclipse.enide.maven.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.RuntimeProcess;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.common.preferences.CommonDialogs;
import org.nodeclipse.enide.maven.Activator;
import org.nodeclipse.enide.maven.preferences.MavenConstants;
import org.nodeclipse.enide.maven.util.NodeclipseLogger;
import org.nodeclipse.enide.maven.util.VariablesUtil;

/**
 * pom.xml Run As Maven build<br>
 * see LaunchConfigurationDelegate in .debug and .phantomjs, .jjs module for comparison.
 * 
 * @since 0.10
 * @author Paul Verest
 */
public class LaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	boolean isWindows = Platform.getOS().startsWith("win");
	IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
	protected File workingDirectoryDefault = null; //is different for different launch types
	private boolean warned = false;

	//DONE shared processing function for 2 launch types.      

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		//{ copy-paste part
		//IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		
		// Using configuration to build command line	
		List<String> cmdLine = new ArrayList<String>();
		
		// Maven home to use is stored in preference.
		//String mavenPath = preferenceStore.getString(MavenConstants.MAVEN_PATH);
		String mavenHomeToUse = preferenceStore.getString(MavenConstants.MAVEN_HOME_TO_USE);
		String mavenPath = mavenHomeToUse+ (isWindows?"\\bin\\mvn.bat":"/bin/mvn");
		// Check if the maven location is correctly configured
		File mavenFile = new File(mavenPath);
		if( ("".equals(mavenHomeToUse)) || (!mavenFile.exists()) ){
			// If the location is not valid than show a dialog which prompts the user to goto the preferences page
			CommonDialogs.showPreferencesDialog(MavenConstants.PREFERENCES_PAGE,
					"Maven home to use is not correctly configured.\n\n"
					+ "Please goto Window -> Preferences -> "+MavenConstants.PREFERENCE_PAGE_NAME
					+" and configure the correct location");
			return;
		}
		cmdLine.add(mavenPath);
		
		if (preferenceStore.getBoolean(MavenConstants.MAVEN_OPTION_SHOW_VERSION))
			cmdLine.add("-V"); //-V, --show-version
		if (preferenceStore.getBoolean(MavenConstants.MAVEN_OPTION_DEBUG))
			cmdLine.add("-X");
		if (preferenceStore.getBoolean(MavenConstants.MAVEN_OPTION_QUIET))
			cmdLine.add("-q");
		if (preferenceStore.getBoolean(MavenConstants.MAVEN_OPTION_FORCED_DEPENDENCIES_UPDATE))
			cmdLine.add("-U");
		if (preferenceStore.getBoolean(MavenConstants.MAVEN_OPTION_OFFLINE))
			cmdLine.add("-o");
		if (preferenceStore.getBoolean(MavenConstants.MAVEN_OPTION_TEST_SKIP))
			cmdLine.add("-Dmaven.test.skip=true");
		//    	TODO won't be so simple on Windows! 


		String mavenOptionAlternativeSettings = preferenceStore.getString(MavenConstants.MAVEN_OPTION_ALTERNATIVE_SETTINGS);
		if( !"".equals(mavenOptionAlternativeSettings) ){
			// Check if the settings.xml location is correctly configured
			File mavenOptionAlternativeSettingsFile = new File(mavenOptionAlternativeSettings);
			if(!mavenOptionAlternativeSettingsFile.exists()){
				// If the location is not valid than show a dialog which prompts the user to goto the preferences page
				CommonDialogs.showPreferencesDialog(MavenConstants.PREFERENCES_PAGE,
						"Alternative settings.xml location is not correctly configured.\n\n"
						+ "Please goto Window -> Preferences -> "+MavenConstants.PREFERENCE_PAGE_NAME
						+" and configure the correct location");
				return;
			}			
			cmdLine.add("-s "+mavenOptionAlternativeSettings);
		}
		
		//} copy-paste part
		
		specialOptions(configuration, preferenceStore, cmdLine); 

		launchTail(cmdLine,configuration,launch);
	}
	
	// -f pom.xml package
	protected void specialOptions(ILaunchConfiguration configuration,
	IPreferenceStore preferenceStore, List<String> cmdLine) throws CoreException {
		// special for `mvn package`
		String mavenOptions= preferenceStore.getString(MavenConstants.MAVEN_OPTIONS);
		if(!mavenOptions.equals("")) {
			String[] sa = mavenOptions.split(" ");
			for(String s : sa) {
				cmdLine.add(s);
			}			
		}
		
		String file = configuration.getAttribute("KEY_FILE_PATH",	"");
		String filePath = ResourcesPlugin.getWorkspace().getRoot().findMember(file).getLocation().toOSString();
		// path is relative, so cannot find it, unless get absolute path
		cmdLine.add("-f");  //  -f,--file <arg>                        Force the use of an alternate POM
		cmdLine.add(filePath);
		cmdLine.add("package");

		//workingPath = (new File(filePath)).getParentFile();
		//setWorkingDirectoryDefault(filePath);
		workingDirectoryDefault = (new File(filePath)).getParentFile();
		
}
	
//	protected File getWorkingDirectoryDefault(String filePath){
//	return (new File(filePath)).getParentFile();
//}
//	protected void setWorkingDirectoryDefault(String filePath){
//		workingDirectoryDefault = (new File(filePath)).getParentFile();
//	}
//	protected File getWorkingDirectoryDefault(){
//		return workingDirectoryDefault;
//	}
	

		
	protected void launchTail(
			List<String> cmdLine, ILaunchConfiguration configuration, //String mode,
			ILaunch launch //, IProgressMonitor monitor
				) throws CoreException {
		//{ copy-paste tail part 
		//TODO rename workingPath to workingDirectory, workingDirectory to workingDirectoryConfig
		
		//workingDirectory is pom.xml location - differs : filePath
		File workingPath = null;
		String workingDirectory = configuration.getAttribute(MavenConstants.ATTR_WORKING_DIRECTORY, "");
		if(workingDirectory.length() > 0) {
			workingDirectory = VariablesUtil.resolveValue(workingDirectory);
			if(workingDirectory != null) {
				workingPath = new File(workingDirectory);
			}
		}
		if (workingPath == null){
			//workingPath = (new File(filePath)).getParentFile();
			//File workingPath = getWorkingPath(configuration);
			//workingPath = getWorkingDirectoryDefault();
			workingPath = workingDirectoryDefault;
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
		//} copy-paste tail part
	}
	
//	protected String[] getEnvironmentVariables(ILaunchConfiguration configuration) throws CoreException {
//		return getEnvironmentVariables(configuration, null);
//	}
	/** Get EnvironmentVariables from ILaunchConfiguration
	 * and adds JAVA_HOME, M2_HOME, PATH, TEMP, SystemDrive, HOME</br>
	 * Used in .launch and .launchexec 
	 * @param configuration ILaunchConfiguration
	 * @return String[]
	 * @throws CoreException
	 */
	protected String[] getEnvironmentVariables(ILaunchConfiguration configuration) throws CoreException {
		//, IPreferenceStore preferenceStore
		Map<String, String> envm = new HashMap<String, String>();
		envm = configuration.getAttribute(MavenConstants.ATTR_ENVIRONMENT_VARIABLES, envm);
		String[] envp = new String[envm.size() + (2+2) + 4 + 2];
		int idx = 0;
		for(String key : envm.keySet()) {
			String value = envm.get(key);
			envp[idx++] = key + "=" + value;
		}
		envp[idx++] = "JAVA_HOME=" + System.getProperty("java.home"); //System.getenv("JAVA_HOME");
		//ERROR: M2_HOME not found in your environment.
		//Please set the M2_HOME variable in your environment to match the
		//location of the Maven installation
		// note: MAVEN_HOME does not substitute M2_HOME when called from Java
		envp[idx++] = "M2_HOME=" + preferenceStore.getString(MavenConstants.MAVEN_HOME_TO_USE); 
		//System.getenv("MAVEN_HOME");
		envp[idx++] = "JAVA_OPTS=" + System.getenv("JAVA_OPTS");
		envp[idx++] = "MAVEN_OPTS=" + preferenceStore.getString(MavenConstants.MAVEN_OPTS); 
		//+ #81
		envp[idx++] = "PATH=" + System.getenv("PATH");
		envp[idx++] = "TEMP=" + System.getenv("TEMP");
		envp[idx++] = "TMP=" + System.getenv("TMP");
		envp[idx++] = "SystemDrive=" + System.getenv("SystemDrive");
		//+
		envp[idx++] = "HOME=" + System.getenv("HOME");
		envp[idx++] = "USERPROFILE=" + System.getenv("USERPROFILE");
		
		if (!warned ){
			NodeclipseLogger.log("  Warning: JAVA_HOME, M2_HOME and others environment variables will be applied automatically to every `mvn` launch.\n");
			StringBuilder sb = new StringBuilder(100);
			for(int i=0; i<envp.length; i++){
				sb.append("  ").append(envp[i]).append('\n');	
			}
			NodeclipseLogger.log(sb.toString());
			warned = true;
		}
		return envp;
	}
}
