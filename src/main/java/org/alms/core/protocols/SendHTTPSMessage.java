package org.alms.core.protocols;

import org.alms.messages.IMsg;
import org.alms.DataAccess.UserManager;
import org.alms.beans.Header;
import org.alms.beans.RelatedParty;
import org.alms.beans.UserAccount;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import javax.net.ssl.SSLSocketFactory;

public class SendHTTPSMessage
		implements ISendMessage
{
	
	private String username;
	private String password;
	private RelatedParty destination;
	

	@Override
	public String DeliverMessage(IMsg messageData, String SchemaValidation)  
	{
		OutputStreamWriter  wr = null;
		BufferedReader rd  = null;
		StringBuilder sb = null;
		String line = null;	
		
		try
		{
			setDestination(messageData);
			UserAccount acc = getUser(messageData);
			serviceDetails(acc);
			
			// Open a secure connection.
			URL url = new URL( acc.getURL());	      	  
		    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();	      
		    con.setSSLSocketFactory(getFactory(new File(acc.getFileStore()), acc.getFileStorePW()));		
		    
		    // Set up the connection properties		      
		    con.setRequestMethod(acc.getHttpVerb());
		    con.setDoOutput(true);
		    con.setReadTimeout(10000);  
		    con.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
		    con.setRequestProperty("username", this.username);
		    con.setRequestProperty("password", this.password);
		    con.setRequestProperty("SchemaValidation", SchemaValidation);	  	
		    
		    con.connect();
		    
		    //Write to request			    
		    wr = new OutputStreamWriter (con.getOutputStream());
		    wr.write (messageData.getIncomingMessage());
		    wr.flush();				    
		    
		    //Read from Response
		    rd  = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
	
	private void setDestination(IMsg messageData)
	{
		this.destination = messageData.getMsgDestination();		
	}
	
	
	private UserAccount getUser(IMsg messageData) throws Exception
	{	 
		
		UserManager manager = new UserManager();
		return manager.GetUserByUniversialId(this.destination.getNamespaceID());
	}
	
	private void serviceDetails(UserAccount acc)
	{
		
		for (Header hd : acc.getHeaderVariables())
		{
			if (hd.getVariableName().equals("username"))
			{
				this.username=hd.getValue();
			}
			
			if (hd.getVariableName().equals("password"))
			{
				this.password=hd.getValue();
			}
		}
		
	}	
	
	private SSLSocketFactory getFactory( File pKeyFile, String pKeyPassword ) throws  Exception   
	{
		  KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
		  KeyStore keyStore = KeyStore.getInstance("JKS");
	
		  InputStream keyInput = new FileInputStream(pKeyFile);
		  keyStore.load(keyInput, pKeyPassword.toCharArray());
		  keyInput.close();
	
		  keyManagerFactory.init(keyStore, pKeyPassword.toCharArray());
	
		  SSLContext context = SSLContext.getInstance("TLS");
		  context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
	
		  return context.getSocketFactory();
	}
}
