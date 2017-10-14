package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by vivhp on 9/28/2017.
 */

public class Authent {

    @Expose
    private Long t;

    @Expose
    private String k;

    public Authent() {
    }

    public Long getT() {
        return t;
    }

    public void setT(Long t) {
        this.t = t;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return "{" +
                "\"t\":" + t +
                ", \"k\":" + k + '\'' +
                '}';
    }
}
