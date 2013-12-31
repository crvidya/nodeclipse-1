package org.nodeclipse.enide.editors.gradle.highlight;

public class Words {

    public static final String[] KEYWORDS = 
    	{ "apply", "plugin", "sourceCompatibility", 
            "repositories", "dependencies", "test",
            "version","group", "name",
            "buildscript", 
            
            "android", "compileSdkVersion", "buildToolsVersion",//
            "sourceSets", "main", "manifest",//
            "srcFile", "srcDirs", "setRoot"//
    };

    public static final String[] RESERVEDWORDS = { "let", "yield",//
            "abstract", "enum", "int", "short",//
            "boolean", "export", "interface", "static",//
            "byte", "extends", "long", "super",//
            "char", "final", "native", "synchronized",//
            "class", "float", "package", "throws",//
            "const", "goto", "private", "transient",//
            "debugger", "implements", "protected", "volatile",//
            "double", "import", "public"//
    };

    public static final String[] NODE_WORDS = new String[] { "require", "__filename",//
            "__dirname", "module", "exports", "setInterval"//
    };

}
