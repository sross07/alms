/*******************************************************************************
 * Copyright (c) 2012 Scott Ross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Scott Ross - initial API and implementation
 ******************************************************************************/
package org.alms.DataAccess;
import java.util.List;
import org.alms.beans.*;

import com.google.code.morphia.Datastore;

public class PollManager 
{
	private MorphiaMapperSetup morphiaMapper;
	private Datastore ds;
	
	public PollManager() throws Exception {
		this.morphiaMapper= new MorphiaMapperSetup();		
		this.ds=morphiaMapper.getDs();	
	}
	
	public void Save(PollMessage msg) 
	{		
		this.ds.save(msg);
	}
	
	public PollMessage GetPollMessage(String uuid)
	{
		List<PollMessage> messageList=
				this.ds.find(org.alms.beans.PollMessage.class, "uuid =", uuid).asList();
		
		return messageList.get(0);
	}
}
