package com.xProject.XosoVIP.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xProject.XosoVIP.xoso.model.entity.FrequencyLotoEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class RESP_FrequencyLoto extends RESP_Basic implements Serializable {

    @Expose
    private int sum;
    @Expose
    private List<FrequencyLotoEntity> data;

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public List<FrequencyLotoEntity> getData() {
        return data;
    }

    public void setData(List<FrequencyLotoEntity> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RESP_FrequencyLoto{" +
                "sum=" + sum +
                ", data=" + data +
                '}';
    }
}
