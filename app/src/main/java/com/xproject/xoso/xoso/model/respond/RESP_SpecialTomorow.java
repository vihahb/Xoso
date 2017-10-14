package com.xproject.xoso.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xproject.xoso.xoso.model.entity.SpecialTomorowEntity;

/**
 * Created by vivhp on 10/12/2017.
 */

public class RESP_SpecialTomorow extends RESP_Basic {

    @Expose
    private SpecialTomorowEntity data;

    public SpecialTomorowEntity getData() {
        return data;
    }

    public void setData(SpecialTomorowEntity data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
