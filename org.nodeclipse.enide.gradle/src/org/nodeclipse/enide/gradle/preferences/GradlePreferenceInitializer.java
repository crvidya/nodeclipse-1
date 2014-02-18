package org.nodeclipse.enide.gradle.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.enide.gradle.Activator;

public class GradlePreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String envGradleHome = System.getenv("GRADLE_HOME");
		if (envGradleHome!=null){
			store.setDefault(GradleConstants.GRADLE_HOME, envGradleHome ); 
			store.setDefault(GradleConstants.GRADLE_HOME_TO_USE, envGradleHome );
		}
		String envGradleOpts = System.getenv("GRADLE_OPTS");
		if (envGradleOpts!=null)
			store.setDefault(GradleConstants.GRADLE_OPTS, envGradleOpts );
		//store.setDefault(MavenConstants.MAVEN_OPTION_JETTY_PORT, "8080" );
	}

}

