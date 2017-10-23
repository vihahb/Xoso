package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.xproject.xoso.sdk.callback.DateTimePickerListener;
import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.SharedUtils;
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.model.respond.RESP_CycleSpecial;
import com.xproject.xoso.xoso.presenter.activity.ActivityCycleSpecialPresenter;
import com.xproject.xoso.xoso.view.activity.inf.IActivityCycleSpecial;
import com.xproject.xoso.xoso.view.adapter.AdapterSpinner;
import com.xtelsolution.xoso.R;

import java.util.Calendar;
import java.util.List;

public class ActivityCycleSpecial extends BasicActivity implements IActivityCycleSpecial, View.OnClickListener {

    Spinner sp_province;
    List<ProvinceEntity> provinceList;
    private EditText edt_query_date;
    private Button btn_result;
    private SpeedTemp temp;
    private AdapterSpinner provinceAdapter;
    private ActivityCycleSpecialPresenter presenter;
    private int tmp_province_code = - 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_special);
        initToolbar(R.id.toolbar, "Chu kỳ đặc biệt");
        presenter = new ActivityCycleSpecialPresenter(this);
        initView();

    }

    private void initView() {
        temp = new SpeedTemp();
        provinceList = presenter.getCategory();
        edt_query_date = findEditText(R.id.edt_date_query);
        edt_query_date.setOnClickListener(this);
        btn_result = findButton(R.id.btn_result);
        btn_result.setOnClickListener(this);
        sp_province = findSpinner(R.id.sp_province);
        provinceAdapter = new AdapterSpinner(provinceList, this);
        sp_province.setAdapter(provinceAdapter);
        initSpinnerSelect();
        setToDayDefault(edt_query_date);
    }

    private void initSpinnerSelect() {
        tmp_province_code = SharedUtils.getInstance().getIntValue(Constants.PROVINCE_FAVORITE_CODE);
        if (tmp_province_code > 0){
            for (int i = 0; i < provinceList.size(); i++) {
                if (provinceList.get(i).getMavung() == tmp_province_code) {
                    sp_province.setSelection(i);
                }
            }
        }
        sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp.setId_cat(provinceList.get(position).getMavung());
                temp.setName_cat(provinceList.get(position).getTenvung());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean checkBeginNotNull() {
        if (TextUtils.isEmpty(edt_query_date.getText().toString())) {
            showShortToast("Vui lòng chọn Ngày xem đến.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void getCycleSpecial(RESP_CycleSpecial entity) {
        startActivity(ActivityCycleSpecialInfo.class, Constants.OBJECT, entity, Constants.TEMP, temp);
    }

    @Override
    public void getCycleSpecialError(String mes) {
        if (mes != null) {
            showShortToast(mes);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_result) {
            checkRequirement();
        } else if (v.getId() == R.id.edt_date_query) {
            TimeUtils.showDateTimePickerDialog(this, new DateTimePickerListener() {
                @Override
                public void onDateTimePickerListener(int year, int month, int day) {
                    temp.setDate_begin(year + "-" + (month + 1) + "-" + day);
                    temp.setDate_format_begin(day + "/" + (month + 1) + "/" + year);
                    edt_query_date.setText(day + "/" + (month + 1) + "/" + year);
                }
            }, false);
        }
    }

    private void checkRequirement() {
        if (checkBeginNotNull()) {
            presenter.getCycleSpecial(temp);
        }
    }

    private void setToDayDefault(EditText edt_query_date) {
        Calendar calendarToday = Calendar.getInstance();
        int toDay = calendarToday.get(Calendar.DAY_OF_MONTH);
        int month = (calendarToday.get(Calendar.MONTH)) + 1;
        int year = calendarToday.get(Calendar.YEAR);

        long milisTimeToday = calendarToday.getTimeInMillis();

        String setDate_begin = year + "-" + month + "-" + toDay;
        String setDate_format_begin = toDay + "/" + month + "/" + year;

        temp.setDate_begin(setDate_begin);
        temp.setDate_format_begin(setDate_format_begin);

        edt_query_date.setText(temp.getDate_format_begin());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
