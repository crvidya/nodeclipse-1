package org.nodeclipse.debug.launch;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.nodeclipse.debug.util.Constants;
import org.nodeclipse.ui.NodeclipseConsole;

/**
 * Using "Run As" --> "coffee -c" will lead here
 * 
 * @author Paul Verest
 * @since 0.6
 **/
public class LaunchShortcutCoffeeCompile extends LaunchShortcut{

	@Override
	protected void setMoreAttributes(ILaunchConfigurationWorkingCopy workingCopy) {
		NodeclipseConsole.write(this.getClass().getName()+"\n");		
		workingCopy.setAttribute(Constants.ATTR_COFFEE_COMPILE, "-c"); // any value
	}
}
