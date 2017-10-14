package com.xproject.xoso.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xproject.xoso.xoso.model.entity.SyntheticEntity;

import java.util.List;

/**
 * Created by vivhp on 10/9/2017.
 */

public class RESP_Synthetic extends RESP_Basic {

    @Expose
    private List<SyntheticEntity> data;

    public List<SyntheticEntity> getData() {
        return data;
    }

    public void setData(List<SyntheticEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_Synthetic{" +
                "data=" + data +
                '}';
    }
}
