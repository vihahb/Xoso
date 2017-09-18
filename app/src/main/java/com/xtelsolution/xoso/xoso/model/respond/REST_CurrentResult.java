package com.xtelsolution.xoso.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xtelsolution.xoso.xoso.model.entity.CurentResult;

import java.util.List;

/**
 * Created by vivhp on 9/11/2017.
 */

public class REST_CurrentResult extends RESP_Basic {

    @Expose
    private List<CurentResult> data;

    public List<CurentResult> getData() {
        return data;
    }

    public void setData(List<CurentResult> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "REST_CurrentResult{" +
                "data=" + data +
                '}';
    }
}
