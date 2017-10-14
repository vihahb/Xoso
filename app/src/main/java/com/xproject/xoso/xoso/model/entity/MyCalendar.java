package com.xproject.xoso.xoso.model.entity;

/**
 * Created by vivhp on 9/1/2017.
 */

public class MyCalendar {

    private String dateLabel;
    private String dateValue;
    private String monthValue;
    private String monthLabel;
    private String yearValue;
    private boolean selectPosition = false;

    public MyCalendar() {
    }

    public MyCalendar(String dateLabel, String dateValue, String monthValue, String monthLabel, String yearValue, boolean selectPosition) {
        this.dateLabel = dateLabel;
        this.dateValue = dateValue;
        this.monthValue = monthValue;
        this.monthLabel = monthLabel;
        this.yearValue = yearValue;
        this.selectPosition = selectPosition;
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(String monthValue) {
        this.monthValue = monthValue;
    }

    public String getMonthLabel() {
        return monthLabel;
    }

    public void setMonthLabel(String monthLabel) {
        this.monthLabel = monthLabel;
    }

    public String getYearValue() {
        return yearValue;
    }

    public void setYearValue(String yearValue) {
        this.yearValue = yearValue;
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
                ", dateValue='" + dateValue + '\'' +
                ", monthValue='" + monthValue + '\'' +
                ", monthLabel='" + monthLabel + '\'' +
                ", yearValue='" + yearValue + '\'' +
                ", selectPosition=" + selectPosition +
                '}';
    }
}
