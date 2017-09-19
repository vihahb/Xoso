package com.xtelsolution.xoso.xoso.presenter.fragment;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;
import com.xtelsolution.xoso.sdk.callback.Icmd;
import com.xtelsolution.xoso.sdk.common.SocketSingleton;
import com.xtelsolution.xoso.sdk.utils.JsonHelper;
import com.xtelsolution.xoso.sdk.utils.ResponseHandle;
import com.xtelsolution.xoso.xoso.model.MainModel;
import com.xtelsolution.xoso.xoso.model.entity.Error;
import com.xtelsolution.xoso.xoso.model.respond.RESP_GetLottery;
import com.xtelsolution.xoso.xoso.model.respond.RESP_NewResult;
import com.xtelsolution.xoso.xoso.model.respond.RESP_Result;
import com.xtelsolution.xoso.xoso.view.fragment.inf.IFragmentCentralContent;

import java.util.Arrays;

/**
 * Created by vivhp on 9/12/2017.
 */

public class FragmentCentralContentPresenter {

//    private Socket socket;
//
//    {
//        try {
//            IO.Options opts = new IO.Options();
//            opts.forceNew = true;
//            opts.reconnection = true;
//            socket = IO.socket("http://124.158.4.190:3000/mientrung", opts);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//            Log.e(TAG, "URISyntaxException: " + e.toString());
//        }
//    }

    private SocketSingleton socketSingleton;

    private String cat_area_1, cat_area_2, cat_area_3;

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
                    socketSingleton.getSocket().connect();
                    socketSingleton.getSocket().on(Socket.EVENT_CONNECT, onConnect);
                    break;
            }
        }
    };

    public FragmentCentralContentPresenter(final IFragmentCentralContent view) {
        this.view = view;
        socketSingleton = new SocketSingleton(view.getActivity(), "mientrung");
        socketSingleton.getSocket().on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        socketSingleton.getSocket().on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        socketSingleton.getSocket().on(Socket.EVENT_DISCONNECT, onDisconnect);

    }

    public void getResultLottery(String date) {
        icmd.excute(1, date);
    }


    public void connectSocket() {
        icmd.excute(2);
    }

    public void checkSocket() {
        if (socketSingleton.getSocket().connected()) {
            socketSingleton.getSocket().disconnect();
        }
    }

    public void disconnectSocket() {
        socketSingleton.getSocket().disconnect();
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
                        Log.e(TAG, "connect success: " + socketSingleton.getSocket().connected());
                        socketSingleton.getSocket().emit("get_current_result");

                        /**
                         * BEGIN - Listen current_result */
                        socketSingleton.getSocket().on("current_result", new Emitter.Listener() {
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
                        socketSingleton.getSocket().on("new_result", new Emitter.Listener() {
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
                    }
                });
            }
        }
    };
}
