package org.nodeclipse.enide.maven.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
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

	private DirectoryFieldEditor mavenHome;
	private DirectoryFieldEditor mavenHomeToUse;
	private StringFieldEditor mavenJvmOpts;
    private FileFieldEditor mavenPath;

    private StringFieldEditor mavenOptions;
    private BooleanFieldEditor mavenOptionShowVersion;
    private BooleanFieldEditor mavenOptionDebug;
    private BooleanFieldEditor mavenOptionQuiet;
    private BooleanFieldEditor mavenOptionOffline;
    private BooleanFieldEditor mavenOptionForcedDependenciesUpdate;
    private BooleanFieldEditor mavenOptionTestSkip;
    private FileFieldEditor mavenOptionAlternativeSettings;
    private StringFieldEditor mavenOptionJettyPort;

    public MavenPreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription(
//        	VersionUtil.getLongString()+  //TODO make plugin to collect Eclipse utils
//        	"\n"+
    		"Maven requires to set JAVA_HOME (that Eclipse does not use) and MAVEN_HOME environment variable to use from shell.\n"
    		+"MAVEN_HOME is to be folder where you extracted downloaded Maven distribution,"
    		+" e.g. D:\\Progs\\Maven\\apache-maven-3.1.1\n" //D:\Progs\Maven\apache-maven-3.1.1
    		+"Then it is easy to add mvn to Path like following string \"%JAVA_HOME%\\bin;%MAVEN_HOME%\\bin;\"."
        		);
    }
	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected void createFieldEditors() {


		mavenHome = new DirectoryFieldEditor(MavenConstants.MAVEN_HOME, "Maven home directory:", getFieldEditorParent());
		mavenHome.setEnabled(false, getFieldEditorParent());
		addField(mavenHome);
		//TODO show maven version in this Preference Page
		mavenHomeToUse = new DirectoryFieldEditor(MavenConstants.MAVEN_HOME_TO_USE, "Maven home to use:", getFieldEditorParent());
		addField(mavenHomeToUse);
		mavenJvmOpts = new StringFieldEditor(MavenConstants.MAVEN_OPTS, "JVM options MAVEN_OPTS:", getFieldEditorParent());
		addField(mavenJvmOpts);
		
		mavenPath = new FileFieldEditor(MavenConstants.MAVEN_PATH, "Maven path (@deprecated)", getFieldEditorParent());
		addField(mavenPath);
		
		mavenOptions = new StringFieldEditor(MavenConstants.MAVEN_OPTIONS, "Maven options (mvn -h):", getFieldEditorParent());
		addField(mavenOptions);
		mavenOptionShowVersion = new BooleanFieldEditor(MavenConstants.MAVEN_OPTION_SHOW_VERSION,"-V, --show-version Display version information without stopping build", getFieldEditorParent());
        addField(mavenOptionShowVersion);
        mavenOptionDebug = new BooleanFieldEditor(MavenConstants.MAVEN_OPTION_DEBUG,"-X Debug	(Produce execution debug output)", getFieldEditorParent());
        addField(mavenOptionDebug);
        mavenOptionQuiet = new BooleanFieldEditor(MavenConstants.MAVEN_OPTION_QUIET,"-q Quiet	(Quiet output - only show errors)", getFieldEditorParent());
        addField(mavenOptionQuiet);
        mavenOptionForcedDependenciesUpdate = new BooleanFieldEditor(MavenConstants.MAVEN_OPTION_FORCED_DEPENDENCIES_UPDATE, "-U Forces a check for updated releases and snapshots on remote repositories", getFieldEditorParent());
        addField(mavenOptionForcedDependenciesUpdate);
		mavenOptionOffline = new BooleanFieldEditor(MavenConstants.MAVEN_OPTION_OFFLINE, "-o Offline	(Work offline)", getFieldEditorParent());
        addField(mavenOptionOffline);
        mavenOptionTestSkip = new BooleanFieldEditor(MavenConstants.MAVEN_OPTION_TEST_SKIP,"-Dmaven.test.skip=true	(skip unit testing)", getFieldEditorParent());
        addField(mavenOptionTestSkip);

        mavenOptionAlternativeSettings = new FileFieldEditor(MavenConstants.MAVEN_OPTION_ALTERNATIVE_SETTINGS, "-s Alternative settings.xml:", getFieldEditorParent());
		addField(mavenOptionAlternativeSettings);

		mavenOptionJettyPort = new StringFieldEditor(MavenConstants.MAVEN_OPTION_JETTY_PORT, "-Djetty.port=", getFieldEditorParent());
		addField(mavenOptionJettyPort);
	}


}
