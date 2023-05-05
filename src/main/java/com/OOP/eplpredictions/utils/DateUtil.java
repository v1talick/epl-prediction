package com.OOP.eplpredictions.utils;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

//    public static boolean isDateLater(Date date1, Date date2) {
//        Calendar cal1 = Calendar.getInstance();
//        cal1.setTime(date1);
//        cal1.set(Calendar.HOUR_OF_DAY, 0);
//        cal1.set(Calendar.MINUTE, 0);
//        cal1.set(Calendar.SECOND, 0);
//        cal1.set(Calendar.MILLISECOND, 0);
//
//        Calendar cal2 = Calendar.getInstance();
//        cal2.setTime(date2);
//        cal2.set(Calendar.HOUR_OF_DAY, 0);
//        cal2.set(Calendar.MINUTE, 0);
//        cal2.set(Calendar.SECOND, 0);
//        cal2.set(Calendar.MILLISECOND, 0);
//
//        return !cal1.before(cal2);
//    }
    public static boolean isFirstDate7DaysLater(Date firstDate, Date secondDate) {
        long sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000; // 7 days in milliseconds
        long differenceInMillis = firstDate.getTime() - secondDate.getTime();

        return differenceInMillis >= sevenDaysInMillis;
    }
}
