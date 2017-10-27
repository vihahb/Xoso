package com.xproject.xoso.sdk.common;

import android.util.Log;

import com.xproject.xoso.xoso.model.entity.Authent;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by vivhp on 9/8/2017.
 */

public class Constants {

    public static final String SERVER_API = "124.158.4.190:3000/";
    public static final String API_DREAM = "dream";
    public static final String AREA = "bac";
    public static final String NOTIFICATION_ID = "notify_id";
    public static final String NOTIFICATION = "notification";
    public static final String AREA_NOTIFY = "area_notify";
    public static final String GET_FULL_VALUE = "get_full_value";
    public static final String FLAG_NOTRTH = "flag_notify";
    public static final String FLAG_CENTRAL = "flag_central";
    public static final String FLAG_SOUTH = "flag_south";
    public static final String FCM_TOKEN = "fcm_token";
    public static final String ACTION_TYPE = "action_type";
    public static final String LIST_SPEED = "LIST_SPEED";
    public static final String TEMP = "temp";
    public static final String LIST_FREQUENCY = "frequency";
    public static final String IMPORTANT = "important";
    public static final String TITLE = "title";
    public static final String OBJECT = "object";
    public static final String START_LIVE = "start_live";
    public static final String NOTIFY = "notify";
    public static final String URL = "url";
    //REGION
    public static final String FLAG_RADIO_REGION = "FLAG_RADIO_REGION";
    //CODE PROVINCE
    public static final String PROVINCE_FAVORITE_CODE = "PROVINCE_FAVORITE_CODE";
    private static final String TAG = "Constants";
    public static String SHARED_NAME = "xoso_pref";
    public static String DREAM_FLAG = "dream_flag";
    public static String CATEGORY_FLAG = "CATEGORY_FLAG";
    /**
     * Setting configuration
     */
    //Notification
    public static String NOTIFY_N_FLAG = "NOTIFY_N_FLAG";
    public static String NOTIFY_C_FLAG = "NOTIFY_C_FLAG";
    public static String NOTIFY_S_FLAG = "NOTIFY_S_FLAG";
    //ViBRATE
    public static String ViBRATE_FLAG = "VIBRATE_FLAG";
    //SOUND
    public static String SOUND_FLAG = "SOUND_FLAG";

    public static final String RESET = "RESET";

    /**
     * Setting check live*/
    public static final String CHECK_DONE_N = "check_done_n";
    public static final String CHECK_DONE_C = "check_done_c";
    public static final String CHECK_DONE_S = "check_done_s";

    public static Authent getAuthent() {
        Authent authent = new Authent();

        Date date = new Date();
        Timestamp time_stamp = new Timestamp(date.getTime());
        long time = ((long) Math.floor(time_stamp.getTime())) / 1000;
        String key = "131295041195841654789342" + time;

        authent.setT(time);
        authent.setK(getMD5(key));
        Log.e(TAG, "getAuthent: " + authent.toString());
        return authent;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
