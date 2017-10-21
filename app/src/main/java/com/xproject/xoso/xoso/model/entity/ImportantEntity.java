package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by vivhp on 10/6/2017.
 */

public class ImportantEntity implements Serializable {

    @Expose
    private int number;
    @Expose
    private int current_streak;
    @Expose
    private int max_streak;
    @Expose
    private int count;
    @Expose
    private int not_appear;
    @Expose
    private String last_appear;
    @Expose
    private int no_last;

    public ImportantEntity() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCurrent_streak() {
        return current_streak;
    }

    public void setCurrent_streak(int current_streak) {
        this.current_streak = current_streak;
    }

    public int getMax_streak() {
        return max_streak;
    }

    public void setMax_streak(int max_streak) {
        this.max_streak = max_streak;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNot_appear() {
        return not_appear;
    }

    public void setNot_appear(int not_appear) {
        this.not_appear = not_appear;
    }

    public String getLast_appear() {
        return last_appear;
    }

    public void setLast_appear(String last_appear) {
        this.last_appear = last_appear;
    }

    public int getNo_last() {
        return no_last;
    }

    public void setNo_last(int no_last) {
        this.no_last = no_last;
    }

    @Override
    public String toString() {
        return "ImportantEntity{" +
                "number=" + number +
                ", current_streak=" + current_streak +
                ", max_streak=" + max_streak +
                ", count=" + count +
                ", not_appear=" + not_appear +
                ", last_appear='" + last_appear + '\'' +
                ", no_last=" + no_last +
                '}';
    }
}
