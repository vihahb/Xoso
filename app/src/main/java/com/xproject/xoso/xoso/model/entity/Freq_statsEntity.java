package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vivhp on 10/12/2017.
 */

public class Freq_statsEntity implements Serializable {
    @Expose
    private int number;
    @Expose
    private int count;
    @Expose
    private List<String> date;

    public Freq_statsEntity() {
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

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Freq_statsEntity{" +
                "number=" + number +
                ", count=" + count +
                ", date=" + date +
                '}';
    }
}
