package com.xProject.XosoVIP.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vivhp on 9/12/2017.
 */

public class EndResult implements Serializable {

    @Expose
    private List<String> e0;
    @Expose
    private List<String> e1;
    @Expose
    private List<String> e2;
    @Expose
    private List<String> e3;
    @Expose
    private List<String> e4;
    @Expose
    private List<String> e5;
    @Expose
    private List<String> e6;
    @Expose
    private List<String> e7;
    @Expose
    private List<String> e8;
    @Expose
    private List<String> e9;

    public EndResult() {
    }

    public EndResult(List<String> e0, List<String> e1, List<String> e2, List<String> e3, List<String> e4, List<String> e5, List<String> e6, List<String> e7, List<String> e8, List<String> e9) {
        this.e0 = e0;
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
        this.e5 = e5;
        this.e6 = e6;
        this.e7 = e7;
        this.e8 = e8;
        this.e9 = e9;
    }

    public List<String> getE0() {
        return e0;
    }

    public void setE0(List<String> e0) {
        this.e0 = e0;
    }

    public List<String> getE1() {
        return e1;
    }

    public void setE1(List<String> e1) {
        this.e1 = e1;
    }

    public List<String> getE2() {
        return e2;
    }

    public void setE2(List<String> e2) {
        this.e2 = e2;
    }

    public List<String> getE3() {
        return e3;
    }

    public void setE3(List<String> e3) {
        this.e3 = e3;
    }

    public List<String> getE4() {
        return e4;
    }

    public void setE4(List<String> e4) {
        this.e4 = e4;
    }

    public List<String> getE5() {
        return e5;
    }

    public void setE5(List<String> e5) {
        this.e5 = e5;
    }

    public List<String> getE6() {
        return e6;
    }

    public void setE6(List<String> e6) {
        this.e6 = e6;
    }

    public List<String> getE7() {
        return e7;
    }

    public void setE7(List<String> e7) {
        this.e7 = e7;
    }

    public List<String> getE8() {
        return e8;
    }

    public void setE8(List<String> e8) {
        this.e8 = e8;
    }

    public List<String> getE9() {
        return e9;
    }

    public void setE9(List<String> e9) {
        this.e9 = e9;
    }

    @Override
    public String toString() {
        return "EndResult{" +
                "e0=" + e0 +
                ", e1=" + e1 +
                ", e2=" + e2 +
                ", e3=" + e3 +
                ", e4=" + e4 +
                ", e5=" + e5 +
                ", e6=" + e6 +
                ", e7=" + e7 +
                ", e8=" + e8 +
                ", e9=" + e9 +
                '}';
    }
}
