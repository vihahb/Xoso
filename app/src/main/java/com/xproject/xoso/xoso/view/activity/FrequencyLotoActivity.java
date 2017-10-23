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
import com.xproject.xoso.xoso.model.respond.RESP_FrequencyLoto;
import com.xproject.xoso.xoso.presenter.activity.FrequencyLotoActivityPresenter;
import com.xproject.xoso.xoso.view.activity.inf.IFrequencyLotoActivity;
import com.xproject.xoso.xoso.view.adapter.AdapterSpinner;
import com.xproject.xoso.xoso.view.adapter.AdapterStringCustom;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FrequencyLotoActivity extends BasicActivity implements View.OnClickListener, IFrequencyLotoActivity {

    List<ProvinceEntity> provinceEntityList;
    SpeedTemp temp;
    FrequencyLotoActivityPresenter presenter;
    private EditText edt_begin, edt_end, edt_number;
    private Spinner sp_province, sp_day;
    private Button btnResult;
    private AdapterSpinner provinceAdapter;
    private AdapterStringCustom adapterDay;
    private List<String> dayList;
    private int day_of_week;
    private int tmp_province_code = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequency_loto);
        temp = new SpeedTemp();
        initToolbar(R.id.toolbar, "Tần số nhịp lô tô");
        presenter = new FrequencyLotoActivityPresenter(this);
        initView();
    }

    private void initView() {
        edt_begin = findEditText(R.id.edt_date_begin);
        edt_begin.setOnClickListener(this);
        edt_end = findEditText(R.id.edt_date_end);
        edt_end.setOnClickListener(this);
        edt_number = findEditText(R.id.edt_number_set);

        btnResult = findButton(R.id.btn_result);
        btnResult.setOnClickListener(this);

        provinceEntityList = presenter.getCategory();

        sp_province = findSpinner(R.id.sp_province);
        provinceAdapter = new AdapterSpinner(provinceEntityList, this);
        sp_province.setAdapter(provinceAdapter);

        sp_day = findSpinner(R.id.sp_day);
        dayList = new ArrayList<>();
        dayList.add("Tất cả các thứ");
        dayList.add("Chủ nhật");
        dayList.add("Thứ 2");
        dayList.add("Thứ 3");
        dayList.add("Thứ 4");
        dayList.add("Thứ 5");
        dayList.add("Thứ 6");
        dayList.add("Thứ 7");
        adapterDay = new AdapterStringCustom(dayList, this);
        sp_day.setAdapter(adapterDay);
        sp_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day_of_week = position;
                switch (position) {
                    case 0:
                        temp.setDay_of_week("7");
                        break;
                    case 1:
                        temp.setDay_of_week("0");
                        break;
                    case 2:
                        temp.setDay_of_week("1");
                        break;
                    case 3:
                        temp.setDay_of_week("2");
                        break;
                    case 4:
                        temp.setDay_of_week("3");
                        break;
                    case 5:
                        temp.setDay_of_week("4");
                        break;
                    case 6:
                        temp.setDay_of_week("5");
                        break;
                    case 7:
                        temp.setDay_of_week("6");
                        break;
                }
                temp.setDay_name(dayList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_result:
                if (checkBeginNotNull() && checkEndNotNull() && checkSumNotNull() && checkBeginEqualEnd()) {
                    if (temp.getDay_of_week().equals("7")) {
                        presenter.getFrequencyLotoAllDay(temp);
                    } else {
                        presenter.getFrequencyLotoOneDay(temp);
                    }
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

    @Override
    public void getFrequencyLoto(RESP_FrequencyLoto obj) {
        startActivity(FrequencyLotoInfoActivity.class, Constants.LIST_SPEED, obj.getData(), Constants.TEMP, temp);
    }

    @Override
    public void getFrequencyLotoError(String message) {
        if (message != null) {
            showShortToast(message);
        }
    }

    private boolean checkSumNotNull() {
        if (TextUtils.isEmpty(edt_number.getText().toString())) {
            showShortToast("Vui lòng nhập cặp số khảo sát muốn xem.");
            return false;
        } else {
            temp.setNumber(edt_number.getText().toString());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
