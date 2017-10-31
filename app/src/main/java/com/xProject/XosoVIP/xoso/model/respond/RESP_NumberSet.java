package com.xProject.XosoVIP.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xProject.XosoVIP.xoso.model.entity.AnalyticsSetNumber;

import java.util.List;

/**
 * Created by vivhp on 10/5/2017.
 */

public class RESP_NumberSet extends RESP_Basic {

    @Expose
    private List<AnalyticsSetNumber> data;

    public List<AnalyticsSetNumber> getData() {
        return data;
    }

    public void setData(List<AnalyticsSetNumber> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_NumberSet{" +
                "data=" + data +
                '}';
    }
}
