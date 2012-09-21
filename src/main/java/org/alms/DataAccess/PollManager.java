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
