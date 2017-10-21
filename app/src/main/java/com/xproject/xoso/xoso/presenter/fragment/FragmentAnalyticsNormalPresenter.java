package com.xproject.xoso.xoso.presenter.fragment;

import com.xproject.xoso.xoso.model.entity.GridMenu;
import com.xproject.xoso.xoso.view.fragment.inf.IAnalyticsNormal;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 9/7/2017.
 */

public class FragmentAnalyticsNormalPresenter {

    private IAnalyticsNormal view;

    public FragmentAnalyticsNormalPresenter(IAnalyticsNormal view) {
        this.view = view;
    }

    public void initListMenu() {
        List<GridMenu> menus = new ArrayList<>();
        menus.add(0, new GridMenu(R.mipmap.ic_loto_cycle, "Chu kỳ lô tô"));
        menus.add(1, new GridMenu(R.mipmap.ic_cycle_special, "Chu kỳ đặc biệt"));
        menus.add(2, new GridMenu(R.mipmap.ic_analy_frequency_loto, "Tần số nhịp lô tô"));
        menus.add(3, new GridMenu(R.mipmap.ic_analy_cycle_loto, "Dàn lô tô"));
        menus.add(4, new GridMenu(R.mipmap.ic_analy_special, "Dàn đặc biệt"));
        menus.add(5, new GridMenu(R.mipmap.ic_analy_special_tomorrow, "ĐB ngày mai"));
        view.initGridMenu(menus);
    }
}
