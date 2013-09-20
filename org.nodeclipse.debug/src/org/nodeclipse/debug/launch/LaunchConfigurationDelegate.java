package org.nodeclipse.debug.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.chromium.debug.core.ChromiumDebugPlugin;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.debug.core.model.RuntimeProcess;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.nodeclipse.debug.util.Constants;
import org.nodeclipse.debug.util.NodeDebugUtil;
import org.nodeclipse.debug.util.VariablesUtil;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.preferences.PreferenceConstants;

/**
 * launch() implements starting Node and passing all parameters
 * 
 * @author Lamb, Tomoyuki, Pushkar, Paul Vverest
 */
public class LaunchConfigurationDelegate implements
		ILaunchConfigurationDelegate {
	private static RuntimeProcess nodeProcess = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.debug.core.model.ILaunchConfigurationDelegate#launch(org.
	 * eclipse.debug.core.ILaunchConfiguration, java.lang.String,
	 * org.eclipse.debug.core.ILaunch,
	 * org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {
		if(nodeProcess != null && !nodeProcess.isTerminated()) {
			throw new CoreException(new Status(IStatus.OK, ChromiumDebugPlugin.PLUGIN_ID, null, null));
		}

		// Using configuration to build command line		
		String nodePath= Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.NODE_PATH);

		// Check if the node location is correctly configured
		File nodeFile = new File(nodePath);
		if(!nodeFile.exists()){
			// If the location is not valid than show a dialog which prompts the user to goto the preferences page
			showPreferencesDialog("Node.js runtime is not correctly configured.\n\n"
					+ "Please goto Window -> Prefrences -> Nodeclipse and configure the correct location");
			return;
		}
		
		List<String> cmdLine = new ArrayList<String>();
		// Application path should be stored in preference.
		cmdLine.add(nodePath);
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			// -brk says to Node runtime wait until Chromium Debugger starts and connects
			// that is causing "stop on first line" behavior,
			// otherwise small apps or first line can be undebuggable.
			// TODO flexible debugging port, instead of hard-coded 5858
			// #61 https://github.com/Nodeclipse/nodeclipse-1/issues/61
			cmdLine.add("--debug-brk=5858"); 
		}
		
		String nodeArgs = configuration.getAttribute(Constants.ATTR_NODE_ARGUMENTS, "");
		if(!nodeArgs.equals("")) {
			String[] sa = nodeArgs.split(" ");
			for(String s : sa) {
				cmdLine.add(s);
			}
		}
		
		String file = configuration.getAttribute(Constants.KEY_FILE_PATH,	Constants.BLANK_STRING);
		String extension = null;
		int i = file.lastIndexOf('.');
		if(i > 0) {
			extension = file.substring(i+1);
		} else {
			throw new CoreException(new Status(IStatus.OK, ChromiumDebugPlugin.PLUGIN_ID,
				"Target file does not have extension: " + file, null));
		}
		
		// #57 running app.js with node-dev, forever, supervisor, nodemon etc
		// https://github.com/Nodeclipse/nodeclipse-1/issues/57
		String nodeMonitor = configuration.getAttribute(Constants.ATTR_NODE_MONITOR, "");
		if(!nodeMonitor.equals("")) { // any value
			//TODO support selection, now only one
			
			String nodeMonitorPath= Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.NODE_MONITOR_PATH);
			
			// Check if the node monitor location is correctly configured
			File nodeMonitorFile = new File(nodeMonitorPath);
			if(!nodeMonitorFile.exists()){
				// If the location is not valid than show a dialog which prompts the user to goto the preferences page
				showPreferencesDialog("Node.js monitor is not correctly configured.\n"
						+ "Select path to installed util: forever, node-dev, nodemon or superviser.\n\n"
						+ "Please goto Window -> Prefrences -> Nodeclipse and configure the correct location");
				return;
			}
			cmdLine.add(nodeMonitorPath);
		} else {
			if("coffee".equals(extension)) {
				cmdLine.add(Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.COFFEE_PATH));
			}
		}
		
		String filePath = 
				ResourcesPlugin.getWorkspace().getRoot().findMember(file).getLocation().toOSString();
		// path is relative, so can not found it.
		cmdLine.add(filePath);

		String programArgs = configuration.getAttribute(Constants.ATTR_PROGRAM_ARGUMENTS, "");
		if(!programArgs.equals("")) {
			String[] sa = programArgs.split(" ");
			for(String s : sa) {
				cmdLine.add(s);
			}
		}
		
		String workingDirectory = configuration.getAttribute(Constants.ATTR_WORKING_DIRECTORY, "");
		File workingPath = null;
		if(workingDirectory.length() == 0) {
			workingPath = (new File(filePath)).getParentFile();
		} else {
			workingDirectory = VariablesUtil.resolveValue(workingDirectory);
			if(workingDirectory == null) {
				workingPath = (new File(filePath)).getParentFile();
			} else {
				workingPath = new File(workingDirectory);
			}
		}
		
		Map<String, String> envm = new HashMap<String, String>();
		envm = configuration.getAttribute(Constants.ATTR_ENVIRONMENT_VARIABLES, envm);
		String[] envp = new String[envm.size()];
		int idx = 0;
		for(String key : envm.keySet()) {
			String value = envm.get(key);
			envp[idx++] = key + "=" + value;
		}
		
		String[] cmds = {};
		cmds = cmdLine.toArray(cmds);
		// Launch a process to debug.eg,
		Process p = DebugPlugin.exec(cmds, workingPath, envp);
		RuntimeProcess process = (RuntimeProcess)DebugPlugin.newProcess(launch, p, Constants.PROCESS_MESSAGE);
		if (mode.equals(ILaunchManager.DEBUG_MODE)) {
			if(!process.isTerminated()) { 
				NodeDebugUtil.launch(mode, launch, monitor);
			}
		}
		nodeProcess = process;
	}

	private void showPreferencesDialog(final String message) {
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

				MessageDialog dialog = new MessageDialog(shell, "Nodeclipse", null, message, 
						MessageDialog.ERROR, new String[] { "Open Prefrences ...", "Cancel" }, 0);
				int result = dialog.open();
				if (result == 0) {
					PreferenceDialog pref = PreferencesUtil.createPreferenceDialogOn(shell,
						PreferenceConstants.PREFERENCES_PAGE, null, null);
					if (pref != null)
						pref.open();
				}
			}
		});
	}
	
	public static void terminateNodeProcess() {
		if(nodeProcess != null) {
			try {
				nodeProcess.terminate();
			} catch (DebugException e) {
				e.printStackTrace();
			}
			nodeProcess = null;
		}
	}
}
