package com.xProject.XosoVIP.xoso.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.xoso.model.entity.GridMenu;
import com.xProject.XosoVIP.xoso.presenter.fragment.FragmentAnalyticsNormalPresenter;
import com.xProject.XosoVIP.xoso.view.activity.ActivityCycleSpecial;
import com.xProject.XosoVIP.xoso.view.activity.ActivityLotoCycle;
import com.xProject.XosoVIP.xoso.view.activity.CycleListSpecialActivity;
import com.xProject.XosoVIP.xoso.view.activity.FrequencyLotoActivity;
import com.xProject.XosoVIP.xoso.view.activity.SpecialTomorowActivity;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterGridMenu;
import com.xProject.XosoVIP.xoso.view.adapter.inf.ViewGrid;
import com.xProject.XosoVIP.xoso.view.fragment.inf.IAnalyticsNormal;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 9/7/2017
 */

public class FragmentAnalyticsVip extends BasicFragment implements ViewGrid, IAnalyticsNormal {

    private List<GridMenu> menuList;
    private AdapterGridMenu adapterGridMenu;
    private RecyclerView rcl_analytics_normal;
    private FragmentAnalyticsNormalPresenter presenter;

    public FragmentAnalyticsVip() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_analytics_nomal, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FragmentAnalyticsNormalPresenter(this);
        initWidGet(view);
    }

    private void initWidGet(View view) {
        menuList = new ArrayList<>();
        rcl_analytics_normal = (RecyclerView) view.findViewById(R.id.rcl_analytics_nomal);
        adapterGridMenu = new AdapterGridMenu(menuList, getContext(), this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        layoutManager.setAutoMeasureEnabled(true);
        rcl_analytics_normal.setLayoutManager(layoutManager);
        rcl_analytics_normal.setAdapter(adapterGridMenu);
        presenter.initListMenu();
    }

    @Override
    public void onClickItem(int position) {
        switch (position) {
            case 0:
                startActivity(ActivityLotoCycle.class);
                break;
            case 1:
                startActivity(ActivityCycleSpecial.class);
                break;
            case 2:
                startActivity(FrequencyLotoActivity.class);
                break;
            case 3:
                startActivity(CycleListSpecialActivity.class, Constants.ACTION_TYPE, 1);
                break;
            case 4:
                startActivity(CycleListSpecialActivity.class, Constants.ACTION_TYPE, 2);
                break;
            case 5:
                startActivity(SpecialTomorowActivity.class);
                break;
        }
    }

    @Override
    public void initGridMenu(List<GridMenu> menus) {
        adapterGridMenu.refreshData(menus);
    }
}
