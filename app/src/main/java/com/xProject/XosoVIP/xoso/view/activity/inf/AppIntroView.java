package com.xProject.XosoVIP.xoso.view.activity.inf;

import android.app.Activity;

import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;

import java.util.List;

/**
 * Created by xtel on 10/31/17.
 */

public interface AppIntroView  {
    void getProvinceListSuccess(List<ProvinceEntity> provinceEntityList);
    Activity getActivity();

    void getProvinceListError();
}
