package org.nodeclipse.ui.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.nodeclipse.ui.Activator;

/**
 * @author Tomoyuki Inagaki
 * @author Paul Verest
 */
public class NodePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private FileFieldEditor nodePath;
    private IntegerFieldEditor nodeDebugPort;
    private FileFieldEditor expressPath;
    private FileFieldEditor coffeePath;
    private FileFieldEditor nodeMonitorPath;
   //private FileFieldEditor completionsPath;
    
    public NodePreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("Node Settings");
    }

    @Override
    public void init(IWorkbench workbench) {

    }

    @Override
    protected void createFieldEditors() {
        nodePath = new FileFieldEditor(PreferenceConstants.NODE_PATH, "Node Path:", getFieldEditorParent());
        addField(nodePath);

        nodeDebugPort = new IntegerFieldEditor(PreferenceConstants.NODE_DEBUG_PORT, "Node debug port:", getFieldEditorParent());
        addField(nodeDebugPort);

        expressPath = new FileFieldEditor(PreferenceConstants.EXPRESS_PATH, "Express Path:", getFieldEditorParent());
        addField(expressPath);

        coffeePath = new FileFieldEditor(PreferenceConstants.COFFEE_PATH, "Coffee Path:", getFieldEditorParent());
        addField(coffeePath);
        
        nodeMonitorPath = new FileFieldEditor(PreferenceConstants.NODE_MONITOR_PATH, "Node monitor Path:", getFieldEditorParent());
        addField(nodeMonitorPath);

//        completionsPath = new FileFieldEditor(PreferenceConstants.COMPLETIONS_JSON_PATH, "Completions.json Path:", getFieldEditorParent());
//        addField(completionsPath);
    }

    @Override
    public boolean isValid() {
        return super.isValid();
    }

}