package com.xProject.XosoVIP.xoso.view.fragment.inf;

import com.xProject.XosoVIP.xoso.model.entity.CurentResult;
import com.xProject.XosoVIP.xoso.model.entity.ResultLottery;
import com.xProject.XosoVIP.xoso.model.respond.RESP_LiveLoto;
import com.xProject.XosoVIP.xoso.model.respond.RESP_NewResult;
import com.xProject.XosoVIP.xoso.view.activity.inf.BasicView;

/**
 * Created by vivhp on 9/8/2017.
 */

public interface IFragmentNorthContentView extends BasicView {

    void getResultLotterySuccess(ResultLottery data);

    void getResultLotteryError(String error);

    void setDataSocket(CurentResult data);

    void setNewResult(RESP_NewResult newResult);

    void cleaOldData();

    void setLiveLoto(RESP_LiveLoto liveLoto);

    void setEndLive();

}
