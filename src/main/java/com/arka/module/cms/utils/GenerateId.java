package com.arka.module.cms.utils;

public class GenerateId {
	public static  String padLeftZeros(String type, String inputString, int length) {
	    if (inputString.length() >= length) {
	        return inputString;
	    }
	    StringBuilder sb = new StringBuilder();
	    while (sb.length() < length - inputString.length()) {
	        sb.append('0');
	    }
	    sb.append(inputString);
	 
	    return type+sb.toString();
	}

}
