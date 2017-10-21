package com.xproject.xoso.xoso.view.activity.inf;

import com.xproject.xoso.xoso.model.respond.RESP_FrequencyLoto;

/**
 * Created by vivhp on 10/10/2017.
 */

public interface IFrequencyLotoActivity extends BasicView {
    void getFrequencyLoto(RESP_FrequencyLoto obj);

    void getFrequencyLotoError(String message);
}
