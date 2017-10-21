package com.xproject.xoso.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.xproject.xoso.sdk.utils.AlarmUtils;

/**
 * Created by vivhp on 9/25/2017.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Toast.makeText(context, "Boot complete Done", Toast.LENGTH_SHORT).show();
            /**
             * Setting the alarm here
             * */
            AlarmUtils.startService(context, AlarmUtils.NOTIFY_TYPE);
        }
    }
}
