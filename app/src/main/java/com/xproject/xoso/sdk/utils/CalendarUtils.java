package com.xproject.xoso.sdk.utils;

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
        String day_name = "";
        try {
            Log.e(TAG, "getDayName: " + f.format(date));
            switch (f.format(date)){
                case "T.2":
                    day_name = "Thứ 2";
                    break;
                case "T.3":
                    day_name = "Thứ 3";
                    break;
                case "T.4":
                    day_name = "Thứ 4";
                    break;
                case "T.5":
                    day_name = "Thứ 5";
                    break;
                case "T.6":
                    day_name = "Thứ 6";
                    break;
                case "T.7":
                    day_name = "Thứ 7";
                    break;
                case "CN":
                    day_name = "Chủ nhật";
                    break;
                case "Mon":
                    day_name = "Thứ 2";
                    break;
                case "Tue":
                    day_name = "Thứ 3";
                    break;
                case "Wed":
                    day_name = "Thứ 4";
                    break;
                case "Thu":
                    day_name = "Thứ 5";
                    break;
                case "Fri":
                    day_name = "Thứ 6";
                    break;
                case "Sat":
                    day_name = "Thứ 7";
                    break;
                case "Sun":
                    day_name = "Chủ nhật";
                    break;
            }
            return day_name;
        }
        catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
