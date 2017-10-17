package com.bd;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;

public class CheckAvailability extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		
		String domain = request.getParameter( "domainName" );
		
		if( isAvailable( domain ) ) {
			
			response.getWriter().write( "available" );
		}
		else {
			
			response.getWriter().write( "Not available" );
		}
		
	}
	
	private boolean isAvailable(String domain) throws IOException {
		
		SecurityFactory.reloadSecurityDetails();
		
		Response btclResponse = Jsoup.connect( 
				Configuration.getHost() 
				+ "/DomainAction.do?"
				+ "domainName="
				+ domain 
				+ "&columnID="
				+ Configuration.getColumnid()
				+ "&domainExt=1"
				+ "&searchDomain=yes"
				+ "&mode=" + Configuration.getMode()
				+ "&csrfPreventionSalt="
				+ SecurityFactory.getCSRF() )
				.referrer( Configuration.getHost() + "/" )
				.cookie( "JSESSIONID", SecurityFactory.getSessionID() )
				.method( Method.POST )
				.timeout( 100000 )
				.execute();
		
		return btclResponse.body().contains("Congratulations! This domain is available to buy.");
	}
}
