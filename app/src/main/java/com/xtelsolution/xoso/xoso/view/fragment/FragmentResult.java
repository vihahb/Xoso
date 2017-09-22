package com.xtelsolution.xoso.xoso.view.fragment;

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

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.xoso.view.fragment.inf.OnCompleteListener;
import com.xtelsolution.xoso.xoso.view.widget.LockableViewPager;

import java.util.ArrayList;
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
        }catch (ClassCastException e){
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
        if (viewPager!=null) {
            viewPager.setCurrentItem(i);
        }
    }

    public void setLive(int i) {
        if (viewPager!=null){
            viewPager.setCurrentItem(i);
        }
    }

    public void queryDate(String date) {
        int fragmentPosition = viewPager.getCurrentItem();
        switch (fragmentPosition){
            case 0:
                ((FragmentNorthResult)pagerAdapter.getItem(0)).queryResult(date);
                break;
            case 1:
                ((FragmentCentralResult)pagerAdapter.getItem(1)).queryResult(date);
                break;
            case 2:
                ((FragmentSouthResult)pagerAdapter.getItem(2)).queryResult(date);
                break;

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
