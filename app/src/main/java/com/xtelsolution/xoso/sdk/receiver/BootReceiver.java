package com.xtelsolution.xoso.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.xtelsolution.xoso.sdk.service.SchedulingService;
import com.xtelsolution.xoso.sdk.utils.AlarmUtils;
import com.xtelsolution.xoso.sdk.utils.TimeUtils;

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
            Intent intent1 = new Intent(context, SchedulingService.class);
            context.startService(intent1);
        }
    }
}
