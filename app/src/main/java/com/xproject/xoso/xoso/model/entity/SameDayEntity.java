package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by vivhp on 10/12/2017.
 */

public class SameDayEntity implements Serializable{
    @Expose
    private String day;
    @Expose
    private String special;

    public SameDayEntity() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    @Override
    public String toString() {
        return "SameDayEntity{" +
                "day='" + day + '\'' +
                ", special='" + special + '\'' +
                '}';
    }
}
