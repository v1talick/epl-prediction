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
}
