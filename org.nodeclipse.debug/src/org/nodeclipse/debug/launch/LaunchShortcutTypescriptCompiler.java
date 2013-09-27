package org.nodeclipse.debug.launch;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.nodeclipse.debug.util.Constants;

/**
 * Using "Run As" --> "tsc" will lead here
 * 
 * @author Paul Verest
 * @since 0.6
 **/
public class LaunchShortcutTypescriptCompiler extends LaunchShortcut{

	@Override
	protected void setMoreAttributes(ILaunchConfigurationWorkingCopy workingCopy) {
		workingCopy.setAttribute(Constants.ATTR_TYPESCRIPT_COMPILER, "tsc"); // any value
	}
}
