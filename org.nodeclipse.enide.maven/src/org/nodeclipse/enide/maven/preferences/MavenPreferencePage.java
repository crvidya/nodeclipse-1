package org.nodeclipse.enide.maven.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.nodeclipse.enide.maven.Activator;

/**
 * @author Paul Verest
 */
public class MavenPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private FileFieldEditor mavenPath;

    public MavenPreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription(
//        	VersionUtil.getLongString()+  //TODO make plugin to collect Eclipse utils
//        	"\n"+
    		"Maven to use");
    }
	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
	      mavenPath = new FileFieldEditor(MavenConstants.MAVEN_PATH, "Maven path:", getFieldEditorParent());
	        addField(mavenPath);		
	}


}
