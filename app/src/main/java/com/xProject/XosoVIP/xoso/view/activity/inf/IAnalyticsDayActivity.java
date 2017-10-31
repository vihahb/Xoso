package com.xProject.XosoVIP.xoso.view.activity.inf;

import com.xProject.XosoVIP.xoso.model.entity.AnalyticsSetNumber;

import java.util.List;

/**
 * Created by vivhp on 10/9/2017.
 */

public interface IAnalyticsDayActivity extends BasicView {
    void getAnalyticsDay(List<AnalyticsSetNumber> data);

    void getDayError(String message);
}
