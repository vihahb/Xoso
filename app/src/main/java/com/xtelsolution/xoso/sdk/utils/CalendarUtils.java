package com.xtelsolution.xoso.sdk.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vivhp on 9/22/2017.
 */

public class CalendarUtils {
    private static CalendarUtils instance;
    private static final String TAG = "CalendarUtils";
    public synchronized static CalendarUtils getInstance() {
        if (instance == null)
            instance = new CalendarUtils();
        return instance;
    }

    public static String getMonth(Calendar calendar) {
        String monthName = new SimpleDateFormat("MMM").format(calendar.getTime());
        return monthName;
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        Log.e(TAG, "getMonthForInt: " + month);
        return month;
    }

    public static String getDayName(Calendar calendar) {

        Date date = new Date(calendar.getTimeInMillis());
        DateFormat f = new SimpleDateFormat("EEE");
        try {
            Log.e(TAG, "getDayName: " + f.format(date));
            return f.format(date);
        }
        catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
