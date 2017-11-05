package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.xoso.model.entity.FrequencyEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterFrequency;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ActivityFrequencyInfo extends BasicActivity {

    List<FrequencyEntity> frequencyEntityList;
    private TextView tv_title_speed;
    private RecyclerView rcl_analytics_frequency_value;
    private AdapterFrequency adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.mipmap.background_home);
        setContentView(R.layout.activity_frequency_info);
        initToolbar(R.id.toolbar, "Chi tiết TK tần suất bộ số");
        initView();
        getData();
    }

    private void getData() {

        SpeedTemp temp = (SpeedTemp) getIntent().getSerializableExtra(Constants.TEMP);
        if (temp != null) {
            tv_title_speed.setText(Html.fromHtml("<b>" + temp.getName_cat() + "</b>" + " trong vòng " + temp.getDate_count() + " ngày trước"));
        }
        frequencyEntityList = (List<FrequencyEntity>) getIntent().getSerializableExtra(Constants.LIST_FREQUENCY);
        if (frequencyEntityList.size() > 0) {
            Collections.sort(frequencyEntityList, new Comparator<FrequencyEntity>() {
                @Override
                public int compare(FrequencyEntity o1, FrequencyEntity o2) {
                    if (Integer.parseInt(o1.getNumber()) == Integer.parseInt(o2.getNumber()))
                        return 0;

                    return Integer.parseInt(o1.getNumber()) < Integer.parseInt(o2.getNumber()) ? -1 : 1;
                }
            });
            adapter.refreshData(frequencyEntityList);
        }
    }

    private void initView() {
        frequencyEntityList = new ArrayList<>();
        tv_title_speed = findTextView(R.id.tv_title_speed);
        rcl_analytics_frequency_value = findRecyclerView(R.id.rcl_analytics_frequency_value);
        rcl_analytics_frequency_value.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterFrequency(frequencyEntityList, this);
        rcl_analytics_frequency_value.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
