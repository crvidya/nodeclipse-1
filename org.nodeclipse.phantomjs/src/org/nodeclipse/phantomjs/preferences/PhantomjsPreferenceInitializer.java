package org.nodeclipse.phantomjs.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.preferences.PreferenceConstants;

public class PhantomjsPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		// ! uses .ui.preferences
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		//TODO this does not work!
		store.setDefault(PreferenceConstants.PHANTOMJS_DEBUG_PORT, "6060"); // is free as on http://en.wikipedia.org/wiki/List_of_TCP_and_UDP_ports
	}

}
