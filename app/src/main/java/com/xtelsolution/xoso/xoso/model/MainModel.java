package com.xtelsolution.xoso.xoso.model;

import android.util.Log;

import com.xtelsolution.xoso.sdk.utils.ResponseHandle;

import static android.content.ContentValues.TAG;

/**
 * Created by vivhp on 9/9/2017.
 */

public class MainModel extends BasicModel {

    private static MainModel instance;
    private static final String TAG = "MainModel";

    public static synchronized MainModel getInstance() {
        if (instance == null)
            instance = new MainModel();
        return instance;
    }

    public void getListDream(ResponseHandle responseHandle) {
        String url_dream = SERVER_API + API_DREAM;
        requestServer.getApi(url_dream, null, responseHandle);
    }

    public void getListRegion(ResponseHandle responseHandle) {
        String url_region = SERVER_API + API_REGION;
        requestServer.getApi(url_region, null, responseHandle);
    }

    public void getLottery(String date_time, ResponseHandle responseHandle) {
        String url_lottery_result = SERVER_API + API_RESULT_AREA + "area=bac&date=" + date_time;
        Log.e(TAG, "url_lottery_result: " + url_lottery_result);
        requestServer.getApi(url_lottery_result, null, responseHandle);
    }

    public void getLotteryCentral(String date_time, ResponseHandle responseHandle) {
        String url_lottery_result = SERVER_API + API_RESULT_AREA + "area=trung&date=" + date_time;
        Log.e(TAG, "url_lottery_result: " + url_lottery_result);
        requestServer.getApi(url_lottery_result, null, responseHandle);
    }

    public void getLotterySouth(String date_time, ResponseHandle responseHandle) {
        String url_lottery_result = SERVER_API + API_RESULT_AREA + "area=nam&date=" + date_time;
        Log.e(TAG, "url_lottery_result: " + url_lottery_result);
        requestServer.getApi(url_lottery_result, null, responseHandle);
    }
}
