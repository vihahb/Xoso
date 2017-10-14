package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by vivhp on 10/9/2017.
 */

public class SyntheticEntity implements Serializable {

    @Expose
    private int number;
    @Expose
    private int count;
    @Expose
    private String last_appear;

    public SyntheticEntity() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLast_appear() {
        return last_appear;
    }

    public void setLast_appear(String last_appear) {
        this.last_appear = last_appear;
    }

    @Override
    public String toString() {
        return "SyntheticEntity{" +
                "number=" + number +
                ", count=" + count +
                ", last_appear='" + last_appear + '\'' +
                '}';
    }
}
