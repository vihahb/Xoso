package com.xproject.xoso.xoso.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.view.adapter.AdapterSpinner;
import com.xproject.xoso.xoso.view.adapter.AdapterStringCustom;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

public class BridgeForDayActivity extends BasicActivity implements View.OnClickListener {

    int province_code = 0;
    private Spinner sp_province;
    private List<ProvinceEntity> provinceEntityList;
    private AdapterSpinner adapterSpinner;
    private Button btnResult;
    private CheckBox checkSpecial;
    private EditText edt_number_set;
    private List<String> dayList;
    private Spinner sp_day;
    private AdapterStringCustom adapterDay;
    private String day_of_week;
    private int type_query = 0;
    private Toolbar toolbar;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge_for_day);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        getData();
    }

    private void initView() {
        provinceEntityList = getCategory();
        sp_province = (Spinner) findViewById(R.id.sp_province);
        adapterSpinner = new AdapterSpinner(provinceEntityList, this);
        sp_province.setAdapter(adapterSpinner);

        edt_number_set = findEditText(R.id.edt_number);

        checkSpecial = findCheckBox(R.id.checkSpecial);
        btnResult = findButton(R.id.btn_result);
        btnResult.setOnClickListener(this);

        dayList = new ArrayList<>();
        dayList.add("Tất cả các ngày");
        dayList.add("Chủ nhật");
        dayList.add("Thứ 2");
        dayList.add("Thứ 3");
        dayList.add("Thứ 4");
        dayList.add("Thứ 5");
        dayList.add("Thứ 6");
        dayList.add("Thứ 7");

        sp_day = findSpinner(R.id.sp_day);
        adapterDay = new AdapterStringCustom(dayList, this);
        sp_day.setAdapter(adapterDay);

        initSpinnerSelect();
    }

    private void initSpinnerSelect() {
        sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                province_code = provinceEntityList.get(position).getMavung();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        day_of_week = "all";
                        break;
                    case 1:
                        day_of_week = "0";
                        break;
                    case 2:
                        day_of_week = "1";
                        break;
                    case 3:
                        day_of_week = "2";
                        break;
                    case 4:
                        day_of_week = "3";
                        break;
                    case 5:
                        day_of_week = "4";
                        break;
                    case 6:
                        day_of_week = "5";
                        break;
                    case 7:
                        day_of_week = "6";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_result) {
            if (checkLengthNotNull()) {
                String url = "";
                if (type_query == 2) {
                    if (checkSpecial.isChecked()) {
                        url = "http://xoso.la/index.php?route=webview/dacbiettheothu&proviceCode=" + province_code + "&thu=" + day_of_week + "&day_count=" + edt_number_set.getText().toString() + "&special=true";
                    } else {
                        url = "http://xoso.la/index.php?route=webview/dacbiettheothu&proviceCode=" + province_code + "&thu=" + day_of_week + "&day_count=" + edt_number_set.getText().toString() + "&special=false";
                    }
                } else if (type_query == 1) {
                    url = "http://xoso.la/index.php?route=webview/theothu&proviceCode=" + province_code + "&thu=" + day_of_week + "&day_count=" + edt_number_set.getText().toString();
                }


//                new FinestWebView.Builder(this)
//                        .updateTitleFromHtml(true)
//                        .webViewJavaScriptEnabled(true)
//                        .webViewAllowContentAccess(true)
//                        .webViewLoadWithOverviewMode(true)
//                        .webViewNeedInitialFocus(true)
//                        .showIconBack(true)
//                        .show(url);
                startActivity(WebViewActivity.class, Constants.TITLE, title, Constants.URL, url);
            }
        }
    }

    private boolean checkLengthNotNull() {
        if (TextUtils.isEmpty(edt_number_set.getText())) {
            showShortToast("Vui lòng không để trống biên độ cầu chạy");
            return false;
        } else {
            return true;
        }
    }

    public void getData() {
        type_query = getIntent().getIntExtra(Constants.ACTION_TYPE, -1);
        switch (type_query) {
            case 1:
                title = "Cầu theo thứ";
                initToolbar(title);
                checkSpecial.setVisibility(View.GONE);
                break;
            case 2:
                title = "Cầu đặc biệt theo thứ";
                checkSpecial.setVisibility(View.VISIBLE);
                initToolbar(title);
                break;
        }
    }

    private void initToolbar(String title) {
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (title != null)
            actionBar.setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
