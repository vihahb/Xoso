package com.xProject.XosoVIP.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.xProject.XosoVIP.sdk.callback.DialogListener;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.xoso.view.activity.MainActivity;
import com.xProject.XosoVIP.xoso.view.widget.WindowAlarm;

public class CheckRunningActivityReceive extends BroadcastReceiver {

    public final String TAG = "RUNNING_ACTIVITY_TASK"; // CheckRunningApplicationReceiver

    @Override
    public void onReceive(final Context aContext, Intent anIntent) {
        int action_type = anIntent.getIntExtra(Constants.ACTION_TYPE, -1);
        WindowAlarm window = new WindowAlarm(aContext);
        window.setType(action_type);
        window.setActionListener(new DialogListener() {
            @Override
            public void negativeClicked() {
            }

            @Override
            public void positiveClicked() {
                Intent intent = new Intent(aContext, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                aContext.startActivity(intent);
            }
        });
        if (!window.isShowing())
            window.show();
    }

}
