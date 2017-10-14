package com.xproject.xoso.xoso.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.view.fragment.inf.OnLoadComplete;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vivhp on 9/7/2017.
 */

public class FragmentNorthResult extends BasicFragment implements OnLoadComplete {

    private static final String TAG = "FragmentNorthResult";
    private MyPagerAdapter adapterViewPager;
    private PagerTabStrip pagerTabStrip;
    private static Context mContext;
    private ViewPager vpPager;

    private List<Fragment> fragmentList;

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
        fragmentList = new ArrayList<>();
        for (int i = 0; i < TimeUtils.DAYS_OF_TIME; i++) {
            fragmentList.add(i, FragmentNorthContent.newInstance(TimeUtils.getDayForPosition(i).getTimeInMillis()));
        }
        mContext = getContext();
        pagerTabStrip = (PagerTabStrip) view.findViewById(R.id.pager_header);
        pagerTabStrip.setDrawFullUnderline(false);
        pagerTabStrip.setTabIndicatorColor(getContext().getResources().getColor(R.color.blue));
        pagerTabStrip.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
        vpPager = (ViewPager) view.findViewById(R.id.vpPager);
//        vpPager.setPageTransformer(false, new PageTransformer());
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager(), fragmentList);
        vpPager.setAdapter(adapterViewPager);
        // set pager to current date

        if (TimeUtils.checkTimeInMilisecondNorth(18, 15, 23, 58)) {
            vpPager.setCurrentItem(TimeUtils.getPositionForDay(Calendar.getInstance()));
        } else {
            vpPager.setCurrentItem(TimeUtils.getPositionForDay(Calendar.getInstance()) - 1);
        }
    }

    private boolean checkSelectedDay() {
        if (vpPager.getCurrentItem() == TimeUtils.getPositionForDay(Calendar.getInstance())) {
            return true;
        } else {
            return false;
        }
    }


    public void setLive() {
        if (!checkSelectedDay()) {
            int position = TimeUtils.getPositionForDay(Calendar.getInstance());
            ((FragmentNorthContent) adapterViewPager.getItem(position)).startLive();
            vpPager.setCurrentItem(position);
        } else {
            int position = TimeUtils.getPositionForDay(Calendar.getInstance());
            ((FragmentNorthContent) adapterViewPager.getItem(position)).startLive();
        }
    }

    public void queryResult(Calendar calendar) {
        vpPager.setCurrentItem(TimeUtils.getPositionForDay(calendar));
    }

    @Override
    public void onComplete() {
        setLive();
    }

    public static class MyPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> list;

        public MyPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
            super(fragmentManager);
            list = fragmentList;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {
//            Log.e(TAG, "getItem: " + position);
//            long timeForPosition = TimeUtils.getDayForPosition(position).getTimeInMillis();
//            return FragmentNorthContent.newInstance(timeForPosition);
            return list.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Calendar cal = TimeUtils.getDayForPosition(position);
            return TimeUtils.getTitleTime(mContext, cal.getTimeInMillis());
        }
    }

}
