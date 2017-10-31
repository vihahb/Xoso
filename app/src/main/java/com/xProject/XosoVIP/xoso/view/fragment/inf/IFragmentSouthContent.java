package com.xProject.XosoVIP.xoso.view.fragment.inf;

import com.xProject.XosoVIP.xoso.model.entity.ResultLottery;
import com.xProject.XosoVIP.xoso.model.respond.RESP_LiveLoto;
import com.xProject.XosoVIP.xoso.model.respond.RESP_NewResult;
import com.xProject.XosoVIP.xoso.model.respond.RESP_Result;
import com.xProject.XosoVIP.xoso.view.activity.inf.BasicView;

/**
 * Created by vivhp on 9/13/2017.
 */

public interface IFragmentSouthContent extends BasicView {

    void getResultLotteryError(String error);

    void setTableRegion1(ResultLottery data);

    void setTableRegion2(ResultLottery data);

    void setTableRegion3(ResultLottery data);

    void setTableRegion4(ResultLottery data);

    void setDataSocket(RESP_Result resp_result);

    void setTable4Hidden();

    void setTable3Hidden();

    void setTable2Hidden();

    void setVisibleTable(boolean isVisible);

    void random(int i);

    void setNewResult(RESP_NewResult newResult, int i);

    void setEndLive();

    void setLiveLoto(RESP_LiveLoto liveLoto, int table);
}
