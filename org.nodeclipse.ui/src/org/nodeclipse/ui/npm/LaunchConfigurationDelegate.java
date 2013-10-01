package org.nodeclipse.ui.npm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.preferences.PreferenceConstants;
import org.nodeclipse.ui.util.Constants;
import org.nodeclipse.ui.util.OSUtils;

public class LaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

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
    public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
        // Using configuration to build command line
        List<String> cmdLine = new ArrayList<String>();
        String nodePath = Activator.getDefault().getPreferenceStore().getString(PreferenceConstants.NODE_PATH);
        cmdLine.add(findNpm(nodePath));
        
        String goal = configuration.getAttribute(Constants.KEY_GOAL, Constants.BLANK_STRING);
        cmdLine.add(goal);
        String file = configuration.getAttribute(Constants.KEY_FILE_PATH, Constants.BLANK_STRING);
        String filePath = ResourcesPlugin.getWorkspace().getRoot().findMember(file).getLocation().toOSString();

        String[] cmds = {};
        cmds = cmdLine.toArray(cmds);
        Process p = null;
        if(OSUtils.isMacOS()) {
        	String[] env = {"PATH=" + nodePath.substring(0, nodePath.lastIndexOf(File.separator))};
        	p = DebugPlugin.exec(cmds, (new File(filePath)).getParentFile(), env);
        } else {
        	p = DebugPlugin.exec(cmds, (new File(filePath)).getParentFile());
        }
        DebugPlugin.newProcess(launch, p, Constants.NPM_PROCESS_MESSAGE + goal);
    }
    
    private static String findNpm(String nodePath) {
        //TODO npm application path should be stored in preference.
        String npmFileName = getNpmFileName();
        String npmPath = nodePath.substring(0, nodePath.lastIndexOf(File.separator) + 1) + npmFileName;
    	File npmFile = new File(npmPath);
    	if(npmFile.exists()) {
    		return npmFile.getAbsolutePath();
    	}
    	
        String path = System.getenv("PATH");
        String[] paths = path.split("" + File.pathSeparatorChar, 0);
        List<String> directories = new ArrayList<String>();
        for(String p : paths) {
        	directories.add(p);
        }

        // ensure /usr/local/bin is included for OS X
        if (OSUtils.isMacOS()) {
            directories.add("/usr/local/bin");
        }

        // search for Node.js in the PATH directories
        for (String directory : directories) {
        	npmFile = new File(directory, npmFileName);

            if (npmFile.exists()) {
                return npmFile.getAbsolutePath();
            }
        }

        throw new IllegalStateException("Could not find npm.");
    }

    private static String getNpmFileName() {
        if (OSUtils.isWindows()) {
            return "npm.cmd";
        }

        return "npm";
    }
}
