package com.xproject.xoso.xoso.model;

import android.util.Log;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.entity.Authent;

import io.fabric.sdk.android.services.network.HttpRequest;

/**
 * Created by vivhp on 9/9/2017
 */

public class MainModel extends BasicModel {

    private static final String TAG = "MainModel";
    private static MainModel instance;

    public static synchronized MainModel getInstance() {
        if (instance == null)
            instance = new MainModel();
        return instance;
    }

    public void getListDream(ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        String url_dream = SERVER_API + API_DREAM + "?t=" + authent.getT() + "&k=" + authent.getK();
        requestServer.getApi(url_dream, basic_authent, responseHandle);
    }

    public void getListRegion(ResponseHandle responseHandle) {
        String url_region = SERVER_API + API_REGION;
        requestServer.getApi(url_region, null, responseHandle);
    }

    public void getLottery(String date_time, ResponseHandle responseHandle) {
//        String url_lottery_result = SERVER_API + API_RESULT_AREA + "area=bac&date=" + date_time;

        Authent authent = Constants.getAuthent();

        String url_lottery_result = SERVER_API + API_RESULT_AREA + "area=bac&date=" + date_time + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "url_lottery_result: " + url_lottery_result);
        requestServer.getApi(url_lottery_result, basic_authent, responseHandle);
    }

    public void getLotteryCentral(String date_time, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_lottery_result = SERVER_API + API_RESULT_AREA + "area=trung&date=" + date_time + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "url_lottery_result: " + url_lottery_result);
        requestServer.getApi(url_lottery_result, basic_authent, responseHandle);
    }

    public void getLotterySouth(String date_time, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_lottery_result = SERVER_API + API_RESULT_AREA + "area=nam&date=" + date_time + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "url_lottery_result: " + url_lottery_result);
        requestServer.getApi(url_lottery_result, basic_authent, responseHandle);
    }

    public void getCategory(ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_lottery_result = SERVER_API + "info/category?t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "url_category_result: " + url_lottery_result);
        requestServer.getApi(url_lottery_result, basic_authent, responseHandle);
    }
}
