package org.nodeclipse.jjs.launch;

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
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.preferences.Dialogs;
import org.nodeclipse.ui.preferences.PreferenceConstants;
import org.nodeclipse.ui.util.NodeclipseConsole;

/**
 * Launching `jjs` from Java 8
 * 
 * @since 0.7
 * @author Paul Verest
 */
public class LaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode,
			ILaunch launch, IProgressMonitor monitor) throws CoreException {

		//NodeclipseConsole.write("launch jjs\n");
		
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		boolean isDebugMode = mode.equals(ILaunchManager.DEBUG_MODE);
		
		// Using configuration to build command line	
		List<String> cmdLine = new ArrayList<String>();

		String jjsPath= preferenceStore.getString(PreferenceConstants.JJS_PATH);
		// Check if the node location is correctly configured
		File jjsFile = new File(jjsPath);
		if(!jjsFile.exists()){
			// If the location is not valid than show a dialog which prompts the user to goto the preferences page
			Dialogs.showPreferencesDialog("path to jjs util from Java 8 runtime is not correctly configured.\n\n"
					+ "Please goto Window -> Prefrences -> Nodeclipse and configure the correct location under 'JJS path:'");
			return;
		}			
		cmdLine.add(jjsPath);
		
		if (isDebugMode) {
			//TODO how to debug
		}

		String file = configuration.getAttribute("KEY_FILE_PATH",	"");
		String filePath = ResourcesPlugin.getWorkspace().getRoot().findMember(file).getLocation().toOSString();
		// path is relative, so cannot find it, unless get absolute path
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
			//TODO how to debug
		}
		
	}

}
