package org.web.action;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class LogoutActionBean extends ALMSActionBean 
{
    public Resolution logout() throws Exception {
        getContext().logout();
        return new RedirectResolution("/UserLogin.action");
    }
}

