package com.bd;

import org.jsoup.Jsoup;

import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

public class SecurityFactory {

	private static String session = "";
	private static String csrf = "";
	
	public static String getSessionID() throws IOException {
		
		return session;
	}
	
	public static String getCSRF() throws IOException {
		
		return csrf;
	}
	
	public static String forceGetCSRF() throws IOException {
		
		reloadSecurityDetails();
		return csrf;
	}
	
	public static String forceGetSessionID() throws IOException {
		
		reloadSecurityDetails();
		return session;
	}
	
	public static void reloadSecurityDetails() throws IOException {
		
		Response res = Jsoup.connect( Configuration.getHost() + "/domain/search" ).method( Method.GET).timeout(10000).execute();
		Document doc = Jsoup.parse( res.body() );
		
		session = res.cookie( "JSESSIONID" );
		csrf = doc.select( "input[name=csrfPreventionSalt]" ).val();
	}
}
