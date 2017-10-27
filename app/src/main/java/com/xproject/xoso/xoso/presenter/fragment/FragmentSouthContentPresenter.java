package com.xproject.xoso.xoso.presenter.fragment;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.JsonHelper;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.MainModel;
import com.xproject.xoso.xoso.model.entity.Authent;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.respond.RESP_GetLottery;
import com.xproject.xoso.xoso.model.respond.RESP_LiveLoto;
import com.xproject.xoso.xoso.model.respond.RESP_NewResult;
import com.xproject.xoso.xoso.model.respond.RESP_Result;
import com.xproject.xoso.xoso.view.fragment.inf.IFragmentSouthContent;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * Created by vivhp on 9/13/2017.
 */

public class FragmentSouthContentPresenter {

    private static final String TAG = "FragmentSouthContentP";
    private static final String SERVER_ADDRESS = "";
    private Socket socket;
    private String cat_area_1, cat_area_2, cat_area_3, cat_area_4;
    private IFragmentSouthContent view;
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
    private Emitter.Listener currentResult = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (view.getActivity() != null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "get_current_result: " + args[0].toString());
                        RESP_Result resp_result = JsonHelper.getObjectNoException(args[0].toString(), RESP_Result.class);
                        Log.e(TAG, "Helper: " + resp_result.toString());
                        switch (resp_result.getData().size()) {
                            case 1:
                                view.random(1);
                                cat_area_1 = String.valueOf(resp_result.getData().get(0).getCat_id());
                                break;
                            case 2:
                                view.random(2);
                                cat_area_1 = String.valueOf(resp_result.getData().get(0).getCat_id());
                                cat_area_2 = String.valueOf(resp_result.getData().get(1).getCat_id());
                                break;
                            case 3:
                                view.random(3);
                                cat_area_1 = String.valueOf(resp_result.getData().get(0).getCat_id());
                                cat_area_2 = String.valueOf(resp_result.getData().get(1).getCat_id());
                                cat_area_3 = String.valueOf(resp_result.getData().get(2).getCat_id());
                                break;
                            case 4:
                                view.random(4);
                                cat_area_1 = String.valueOf(resp_result.getData().get(0).getCat_id());
                                cat_area_2 = String.valueOf(resp_result.getData().get(1).getCat_id());
                                cat_area_3 = String.valueOf(resp_result.getData().get(2).getCat_id());
                                cat_area_4 = String.valueOf(resp_result.getData().get(3).getCat_id());
                                break;
                        }
                        view.setDataSocket(resp_result);
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
                        Log.e(TAG, "new_result: " + Arrays.toString(args));
                        if (newResult.getCat_id().equals(cat_area_1)) {
                            view.setNewResult(newResult, 1);
                        } else if (newResult.getCat_id().equals(cat_area_2)) {
                            view.setNewResult(newResult, 2);
                        } else if (newResult.getCat_id().equals(cat_area_3)) {
                            view.setNewResult(newResult, 3);
                        } else if (newResult.getCat_id().equals(cat_area_4)) {
                            view.setNewResult(newResult, 4);
                        }
                    }
                });
            }
        }
    };
    private Emitter.Listener done = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            if (view.getActivity() != null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        socket.emit("get_current_result");
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
    private Emitter.Listener liveLoto = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (view.getActivity() != null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RESP_LiveLoto liveLoto = JsonHelper.getObjectNoException(args[0].toString(), RESP_LiveLoto.class);
                        Log.e(TAG, "call: liveloto " + liveLoto.toString());
                        if (liveLoto.getCat_id().equals(cat_area_1)) {
                            view.setLiveLoto(liveLoto, 1);
                        } else if (liveLoto.getCat_id().equals(cat_area_2)) {
                            view.setLiveLoto(liveLoto, 2);
                        } else if (liveLoto.getCat_id().equals(cat_area_3)) {
                            view.setLiveLoto(liveLoto, 3);
                        } else if (liveLoto.getCat_id().equals(cat_area_4)) {
                            view.setLiveLoto(liveLoto, 4);
                        }
                    }
                });
            }
        }
    };
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int) params[0]) {
                case 1:
                    String date_time = (String) params[1];
                    MainModel.getInstance().getLotterySouth(date_time, new ResponseHandle<RESP_GetLottery>(RESP_GetLottery.class) {
                        @Override
                        public void onSuccess(RESP_GetLottery obj) {
                            view.setVisibleTable(true);
                            Log.e(TAG, "onSuccess: obj " + obj.toString());
                            switch (obj.getData().size()) {
                                case 1:
                                    view.setTable2Hidden();
                                    view.setTable3Hidden();
                                    view.setTable4Hidden();
                                    view.setTableRegion1(obj.getData().get(0));
                                    break;
                                case 2:
                                    view.setTable3Hidden();
                                    view.setTable4Hidden();
                                    view.setTableRegion1(obj.getData().get(0));
                                    view.setTableRegion2(obj.getData().get(1));
                                    break;
                                case 3:
                                    view.setTable4Hidden();
                                    view.setTableRegion1(obj.getData().get(0));
                                    view.setTableRegion2(obj.getData().get(1));
                                    view.setTableRegion3(obj.getData().get(2));
                                    break;
                                case 4:
                                    view.setTableRegion1(obj.getData().get(0));
                                    view.setTableRegion2(obj.getData().get(1));
                                    view.setTableRegion3(obj.getData().get(2));
                                    view.setTableRegion4(obj.getData().get(3));
                                    break;
                            }
                        }

                        @Override
                        public void onError(Error error) {
                            if (error.getMessage() != null) {
                                view.getResultLotteryError(error.getMessage());
                            }
                            view.setVisibleTable(false);
                        }
                    });
                    break;

                case 2:
                    if (view.getActivity() != null) {
                        view.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (socket != null) {
                                    if (!socket.connected()) {
                                        socket.connect();
                                    }

                                    socket.on(Socket.EVENT_CONNECT, onConnect);
                                    socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
                                    socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
                                    socket.on(Socket.EVENT_DISCONNECT, onDisconnect);
                                    socket.on("authenticated", authenticate);
                                    socket.on("current_result", currentResult);
                                    socket.on("new_result", newResult);
                                    socket.on("liveloto", liveLoto);
                                    socket.on("done", done);
                                }
                            }
                        });
                    }
                    break;
            }
        }
    };

    public FragmentSouthContentPresenter(IFragmentSouthContent view) {
        this.view = view;
        {
            try {
                IO.Options opts = new IO.Options();
                opts.reconnection = true;
                socket = IO.socket("http://124.158.4.190:3000/miennam", opts);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void getResultLottery(String date) {
        icmd.excute(1, date);
    }

    public void connectSocket() {
        icmd.excute(2);
    }

    public void checkSocket() {
        if (socket.connected()) {
            socket.disconnect();
        }
    }

    public void disconnectSocket() {
        if (socket != null) {
            checkSocket();
        }
    }

    private void initAuthentSocket() {
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

    public void listenSocketEnvent() {
        icmd.excute(3);
    }
}
