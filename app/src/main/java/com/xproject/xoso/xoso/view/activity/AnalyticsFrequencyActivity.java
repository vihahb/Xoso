package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.xoso.model.entity.FrequencyEntity;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.presenter.activity.AnalyticsFrequencyActivityPresenter;
import com.xproject.xoso.xoso.view.activity.inf.IAnalyticsFrequencyACtivity;
import com.xproject.xoso.xoso.view.adapter.AdapterSpinner;
import com.xtelsolution.xoso.R;

import java.util.List;

public class AnalyticsFrequencyActivity extends BasicActivity implements IAnalyticsFrequencyACtivity, View.OnClickListener {

    private EditText edt_number, edt_date;
    private AdapterSpinner provinceSpinner;
    private CheckBox checkSpecial;
    private List<ProvinceEntity> provinceEntityList;
    private Spinner sp_province;
    private AnalyticsFrequencyActivityPresenter presenter;
    private SpeedTemp temp;
    private Button btnGetResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_frequency);
        presenter = new AnalyticsFrequencyActivityPresenter(this);
        initToolbar(R.id.toolbar, "Thống kê tần suất bộ số");
        initView();
        findViewById(R.id.onstraintLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });
    }

    private void initView() {
        temp = new SpeedTemp();
        provinceEntityList = presenter.getCategory();
        edt_number = findEditText(R.id.edt_number_set);
        edt_date = findEditText(R.id.edt_day_ago);
        checkSpecial = findCheckBox(R.id.checkSpecial);
        sp_province = findSpinner(R.id.sp_province);

        provinceSpinner = new AdapterSpinner(provinceEntityList, this);
        sp_province.setAdapter(provinceSpinner);

        btnGetResult = findButton(R.id.btn_get_result);
        btnGetResult.setOnClickListener(this);
        initSpinnerSelect();
    }

    private void initSpinnerSelect() {
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
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getFrequencysuccess(List<FrequencyEntity> data) {
        startActivity(ActivityFrequencyInfo.class, Constants.LIST_FREQUENCY, data, Constants.TEMP, temp);
    }

    @Override
    public void getFrequencyError(String message) {
        if (!TextUtils.isEmpty(message)) {
            showShortToast(message);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_result:
                if (checkDayAgoNotNull()) {
                    if (checkSpecial.isChecked()) {
                        temp.setOnly_special(true);
                    } else {
                        temp.setOnly_special(false);
                    }

                    String result = edt_number.getText().toString();

                    String[] list_result = result.split("\\.");
                    Log.e("abc", "onClick: " + list_result.length);

                    if (list_result.length == 0) {
                        // không nhập
                        Log.e("abc", "onClick: 2 " + list_result.length);
                        return;
                    } else {
                        Log.e("abc", "onClick: 3 " + list_result.length);
                        for (int i = list_result.length - 1; i >= 0; i--) {
                            if (list_result[i].length() > 2) {
                                // Nhập nhiều hơn 2 số
                                showShortToast("Sai định dạng bộ số. Vui lòng nhập đúng định dạng.");
                                return;
                            }
                        }
                    }

                    result = result.replace(".", ",");
                    temp.setNumber(result);
                    presenter.getFrequency(temp);
                }
                break;
        }
    }

    private boolean checkNumberNotNull() {
        if (TextUtils.isEmpty(edt_number.getText().toString())) {
            return false;
        } else {
            temp.setNumber(edt_number.getText().toString());
            return true;
        }
    }

    private boolean checkDayAgoNotNull() {
        if (TextUtils.isEmpty(edt_date.getText().toString())) {
            showShortToast("Vui lòng nhập số ngày");
            return false;
        }
        if (edt_date.getText().toString().equals("0")) {
            showShortToast("Vui lòng nhập số ngày lớn hơn 0");
            return false;
        }
        temp.setDate_count(edt_date.getText().toString());
        return true;
    }
}