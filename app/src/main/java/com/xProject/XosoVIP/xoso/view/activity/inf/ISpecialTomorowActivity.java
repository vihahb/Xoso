package com.xProject.XosoVIP.xoso.view.activity.inf;

import com.xProject.XosoVIP.xoso.model.entity.SpecialTomorowEntity;

/**
 * Created by vivhp on 10/12/2017.
 */

public interface ISpecialTomorowActivity extends BasicView {
    void getSpecialTomorow(SpecialTomorowEntity data);

    void getSpecialTomorowError(String mes);

}
