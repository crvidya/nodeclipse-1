package org.nodeclipse.mongodb.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.nodeclipse.ui.Activator;
import org.nodeclipse.ui.preferences.PreferenceConstants;

/**
 * @author Paul Verest
 * @since 0.11 moved from NodePreferencePage
 */
public class MongodbPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private FileFieldEditor mongoDBShellPath;
    private StringFieldEditor mongoDBShellOptions;
    
	public MongodbPreferencePage(){
	       super(GRID);
	        // ! uses .ui
	        setPreferenceStore(Activator.getDefault().getPreferenceStore());
	        setDescription("Mongodb Shell settings");
	}
	
	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {
        mongoDBShellPath = new FileFieldEditor(PreferenceConstants.MONGODB_SHELL_PATH, "MongoDB Shell path:", getFieldEditorParent());
        addField(mongoDBShellPath);
        
        mongoDBShellOptions = new StringFieldEditor(PreferenceConstants.MONGODB_SHELL_OPTIONS, "MongoDB Shell options:", getFieldEditorParent());
        addField(mongoDBShellOptions);
        
	}

}
