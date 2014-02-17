package org.nodeclipse.enide.gradle.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.nodeclipse.enide.gradle.Activator;

/**
 * @author Paul Verest
 */
public class GradlePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private DirectoryFieldEditor gradleHome;
	private DirectoryFieldEditor gradleHomeToUse;
	private StringFieldEditor gradleJvmOpts;
    private FileFieldEditor gradlePath;
    
    private StringFieldEditor gradleOptions;
    private BooleanFieldEditor gradleOptionDebug;
    private BooleanFieldEditor gradleOptionInfo;
    private BooleanFieldEditor gradleOptionQuiet;
    private BooleanFieldEditor gradleOptionOffline;

    public GradlePreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription(
//        	VersionUtil.getLongString()+  //TODO make plugin to collect Eclipse utils
//        	"\n"+
        	"From getting-started.html:\n"
        	+"For running Gradle, create GRADLE_HOME environment variable pointing to folder with unpacked Gradle distribution"
        	+ "and add GRADLE_HOME/bin to your PATH environment variable. Usually, this is sufficient to run Gradle.\n"
        	+"Gradle uses whichever JDK it finds in your path (to check, use java -version)."
        	+" Alternatively, you can set the JAVA_HOME environment variable to point to the install directory of the desired JDK.\n"	
    		+"\nFor this plugin specifying Gradle home to use will be enough."
        	+" (This lets you easily experiment with different versions.)");
    }
	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
		

	    gradleHome = new DirectoryFieldEditor(GradleConstants.GRADLE_HOME, "Gradle home directory:", getFieldEditorParent());
		gradleHome.setEnabled(false, getFieldEditorParent());
		addField(gradleHome);
		//TODO show version in this Preference Page
		gradleHomeToUse = new DirectoryFieldEditor(GradleConstants.GRADLE_HOME_TO_USE, "Gradle home to use:", getFieldEditorParent());
		addField(gradleHomeToUse);
		gradleJvmOpts = new StringFieldEditor(GradleConstants.GRADLE_OPTS, "JVM options GRADLE_OPTS:", getFieldEditorParent());
		addField(gradleJvmOpts);
		
      gradlePath = new FileFieldEditor(GradleConstants.GRADLE_PATH, "Gradle path (@deprecated):", getFieldEditorParent());
        addField(gradlePath);
        
      gradleOptions = new StringFieldEditor(GradleConstants.GRADLE_OPTIONS, "Gradle options (gradle -h):", getFieldEditorParent());
        addField(gradleOptions);
      gradleOptionDebug = new BooleanFieldEditor(GradleConstants.GRADLE_OPTION_DEBUG,"-d, --debug Log in debug mode (includes normal stacktrace).", getFieldEditorParent());
        addField(gradleOptionDebug);
      gradleOptionInfo = new BooleanFieldEditor(GradleConstants.GRADLE_OPTION_INFO,"-i, --info Set log level to info.", getFieldEditorParent());
        addField(gradleOptionInfo);
      gradleOptionQuiet = new BooleanFieldEditor(GradleConstants.GRADLE_OPTION_QUIET,"-q, --quiet Log errors only.", getFieldEditorParent());
        addField(gradleOptionQuiet);
      gradleOptionOffline = new BooleanFieldEditor(GradleConstants.GRADLE_OPTION_OFFLINE,"--offline The build should operate without accessing network resources.", getFieldEditorParent());
        addField(gradleOptionOffline);
	}


}
