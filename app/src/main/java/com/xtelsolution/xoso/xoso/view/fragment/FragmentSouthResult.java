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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by vivhp on 9/7/2017.
 */

public class FragmentSouthResult extends BasicFragment {

    private static final String TAG = "FragmentSouthResult";
    private MyPagerAdapter adapterViewPager;
    private static Context mContext;
    private PagerTabStrip pagerTabStrip;
    private ViewPager vpPager;

    public static FragmentSouthResult newInstance() {

        FragmentSouthResult fragment = new FragmentSouthResult();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_south_result, container, false);
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
        vpPager = view.findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setPageTransformer(false, new PageTransformer());
        vpPager.setAdapter(adapterViewPager);
        // set pager to current date
        // set pager to current date
        if (TimeUtils.checkTimeInMilisecondNorth(17, 12, 23, 58)){
            vpPager.setCurrentItem(TimeUtils.getPositionForDay(Calendar.getInstance()));
        }else {
            vpPager.setCurrentItem(TimeUtils.getPositionForDay(Calendar.getInstance())-1);
        }
    }

    public void queryResult(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date queryDate = null;
        try {
            queryDate = format.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(queryDate);
            vpPager.setCurrentItem(TimeUtils.getPositionForDay(calendar));
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e(TAG, "queryResult: " + e.toString());
        }
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
            return FragmentSouthContent.newInstance(timeForPosition);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Calendar cal = TimeUtils.getDayForPosition(position);
            return TimeUtils.getTitleTime(mContext,cal.getTimeInMillis());
        }
    }
}
