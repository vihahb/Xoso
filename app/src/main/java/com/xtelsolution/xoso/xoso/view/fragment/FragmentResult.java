package com.xtelsolution.xoso.xoso.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.xoso.view.widget.LockableViewPager;

/**
 * Created by vivhp on 9/6/2017.
 */

public class FragmentResult extends BasicFragment {

//    private BottomNavigationViewEx navigationTop;

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
    }

    private void initWidget(View view) {

        viewPager = view.findViewById(R.id.vp_result);
        pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setSwipeable(false);
        tabLayout = view.findViewById(R.id.tabLayout);
        /**
         * 1
         **/
        View vNorth = LayoutInflater.from(getContext()).inflate(R.layout.custom_view, null);
        north = vNorth.findViewById(R.id.tab);
        north.setText("Miền Bắc");
        tv_live_North = vNorth.findViewById(R.id.tv_live);
        tv_live_North.setVisibility(View.VISIBLE);
        tabLayout.addTab(tabLayout.newTab().setCustomView(vNorth), 0);

        /**
         * 2*/
        View vCentral = LayoutInflater.from(getContext()).inflate(R.layout.custom_view, null);
        central = vCentral.findViewById(R.id.tab);
        central.setText("Miền Trung");
        tv_live_Central = vNorth.findViewById(R.id.tv_live);
        tabLayout.addTab(tabLayout.newTab().setCustomView(vCentral), 1);

        /**
         * 3*/
        View vSouth = LayoutInflater.from(getContext()).inflate(R.layout.custom_view, null);
        south = vSouth.findViewById(R.id.tab);
        south.setText("Miền Nam");
        tv_live_South = vNorth.findViewById(R.id.tv_live);
        tabLayout.addTab(tabLayout.newTab().setCustomView(vSouth), 2);

        /**
         * 4*/
        View vVietlott = LayoutInflater.from(getContext()).inflate(R.layout.custom_view, null);
        vietlott = vVietlott.findViewById(R.id.tab);
        vietlott.setText("Vietlott");
        tabLayout.addTab(tabLayout.newTab().setCustomView(vVietlott), 3);
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()){
//                    case 0:
//                        viewPager.getAdapter().get;
//                        break;
//                    case 1:
//                        replaceFragment(R.id.vp_result, FragmentCentralResult.newInstance(), "CENTRAL");
//                        break;
//                    case 2:
//                        replaceFragment(R.id.vp_result, new FragmentSouthResult(), "SOUTH");
//                        break;
//                    case 3:
////                        replaceFragment(R.id.fr_result, new FragmentNorthResult());
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//        replaceFragment(R.id.vp_result, new FragmentNorthResult());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void initResources() {

    }

    private void cleanupResources() {
    }

    public void setLiveFragment(int i) {
        switch (i){
            case 1:
                FragmentNorthResult northResult = (FragmentNorthResult) getChildFragmentManager().findFragmentByTag("NORTH");
                viewPager.setCurrentItem(0);
                break;
            case 2:
                FragmentCentralResult centralResult = (FragmentCentralResult) getChildFragmentManager().findFragmentByTag("CENTRAL");
                viewPager.setCurrentItem(1);
                break;
            case 3:
                FragmentSouthResult southResult = (FragmentSouthResult) getChildFragmentManager().findFragmentByTag("SOUTH");
                viewPager.setCurrentItem(2);
                break;
        }
    }

    public void selected(int i) {
        viewPager.setCurrentItem(i);
    }

    public static class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
        private static int NUM_ITEMS = 4;

        public FragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FragmentNorthResult.newInstance();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return FragmentCentralResult.newInstance();
                case 2: // Fragment # 1 - This will show SecondFragment
                    return FragmentSouthResult.newInstance();
                case 3: // Fragment # 1 - This will show SecondFragment
                    return FragmentSouthResult.newInstance();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            switch (position){
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

}
