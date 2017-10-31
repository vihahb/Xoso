package com.xProject.XosoVIP.xoso.model;

import com.xProject.XosoVIP.sdk.utils.RequestServer;

/**
 * Created by vivhp on 9/5/2017.
 */

public class BasicModel {
    public final String SERVER_API = "http://124.158.4.190:3000/";
    public final String API_REGION = "info/category";
    public final String API_DREAM = "dream";
    public final String API_RESULT = "result?type=category&";
    public final String API_RESULT_AREA = "result?type=area&";
    public RequestServer requestServer = new RequestServer();
}
