package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.xoso.model.entity.AnalyticsSetNumber;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterAnalyticsSpeedActivity;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SpeedListActivity extends BasicActivity {
    private RecyclerView rcl_speed;
    private AdapterAnalyticsSpeedActivity adapter;
    private List<AnalyticsSetNumber> list;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_list);
        initToolbar(R.id.toolbar, "Chi tiết thống kê nhanh");
        initView();
        getData();
    }

    private void initView() {
        tv_title = findTextView(R.id.tv_title_speed);

        list = new ArrayList<>();
        rcl_speed = findRecyclerView(R.id.rcl_analytics_speed_value);
        adapter = new AdapterAnalyticsSpeedActivity(list, this);
        rcl_speed.setLayoutManager(new LinearLayoutManager(this));
        rcl_speed.setAdapter(adapter);
    }


    public void getData() {
        int type_title = getIntent().getIntExtra(Constants.TITLE, 1);
        if (type_title == 1) {
            initToolbar(R.id.toolbar, "Chi tiết thống kê nhanh");
        } else if (type_title == 2) {
            initToolbar(R.id.toolbar, "Chi tiết thống kê theo tổng");
        } else if (type_title == 3) {
            initToolbar(R.id.toolbar, "Chi tiết TK tổng hợp");
        }

        SpeedTemp temp = (SpeedTemp) getIntent().getSerializableExtra(Constants.TEMP);
        if (temp != null) {
            tv_title.setText(temp.getName_cat() + ", " + temp.getDate_format_begin() + " đến " + temp.getDate_format_end());
        }
        list = (List<AnalyticsSetNumber>) getIntent().getSerializableExtra(Constants.LIST_SPEED);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = list.size(); j == 0; j--) {
                    if (list.get(i).getNumber().equals(list.get(j).getNumber())) {
                        list.remove(j);
                    }
                }
            }
            Collections.sort(list, new Comparator<AnalyticsSetNumber>() {
                @Override
                public int compare(AnalyticsSetNumber o1, AnalyticsSetNumber o2) {
                    if (Integer.parseInt(o1.getNumber()) == Integer.parseInt(o2.getNumber()))
                        return 0;

                    return Integer.parseInt(o1.getNumber()) < Integer.parseInt(o2.getNumber()) ? -1 : 1;
                }
            });
            adapter.refreshData(list);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
