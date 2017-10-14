package com.xproject.xoso.xoso.presenter.fragment;

import com.xtelsolution.xoso.R;
import com.xproject.xoso.xoso.model.entity.GridMenu;
import com.xproject.xoso.xoso.view.fragment.inf.IFragmentExplore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 9/13/2017.
 */

public class FragmentExplorePresenter {
    private IFragmentExplore view;

    public FragmentExplorePresenter(IFragmentExplore view) {
        this.view = view;
    }

    public void initListMenu(){
        List<GridMenu> menus = new ArrayList<>();
        menus.add(0, new GridMenu(R.mipmap.ic_soi_cau_loto, "Soi cầu lô tô"));
        menus.add(0, new GridMenu(R.mipmap.ic_cau_loai_loto, "Cầu loại lô tô"));
        menus.add(0, new GridMenu(R.mipmap.ic_soi_cau_bach_thu, "Cầu bạch thủ"));
        menus.add(0, new GridMenu(R.mipmap.ic_cau_loai_bach_thu, "Cầu loại bạch thủ"));
        menus.add(0, new GridMenu(R.mipmap.ic_cau_giai_dac_biet, "Cầu giải đặc biệt"));
        menus.add(0, new GridMenu(R.mipmap.ic_cau_hai_nhay, "Cầu ăn hai nháy"));
        menus.add(0, new GridMenu(R.mipmap.ic_cau_theo_thu, "Cầu theo thứ"));
        menus.add(0, new GridMenu(R.mipmap.ic_cau_dac_biet_theo_thu, "Cầu ĐB theo thứ"));
        menus.add(0, new GridMenu(R.mipmap.ic_lich_su_cau, "Lịch sử cầu lô tô"));
        view.initGridMenu(menus);
    }
}
