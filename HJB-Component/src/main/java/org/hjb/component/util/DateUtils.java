package org.hjb.component.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String getNowYYYYMMddHHmmss() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date());
	}

	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}
}
