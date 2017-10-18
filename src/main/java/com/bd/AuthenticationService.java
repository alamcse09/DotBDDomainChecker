package com.bd;

public class AuthenticationService extends DataInterface<Subscriber>{

	private static AuthenticationService authenticationService = null;
	
	private AuthenticationService() {};
	
	
	private synchronized static void createAuthenticationService() {
		
		if( authenticationService == null ) {
			
			authenticationService = new AuthenticationService();
		}
	}
	
	public synchronized static AuthenticationService getInstance() {
		
		if( authenticationService == null ) {
			
			createAuthenticationService();
		}
		
		return authenticationService;
	}
	
	public boolean authenticate( String email, String token ) {
		
		Subscriber subscriber = getByColumn( "email", email );
		
		return subscriber.getToken() == token;
	}
}
