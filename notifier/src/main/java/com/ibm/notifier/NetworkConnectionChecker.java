package com.ibm.notifier;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class NetworkConnectionChecker {
	
	public static final String DEFAULT_URL = "http://w3.ibm.com";
	private IOException exception;
	
	public boolean isConnected() throws IOException{
		return isConnected(DEFAULT_URL);
	}
	
	public boolean isConnected(final String url) throws IOException {
		URLConnection myURLConnection;
		try {
		    URL myURL = new URL(url);
		    myURLConnection = myURL.openConnection();
		    myURLConnection.connect();
		    return true;
		} 
		catch (MalformedURLException e) {
			exception = new IOException("Invalid url: "+url,e.getCause()); 
		    throw exception;
		} 
		catch (IOException e) {
			exception = e;
		    return false;
		}
		
	}
	
	public IOException getIOException(){
		return this.exception;
	}

}


