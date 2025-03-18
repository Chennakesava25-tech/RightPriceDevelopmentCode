package com.rightprice.auth.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String getMySqlFormattedDate(String inputDate){
		String mySqlFormatDate = inputDate;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 Date date;
		try {
			mySqlFormatDate = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("dd/MM/yyyy").parse(inputDate));
			AppLoger.APPLOGGER.info("Formatted Date : "  + mySqlFormatDate);
		} catch (ParseException e) {
			e.printStackTrace();
			AppLoger.APPLOGGER.info("Exception occured while date conversion to MySql DB format");
		}
		
		return mySqlFormatDate;
	}
	
	public static String getMSSqlFormattedDate(String inputDate){
		AppLoger.APPLOGGER.info("============================================MSSQL date conversion====================================================================");
		AppLoger.APPLOGGER.info("input date : " + inputDate);
		String msSqlFormatDate = inputDate;
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//Date date;
		try {
			msSqlFormatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("dd/MM/yyyy").parse(inputDate));
			AppLoger.APPLOGGER.info("Formatted Date : "  + msSqlFormatDate);
		} catch (ParseException e) {
			e.printStackTrace();
			AppLoger.APPLOGGER.info("Exception occured while date conversion to MySql DB format");
		}
		
		AppLoger.APPLOGGER.info("===============================================MSSQL date conversion=================================================================");
		return msSqlFormatDate;
	}

}
