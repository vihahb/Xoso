package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import io.realm.RealmObject;

/**
 * Created by vivhp on 10/4/2017.
 */

public class ProvinceEntity extends RealmObject {

    @Expose
    private int mavung;

    @Expose
    private String tenvung;

    public ProvinceEntity() {
    }

    public ProvinceEntity(int province_id, String provinceName) {
        this.mavung = province_id;
        this.tenvung = provinceName;
    }

    public int getMavung() {
        return mavung;
    }

    public void setMavung(int mavung) {
        this.mavung = mavung;
    }

    public String getTenvung() {
        return tenvung;
    }

    public void setTenvung(String tenvung) {
        this.tenvung = tenvung;
    }

    @Override
    public String toString() {
        return "ProvinceEntity{" +
                "mavung=" + mavung +
                ", tenvung='" + tenvung + '\'' +
                '}';
    }
}
