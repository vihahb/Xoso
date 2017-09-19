package com.xtelsolution.xoso.sdk.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.sdk.common.Constants;
import com.xtelsolution.xoso.xoso.view.activity.MainActivity;

/**
 * Created by vivhp on 9/13/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        Intent intentLive = new Intent(context, MainActivity.class);
        String content_notify ="";
        switch (intent.getIntExtra(Constants.AREA_NOTIFY, 1)){
            case 1:
                intentLive.putExtra(Constants.START_LIVE, 1);
                content_notify = "Đã đến giờ quay giải miền Bắc";
                break;
            case 2:
                intentLive.putExtra(Constants.START_LIVE, 2);
                content_notify = "Đã đến giờ quay giải miền Trung";
                break;
            case 3:
                intentLive.putExtra(Constants.START_LIVE, 3);
                content_notify = "Đã đến giờ quay giải miền Nam";
                break;
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentLive, PendingIntent.FLAG_UPDATE_CURRENT);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("Trực tiếp");
        builder.setContentText(content_notify);
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setSmallIcon(R.mipmap.ic_nav_north);
        builder.setContentIntent(pendingIntent);

        int notify_id = intent.getIntExtra(Constants.NOTIFICATION_ID, 0);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notify_id, builder.build());
    }
}