package com.xproject.xoso.sdk.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xproject.xoso.sdk.utils.AlarmUtils;

import java.util.Calendar;

/**
 * Created by vivhp on 9/12/2017.
 */

public class SchedulingService extends Service {

    private BroadcastReceiver tickReceiver;
    private static final IntentFilter s_intentFilter;

    static {
        s_intentFilter = new IntentFilter();
        s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
    }

    private static final String TAG = "SchedulingService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AlarmUtils.setAlarm();
        registerReceiver(m_timeChangedReceiver, s_intentFilter);
    }


    private final BroadcastReceiver m_timeChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action != null && (action.equals(Intent.ACTION_TIME_CHANGED) ||
                    action.equals(Intent.ACTION_TIMEZONE_CHANGED))) {
                doWorkSon();
            }
        }
    };

    private void doWorkSon() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minutes = Calendar.getInstance().get(Calendar.MINUTE);
        Log.e(TAG, "Time change : " + hour + ":" + minutes);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: Destroy " + m_timeChangedReceiver);
        unregisterReceiver(tickReceiver);
    }
}
