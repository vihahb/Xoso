package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.JsonHelper;
import com.xproject.xoso.xoso.model.entity.CycleSpecialEntity;
import com.xproject.xoso.xoso.model.entity.SpecialCycleEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.model.respond.RESP_CycleSpecial;
import com.xproject.xoso.xoso.view.adapter.AdapterCycleSpecial;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityCycleSpecialInfo extends BasicActivity {

    private TextView tv_title_speed;
    private AdapterCycleSpecial adapter_begin, adapter_end, adapter_sum;

    RESP_CycleSpecial entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_special_info);
        initToolbar(R.id.toolbar, "Chi tiết chu kỳ đặc biệt");
        initView();
        getData();
    }

    private void getData() {
        entity = (RESP_CycleSpecial) getIntent().getSerializableExtra(Constants.OBJECT);
        String rs = JsonHelper.toJson(entity);
        SpeedTemp temp = (SpeedTemp) getIntent().getSerializableExtra(Constants.TEMP);

        if (temp != null) {
            tv_title_speed.setText("Thống kê chu kỳ đặc biệt " + temp.getName_cat());
        }

        if (entity != null) {
            adapter_begin.refreshData(entity.getData().getBegin_with());
            Log.e("getData: ", "1 " + entity.getData().getBegin_with().get(0).getDay_count());
            adapter_end.refreshData(entity.getData().getEnd_with());
            Log.e("getData: ", "2 " + entity.getData().getEnd_with().get(0).getDay_count());
            adapter_sum.refreshData(entity.getData().getSum_equal());
            Log.e("getData: ", "3 " + entity.getData().getSum_equal().get(0).getDay_count());
        } else {
            showShortToast("Không có dữ liệu");
        }
    }

    private void initView() {

        entity = new RESP_CycleSpecial();

        tv_title_speed = findTextView(R.id.tv_title_speed);
        TextView tv_title_begin = findTextView(R.id.tv_title_begin);
        TextView tv_title_end = findTextView(R.id.tv_title_end);
        TextView tv_title_sum = findTextView(R.id.tv_title_sum);

        String begin = "1. Chu kỳ gần nhất của các số <font color='red'> đầu </font> trong 2 số cuối giải đặc biệt";
        tv_title_begin.setText(Html.fromHtml(begin));

        String end = "2. Chu kỳ gần nhất của các số <font color='red'> cuối </font> trong 2 số cuối giải đặc biệt";
        tv_title_end.setText(Html.fromHtml(end));

        String sum = "3. Chu kỳ gần nhất của các <font color='red'> tổng </font> trong 2 số cuối giải đặc biệt";
        tv_title_sum.setText(Html.fromHtml(sum));

        RecyclerView rcl_begin = findRecyclerView(R.id.rcl_begin);
        rcl_begin.setLayoutManager(new LinearLayoutManager(this));
        adapter_begin = new AdapterCycleSpecial(this);
        rcl_begin.setAdapter(adapter_begin);


        RecyclerView rcl_end = findRecyclerView(R.id.rcl_end);
        rcl_end.setLayoutManager(new LinearLayoutManager(this));
        adapter_end = new AdapterCycleSpecial(this);
        rcl_end.setAdapter(adapter_end);


        RecyclerView rcl_sum = findRecyclerView(R.id.rcl_sum);
        rcl_sum.setLayoutManager(new LinearLayoutManager(this));
        adapter_sum = new AdapterCycleSpecial(this);
        rcl_sum.setAdapter(adapter_sum);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
