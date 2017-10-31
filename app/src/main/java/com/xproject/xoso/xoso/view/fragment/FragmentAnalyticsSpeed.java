package com.xproject.xoso.xoso.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xproject.xoso.xoso.model.entity.GridMenu;
import com.xproject.xoso.xoso.presenter.fragment.FragmentAnalyticsSpeedPresenter;
import com.xproject.xoso.xoso.view.activity.AnalyticsDayActivity;
import com.xproject.xoso.xoso.view.activity.AnalyticsFrequencyActivity;
import com.xproject.xoso.xoso.view.activity.AnalyticsImportantActivity;
import com.xproject.xoso.xoso.view.activity.AnalyticsLotoAuto;
import com.xproject.xoso.xoso.view.activity.AnalyticsSpeedActivity;
import com.xproject.xoso.xoso.view.activity.AnalyticsSum;
import com.xproject.xoso.xoso.view.activity.AnalyticsSynthetic;
import com.xproject.xoso.xoso.view.adapter.AdapterGridMenu;
import com.xproject.xoso.xoso.view.adapter.inf.ViewGrid;
import com.xproject.xoso.xoso.view.fragment.inf.IAnalyticsSpeed;
import com.xproject.xoso.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 9/7/2017.
 */

public class FragmentAnalyticsSpeed extends BasicFragment implements ViewGrid, IAnalyticsSpeed {

    private List<GridMenu> menuList;
    private AdapterGridMenu adapterGridMenu;
    private RecyclerView rcl_analytics_normal;
    private FragmentAnalyticsSpeedPresenter presenter;

    public FragmentAnalyticsSpeed() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_analytics_speed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FragmentAnalyticsSpeedPresenter(this);
        initWidGet(view);
    }

    private void initWidGet(View view) {
        menuList = new ArrayList<>();
        rcl_analytics_normal = (RecyclerView) view.findViewById(R.id.rcl_analytics_speed);
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
                startActivity(AnalyticsSpeedActivity.class);
                break;
            case 1:
                startActivity(AnalyticsSum.class);
                break;
            case 2:
                startActivity(AnalyticsFrequencyActivity.class);
                break;
            case 3:
                startActivity(AnalyticsImportantActivity.class);
                break;
            case 4:
                startActivity(AnalyticsSynthetic.class);
                break;
            case 5:
                startActivity(AnalyticsLotoAuto.class);
                break;
            case 6:
                startActivity(AnalyticsDayActivity.class);
                break;
        }
    }

    @Override
    public void initGridMenu(List<GridMenu> menus) {
        adapterGridMenu.refreshData(menus);
    }
}
