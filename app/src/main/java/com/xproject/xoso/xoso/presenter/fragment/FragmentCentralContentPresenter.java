package com.xproject.xoso.xoso.presenter.fragment;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.MainModel;
import com.xproject.xoso.xoso.model.entity.Authent;
import com.xproject.xoso.xoso.model.respond.RESP_GetLottery;
import com.xproject.xoso.xoso.model.respond.RESP_LiveLoto;
import com.xproject.xoso.xoso.model.respond.RESP_Result;
import com.xproject.xoso.sdk.utils.JsonHelper;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.respond.RESP_NewResult;
import com.xproject.xoso.xoso.view.fragment.inf.IFragmentCentralContent;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * Created by vivhp on 9/12/2017.
 */

public class FragmentCentralContentPresenter {

    private Socket socket;

    private String cat_area_1, cat_area_2, cat_area_3;
    private static final String SERVER_ADDRESS = "http://124.158.4.190:3000/";
    {
        try {
            IO.Options opts = new IO.Options();
            opts.forceNew = true;
            opts.reconnection = true;
            socket = IO.socket("http://124.158.4.190:3000/mientrung", opts);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String TAG = "FragmentCentralContentP";

    private IFragmentCentralContent view;
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int) params[0]) {
                case 1:
                    String date_time = (String) params[1];
                    MainModel.getInstance().getLotteryCentral(date_time, new ResponseHandle<RESP_GetLottery>(RESP_GetLottery.class) {
                        @Override
                        public void onSuccess(RESP_GetLottery obj) {
                            view.setVisibleTable(true);
                            Log.e(TAG, "onSuccess: obj " + obj.toString());
                            switch (obj.getData().size()) {
                                case 1:
                                    view.setTable2Hidden();
                                    view.setTable3Hidden();
                                    view.setTableRegion1(obj.getData().get(0));
                                    break;
                                case 2:
                                    view.setTable3Hidden();
                                    view.setTableRegion1(obj.getData().get(0));
                                    view.setTableRegion2(obj.getData().get(1));
                                    break;
                                case 3:
                                    view.setTableRegion1(obj.getData().get(0));
                                    view.setTableRegion2(obj.getData().get(1));
                                    view.setTableRegion3(obj.getData().get(2));
                                    break;
                            }
                        }

                        @Override
                        public void onError(Error error) {
                            view.getResultLotteryError(error.getMessage());
                            view.setVisibleTable(false);
                        }
                    });
                    break;

                case 2:
                    if (view.getActivity()!=null){
                        view.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                socket.connect();
                                socket.on(Socket.EVENT_CONNECT, onConnect);
                                socket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
                                socket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
                                socket.on(Socket.EVENT_DISCONNECT, onDisconnect);
                            }
                        });
                    }
                    break;
            }
        }
    };

    public FragmentCentralContentPresenter(final IFragmentCentralContent view) {
        this.view = view;

    }

    public void getResultLottery(String date) {
        icmd.excute(1, date);
    }


    public void connectSocket() {
        icmd.excute(2);
//        if (NetworkUtils.getInstance().isOnline(view.getActivity().getApplicationContext())){
//        } else {
//            view.getResultLotteryError(view.getActivity().getString(R.string.err_network));
//        }
    }

    public void checkSocket() {
        if (socket.connected()) {
            socket.disconnect();
        }
    }

    public void disconnectSocket() {
        if (socket!=null) {
           checkSocket();
        }
    }

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
        public void call(Object... args) {
            if (view.getActivity() != null) {
                view.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "Socket Connected!");
                        Log.e(TAG, "connect success: " + socket.connected());
                        initAuthentSocket();

                        /**
                         * BEGIN - Listen current_result */
                        socket.on("current_result", new Emitter.Listener() {
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
                                            }
                                            view.setDataSocket(resp_result);
                                        }
                                    });
                                }
                            }
                        });
                        /**
                         * END - Listen current_result*/


                        /**
                         * BEGIN - Listen new_result*/
                        socket.on("new_result", new Emitter.Listener() {
                            @Override
                            public void call(final Object... args) {
                                if (view.getActivity() != null) {
                                    view.getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            RESP_NewResult newResult = JsonHelper.getObjectNoException(args[0].toString(), RESP_NewResult.class);
                                            Log.e(TAG, "new_result: " + Arrays.toString(args));
                                            if (newResult.getCat_id().equals(cat_area_1)) {
                                                Log.e(TAG, "run: cat id 1 " + cat_area_1);
                                                view.setNewResult(newResult, 1);
                                            } else if (newResult.getCat_id().equals(cat_area_2)) {
                                                view.setNewResult(newResult, 2);
                                                Log.e(TAG, "run: cat id 2 " + cat_area_2);
                                            } else if (newResult.getCat_id().equals(cat_area_3)) {
                                                Log.e(TAG, "run: cat id 3 " + cat_area_3);
                                                view.setNewResult(newResult, 3);
                                            }
                                        }
                                    });
                                }
                            }
                        });
                        /**
                         * END - Listen new_result*/

                        socket.on("liveloto", new Emitter.Listener() {
                            @Override
                            public void call(final Object... args) {
                                if (view.getActivity() != null) {
                                    view.getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            RESP_LiveLoto liveLoto = JsonHelper.getObjectNoException(args[0].toString(), RESP_LiveLoto.class);
                                            Log.e(TAG, "new_result: " + Arrays.toString(args));
                                            if (liveLoto.getCat_id().equals(cat_area_1)) {
                                                Log.e(TAG, "run: cat id 1 " + cat_area_1);
                                                view.setLiveLoto(liveLoto, 1);
                                            } else if (liveLoto.getCat_id().equals(cat_area_2)) {
                                                view.setLiveLoto(liveLoto, 2);
                                                Log.e(TAG, "run: cat id 2 " + cat_area_2);
                                            } else if (liveLoto.getCat_id().equals(cat_area_3)) {
                                                Log.e(TAG, "run: cat id 3 " + cat_area_3);
                                                view.setLiveLoto(liveLoto, 3);
                                            }

                                        }
                                    });
                                }
                            }
                        });

                        socket.on("done", new Emitter.Listener() {
                            @Override
                            public void call(Object... args) {
                                if (view.getActivity() != null) {
                                    view.getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            view.setEndLive();
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        }
    };

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
        socket.on("authenticated", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                if (view.getActivity() != null){
                    view.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            socket.emit("get_current_result");
                        }
                    });
                }
            }
        });
    }
}
