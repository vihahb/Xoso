package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by vivhp on 10/10/2017.
 */

public class CycleLotoEntity implements Serializable {

    @Expose
    private int number;
    @Expose
    private String start;
    @Expose
    private String end;
    @Expose
    private String last_appear;
    @Expose
    private int count;

    public CycleLotoEntity() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getLast_appear() {
        return last_appear;
    }

    public void setLast_appear(String last_appear) {
        this.last_appear = last_appear;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CycleLotoEntity{" +
                "number=" + number +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", last_appear='" + last_appear + '\'' +
                ", count=" + count +
                '}';
    }
}
