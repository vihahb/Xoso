package com.xProject.XosoVIP.xoso.model.entity;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by vivhp on 9/11/2017.
 */

public class ResultLottery {

    @Expose
    private String area;
    @Expose
    private int cat_id;
    @Expose
    private List<String> res_special;
    @Expose
    private List<String> res_first;
    @Expose
    private List<String> res_second;
    @Expose
    private List<String> res_third;
    @Expose
    private List<String> res_fourth;
    @Expose
    private List<String> res_fifth;
    @Expose
    private List<String> res_sixth;
    @Expose
    private List<String> res_seventh;
    @Expose
    private List<String> res_eight;
    @Expose
    private List<String> loto;
    @Expose
    private BeginResult begin_with;
    @Expose
    private EndResult end_with;


    public ResultLottery() {
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public List<String> getRes_special() {
        return res_special;
    }

    public void setRes_special(List<String> res_special) {
        this.res_special = res_special;
    }

    public List<String> getRes_first() {
        return res_first;
    }

    public void setRes_first(List<String> res_first) {
        this.res_first = res_first;
    }

    public List<String> getRes_second() {
        return res_second;
    }

    public void setRes_second(List<String> res_second) {
        this.res_second = res_second;
    }

    public List<String> getRes_third() {
        return res_third;
    }

    public void setRes_third(List<String> res_third) {
        this.res_third = res_third;
    }

    public List<String> getRes_fourth() {
        return res_fourth;
    }

    public void setRes_fourth(List<String> res_fourth) {
        this.res_fourth = res_fourth;
    }

    public List<String> getRes_fifth() {
        return res_fifth;
    }

    public void setRes_fifth(List<String> res_fifth) {
        this.res_fifth = res_fifth;
    }

    public List<String> getRes_sixth() {
        return res_sixth;
    }

    public void setRes_sixth(List<String> res_sixth) {
        this.res_sixth = res_sixth;
    }

    public List<String> getRes_seventh() {
        return res_seventh;
    }

    public void setRes_seventh(List<String> res_seventh) {
        this.res_seventh = res_seventh;
    }

    public List<String> getRes_eight() {
        return res_eight;
    }

    public void setRes_eight(List<String> res_eight) {
        this.res_eight = res_eight;
    }

    public List<String> getLoto() {
        return loto;
    }

    public void setLoto(List<String> loto) {
        this.loto = loto;
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

    @Override
    public String toString() {
        return "ResultLottery{" +
                "area='" + area + '\'' +
                ", cat_id=" + cat_id +
                ", res_special=" + res_special +
                ", res_first=" + res_first +
                ", res_second=" + res_second +
                ", res_third=" + res_third +
                ", res_fourth=" + res_fourth +
                ", res_fifth=" + res_fifth +
                ", res_sixth=" + res_sixth +
                ", res_seventh=" + res_seventh +
                ", res_eight=" + res_eight +
                ", loto=" + loto +
                ", begin_with=" + begin_with +
                ", end_with=" + end_with +
                '}';
    }
}
