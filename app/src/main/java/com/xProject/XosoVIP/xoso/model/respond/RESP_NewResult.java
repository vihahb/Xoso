package com.xProject.XosoVIP.xoso.model.respond;

import com.google.gson.annotations.Expose;

/**
 * Created by vivhp on 9/15/2017.
 */

public class RESP_NewResult {

    @Expose
    private String result_name;
    @Expose
    private String cat_id;
    @Expose
    private String value;
    @Expose
    private String index;

    public RESP_NewResult() {
    }

    public RESP_NewResult(String result_name, String cat_id, String value, String index) {
        this.result_name = result_name;
        this.cat_id = cat_id;
        this.value = value;
        this.index = index;
    }

    public String getResult_name() {
        return result_name;
    }

    public void setResult_name(String result_name) {
        this.result_name = result_name;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "RESP_NewResult{" +
                "result_name='" + result_name + '\'' +
                ", cat_id='" + cat_id + '\'' +
                ", value='" + value + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
