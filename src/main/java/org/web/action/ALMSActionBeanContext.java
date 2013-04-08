package org.web.action;
import net.sourceforge.stripes.action.ActionBeanContext;
import org.alms.beans.*;

public class ALMSActionBeanContext extends ActionBeanContext
{	
	public UserAccount getUser() {
        return (UserAccount) getRequest().getSession().getAttribute("user");
    }

    /** Sets the currently logged in user. */
    public void setUser(UserAccount currentUser) {
        getRequest().getSession().setAttribute("user", currentUser);
    }

    /** Logs the user out by invalidating the session. */
    public void logout() {
        getRequest().getSession().invalidate();
    }
}
