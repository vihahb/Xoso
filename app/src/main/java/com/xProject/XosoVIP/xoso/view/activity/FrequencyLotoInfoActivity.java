package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.xoso.model.entity.FrequencyLotoEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterFrequencyLoto;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.List;

public class FrequencyLotoInfoActivity extends BasicActivity {

    private TextView tv_title_speed, tv_total_appends;
    private RecyclerView rcl_frequency_loto;
    private SpeedTemp temp;
    private List<FrequencyLotoEntity> list;
    private AdapterFrequencyLoto adapterFrequencyLoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.mipmap.background_home);
        setContentView(R.layout.activity_frequency_loto_info);
        initToolbar(R.id.toolbar, "Chi tiết tần số nhịp lô tô");
        initView();
        getData();
    }

    private void getData() {
        List<FrequencyLotoEntity> fre_list = (List<FrequencyLotoEntity>) getIntent().getSerializableExtra(Constants.LIST_SPEED);
        temp = (SpeedTemp) getIntent().getSerializableExtra(Constants.TEMP);
        if (temp != null) {
            if (temp.getNumber().length() < 2) {
                tv_title_speed.setText("Thống kê tần số nhịp " + temp.getName_cat() + " bộ số 0" + temp.getNumber());
            } else {
                tv_title_speed.setText("Thống kê tần số nhịp " + temp.getName_cat() + " bộ số " + temp.getNumber());
            }
        }
        if (fre_list.size() > 0) {
            for (int i = 0; i < fre_list.size(); i++) {
                if (fre_list.get(i).getDate_between() == null) {
                    fre_list.remove(i);
                }
            }
            adapterFrequencyLoto.refreshData(fre_list);
            if (getTotalAppend(fre_list) != -1) {
                tv_total_appends.setText(getTotalAppend(fre_list) + " lần");
            }
        }
    }

    private void initView() {
        list = new ArrayList<>();
        temp = new SpeedTemp();
        tv_title_speed = findTextView(R.id.tv_title_speed);
        tv_total_appends = findTextView(R.id.tv_total_appends);

        rcl_frequency_loto = findRecyclerView(R.id.rcl_analytics_fre_loto);
        rcl_frequency_loto.setLayoutManager(new LinearLayoutManager(this));
        adapterFrequencyLoto = new AdapterFrequencyLoto(list, this);
        rcl_frequency_loto.setAdapter(adapterFrequencyLoto);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private int getTotalAppend(List<FrequencyLotoEntity> lotoEntityList) {
        int total = -1;
        for (int i = 0; i < lotoEntityList.size(); i++) {
            total += lotoEntityList.get(i).getCount();
        }
        return total;
    }
}
