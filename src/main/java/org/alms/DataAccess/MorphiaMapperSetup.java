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

import org.alms.core.ApplicationConfig;
import com.mongodb.Mongo;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;

public class MorphiaMapperSetup 
{
	private Mongo m;
	private Datastore ds;
	
	public Datastore getDs() {
		return ds;
	}
	
	public void setDs(Datastore ds) {
		this.ds = ds;
	}
	
	public MorphiaMapperSetup() throws Exception
	{
		// Eventually need to do checking..
		this.Setup();		
	}
		
	/**
	 * 
	 * Implemented a authentication on MongoDB
	 * Added a property in configuration to switch "true" / "false" in auth is on 
	 * This allows local development and flexibility 
	 * 
	 * @throws Exception
	 */
	
	private void Setup() throws Exception {	
		
		ApplicationConfig c = ApplicationConfig.getApplicationConfig();			
		this.m = new Mongo(c.getProperty("DatabaseUrl"));
		
				
		Morphia morphia = new Morphia();
		morphia.map(org.alms.beans.UserAccount.class).map(org.alms.beans.MessageInfo.class).map(org.alms.beans.PollMessage.class);		
				
		if (c.getProperty("MongoAuthMode").equals("true"))
		{
			this.ds=morphia.createDatastore(m,c.getProperty("DataBase"), c.getProperty("MongoUsername"), c.getProperty("MongoPassword").toCharArray());
		}
		else
		{
			this.ds=morphia.createDatastore(m,c.getProperty("DataBase"));
		}
	
	}	

}
