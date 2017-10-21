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
    private static final String TAG = "CalendarUtils";
    private static CalendarUtils instance;

    public synchronized static CalendarUtils getInstance() {
        if (instance == null)
            instance = new CalendarUtils();
        return instance;
    }


    public static String getDay(Calendar calendar) {
        String dayValue = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        return dayValue;
    }


    public static String getMonthValue(Calendar calendar) {
        String monthValue = String.valueOf((calendar.get(Calendar.MONTH) + 1));
        return monthValue;
    }

    public static String getMonth(Calendar calendar) {
        String monthName = new SimpleDateFormat("MMM").format(calendar.getTime());
        return monthName;
    }

    public static String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    public static String getMonthName(Calendar calendar) {
        Date date = new Date(calendar.getTimeInMillis());
        DateFormat f = new SimpleDateFormat("MM");
        String month_name = "";

        try {

            switch (f.format(date)) {
                case "01":
                    month_name = "Tháng 1";
                    break;
                case "02":
                    month_name = "Tháng 2";
                    break;
                case "03":
                    month_name = "Tháng 3";
                    break;
                case "04":
                    month_name = "Tháng 4";
                    break;
                case "05":
                    month_name = "Tháng 5";
                    break;
                case "06":
                    month_name = "Tháng 6";
                    break;
                case "07":
                    month_name = "Tháng 7";
                    break;
                case "08":
                    month_name = "Tháng 8";
                    break;
                case "09":
                    month_name = "Tháng 9";
                    break;
                case "10":
                    month_name = "Tháng 10";
                    break;
                case "11":
                    month_name = "Tháng 11";
                    break;
                case "12":
                    month_name = "Tháng 12";
                    break;
            }

            return month_name;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDayName(Calendar calendar) {

        Date date = new Date(calendar.getTimeInMillis());
        DateFormat f = new SimpleDateFormat("EEE");
        String day_name = "";
        try {
            switch (f.format(date)) {
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
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDayNameTitle(Calendar calendar) {

        Date date = new Date(calendar.getTimeInMillis());
        DateFormat f = new SimpleDateFormat("EEE");
        String day_name = "";
        try {
            switch (f.format(date)) {
                case "T.2":
                    day_name = "TH 2";
                    break;
                case "T.3":
                    day_name = "TH 3";
                    break;
                case "T.4":
                    day_name = "TH 4";
                    break;
                case "T.5":
                    day_name = "TH 5";
                    break;
                case "T.6":
                    day_name = "TH 6";
                    break;
                case "T.7":
                    day_name = "TH 7";
                    break;
                case "CN":
                    day_name = "CN";
                    break;
                case "Mon":
                    day_name = "TH 2";
                    break;
                case "Tue":
                    day_name = "TH 3";
                    break;
                case "Wed":
                    day_name = "TH 4";
                    break;
                case "Thu":
                    day_name = "TH 5";
                    break;
                case "Fri":
                    day_name = "TH 6";
                    break;
                case "Sat":
                    day_name = "TH 7";
                    break;
                case "Sun":
                    day_name = "CN";
                    break;
            }
            return day_name;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
