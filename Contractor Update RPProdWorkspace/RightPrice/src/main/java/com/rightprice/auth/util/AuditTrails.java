package com.rightprice.auth.util;

import org.opensaml.saml2.core.Assertion;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Component;

@Component
public class AuditTrails {
	
	private String currentUser; 
	private String currentTimeStamp;
	
	private static int isActive = 1;
	public String getCurrentUser() {
		/*currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
		AppLoger.APPLOGGER.info(currentUser);
		return SecurityContextHolder.getContext().getAuthentication().getName();*/
		AppLoger.APPLOGGER.info("on Login Services");
    	AppLoger.APPLOGGER.info("1");
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	AppLoger.APPLOGGER.info("2");
    	SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
    	AppLoger.APPLOGGER.info("Credentials are : " +credential);
    	AppLoger.APPLOGGER.info("3");
    	Assertion assertion = credential.getAuthenticationAssertion();
    	AppLoger.APPLOGGER.info("4");
    	String id = assertion.getID();
    	AppLoger.APPLOGGER.info("Assertion id is :" +id);
    	String username = credential.getNameID().getValue().toString();
		AppLoger.APPLOGGER.info("currentUser : "  + currentUser);
		return username;
	}
	public String getCurrentTimeStamp() {
		
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("MM-dd-yyyy HH:mm:ss.SSS");  
																		//MM-dd-yyyy HH:mm:ss.SSS
		String currentTimeStamp= sdf.format(dt);
		AppLoger.APPLOGGER.info("currentTimeStamp : "  + currentTimeStamp);
		return currentTimeStamp;
	}
	
}
