package com.xProject.XosoVIP.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xProject.XosoVIP.xoso.model.entity.Dream;

import java.util.List;

/**
 * Created by vivhp on 9/9/2017.
 */

public class RESP_Dream extends RESP_Basic {

    @Expose
    private List<Dream> data;

    public List<Dream> getData() {
        return data;
    }

    public void setData(List<Dream> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_Dream{" +
                "data=" + data +
                '}';
    }
}
