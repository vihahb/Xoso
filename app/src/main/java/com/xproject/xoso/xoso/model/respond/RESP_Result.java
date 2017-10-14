package com.xproject.xoso.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xproject.xoso.xoso.model.entity.CurentResult;

import java.util.List;

/**
 * Created by vivhp on 9/11/2017.
 */

public class RESP_Result {
    @Expose
    private String success;
    @Expose
    private List<CurentResult> data;
    @Expose
    private String message;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<CurentResult> getData() {
        return data;
    }

    public void setData(List<CurentResult> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RESP_Result{" +
                "success='" + success + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
