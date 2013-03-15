package org.web.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public class ALMSActionBean implements ActionBean
{
	private ActionBeanContext context;

	@Override
	public ActionBeanContext getContext() {
		return this.context;
	}

	@Override
	public void setContext(ActionBeanContext context) {
		this.context = (ActionBeanContext) context;		
	}
}
