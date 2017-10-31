package com.xProject.XosoVIP.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xProject.XosoVIP.xoso.model.entity.BeginResult;
import com.xProject.XosoVIP.xoso.model.entity.EndResult;

import java.util.List;

/**
 * Created by vivhp on 10/11/2017.
 */

public class RESP_LiveLoto extends RESP_Basic {

    @Expose
    private String cat_id;
    @Expose
    private List<String> loto;
    @Expose
    private BeginResult begin_with;
    @Expose
    private EndResult end_with;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
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
        return "RESP_LiveLoto{" +
                "cat_id=" + cat_id +
                ", loto=" + loto +
                ", begin_with=" + begin_with +
                ", end_with=" + end_with +
                '}';
    }
}
