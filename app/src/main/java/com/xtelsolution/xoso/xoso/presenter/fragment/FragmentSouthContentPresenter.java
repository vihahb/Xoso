package com.xtelsolution.xoso.xoso.presenter.fragment;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.xtelsolution.xoso.sdk.callback.Icmd;
import com.xtelsolution.xoso.sdk.utils.JsonHelper;
import com.xtelsolution.xoso.sdk.utils.ResponseHandle;
import com.xtelsolution.xoso.xoso.model.MainModel;
import com.xtelsolution.xoso.xoso.model.entity.Error;
import com.xtelsolution.xoso.xoso.model.respond.RESP_GetLottery;
import com.xtelsolution.xoso.xoso.model.respond.RESP_NewResult;
import com.xtelsolution.xoso.xoso.model.respond.RESP_Result;
import com.xtelsolution.xoso.xoso.view.fragment.inf.IFragmentSouthContent;

import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * Created by vivhp on 9/13/2017.
 */

public class FragmentSouthContentPresenter {

    private Socket socket;

    {
        try {
            socket = IO.socket("http://124.158.4.190:3000/miennam");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e(TAG, "URISyntaxException: " + e.toString());
        }
    }

    private String cat_area_1, cat_area_2, cat_area_3;
    private static final String TAG = "FragmentSouthContentP";

    private IFragmentSouthContent view;
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
                                    view.setTable4Hidden();
                                    view.setTableRegion1(obj.getData().get(0));
                                    view.setTableRegion2(obj.getData().get(1));
                                    view.setTableRegion3(obj.getData().get(2));
                                    view.setTableRegion4(obj.getData().get(3));
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
                    socket.connect();
                    socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            Log.e(TAG, "connect success: " + Arrays.toString(args));
                            socket.emit("get_current_result");
                        }
                    });
                    break;
            }
        }
    };

    public FragmentSouthContentPresenter(IFragmentSouthContent view) {
        this.view = view;
    }

    public void getResultLottery(String date) {
        icmd.excute(1, date);
    }

    public void connectSocket() {
        icmd.excute(2);
        listenSocket();
    }

    public void listenSocket() {
        socket.on("current_result", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                if (view.getActivity() != null) {
                    view.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "get_current_result: " + args[0].toString());
                            RESP_Result resp_result = JsonHelper.getObjectNoException(args[0].toString(), RESP_Result.class);
                            Log.e(TAG, "Helper: " + JsonHelper.getObjectNoException(resp_result.toString(), RESP_Result.class));
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

        socket.on("new_result", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {

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
                        }
                    }
                });
            }
        });
    }

    public void disconnectSocket() {
        socket.disconnect();
    }

}
