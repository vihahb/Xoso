package com.xtelsolution.xoso.sdk.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
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
        /**
         * Notification North area*/
        AlarmUtils.getInstance().setAlarmManagerNorth(1);
        AlarmUtils.getInstance().setAlarmManagerNorth(2);
        AlarmUtils.getInstance().setAlarmManagerNorth(3);
//        if (TimeUtils.checkTimeInMilisecondNorth(14, 0, 15, 40)){
//
//        } else {
//            AlarmUtils.getInstance().setAlarmManagerNorth(true, 1);
//        }

        /**
         * Notification Central area*/
        if (TimeUtils.checkTimeInMilisecondNorth(15, 0, 15, 40)){

        }

        /**
         * Notification South area*/
        if (TimeUtils.checkTimeInMilisecondNorth(15, 0, 15, 40)){
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
