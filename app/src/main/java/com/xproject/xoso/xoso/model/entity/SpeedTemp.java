package com.xproject.xoso.xoso.model.entity;

import java.io.Serializable;

/**
 * Created by vivhp on 10/5/2017.
 */

public class SpeedTemp implements Serializable {

    private int id_cat;
    private String name_cat;
    private String date_begin;
    private String date_format_begin;
    private String date_format_end;
    private String date_end;
    private boolean only_special;
    private String number;
    private String date_count;
    //Importan
    private String type;
    private String type_name;
    //Day
    private String day_of_week;
    private String day_name;
    private String week;

    private String AllOrOne;

    public SpeedTemp() {
    }

    public String getName_cat() {
        return name_cat;
    }

    public void setName_cat(String name_cat) {
        this.name_cat = name_cat;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getDate_begin() {
        return date_begin;
    }

    public void setDate_begin(String date_begin) {
        this.date_begin = date_begin;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public boolean isOnly_special() {
        return only_special;
    }

    public void setOnly_special(boolean only_special) {
        this.only_special = only_special;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getDate_format_begin() {
        return date_format_begin;
    }

    public void setDate_format_begin(String date_format_begin) {
        this.date_format_begin = date_format_begin;
    }

    public String getDate_format_end() {
        return date_format_end;
    }

    public void setDate_format_end(String date_format_end) {
        this.date_format_end = date_format_end;
    }

    public String getDate_count() {
        return date_count;
    }

    public void setDate_count(String date_count) {
        this.date_count = date_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public String getAllOrOne() {
        return AllOrOne;
    }

    public void setAllOrOne(String allOrOne) {
        AllOrOne = allOrOne;
    }

    @Override
    public String toString() {
        return "SpeedTemp{" +
                "id_cat=" + id_cat +
                ", name_cat='" + name_cat + '\'' +
                ", date_begin='" + date_begin + '\'' +
                ", date_format_begin='" + date_format_begin + '\'' +
                ", date_format_end='" + date_format_end + '\'' +
                ", date_end='" + date_end + '\'' +
                ", only_special=" + only_special +
                ", number='" + number + '\'' +
                ", date_count='" + date_count + '\'' +
                ", type='" + type + '\'' +
                ", type_name='" + type_name + '\'' +
                ", day_of_week='" + day_of_week + '\'' +
                ", day_name='" + day_name + '\'' +
                ", week='" + week + '\'' +
                ", AllOrOne='" + AllOrOne + '\'' +
                '}';
    }
}
