package com.xProject.XosoVIP.xoso.view.activity.inf;

import com.xProject.XosoVIP.xoso.model.respond.RESP_CycleLotoVip;

/**
 * Created by vivhp on 10/11/2017.
 */

public interface IActivityCycleListSpecial extends BasicView {

    void getListCycleSpecial(RESP_CycleLotoVip obj);

    void getListCycleSpecialError(String mes);

}
