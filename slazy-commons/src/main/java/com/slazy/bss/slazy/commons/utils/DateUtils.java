package com.slazy.bss.slazy.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static DateUtils eu = new DateUtils();

	public static DateUtils getInstance() {
		return eu;
	}

	public String timestampToDate(String timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(new Date(Long.parseLong(timestamp) * 1000L));
		return date;
	}

	public String timestampToDateForYYYYMMDD(String timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date(Long.parseLong(timestamp) * 1000L));
		return date;
	}

	public String timestampToDateForYYYYMMDDHHMM(String timestamp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String date = sdf.format(new Date(Long.parseLong(timestamp) * 1000L));
		return date;
	}

	public String StringToStringForYYYYMMDD(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		return sdf.format(d);
	}

	public Date StringToDateForYYYYMMDD(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		return d;
	}
	
	/**
	 * 增加分钟
	 * @param minute
	 * @return
	 */
	public Date addMinuteTimes(int minute) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE,minute);
		return cal.getTime();
	}
	
	/**
	 * 增加小时
	 * @param minute
	 * @return
	 */
	public Date addHoursTimes(int hour) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR,hour);
		return cal.getTime();
	}
	
	/**
	 * 增加秒
	 * @param minute
	 * @return
	 */
	public Date addSecondTimes(int second) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND,second);
		return cal.getTime();
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(DateUtils.getInstance().StringToStringForYYYYMMDD("2017-11-18 12:00:00"));
	}

}
