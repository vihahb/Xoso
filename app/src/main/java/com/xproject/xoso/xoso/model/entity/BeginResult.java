package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vivhp on 9/12/2017.
 */

public class BeginResult implements Serializable {

    @Expose
    private List<String> b0;
    @Expose
    private List<String> b1;
    @Expose
    private List<String> b2;
    @Expose
    private List<String> b3;
    @Expose
    private List<String> b4;
    @Expose
    private List<String> b5;
    @Expose
    private List<String> b6;
    @Expose
    private List<String> b7;
    @Expose
    private List<String> b8;
    @Expose
    private List<String> b9;

    public BeginResult() {
    }

    public BeginResult(List<String> b0, List<String> b1, List<String> b2, List<String> b3, List<String> b4, List<String> b5, List<String> b6, List<String> b7, List<String> b8, List<String> b9) {
        this.b0 = b0;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.b5 = b5;
        this.b6 = b6;
        this.b7 = b7;
        this.b8 = b8;
        this.b9 = b9;
    }

    public List<String> getB0() {
        return b0;
    }

    public void setB0(List<String> b0) {
        this.b0 = b0;
    }

    public List<String> getB1() {
        return b1;
    }

    public void setB1(List<String> b1) {
        this.b1 = b1;
    }

    public List<String> getB2() {
        return b2;
    }

    public void setB2(List<String> b2) {
        this.b2 = b2;
    }

    public List<String> getB3() {
        return b3;
    }

    public void setB3(List<String> b3) {
        this.b3 = b3;
    }

    public List<String> getB4() {
        return b4;
    }

    public void setB4(List<String> b4) {
        this.b4 = b4;
    }

    public List<String> getB5() {
        return b5;
    }

    public void setB5(List<String> b5) {
        this.b5 = b5;
    }

    public List<String> getB6() {
        return b6;
    }

    public void setB6(List<String> b6) {
        this.b6 = b6;
    }

    public List<String> getB7() {
        return b7;
    }

    public void setB7(List<String> b7) {
        this.b7 = b7;
    }

    public List<String> getB8() {
        return b8;
    }

    public void setB8(List<String> b8) {
        this.b8 = b8;
    }

    public List<String> getB9() {
        return b9;
    }

    public void setB9(List<String> b9) {
        this.b9 = b9;
    }

    @Override
    public String toString() {
        return "BeginResult{" +
                "b0=" + b0 +
                ", b1=" + b1 +
                ", b2=" + b2 +
                ", b3=" + b3 +
                ", b4=" + b4 +
                ", b5=" + b5 +
                ", b6=" + b6 +
                ", b7=" + b7 +
                ", b8=" + b8 +
                ", b9=" + b9 +
                '}';
    }
}
