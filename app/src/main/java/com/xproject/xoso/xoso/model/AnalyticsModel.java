package com.xproject.xoso.xoso.model;

import android.util.Log;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.entity.Authent;

import io.fabric.sdk.android.services.network.HttpRequest;

/**
 * Created by vivhp on 10/5/2017.
 */

public class AnalyticsModel extends BasicModel {
    private static final String TAG = "AnalyticsModel";
    private static AnalyticsModel instance;

    public static synchronized AnalyticsModel getInstance() {
        if (instance == null)
            instance = new AnalyticsModel();
        return instance;
    }

    /**
     * Analytics Speed
     */
    public void getSpeedAnalytics(String begin_date, String end_date, String number, int category_id, boolean onlySpecial, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_speed = SERVER_API + "stats?start_date=" + begin_date + "&end_date=" + end_date + "&category=" + category_id + "&only_special=" + onlySpecial + "&numbers=" + number + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getSpeedAnalytics: " + url_speed);
        requestServer.getApi(url_speed, basic_authent, responseHandle);
    }

    public void getSumAnalytics(String begin_date, String end_date, String number, int category_id, boolean onlySpecial, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_sum = SERVER_API + "stats?start_date=" + begin_date + "&end_date=" + end_date + "&category=" + category_id + "&only_special=" + onlySpecial + "&sum=" + number + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getSumAnalytics: " + url_sum);
        requestServer.getApi(url_sum, basic_authent, responseHandle);
    }

    public void getFrequencyAnalytics(String date_count, String number, int category_id, boolean onlySpecial, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_frequency = SERVER_API + "stats/freq?date_count=" + date_count + "&category=" + category_id + "&only_special=" + onlySpecial + "&numbers=" + number + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getFrequencyAnalytics: " + url_frequency);
        requestServer.getApi(url_frequency, basic_authent, responseHandle);
    }

    public void getImportantAnalytics(String type, int category_id, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_Important = SERVER_API + "stats/important?category=" + category_id + "&type=" + type + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getImportantAnalytics: " + url_Important);
        requestServer.getApi(url_Important, basic_authent, responseHandle);
    }

    public void getSynthetic(String type, String start, String end, int cat_id, boolean isSpecial, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_Synthetic = SERVER_API + "stats/general?type=" + type + "&category=" + cat_id + "&start_date=" + start + "&end_date=" + end + "&only_special=" + isSpecial + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getSynthetic: " + url_Synthetic);
        requestServer.getApi(url_Synthetic, basic_authent, responseHandle);
    }

    public void getDay(String week, String end_date, String day_of_week, int cat_id, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_Day = SERVER_API + "stats/day?category=" + cat_id + "&week=" + week + "&end_date=" + end_date + "&day_of_week=" + day_of_week + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getDay: " + url_Day);
        requestServer.getApi(url_Day, basic_authent, responseHandle);
    }

    /**
     * Analytics VIP
     */

    public void getCycleLoto(String number, int cat_id, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_CycleLoto = SERVER_API + "vip/loto-2?cat_id=" + cat_id + "&numbers=" + number + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getCycleLoto: " + url_CycleLoto);
        requestServer.getApi(url_CycleLoto, basic_authent, responseHandle);
    }

    public void getCycleSpecial(String query_date, int cat_id, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_CycleSpecial = SERVER_API + "vip/freqspecial?cat_id=" + cat_id + "&end_date=" + query_date + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getCycleSpecial: " + url_CycleSpecial);
        requestServer.getApi(url_CycleSpecial, basic_authent, responseHandle);
    }

    public void getCycleListSpecial(String number, String start_date, String end_date, int cat_id, String allOrOne, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_CycleListSpecial = SERVER_API + "vip/array?number=" + number + "&start_date=" + start_date + "&end_date=" + end_date + "&category=" + cat_id + "&only_special=true&type=" + allOrOne + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getCycleListSpecial: " + url_CycleListSpecial);
        requestServer.getApi(url_CycleListSpecial, basic_authent, responseHandle);
    }

    public void getCycleListLoto(String number, String start_date, String end_date, int cat_id, String allOrOne, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_CycleListLoto = SERVER_API + "vip/array?number=" + number + "&start_date=" + start_date + "&end_date=" + end_date + "&category=" + cat_id + "&only_special=false&type=" + allOrOne + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getCycleListLoto: " + url_CycleListLoto);
        requestServer.getApi(url_CycleListLoto, basic_authent, responseHandle);
    }

    public void getFrequencyLoto(String begin, String end, int cat_id, String number, String day, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();

        String url_FrequencyLoto = SERVER_API + "vip/timingfreq?number=" + number + "&start_date=" + begin + "&end_date=" + end + "&category=" + cat_id + "&day_of_week=" + day + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getCycleLoto: " + url_FrequencyLoto);
        requestServer.getApi(url_FrequencyLoto, basic_authent, responseHandle);
    }

    public void getFrequencyLotoAllDay(String begin, String end, int cat_id, String number, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();

        String url_FrequencyLotoAllDay = SERVER_API + "vip/timingfreq?number=" + number + "&start_date=" + begin + "&end_date=" + end + "&category=" + cat_id + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "getCycleLoto: " + url_FrequencyLotoAllDay);
        requestServer.getApi(url_FrequencyLotoAllDay, basic_authent, responseHandle);
    }

    public void getSpecialTomorow(String date, int cat_id, ResponseHandle responseHandle) {
        Authent authent = Constants.getAuthent();
        String url_SpecialTomorow = SERVER_API + "vip/specialtomorrow?cat_id=" + cat_id + "&date=" + date + "&t=" + authent.getT() + "&k=" + authent.getK();
        String basic_authent = "Basic " + HttpRequest.Base64.encode("XtelSolution:xoso.Xtel.123!@#");
        Log.e(TAG, "url_SpecialTomorow: " + url_SpecialTomorow);
        requestServer.getApi(url_SpecialTomorow, basic_authent, responseHandle);
    }
}
