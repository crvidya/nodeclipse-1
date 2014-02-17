package org.nodeclipse.enide.gradle.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.enide.gradle.Activator;

public class GradlePreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(GradleConstants.GRADLE_HOME, System.getenv("GRADLE_HOME") ); 
		store.setDefault(GradleConstants.GRADLE_HOME_TO_USE, System.getenv("GRADLE_HOME") );
		store.setDefault(GradleConstants.GRADLE_OPTS, System.getenv("GRADLE_OPTS") );
		//store.setDefault(MavenConstants.MAVEN_OPTION_JETTY_PORT, "8080" );
	}

}

