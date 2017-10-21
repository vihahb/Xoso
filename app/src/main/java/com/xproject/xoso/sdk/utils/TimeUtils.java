package com.xproject.xoso.sdk.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;

import com.xproject.xoso.sdk.callback.DateTimePickerListener;
import com.xtelsolution.xoso.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
    public static final Calendar FIRST_DAY_OF_TIME;
    public static final Calendar LAST_DAY_OF_TIME;
    public static final int DAYS_OF_TIME;
    private static final String TAG = "TimeUtils";

    static {
        FIRST_DAY_OF_TIME = Calendar.getInstance();
        FIRST_DAY_OF_TIME.set(2001, Calendar.JANUARY, 1);
        LAST_DAY_OF_TIME = Calendar.getInstance();
        DAYS_OF_TIME = (int) ((LAST_DAY_OF_TIME.getTimeInMillis() - FIRST_DAY_OF_TIME.getTimeInMillis()) / (24 * 60 * 60 * 1000)) + 1; //73413;
//        Log.e(TAG, "static initializer Time Utils: " + LAST_DAY_OF_TIME.get(Calendar.YEAR) + " - " + LAST_DAY_OF_TIME.get(Calendar.MONTH) + " - " + LAST_DAY_OF_TIME.get(Calendar.DAY_OF_MONTH));
    }

//    public String getYesterday() {
//        final Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, -1);
//        return cal.getTime();
//    }

    /**
     * Get the position in the ViewPager for a given day
     *
     * @param day
     * @return the position or 0 if day is null
     */
    public static int getPositionForDay(Calendar day) {
        if (day != null) {
//            Log.e(TAG, "Day from position: " + getDayForPosition((int) ((day.getTimeInMillis() - FIRST_DAY_OF_TIME.getTimeInMillis())
//                    / 86400000  //(24 * 60 * 60 * 1000)
//            )));
            return (int) ((day.getTimeInMillis() - FIRST_DAY_OF_TIME.getTimeInMillis())
                    / 86400000  //(24 * 60 * 60 * 1000)
            );
        }
        return 0;
    }

    /**
     * Get the day for a given position in the ViewPager
     *
     * @param position
     * @return the day
     * @throws IllegalArgumentException if position is negative
     */
    public static Calendar getDayForPosition(int position) throws IllegalArgumentException {
        if (position < 0) {
            throw new IllegalArgumentException("position cannot be negative");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(FIRST_DAY_OF_TIME.getTimeInMillis());
        cal.add(Calendar.DAY_OF_YEAR, position);
        return cal;
    }


    public static String getFormattedDate(Context context, long date) {
        final String defaultPattern = "yyyy-MM-dd";

        String pattern = null;
        if (context != null) {
            pattern = context.getString(R.string.date_format);
        }
        if (pattern == null) {
            pattern = defaultPattern;
        }
        SimpleDateFormat simpleDateFormat = null;
        try {
            simpleDateFormat = new SimpleDateFormat(pattern);
        } catch (IllegalArgumentException e) {
            simpleDateFormat = new SimpleDateFormat(defaultPattern);
        }

        return simpleDateFormat.format(new Date(date));
    }

    public static String getTitleTime(Context context, Long milliseconds) {
        final String defaultPattern = "dd-MM-yyyy";

        String pattern = null;
        if (context != null) {
            pattern = context.getString(R.string.date_format_title);
        }
        if (pattern == null) {
            pattern = defaultPattern;
        }
        SimpleDateFormat simpleDateFormat = null;
        try {
            simpleDateFormat = new SimpleDateFormat(pattern);
        } catch (IllegalArgumentException e) {
            simpleDateFormat = new SimpleDateFormat(defaultPattern);
        }

        return simpleDateFormat.format(new Date(milliseconds));
    }

    public static String getFormatTimeClient(String date) {
        Date time = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            time = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if (time != null) {
            return format.format(time);
        } else {
            return null;
        }
    }

    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static String getDateFromCal(Calendar calendar) {
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static String getDateFromMilisecond(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        return dateFormat.format(date);
    }

    public static String getDateFromStringFomat(String date) {
        Date time = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(time);
    }

    public static String getHourseFromMilisecond(long time) {
        //New Date object from milisecond
        Date date = new Date(time);
        //Format Date
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        //Pass date object
        String formatedDate = format.format(date);
        Log.e(TAG, "init hour: " + formatedDate);
        return formatedDate;
    }

    public static boolean checkTimeInMilisecondNorth(int hour_begin, int minutes_begin, int hour_end, int minutes_end) {
        /**
         * Now Time*/
        long curentTimes = System.currentTimeMillis();

        /**
         * Begin Live Time*/
        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.set(Calendar.HOUR_OF_DAY, hour_begin);
        calendarBegin.set(Calendar.MINUTE, minutes_begin);
        long timeSetLive = calendarBegin.getTimeInMillis();

        /**
         * End Live Time*/
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(Calendar.HOUR_OF_DAY, hour_end);
        calendarEnd.set(Calendar.MINUTE, minutes_end);

        long timeEndLive = calendarEnd.getTimeInMillis();

        if (curentTimes >= timeSetLive && curentTimes <= timeEndLive) {
            return true;
        } else {
            return false;
        }

    }

    public static void showDateTimePickerDialog(final Context context, final DateTimePickerListener dateTimePickerListener, boolean large_than_today) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, final int year, final int month, final int day) {
                calendar.set(year, month, day);
                dateTimePickerListener.onDateTimePickerListener(year, month, day);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        if (!large_than_today) {
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        }
        datePickerDialog.show();
    }

    public static int subtractionDay(String time_start, String time_end) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long start_time = formater.parse(time_start).getTime();
            long end_time = formater.parse(time_end).getTime();
            long report_time = start_time - end_time;
            return ((Long) (report_time / (24 * 60 * 60 * 1000))).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static Calendar getCalendarFromString(String date_string) {
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = formatter.parse(date_string);
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            Log.e(TAG, "getCalendarFromString: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String convertTimeInMilisecondToDate(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        return dateFormat.format(date);
    }
}