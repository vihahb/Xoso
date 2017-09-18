package com.xtelsolution.xoso.xoso.model.entity;

/**
 * Created by vivhp on 9/1/2017.
 */

public class MyCalendar {

    private String dateLabel;
    private int dateValue;
    private boolean selectPosition = false;

    public MyCalendar() {
    }

    public MyCalendar(String dateLabel, int dateValue, boolean selectPosition) {
        this.dateLabel = dateLabel;
        this.dateValue = dateValue;
        this.selectPosition = selectPosition;
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    public int getDateValue() {
        return dateValue;
    }

    public void setDateValue(int dateValue) {
        this.dateValue = dateValue;
    }

    public boolean isSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(boolean selectPosition) {
        this.selectPosition = selectPosition;
    }

    @Override
    public String toString() {
        return "MyCalendar{" +
                "dateLabel='" + dateLabel + '\'' +
                ", dateValue=" + dateValue +
                ", selectPosition=" + selectPosition +
                '}';
    }
}
