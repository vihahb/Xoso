package com.xproject.xoso.xoso.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xproject.xoso.R;

/**
 * Created by vivhp on 9/6/2017
 */

public class FragmentAnalytics extends BasicFragment {

    private ViewPager viewPager;
    private TabLayout tabAnalytics;

    public static FragmentAnalytics newInstance() {

        Bundle args = new Bundle();

        FragmentAnalytics fragment = new FragmentAnalytics();
        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_analytics, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWidGet(view);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
//            initWidGet(view);
        } else {
            cleanWidget();
        }
    }

    private void cleanWidget() {
        if (tabAnalytics != null)
            tabAnalytics = null;
    }

    private void initWidGet(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_analytics);
        tabAnalytics = (TabLayout) view.findViewById(R.id.tabAnalytics);
        FragmentManager manager = getActivity().getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager);
        viewPager.setAdapter(adapter);
        tabAnalytics.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabAnalytics));
//        tabAnalytics.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cleanWidget();
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment frag = null;
            switch (position) {
                case 0:
                    frag = new FragmentAnalyticsVip();
                    break;
                case 1:
                    frag = new FragmentAnalyticsSpeed();
                    break;
            }
            return frag;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            switch (position) {
                case 0:
                    title = "Thống kê VIP";
                    break;
                case 1:
                    title = "Thống kê nhanh";
                    break;
            }

            return title;
        }
    }
}
