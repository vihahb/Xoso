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

    public static void setAlarmManagerNorth(int area_type) {

        if (area_type == 1) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 14);
            calendar.set(Calendar.MINUTE, 0);

            Intent notificationNorth = new Intent(context, AlarmReceiver.class);
            notificationNorth.putExtra(Constants.NOTIFICATION_ID, 1);
            notificationNorth.putExtra(Constants.AREA_NOTIFY, area_type);
            PendingIntent northPendingIntent = PendingIntent.getBroadcast(context, 0, notificationNorth, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, northPendingIntent);
        }

        if (area_type == 2){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 14);
            calendar.set(Calendar.MINUTE, 3);

            Intent notificationNorth = new Intent(context, AlarmReceiver.class);
            notificationNorth.putExtra(Constants.NOTIFICATION_ID, 2);
            notificationNorth.putExtra(Constants.AREA_NOTIFY, area_type);
            PendingIntent northPendingIntent = PendingIntent.getBroadcast(context, 0, notificationNorth, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, northPendingIntent);
        }

        if (area_type == 3){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 14);
            calendar.set(Calendar.MINUTE, 5);

            Intent notificationNorth = new Intent(context, AlarmReceiver.class);
            notificationNorth.putExtra(Constants.NOTIFICATION_ID, 3);
            notificationNorth.putExtra(Constants.AREA_NOTIFY, area_type);
            PendingIntent northPendingIntent = PendingIntent.getBroadcast(context, 0, notificationNorth, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, northPendingIntent);
        }
    }

}
