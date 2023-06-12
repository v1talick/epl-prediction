package com.OOP.eplpredictions.utils;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    public static boolean isFirstDate7DaysLater(Date firstDate, Date secondDate) {
        long sevenDaysInMillis = 7 * 24 * 60 * 60 * 1000; // 7 days in milliseconds
        long differenceInMillis = firstDate.getTime() - secondDate.getTime();

        return differenceInMillis >= sevenDaysInMillis;
    }

    public static boolean isScheduleDate(Date date) {

        // Get today's date
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());

        // Get the date passed in as a parameter
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // Subtract 2 days from today's date
        Calendar twoDaysAgo = (Calendar) today.clone();
        twoDaysAgo.add(Calendar.DAY_OF_MONTH, -2);

        // Add 7 days to today's date
        Calendar sevenDaysLater = (Calendar) today.clone();
        sevenDaysLater.add(Calendar.DAY_OF_MONTH, 7);

        // Check if the date falls within the valid range
        return !cal.before(twoDaysAgo) && !cal.after(sevenDaysLater);
    }

}
