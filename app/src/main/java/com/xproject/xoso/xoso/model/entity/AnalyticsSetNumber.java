package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by vivhp on 10/5/2017.
 */

public class AnalyticsSetNumber implements Serializable {

    @Expose
    private int count;
    @Expose
    private String number;
    @Expose
    private String last_appear;

    public AnalyticsSetNumber() {
    }

    public AnalyticsSetNumber(int count, String number, String last_appear) {
        this.count = count;
        this.number = number;
        this.last_appear = last_appear;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLast_appear() {
        return last_appear;
    }

    public void setLast_appear(String last_appear) {
        this.last_appear = last_appear;
    }

    @Override
    public String toString() {
        return "AnalyticsSetNumber{" +
                "count=" + count +
                ", number='" + number + '\'' +
                ", last_appear='" + last_appear + '\'' +
                '}';
    }
}
