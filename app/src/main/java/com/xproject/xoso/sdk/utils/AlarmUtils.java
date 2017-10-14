package com.xproject.xoso.sdk.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.receiver.AlarmReceiver;
import com.xproject.xoso.sdk.service.SchedulingService;
import com.xproject.xoso.xoso.ProjectApplication;

import java.util.Calendar;

/**
 * Created by vivhp on 9/13/2017.
 */

public class AlarmUtils {

    private static final String TAG = "AlarmUtils";

    public static int NOTIFY_TYPE = 1;

    private static Context context = ProjectApplication.context;

    public static void startService(Context context, int type) {
        Intent intent = new Intent(context, SchedulingService.class);
        intent.putExtra(Constants.NOTIFY, type);
        context.startService(intent);
    }



    public static void setNorthAlarm() {

        long startTime;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() > System.currentTimeMillis()){
            startTime = calendar.getTimeInMillis();
        } else {
            startTime = calendar.getTimeInMillis()+ AlarmManager.INTERVAL_DAY;
        }

        Log.e(TAG, "setNorthAlarm: " + startTime);
        Log.e(TAG, "setNorthAlarm in format: " + TimeUtils.getHourseFromMilisecond(startTime));

        Intent notificationNorth = new Intent(context, AlarmReceiver.class);
        notificationNorth.putExtra(Constants.NOTIFICATION_ID, 1);
        PendingIntent northPendingIntent = PendingIntent.getBroadcast(context, 11, notificationNorth, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, northPendingIntent);
    }


    public static void setAlarmCentral() {
        long startTime;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() > System.currentTimeMillis()){
            startTime = calendar.getTimeInMillis();
        } else {
            startTime = calendar.getTimeInMillis()+ AlarmManager.INTERVAL_DAY;
        }
        Log.e(TAG, "setCentralAlarm: " + startTime);
        Log.e(TAG, "setCentralAlarm in format: " + TimeUtils.getHourseFromMilisecond(startTime));

        Intent notificationCentral = new Intent(context, AlarmReceiver.class);
        notificationCentral.putExtra(Constants.NOTIFICATION_ID, 2);
        PendingIntent centralPendingIntent = PendingIntent.getBroadcast(context, 22, notificationCentral, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, centralPendingIntent);

    }


    public static void setAlarmSouth() {
        long startTime;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() > System.currentTimeMillis()){
            startTime = calendar.getTimeInMillis();
        } else {
            startTime = calendar.getTimeInMillis()+ AlarmManager.INTERVAL_DAY;
        }

        Intent notificationSouth = new Intent(context, AlarmReceiver.class);
        notificationSouth.putExtra(Constants.NOTIFICATION_ID, 3);
        PendingIntent southPendingIntent = PendingIntent.getBroadcast(context, 33, notificationSouth, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, southPendingIntent);
    }


    public static void setNorthAlarmIntoSpin() {

        long startTime;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() > System.currentTimeMillis()){
            startTime = calendar.getTimeInMillis();
        } else {
            startTime = calendar.getTimeInMillis()+ AlarmManager.INTERVAL_DAY;
        }

        Log.e(TAG, "setNorthAlarm: " + startTime);
        Log.e(TAG, "setNorthAlarm in format: " + TimeUtils.getHourseFromMilisecond(startTime));

        Intent notificationNorth = new Intent(context, AlarmReceiver.class);
        notificationNorth.putExtra(Constants.NOTIFICATION_ID, 4);
        PendingIntent northPendingIntent = PendingIntent.getBroadcast(context, 101, notificationNorth, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, northPendingIntent);
    }


    public static void setAlarmCentralIntoSpin() {
        long startTime;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() > System.currentTimeMillis()){
            startTime = calendar.getTimeInMillis();
        } else {
            startTime = calendar.getTimeInMillis()+ AlarmManager.INTERVAL_DAY;
        }

        Log.e(TAG, "setCentralAlarmFix: " + startTime);
        Log.e(TAG, "setCentralAlarmFix in format: " + TimeUtils.getHourseFromMilisecond(startTime));

        Intent notificationNorth = new Intent(context, AlarmReceiver.class);
        notificationNorth.putExtra(Constants.NOTIFICATION_ID, 5);
        PendingIntent northPendingIntent = PendingIntent.getBroadcast(context, 102, notificationNorth, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, northPendingIntent);

    }

    public static void setAlarmSouthIntoSpin() {
        long startTime;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() > System.currentTimeMillis()){
            startTime = calendar.getTimeInMillis();
        } else {
            startTime = calendar.getTimeInMillis()+ AlarmManager.INTERVAL_DAY;
        }

        Log.e(TAG, "setSouthAlarmFix: " + startTime);
        Log.e(TAG, "setSouthAlarmFix in format: " + TimeUtils.getHourseFromMilisecond(startTime));

        Intent notificationNorth = new Intent(context, AlarmReceiver.class);
        notificationNorth.putExtra(Constants.NOTIFICATION_ID, 6);
        PendingIntent northPendingIntent = PendingIntent.getBroadcast(context, 103, notificationNorth, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, northPendingIntent);
    }


    public static void setAlarm() {
        setNorthAlarm();
        setAlarmCentral();
        setAlarmSouth();

        /**
         * Set Alarm spin*/
        setNorthAlarmIntoSpin();
        setAlarmCentralIntoSpin();
        setAlarmSouthIntoSpin();
    }
}
