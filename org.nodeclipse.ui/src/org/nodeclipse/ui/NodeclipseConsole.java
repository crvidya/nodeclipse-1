package org.nodeclipse.ui;

import java.io.IOException;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.ui.console.MessageConsole;

/**
 * Console Helping
 * fsor tracing node launch parameters
 * 
 * @author pverest
 * 
 */
public class NodeclipseConsole {

	private static NodeclipseConsole instance = null;
	private static IOConsoleOutputStream stream = null;

	public NodeclipseConsole() {
		MessageConsole console = new MessageConsole("Nodeclipse Console", null);
		console.activate();
		ConsolePlugin.getDefault().getConsoleManager()
				.addConsoles(new IConsole[] { console });
		stream = console.newOutputStream();
	}

	private static NodeclipseConsole getInstance() {
		if (instance == null)
			instance = new NodeclipseConsole();
		return instance;
	}

	public static void write(String s) {
		instance = getInstance();
		try {
			stream.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void finalize() {
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
