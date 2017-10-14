package com.xproject.xoso.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xproject.xoso.xoso.model.entity.CycleSpecialEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class RESP_CycleSpecial extends RESP_Basic implements Serializable {

    @Expose
    private CycleSpecialEntity data;

    public RESP_CycleSpecial() {
    }

    public CycleSpecialEntity getData() {
        return data;
    }

    public void setData(CycleSpecialEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_CycleSpecial{" +
                "data=" + data +
                '}';
    }
}
