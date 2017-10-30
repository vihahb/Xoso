package com.xproject.xoso.xoso.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
        adapterCalendar = new AdapterCalendar(vpPager, getContext());
        recyclerTabLayout.setUpWithViewPager(vpPager);
        recyclerTabLayout.setAdapter(adapterCalendar);
        adapterCalendar.refreshList(calendarList);

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

    public void queryResult(final Calendar calendar) {
        showProgressBar(false, false, null, "Loading...");

        long one_month = 2629746000L;

        Calendar last_calendar = Calendar.getInstance();
        last_calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        Log.e(TAG, "last_calendar: " + last_calendar.get(Calendar.YEAR) + "-" + last_calendar.get(Calendar.MONTH) + "-" + last_calendar.get(Calendar.DAY_OF_MONTH));

        final Calendar first_calendar = Calendar.getInstance();
        first_calendar.setTimeInMillis(last_calendar.getTimeInMillis() - one_month);
        Log.e(TAG, "first_calendar: " + first_calendar.get(Calendar.YEAR) + "-" + first_calendar.get(Calendar.MONTH) + "-" + first_calendar.get(Calendar.DAY_OF_MONTH));


        int DAYS_OF_TIME = (int) ((last_calendar.getTimeInMillis() - first_calendar.getTimeInMillis()) / (24 * 60 * 60 * 1000)) + 1;
        Log.e(TAG, "DAYS_OF_TIME: " + DAYS_OF_TIME);

        fragmentList.clear();
        calendarList.clear();
        for (int i = 0; i < DAYS_OF_TIME; i++) {
            MyCalendar set_calendar = new MyCalendar();
            set_calendar.setDateLabel(CalendarUtils.getDayNameTitle(getDayForPosition(first_calendar, i)));
            set_calendar.setDateValue(CalendarUtils.getDay(getDayForPosition(first_calendar, i)));
            set_calendar.setMonthLabel(CalendarUtils.getMonthName(getDayForPosition(first_calendar, i)));
            set_calendar.setMonthValue(CalendarUtils.getMonthValue(getDayForPosition(first_calendar, i)));
            calendarList.add(i, set_calendar);
            fragmentList.add(i, FragmentNorthContent.newInstance(getDayForPosition(first_calendar,i).getTimeInMillis()));
        }
        adapterCalendar.refreshList(calendarList);
        adapterViewPager.notifyDataSetChanged();

        if (fragmentList.size() > 0){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    vpPager.setCurrentItem(fragmentList.size()-1);
                    closeProgressBar();

                }
            }, 1500);
        }
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
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
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

    public Calendar getDayForPosition(Calendar FIRST_DAY_OF_TIME, int position) throws IllegalArgumentException {
        if (position < 0) {
            throw new IllegalArgumentException("position cannot be negative");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(FIRST_DAY_OF_TIME.getTimeInMillis());
        cal.add(Calendar.DAY_OF_YEAR, position);
        return cal;
    }

    /**
     * Get the position in the ViewPager for a given day
     *
     * @param day
     * @return the position or 0 if day is null
     */
    public static int getPositionForDay(Calendar FIRST_DAY_OF_TIME, Calendar day) {
        if (day != null) {
            return (int) ((day.getTimeInMillis() - FIRST_DAY_OF_TIME.getTimeInMillis())
                    / 86400000  //(24 * 60 * 60 * 1000)
            );
        }
        return 0;
    }

}
