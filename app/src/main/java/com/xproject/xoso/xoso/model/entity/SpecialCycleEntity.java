package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by vivhp on 10/10/2017.
 */

public class SpecialCycleEntity implements Serializable {
    @Expose
    private String number;
    @Expose
    private int day_count;
    @Expose
    private String date_appear;
    @Expose
    private int max_gan;
    @Expose
    private String last_search_appear;
    @Expose
    private String end;
    @Expose
    private String start;

    public SpecialCycleEntity() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getDay_count() {
        return day_count;
    }

    public void setDay_count(int day_count) {
        this.day_count = day_count;
    }

    public String getDate_appear() {
        return date_appear;
    }

    public void setDate_appear(String date_appear) {
        this.date_appear = date_appear;
    }

    public int getMax_gan() {
        return max_gan;
    }

    public void setMax_gan(int max_gan) {
        this.max_gan = max_gan;
    }

    public String getLast_search_appear() {
        return last_search_appear;
    }

    public void setLast_search_appear(String last_search_appear) {
        this.last_search_appear = last_search_appear;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "SpecialCycleEntity{" +
                "number='" + number + '\'' +
                ", day_count=" + day_count +
                ", date_appear='" + date_appear + '\'' +
                ", max_gan=" + max_gan +
                ", last_search_appear='" + last_search_appear + '\'' +
                ", end='" + end + '\'' +
                ", start='" + start + '\'' +
                '}';
    }
}
