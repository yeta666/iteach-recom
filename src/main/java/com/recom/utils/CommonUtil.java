package com.recom.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	/**
	 * 公共工具类
	 * @author yangzq
	 */

	/**
	 * 日期格式化工具类
	 * @author EasonLian
	 */
	private static SimpleDateFormat sdf = null;
	
	public static String getFormatDateString(String pattern,Date date) {
		sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
}
