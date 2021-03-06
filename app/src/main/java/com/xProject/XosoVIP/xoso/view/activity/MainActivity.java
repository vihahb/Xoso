package com.xProject.XosoVIP.xoso.view.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.DatePicker;

import com.github.florent37.tutoshowcase.TutoShowcase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.xProject.XosoVIP.R;
import com.xProject.XosoVIP.sdk.callback.DialogListener;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.service.SocketService;
import com.xProject.XosoVIP.sdk.utils.AlarmUtils;
import com.xProject.XosoVIP.sdk.utils.PermissionHelper;
import com.xProject.XosoVIP.sdk.utils.SharedUtils;
import com.xProject.XosoVIP.sdk.utils.TimeUtils;
import com.xProject.XosoVIP.xoso.model.entity.DrawerMenu;
import com.xProject.XosoVIP.xoso.presenter.activity.HomePresenter;
import com.xProject.XosoVIP.xoso.view.activity.inf.IHomeView;
import com.xProject.XosoVIP.xoso.view.activity.inf.onDateSelectListener;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterMenu;
import com.xProject.XosoVIP.xoso.view.fragment.FragmentAnalytics;
import com.xProject.XosoVIP.xoso.view.fragment.FragmentExplore;
import com.xProject.XosoVIP.xoso.view.fragment.FragmentMore;
import com.xProject.XosoVIP.xoso.view.fragment.FragmentResult;
import com.xProject.XosoVIP.xoso.view.fragment.inf.OnCompleteListener;
import com.xProject.XosoVIP.xoso.view.widget.LockableViewPager;
import com.xProject.XosoVIP.xoso.view.widget.PageTransformer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends BasicActivity implements IHomeView, OnCompleteListener, onDateSelectListener {

    public static boolean mainStory = true;
    BroadcastReceiver broadcastLive;
    BroadcastReceiver broadcastClose;
    private List<DrawerMenu> menuList;
    private RecyclerView rcl_menu;
    private AdapterMenu adapterMenu;
    private HomePresenter presenter;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private BottomNavigationViewEx navigationBottom;
    private LockableViewPager viewPager;
    private FragmentPagerAdapter pagerAdapter;
    private boolean isResultView = false;
    private MenuItem item_menu;
    private AppBarLayout.LayoutParams params;
    private boolean chec_n = false;
    private boolean chec_c = false;
    private boolean chec_s = false;
    private boolean showGuide = false;
    private BroadcastReceiver broadcastChange;
    private int REQUEST_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.mipmap.background_home);
        setContentView(R.layout.activity_main);
        presenter = new HomePresenter(this);
        initView();
        listentClose();
        listentNotification();
        listentChange();
        checkLive();
        grandPermission();
        registerReceiver(broadcastLive, new IntentFilter("ACTION_LIVE"));
        registerReceiver(broadcastChange, new IntentFilter("ACTION_CHANGE"));
        registerReceiver(broadcastClose, new IntentFilter("ACTION_CLOSE"));
        displayShowCase();
    }

    private void grandPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            PermissionHelper.checkOnlyPermission(Manifest.permission.READ_EXTERNAL_STORAGE, this, 99);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Thông báo");
                builder.setMessage("Để ứng dụng có thể trợ giúp bạn một cách tốt nhất, vui lòng kích hoạt các quyền dưới đây.\n\n- Hiển thị trên các ứng dụng khác.");
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Cài đặt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        Intent intent = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                    Uri.parse("package:" + getPackageName()));
                        }
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                });
                builder.setCancelable(false);
                builder.show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode != Activity.RESULT_OK) {
            grandPermission();
        }
    }

    private void checkLive() {

        chec_n = SharedUtils.getInstance().getBooleanValue(Constants.CHECK_DONE_N);
        chec_c = SharedUtils.getInstance().getBooleanValue(Constants.CHECK_DONE_C);
        chec_s = SharedUtils.getInstance().getBooleanValue(Constants.CHECK_DONE_S);

        if (TimeUtils.checkTimeInMilisecondNorth(18, 10, 18, 40)) {
            if (!chec_n) {
                setFlagLive(true, 1);
            } else {
                setFlagLive(false, 1);
            }
        } else if (TimeUtils.checkTimeInMilisecondNorth(18, 40, 23, 58)) {
            setFlagLive(false, 1);
        }

        if (TimeUtils.checkTimeInMilisecondNorth(17, 10, 17, 40)) {
            if (!chec_c) {
                setFlagLive(true, 2);
            } else {
                setFlagLive(false, 2);
            }
        } else if (TimeUtils.checkTimeInMilisecondNorth(17, 40, 23, 58)) {
            setFlagLive(false, 2);
        }

        if (TimeUtils.checkTimeInMilisecondNorth(16, 10, 16, 40)) {
            if (!chec_s) {
                setFlagLive(true, 3);
            } else {
                setFlagLive(false, 3);
            }
        } else if (TimeUtils.checkTimeInMilisecondNorth(16, 40, 23, 58)) {
            setFlagLive(false, 3);
        }

    }

    private void initView() {
        /* setup enter and exit animation */
        final Animation enterAnimation = new AlphaAnimation(0f, 1f);
        enterAnimation.setDuration(600);
        enterAnimation.setFillAfter(true);

        final Animation exitAnimation = new AlphaAnimation(1f, 0f);
        exitAnimation.setDuration(600);
        exitAnimation.setFillAfter(true);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Kết quả miền Bắc");
        params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        initDrawer();
        initWidget();
//        setChangeLive();
        AlarmUtils.setAlarm();
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

    public void listentClose(){
        broadcastClose = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals("ACTION_CLOSE")){
                    forceExit();
                }
            }
        };
    }

    public void listentChange(){
        broadcastChange = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int action_type =intent.getIntExtra(Constants.ACTION_TYPE, -1);

                if (action_type > 0){
                    if (viewPager.getCurrentItem() > 0){
                        showDialogLive(false, false, action_type, new DialogListener() {
                            @Override
                            public void negativeClicked() {

                            }

                            @Override
                            public void positiveClicked() {
                                viewPager.setCurrentItem(0);
                            }
                        });
                    }
                }
            }
        };
    }

    public void listentNotification() {
        broadcastLive = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int action_type = intent.getIntExtra(Constants.ACTION_TYPE, -1);
                Intent socket_intent = new Intent(context, SocketService.class);
                socket_intent.putExtra(Constants.ACTION_TYPE, action_type);
                context.startService(socket_intent);

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

    public void setFlagLive(boolean flag, int position) {
        if (flag) {
            adapterMenu.changeLiveItem(position);
        } else {
            adapterMenu.changeLiveDone(position);
        }
    }

    public void setChangeLive() {
//        FragmentResult fragmentResult = (FragmentResult) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.vpMain + ":" + 0);
        FragmentResult fragmentResult = (FragmentResult) pagerAdapter.getItem(0);
        if (TimeUtils.checkTimeInMilisecondNorth(18, 10, 18, 40)) {
            ((FragmentResult) pagerAdapter.getItem(0)).changeLive(0);
        } else if (TimeUtils.checkTimeInMilisecondNorth(18, 40, 23, 58)) {
            fragmentResult.emulatorEndLive(0);
        }

        if (TimeUtils.checkTimeInMilisecondNorth(17, 10, 17, 40)) {
            ((FragmentResult) pagerAdapter.getItem(0)).changeLive(1);
        } else if (TimeUtils.checkTimeInMilisecondNorth(17, 40, 23, 58)) {
            fragmentResult.emulatorEndLive(1);
        }

        if (TimeUtils.checkTimeInMilisecondNorth(16, 10, 16, 40)) {
            ((FragmentResult) pagerAdapter.getItem(0)).changeLive(2);
        } else if (TimeUtils.checkTimeInMilisecondNorth(16, 40, 23, 58)) {
            fragmentResult.emulatorEndLive(2);
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
        viewPager.setOffscreenPageLimit(3);
        viewPager.setSwipeable(false);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, new PageTransformer());
        viewPager.setCurrentItem(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position != 0) {
                    mainStory = false;
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
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000L);
                    presenter.saveProvince();
                    presenter.getDream();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
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
                        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
                        if (item_menu != null) {
                            item_menu.setVisible(true);
                        }
                        break;
                    case R.id.nav_analytics:
                        isResultView = false;
                        viewPager.setCurrentItem(1);
                        toolbar.setTitle(item.getTitle());
                        params.setScrollFlags(0);
                        if (item_menu != null) {
                            item_menu.setVisible(false);
                        }
                        break;
                    case R.id.nav_explore:
                        isResultView = false;
                        viewPager.setCurrentItem(2);
                        toolbar.setTitle(item.getTitle());
                        params.setScrollFlags(0);
                        if (item_menu != null) {
                            item_menu.setVisible(false);
                        }
                        break;
                    case R.id.nav_more:
                        isResultView = false;
                        viewPager.setCurrentItem(3);
                        toolbar.setTitle(item.getTitle());
                        params.setScrollFlags(0);
                        if (item_menu != null) {
                            item_menu.setVisible(false);
                        }
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
        item_menu = menu.findItem(R.id.action_select_date);
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
                break;
            case 10:
                navigationBottom.setSelectedItemId(R.id.nav_explore);
                startActivity(ActivityExploreBridgeLoto.class, Constants.ACTION_TYPE, 1);
                break;
            case 11:
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
        unregisterReceiver(broadcastChange);
        unregisterReceiver(broadcastClose);
        stopService(new Intent(this, SocketService.class));
    }

    @Override
    public void onComplete() {
        getData();
    }

    @Override
    public void onDateSelect(Calendar date) {
        ((FragmentResult) pagerAdapter.getItem(0)).queryDate(date);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean accepted = PermissionHelper.checkResult(grantResults);
        if (!accepted) {
            finish();
        }
    }

    protected void displayShowCase() {
        final TutoShowcase showcase = TutoShowcase.from(this);
        showcase.setListener(new TutoShowcase.Listener() {
            @Override
            public void onDismissed() {
                showCase2();
            }
        });
        showcase.setContentView(R.layout.showcase_tap_left);
        showcase.setFitsSystemWindows(true);
        showcase.on(R.id.drawer_btn).addCircle().onClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcase.dismiss();
            }
        });
        showcase.showOnce("One");
    }

    private void showCase2() {
        final TutoShowcase showcase = TutoShowcase.from(this);
        showcase.setListener(new TutoShowcase.Listener() {
            @Override
            public void onDismissed() {
                showCase3();
            }
        });
        showcase.setContentView(R.layout.showcase_tap_right);
        showcase.setFitsSystemWindows(true);
        showcase.on(R.id.action_select_date).addCircle().onClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcase.dismiss();
            }
        });
        showcase.showOnce("Two");
    }

    private void showCase3() {
        final TutoShowcase showcase = TutoShowcase.from(this);
        showcase.setListener(new TutoShowcase.Listener() {
            @Override
            public void onDismissed() {
                showCase4();
            }
        });
        showcase.setContentView(R.layout.showcase_swipe);
        showcase.setFitsSystemWindows(true);
        showcase.on(R.id.rcl_calender_view).addRoundRect().onClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcase.dismiss();
            }
        });
        showcase.showOnce("Three");
    }

    private void showCase4() {
        final TutoShowcase showcase = TutoShowcase.from(this);
        showcase.setContentView(R.layout.showcase_tap_bot);
        showcase.setFitsSystemWindows(true);
        showcase.on(R.id.bottomTab).addRoundRect().onClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcase.dismiss();
            }
        });
        showcase.showOnce("Fourd");
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

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
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
                    title = "Thêm";
                    break;
            }
            return title;
        }
    }
}
