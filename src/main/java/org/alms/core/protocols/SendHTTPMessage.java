package org.alms.core.protocols;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.alms.DataAccess.UserManager;
import org.alms.beans.Header;
import org.alms.beans.RelatedParty;
import org.alms.beans.UserAccount;
import org.alms.messages.IMsg;

public class SendHTTPMessage 
	implements ISendMessage
{
	@Override  
	public String DeliverMessage(IMsg messageData, String SchemaValidation)	
	{	
		String username="";
		String password="";
		
		URL serverAddress = null;
		HttpURLConnection  connection = null;
		OutputStreamWriter wr = null;
		BufferedReader rd  = null;
		StringBuilder sb = null;
		String line = null;
	      
		try
		{
			RelatedParty destination = messageData.getMsgDestination();			 
			UserManager manager = new UserManager();
			
			UserAccount destinationAccount = manager.GetUserByUniversialId(destination.getNamespaceID());	
			
			for (Header hd : destinationAccount.getHeaderVariables())
			{
				if (hd.getVariableName().equals("username"))
				{
					username=hd.getValue();
				}
				
				if (hd.getVariableName().equals("password"))
				{
					password=hd.getValue();
				}
			}
			
			serverAddress = new URL(destinationAccount.getURL());
	        connection = null;
	          
	        //Set up the initial connection
	        connection = (HttpURLConnection) serverAddress.openConnection();
	        connection.setRequestMethod(destinationAccount.getHttpVerb());
	        connection.setDoOutput(true);
	        connection.setReadTimeout(10000);       
	     
	        connection.setRequestProperty("username", username);
	        connection.setRequestProperty("password", password);
	        connection.setRequestProperty("SchemaValidation", SchemaValidation);	        
	        
	        connection.connect();

	        wr = new OutputStreamWriter(connection.getOutputStream());
	        wr.write(messageData.getIncomingMessage());
	        wr.flush();
	        
	        //read the result from the server
	        rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        sb = new StringBuilder();
	        
	        while ((line = rd.readLine()) != null)
	        {
	            sb.append(line);
	        }
	        
	        return sb.toString();	
		}
		
		catch(Exception e)
		{
			return e.toString();
		}	
		
	}

}
