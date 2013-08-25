package org.nodeclipse.bundle.coffee;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;

public class BundlePath {
	public static String getPath() {
		return getBundledPath("coffee-script/bin/coffee");
	}

	private static String getBundledPath(String path) {
		Bundle bundle = Activator.getDefault().getBundle();
		if (bundle == null) {
			//LogUtil.info("getBundlePath(" + path + " bundle is null");
			return "";
		}
		try {
			URL location = FileLocator.toFileURL(bundle.getEntry("/"));
			File file = new File(location.getPath(), path);
			//LogUtil.info("BundledPath: " + file.getAbsolutePath());
			
			return file.getAbsolutePath();
		} catch(Exception ex) {
			//LogUtil.error(ex);
			return "";
		}
	}
}
