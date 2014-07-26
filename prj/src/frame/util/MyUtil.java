package frame.util;

import java.io.UnsupportedEncodingException;

public class MyUtil {
	public static String convert(String str) {
		String result = null;
		
		try {
			result = new String(str.getBytes("8859_1"), "KSC5601");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
				
		return result;
	}
}
