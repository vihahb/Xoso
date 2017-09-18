package com.xtelsolution.xoso.xoso.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.xoso.model.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by vivhp on 9/6/2017.
 */

public class FragmentResult extends BasicFragment {

//    private BottomNavigationViewEx navigationTop;

    TabLayout tabLayout;
    TextView north, central, south, vietlott, tv_live_North, tv_live_Central, tv_live_South;

    public static FragmentResult newInstance() {
        
        Bundle args = new Bundle();
        
        FragmentResult fragment = new FragmentResult();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
//        navigationTop = view.findViewById(R.id.topTab);
//        navigationTop.enableAnimation(false);
//        navigationTop.enableShiftingMode(false);
//        navigationTop.enableItemShiftingMode(false);
//        navigationTop.setTextSize(14);
//        navigationTop.setIconVisibility(false);
//        navigationTop.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.nav_result:
////                        replaceFragment(new FragmentResult());
//                        break;
//                    case R.id.nav_analytics:
////                        replaceFragment(new FragmentAnalytics());
//                        break;
//                    case R.id.nav_explore:
////                        replaceFragment(new FragmentExplore());
//                        break;
//                    case R.id.nav_more:
////                        replaceFragment(new FragmentMore());
//                        break;
//                }
//                return true;
//            }
//        });
//        navigationTop.setSelectedItemId(R.id.nav_result);

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

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        replaceFragment(R.id.fr_result, FragmentNorthResult.newInstance(), "NORTH");
                        break;
                    case 1:
                        replaceFragment(R.id.fr_result, FragmentCentralResult.newInstance(), "CENTRAL");
                        break;
                    case 2:
                        replaceFragment(R.id.fr_result, new FragmentSouthResult(), "SOUTH");
                        break;
                    case 3:
//                        replaceFragment(R.id.fr_result, new FragmentNorthResult());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        replaceFragment(R.id.fr_result, new FragmentNorthResult());
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
                FragmentNorthResult northResult = (FragmentNorthResult) getActivity().getSupportFragmentManager().findFragmentByTag("NORTH");
                replaceFragment(R.id.fr_result, FragmentNorthResult.newInstance(), "NORTH");
                break;
            case 2:
                FragmentCentralResult centralResult = (FragmentCentralResult) getActivity().getSupportFragmentManager().findFragmentByTag("CENTRAL");
                replaceFragment(R.id.fr_result, FragmentCentralResult.newInstance(), "CENTRAL");
                break;
            case 3:
                FragmentSouthResult southResult = (FragmentSouthResult) getActivity().getSupportFragmentManager().findFragmentByTag("SOUTH");
                replaceFragment(R.id.fr_result, FragmentSouthResult.newInstance(), "CENTRAL");
                break;
        }
    }
}
