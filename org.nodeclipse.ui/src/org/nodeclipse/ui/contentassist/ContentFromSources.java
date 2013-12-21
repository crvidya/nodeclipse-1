package org.nodeclipse.ui.contentassist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nodeclipse.ui.util.NodeclipseConsole;
import org.nodeclipse.ui.util.ProcessUtils;

/**
 * Node.js Context from Sources all.json.
 * May be multiple instances, defaultInstance uses bundled all.json file.
 * 
 * 
 * Example from http.json
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
class ContentFromSources {
	
	static final String ALL_JSON = "org/nodeclipse/ui/contentassist/all.json";
	
	//public static JSONArray METHODS;
	public JSONObject NodejsContext;
	
	static ContentFromSources defaultInstance = null; 
	static {
		defaultInstance = new ContentFromSources(ALL_JSON);
	}


    public ContentFromSources(String sourcesAllJsonPath) {
        try {
        	// check if sources to use are selected 
        	//String sourcesAllJsonPath = ProcessUtils.getSourcesAllJsonPath();
        	if ("".equals(sourcesAllJsonPath)) {
        		sourcesAllJsonPath = ALL_JSON;
        	}
            InputStream is = ContentProvider.class.getClassLoader().getResourceAsStream(sourcesAllJsonPath);
            NodejsContext = new JSONObject(inputStream2String(is));
        } catch (JSONException e) {
        	log(e.getLocalizedMessage()+"\n");
        } catch (IOException e) {
        	log(e.getLocalizedMessage()+"\n");
        }
        if (defaultInstance == null){
        	defaultInstance = this;
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
    
    private void readModel(){
        // modules30: timers(m8), module, addons, util(m13), Events(c1), domain(m1)(c1), buffer(c2), stream(c4), crypto(m18)(c7), 
        // tls_(ssl)(m5)(c4), stringdecoder(c1), fs(m67)(c4), path(m7), net(m10)(c2), dgram(m1)(c1), dns(m10), http(m4)(c4), https(m3)(c2), 
        // url(m3), querystring(m2), punycode(m4), readline(m1)(c1), repl(m1), vm(m5)(c1), child_process(m4)(c1), assert(m11), tty(m2)(c2), zlib(m14)(c8), os(m13), cluster(m3)(c1)

    	
    	IRepo repo = new Model();
        try {
        	JSONObject nodejsContextJSONObject = NodejsContext;
			JSONArray modules = nodejsContextJSONObject.getJSONArray("modules");
			log("modules"+modules.length()+" ");
			for (int i = 0; i < modules.length(); i++) {
				JSONObject module = (JSONObject) modules.get(i);
				String moduleName = module.getString("name");
				debug( ", "+moduleName);
				Module moduleObj = new Module(moduleName);
				repo.addModule(moduleObj);

				if (module.has("methods")) {
					JSONArray methods = module.getJSONArray("methods");
					debug("(m"+methods.length()+")");
					for (int j = 0; j < methods.length(); j++) {
						JSONObject method = (JSONObject) methods.get(j);
						// example: "textRaw": "http.createServer([requestListener])","type": "method","name": "createServer",
						String trigger = method.getString("textRaw");
						String name = method.getString("name");
						String desc = formatedName(name,trigger)+method.getString("desc");

						Entry entry = new Entry(moduleObj,EntryType.method,name,trigger,desc);
						repo.addEntry(entry);	
					}
				}

				if (module.has("classes")){
	                JSONArray classes = module.getJSONArray("classes");
	                debug("(c"+classes.length()+")");
	                for (int j = 0; j < classes.length(); j++) {
	                    JSONObject clazz = (JSONObject) classes.get(j);
	                    // example: "textRaw": "Class: Domain","type": "class","name": "Domain"
	                    String trigger = clazz.getString("name");
	                    if (!trigger.startsWith(moduleName)) {
	                    	trigger=moduleName+'.'+trigger;
	                    }
	                    String desc = formatedName(trigger,clazz.getString("textRaw"))+clazz.getString("desc");
						Entry entry = new Entry(moduleObj,EntryType.clazz,trigger,trigger,desc);
						repo.addEntry(entry);	
	                }
                }
                
        	}
        } catch (JSONException e) {
        	log(e.getLocalizedMessage()+"\n"+e);
        }		
    	
    	
    }

	private String formatedName(String name) {
		return "<b>"+name+"</b><br/>";
	}
	private String formatedName(String name, String trigger) {
		return formatedName(name)+"<code>"+trigger+"</code><br/>";
	}


    private static void debug(String s){
    	//NodeclipseConsole.write(s);
    	System.out.print(s);
    }
    private static void log(String s){
    	//NodeclipseConsole.write(s);
    	System.out.print(s);
    }
    
    public static void main(String[] args){
    	//System.out.println(x);
    	ContentFromSources c = new ContentFromSources(ALL_JSON);
    	c.readModel();    	
    }
}

