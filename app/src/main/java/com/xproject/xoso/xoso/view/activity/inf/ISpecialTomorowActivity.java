package com.xproject.xoso.xoso.view.activity.inf;

import com.xproject.xoso.xoso.model.entity.SpecialTomorowEntity;

/**
 * Created by vivhp on 10/12/2017.
 */

public interface ISpecialTomorowActivity extends BasicView{
    void getSpecialTomorow(SpecialTomorowEntity data);
    void getSpecialTomorowError(String mes);

}
