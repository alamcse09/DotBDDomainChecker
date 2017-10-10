package com.bd;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

public class Login {

	public static boolean login() throws IOException {
		
		String sessionId = SecurityFactory.getSessionID();
		String csrf = SecurityFactory.getCSRF();

		Response loginresponse = getLoginResponse( sessionId, csrf );
		
		if( loginresponse.body().contains( "login-form" ) ) {
			
			SecurityFactory.reloadSecurityDetails();
			
			sessionId = SecurityFactory.getSessionID();
			csrf = SecurityFactory.getCSRF(); 
			
			loginresponse = getLoginResponse(sessionId, csrf);
			
			if( loginresponse.body().contains( "login-form" ) ) {
				
				return false;
			}
			else {
				
				return true;
			}
		}
		
		return true;
	}
	
	private static Response getLoginResponse(String sessionId, String csrf ) throws IOException {
		
		Response loginresponse = Jsoup.connect( 
				Configuration.getHost() 
				+ "/Login.do?username="
				+ Configuration.getUsername() 
				+ "&password="
				+ Configuration.getPassword()
				+ "&csrfPreventionSalt="
				+ csrf )
				.referrer( Configuration.getHost() + "/" )
				.cookie( "JSESSIONID", sessionId )
				.method( Method.POST )
				.timeout( 10000 )
				.execute();
		
		return loginresponse;
	}
}
