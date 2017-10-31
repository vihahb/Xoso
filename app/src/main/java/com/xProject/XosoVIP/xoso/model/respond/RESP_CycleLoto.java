package com.xProject.XosoVIP.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xProject.XosoVIP.xoso.model.entity.CycleLotoEntity;

import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class RESP_CycleLoto extends RESP_Basic {

    @Expose
    private List<CycleLotoEntity> data;

    public List<CycleLotoEntity> getData() {
        return data;
    }

    public void setData(List<CycleLotoEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_CycleLoto{" +
                "data=" + data +
                '}';
    }
}
