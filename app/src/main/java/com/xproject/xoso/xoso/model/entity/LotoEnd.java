package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by vivhp on 9/26/2017.
 */

public class LotoEnd {
    @Expose
    private String label;

    @Expose
    private String value;

    public LotoEnd(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LotoEnd{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
