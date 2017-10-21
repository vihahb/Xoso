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
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.AnalyticsSetNumber;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.presenter.activity.AnalyticsSyntheticPresenter;
import com.xproject.xoso.xoso.view.activity.inf.IAnalyticsSynthetic;
import com.xproject.xoso.xoso.view.adapter.AdapterSpinner;
import com.xproject.xoso.xoso.view.adapter.AdapterStringCustom;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AnalyticsSynthetic extends BasicActivity implements View.OnClickListener, IAnalyticsSynthetic {
    private EditText edt_begin, edt_end;
    private Spinner sp_province, sp_type;
    private CheckBox checkSpecial;
    private Button btnResult;
    private AdapterSpinner adapterSpinner;
    private List<ProvinceEntity> provinceEntityList;
    private List<String> type;
    private AnalyticsSyntheticPresenter presenter;
    private AdapterStringCustom adapterStringCustom;
    private SpeedTemp temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_synthetic);

        initToolbar(R.id.toolbar, getString(R.string.title_activity_analytics_synthetic));
        presenter = new AnalyticsSyntheticPresenter(this);
        temp = new SpeedTemp();
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
        type = new ArrayList<>();
        type.add(0, "Tổng chẵn");
        type.add(1, "Tổng lẻ");
        type.add(2, "Chẵn chẵn");
        type.add(3, "Chẵn lẻ");
        type.add(4, "Lẻ chẵn");
        type.add(5, "Lẻ lẻ");
        type.add(6, "Bộ kép");
        type.add(7, "Sát kép");

        provinceEntityList = presenter.getCategory();
        sp_province = (Spinner) findViewById(R.id.sp_province);
        adapterSpinner = new AdapterSpinner(provinceEntityList, this);
        sp_province.setAdapter(adapterSpinner);

        sp_type = findSpinner(R.id.sp_type);
        adapterStringCustom = new AdapterStringCustom(type, this);
        sp_type.setAdapter(adapterStringCustom);

        edt_begin = findEditText(R.id.edt_date_begin);
        edt_begin.setOnClickListener(this);
        edt_end = findEditText(R.id.edt_date_end);
        edt_end.setOnClickListener(this);

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

        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp.setType_name(type.get(position));
                switch (position) {
                    case 0:
                        temp.setType("tong_chan");
                        break;
                    case 1:
                        temp.setType("tong_le");
                        break;
                    case 2:
                        temp.setType("chan_chan");
                        break;
                    case 3:
                        temp.setType("chan_le");
                        break;
                    case 4:
                        temp.setType("le_chan");
                        break;
                    case 5:
                        temp.setType("le_le");
                        break;
                    case 6:
                        temp.setType("bo_kep");
                        break;
                    case 7:
                        temp.setType("sat_kep");
                        break;
                }
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
                if (checkBeginNotNull() && checkEndNotNull() && checkBeginEqualEnd()) {
                    if (checkSpecial.isChecked()) {
                        temp.setOnly_special(true);
                    } else {
                        temp.setOnly_special(false);
                    }
                    presenter.getSynthetic(temp);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
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

    @Override
    public void getSynthetics(List<AnalyticsSetNumber> data) {
        startActivity(SyntheticListActivity.class, Constants.LIST_SPEED, data, Constants.TEMP, temp);
    }

    @Override
    public void getSyntheticsError(String message) {
        if (!TextUtils.isEmpty(message)) {
            showShortToast(message);
        }
    }
}
