package org.nodeclipse.enide.maven.preferences;

public class MavenConstants {
	
	public static final String PLUGIN_ID = "org.nodeclipse.enide.maven";
	public static final String LAUNCH_CONFIGURATION_TYPE_ID = "org.nodeclipse.enide.maven.launch.LaunchConfigurationType";
	public static final String LAUNCH_EXEC_CONFIGURATION_TYPE_ID = "org.nodeclipse.enide.maven.launchexec.LaunchConfigurationType";
	
	public static final String PROCESS_MESSAGE = "Maven Process";

	//+
    public static final String ATTR_PROGRAM_ARGUMENTS = "attr_program_arguments";
    public static final String ATTR_MAVEN_ARGUMENTS = "attr_maven_arguments";
    public static final String ATTR_WORKING_DIRECTORY = "attr_working_directory"; 

	public static final String ATTR_ENVIRONMENT_VARIABLES = "attr_environment_variables";	
	public static final String ATTR_APPEND_ENVIRONMENT_VARIABLES = "attr_append_environment_variables";
	
	public static final String MAVEN_HOME = "maven_home";
	public static final String MAVEN_PATH = "maven_path";	
	public static final String MAVEN_OPTIONS = "maven_options";
	public static final String MAVEN_OPTION_OFFLINE = "maven_option_offline";
	public static final String MAVEN_OPTION_DEBUG = "maven_option_debug";

	public static final String PREFERENCES_PAGE = "org.nodeclipse.enide.maven.preferences.MavenPreferencePage";
	public static final String PREFERENCE_PAGE_NAME ="Maven(Enide)"; // the same as in plugin.xml	
}
