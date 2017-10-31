package com.xProject.XosoVIP.xoso.view.activity.inf;

import com.xProject.XosoVIP.xoso.model.entity.AnalyticsSetNumber;

import java.util.List;

/**
 * Created by vivhp on 10/4/2017.
 */

public interface IAnalyticsSpeedActivity extends BasicView {
    void geetSpeedAnalyticsSuccess(List<AnalyticsSetNumber> listSetNumber);

    void getSpeedError(String message);
}
