package com.xProject.XosoVIP.xoso.model.entity;

import com.google.gson.annotations.Expose;
import com.xProject.XosoVIP.xoso.model.respond.RESP_Basic;

import java.util.List;

/**
 * Created by xtel on 10/25/17.
 */

public class RESP_LuckyEntity extends RESP_Basic {
    @Expose
    private List<String> data;
    @Expose
    private CunmenhEntity cungmenh;
    @Expose
    private String canchi;
    @Expose
    private String amlich;
    @Expose
    private String canchithang;
    @Expose
    private String canchingay;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public CunmenhEntity getCungmenh() {
        return cungmenh;
    }

    public void setCungmenh(CunmenhEntity cungmenh) {
        this.cungmenh = cungmenh;
    }

    public String getCanchi() {
        return canchi;
    }

    public void setCanchi(String canchi) {
        this.canchi = canchi;
    }

    public String getAmlich() {
        return amlich;
    }

    public void setAmlich(String amlich) {
        this.amlich = amlich;
    }

    public String getCanchithang() {
        return canchithang;
    }

    public void setCanchithang(String canchithang) {
        this.canchithang = canchithang;
    }

    public String getCanchingay() {
        return canchingay;
    }

    public void setCanchingay(String canchingay) {
        this.canchingay = canchingay;
    }

    @Override
    public String toString() {
        return "RESP_LuckyEntity{" +
                "data=" + data +
                ", cungmenh=" + cungmenh +
                ", canchi='" + canchi + '\'' +
                ", amlich='" + amlich + '\'' +
                ", canchithang='" + canchithang + '\'' +
                '}';
    }
}
