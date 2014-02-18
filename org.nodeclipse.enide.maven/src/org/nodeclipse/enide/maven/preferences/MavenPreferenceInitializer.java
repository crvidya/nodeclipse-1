package org.nodeclipse.enide.maven.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.nodeclipse.enide.maven.Activator;

public class MavenPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		String envMavenHome = System.getenv("MAVEN_HOME");
		if (envMavenHome!=null){
			store.setDefault(MavenConstants.MAVEN_HOME, envMavenHome ); 
			store.setDefault(MavenConstants.MAVEN_HOME_TO_USE, envMavenHome );
		}
		String envMavenOpts = System.getenv("MAVEN_OPTS");
		if (envMavenOpts!=null)
		store.setDefault(MavenConstants.MAVEN_OPTS, envMavenOpts );
		//store.setDefault(MavenConstants.MAVEN_OPTION_JETTY_PORT, "8080" );
	}

}

