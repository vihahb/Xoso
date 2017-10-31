package com.xProject.XosoVIP.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by vivhp on 10/11/2017.
 */

public class CycleLoto implements Serializable {

    @Expose
    private String last_appear;
    @Expose
    private String last_special;
    @Expose
    private String start;
    @Expose
    private String end;
    @Expose
    private String start_special;
    @Expose
    private String end_special;
    @Expose
    private int max_gan;
    @Expose
    private String last_appear_not_between;
    @Expose
    private String last_not_special;
    @Expose
    private int current_gan;

    public CycleLoto() {
    }

    public String getLast_appear() {
        return last_appear;
    }

    public void setLast_appear(String last_appear) {
        this.last_appear = last_appear;
    }

    public String getLast_special() {
        return last_special;
    }

    public void setLast_special(String last_special) {
        this.last_special = last_special;
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

    public String getStart_special() {
        return start_special;
    }

    public void setStart_special(String start_special) {
        this.start_special = start_special;
    }

    public String getEnd_special() {
        return end_special;
    }

    public void setEnd_special(String end_special) {
        this.end_special = end_special;
    }

    public int getMax_gan() {
        return max_gan;
    }

    public void setMax_gan(int max_gan) {
        this.max_gan = max_gan;
    }

    public String getLast_appear_not_between() {
        return last_appear_not_between;
    }

    public void setLast_appear_not_between(String last_appear_not_between) {
        this.last_appear_not_between = last_appear_not_between;
    }

    public String getLast_not_special() {
        return last_not_special;
    }

    public void setLast_not_special(String last_not_special) {
        this.last_not_special = last_not_special;
    }

    public int getCurrent_gan() {
        return current_gan;
    }

    public void setCurrent_gan(int current_gan) {
        this.current_gan = current_gan;
    }

    @Override
    public String toString() {
        return "CycleLoto{" +
                "last_appear='" + last_appear + '\'' +
                ", last_special='" + last_special + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", start_special='" + start_special + '\'' +
                ", end_special='" + end_special + '\'' +
                ", max_gan=" + max_gan +
                ", last_appear_not_between='" + last_appear_not_between + '\'' +
                ", last_not_special='" + last_not_special + '\'' +
                ", current_gan=" + current_gan +
                '}';
    }
}
