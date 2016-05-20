package org.ytgs.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;


public class StringUtil {
	
	//判断是否该字符串是否为数字
	public static boolean  isNumber(String str){
		Pattern pattern=Pattern.compile("^(-?\\d+)(\\.\\d+)?");		
		return pattern.matcher(str).matches();
	}
	//判断是否该字符串是否为科学计数法
	public static boolean  isScienticNotation(String str){
		Pattern pattern=Pattern.compile("^((-?\\d+.?\\d*)[Ee]{1}(-?\\d+))$");		
		return pattern.matcher(str).matches();
	}
	
	public static String gbkToUtf8(String str) {
	    try {
	      if (str == null) str = "";
	      return new String(str.getBytes("GBK"), "UTF-8"); } catch (UnsupportedEncodingException e) {
	    }
	    return str;
	}
	
	public static String isoToUtf8(String str) {
		try {
			if (str == null) str = "";
			return new String(str.getBytes("ISO-8859-1"), "UTF-8"); } catch (UnsupportedEncodingException e) {
			}
		return str;
	}
}
