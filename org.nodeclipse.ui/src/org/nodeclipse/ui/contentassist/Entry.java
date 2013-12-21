package org.nodeclipse.ui.contentassist;

public class Entry {
	
	private Module module;
	public EntryType type; 
	private String name;
	private String trigger;
	private String desc;

	public Entry(Module module,EntryType type, String name, String trigger, String desc){
		this.module = module;
		this.type = type;
		this.name=name;
		this.trigger=trigger;
		this.desc=desc;		
	}

}
