package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by vivhp on 10/12/2017.
 */

public class StatsEntity implements Serializable{

    @Expose
    private BeginResult begin_with;
    @Expose
    private EndResult end_with;
    @Expose
    private Sum_equal sum_equal;

    public StatsEntity() {
    }

    public BeginResult getBegin_with() {
        return begin_with;
    }

    public void setBegin_with(BeginResult begin_with) {
        this.begin_with = begin_with;
    }

    public EndResult getEnd_with() {
        return end_with;
    }

    public void setEnd_with(EndResult end_with) {
        this.end_with = end_with;
    }

    public Sum_equal getSum_equal() {
        return sum_equal;
    }

    public void setSum_equal(Sum_equal sum_equal) {
        this.sum_equal = sum_equal;
    }

    @Override
    public String toString() {
        return "StatsEntity{" +
                "begin_with=" + begin_with +
                ", end_with=" + end_with +
                ", sum_equal=" + sum_equal +
                '}';
    }
}
