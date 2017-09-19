package com.xtelsolution.xoso.xoso.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.sdk.utils.TimeUtils;
import com.xtelsolution.xoso.xoso.view.adapter.CachingFragmentStatePagerAdapter;
import com.xtelsolution.xoso.xoso.view.widget.PageTransformer;

import java.util.Calendar;

/**
 * Created by vivhp on 9/7/2017.
 */

public class FragmentNorthResult extends BasicFragment {

    private static final String TAG = "FragmentNorthResult";
    private MyPagerAdapter adapterViewPager;
    private PagerTabStrip pagerTabStrip;
    private static Context mContext;

    public static FragmentNorthResult newInstance() {
        FragmentNorthResult fragment = new FragmentNorthResult();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_north_result, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mContext = getContext();
        pagerTabStrip = view.findViewById(R.id.pager_header);
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setTabIndicatorColor(getContext().getResources().getColor(R.color.blue));
        pagerTabStrip.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        final ViewPager vpPager = view.findViewById(R.id.vpPager);
        vpPager.setPageTransformer(false, new PageTransformer());
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        // set pager to current date

        if (TimeUtils.checkTimeInMilisecondNorth(18, 12, 23, 58)){
            vpPager.setCurrentItem(TimeUtils.getPositionForDay(Calendar.getInstance()));
        }else {
            vpPager.setCurrentItem(TimeUtils.getPositionForDay(Calendar.getInstance())-1);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //you are visible to user now - so set whatever you need
            initResources();
        }
        else {
            //you are no longer visible to the user so cleanup whatever you need
            cleanupResources();
        }
    }

    private void initResources() {

    }

    private void cleanupResources() {
    }

    public static class MyPagerAdapter extends CachingFragmentStatePagerAdapter {

        private Calendar cal;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return TimeUtils.DAYS_OF_TIME;
        }

        @Override
        public Fragment getItem(int position) {
            Log.e(TAG, "getItem: " + position);
            long timeForPosition = TimeUtils.getDayForPosition(position).getTimeInMillis();
            return FragmentNorthContent.newInstance(timeForPosition);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Calendar cal = TimeUtils.getDayForPosition(position);
            return TimeUtils.getFormattedDate(mContext,cal.getTimeInMillis());
        }
    }

}
