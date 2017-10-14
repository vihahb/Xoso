package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.xoso.model.entity.FrequencyEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.view.adapter.AdapterFrequency;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityFrequencyInfo extends BasicActivity {

    private TextView tv_title_speed;
    private RecyclerView rcl_analytics_frequency_value;
    private AdapterFrequency adapter;
    List<FrequencyEntity> frequencyEntityList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequency_info);
        initToolbar(R.id.toolbar, "Chi tiết TK tần suất bộ số");
        initView();
        getData();
    }

    private void getData() {

        SpeedTemp temp = (SpeedTemp) getIntent().getSerializableExtra(Constants.TEMP);
        if (temp!=null){
            tv_title_speed.setText(Html.fromHtml("<b>" + temp.getName_cat() + "</b>" + " trong vòng " + temp.getDate_count() + " ngày trước"));
        }
        frequencyEntityList = (List<FrequencyEntity>) getIntent().getSerializableExtra(Constants.LIST_FREQUENCY);
        if (frequencyEntityList.size() > 0){
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
