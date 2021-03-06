package com.xProject.XosoVIP.xoso.model;

import android.util.Log;

import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.ResponseHandle;
import com.xProject.XosoVIP.xoso.model.entity.Authent;

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

    public void getLucky(String birth_day, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_lucky_result = "http://xoso.la/index.php?route=api/luckynumber&birthday=" + birth_day;
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "url_lucky_result: " + url_lucky_result);
        requestServer.getApi(url_lucky_result, basic_authent, responseHandle);
    }
}
