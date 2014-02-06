package org.nodeclipse.enide.maven.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.enide.maven.Activator;

public class MavenPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(MavenConstants.MAVEN_HOME, System.getenv("MAVEN_HOME") ); 
	}

}

