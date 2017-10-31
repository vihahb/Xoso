package com.xProject.XosoVIP.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by vivhp on 10/12/2017.
 */

public class SameDow_NextDayEntity implements Serializable {

    @Expose
    private String today;
    @Expose
    private String special;
    @Expose
    private String next_day;
    @Expose
    private String next_special;

    public SameDow_NextDayEntity() {
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getNext_day() {
        return next_day;
    }

    public void setNext_day(String next_day) {
        this.next_day = next_day;
    }

    public String getNext_special() {
        return next_special;
    }

    public void setNext_special(String next_special) {
        this.next_special = next_special;
    }

    @Override
    public String toString() {
        return "SameDow_NextDayEntity{" +
                "today='" + today + '\'' +
                ", special='" + special + '\'' +
                ", next_day='" + next_day + '\'' +
                ", next_special='" + next_special + '\'' +
                '}';
    }
}
