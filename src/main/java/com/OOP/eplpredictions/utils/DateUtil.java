package com.OOP.eplpredictions.utils;

import com.OOP.eplpredictions.entities.MatchEntity;
import com.OOP.eplpredictions.repositories.MatchRepository;
import com.OOP.eplpredictions.repositories.impl.MatchRepositoryImpl;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static boolean compareDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        return !cal1.before(cal2);
    }

}
