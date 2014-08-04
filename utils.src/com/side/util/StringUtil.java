package com.side.util;

import java.util.regex.Pattern;

import com.side.consts.CommonConst;

public final class StringUtil {
	
	private static final int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	private static final String numberRegEx = "^[0-9]{1,}$";
	
	public static boolean isDate(String date) {
		try {
			if (date == null) {
				return false;
			}
			date = date.trim();
			int len = date.length();
			// 字符串长度不为8或10，即不是20140101格式或不是2014-01-01格式，则认为不是日期字符串
			if (len != 8 && len != 10) {
				return false;
			}
			// 如果是2014-01-01格式，则转换成20140101格式
			if (len == 10) {
				date = date.replace("-", "");
			}
			if (!isNumber(date)) {
				return false;
			}
			
			int year = Integer.valueOf(date.substring(0, 4));
			int month = Integer.valueOf(date.substring(4, 6));
			int day = Integer.valueOf(date.substring(6, 8));
			
			// 月份符合
			if (month > 0 && month <= 12) {
				// 如果是闰年，则日期不能超过29
				if (month == 2 && isLeapYear(year)) {
					if (day > 0 || day <= 29) {
						return true;
					}
				}
				
				if (day > 0 && day <= days[month - 1]) {
					return true;
				}
			}
		} catch (Exception e) {
			CommonConst.log.error("error with paramater date: " + date);
		}
		// 默认返回失败
		return false;
	}
	
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
	}
	
	
	public static boolean isNumber(String number) {
		return Pattern.compile(numberRegEx).matcher(number).find();
	}
	
	
}
