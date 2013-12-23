package org.nodeclipse.ui.contentassist;

/**
 * immutable Model Entry
 * 
 * @author Paul Verest
 */
public class Entry implements Comparable<Entry>{
	
	final Module module;
	final EntryType type; 
	final String name;
	final String trigger;
	final String desc;

	public Entry(Module module,EntryType type, String name, String trigger, String desc){
		this.module = module;
		this.type = type;
		this.name=name;
		this.trigger=trigger;
		this.desc=desc;		
	}

	@Override
	public int compareTo(Entry o) {
		return trigger.compareTo(o.trigger);
	}

	@Override
	public String toString(){
		return trigger;
	}
}
