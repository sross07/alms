package org.web.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class ALMSActionBean implements ActionBean
{
	private ALMSActionBeanContext context;

	public ALMSActionBeanContext getContext() {
		return this.context;
	}
	
	public void setContext(ActionBeanContext context) {
		this.context = (ALMSActionBeanContext) context;		
	}


}
