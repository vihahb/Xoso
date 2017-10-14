package com.xproject.xoso.sdk.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.SharedUtils;

/**
 * Created by vivhp on 9/28/2017.
 */

public class InstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "InstanceIdService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        SharedUtils.getInstance().putStringValue(Constants.FCM_TOKEN, refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {

    }
}
