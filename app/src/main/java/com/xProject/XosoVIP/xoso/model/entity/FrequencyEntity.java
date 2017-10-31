package com.xProject.XosoVIP.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by vivhp on 10/6/2017.
 */

public class FrequencyEntity implements Serializable {

    @Expose
    private String number;
    @Expose
    private int count_day;
    @Expose
    private int count_time;
    @Expose
    private String last_appear;

    public FrequencyEntity() {
    }

    public FrequencyEntity(String number, int count_day, int count_time, String last_appear) {
        this.number = number;
        this.count_day = count_day;
        this.count_time = count_time;
        this.last_appear = last_appear;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCount_day() {
        return count_day;
    }

    public void setCount_day(int count_day) {
        this.count_day = count_day;
    }

    public int getCount_time() {
        return count_time;
    }

    public void setCount_time(int count_time) {
        this.count_time = count_time;
    }

    public String getLast_appear() {
        return last_appear;
    }

    public void setLast_appear(String last_appear) {
        this.last_appear = last_appear;
    }

    @Override
    public String toString() {
        return "FrequencyEntity{" +
                "number='" + number + '\'' +
                ", count_day=" + count_day +
                ", count_time=" + count_time +
                ", last_appear='" + last_appear + '\'' +
                '}';
    }
}
