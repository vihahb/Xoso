package com.xProject.XosoVIP.xoso.presenter.activity;

import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.xoso.model.entity.Dream;
import com.xProject.XosoVIP.xoso.view.activity.inf.IDream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 9/9/2017.
 */

public class DreamPresenter {

    private IDream view;

    public DreamPresenter(IDream view) {
        this.view = view;
    }

    public void getDreamList() {
        List<Dream> list = new ArrayList<>();
        list = DatabaseHelper.getInstance().getListOfObjects(Dream.class);
        view.getDreamSuccess(list);
    }

    public void findProductByName(String name) {
        List<Dream> dreamList = new ArrayList<>();
        dreamList = DatabaseHelper.getInstance().findObjectByName(Dream.class, "dreamed", name);
        view.getDreamFind(dreamList);
    }

}
