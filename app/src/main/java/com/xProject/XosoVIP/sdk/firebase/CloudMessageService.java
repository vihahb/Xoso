package com.xProject.XosoVIP.sdk.firebase;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.Helper;
import com.xProject.XosoVIP.sdk.utils.MessageNotification;
import com.xProject.XosoVIP.xoso.model.entity.DataPayload;
import com.xProject.XosoVIP.xoso.view.activity.MainActivity;

import static com.xProject.XosoVIP.xoso.ProjectApplication.context;

/**
 * Created by vivhp on 9/28/2017.
 */

public class CloudMessageService extends FirebaseMessagingService {

    private static final String TAG = "CloudMessageService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            DataPayload dataPayload = new DataPayload();
            dataPayload.setTitle(remoteMessage.getData().get("title").toString());
            dataPayload.setBody(remoteMessage.getData().get("body").toString());
            dataPayload.setAction(Integer.parseInt(remoteMessage.getData().get("action").toString()));
            Log.e(TAG, "onMessageReceived: " + dataPayload.toString());
            Intent intentLive = new Intent(context, MainActivity.class);
            String content_notify = "";
            int notify_id_value = dataPayload.getAction();
            int id_notify = 0;

            if (notify_id_value == 1) {
                intentLive.putExtra(Constants.START_LIVE, 1);
                content_notify = "Sắp đến giờ quay giải miền Bắc";
            } else if (notify_id_value == 2) {
                intentLive.putExtra(Constants.START_LIVE, 2);
                content_notify = "Săp đến giờ quay giải miền Trung";
            } else if (notify_id_value == 3) {
                intentLive.putExtra(Constants.START_LIVE, 3);
                content_notify = "Sắp đến giờ quay giải miền Nam";
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
            } else if (notify_id_value == 7) {
                intentLive.putExtra(Constants.START_LIVE, 1);
                content_notify = "Kết thúc quay giải miền Bắc";
                id_notify = 4;
            } else if (notify_id_value == 8) {
                intentLive.putExtra(Constants.START_LIVE, 2);
                content_notify = "Kết thúc quay giải miền Trung";
                id_notify = 5;
            } else if (notify_id_value == 9) {
                intentLive.putExtra(Constants.START_LIVE, 3);
                content_notify = "Kết thúc quay giải miền Nam";
                id_notify = 6;
            }


            MessageNotification.notify(context, content_notify, id_notify, intentLive);

            if (Helper.isAppRunning(context, "com.xtelsolution.xoso")) {
                if (id_notify > 0) {
                    Intent live_intent = new Intent("ACTION_LIVE");
                    live_intent.putExtra(Constants.ACTION_TYPE, id_notify);
                    context.sendBroadcast(live_intent);
                }
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
