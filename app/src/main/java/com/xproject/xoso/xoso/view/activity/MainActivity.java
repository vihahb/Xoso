package com.xproject.xoso.xoso.view.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.AlarmUtils;
import com.xproject.xoso.sdk.utils.PermissionHelper;
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.DrawerMenu;
import com.xproject.xoso.xoso.presenter.activity.HomePresenter;
import com.xproject.xoso.xoso.view.activity.inf.IHomeView;
import com.xproject.xoso.xoso.view.activity.inf.onDateSelectListener;
import com.xproject.xoso.xoso.view.adapter.AdapterMenu;
import com.xproject.xoso.xoso.view.fragment.FragmentAnalytics;
import com.xproject.xoso.xoso.view.fragment.FragmentExplore;
import com.xproject.xoso.xoso.view.fragment.FragmentMore;
import com.xproject.xoso.xoso.view.fragment.FragmentResult;
import com.xproject.xoso.xoso.view.fragment.inf.OnCompleteListener;
import com.xproject.xoso.xoso.view.widget.LockableViewPager;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends BasicActivity implements IHomeView, OnCompleteListener, onDateSelectListener {

    private List<DrawerMenu> menuList;
    private RecyclerView rcl_menu;
    private AdapterMenu adapterMenu;
    private HomePresenter presenter;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private BottomNavigationViewEx navigationBottom;
    private LockableViewPager viewPager;
    private FragmentPagerAdapter pagerAdapter;
    BroadcastReceiver broadcastLive;
    private boolean isResultView = false;
    private MenuItem item;
    private AppBarLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Kết quả miền Bắc");
        params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            PermissionHelper.checkOnlyPermission(Manifest.permission.READ_EXTERNAL_STORAGE, this, 99);
        }
        handlerAction();
        initAlarmManager();
        FirebaseMessaging.getInstance().subscribeToTopic("mienbac");
        presenter = new HomePresenter(this);
        initProvinceDatabase();
        initWidget();
        initDrawer();
        logUser();
        registerReceiver(broadcastLive, new IntentFilter("ACTION_LIVE"));
    }

    private void initDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        // mDrawerToggle.setHomeAsUpIndicator(R.drawable.menu_icon);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void handlerAction(){
        broadcastLive = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int action_type = intent.getIntExtra(Constants.ACTION_TYPE, -1);
                switch (action_type) {
                    case 1:
                        ((FragmentResult) pagerAdapter.getItem(0)).changeLive(0);
                        break;
                    case 2:
                        ((FragmentResult) pagerAdapter.getItem(0)).changeLive(1);
                        break;
                    case 3:
                        ((FragmentResult) pagerAdapter.getItem(0)).changeLive(2);
                        break;
                    case 4:
//                        ((FragmentResult) pagerAdapter.getItem(0)).changeLiveEnd(0);
                        ((FragmentResult) pagerAdapter.getItem(0)).emulatorEndLive(0);
                        break;
                    case 5:
                        ((FragmentResult) pagerAdapter.getItem(0)).emulatorEndLive(1);
                        break;
                    case 6:
                        ((FragmentResult) pagerAdapter.getItem(0)).emulatorEndLive(2);
                        break;
                }
            }
        };
    }

    public void setFlagLive(boolean flag, int position){
        if (flag) {
            adapterMenu.changeLiveItem(position);
        } else {
            adapterMenu.changeLiveDone(position);
        }
    }

    private void initProvinceDatabase() {
        presenter.saveProvince();
    }

    public void setChangeLive() {
        if (TimeUtils.checkTimeInMilisecondNorth(18, 10, 18, 45)) {
            ((FragmentResult) pagerAdapter.getItem(0)).changeLive(0);
        } else if (TimeUtils.checkTimeInMilisecondNorth(16, 45, 23, 58)) {
            setEndLive(1);
        }

        if (TimeUtils.checkTimeInMilisecondNorth(17, 10, 17, 45)) {
            ((FragmentResult) pagerAdapter.getItem(0)).changeLive(1);
        } else if (TimeUtils.checkTimeInMilisecondNorth(17, 45, 23, 58)) {
            setEndLive(2);
        }

        if (TimeUtils.checkTimeInMilisecondNorth(16, 10, 16, 45)) {
            ((FragmentResult) pagerAdapter.getItem(0)).changeLive(2);
        } else if (TimeUtils.checkTimeInMilisecondNorth(18, 45, 23, 58)) {
            setEndLive(3);
        }

    }

    public void setEndLive(int position_area) {
        switch (position_area) {
            case 1:
                ((FragmentResult) pagerAdapter.getItem(0)).changeLiveEnd(0);
                setFlagLive(false, 1);
                break;
            case 2:
                ((FragmentResult) pagerAdapter.getItem(0)).changeLiveEnd(1);
                setFlagLive(false, 2);
                break;
            case 3:
                ((FragmentResult) pagerAdapter.getItem(0)).changeLiveEnd(2);
                setFlagLive(false, 3);
                break;
        }
    }

    private void logUser() {
        // TODO: Use the current user's information
        // You can call any combination of these three methods
        Crashlytics.setUserIdentifier(android.os.Build.MODEL);
        Crashlytics.setUserName(Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName());
    }

    private void getData() {
        int live_area = getIntent().getIntExtra(Constants.START_LIVE, -1);
        if (live_area != -1) {
            switch (live_area) {
                case 1:
                    ((FragmentResult) pagerAdapter.getItem(0)).setLive(0);
                    break;
                case 2:
                    ((FragmentResult) pagerAdapter.getItem(0)).setLive(1);
                    break;
                case 3:
                    ((FragmentResult) pagerAdapter.getItem(0)).setLive(2);
                    break;
            }
        }
    }

    private void initWidget() {
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAppBarLayout.setElevation(0);
        }
        viewPager = (LockableViewPager) findViewById(R.id.vpMain);
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        viewPager.setSwipeable(false);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                        if (item != null) {
                            item.setVisible(true);
                        }
                        toolbar.setTitle("Kết quả");
                        break;
                    case 1:
                        params.setScrollFlags(0);
                        if (item != null) {
                            item.setVisible(false);
                        }
                        toolbar.setTitle("Thống kê");
                        break;
                    case 2:
                        params.setScrollFlags(0);
                        if (item != null) {
                            item.setVisible(false);
                        }
                        toolbar.setTitle("Soi Cầu");
                        break;
                    case 3:
                        params.setScrollFlags(0);
                        if (item != null) {
                            item.setVisible(false);
                        }
                        toolbar.setTitle("Mở Rộng");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationBottom = (BottomNavigationViewEx) findViewById(R.id.bottomTab);
        navigationBottom.enableAnimation(false);
        navigationBottom.enableShiftingMode(false);
        navigationBottom.enableItemShiftingMode(false);
        navigationBottom.setupWithViewPager(viewPager);
        initBottomNavigationListener();


        menuList = new ArrayList<>();
        rcl_menu = findRecyclerView(R.id.rcl_menu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcl_menu.setLayoutManager(layoutManager);
        adapterMenu = new AdapterMenu(menuList, this, this);
        rcl_menu.setAdapter(adapterMenu);
        presenter.initDrawerMenu();
        presenter.getDream();
        setChangeLive();
        setLiveDone();
    }

    private void setLiveDone() {
        if (TimeUtils.checkTimeInMilisecondNorth(16, 45, 23, 58)) {
            adapterMenu.changeLiveDone(3);
        }

        if (TimeUtils.checkTimeInMilisecondNorth(17, 45, 23, 58)) {
            adapterMenu.changeLiveDone(2);
        }

        if (TimeUtils.checkTimeInMilisecondNorth(18, 45, 23, 58)) {
            adapterMenu.changeLiveDone(1);
        }
    }

    private void initAlarmManager() {
        AlarmUtils.setAlarm();
    }

    private void initBottomNavigationListener() {
        navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_result:
                        isResultView = true;
                        viewPager.setCurrentItem(0);
                        toolbar.setTitle(item.getTitle());
                        break;
                    case R.id.nav_analytics:
                        isResultView = false;
                        viewPager.setCurrentItem(1);
                        toolbar.setTitle(item.getTitle());
                        break;
                    case R.id.nav_explore:
                        isResultView = false;
                        viewPager.setCurrentItem(2);
                        toolbar.setTitle(item.getTitle());
                        break;
                    case R.id.nav_more:
                        isResultView = false;
                        viewPager.setCurrentItem(3);
                        toolbar.setTitle(item.getTitle());
                        break;
                }
                return true;
            }
        });
        navigationBottom.setSelectedItemId(R.id.nav_result);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            showConfirmExitApp();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        item = menu.findItem(R.id.action_select_date);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_select_date) {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    Log.e("onDate Set", "onDateSet: " + year + " - " + month + " - " + day);
                    Calendar get_cal = Calendar.getInstance();
                    get_cal.set(year, month, day);
                    onDateSelect(get_cal);
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initMenuDrawer(List<DrawerMenu> menuList) {
        adapterMenu.addList(menuList);
    }

    @Override
    public void itemDrawerClick(View view, int position) {
        switch (position) {
            case 2:
                navigationBottom.setSelectedItemId(R.id.nav_result);
                ((FragmentResult) pagerAdapter.getItem(0)).selected(0);
                break;
            case 3:
                navigationBottom.setSelectedItemId(R.id.nav_result);
                ((FragmentResult) pagerAdapter.getItem(0)).selected(1);
                break;
            case 4:
                navigationBottom.setSelectedItemId(R.id.nav_result);
                ((FragmentResult) pagerAdapter.getItem(0)).selected(2);
                break;
            case 6:
                navigationBottom.setSelectedItemId(R.id.nav_analytics);
                startActivity(ActivityLotoCycle.class);
                break;
            case 7:
                navigationBottom.setSelectedItemId(R.id.nav_analytics);
                startActivity(ActivityCycleSpecial.class);
                break;
            case 8:
                navigationBottom.setSelectedItemId(R.id.nav_analytics);
                startActivity(ActivityExploreBridgeLoto.class, Constants.ACTION_TYPE, 1);
                break;
            case 10:
                navigationBottom.setSelectedItemId(R.id.nav_explore);
                startActivity(ActivityExploreBridgeLoto.class, Constants.ACTION_TYPE, 5);
                break;
            case 12:
                navigationBottom.setSelectedItemId(R.id.nav_explore);
                break;
            case 14:
                navigationBottom.setSelectedItemId(R.id.nav_more);
                startActivity(DreamActivity.class);
                break;
            case 15:
                navigationBottom.setSelectedItemId(R.id.nav_more);
                startActivity(RandomSpinActivity.class);
                break;
            case 16:
                startActivity(SettingActivity.class);
                break;
            case 17:
                navigationBottom.setSelectedItemId(R.id.nav_more);
                break;
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastLive);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.checkResult(grantResults);
    }

    @Override
    public void onComplete() {
        getData();
    }

    @Override
    public void onDateSelect(Calendar date) {
        ((FragmentResult) pagerAdapter.getItem(0)).queryDate(date);
    }

    public void setTitleToolbar(String s) {
        if (s != null)
            toolbar.setTitle(s);
    }

    public static class FragmentPagerAdapter extends FragmentStatePagerAdapter {

        List<Fragment> fragmentList;

        public FragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            fragmentList = new ArrayList<>();
            fragmentList.add(FragmentResult.newInstance());
            fragmentList.add(FragmentAnalytics.newInstance());
            fragmentList.add(FragmentExplore.newInstance());
            fragmentList.add(FragmentMore.newInstance());
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
                    title = "Kết quả";
                    break;
                case 1:
                    title = "Thống kê";
                    break;
                case 2:
                    title = "Soi cầu";
                    break;
                case 3:
                    title = "Mở rộng";
                    break;
            }
            return title;
        }
    }

}
