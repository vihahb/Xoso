package com.xproject.xoso.xoso.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xproject.xoso.sdk.utils.CalendarUtils;
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.MyCalendar;
import com.xproject.xoso.xoso.view.adapter.AdapterCalendar;
import com.xproject.xoso.xoso.view.fragment.inf.OnLoadComplete;
import com.xproject.xoso.xoso.view.widget.RecyclerTabLayout;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vivhp on 9/7/2017.
 */

public class FragmentNorthResult extends BasicFragment implements OnLoadComplete {

    private static final String TAG = "FragmentNorthResult";
    //    private PagerTabStrip pagerTabStrip;
    private static Context mContext;
    RecyclerTabLayout recyclerTabLayout;
    List<MyCalendar> calendarList;
    private MyPagerAdapter adapterViewPager;
    private ViewPager vpPager;
    private AdapterCalendar adapterCalendar;
    private List<Fragment> fragmentList;

    public static FragmentNorthResult newInstance() {
        return new FragmentNorthResult();
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
        calendarList = new ArrayList<>();
        for (int i = 0; i < TimeUtils.DAYS_OF_TIME; i++) {
            MyCalendar calendar = new MyCalendar();
            calendar.setDateLabel(CalendarUtils.getDayNameTitle(TimeUtils.getDayForPosition(i)));
            calendar.setDateValue(CalendarUtils.getDay(TimeUtils.getDayForPosition(i)));
            calendar.setMonthLabel(CalendarUtils.getMonthName(TimeUtils.getDayForPosition(i)));
            calendar.setMonthValue(CalendarUtils.getMonthValue(TimeUtils.getDayForPosition(i)));
            calendarList.add(i, calendar);
            fragmentList.add(i, FragmentNorthContent.newInstance(TimeUtils.getDayForPosition(i).getTimeInMillis()));
        }
        mContext = getContext();
        vpPager = (ViewPager) view.findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager(), fragmentList);
        vpPager.setAdapter(adapterViewPager);

        recyclerTabLayout = (RecyclerTabLayout) view.findViewById(R.id.recycler_tab_layout);
        adapterCalendar = new AdapterCalendar(vpPager, getContext(), calendarList);
        recyclerTabLayout.setUpWithViewPager(vpPager);
        recyclerTabLayout.setAdapter(adapterCalendar);
        // set pager to current date

        if (TimeUtils.checkTimeInMilisecondNorth(18, 10, 23, 58)) {
            vpPager.setCurrentItem(fragmentList.size() - 1);
        } else {
            vpPager.setCurrentItem(fragmentList.size() - 2);
        }
    }

    private boolean checkSelectedDay() {
        return vpPager.getCurrentItem() == fragmentList.size() - 1;
    }


    public void setLive() {
        int position = fragmentList.size() - 1;
        if (!checkSelectedDay()) {
            vpPager.setCurrentItem(position);
            ((FragmentNorthContent) adapterViewPager.getItem(position)).startLive();
        } else {
            ((FragmentNorthContent) adapterViewPager.getItem(position)).startLive();
        }
    }

    public void setEndLive() {
        int position = fragmentList.size() - 1;
        if (!checkSelectedDay()) {
            ((FragmentNorthContent) adapterViewPager.getItem(position)).setEndLive();
            vpPager.setCurrentItem(position);
        } else {
            ((FragmentNorthContent) adapterViewPager.getItem(position)).setEndLive();
        }
    }

    public void queryResult(Calendar calendar) {
        vpPager.setCurrentItem(TimeUtils.getPositionForDay(calendar));
    }

    @Override
    public void onComplete() {
        setLive();
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;

        public MyPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
            super(fragmentManager);
            list = fragmentList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Calendar cal = TimeUtils.getDayForPosition(position);
            return TimeUtils.getTitleTime(mContext, cal.getTimeInMillis());
        }
    }

}
