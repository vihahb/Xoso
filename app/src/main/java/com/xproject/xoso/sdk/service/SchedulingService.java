package com.xproject.xoso.sdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.AlarmUtils;
import com.xproject.xoso.sdk.utils.TimeUtils;

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
        AlarmUtils.setAlarm();
    }

    private void checkType(int type) {
        switch (type){
            case 1:
                break;
        }
    }

    private void initAlarmBeforeSpin() {
        /**
         * Notification North Area
         */
        AlarmUtils.setNorthAlarm();

        /**
         * Notification Central area*/

        AlarmUtils.setAlarmCentral();

        /**
         * Notification South area*/
        AlarmUtils.setAlarmSouth();
    }

//    private void initAlarmIntoSpin() {
///**
// * Notification North Area
// */
//        AlarmUtils.setNorthAlarmIntoSpin();
//
//        /**
//         * Notification Central area*/
//        AlarmUtils.setAlarmCentralIntoSpin();
//
//        /**
//         * Notification South area*/
//        AlarmUtils.setAlarmSouthIntoSpin();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
