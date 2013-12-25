package org.nodeclipse.ui.util;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * asked http://stackoverflow.com/questions/19261994/eclipse-plugin-dev-how-to-get-current-bundle-version
 * @author Paul Verest 
 */
public class VersionUtil {

	public static String versionString = "UNDEFINED";
	static {
		Bundle bundle = Platform.getBundle("org.nodeclipse.ui");
		Version version = bundle.getVersion();
		versionString = version.toString(); 
		//""+version.getMajor()+" "+version.getMinor()+" "+version.getMicro()+" "+version.getQualifier();
	}
	
	public VersionUtil() {
	}
}
