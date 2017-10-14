package com.xproject.xoso.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xproject.xoso.xoso.model.entity.ImportantEntity;

import java.util.List;

/**
 * Created by vivhp on 10/6/2017.
 */

public class RESP_Important extends RESP_Basic{

    @Expose
    private List<ImportantEntity> data;

    public List<ImportantEntity> getData() {
        return data;
    }

    public void setData(List<ImportantEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_Important{" +
                "data=" + data +
                '}';
    }
}
