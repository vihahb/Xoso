package com.xProject.XosoVIP.xoso.model.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by xtel on 10/25/17.
 */

public class CunmenhEntity {
    @Expose
    private String nam;
    @Expose
    private String nu;

    public CunmenhEntity(String nam, String nu) {
        this.nam = nam;
        this.nu = nu;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    @Override
    public String toString() {
        return "CunmenhEntity{" +
                "nam='" + nam + '\'' +
                ", nu='" + nu + '\'' +
                '}';
    }
}
