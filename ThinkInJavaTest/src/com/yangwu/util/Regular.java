package com.yangwu.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regular {
	public static boolean match(String content,String regex) {
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(content);
		
		return matcher.matches();
		
	}
}
