package com.xproject.xoso.xoso.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.xoso.model.entity.GridMenu;
import com.xproject.xoso.xoso.presenter.fragment.FragmentExplorePresenter;
import com.xproject.xoso.xoso.view.activity.ActivityExploreBridgeLoto;
import com.xproject.xoso.xoso.view.activity.BridHistorygeActivity;
import com.xproject.xoso.xoso.view.activity.BridgeForDayActivity;
import com.xproject.xoso.xoso.view.adapter.AdapterGridMenu;
import com.xproject.xoso.xoso.view.adapter.inf.ViewGrid;
import com.xproject.xoso.xoso.view.fragment.inf.IFragmentExplore;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 9/6/2017.
 */

public class FragmentExplore extends BasicFragment implements IFragmentExplore, ViewGrid {

    private List<GridMenu> menuList;
    private AdapterGridMenu adapterGridMenu;
    private RecyclerView rcl_explore;
    private FragmentExplorePresenter presenter;

    public static FragmentExplore newInstance() {
        FragmentExplore fragment = new FragmentExplore();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FragmentExplorePresenter(this);
        initWidGet(view);
    }

    private void initWidGet(View view) {
        menuList = new ArrayList<>();
        rcl_explore = (RecyclerView) view.findViewById(R.id.rcl_explore);
        adapterGridMenu = new AdapterGridMenu(menuList, getContext(), this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        layoutManager.setAutoMeasureEnabled(true);
        rcl_explore.setLayoutManager(layoutManager);
        rcl_explore.setAdapter(adapterGridMenu);
        presenter.initListMenu();
    }

    @Override
    public void initGridMenu(List<GridMenu> menus) {
        adapterGridMenu.refreshData(menus);
    }

    @Override
    public void onClickItem(int position) {
        switch (position) {
            case 0:
                startActivity(ActivityExploreBridgeLoto.class, Constants.ACTION_TYPE, 1);
                break;
            case 1:
                startActivity(ActivityExploreBridgeLoto.class, Constants.ACTION_TYPE, 2);
                break;
            case 2:
                startActivity(ActivityExploreBridgeLoto.class, Constants.ACTION_TYPE, 3);
                break;
            case 3:
                startActivity(ActivityExploreBridgeLoto.class, Constants.ACTION_TYPE, 4);
                break;
            case 4:
                startActivity(ActivityExploreBridgeLoto.class, Constants.ACTION_TYPE, 5);
                break;
            case 5:
                startActivity(ActivityExploreBridgeLoto.class, Constants.ACTION_TYPE, 6);
                break;
            case 6:
                startActivity(BridgeForDayActivity.class, Constants.ACTION_TYPE, 1);
                break;
            case 7:
                startActivity(BridgeForDayActivity.class, Constants.ACTION_TYPE, 2);
                break;

            case 8:
                startActivity(BridHistorygeActivity.class);
                break;
        }
    }
}
