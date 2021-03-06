package com.xProject.XosoVIP.xoso.presenter.fragment;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.xProject.XosoVIP.R;
import com.xProject.XosoVIP.sdk.callback.Icmd;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.JsonHelper;
import com.xProject.XosoVIP.sdk.utils.ResponseHandle;
import com.xProject.XosoVIP.xoso.model.MainModel;
import com.xProject.XosoVIP.xoso.model.entity.Authent;
import com.xProject.XosoVIP.xoso.model.entity.Error;
import com.xProject.XosoVIP.xoso.model.respond.RESP_GetLottery;
import com.xProject.XosoVIP.xoso.model.respond.RESP_LiveLoto;
import com.xProject.XosoVIP.xoso.model.respond.RESP_NewResult;
import com.xProject.XosoVIP.xoso.model.respond.RESP_Result;
import com.xProject.XosoVIP.xoso.view.fragment.inf.IFragmentNorthContentView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * Created by vivhp on 9/8/2017
 */

public class FragmentNorthContentPresenter {
    private static final String TAG = "FragmentNorthContentPre";
    private IFragmentNorthContentView view;
    private Socket socket;
    private Emitter.Listener curentResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (view.getActivity() != null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (args[0] != null) {
                            Log.e(TAG, "get_current_result: " + args[0].toString());
                            RESP_Result resp_result = JsonHelper.getObjectNoException(args[0].toString(), RESP_Result.class);
                            Log.e(TAG, "Helper: " + resp_result.toString());
                            view.setDataSocket(resp_result.getData().get(0));
                        }
                    }
                });
            }
        }
    };

    private Emitter.Listener newResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (view.getActivity() != null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RESP_NewResult newResult = JsonHelper.getObjectNoException(args[0].toString(), RESP_NewResult.class);
                        Log.e(TAG, "call: new result " + newResult.toString());
                        view.setNewResult(newResult);
                    }
                });
            }
        }
    };

    private Emitter.Listener liveLoto = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (view.getActivity() != null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RESP_LiveLoto liveLoto = JsonHelper.getObjectNoException(args[0].toString(), RESP_LiveLoto.class);
                        Log.e(TAG, "call: liveloto " + liveLoto.toString());
                        view.setLiveLoto(liveLoto);
                    }
                });
            }
        }
    };

    private Emitter.Listener doneLive = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (view.getActivity() != null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "call: done " + args.toString());
                        view.setEndLive();
                    }
                });
            }
        }
    };

    private Emitter.Listener authenticate = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            if (view.getActivity() != null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        socket.emit("get_current_result");
                    }
                });
            }
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e(TAG, "Socket connect error");
        }
    };
    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "onDisconnect");
        }
    };
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (view.getActivity() != null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "connect success: " + Arrays.toString(args));
                        initAuthentSocket();
                    }
                });
            }
        }
    };


    private Icmd icmd = new Icmd() {
        @Override
        public void excute(final Object... params) {
            switch ((int) params[0]) {
                case 1:
                    String date_time = (String) params[1];
                    MainModel.getInstance().getLottery(date_time, new ResponseHandle<RESP_GetLottery>(RESP_GetLottery.class) {
                        @Override
                        public void onSuccess(final RESP_GetLottery obj) {
                            Log.e(TAG, "onSuccess: obj " + JsonHelper.toJson(obj));
                            view.getResultLotterySuccess(obj.getData().get(0));
                        }

                        @Override
                        public void onError(Error error) {
                            view.getResultLotteryError(error.getMessage());
                        }
                    });
                    break;
                case 2:
                    boolean toDay = (boolean) params[1];
                    if (toDay) {
                        if (socket != null) {
                            if (!socket.connected()) {
                                socket.open().connect();
                            }
                            socket.on(Socket.EVENT_CONNECT, onConnect);
                            socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
                            socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
                            socket.on(Socket.EVENT_DISCONNECT, onDisconnect);
                            socket.on("authenticated", authenticate);
                            socket.on("current_result", curentResult);
                            socket.on("new_result", newResult);
                            socket.on("liveloto", liveLoto);
                            socket.on("done", doneLive);
                        }
                    }
                    break;
            }
        }
    };


    public FragmentNorthContentPresenter(IFragmentNorthContentView view) {
        this.view = view;
        {
            try {
                IO.Options opts = new IO.Options();
                opts.reconnection = true;
                socket = IO.socket("http://124.158.4.190:3000/mienbac", opts);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                Log.e(TAG, "URISyntaxException: " + e.toString());
            }
        }
    }

    public void getResultLottery(String date) {
        icmd.excute(1, date);
    }

    public void socketConnect(boolean toDay) {
        icmd.excute(2, toDay);
    }

    public void initAuthentSocket() {
        Authent data = Constants.getAuthent();
        JSONObject object = new JSONObject();
        try {
            object.put("t", data.getT());
            object.put("k", data.getK());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        socket.emit("authenticate", object);
    }

    public void disconnectSocket() {
        if (socket != null) {
            checkSocket();
        }
    }

    public void checkSocket() {
        if (socket.connected()) {
            socket.close().disconnect();
        }
    }
}
