package org.nodeclipse.enide.maven.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.nodeclipse.enide.maven.Activator;

/**
 * @author Paul Verest
 */
public class MavenPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private FileFieldEditor mavenPath;
    private StringFieldEditor mavenOptions;
    
    private BooleanFieldEditor mavenOptionOffline;
    private BooleanFieldEditor mavenOptionDebug;

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
      mavenOptions = new StringFieldEditor(MavenConstants.MAVEN_OPTIONS, "Maven options (mvn -h):", getFieldEditorParent());
        addField(mavenOptions);

//TODO shared processing function for 2 launch types.      
//      mavenOptionOffline = new BooleanFieldEditor(MavenConstants.MAVEN_OPTION_OFFLINE, 
//        		"-o Offline", getFieldEditorParent());
//        addField(mavenOptionOffline);
//      mavenOptionDebug = new BooleanFieldEditor(MavenConstants.MAVEN_OPTION_DEBUG, 
//        		"-X Debug", getFieldEditorParent());
//        addField(mavenOptionDebug);
	}


}
