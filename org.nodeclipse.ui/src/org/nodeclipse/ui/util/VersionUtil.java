package org.nodeclipse.ui.util;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * @author Paul Verest 
 */
public class VersionUtil {

	public static String versionString = "UNDEFINED";
	
	public VersionUtil() {
		Bundle bundle = Platform.getBundle("org.nodeeclipse.ui");
		Version version = bundle.getVersion();
		versionString = ""+version.getMajor()+ " "+version.getMinor();
	}
	
}
