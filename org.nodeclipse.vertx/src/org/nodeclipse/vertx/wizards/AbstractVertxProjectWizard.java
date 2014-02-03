package org.nodeclipse.vertx.wizards;

import org.nodeclipse.ui.wizards.AbstractNodeProjectWizard;
import org.nodeclipse.vertx.nature.VertxNature;

/**
 * AbstractNashornProjectWizard (copied AbstractNodeProjectWizard), then made as subclass;
 * just like AbstractPhantomjsProjectWizard
 * @author Paul Verest
 */

//@SuppressWarnings("restriction")
public abstract class AbstractVertxProjectWizard extends AbstractNodeProjectWizard {
//extends Wizard implements INewWizard {
	
    //+ to let overriding
	@Override
    protected String getProjectNature(){
		return VertxNature.NATURE_ID;    	
    }

}

