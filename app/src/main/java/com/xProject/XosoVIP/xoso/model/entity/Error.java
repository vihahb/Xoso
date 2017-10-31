package com.xProject.XosoVIP.xoso.model.entity;

import com.google.gson.annotations.Expose;

public class Error {

    @Expose
    private String message;

    public Error() {
    }

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "message='" + message + '\'' +
                '}';
    }
}