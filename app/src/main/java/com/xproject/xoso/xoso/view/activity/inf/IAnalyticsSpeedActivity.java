package com.xproject.xoso.xoso.view.activity.inf;

import com.xproject.xoso.xoso.model.entity.AnalyticsSetNumber;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;

import java.util.List;

/**
 * Created by vivhp on 10/4/2017.
 */

public interface IAnalyticsSpeedActivity extends BasicView {
    void geetSpeedAnalyticsSuccess(List<AnalyticsSetNumber> listSetNumber);

    void getSpeedError(String message);
}
