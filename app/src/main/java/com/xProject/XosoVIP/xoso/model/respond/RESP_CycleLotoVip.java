package com.xProject.XosoVIP.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xProject.XosoVIP.xoso.model.entity.CycleLoto;

/**
 * Created by vivhp on 10/11/2017.
 */

public class RESP_CycleLotoVip extends RESP_Basic {

    @Expose
    private CycleLoto data;

    public CycleLoto getData() {
        return data;
    }

    public void setData(CycleLoto data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_CycleLotoVip{" +
                "data=" + data +
                '}';
    }
}
