<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="org.apache.http.client.methods.HttpGet"%>
<%@page import="org.apache.http.util.EntityUtils"%>
<%@page import="org.apache.http.HttpStatus"%>
<%@page import="org.apache.http.client.entity.UrlEncodedFormEntity"%>
<%@page import="org.apache.http.message.BasicNameValuePair"%>
<%@page import="org.apache.http.NameValuePair"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.http.entity.StringEntity"%>
<%@page import="org.apache.http.HttpResponse"%>
<%@page import="org.apache.http.client.protocol.HttpClientContext"%>
<%@page import="org.apache.http.client.methods.HttpPost"%>
<%@page import="org.apache.http.protocol.BasicHttpContext"%>
<%@page import="org.apache.http.protocol.HttpContext"%>
<%@page import="org.apache.http.impl.client.BasicCookieStore"%>
<%@page import="org.apache.http.client.CookieStore"%>
<%@page import="org.apache.http.impl.client.DefaultHttpClient"%>
<%@page import="org.apache.http.client.HttpClient"%>
<html>
<body>

<%
	
	String domain = request.getParameter("domainName");
	if( domain != null ){
		
		HttpGet httpPost = new HttpGet("http://localhost:8080/BTCL_NEW/Login.do");
		/* ArrayList<NameValuePair> postParameters;

		postParameters = new ArrayList<NameValuePair>();
	    postParameters.add(new BasicNameValuePair("username", "admin"));
	    postParameters.add(new BasicNameValuePair("password", "admin"));
	    
	    httpPost.setHeader( "Referer", "http://localhost:8080/BTCL_NEW/" );
	    
	    httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
 */	    
		HttpClient client = new DefaultHttpClient();
		CookieStore cookieStore = new BasicCookieStore();
		HttpContext context = new BasicHttpContext();
		
		context.setAttribute( HttpClientContext.COOKIE_STORE , cookieStore );
		
		HttpResponse loginResponse = client.execute( httpPost, context );
		
		String outData = EntityUtils.toString( loginResponse.getEntity() );
		
		Document doc = Jsoup.parse( outData );
		Element csrf = doc.tagName( "input" );
		
		System.out.println( csrf );
		//System.out.println( loginResponse.getStatusLine() );
		
		/* EntityUtils.consumeQuietly( loginResponse.getEntity() );
		
		if( loginResponse.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY ){
			
		
			httpPost = new HttpPost("http://localhost:8080/BTCL_NEW/DomainAction.do");

			postParameters = new ArrayList<NameValuePair>();
		    postParameters.add(new BasicNameValuePair( "columnID", "10005" ) );
		    postParameters.add(new BasicNameValuePair( "domainName", domain ) );
		    postParameters.add(new BasicNameValuePair( "domainExt", "1" ) );
		    
		    httpPost.setHeader( "Referer", "http://localhost:8080/BTCL_NEW/" );
		    
		    httpPost.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		    
		    HttpResponse domainResponse = client.execute( httpPost, context );
		    
		    String output = EntityUtils.toString( domainResponse.getEntity() );
		    
		    System.out.println( output );
		    
		 	if( output.contains("Congratulations! This domain is available to buy. ")){
		 		
		 		System.out.println( "available" );
		 	}
		 	else{
		 		
		 		System.out.println( "not available" );
		 	}
		    
		    EntityUtils.consumeQuietly( domainResponse.getEntity() );
		} */
		
	}
%>
<form method="get" action="check.do">
	<input type="text" name="domainName" />
	<input type="submit" value="Check"/>
</form>
</body>
</html>
