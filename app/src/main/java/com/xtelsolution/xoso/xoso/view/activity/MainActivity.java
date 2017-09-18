package com.xtelsolution.xoso.xoso.view.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.sdk.common.Constants;
import com.xtelsolution.xoso.sdk.utils.PermissionHelper;
import com.xtelsolution.xoso.xoso.model.entity.DrawerMenu;
import com.xtelsolution.xoso.xoso.model.entity.MessageEvent;
import com.xtelsolution.xoso.xoso.presenter.activity.HomePresenter;
import com.xtelsolution.xoso.xoso.view.activity.inf.IHomeView;
import com.xtelsolution.xoso.xoso.view.adapter.AdapterMenu;
import com.xtelsolution.xoso.xoso.view.fragment.FragmentAnalytics;
import com.xtelsolution.xoso.xoso.view.fragment.FragmentCentralResult;
import com.xtelsolution.xoso.xoso.view.fragment.FragmentExplore;
import com.xtelsolution.xoso.xoso.view.fragment.FragmentMore;
import com.xtelsolution.xoso.xoso.view.fragment.FragmentNorthResult;
import com.xtelsolution.xoso.xoso.view.fragment.FragmentResult;
import com.xtelsolution.xoso.xoso.view.fragment.FragmentSouthResult;

import org.greenrobot.eventbus.EventBus;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends BasicActivity implements IHomeView {

    private List<DrawerMenu> menuList;
    private RecyclerView rcl_menu;
    private AdapterMenu adapterMenu;
    private HomePresenter presenter;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private BottomNavigationViewEx navigationBottom;
    private boolean isResultView = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            PermissionHelper.checkOnlyPermission(Manifest.permission.READ_EXTERNAL_STORAGE, this, 99);
        }

        presenter = new HomePresenter(this);
        initWidget();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        getData();
    }

    private void getData() {
        int live_area = getIntent().getIntExtra(Constants.START_LIVE, -1);
        if (live_area != -1) {
            FragmentResult result = (FragmentResult) getSupportFragmentManager().findFragmentByTag("RESULT");
            switch (live_area) {
                case 1:
                    result.setLiveFragment(1);
                    break;
                case 2:
                    result.setLiveFragment(2);
                    break;
                case 3:
                    result.setLiveFragment(3);
                    break;
            }
        }
    }

    private void initWidget() {
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mAppBarLayout.setElevation(0);
        }
        navigationBottom = (BottomNavigationViewEx) findViewById(R.id.bottomTab);
        navigationBottom.enableAnimation(false);
        navigationBottom.enableShiftingMode(false);
        navigationBottom.enableItemShiftingMode(false);
        initBottomNavigationListener();


        menuList = new ArrayList<>();
        rcl_menu = findRecyclerView(R.id.rcl_menu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcl_menu.setLayoutManager(layoutManager);
        adapterMenu = new AdapterMenu(menuList, this, this);
        rcl_menu.setAdapter(adapterMenu);
        presenter.startService(true);
        presenter.initDrawerMenu();
        presenter.getDream();
    }

    private void initBottomNavigationListener() {
        navigationBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_result:
                        isResultView = true;
                        replaceFragment(R.id.fr_layout, FragmentResult.newInstance(), "RESULT");
                        toolbar.setTitle(item.getTitle());
                        break;
                    case R.id.nav_analytics:
                        isResultView = false;
                        replaceFragment(R.id.fr_layout, FragmentAnalytics.newInstance(), "ANALYTICS");
                        toolbar.setTitle(item.getTitle());
                        break;
                    case R.id.nav_explore:
                        isResultView = false;
                        replaceFragment(R.id.fr_layout, FragmentExplore.newInstance(), "EXPLORE");
                        toolbar.setTitle(item.getTitle());
                        break;
                    case R.id.nav_more:
                        isResultView = false;
                        replaceFragment(R.id.fr_layout, FragmentMore.newInstance(), "MORE");
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
        if (isResultView) {
            menu.getItem(0).setVisible(true);
        } else {
            menu.getItem(0).setVisible(false);
        }
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
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

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
                replaceFragment(new FragmentNorthResult());
                break;
            case 3:
                navigationBottom.setSelectedItemId(R.id.nav_result);
                replaceFragment(new FragmentCentralResult());
                break;
            case 4:
                navigationBottom.setSelectedItemId(R.id.nav_result);
                replaceFragment(new FragmentSouthResult());
                break;
            case 6:
                navigationBottom.setSelectedItemId(R.id.nav_analytics);
                break;
            case 7:
                navigationBottom.setSelectedItemId(R.id.nav_analytics);
                break;
            case 9:
                navigationBottom.setSelectedItemId(R.id.nav_explore);
                break;
            case 10:
                navigationBottom.setSelectedItemId(R.id.nav_explore);
                break;
            case 12:
                navigationBottom.setSelectedItemId(R.id.nav_more);
                break;
            case 13:
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionHelper.checkResult(grantResults);
    }
}
