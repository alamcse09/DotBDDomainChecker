package com.bd;

public class Configuration {

	private static final String host = "http://bdia.btcl.com.bd";
	
	private static final String mode = "checkDomain";
	private static final String columnID = "10005";
	
	public static String getHost() {
		return host;
	}
	public static String getMode() {
		return mode;
	}
	public static String getColumnid() {
		return columnID;
	}
}
