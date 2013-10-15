package org.nodeclipse.phantomjs.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.RuntimeProcess;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.debug.util.NodeDebugUtil;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.preferences.Dialogs;
import org.nodeclipse.ui.preferences.PreferenceConstants;
import org.nodeclipse.ui.util.NodeclipseConsole;

/**
 * Launching phantomjs is similar to Node.js
<pre>
D:\Progs\phantomjs-1.9.2-windows>phantomjs.exe  --remote-debugger-port=6060 --remote-debugger-autorun=yes  examples\arguments.js 123
0: examples\arguments.js
1: 123
Phantom::exit() called but not quitting in debug mode.
</pre>
 * 
 * @author Paul Verest
 */
public class LaunchConfigurationDelegate 
	extends org.nodeclipse.debug.launch.LaunchConfigurationDelegate
	implements ILaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		
		//NodeclipseConsole.write("launch phantomjs\n");
		
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		boolean isDebugMode = mode.equals(ILaunchManager.DEBUG_MODE);
		
		// Using configuration to build command line	
		List<String> cmdLine = new ArrayList<String>();

		String phantomjsPath= preferenceStore.getString(PreferenceConstants.PHANTOMJS_PATH);
		// Check if the node location is correctly configured
		File phantomjsFile = new File(phantomjsPath);
		if(!phantomjsFile.exists()){
			// If the location is not valid than show a dialog which prompts the user to goto the preferences page
			Dialogs.showPreferencesDialog("PhantomJS runtime is not correctly configured.\n\n"
					+ "Please goto Window -> Prefrences -> Nodeclipse and configure the correct location under 'PhanthomJS path:'");
			return;
		}			
		cmdLine.add(phantomjsPath);	
	
		if (isDebugMode) {
			int phantomjsDebugPort = preferenceStore.getInt(PreferenceConstants.PHANTOMJS_DEBUG_PORT);
			cmdLine.add("--remote-debugger-port="+phantomjsDebugPort);
			
			if (preferenceStore.getBoolean(PreferenceConstants.PHANTOMJS_DEBUG_AUTORUN)) //default false
				cmdLine.add("--remote-debugger-autorun=yes");
		}
			
		String file = configuration.getAttribute("KEY_FILE_PATH",	"");
		
		String filePath = ResourcesPlugin.getWorkspace().getRoot().findMember(file).getLocation().toOSString();
		// path is relative, so can not found it.
		cmdLine.add(filePath);
		
		
		for(String s : cmdLine) NodeclipseConsole.write(s+" ");
		NodeclipseConsole.write("\n");
		
		String[] cmds = {};
		cmds = cmdLine.toArray(cmds);
		// Launch a process to debug.eg,
		//TODO Process p = DebugPlugin.exec(cmds, workingPath, envp);
		Process p = DebugPlugin.exec(cmds, null, null);
		RuntimeProcess process = (RuntimeProcess)DebugPlugin.newProcess(launch, p, Constants.PROCESS_MESSAGE);
		if (isDebugMode) {
			int phantomjsDebugPort = preferenceStore.getInt(PreferenceConstants.PHANTOMJS_DEBUG_PORT);
			NodeDebugUtil.launch(mode, launch, monitor, phantomjsDebugPort);
		}
//			if(!process.isTerminated()) { 
//				NodeDebugUtil.launch(mode, launch, monitor);
//			}
//		}
		
		
	}

}
