package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.xoso.model.entity.AnalyticsSetNumber;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.view.adapter.AdapterDayAnalytics;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsDayInfo extends BasicActivity {

    private RecyclerView rcl_27_value, rcl_10_value;
    private TextView tv_title, tv_title_27;
    List<AnalyticsSetNumber> list, list_10;
    private AdapterDayAnalytics adapter_27, adapter_10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_day_info);
        initToolbar(R.id.toolbar, "Chi tiết thống kê theo ngày");
        initView();
        getData();
    }

    private void getData() {
        SpeedTemp temp = (SpeedTemp) getIntent().getSerializableExtra(Constants.TEMP);
        if (temp!=null){
            tv_title.setText(temp.getName_cat() + " cho tất cả các " + temp.getDay_name() + " trong " + temp.getWeek() + " tuần trước");
        }
        List<AnalyticsSetNumber> extraList = (List<AnalyticsSetNumber>) getIntent().getSerializableExtra(Constants.LIST_SPEED);
        if (extraList.size() > 0){
            List<AnalyticsSetNumber> numberList27 = new ArrayList<>();
            for (int i = 0; i < extraList.size(); i++) {
                numberList27.add(extraList.get(i));
                if (numberList27.size() == 27){
                    break;
                }
            }
            adapter_27.refreshData(numberList27);


            List<AnalyticsSetNumber> numberList10 = new ArrayList<>();
            for (int i = extraList.size()-1; i >= 0; i--) {
                numberList10.add(extraList.get(i));
                if (numberList10.size() == 10){
                    break;
                }
            }

            adapter_10.refreshData(numberList10);
        }
    }

    private void initView() {
        list = new ArrayList<>();
        list_10 = new ArrayList<>();
        tv_title = findTextView(R.id.tv_title_speed);
        tv_title_27 = findTextView(R.id.tv_title_day);

        rcl_27_value = findRecyclerView(R.id.rcl_day_27);
        adapter_27 = new AdapterDayAnalytics(list, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        rcl_27_value.setLayoutManager(layoutManager);
        rcl_27_value.setAdapter(adapter_27);

        rcl_10_value = findRecyclerView(R.id.rcl_day_10);
        adapter_10 = new AdapterDayAnalytics(list_10, this);
        GridLayoutManager layoutManager_10 = new GridLayoutManager(this, 4);
        rcl_10_value.setLayoutManager(layoutManager_10);
        rcl_10_value.setAdapter(adapter_10);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
