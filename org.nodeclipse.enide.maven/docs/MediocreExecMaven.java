package runitself;
import java.util.*;
import java.io.*;

// http://www.javaworld.com/article/2071275/core-java/when-runtime-exec---won-t.html
public class MediocreExecMaven {
	private static void log(String message) {
		System.out.println(message);
	}
	public static void main(String args[]) {
		try {
			
			String mavenPath = "D:\\Progs\\springsource\\apache-maven-3.0.4\\bin\\mvn.bat";
			String mavenOptions  = "-X compile exec:java -Dexec.mainClass=runclass.Runme";
			
			String[] cmdLine = new String[2];
			cmdLine[0] = mavenPath;  //cmdLine.add(mavenPath);
			cmdLine[1] = mavenOptions;		//cmdLine.add(mavenOptions+" compile exec:java -Dexec.mainClass="+packageClass);		
			
			String[] envp = new String[2];
			//Map<String, String> envm = new HashMap<String, String>();
			envp[0] = "JAVA_HOME=" + System.getProperty("java.home"); //System.getenv("JAVA_HOME");
			envp[1] = "M2_HOME=" + System.getenv("MAVEN_HOME");		
			
			File workingDirectory = null;
			String currentDir = new File(".").getAbsolutePath();
			log(currentDir);
			String userDir = System.getProperty("user.dir"); //User working directory ; "user.home" 	User home directory
			workingDirectory = new File(userDir);		
			log(workingDirectory.toString());
			
			//
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(cmdLine, envp, workingDirectory);
			InputStream stdout = proc.getInputStream();
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stdout);
			InputStreamReader isr2 = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			BufferedReader br2 = new BufferedReader(isr2);
			
			String line = null;
			System.out.println("<OutputStream>");
			while ((line = br.readLine()) != null)
				System.out.println(line);
			System.out.println("</OutputStream>");
			
			System.out.println("<ErrorStream>");
			while ((line = br2.readLine()) != null)
				System.out.println(line);
			System.out.println("</ErrorStream>");
			
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
//	[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/NoPluginFoundForPrefixException
//	</OutputStream>
//	<ErrorStream>
//	'cmd' �����ڲ����ⲿ���Ҳ���ǿ����еĳ���
//	�������ļ���
//	</ErrorStream>
//	Process exitValue: 1
