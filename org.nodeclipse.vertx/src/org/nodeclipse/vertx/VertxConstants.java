package org.nodeclipse.vertx;

public class VertxConstants {

	public static final String PLUGIN_ID = "org.nodeclipse.vertx";
	public static final String NATURE_ID = PLUGIN_ID+".VertxNature";
	public static final String LAUNCH_CONFIGURATION_TYPE_ID = PLUGIN_ID+".launch.LaunchConfigurationType";

	public static final String PROCESS_MESSAGE = "Vert.x Process";
	
	public static final String VERTX_PATH = "vertx_path";
	
	public static final String PREFERENCES_PAGE = PLUGIN_ID+".preferences.VertxPreferencePage";
	public static final String PREFERENCE_PAGE_NAME = "Nodeclipse/Vert.x"; // ! not the same as in plugin.xml
	public static final String PREFERENCE_PAGE_DESCRIPTION = "Vert.x settings";
	
	public static final String WIZARD_WINDOW_TITLE = "New Vert.x JavaScript Project";
	public static final String WIZARD_PAGE_TITLE = "Create a Vert.x JavaScript Project";
	public static final String WIZARD_PAGE_DESCRIPTION = "Create a new Vert.x JavaScript project.";
	
	public static final String KEY_FILE_PATH = "key_file_path"; //like in .ui Constants
}
