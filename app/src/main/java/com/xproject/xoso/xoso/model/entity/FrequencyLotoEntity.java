package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class FrequencyLotoEntity implements Serializable {
    @Expose
    private String date;
    @Expose
    private int count;
    @Expose
    private List<String> atat_result;
    @Expose
    private Integer date_between;

    public FrequencyLotoEntity() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getAtat_result() {
        return atat_result;
    }

    public void setAtat_result(List<String> atat_result) {
        this.atat_result = atat_result;
    }

    public Integer getDate_between() {
        return date_between;
    }

    public void setDate_between(Integer date_between) {
        this.date_between = date_between;
    }

    @Override
    public String toString() {
        return "FrequencyLotoEntity{" +
                "date='" + date + '\'' +
                ", count=" + count +
                ", atat_result=" + atat_result +
                ", date_between=" + date_between +
                '}';
    }
}
