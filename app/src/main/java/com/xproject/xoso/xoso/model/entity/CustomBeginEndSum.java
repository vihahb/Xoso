package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by vivhp on 10/12/2017.
 */

public class CustomBeginEndSum {

    @Expose
    private String number;
    @Expose
    private String count;

    public CustomBeginEndSum() {
    }

    public CustomBeginEndSum(String number, String count) {
        this.number = number;
        this.count = count;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CustomBeginEndSum{" +
                "number='" + number + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
