package org.nodeclipse.ui.contentassist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nodeclipse.ui.util.Constants;
import org.nodeclipse.ui.util.NodeclipseConsole;
import org.nodeclipse.ui.util.ProcessUtils;

/**
 * http.json
 * 
<pre> 
      "methods": [
        {
          "textRaw": "http.createServer([requestListener])",
          "type": "method",
          "name": "createServer",
          "desc": "<p>Returns a new web server object.\n\n</p>\n<p>The <code>requestListener</code> is a function which is automatically\nadded to the <code>&#39;request&#39;</code> event.\n\n</p>\n",
          "signatures": [
            {
              "params": [
                {
                  "name": "requestListener",
                  "optional": true
                }
              ]
            }
          ]
        },
</pre>
 * 
 * 
 * @author Paul Verest
 */
public class ContentFromSources {
	
	public static final String HTTP_JSON = "org/nodeclipse/ui/contentassist/http.json";
	
	public static JSONArray METHODS;

    static {
        try {
//        	// option to have completions.json as external file
//        	String completionJsonPath = ProcessUtils.getCompletionsJsonPath();
//        	if (completionJsonPath == null || completionJsonPath.equals("")) {
//        		completionJsonPath = Constants.COMPLETIONS_JSON;
//        	}
            InputStream is = ContentProvider.class.getClassLoader().getResourceAsStream(HTTP_JSON);
            JSONObject object = new JSONObject(inputStream2String(is));
            JSONArray modules = object.getJSONArray("modules");
            int modules_length = modules.length();
            log("modules_length="+modules_length+"\n");
            
            JSONObject module = modules.getJSONObject(0);            
            METHODS = module.getJSONArray("methods");
            
            
        } catch (JSONException e) {
        	log(e.getLocalizedMessage()+"\n");
        } catch (IOException e) {
        	log(e.getLocalizedMessage()+"\n");
        }
    }
    
    public static String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    private static void log(String s){
    	NodeclipseConsole.write(s);
    }
    
    public static void main(String[] args){
    	//System.out.println(x);
    	
    }
}

