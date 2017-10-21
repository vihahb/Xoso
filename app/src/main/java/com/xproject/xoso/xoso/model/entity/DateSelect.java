package com.xproject.xoso.xoso.model.entity;

import java.io.Serializable;

/**
 * Created by vivhp on 10/5/2017.
 */

public class DateSelect implements Serializable {
    private int count;
    private int name;

    public DateSelect() {
    }

    public DateSelect(int count, int name) {
        this.count = count;
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DateSelect{" +
                "count=" + count +
                ", name=" + name +
                '}';
    }
}
