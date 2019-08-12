package com.dd.common.utils.number;

import java.math.BigDecimal;

/**
 * @author: Ivan
 * @date: 2019/5/21 20:54
 */
public class NumberUtils {

	public static String bigDecimalToString(BigDecimal decimal) {
		String s = decimal.toEngineeringString();
		if (s.contains(".")) {
			char lastChar = s.charAt(s.length() - 1);
			while (lastChar == '0') {
				s = s.substring(0, s.length() - 1);
				lastChar = s.charAt(s.length() - 1);
			}
			lastChar = s.charAt(s.length() - 1);
			if (lastChar == '.') {
				s = s.substring(0, s.length() - 1);
			}
			return s;
		} else {
			return s;
		}
	}



}
