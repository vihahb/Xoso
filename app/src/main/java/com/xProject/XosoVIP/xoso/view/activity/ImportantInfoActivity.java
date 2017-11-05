package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.xoso.model.entity.ImportantEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterImportant;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.List;

public class ImportantInfoActivity extends BasicActivity {

    private RecyclerView rcl_important;
    private TextView tv_title, tv_type, tv_date_not_appen;
    private AdapterImportant adapterImportant;
    private List<ImportantEntity> list;
    private SpeedTemp temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.mipmap.background_home);
        setContentView(R.layout.activity_important_info);
        initToolbar(R.id.toolbar, getString(R.string.title_activity_important_info));
        initView();
        getData();
    }

    private void getData() {
        temp = (SpeedTemp) getIntent().getSerializableExtra(Constants.TEMP);
        list = (List<ImportantEntity>) getIntent().getSerializableExtra(Constants.IMPORTANT);
        switch (temp.getType()) {
            case "0":
                adapterImportant.refresh(list, 0);
                tv_date_not_appen.setText("Số ngày chưa về");
                break;
            case "1":
                adapterImportant.refresh(list, 1);
                tv_date_not_appen.setText("Số ngày không về");
                break;
            case "2":
                adapterImportant.refresh(list, 2);
                tv_date_not_appen.setText("Số lần quay không về");
                break;
            case "3":
                adapterImportant.refresh(list, 3);
                tv_date_not_appen.setText("Số ngày về liên tiếp");
                break;
            case "4":
                adapterImportant.refresh(list, 4);
                tv_date_not_appen.setText("Số ngày về liên tiếp");
                break;
        }

        tv_title.setText(Html.fromHtml("<b>" + temp.getName_cat() + "</b> trong vòng 30 lần quay số trước"));
        tv_type.setText(temp.getType_name());
    }

    private void initView() {
        list = new ArrayList<>();
        rcl_important = findRecyclerView(R.id.rcl_important);
        tv_title = findTextView(R.id.tv_title_speed);
        tv_type = findTextView(R.id.tv_select_type);
        tv_date_not_appen = findTextView(R.id.tv_date_not_appen);
        adapterImportant = new AdapterImportant(this, list);
        rcl_important.setLayoutManager(new LinearLayoutManager(this));
        rcl_important.setAdapter(adapterImportant);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
