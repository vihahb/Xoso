package com.xProject.XosoVIP.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;

import java.util.List;

/**
 * Created by vivhp on 10/4/2017.
 */

public class RESP_Category extends RESP_Basic {
    @Expose
    private List<ProvinceEntity> data;

    public List<ProvinceEntity> getData() {
        return data;
    }

    public void setData(List<ProvinceEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_Category{" +
                "data=" + data +
                '}';
    }
}
