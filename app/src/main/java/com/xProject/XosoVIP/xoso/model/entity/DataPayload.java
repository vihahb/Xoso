package com.xProject.XosoVIP.xoso.model.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by vivhp on 10/16/2017.
 */

public class DataPayload {
    @Expose
    private String title;
    @Expose
    private String body;
    @Expose
    private int action;

    public DataPayload() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "DataPayload{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", action=" + action +
                '}';
    }
}
