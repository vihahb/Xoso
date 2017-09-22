package com.xtelsolution.xoso.xoso.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.sdk.utils.CalendarUtils;
import com.xtelsolution.xoso.sdk.utils.TimeUtils;
import com.xtelsolution.xoso.xoso.model.entity.MyCalendar;
import com.xtelsolution.xoso.xoso.view.adapter.AdapterCalendar;
import com.xtelsolution.xoso.xoso.view.adapter.inf.ListCalendarView;
import com.xtelsolution.xoso.xoso.view.widget.PageTransformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vivhp on 9/7/2017.
 */

public class FragmentNorthResult extends BasicFragment implements ListCalendarView {

    private static final String TAG = "FragmentNorthResult";


    private static Context mContext;
    private ViewPager vpPager;
    private MyPagerAdapter adapterViewPager;

    private List<MyCalendar> calendarList;
    private RecyclerView rcl_calendar;
    private AdapterCalendar adapterCalendar;

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
        vpPager = view.findViewById(R.id.vpPager);
        calendarList = new ArrayList<>();
        rcl_calendar = view.findViewById(R.id.rcl_calendar);
        rcl_calendar.setItemAnimator(new DefaultItemAnimator());
        adapterCalendar = new AdapterCalendar(getContext(), this, calendarList);
        LinearSnapHelper layoutManager = new LinearSnapHelper();
        layoutManager.attachToRecyclerView(rcl_calendar);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcl_calendar.setLayoutManager(manager);
        rcl_calendar.setAdapter(adapterCalendar);
        initCalendarBar();
    }

    private void initCalendarBar(){

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int myMonth = cal.get(Calendar.MONTH);

        while (myMonth == cal.get(Calendar.MONTH)) {
            Log.e(TAG, "loop add time" + cal.getTime());

            MyCalendar days = new MyCalendar();
            days.setDateLabel(CalendarUtils.getDayName(cal));
            days.setDateValue(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
            days.setMonthLabel(CalendarUtils.getMonth(cal));
            days.setMonthValue(String.valueOf(cal.get(Calendar.MONTH)));
            days.setYearValue(String.valueOf(cal.get(Calendar.YEAR)));

            calendarList.add(days);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.e(TAG, "calendar list size: " + calendarList.size());
        Calendar today = Calendar.getInstance();
        for (int i = 0; i < calendarList.size(); i++) {
            if (calendarList.get(i).getDateValue().equals(String.valueOf(today.get(Calendar.DAY_OF_MONTH)))) {
                calendarList.get(i).setSelectPosition(true);
            }
        }
        vpPager.setPageTransformer(false, new PageTransformer());
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager(), calendarList);
        vpPager.setAdapter(adapterViewPager);
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

    @Override
    public void onSelected(String date, int position) {

    }


    public static class MyPagerAdapter extends FragmentStatePagerAdapter {
        List<MyCalendar> calendarList;

        public MyPagerAdapter(FragmentManager fragmentManager, List<MyCalendar> calendarList) {
            super(fragmentManager);
            this.calendarList = calendarList;
        }

        @Override
        public int getCount() {
            return calendarList.size();
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
            return TimeUtils.getTitleTime(mContext, cal.getTimeInMillis());
        }
    }

}
