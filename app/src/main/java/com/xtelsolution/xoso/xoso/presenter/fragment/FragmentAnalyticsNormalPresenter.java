package com.xtelsolution.xoso.xoso.presenter.fragment;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.xoso.model.entity.GridMenu;
import com.xtelsolution.xoso.xoso.view.fragment.inf.IAnalyticsNormal;

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
        menus.add(0, new GridMenu(R.mipmap.ic_analy_cycle, "Chu kỳ lô tô"));
        menus.add(0, new GridMenu(R.mipmap.ic_analy_special, "Chu kỳ đặc biệt"));
        menus.add(0, new GridMenu(R.mipmap.ic_analy_frequency_loto, "Tần số nhịp lô tô"));
        menus.add(0, new GridMenu(R.mipmap.ic_analy_cycle_loto, "Chu kỳ dàn lô tô"));
        menus.add(0, new GridMenu(R.mipmap.ic_analy_special, "Dàn đặc biệt"));
        menus.add(0, new GridMenu(R.mipmap.ic_analy_special_tomorrow, "Đặc biệt ngày mai"));
        menus.add(0, new GridMenu(R.mipmap.ic_analy_cycle_max, "Chu kỳ max"));
        view.initGridMenu(menus);
    }
}
