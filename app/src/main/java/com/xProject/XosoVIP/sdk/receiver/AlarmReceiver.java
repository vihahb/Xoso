package com.xProject.XosoVIP.sdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.service.SocketService;
import com.xProject.XosoVIP.sdk.utils.AlarmUtils;
import com.xProject.XosoVIP.sdk.utils.Helper;
import com.xProject.XosoVIP.sdk.utils.MessageNotification;
import com.xProject.XosoVIP.sdk.utils.SharedUtils;
import com.xProject.XosoVIP.xoso.ProjectApplication;
import com.xProject.XosoVIP.xoso.view.activity.MainActivity;

/**
 * Created by vivhp on 9/13/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmReceiver";
    private boolean flag_n = true, flag_c = true, flag_s = true;
    private boolean notify = true;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "onReceive: " + "Da receive");

        int id_notify = 0;

        AlarmUtils.startService(context, AlarmUtils.NOTIFY_TYPE);
        Intent intentLive = new Intent(context, MainActivity.class);
        String content_notify = "";
        int notify_id_value = intent.getIntExtra(Constants.NOTIFICATION_ID, 1);

        if (notify_id_value == 1) {
            intentLive.putExtra(Constants.START_LIVE, 1);
            content_notify = "Sắp đến giờ quay giải miền Bắc";
            id_notify = 1;
        } else if (notify_id_value == 2) {
            intentLive.putExtra(Constants.START_LIVE, 2);
            content_notify = "Săp đến giờ quay giải miền Trung";
            id_notify = 2;
        } else if (notify_id_value == 3) {
            intentLive.putExtra(Constants.START_LIVE, 3);
            content_notify = "Sắp đến giờ quay giải miền Nam";
            id_notify = 3;
        } else if (notify_id_value == 4) {
            intentLive.putExtra(Constants.START_LIVE, 1);
            content_notify = "Đang quay giải miền Bắc";
            id_notify = 1;
        } else if (notify_id_value == 5) {
            intentLive.putExtra(Constants.START_LIVE, 2);
            content_notify = "Đang quay giải miền Trung";
            id_notify = 2;
        } else if (notify_id_value == 6) {
            intentLive.putExtra(Constants.START_LIVE, 3);
            content_notify = "Đang quay giải miền Nam";
            id_notify = 3;
        }

        if (Helper.isAppRunning(context, ProjectApplication.context.getPackageName())) {
            notify = false;
            if (id_notify > 0) {
                Intent live_intent = new Intent("ACTION_LIVE");
                live_intent.putExtra(Constants.ACTION_TYPE, id_notify);
                context.sendBroadcast(live_intent);
            }
        }

        /**
         * Set Alarm spin
         *
         * @deprecated Flag on method
         * flag = false -> set on alarm
         * flag = true -> set off alarm
         */
        flag_n = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.NOTIFY_N_FLAG);
        if (flag_n) {
            Log.e(TAG, "setAlarm: N - ON");
            MessageNotification.notify(context, content_notify, id_notify, intentLive);
        }


        flag_c = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.NOTIFY_C_FLAG);
        if (flag_c) {
            Log.e(TAG, "setAlarm: C - ON");
            MessageNotification.notify(context, content_notify, id_notify, intentLive);
        }

        flag_s = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.NOTIFY_S_FLAG);
        if (flag_s) {
            Log.e(TAG, "setAlarm: S - ON");
            MessageNotification.notify(context, content_notify, id_notify, intentLive);
        }



        int reset = intent.getIntExtra(Constants.RESET, 0);
        if (reset > 0){
            SharedUtils.getInstance().putBooleanValue(Constants.CHECK_DONE_N, false);
            SharedUtils.getInstance().putBooleanValue(Constants.CHECK_DONE_C, false);
            SharedUtils.getInstance().putBooleanValue(Constants.CHECK_DONE_S, false);
        }

//        PendingIntent pendingIntent = PendingIntent.getService(context, 99, intentLive, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//        builder.setContentTitle("Trực tiếp");
//        builder.setContentText(content_notify);
//        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
//        builder.setDefaults(Notification.DEFAULT_SOUND);
//        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setContentIntent(pendingIntent);
//
//        int notify_id = intent.getIntExtra(Constants.NOTIFICATION_ID, 0);
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(notify_id, builder.build());
    }
}
