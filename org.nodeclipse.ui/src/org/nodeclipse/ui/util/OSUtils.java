package org.nodeclipse.ui.util;

import org.eclipse.core.runtime.Platform;

/**
 * @author Nodeclipse authors
 * @author Paul Verest
 */
public class OSUtils {

	public static final boolean isWindows = Platform.getOS().startsWith("win");
	
    public static boolean isWindows() {
        return isWindows;
    }

    public static boolean isMacOS() {
        return Platform.getOS().equals(Platform.OS_MACOSX);
    }

    public static boolean isLinux() {
        return Platform.getOS().equals(Platform.OS_LINUX);
    }

}
