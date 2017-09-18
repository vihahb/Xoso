package com.xtelsolution.xoso.xoso.model.respond;

import com.google.gson.annotations.Expose;

/**
 * Created by vivhp on 9/8/2017.
 */

public class RESP_Basic {
    @Expose
    private boolean success;
    @Expose
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
