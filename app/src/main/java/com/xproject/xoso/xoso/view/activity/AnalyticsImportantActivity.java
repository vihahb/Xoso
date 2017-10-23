package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.SharedUtils;
import com.xproject.xoso.xoso.model.entity.ImportantEntity;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.presenter.activity.AnalyticsImportantActivityPresenter;
import com.xproject.xoso.xoso.view.activity.inf.IAnalyticsImportantActivity;
import com.xproject.xoso.xoso.view.adapter.AdapterSpinner;
import com.xproject.xoso.xoso.view.adapter.AdapterStringCustom;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsImportantActivity extends BasicActivity implements View.OnClickListener, IAnalyticsImportantActivity {

    SpeedTemp temp;
    private Spinner sp_province, sp_analytics_type;
    private Button btn_get_result;
    private List<ProvinceEntity> provinceEntityList;
    private List<String> analytics_type;
    private AnalyticsImportantActivityPresenter presenter;
    private int max = 0, min = 0;
    private int tmp_province_code = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_important_activity);
        initToolbar(R.id.toolbar, getString(R.string.title_activity_analytics_important_activity));
        presenter = new AnalyticsImportantActivityPresenter(this);
        initView();
    }

    private void initView() {
        temp = new SpeedTemp();
        provinceEntityList = presenter.getCategory();
        analytics_type = new ArrayList<>();
        analytics_type.add(0, "27 bộ số xuất hiện nhiều nhất trong 30 ngày trước");
        analytics_type.add(1, "10 bộ số xuất hiện ít nhất trong 30 ngày trước");
        analytics_type.add(2, "Những bộ số không xuất hiện trong vòng 10 lần gần nhất");
        analytics_type.add(3, "Những bộ số xuất hiện liên tiếp");
        analytics_type.add(4, "Những bộ số xuất hiện liên tiếp và kết thúc vào ngày hôm nay");

        sp_province = findSpinner(R.id.sp_province);
        sp_analytics_type = findSpinner(R.id.sp_type_analytics);
        btn_get_result = findButton(R.id.btn_get_result);
        btn_get_result.setOnClickListener(this);

        initAdapter();
    }

    private void initAdapter() {
        AdapterStringCustom adapterType = new AdapterStringCustom(analytics_type, this);
        AdapterSpinner provinceAdapter = new AdapterSpinner(provinceEntityList, this);
        sp_analytics_type.setAdapter(adapterType);
        sp_province.setAdapter(provinceAdapter);

        initSpinnerSelect();
    }

    private void initSpinnerSelect() {
        tmp_province_code = SharedUtils.getInstance().getIntValue(Constants.PROVINCE_FAVORITE_CODE);
        if (tmp_province_code > 0){
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

        sp_analytics_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp.setType(String.valueOf(position));
                temp.setType_name(analytics_type.get(position));
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_result:
                presenter.getImportant(temp);
                break;
        }
    }

    @Override
    public void getImportantSuccess(List<ImportantEntity> list) {
        startActivity(ImportantInfoActivity.class, Constants.IMPORTANT, list, Constants.TEMP, temp);
    }

    @Override
    public void getImportantError(String mes) {
        if (mes != null) {
            showShortToast(mes);
        }
    }

    public int checkMax(List<ImportantEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCount() > max) {
                max = list.get(i).getCount();
            }
        }
        return max;
    }

    public int checkMin(List<ImportantEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCount() < max) {
                min = list.get(i).getCount();
            }
        }
        return max;
    }
}
