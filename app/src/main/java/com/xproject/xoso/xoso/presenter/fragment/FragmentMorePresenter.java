package com.xproject.xoso.xoso.presenter.fragment;

import com.xproject.xoso.xoso.model.entity.GridMenu;
import com.xproject.xoso.xoso.view.fragment.inf.IFragmentMore;
import com.xproject.xoso.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 9/9/2017.
 */

public class FragmentMorePresenter {

    private IFragmentMore view;

    public FragmentMorePresenter(IFragmentMore view) {
        this.view = view;
    }

    public void initListMenu() {
        List<GridMenu> menus = new ArrayList<>();
        menus.add(0, new GridMenu(R.mipmap.ic_dream_list, "Sổ mơ"));
        menus.add(1, new GridMenu(R.mipmap.ic_random_generator, "Quay thử"));
        menus.add(2, new GridMenu(R.mipmap.ic_number_luck, "Số may mắn"));
        menus.add(3, new GridMenu(R.mipmap.ic_calendar_open, "Lịch quay thưởng"));
        view.initGridMenu(menus);
    }
}
