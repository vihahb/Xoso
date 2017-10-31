package com.xProject.XosoVIP.xoso.view.activity.inf;

import com.xProject.XosoVIP.xoso.model.entity.AnalyticsSetNumber;

import java.util.List;

/**
 * Created by vivhp on 10/9/2017.
 */

public interface IAnalyticsSynthetic extends BasicView {
    void getSynthetics(List<AnalyticsSetNumber> data);

    void getSyntheticsError(String message);
}
