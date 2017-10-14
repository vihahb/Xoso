package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vivhp on 10/12/2017.
 */

public class SpecialTomorowEntity implements Serializable {

    @Expose
    private String special;
    @Expose
    private String date;
    @Expose
    private List<SameDow_NextDayEntity> next_day;
    @Expose
    private List<Freq_statsEntity> freq_stats;
    @Expose
    private StatsEntity stats;
    @Expose
    private List<SameDow_NextDayEntity> same_dow;
    @Expose
    private List<SameDayEntity> same_day;

    public SpecialTomorowEntity() {
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<SameDow_NextDayEntity> getNext_day() {
        return next_day;
    }

    public void setNext_day(List<SameDow_NextDayEntity> next_day) {
        this.next_day = next_day;
    }

    public List<Freq_statsEntity> getFreq_stats() {
        return freq_stats;
    }

    public void setFreq_stats(List<Freq_statsEntity> freq_stats) {
        this.freq_stats = freq_stats;
    }

    public StatsEntity getStats() {
        return stats;
    }

    public void setStats(StatsEntity stats) {
        this.stats = stats;
    }

    public List<SameDow_NextDayEntity> getSame_dow() {
        return same_dow;
    }

    public void setSame_dow(List<SameDow_NextDayEntity> same_dow) {
        this.same_dow = same_dow;
    }

    public List<SameDayEntity> getSame_day() {
        return same_day;
    }

    public void setSame_day(List<SameDayEntity> same_day) {
        this.same_day = same_day;
    }

    @Override
    public String toString() {
        return "SpecialTomorowEntity{" +
                "special='" + special + '\'' +
                ", date='" + date + '\'' +
                ", next_day=" + next_day +
                ", freq_stats=" + freq_stats +
                ", stats=" + stats +
                ", same_dow=" + same_dow +
                ", same_day=" + same_day +
                '}';
    }
}
