package com.xProject.XosoVIP.xoso.view.activity.inf;

import com.xProject.XosoVIP.xoso.model.entity.RESP_LuckyEntity;

/**
 * Created by xtel on 10/25/17.
 */

public interface ILuckyActivityView extends BasicView {
    void getLuckySuccess(RESP_LuckyEntity luckyEntity);
    void getLuckyError(String mes);
}
