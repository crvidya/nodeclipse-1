package org.nodeclipse.phantomjs.launch;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;


/**
 * Using "Run"-->"Run Configurations"--> "New Configuration"-- > "Run" will lead
 * here.
 * @author Paul Verest
 **/
public class LaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {

    @Override
    public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
        ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] { 
//        		new LaunchConfigurationMainTab(),
//        		new NodeArgumentsTab(),
//        		new NodeEnvironmentTab(),
        		new CommonTab() 
        };
        setTabs(tabs);
    }
}
