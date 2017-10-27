package com.xproject.xoso.xoso.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.SharedUtils;
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.view.activity.MainActivity;
import com.xproject.xoso.xoso.view.fragment.inf.OnCompleteListener;
import com.xproject.xoso.xoso.view.widget.LockableViewPager;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by vivhp on 9/6/2017.
 */

public class FragmentResult extends BasicFragment {

//    private BottomNavigationViewEx navigationTop;

    LockableViewPager viewPager;
    TabLayout tabLayout;
    TextView north, central, south, tv_live_North, tv_live_Central, tv_live_South;
    private OnCompleteListener listener;
    private FragmentPagerAdapter pagerAdapter;
    private boolean live_one = false, live_two = false, live_three = false;
    private boolean check_done_n = false, check_done_c = false, check_done_s = false;
    private int tmp_flag_region_radio;

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
        tabLayout.setupWithViewPager(viewPager);

        check_done_n = SharedUtils.getInstance().getBooleanValue(Constants.CHECK_DONE_N);
        check_done_c = SharedUtils.getInstance().getBooleanValue(Constants.CHECK_DONE_C);
        check_done_s = SharedUtils.getInstance().getBooleanValue(Constants.CHECK_DONE_S);

        /**
         * Check live north*/
        if (TimeUtils.checkTimeInMilisecondNorth(18, 10, 18, 40)) {
            if (!check_done_n){
                setViewTab1(true);
            } else {
                setViewTab1(false);
            }
        } else {
            setViewTab1(false);
        }

        /**
         * Check live central*/
        if (TimeUtils.checkTimeInMilisecondNorth(17, 10, 17, 40)) {
            if (!check_done_c){
                setViewTab2(true);
            } else {
                setViewTab2(false);
            }
        } else {
            setViewTab2(false);
        }

        /**
         * Check live south*/
        if (TimeUtils.checkTimeInMilisecondNorth(16, 10, 16, 40)) {
            if (!check_done_s){
                setViewTab3(true);
            } else {
                setViewTab3(false);
            }
        } else {
            setViewTab3(false);
        }
//        setViewTab4();

        tmp_flag_region_radio = SharedUtils.getInstance().getIntValue(Constants.FLAG_RADIO_REGION);
        if (tmp_flag_region_radio > 0) {
            switch (tmp_flag_region_radio) {
                case 1:
                    viewPager.setCurrentItem(0);
                    break;
                case 2:
                    viewPager.setCurrentItem(1);
                    break;
                case 3:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }

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
        north.setText("Miền Bắc");
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
        central.setText("Miền Trung");
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
        south.setText("Miền Nam");

        if (flag) {
            tv_live_South.setVisibility(View.VISIBLE);
        } else {
            tv_live_South.setVisibility(View.INVISIBLE);
        }
        if (tab != null) {
            tab.setCustomView(southView);
        }

    }

//    private void setViewTab4() {
//        /**
//         * Set custom View for South result*/
//        TabLayout.Tab tab = tabLayout.getTabAt(3);
//        View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(3);
//        tabView.requestLayout();
//        View vlView = LayoutInflater.from(getContext()).inflate(R.layout.custom_view, null);
//        tv_live_South = (TextView) vlView.findViewById(R.id.tv_live);
//        vietlott = (TextView) vlView.findViewById(R.id.tab);
//        vietlott.setText("Vietlott");
//
//        if (tab != null) {
//            tab.setCustomView(vlView);
//        }
//    }

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
            MainActivity mainActivity = (MainActivity) getActivity();
            switch (i) {
                case 0:

                    /**
                     * End live if central exists*/
                    if (live_two) {
                        reSetViewTab2(false);
                        mainActivity.setFlagLive(false, 2);
                    }

                    /**
                     * End live if south exists*/
                    if (live_three) {
                        reSetViewTab3(false);
                        mainActivity.setFlagLive(false, 3);
                    }

                    /**
                     * Set Done live north*/
                    reSetViewTab1(true);
                    mainActivity.setFlagLive(true, 1);
                    viewPager.setCurrentItem(0);
                    ((FragmentNorthResult) pagerAdapter.getItem(0)).setLive();
                    break;
                case 1:

                    /**
                     * End live if south exists*/
                    if (live_three) {
                        reSetViewTab3(false);
                        mainActivity.setFlagLive(false, 3);
                    }

                    /**
                     * End live if north exists*/
                    if (live_one) {
                        reSetViewTab1(false);
                        mainActivity.setFlagLive(false, 1);
                    }

                    /**
                     * Set Done live central*/
                    reSetViewTab2(true);
                    mainActivity.setFlagLive(true, 2);
                    viewPager.setCurrentItem(1);
                    ((FragmentCentralResult) pagerAdapter.getItem(1)).setLive();
                    break;

                case 2:
                    /**
                     * End live if north exists*/
                    if (live_one) {
                        reSetViewTab1(false);
                        mainActivity.setFlagLive(false, 1);
                    }

                    /**
                     * End live if central exists*/
                    if (live_two) {
                        reSetViewTab2(false);
                        mainActivity.setFlagLive(false, 2);
                    }

                    /**
                     * Set Done live south*/
                    reSetViewTab3(true);
                    mainActivity.setFlagLive(true, 3);
                    viewPager.setCurrentItem(2);
                    ((FragmentSouthResult) pagerAdapter.getItem(2)).setLive();
                    break;
            }
        }
    }

    private void reSetViewTab1(boolean flag) {
        /**
         * Set custom View for South result*/
        this.live_one = flag;
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
        this.live_two = flag;
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
        this.live_three = flag;
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
//        MainActivity mainActivity = (MainActivity) getActivity();
        if (viewPager != null) {
            switch (i) {
                case 0:
                    reSetViewTab1(false);
//                    mainActivity.setFlagLive(false, 1);
                    break;
                case 1:
                    reSetViewTab2(false);
//                    mainActivity.setFlagLive(false, 2);
                    break;
                case 2:
                    reSetViewTab3(false);
//                    mainActivity.setFlagLive(false, 3);
                    break;
            }
        }
    }


    public void emulatorEndLive(int i) {
        switch (i) {
            case 0:
                ((FragmentNorthResult) pagerAdapter.getItem(0)).setEndLive();
                break;
            case 1:
                ((FragmentCentralResult) pagerAdapter.getItem(1)).setEndLive();
                break;
            case 2:
                ((FragmentSouthResult) pagerAdapter.getItem(2)).setEndLive();
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    public static class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

        List<Fragment> fragmentList;

        public FragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            fragmentList = new ArrayList<>();
            fragmentList.add(FragmentNorthResult.newInstance());
            fragmentList.add(FragmentCentralResult.newInstance());
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
                    title = "Miền Bắc";
                    break;
                case 1:
                    title = "Miền Trung";
                    break;
                case 2:
                    title = "Miền Nam";
                    break;
            }
            return title;
        }

    }

}
