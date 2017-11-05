package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.SharedUtils;
import com.xProject.XosoVIP.sdk.utils.TimeUtils;
import com.xProject.XosoVIP.xoso.model.entity.AnalyticsSetNumber;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.presenter.activity.AnalyticsDayPresenter;
import com.xProject.XosoVIP.xoso.view.activity.inf.IAnalyticsDayActivity;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterSpinner;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterStringCustom;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsDayActivity extends BasicActivity implements IAnalyticsDayActivity, View.OnClickListener {

    int day_of_week, week_value;
    SpeedTemp temp;
    private Button btn_result;
    private Spinner sp_province, sp_day, sp_week;
    private AdapterStringCustom adapterDay, adapterWeek;
    private AdapterSpinner provinceAdapter;
    private List<String> dayList, weekList;
    private List<ProvinceEntity> provinceEntityList;
    private AnalyticsDayPresenter presenter;
    private int tmp_province_code = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_day);
        getWindow().setBackgroundDrawableResource(R.mipmap.background_home);
        temp = new SpeedTemp();
        presenter = new AnalyticsDayPresenter(this);
        initToolbar(R.id.toolbar, "Thống kê theo ngày");
        initView();
    }

    private void initView() {
        sp_province = findSpinner(R.id.sp_province);
        provinceEntityList = presenter.getCategory();
        provinceAdapter = new AdapterSpinner(provinceEntityList, this);

        sp_province.setAdapter(provinceAdapter);

        tmp_province_code = SharedUtils.getInstance().getIntValue(Constants.PROVINCE_FAVORITE_CODE);
        if (tmp_province_code > 0) {
            for (int i = 0; i < provinceEntityList.size(); i++) {
                if (provinceEntityList.get(i).getMavung() == tmp_province_code) {
                    sp_province.setSelection(i);
                }
            }
        }
        sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp.setId_cat(provinceEntityList.get(position).getMavung());
                temp.setName_cat(provinceEntityList.get(position).getTenvung());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_result = findButton(R.id.btn_result);
        btn_result.setOnClickListener(this);
        sp_day = findSpinner(R.id.sp_day);
        sp_week = findSpinner(R.id.sp_week);

        dayList = new ArrayList<>();
        dayList.add("Chủ nhật");
        dayList.add("Thứ 2");
        dayList.add("Thứ 3");
        dayList.add("Thứ 4");
        dayList.add("Thứ 5");
        dayList.add("Thứ 6");
        dayList.add("Thứ 7");

        weekList = new ArrayList<>();
        weekList.add("4 tuần trước");
        weekList.add("8 tuần trước");
        weekList.add("12 tuần trước");
        weekList.add("24 tuần trước");
        weekList.add("36 tuần trước");
        weekList.add("48 tuần trước");
        weekList.add("60 tuần trước");
        weekList.add("66 tuần trước");
        weekList.add("72 tuần trước");
        weekList.add("80 tuần trước");

        adapterDay = new AdapterStringCustom(dayList, this);
        adapterWeek = new AdapterStringCustom(weekList, this);

        sp_day.setAdapter(adapterDay);
        sp_week.setAdapter(adapterWeek);


        sp_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day_of_week = position;
                temp.setDay_of_week(String.valueOf(day_of_week));
                temp.setDay_name(dayList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        temp.setWeek("4");
                        break;
                    case 1:
                        temp.setWeek("8");
                        break;
                    case 2:
                        temp.setWeek("12");
                        break;
                    case 3:
                        temp.setWeek("24");
                        break;
                    case 4:
                        temp.setWeek("36");
                        break;
                    case 5:
                        temp.setWeek("48");
                        break;
                    case 6:
                        temp.setWeek("60");
                        break;
                    case 7:
                        temp.setWeek("66");
                        break;
                    case 8:
                        temp.setWeek("72");
                        break;
                    case 9:
                        temp.setWeek("80");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getAnalyticsDay(List<AnalyticsSetNumber> data) {
        startActivity(AnalyticsDayInfo.class, Constants.LIST_SPEED, data, Constants.TEMP, temp);
    }

    @Override
    public void getDayError(String message) {
        if (message != null) {
            showShortToast(message);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_result:
                temp.setDate_end(TimeUtils.getToday());
                presenter.getDay(temp);
                break;
        }
    }
}
