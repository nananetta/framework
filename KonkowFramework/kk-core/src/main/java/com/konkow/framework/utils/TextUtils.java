package com.konkow.framework.utils;

public class TextUtils {

	public static String removeQuote(String text){
		try {
			return text.replace("\"", "");
		} catch(Exception e){
			throw ExceptionUtils.wrap(e);
		}
	}
	
	public static String normalizePath(String text){
		try {
			return text.trim().replace("/", "\\\\");
		} catch(Exception e){
			throw ExceptionUtils.wrap(e);
		}
	}
}
