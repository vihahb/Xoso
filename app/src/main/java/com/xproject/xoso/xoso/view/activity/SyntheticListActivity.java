package com.xproject.xoso.xoso.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.xoso.model.entity.AnalyticsSetNumber;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.view.adapter.AdapterAnalyticsSpeedActivity;
import com.xproject.xoso.xoso.view.adapter.AdapterSynthetic;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

public class SyntheticListActivity extends BasicActivity {

    private static final String TAG = "SyntheticListActivity";
    private RecyclerView rcl_synthetic;
    private AdapterSynthetic adapter;
    private List<AnalyticsSetNumber> list;
    private TextView tv_title, tv_number, tv_count, tv_last_append, tv_not_append, tv_select_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthetic_list);
        initToolbar(R.id.toolbar, "Chi tiết TK tổng hợp");
        initView();
        getData();
    }

    private void initView() {
        tv_select_type = findTextView(R.id.tv_select_type);
        tv_title = findTextView(R.id.tv_title_speed);
        list = new ArrayList<>();
        rcl_synthetic = findRecyclerView(R.id.rcl_analytics_synthetic_value);
        adapter = new AdapterSynthetic(list, this);
        rcl_synthetic.setLayoutManager(new LinearLayoutManager(this));
        rcl_synthetic.setAdapter(adapter);
    }

    public void getData() {

        SpeedTemp temp = (SpeedTemp) getIntent().getSerializableExtra(Constants.TEMP);
        if (temp != null) {
            tv_title.setText(temp.getName_cat() + ", " + temp.getDate_format_begin() + " đến " + temp.getDate_format_end());
            tv_select_type.setText("Loại thống kê: " + temp.getType_name());
        }
        list = (List<AnalyticsSetNumber>) getIntent().getSerializableExtra(Constants.LIST_SPEED);
        if (list.size() > 0) {

            for (int i = list.size() - 1; i > -1; i--) {
                AnalyticsSetNumber setNumber = list.get(i);
                if (setNumber.getLast_appear().equals("")) {
//                    Log.e(TAG, "getData: remove " + true);
                    list.remove(i);
                } else {
//                    Log.e(TAG, "getData: remove " + false);
                }
            }
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
