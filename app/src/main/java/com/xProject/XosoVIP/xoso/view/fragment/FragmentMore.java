package com.xProject.XosoVIP.xoso.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xProject.XosoVIP.xoso.model.entity.GridMenu;
import com.xProject.XosoVIP.xoso.presenter.fragment.FragmentMorePresenter;
import com.xProject.XosoVIP.xoso.view.activity.CategoryOpenSpinActivity;
import com.xProject.XosoVIP.xoso.view.activity.DreamActivity;
import com.xProject.XosoVIP.xoso.view.activity.LuckyActivity;
import com.xProject.XosoVIP.xoso.view.activity.RandomSpinActivity;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterGridMenu;
import com.xProject.XosoVIP.xoso.view.adapter.inf.ViewGrid;
import com.xProject.XosoVIP.xoso.view.fragment.inf.IFragmentMore;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 9/6/2017.
 */

public class FragmentMore extends BasicFragment implements ViewGrid, IFragmentMore {

    private List<GridMenu> menuList;
    private AdapterGridMenu adapterGridMenu;
    private RecyclerView rcl_more;
    private FragmentMorePresenter presenter;

    public static FragmentMore newInstance() {
        FragmentMore fragment = new FragmentMore();
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
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FragmentMorePresenter(this);
        initWidGet(view);
    }

    private void initWidGet(View view) {
        menuList = new ArrayList<>();
        rcl_more = (RecyclerView) view.findViewById(R.id.rcl_more);
        adapterGridMenu = new AdapterGridMenu(menuList, getContext(), this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        layoutManager.setAutoMeasureEnabled(true);
        rcl_more.setLayoutManager(layoutManager);
        rcl_more.setAdapter(adapterGridMenu);
        presenter.initListMenu();
    }

    @Override
    public void onClickItem(int position) {
        switch (position) {
            case 0:
                startActivity(DreamActivity.class);
                break;
            case 1:
                startActivity(RandomSpinActivity.class);
                break;
            case 2:
                startActivity(LuckyActivity.class);
                break;
            case 3:
                startActivity(CategoryOpenSpinActivity.class);
//                String url = "http://xoso.la/index.php?route=webview/lichquaythuong";
//                startActivity(WebViewActivity.class, Constants.URL, url);
//                new FinestWebView.Builder(getActivity())
//                    .updateTitleFromHtml(true)
//                    .webViewJavaScriptEnabled(true)
//                    .webViewAllowContentAccess(true)
//                    .webViewLoadWithOverviewMode(true)
//                    .webViewNeedInitialFocus(true)
//                    .showIconBack(true)
//                    .show("http://xoso.la/index.php?route=webview/lichquaythuong");
                break;
        }
    }

    @Override
    public void initGridMenu(List<GridMenu> menus) {
        adapterGridMenu.refreshData(menus);
    }
}
