package com.sreenivasam.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;

public class Utility {

	public static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

	public static final String[] monthNames = { "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };

	public static boolean isEmpty(Object obj) {
		try {

			if (obj instanceof Collection) {
				return obj == null || ((Collection<?>) obj).size() == 0;
			} else if (obj instanceof String) {
				return obj == null || ((String) obj).isEmpty();
			} else if (obj instanceof Integer) {
				return obj == null || Integer.valueOf(String.valueOf(obj)) == 0;
			} else if (obj instanceof Long) {
				return obj == null || Long.valueOf(String.valueOf(obj)) == 0;
			} else if (obj instanceof Float) {
				return obj == null || Float.valueOf(String.valueOf(obj)) == 0;
			} else if (obj instanceof Double) {
				return obj == null || Double.valueOf(String.valueOf(obj)) == 0;
			} else if (obj instanceof Boolean) {
				return obj == null;
			} else {
				return obj == null;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public static String getDateDifference(LocalDate date1, LocalDate date2) {
		Period age = Period.between(date1, date2);
		String ageVal = age.getYears() + " Years, " + age.getMonths() + " Months, " + (age.getDays() + 1) + " Days";
		return ageVal;
	}

	public static String getMonthName(Integer month) {
		String monthName = "";
		if (month != null) {
			monthName = monthNames[month - 1];
		}
		return monthName;
	}

}
