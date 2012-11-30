package org.alms.beans;
import java.util.ArrayList;
import javax.ws.rs.core.MultivaluedMap;

public class DestinationInformation 
{
	public String URL;
	public String httpVerb;
	public MultivaluedMap<String, String> headers;
	public String destinationType;
	
	public String getDestinationType() {
		return destinationType;
	}
	public void setDestinationType(String destinationType) {
		this.destinationType = destinationType;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getHttpVerb() {
		return httpVerb;
	}
	public void setHttpVerb(String httpVerb) {
		this.httpVerb = httpVerb;
	}
	public MultivaluedMap<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(MultivaluedMap<String, String> headers) {
		this.headers = headers;
	}
}
