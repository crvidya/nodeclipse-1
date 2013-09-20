package org.nodeclipse.debug.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.preferences.PreferenceConstants;

public class NodeDebugUtil {
	private static final String CONFIG_NAME = "STANDALONE_V8";
    //public static final String LAUNCH_CONFIGURATION_TYPE_ID = "org.chromium.debug.ui.LaunchType$StandaloneV8";
	private static final String LAUNCH_CONFIGURATION_TYPE_ID = "org.nodeclipse.debug.launch.LaunchType$StandaloneV8";
	
	public static void launch(final String mode, ILaunch launch,
			IProgressMonitor monitor) throws CoreException {
		ILaunchConfigurationType type = DebugPlugin
				.getDefault()
				.getLaunchManager()
				.getLaunchConfigurationType(LAUNCH_CONFIGURATION_TYPE_ID);
		
		if (type != null) {
			ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(
					null, CONFIG_NAME);
			workingCopy.setAttribute("debug_host", "localhost");
			//workingCopy.setAttribute("debug_port", 5858);
			IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
			int nodeDebugPort = preferenceStore.getInt(PreferenceConstants.NODE_DEBUG_PORT);
			if (nodeDebugPort==0) { nodeDebugPort=5858;};
			workingCopy.setAttribute("debug_port", nodeDebugPort);
			final ILaunchConfiguration config = workingCopy.doSave();
			// super.launch(config, mode, launch, monitor);
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					DebugUITools.launch(config, mode);
				}
			});
		}		
	}
	
	public static void deleteConfig() {
		ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType type = manager.getLaunchConfigurationType(LAUNCH_CONFIGURATION_TYPE_ID);
		
		try {
			ILaunchConfiguration[] confs = manager.getLaunchConfigurations(type);
			for(ILaunchConfiguration conf : confs) {
				if(CONFIG_NAME.equals(conf.getName())) {
					conf.delete();
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}	
	}
	
	public static void deleteProject() {
        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(CONFIG_NAME);
        if (project.exists()) {
        	try {
				project.delete(true, null);
			} catch (CoreException e) {
				e.printStackTrace();
			}
        }		
	}
}
