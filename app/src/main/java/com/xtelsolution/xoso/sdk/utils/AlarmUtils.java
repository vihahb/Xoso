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

    public static void setAlarmManagerNorth(boolean isNorth, boolean less5HourToday) {
        if (isNorth) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 15);
            calendar.set(Calendar.MINUTE, 40);

            Intent notificationNorth = new Intent(context, AlarmReceiver.class);
            notificationNorth.putExtra(Constants.NOTIFICATION_ID, 1);
            notificationNorth.putExtra(Constants.AREA_NOTIFY, 1);
            PendingIntent northPendingIntent = PendingIntent.getBroadcast(context, 0, notificationNorth, PendingIntent.FLAG_UPDATE_CURRENT);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (less5HourToday) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), northPendingIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + Constants.MILIS_OF_24H, northPendingIntent);
            }
        }
//        alarmManager.set(AlarmManager.RTC_WAKEUP, );
    }

}
