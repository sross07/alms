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
		this.m = new Mongo();
				
		
		Morphia morphia = new Morphia();
		morphia.map(org.alms.beans.UserAccount.class).map(org.alms.beans.MessageInfo.class).map(org.alms.beans.PollMessage.class);		
		ds=morphia.createDatastore(m,c.getProperty("DataBase"));
		
		if (c.getProperty("MongoAuthMode").equals("true"))
		{
			ds.getDB().authenticate(c.getProperty("MongoUsername"), c.getProperty("MongoPassword").toCharArray());
		}
	
	}	

}
