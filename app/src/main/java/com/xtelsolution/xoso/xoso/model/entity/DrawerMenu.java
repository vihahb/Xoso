package com.xtelsolution.xoso.xoso.model.entity;

/**
 * Created by vivhp on 9/1/2017.
 */

public class DrawerMenu {

    private int Type;
    private String ItemName;
    private int resource;
    private String ItemTime;

    public DrawerMenu() {
    }

    public DrawerMenu(int type, String itemName, int resource, String itemTime) {
        Type = type;
        ItemName = itemName;
        this.resource = resource;
        this.ItemTime = itemTime;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public String getItemTime() {
        return ItemTime;
    }

    public void setItemTime(String itemTime) {
        ItemTime = itemTime;
    }

    @Override
    public String toString() {
        return "DrawerMenu{" +
                "Type=" + Type +
                ", ItemName='" + ItemName + '\'' +
                ", resource=" + resource +
                ", ItemTime='" + ItemTime + '\'' +
                '}';
    }
}
