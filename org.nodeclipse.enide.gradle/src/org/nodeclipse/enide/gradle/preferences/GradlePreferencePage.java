package org.nodeclipse.enide.gradle.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.nodeclipse.enide.gradle.Activator;

/**
 * @author Paul Verest
 */
public class GradlePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private FileFieldEditor gradlePath;

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
	}


}
