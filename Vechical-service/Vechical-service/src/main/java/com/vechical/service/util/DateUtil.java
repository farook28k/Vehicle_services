package com.vechical.service.util;

import java.util.Date; 
import java.util.Optional;

public class DateUtil {
	private DateUtil() {

	}

	public static Date convertSQLDateToUtilDate(java.sql.Date sdate) {
		return Optional.ofNullable(sdate).map(date -> new Date(sdate.getTime())).orElse(null);
	}

	public static java.sql.Date convertUtilToSQL(Date udate) {
		return Optional.ofNullable(udate).map(date -> new java.sql.Date(udate.getTime())).orElse(null);
	}

}
 