package com.xtelsolution.xoso.sdk.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.xtelsolution.xoso.sdk.common.Constants;
import com.xtelsolution.xoso.sdk.receiver.AlarmReceiver;
import com.xtelsolution.xoso.xoso.ProjectApplication;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by vivhp on 9/13/2017.
 */

public class AlarmUtils {

    private static AlarmUtils ourInstance;
    private static Context context = ProjectApplication.context;

    public static AlarmUtils getInstance() {
        if (ourInstance == null) {
            ourInstance = new AlarmUtils();
        }
        return ourInstance;
    }


    /**
     * @param flag: true - overtime
     *              false - not yet
     *              */
    public static void setNorthAlarm(boolean flag) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 8);
        calendar.set(Calendar.SECOND, 0);

        Intent notificationNorth = new Intent(context, AlarmReceiver.class);
        notificationNorth.putExtra(Constants.NOTIFICATION_ID, 1);
        PendingIntent northPendingIntent = PendingIntent.getBroadcast(context, 0, notificationNorth, PendingIntent.FLAG_UPDATE_CURRENT);

        if (!flag) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), northPendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY, northPendingIntent);
        }
    }

    /**
     * @param flag: true - overtime
     *              false - not yet
     *              */
    public static void setAlarmCentral(boolean flag) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 8);
        calendar.set(Calendar.SECOND, 0);

        Intent notificationCentral = new Intent(context, AlarmReceiver.class);
        notificationCentral.putExtra(Constants.NOTIFICATION_ID, 2);
        PendingIntent centralPendingIntent = PendingIntent.getBroadcast(context, 0, notificationCentral, PendingIntent.FLAG_UPDATE_CURRENT);

        if (!flag) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), centralPendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY, centralPendingIntent);
        }
    }

    /**
     * @param flag: true - overtime
     *              false - not yet
     *              */
    public static void setAlarmSouth(boolean flag) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 8);
        calendar.set(Calendar.SECOND, 0);

        Intent notificationSouth = new Intent(context, AlarmReceiver.class);
        notificationSouth.putExtra(Constants.NOTIFICATION_ID, 3);
        PendingIntent southPendingIntent = PendingIntent.getBroadcast(context, 0, notificationSouth, PendingIntent.FLAG_UPDATE_CURRENT);

        if (!flag) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), southPendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY, southPendingIntent);
        }
    }








    /**
     * @param flag: true - overtime
     *              false - not yet
     *              */
    public static void setNorthAlarmIntoSpin(boolean flag) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);

        Intent notificationNorth = new Intent(context, AlarmReceiver.class);
        notificationNorth.putExtra(Constants.NOTIFICATION_ID, 4);
        PendingIntent northPendingIntent = PendingIntent.getBroadcast(context, 0, notificationNorth, PendingIntent.FLAG_UPDATE_CURRENT);

        if (!flag) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), northPendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY, northPendingIntent);
        }
    }

    /**
     * @param flag: true - overtime
     *              false - not yet
     *              */
    public static void setAlarmCentralIntoSpin(boolean flag) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);

        Intent notificationCentral = new Intent(context, AlarmReceiver.class);
        notificationCentral.putExtra(Constants.NOTIFICATION_ID, 5);
        PendingIntent centralPendingIntent = PendingIntent.getBroadcast(context, 0, notificationCentral, PendingIntent.FLAG_UPDATE_CURRENT);

        if (!flag) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), centralPendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY, centralPendingIntent);
        }
    }

    /**
     * @param flag: true - overtime
     *              false - not yet
     *              */
    public static void setAlarmSouthIntoSpin(boolean flag) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);

        Intent notificationSouth = new Intent(context, AlarmReceiver.class);
        notificationSouth.putExtra(Constants.NOTIFICATION_ID, 6);
        PendingIntent southPendingIntent = PendingIntent.getBroadcast(context, 0, notificationSouth, PendingIntent.FLAG_UPDATE_CURRENT);

        if (!flag) {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), southPendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + AlarmManager.INTERVAL_DAY, southPendingIntent);
        }
    }


}
