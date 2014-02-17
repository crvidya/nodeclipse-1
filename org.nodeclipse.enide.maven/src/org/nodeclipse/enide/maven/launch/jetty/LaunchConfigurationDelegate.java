package org.nodeclipse.enide.maven.launch.jetty;

import java.io.File;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.enide.maven.preferences.MavenConstants;

/**
 * mvn -Djetty.port=9999 jetty:run</br>
 * 
 * @since 0.11
 * @author Paul Verest
 */
public class LaunchConfigurationDelegate  
	extends org.nodeclipse.enide.maven.launch.LaunchConfigurationDelegate
	implements ILaunchConfigurationDelegate {

	@Override
	protected void specialOptions(ILaunchConfiguration configuration,
			IPreferenceStore preferenceStore, List<String> cmdLine) throws CoreException {

		StringBuilder cmdLineSb = new StringBuilder(200);
		
		String mavenOptions= preferenceStore.getString(MavenConstants.MAVEN_OPTIONS);
//		if(!mavenOptions.equals("")) {
//			String[] sa = mavenOptions.split(" ");
//			for(String s : sa) {
//				cmdLine.add(s);
//			}			
//		}
		cmdLineSb.append(mavenOptions);
		
		String file = configuration.getAttribute(MavenConstants.KEY_FILE_PATH,	"");
		String filePath = ResourcesPlugin.getWorkspace().getRoot().findMember(file).getLocation().toOSString();
		// path is relative, so cannot find it, unless get absolute path
//		cmdLine.add("-f");  //  -f,--file <arg>                        Force the use of an alternate POM
//		cmdLine.add(filePath);
		cmdLineSb.append(" -f ").append(filePath);
		
		int jettyPort = preferenceStore.getInt(MavenConstants.MAVEN_OPTION_JETTY_PORT);
		if (jettyPort!=0){
			//cmdLine.add("-Djetty.port="+jettyPort);
			cmdLineSb.append(" -Djetty.port=").append(filePath);
		}
		
		//cmdLine.add("jetty:run"); 
		cmdLineSb.append(" jetty:run");

		cmdLine.add(cmdLineSb.toString());

		//workingPath = (new File(filePath)).getParentFile();
		//setWorkingDirectoryDefault(filePath);
		workingDirectoryDefault = (new File(filePath)).getParentFile();
	}
}
