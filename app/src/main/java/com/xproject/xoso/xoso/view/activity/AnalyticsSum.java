package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.xproject.xoso.sdk.callback.DateTimePickerListener;
import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.SharedUtils;
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.AnalyticsSetNumber;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.presenter.activity.AnalyticsSumActivityPresenter;
import com.xproject.xoso.xoso.view.activity.inf.IAnalyticsSumActivity;
import com.xproject.xoso.xoso.view.adapter.AdapterSpinner;
import com.xtelsolution.xoso.R;

import java.util.Calendar;
import java.util.List;

public class AnalyticsSum extends BasicActivity implements IAnalyticsSumActivity, View.OnClickListener {

    SpeedTemp temp;
    private EditText edt_begin, edt_end, edt_number_set;
    private Spinner sp_province;
    private CheckBox checkSpecial;
    private Button btnResult;
    private AdapterSpinner adapterSpinner;
    private List<ProvinceEntity> provinceEntityList;
    private AnalyticsSumActivityPresenter presenter;
    private int tmp_province_code = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_sum);
        initToolbar(R.id.toolbar, "Thống kê theo tổng");
        presenter = new AnalyticsSumActivityPresenter(this);
        initView();
        findViewById(R.id.constraintLayout).setOnTouchListener(new View.OnTouchListener() {
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
//        provinceEntityList = new ArrayList<>();
        provinceEntityList = presenter.getCategory();
        sp_province = (Spinner) findViewById(R.id.sp_province);
        adapterSpinner = new AdapterSpinner(provinceEntityList, this);
        sp_province.setAdapter(adapterSpinner);

        edt_begin = findEditText(R.id.edt_date_begin);
        edt_begin.setOnClickListener(this);
        edt_end = findEditText(R.id.edt_date_end);
        edt_end.setOnClickListener(this);
        edt_number_set = findEditText(R.id.edt_number_set);

        checkSpecial = findCheckBox(R.id.checkSpecial);
        btnResult = findButton(R.id.btn_result);
        btnResult.setOnClickListener(this);
        initSpinnerSelect();
        setDefaultTime(edt_begin, edt_end);
    }

    private void setDefaultTime(EditText edt_begin, EditText edt_end) {
        Calendar calendarToday = Calendar.getInstance();
        int toDay = calendarToday.get(Calendar.DAY_OF_MONTH);
        int month = (calendarToday.get(Calendar.MONTH)) + 1;
        int year = calendarToday.get(Calendar.YEAR);

        long milisTimeToday = calendarToday.getTimeInMillis();

        long milisTimeOldDay = milisTimeToday - 2592000000L;
        Calendar calendarOldDay = Calendar.getInstance();
        calendarOldDay.setTimeInMillis(milisTimeOldDay);
        int oldDay = calendarOldDay.get(Calendar.DAY_OF_MONTH);
        int oldMonth = (calendarOldDay.get(Calendar.MONTH)) + 1;
        int oldYear = calendarOldDay.get(Calendar.YEAR);


        String begin_date = oldYear + "-" + oldMonth + "-" + oldDay;
        String begin_date_form = oldDay + "/" + oldMonth + "/" + oldYear;

        String end_date = year + "-" + month + "-" + toDay;
        String end_date_form = toDay + "/" + month + "/" + year;

        temp.setDate_begin(begin_date);
        temp.setDate_format_begin(begin_date_form);

        temp.setDate_end(end_date);
        temp.setDate_format_end(end_date_form);

        edt_begin.setText(temp.getDate_format_begin());
        edt_end.setText(temp.getDate_format_end());

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getSumAnalyticsSuccess(List<AnalyticsSetNumber> listSetNumber) {
        startActivity(SpeedListActivity.class, Constants.LIST_SPEED, listSetNumber, Constants.TEMP, temp, Constants.TITLE, 2);
    }

    @Override
    public void getSumError(String message) {
        if (!TextUtils.isEmpty(message)) {
            showShortToast(message);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_result:
                if (checkBeginNotNull() && checkEndNotNull() && checkSumNotNull() && checkBeginEqualEnd()) {
                    if (checkSpecial.isChecked()) {
                        temp.setOnly_special(true);
                    } else {
                        temp.setOnly_special(false);
                    }
                    presenter.getListSetNumber(temp);
                }
                break;
            case R.id.edt_date_begin:
                TimeUtils.showDateTimePickerDialog(this, new DateTimePickerListener() {
                    @Override
                    public void onDateTimePickerListener(int year, int month, int day) {
                        temp.setDate_begin(year + "-" + (month + 1) + "-" + day);
                        temp.setDate_format_begin(day + "/" + (month + 1) + "/" + year);
                        edt_begin.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, false);
                break;
            case R.id.edt_date_end:
                TimeUtils.showDateTimePickerDialog(this, new DateTimePickerListener() {
                    @Override
                    public void onDateTimePickerListener(int year, int month, int day) {
                        temp.setDate_end(year + "-" + (month + 1) + "-" + day);
                        temp.setDate_format_end(day + "/" + (month + 1) + "/" + year);
                        edt_end.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, false);
                break;
        }
    }

    private boolean checkSumNotNull() {
        if (TextUtils.isEmpty(edt_number_set.getText().toString())) {
            showShortToast("Vui lòng nhập tổng muốn xem.");
            return false;
        } else {
            temp.setNumber(edt_number_set.getText().toString());
            return true;
        }
    }

    private boolean checkBeginNotNull() {
        if (TextUtils.isEmpty(edt_begin.getText().toString())) {
            showShortToast("Vui lòng chọn ngày bắt đầu");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkEndNotNull() {
        if (TextUtils.isEmpty(edt_end.getText().toString())) {
            showShortToast("Vui lòng chọn ngày kết thúc");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkBeginEqualEnd() {
        if (temp.getDate_begin() != null && temp.getDate_end() != null) {
            Calendar calendarBegin = TimeUtils.getCalendarFromString(temp.getDate_begin());
            long time_begin = calendarBegin.getTimeInMillis();

            Calendar calendarEnd = TimeUtils.getCalendarFromString(temp.getDate_end());
            long time_end = calendarEnd.getTimeInMillis();

            if (time_begin > time_end) {
                showShortToast("Vui lòng chọn thời gian bắt đầu nhỏ hơn thời gian kết thúc.");
                return false;
            } else if (time_begin <= time_end) {
                return true;
            }

        } else if (temp.getDate_begin() == null) {
            showShortToast("Vui lòng kiểm tra ngày bắt đầu");
            return false;
        } else if (temp.getDate_end() == null) {
            showShortToast("Vui lòng kiểm tra ngày kết thúc");
            return false;
        }
        return false;
    }
}
