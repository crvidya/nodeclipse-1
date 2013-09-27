package org.nodeclipse.debug.launch;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.nodeclipse.debug.util.Constants;

/**
 * Using "Run As" --> "coffee -c" will lead here
 * 
 * @author Paul Verest
 * @since 0.6
 **/
public class LaunchShortcutCoffeeCompile extends LaunchShortcut{

	@Override
	protected void setMoreAttributes(ILaunchConfigurationWorkingCopy workingCopy) {
		workingCopy.setAttribute(Constants.ATTR_COFFEE_COMPILE, "-c"); // any value
	}
}
