package com.xProject.XosoVIP.xoso.presenter.fragment;

import com.xProject.XosoVIP.xoso.model.entity.GridMenu;
import com.xProject.XosoVIP.xoso.view.fragment.inf.IAnalyticsSpeed;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 10/4/2017.
 */

public class FragmentAnalyticsSpeedPresenter {
    private IAnalyticsSpeed view;

    public FragmentAnalyticsSpeedPresenter(IAnalyticsSpeed view) {
        this.view = view;
    }

    public void initListMenu() {
        List<GridMenu> menus = new ArrayList<>();
        menus.add(0, new GridMenu(R.mipmap.ic_thongke_nhanh, "TK nhanh"));
        menus.add(1, new GridMenu(R.mipmap.ic_thongke_tong, "TK theo tổng"));
        menus.add(2, new GridMenu(R.mipmap.ic_tanxuat, "Tần suất bộ số"));
        menus.add(3, new GridMenu(R.mipmap.ic_thongke_quantrong, "TK quan trọng"));
        menus.add(4, new GridMenu(R.mipmap.ic_thongke_tonghop, "TK tổng hợp"));
        menus.add(5, new GridMenu(R.mipmap.ic_loxien_auto, "Lô xiên tự động"));
        menus.add(6, new GridMenu(R.mipmap.ic_thongke_theongay, "TK theo ngày"));
        view.initGridMenu(menus);
    }
}
