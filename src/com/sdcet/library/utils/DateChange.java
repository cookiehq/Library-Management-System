package com.sdcet.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateChange {
	
	/**
	 * 当前时间加一个月
	 * @param date
	 * @return
	 */
	public static String subMonth(String date){  
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date dt = null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(dt); 
        rightNow.add(Calendar.MONTH, 1);  
        Date dt1 = rightNow.getTime();  
        String reStr = sdf.format(dt1);  
        return reStr;  
    }
	
	/**
	 * 当前时间加五天
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String subDay(String date){  
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	     Date dt = null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	     Calendar rightNow = Calendar.getInstance();  
	     rightNow.setTime(dt);  
	     rightNow.add(Calendar.DAY_OF_MONTH, 5);  
	     Date dt1 = rightNow.getTime();  
	     String reStr = sdf.format(dt1);  
	     return reStr;  
	 }
	
	/**
	 * 当前时间加两天
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String subDay2(String date){  
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	     Date dt = null;
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	     Calendar rightNow = Calendar.getInstance();  
	     rightNow.setTime(dt);  
	     rightNow.add(Calendar.DAY_OF_MONTH, 2);  
	     Date dt1 = rightNow.getTime();  
	     String reStr = sdf.format(dt1);  
	     return reStr;  
	 }
}
