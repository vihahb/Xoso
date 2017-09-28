package com.xtelsolution.xoso.sdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xtelsolution.xoso.sdk.utils.AlarmUtils;
import com.xtelsolution.xoso.sdk.utils.TimeUtils;

/**
 * Created by vivhp on 9/12/2017.
 */

public class SchedulingService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initAlarmBeforeSpin();
        initAlarmIntoSpin();
    }

    private void initAlarmBeforeSpin() {
        /**
         * Notification North Area
         */
        if (TimeUtils.checkTimeInMilisecondNorth(18, 0, 18, 15)) {
            AlarmUtils.setNorthAlarm(false);
        } else {
            AlarmUtils.setNorthAlarm(true);
        }


        /**
         * Notification Central area*/
        if (TimeUtils.checkTimeInMilisecondNorth(17, 0, 17, 15)) {
            AlarmUtils.setAlarmCentral(false);
        } else {
            AlarmUtils.setAlarmCentral(true);
        }

        /**
         * Notification South area*/
        if (TimeUtils.checkTimeInMilisecondNorth(16, 0, 16, 15)) {
            AlarmUtils.setAlarmSouth(false);
        } else {
            AlarmUtils.setAlarmSouth(true);
        }
    }

    private void initAlarmIntoSpin() {
/**
 * Notification North Area
 */
        if (TimeUtils.checkTimeInMilisecondNorth(18, 15, 18, 30)) {
            AlarmUtils.setNorthAlarmIntoSpin(false);
        } else {
            AlarmUtils.setNorthAlarmIntoSpin(true);
        }


        /**
         * Notification Central area*/
        if (TimeUtils.checkTimeInMilisecondNorth(17, 15, 17, 30)) {
            AlarmUtils.setAlarmCentralIntoSpin(false);
        } else {
            AlarmUtils.setAlarmCentralIntoSpin(true);
        }

        /**
         * Notification South area*/
        if (TimeUtils.checkTimeInMilisecondNorth(16, 15, 16, 30)) {
            AlarmUtils.setAlarmSouthIntoSpin(false);
        } else {
            AlarmUtils.setAlarmSouthIntoSpin(true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
