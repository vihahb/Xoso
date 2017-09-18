package com.xtelsolution.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import io.realm.RealmObject;

/**
 * Created by vivhp on 9/9/2017.
 */

public class Dream extends RealmObject{
    @Expose
    private int dream_id;
    @Expose
    private String dreamed;
    @Expose
    private String number;

    public Dream() {
    }

    public Dream(int dream_id, String dreamed, String number) {
        this.dream_id = dream_id;
        this.dreamed = dreamed;
        this.number = number;
    }

    public int getDream_id() {
        return dream_id;
    }

    public void setDream_id(int dream_id) {
        this.dream_id = dream_id;
    }

    public String getDreamed() {
        return dreamed;
    }

    public void setDreamed(String dreamed) {
        this.dreamed = dreamed;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Dream{" +
                "dream_id=" + dream_id +
                ", dreamed='" + dreamed + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
