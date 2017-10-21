package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.xoso.model.entity.CycleLotoEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.view.adapter.AdapterCycleLoto;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

public class CycleLotoListInfo extends BasicActivity {

    List<CycleLotoEntity> lotoEntityList;
    private RecyclerView rcl_cycle_loto;
    private AdapterCycleLoto adapterCycleLoto;
    private SpeedTemp temp;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_loto_list_info);
        initToolbar(R.id.toolbar, "Chi tiết chu kỳ lô tô");
        initView();
        getData();
    }

    private void getData() {
        List<CycleLotoEntity> data = (List<CycleLotoEntity>) getIntent().getSerializableExtra(Constants.LIST_SPEED);
        temp = (SpeedTemp) getIntent().getSerializableExtra(Constants.TEMP);

        if (temp != null) {
            tv_title.setText("Thống kê chu kỳ lô tô " + temp.getName_cat());
        }

        if (data != null) {
            adapterCycleLoto.refreshData(data);
        } else {
            showShortToast("Không có dữ liệu");
        }
    }

    private void initView() {

        tv_title = findTextView(R.id.tv_title_speed);

        lotoEntityList = new ArrayList<>();

        rcl_cycle_loto = findRecyclerView(R.id.rcl_cycle_loto);
        rcl_cycle_loto.setLayoutManager(new LinearLayoutManager(this));
        adapterCycleLoto = new AdapterCycleLoto(lotoEntityList, this);
        rcl_cycle_loto.setAdapter(adapterCycleLoto);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
