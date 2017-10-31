package com.xProject.XosoVIP.xoso.model.entity;

/**
 * Created by vivhp on 9/7/2017.
 */

public class GridMenu {

    private int resource;

    private String name;

    public GridMenu() {
    }

    public GridMenu(int resource, String name) {
        this.resource = resource;
        this.name = name;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GridMenu{" +
                "resource=" + resource +
                ", name='" + name + '\'' +
                '}';
    }
}
