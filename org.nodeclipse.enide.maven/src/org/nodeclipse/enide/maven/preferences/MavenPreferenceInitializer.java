package org.nodeclipse.enide.maven.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.enide.maven.Activator;

public class MavenPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(MavenConstants.MAVEN_HOME, System.getenv("MAVEN_HOME") ); 
		store.setDefault(MavenConstants.MAVEN_HOME_TO_USE, System.getenv("MAVEN_HOME") );
		store.setDefault(MavenConstants.MAVEN_OPTS, System.getenv("MAVEN_OPTS") );
		//store.setDefault(MavenConstants.MAVEN_OPTION_JETTY_PORT, "8080" );
	}

}

