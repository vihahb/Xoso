package com.xProject.XosoVIP.xoso.view.fragment.inf;

import com.xProject.XosoVIP.xoso.model.entity.ResultLottery;
import com.xProject.XosoVIP.xoso.model.respond.RESP_LiveLoto;
import com.xProject.XosoVIP.xoso.model.respond.RESP_NewResult;
import com.xProject.XosoVIP.xoso.model.respond.RESP_Result;
import com.xProject.XosoVIP.xoso.view.activity.inf.BasicView;

/**
 * Created by vivhp on 9/12/2017.
 */

public interface IFragmentCentralContent extends BasicView {
    void getResultLotteryError(String error);

    void setTableRegion1(ResultLottery data);

    void setTableRegion2(ResultLottery data);

    void setTableRegion3(ResultLottery data);

    void setDataSocket(RESP_Result resp_result);

    void setEndLive();

    void setNewResult(RESP_NewResult newResult, int position_table);

    void setTable3Hidden();

    void setTable2Hidden();

    void setVisibleTable(boolean isVisible);

    void clearOldData();

    void random(int random_table);

    void setLiveLoto(RESP_LiveLoto liveLoto, int positionTable);
}
