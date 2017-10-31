package com.xProject.XosoVIP.xoso.model.entity;

import com.google.gson.annotations.Expose;

/**
 * Created by vivhp on 9/14/2017.
 */

public class MessageEvent {
    @Expose
    private String eventName;

    public MessageEvent(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "eventName='" + eventName + '\'' +
                '}';
    }
}
