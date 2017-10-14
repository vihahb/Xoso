package com.xproject.xoso.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xproject.xoso.xoso.model.entity.FrequencyEntity;

import java.util.List;

/**
 * Created by vivhp on 10/6/2017.
 */

public class RESP_Frequency extends RESP_Basic{

    @Expose
    private List<FrequencyEntity> data;

    public List<FrequencyEntity> getData() {
        return data;
    }

    public void setData(List<FrequencyEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_Frequency{" +
                "data=" + data +
                '}';
    }
}
