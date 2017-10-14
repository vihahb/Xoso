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
import com.xproject.xoso.xoso.view.fragment.inf.IFragmentNorthContentView;

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
    {
        try {
            socket = IO.socket("http://124.158.4.190:3000/mienbac");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e(TAG, "URISyntaxException: " + e.toString());
        }
    }

    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
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
            }
        }
    };

    public FragmentNorthContentPresenter(IFragmentNorthContentView view) {
        this.view = view;
    }

    public void getResultLottery(String date) {
        icmd.excute(1, date);
    }

    public void socketConnect() {
        socket.connect();
//        view.cleaOldData();
        listenSocketEvent();
//        if (NetworkUtils.getInstance().isOnline(view.getActivity().getApplicationContext())){
//3
//        } else {
//            view.getResultLotteryError(view.getActivity().getString(R.string.err_network));
//        }
    }

    private void listenSocketEvent() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                if (view.getActivity() != null) {
                    view.getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "connect success: " + Arrays.toString(args));

                            initAuthentSocket();

                            socket.on("current_result", new Emitter.Listener() {
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
                                                    view.setCurrentResult(resp_result);
                                                    view.setDataSocket(resp_result);
                                                }
                                            }
                                        });
                                    }
                                }
                            });

                            socket.on("new_result", new Emitter.Listener() {
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
                            });

                            socket.on("liveloto", new Emitter.Listener() {
                                @Override
                                public void call(final Object... args) {
                                    if (view.getActivity() != null) {
                                        view.getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                RESP_LiveLoto liveLoto = JsonHelper.getObjectNoException(args[0].toString(), RESP_LiveLoto.class);
                                                Log.e(TAG, "call: new result " + liveLoto.toString());
                                                view.setLiveLoto(liveLoto);
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
        });
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

    public void disconnectSocket() {
        if (socket != null) {
            socket.disconnect();
        }
    }

    public void checkSocket() {
        if (socket.connected()){
            socket.disconnect();
        }
    }
}
