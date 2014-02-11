package org.nodeclipse.enide.gradle.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
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
    		"Gradle to use");
    }
	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
      gradlePath = new FileFieldEditor(GradleConstants.GRADLE_PATH, "Gradle path:", getFieldEditorParent());
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
