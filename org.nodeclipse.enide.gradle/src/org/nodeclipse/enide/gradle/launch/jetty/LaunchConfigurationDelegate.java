package org.nodeclipse.enide.gradle.launch.jetty;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * `build.gradle` Run As Gradle GUI<br>
 * http://www.gradle.org/docs/current/userguide/web_project_tutorial.html
 * 
 * @since 0.11
 * @author Paul Verest
 */
public class LaunchConfigurationDelegate 
	extends org.nodeclipse.enide.gradle.launch.LaunchConfigurationDelegate
	implements ILaunchConfigurationDelegate {
	
	@Override
	protected void specialOptions(ILaunchConfiguration configuration,
			IPreferenceStore preferenceStore, List<String> cmdLine) throws CoreException {
		cmdLine.add("jettyRun");
	}
}
