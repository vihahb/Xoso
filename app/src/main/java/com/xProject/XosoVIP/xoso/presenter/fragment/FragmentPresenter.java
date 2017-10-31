package com.xProject.XosoVIP.xoso.presenter.fragment;

import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.view.fragment.inf.FragmentView;

import java.util.List;

/**
 * Created by xtel on 10/31/17.
 */

public class FragmentPresenter {
    private FragmentView view;

    public FragmentPresenter(FragmentView view) {
        this.view = view;
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }
}
