package com.xProject.XosoVIP.sdk.service;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.receiver.CheckRunningActivityReceive;
import com.xProject.XosoVIP.sdk.utils.Helper;
import com.xProject.XosoVIP.sdk.utils.JsonHelper;
import com.xProject.XosoVIP.xoso.ProjectApplication;
import com.xProject.XosoVIP.xoso.model.entity.Authent;
import com.xProject.XosoVIP.xoso.model.respond.RESP_NewResult;
import com.xProject.XosoVIP.xoso.view.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xtel on 11/3/17.
 */

public class SocketService extends IntentService {
    private static final String TAG = "SocketService";

    Socket mSocket;
    int action_type;
    private Context context = ProjectApplication.context;
    boolean in_activity = false;
    MainActivity mainActivity;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(TAG, "onHandleIntent: " + intent);
    }

    public SocketService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /**
         * Continues develop*/
        try {
            action_type = intent.getIntExtra(Constants.ACTION_TYPE, -1);
            IO.Options opts = new IO.Options();
            opts.forceNew = true;
            opts.reconnection = true;
            switch (action_type) {
                case 1:
                    mSocket = IO.socket("http://124.158.4.190:3000/mienbac", opts);
                    break;
                case 2:
                    mSocket = IO.socket("http://124.158.4.190:3000/mientrung", opts);
                    break;
                case 3:
                    mSocket = IO.socket("http://124.158.4.190:3000/miennam", opts);
                    break;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        listenSocket();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void listenSocket() {
        if (mSocket != null) {
            mSocket.connect();
            mSocket.on("connect_error", onConnectError);
            mSocket.on("connect_timeout", onConnectError);
            mSocket.on("connect", onConnect);
            mSocket.on("disconnect", onDisconnect);
            mSocket.on("authenticated", authenticate);
            mSocket.on("new_result", newResult);
        }
    }

    private void checkACtivity() {
        try {
            // Using ACTIVITY_SERVICE with getSystemService(String)
            // to retrieve a ActivityManager for interacting with the global system state.

            ActivityManager am = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);

            // Return a list of the tasks that are currently running,
            // with the most recent being first and older ones after in order.
            // Taken 1 inside getRunningTasks method means want to take only
            // top activity from stack and forgot the olders.

            List<ActivityManager.RunningTaskInfo> alltasks = null;
            if (am != null) {
                alltasks = am
                        .getRunningTasks(1);
            }

            //
            for (ActivityManager.RunningTaskInfo aTask : alltasks) {
                // Used to check for CURRENT example main screen

                String MainClass = MainActivity.class.getName();
                Log.e(TAG, "onReceive: " + MainClass);

                if (!Helper.isAppIsInBackground(context) && aTask.topActivity.getClassName().equals(MainClass)) {
                    if (Helper.isAppRunning(context, getPackageName())) {
                        Intent intent = new Intent("ACTION_CHANGE");
                        intent.putExtra(Constants.ACTION_TYPE, action_type);
                        sendBroadcast(intent);
                    }
                    in_activity = true;
                    Toast.makeText(context, "Current Result Screen.", Toast.LENGTH_LONG).show();
                    break;
                } else {
                    if (Helper.isAppRunning(context, getPackageName()) && !Helper.isAppIsInBackground(context)) {
                        Intent intent = new Intent(context, CheckRunningActivityReceive.class);
                        intent.putExtra(Constants.ACTION_TYPE, action_type);
                        sendBroadcast(intent);
                    }
                }
                // These are showing current running activity in logcat with
                // the use of different methods

                Log.i(TAG, "===============================");

                Log.i(TAG, "TASK.baseActivity: "
                        + aTask.baseActivity.flattenToShortString());

                Log.i(TAG, "TASK.baseActivity: "
                        + aTask.baseActivity.getClassName());

                Log.i(TAG, "TASK.topActivity: "
                        + aTask.topActivity.flattenToShortString());

                Log.i(TAG, "TASK.topActivity: "
                        + aTask.topActivity.getClassName());

                Log.i(TAG, "===============================");
                
            }
        } catch (Throwable t) {
            Log.i(TAG, "Throwable caught: "
                    + t.getMessage(), t);
        }
    }

    Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e(TAG, "onConnect: " + Arrays.toString(args));
            Authent data = Constants.getAuthent();
            JSONObject object = new JSONObject();
            try {
                object.put("t", data.getT());
                object.put("k", data.getK());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mSocket.emit("authenticate", object);
        }
    };

    Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e(TAG, "onConnectError: " + Arrays.toString(args));
        }
    };
    Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e(TAG, "onDisconnect: " + Arrays.toString(args));
        }
    };
    Emitter.Listener authenticate = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            mSocket.emit("get_current_result");
        }
    };

    private boolean alertNorth = false;
    private boolean alertCentral = false;
    private boolean alertSouth = false;
    Emitter.Listener newResult = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            RESP_NewResult newResult = JsonHelper.getObjectNoException(args[0].toString(), RESP_NewResult.class);
            Log.e(TAG, "call: new result " + newResult.toString());
            switch (action_type) {
                case 1:
                    if (newResult.getResult_name().equals("res_first")&& !alertNorth) {
                        alertNorth = true;
                        checkACtivity();
                    }
                    break;
                case 2:
                    if (newResult.getResult_name().equals("res_eight")&& !alertCentral) {
                        alertCentral = true;
                        checkACtivity();
                    }
                    break;
                case 3:
                    if (newResult.getResult_name().equals("res_eight") && !alertSouth) {
                        alertSouth = true;
                        checkACtivity();
                    }
                    break;
            }
        }
    };
}
