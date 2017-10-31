package com.xProject.XosoVIP.xoso.view.activity.inf;

import com.xProject.XosoVIP.xoso.model.entity.ImportantEntity;

import java.util.List;

/**
 * Created by vivhp on 10/6/2017.
 */

public interface IAnalyticsImportantActivity extends BasicView {
    void getImportantSuccess(List<ImportantEntity> list);

    void getImportantError(String mes);
}
