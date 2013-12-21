package org.nodeclipse.ui.contentassist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pverest
 */
public class Model implements IRepo {
	
	List<Module> modules = new ArrayList<Module>();
	List<Entry> entries = new ArrayList<Entry>();

	@Override
	public void addModule(Module module) {
		modules.add(module);
	}

	@Override
	public void addEntry(Entry entry) {
		entries.add(entry);
	}

	@Override
	public List<Entry> findMatchingEntries(String input) {
		// TODO Auto-generated method stub
		return null;
	}

}
