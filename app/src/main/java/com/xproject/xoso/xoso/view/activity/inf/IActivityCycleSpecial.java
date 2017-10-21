package com.xproject.xoso.xoso.view.activity.inf;

import com.xproject.xoso.xoso.model.respond.RESP_CycleSpecial;

/**
 * Created by vivhp on 10/10/2017.
 */

public interface IActivityCycleSpecial extends BasicView {

    void getCycleSpecial(RESP_CycleSpecial entity);

    void getCycleSpecialError(String mes);

}
