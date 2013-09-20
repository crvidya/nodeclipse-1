package org.nodeclipse.debug.launch;

import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.nodeclipse.debug.util.Constants;

/**
 * Using "Run As" --> "Node with monitor" will lead here
 * 
 * @author Paul Verest
 * @since 0.6
 **/
public class LaunchShortcutWithMonitor extends LaunchShortcut{

	@Override
	protected void setMoreAttributes(ILaunchConfigurationWorkingCopy workingCopy) {
		workingCopy.setAttribute(Constants.ATTR_NODE_MONITOR, "e.g. node_dev"); // any value
	}
}
