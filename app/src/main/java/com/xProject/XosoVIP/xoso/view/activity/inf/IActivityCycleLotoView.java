package com.xProject.XosoVIP.xoso.view.activity.inf;

import com.xProject.XosoVIP.xoso.model.entity.CycleLotoEntity;

import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public interface IActivityCycleLotoView extends BasicView {
    void getCycleLotoSuccess(List<CycleLotoEntity> data);

    void getCycleLotoError(String message);
}
