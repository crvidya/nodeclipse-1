package org.nodeclipse.common.ui;

import java.io.IOException;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.ui.console.MessageConsole;

/**
 * Console showing executable's options and launch parameters 
 * and also runtime errors
 * 
 * @author pverest
 */
public class CommonConsole {

	private static CommonConsole instance = null;
	private static IOConsoleOutputStream stream = null;

	public CommonConsole() {
		MessageConsole console = new MessageConsole("Common Console", null);
		console.activate();
		ConsolePlugin.getDefault().getConsoleManager()
				.addConsoles(new IConsole[] { console });
		stream = console.newOutputStream();
	}
	
	static {
		getInstance();
		//write(VersionUtil.getLongString());
		write("visit http://www.nodeclipse.org/\n\n");
	}

	private static CommonConsole getInstance() {
		if (instance == null)
			instance = new CommonConsole();
		return instance;
	}

	public static void write(String s) {
//		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
//		boolean nodeclipseConsoleEnabled = preferenceStore.getBoolean(PreferenceConstants.NODECLIPSE_CONSOLE_ENABLED);//@since 0.7
//		if (!nodeclipseConsoleEnabled)
//			return;
		
		instance = getInstance();
		try {
			stream.write(s);
		} catch (IOException e) {
			//TODO how to show?
			//e.printStackTrace();
		}
	}

	@Override
	public void finalize() {
		try {
			stream.close();
		} catch (IOException e) {
			//TODO how to show?
			//e.printStackTrace();
		}
	}

}
