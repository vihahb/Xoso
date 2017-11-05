package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.xProject.XosoVIP.R;
import com.xProject.XosoVIP.sdk.callback.DateTimePickerListener;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.sdk.utils.SharedUtils;
import com.xProject.XosoVIP.sdk.utils.TimeUtils;
import com.xProject.XosoVIP.sdk.utils.Utils;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.presenter.activity.ActivityExploreBridgeLotoPresenter;
import com.xProject.XosoVIP.xoso.view.activity.inf.ActivityExploreBridgeLotoView;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterSpinner;

import java.util.Calendar;
import java.util.List;

public class BridHistorygeActivity extends BasicActivity implements View.OnClickListener, ActivityExploreBridgeLotoView {

    TextInputLayout input_number, input_end;
    private Spinner sp_province;
    private AdapterSpinner adapterSpinner;
    private List<ProvinceEntity> provinceEntityList;
    private EditText edt_date_end, edt_date_begin, edt_position_1, edt_position_2;
    private Button btnResult;
    private CheckBox checkSpecial;
    private String title;
    private ActivityExploreBridgeLotoPresenter presenter;
    private AdapterSpinner provinceAdapter;
    private SpeedTemp temp;
    private int tmp_province_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.mipmap.background_home);
        title = "Lịch sử cầu";
        setContentView(R.layout.activity_brid_historyge);
        initToolbar(R.id.toolbar, title);
        temp = new SpeedTemp();
        initVIew();
        presenter = new ActivityExploreBridgeLotoPresenter(this);
    }

    private void initVIew() {
        provinceEntityList = DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);

        sp_province = findSpinner(R.id.sp_province);
        provinceAdapter = new AdapterSpinner(provinceEntityList, this);
        sp_province.setAdapter(provinceAdapter);


        edt_date_begin = findEditText(R.id.edt_date_begin);
        edt_date_begin.setOnClickListener(this);
        edt_date_end = findEditText(R.id.edt_date_end);
        edt_date_end.setOnClickListener(this);

        edt_position_1 = findEditText(R.id.edt_position_1);
        edt_position_2 = findEditText(R.id.edt_position_2);

        checkSpecial = findCheckBox(R.id.checkSpecial);

        btnResult = findButton(R.id.btn_result);
        btnResult.setOnClickListener(this);

        edt_position_1.requestFocus();
        Utils.showKeyBoard(this, edt_position_1);

        initSpinnerSelect();
        setDefaultTime(edt_date_begin, edt_date_end);
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


        String begin_date = "";
        String old_month = String.valueOf(oldMonth);
        String old_day = String.valueOf(oldDay);
        if (old_month.length() == 1) {
            old_month = "0" + old_month;
        }
        if (old_day.length() == 1) {
            old_day = "0" + old_day;
        }
        begin_date = oldYear + "-" + old_month + "-" + old_day;
        String begin_date_form = oldDay + "/" + oldMonth + "/" + oldYear;


        String end_date = "";
        String n_month = String.valueOf(month);
        String n_day = String.valueOf(toDay);
        if (n_month.length() == 1) {
            n_month = "0" + n_month;
        }
        if (n_day.length() == 1) {
            n_day = "0" + n_day;
        }
        end_date = year + "-" + n_month + "-" + n_day;
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
        if (tmp_province_code > 0) {
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_result:
                if (checkBeginNotNull() && checkEndNotNull() && checPositionNotNull() && checkBeginEqualEnd()) {
                    temp.setPosition_1(Integer.parseInt(edt_position_1.getText().toString()));
                    temp.setPosition_2(Integer.parseInt(edt_position_2.getText().toString()));
                    if (checkSpecial.isChecked()) {
                        temp.setOnly_special(true);
                    } else {
                        temp.setOnly_special(false);
                    }
                    presenter.getHistory(temp);
                }
                break;
            case R.id.edt_date_begin:
                TimeUtils.showDateTimePickerDialog(this, new DateTimePickerListener() {
                    @Override
                    public void onDateTimePickerListener(int year, int month, int day) {
                        temp.setDate_begin(year + "-" + (month + 1) + "-" + day);
                        temp.setDate_format_begin(day + "/" + (month + 1) + "/" + year);
                        edt_date_begin.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, false);
                break;
            case R.id.edt_date_end:
                TimeUtils.showDateTimePickerDialog(this, new DateTimePickerListener() {
                    @Override
                    public void onDateTimePickerListener(int year, int month, int day) {
                        temp.setDate_end(year + "-" + (month + 1) + "-" + day);
                        temp.setDate_format_end(day + "/" + (month + 1) + "/" + year);
                        edt_date_end.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, false);
                break;
        }
    }

    private boolean checPositionNotNull() {
        if (edt_position_1.getText().toString().length() == 0) {
            showShortToast("Vị trí 1 không được để trống");
            return false;
        }

        if (edt_position_2.getText().toString().length() == 0) {
            showShortToast("Vị trí 2 không được để trống");
            return false;
        }
        return true;
    }

    private boolean checkBeginNotNull() {
        if (TextUtils.isEmpty(edt_date_begin.getText().toString())) {
            showShortToast("Vui lòng chọn ngày bắt đầu");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkEndNotNull() {
        if (TextUtils.isEmpty(edt_date_end.getText().toString())) {
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

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void returnUrl(String url) {
        startActivity(WebViewActivity.class, Constants.TITLE, title, Constants.URL, url);
    }
}
