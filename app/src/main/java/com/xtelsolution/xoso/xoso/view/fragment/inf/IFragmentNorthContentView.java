package com.xtelsolution.xoso.xoso.view.fragment.inf;

import com.xtelsolution.xoso.xoso.model.entity.ResultLottery;
import com.xtelsolution.xoso.xoso.model.respond.RESP_NewResult;
import com.xtelsolution.xoso.xoso.model.respond.RESP_Result;
import com.xtelsolution.xoso.xoso.view.activity.inf.BasicView;

import java.util.List;

/**
 * Created by vivhp on 9/8/2017.
 */

public interface IFragmentNorthContentView extends BasicView{

    void getResultLotterySuccess(ResultLottery data);
    void getResultLotteryError(String error);

    void setDataSocket(RESP_Result resp_result);

    void setNewResult(RESP_NewResult newResult);

    void cleaOldData();
}
