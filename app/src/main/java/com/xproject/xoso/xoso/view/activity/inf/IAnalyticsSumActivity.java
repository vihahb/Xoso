package com.xproject.xoso.xoso.view.activity.inf;

import com.xproject.xoso.xoso.model.entity.AnalyticsSetNumber;

import java.util.List;

/**
 * Created by vivhp on 10/5/2017.
 */

public interface IAnalyticsSumActivity extends BasicView{
    void getSumAnalyticsSuccess(List<AnalyticsSetNumber> listSetNumber);

    void getSumError(String message);
}
