package org.nodeclipse.enide.maven.util;

import org.nodeclipse.common.ui.CommonConsole;

/**
 * Allow to use any logger
 * @author pverest
 * @since 0.10
 */
public class NodeclipseLogger {
	
	public static void log(String message){
		System.out.print(message);
		CommonConsole.write(message);
	}

}
