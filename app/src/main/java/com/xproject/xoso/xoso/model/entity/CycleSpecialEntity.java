package com.xproject.xoso.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class CycleSpecialEntity implements Serializable {

    @Expose
    private List<SpecialCycleEntity> begin_with;
    @Expose
    private List<SpecialCycleEntity> end_with;
    @Expose
    private List<SpecialCycleEntity> sum_equal;

    public CycleSpecialEntity() {
    }

    public List<SpecialCycleEntity> getBegin_with() {
        return begin_with;
    }

    public void setBegin_with(List<SpecialCycleEntity> begin_with) {
        this.begin_with = begin_with;
    }

    public List<SpecialCycleEntity> getEnd_with() {
        return end_with;
    }

    public void setEnd_with(List<SpecialCycleEntity> end_with) {
        this.end_with = end_with;
    }

    public List<SpecialCycleEntity> getSum_equal() {
        return sum_equal;
    }

    public void setSum_equal(List<SpecialCycleEntity> sum_equal) {
        this.sum_equal = sum_equal;
    }

    @Override
    public String toString() {
        return "CycleSpecialEntity{" +
                "begin_with=" + begin_with +
                ", end_with=" + end_with +
                ", sum_equal=" + sum_equal +
                '}';
    }
}
