package com.xtelsolution.xoso.xoso.model.respond;

import com.google.gson.annotations.Expose;
import com.xtelsolution.xoso.xoso.model.entity.ResultLottery;

import java.util.List;

/**
 * Created by vivhp on 9/11/2017.
 */

public class RESP_GetLottery extends RESP_Basic{

    @Expose
    private List<ResultLottery> data;

    public List<ResultLottery> getData() {
        return data;
    }

    public void setData(List<ResultLottery> data) {
        this.data = data;
    }
}
