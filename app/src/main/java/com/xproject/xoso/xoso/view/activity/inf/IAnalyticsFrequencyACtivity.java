package com.xproject.xoso.xoso.view.activity.inf;

import com.xproject.xoso.xoso.model.entity.FrequencyEntity;

import java.util.List;

/**
 * Created by vivhp on 10/6/2017.
 */

public interface IAnalyticsFrequencyACtivity extends BasicView {
    void getFrequencysuccess(List<FrequencyEntity> data);

    void getFrequencyError(String message);
}
