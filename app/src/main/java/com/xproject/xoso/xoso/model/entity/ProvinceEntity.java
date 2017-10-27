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

    @Expose
    private String mamien;

    @Expose
    private String tenvungUnicode;

    public ProvinceEntity() {
    }

    public ProvinceEntity(int mavung, String tenvung, String mamien) {
        this.mavung = mavung;
        this.tenvung = tenvung;
        this.mamien = mamien;
    }

    public ProvinceEntity(int mavung, String tenvung, String mamien, String tenvungUnicode) {
        this.mavung = mavung;
        this.tenvung = tenvung;
        this.mamien = mamien;
        this.tenvungUnicode = tenvungUnicode;
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

    public String getMamien() {
        return mamien;
    }

    public void setMamien(String mamien) {
        this.mamien = mamien;
    }

    public String getTenvungUnicode() {
        return tenvungUnicode;
    }

    public void setTenvungUnicode(String tenvungUnicode) {
        this.tenvungUnicode = tenvungUnicode;
    }

    @Override
    public String toString() {
        return "ProvinceEntity{" +
                "mavung=" + mavung +
                ", tenvung='" + tenvung + '\'' +
                ", mamien='" + mamien + '\'' +
                '}';
    }
}
