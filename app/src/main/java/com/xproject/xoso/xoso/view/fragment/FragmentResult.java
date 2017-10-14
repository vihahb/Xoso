package com.xproject.xoso.xoso.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.SharedUtils;
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.view.activity.MainActivity;
import com.xproject.xoso.xoso.view.widget.LockableViewPager;
import com.xtelsolution.xoso.R;
import com.xproject.xoso.xoso.view.fragment.inf.OnCompleteListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vivhp on 9/6/2017.
 */

public class FragmentResult extends BasicFragment {

//    private BottomNavigationViewEx navigationTop;

    private OnCompleteListener listener;
    LockableViewPager viewPager;
    TabLayout tabLayout;
    TextView north, central, south, vietlott, tv_live_North, tv_live_Central, tv_live_South;
    private FragmentPagerAdapter pagerAdapter;

    public static FragmentResult newInstance() {

        Bundle args = new Bundle();

        FragmentResult fragment = new FragmentResult();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.listener = (OnCompleteListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initWidget(view);
        listener.onComplete();
    }


    private void initWidget(View view) {

        Log.e("Token fcm", "token: " + SharedUtils.getInstance().getStringValue(Constants.FCM_TOKEN));

        viewPager = (LockableViewPager) view.findViewById(R.id.vp_result);
        pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setSwipeable(false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Activity activity = getActivity();
                if (activity instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) activity;
//                    switch (position) {
//                        case 0:
//                            mainActivity.setTitleToolbar("Kết quả xổ số miền Bắc");
//                            break;
//                        case 1:
//                            mainActivity.setTitleToolbar("Kết quả xổ số miền Trung");
//                            break;
//                        case 2:
//                            mainActivity.setTitleToolbar("Kết quả xổ số miền Nam");
//                            break;
//                        case 3:
//                            mainActivity.setTitleToolbar("Kết quả xổ số Vietlott");
//                            break;
//                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setupWithViewPager(viewPager);

        /**
         * Check live north*/
        if (TimeUtils.checkTimeInMilisecondNorth(18, 15, 18, 50)) {
            setViewTab1(true);
        } else {
            setViewTab1(false);
        }

        /**
         * Check live central*/
        if (TimeUtils.checkTimeInMilisecondNorth(17, 15, 17, 50)) {
            setViewTab2(true);
        } else {
            setViewTab2(false);
        }

        /**
         * Check live south*/
        if (TimeUtils.checkTimeInMilisecondNorth(16, 15, 16, 50)) {
            setViewTab3(true);
        } else {
            setViewTab3(false);
        }
        setViewTab4();

    }

    private void setViewTab1(boolean flag) {
        /**
         * Set custom View for North result*/
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(0);
        tabView.requestLayout();
        View northView = LayoutInflater.from(getContext()).inflate(R.layout.custom_view, null);

        tv_live_North = (TextView) northView.findViewById(R.id.tv_live);
        north = (TextView) northView.findViewById(R.id.tab);
        north.setText("M. Bắc");
        if (flag) {
            tv_live_North.setVisibility(View.VISIBLE);
        } else {
            tv_live_North.setVisibility(View.INVISIBLE);
        }

        if (tab != null) {
            tab.setCustomView(northView);
        }
    }

    private void setViewTab2(boolean flag) {
        /**
         * Set custom View for Central result*/
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(1);
        tabView.requestLayout();
        View centralView = LayoutInflater.from(getContext()).inflate(R.layout.custom_view, null);
        tv_live_Central = (TextView) centralView.findViewById(R.id.tv_live);
        central = (TextView) centralView.findViewById(R.id.tab);
        central.setText("M. Trung");
        if (flag) {
            tv_live_Central.setVisibility(View.VISIBLE);
        } else {
            tv_live_Central.setVisibility(View.INVISIBLE);
        }
        if (tab != null) {
            tab.setCustomView(centralView);
        }

    }

    private void setViewTab3(boolean flag) {
        /**
         * Set custom View for South result*/
        TabLayout.Tab tab = tabLayout.getTabAt(2);
        View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(2);
        tabView.requestLayout();
        View southView = LayoutInflater.from(getContext()).inflate(R.layout.custom_view, null);
        tv_live_South = (TextView) southView.findViewById(R.id.tv_live);
        south = (TextView) southView.findViewById(R.id.tab);
        south.setText("M. Nam");

        if (flag) {
            tv_live_South.setVisibility(View.VISIBLE);
        } else {
            tv_live_South.setVisibility(View.INVISIBLE);
        }
        if (tab != null) {
            tab.setCustomView(southView);
        }

    }

    private void setViewTab4() {
        /**
         * Set custom View for South result*/
        TabLayout.Tab tab = tabLayout.getTabAt(3);
        View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(3);
        tabView.requestLayout();
        View vlView = LayoutInflater.from(getContext()).inflate(R.layout.custom_view, null);
        tv_live_South = (TextView) vlView.findViewById(R.id.tv_live);
        vietlott = (TextView) vlView.findViewById(R.id.tab);
        vietlott.setText("Vietlott");

        if (tab != null) {
            tab.setCustomView(vlView);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void selected(int i) {
        Log.e("Selected ", "selected: value" + i);
        if (viewPager != null) {
            viewPager.setCurrentItem(i);
        }
    }

    public void setLive(int i) {
        if (viewPager != null) {
            viewPager.setCurrentItem(i);
        }
    }

    public void queryDate(Calendar date) {
        int fragmentPosition = viewPager.getCurrentItem();
        switch (fragmentPosition) {
            case 0:
                ((FragmentNorthResult) pagerAdapter.getItem(0)).queryResult(date);
                break;
            case 1:
                ((FragmentCentralResult) pagerAdapter.getItem(1)).queryResult(date);
                break;
            case 2:
                ((FragmentSouthResult) pagerAdapter.getItem(2)).queryResult(date);
                break;

        }
    }

    public void changeLive(int i) {
        if (viewPager != null) {
            viewPager.setCurrentItem(i);
            switch (i) {
                case 0:
                    reSetViewTab1(true);
                    viewPager.setCurrentItem(0);
                    ((FragmentNorthResult) pagerAdapter.getItem(0)).setLive();
                    break;
                case 1:
                    reSetViewTab2(true);
                    viewPager.setCurrentItem(1);
                    ((FragmentCentralResult) pagerAdapter.getItem(1)).setLive();
                    break;
                case 2:
                    reSetViewTab3(true);
                    viewPager.setCurrentItem(2);
                    ((FragmentSouthResult) pagerAdapter.getItem(2)).setLive();
                    break;
            }
        }
    }

    private void reSetViewTab1(boolean flag) {
        /**
         * Set custom View for South result*/
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        View southView = null;
        if (tab != null) {
            southView = tab.getCustomView();
            tv_live_South = (TextView) southView.findViewById(R.id.tv_live);

            if (flag) {
                tv_live_South.setVisibility(View.VISIBLE);
            } else {
                tv_live_South.setVisibility(View.INVISIBLE);
            }
            tab.setCustomView(southView);
        }
    }

    private void reSetViewTab2(boolean flag) {
        /**
         * Set custom View for South result*/
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        View southView = null;
        if (tab != null) {
            southView = tab.getCustomView();
            tv_live_South = (TextView) southView.findViewById(R.id.tv_live);

            if (flag) {
                tv_live_South.setVisibility(View.VISIBLE);
            } else {
                tv_live_South.setVisibility(View.INVISIBLE);
            }
            tab.setCustomView(southView);
        }
    }

    private void reSetViewTab3(boolean flag) {
        /**
         * Set custom View for South result*/
        TabLayout.Tab tab = tabLayout.getTabAt(2);
        View southView = null;
        if (tab != null) {
            southView = tab.getCustomView();
            tv_live_South = (TextView) southView.findViewById(R.id.tv_live);

            if (flag) {
                tv_live_South.setVisibility(View.VISIBLE);
            } else {
                tv_live_South.setVisibility(View.INVISIBLE);
            }
            tab.setCustomView(southView);
        }
    }

    public void changeLiveEnd(int i) {
        if (viewPager != null) {
            switch (i) {
                case 0:
                    reSetViewTab1(false);
                    break;
                case 1:
                    reSetViewTab2(false);
                    break;
                case 2:
                    reSetViewTab3(false);
                    break;
            }
        }
    }


    public static class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

        List<Fragment> fragmentList;

        public FragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            fragmentList = new ArrayList<>();
            fragmentList.add(FragmentNorthResult.newInstance());
            fragmentList.add(FragmentCentralResult.newInstance());
            fragmentList.add(FragmentSouthResult.newInstance());
            fragmentList.add(FragmentSouthResult.newInstance());
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return fragmentList.size();
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            switch (position) {
                case 0:
                    title = "M. Bắc";
                    break;
                case 1:
                    title = "M. Trung";
                    break;
                case 2:
                    title = "M. Nam";
                    break;
                case 3:
                    title = "Vietlott";
                    break;
            }
            return title;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
