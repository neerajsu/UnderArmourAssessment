package com.underarmour.assessment.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public  static String toDateFormat(Date dateToFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dateToFormat);
	}
}
