package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
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

import com.xProject.XosoVIP.sdk.callback.DateTimePickerListener;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.TimeUtils;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.presenter.activity.ActivityExploreBridgeLotoPresenter;
import com.xProject.XosoVIP.xoso.view.activity.inf.ActivityExploreBridgeLotoView;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterSpinner;
import com.xProject.XosoVIP.R;

import java.util.Calendar;
import java.util.List;

public class ActivityExploreBridgeLoto extends BasicActivity implements ActivityExploreBridgeLotoView, View.OnClickListener {

    SpeedTemp temp;
    ActivityExploreBridgeLotoPresenter presenter;
    boolean loadingFinished = true;
    boolean redirect = false;
    int action;

    Toolbar toolbar;
    TextInputLayout input_number, input_end;
    private Spinner sp_province;
    private AdapterSpinner adapterSpinner;
    private List<ProvinceEntity> provinceEntityList;
    private EditText edt_date_end, edt_number;
    private Button btnResult;
    private CheckBox checkSpecial;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.mipmap.background_home);
        setContentView(R.layout.activity_explore_bridge_loto);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new ActivityExploreBridgeLotoPresenter(this);
        initView();
        findViewById(R.id.constraintLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                return true;
            }
        });

        getAction();
    }

    private void initToolbar(String title) {

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (title != null)
            actionBar.setTitle(title);
    }

    private void getAction() {
        action = getIntent().getIntExtra(Constants.ACTION_TYPE, -1);
        if (action > 0) {
            switch (action) {
                case 1:
                    title = "Cầu lô tô";
                    initToolbar("Cầu lô tô");
                    break;

                case 2:
                    title = "Cầu loại lô tô";
                    input_end.setHint("Ngày kết thúc");
                    input_number.setHint("Biên độ cầu chạy");
                    initToolbar(title);
                    break;

                case 3:
                    title = "Cầu bạch thủ";
                    input_end.setHint("Ngày kết thúc");
                    input_number.setHint("Biên độ cầu chạy");
                    initToolbar(title);
                    break;

                case 4:
                    title = "Cầu loại bạch thủ";
                    input_end.setHint("Ngày kết thúc");
                    input_number.setHint("Biên độ cầu chạy");
                    initToolbar(title);
                    break;

                case 5:
                    title = "Cầu giải đặc biệt";
                    input_end.setHint("Ngày kết thúc");
                    input_number.setHint("Biên độ cầu chạy");
                    initToolbar(title);
                    checkSpecial.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    title = "Cầu ăn hai nháy";
                    input_end.setHint("Ngày kết thúc");
                    input_number.setHint("Biên độ cầu chạy");
                    initToolbar(title);
                    break;
            }
        }
    }

    private void initView() {
        temp = new SpeedTemp();
//        provinceEntityList = new ArrayList<>();
        provinceEntityList = presenter.getCategory();
        sp_province = (Spinner) findViewById(R.id.sp_province);
        adapterSpinner = new AdapterSpinner(provinceEntityList, this);
        sp_province.setAdapter(adapterSpinner);

        input_number = (TextInputLayout) findViewById(R.id.input_number);
        input_end = (TextInputLayout) findViewById(R.id.input_end);


        checkSpecial = findCheckBox(R.id.checkSpecial);
        checkSpecial.setVisibility(View.GONE);

        edt_date_end = findEditText(R.id.edt_date_end);
        edt_date_end.setOnClickListener(this);

        edt_number = findEditText(R.id.edt_number);

        btnResult = findButton(R.id.btn_result);
        btnResult.setOnClickListener(this);

        initSpinnerSelect();
        setDefaultTime(null, edt_date_end);
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
        String end_date_form = toDay + "/" + n_month + "/" + year;

        temp.setDate_begin(begin_date);
        temp.setDate_format_begin(begin_date_form);

        temp.setDate_end(end_date);
        temp.setDate_format_end(end_date_form);


        if (edt_begin != null) {
            edt_begin.setText(temp.getDate_format_begin());
        }
        if (edt_end != null) {
            edt_end.setText(temp.getDate_format_end());
        }

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
    public void returnUrl(String url) {
        Log.e("URL", "returnUrl: " + url);
        startActivity(WebViewActivity.class, Constants.TITLE, title, Constants.URL, url);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_result:
                if (checkEndNotNull() && checNumberNotNull()) {
                    switch (action) {
                        case 1:
                            presenter.getBridgeLoto(temp);
                            break;
                        case 2:
                            presenter.getTypeLoto(temp);
                            break;
                        case 3:
                            presenter.getBachthu(temp);
                            break;
                        case 4:
                            presenter.getTypeBachthu(temp);
                            break;
                        case 5:
                            if (checkSpecial.isChecked()) {
                                temp.setOnly_special(true);
                            } else {
                                temp.setOnly_special(false);
                            }
                            presenter.getSpecialBridge(temp);
                            break;
                        case 6:
                            presenter.getLoto2Nhay(temp);
                            break;
                    }
                }
                break;
            case R.id.edt_date_end:
                TimeUtils.showDateTimePickerDialog(this, new DateTimePickerListener() {
                    @Override
                    public void onDateTimePickerListener(int year, int month, int day) {
                        String s_month = String.valueOf(month + 1);
                        String s_day = String.valueOf(day);
                        if (s_month.length() == 1){
                            s_month = "0"+s_month;
                        }

                        if (s_day.length() == 1){
                            s_day = "0" + s_day;
                        }
                        temp.setDate_end(year + "-" + s_month + "-" + s_day);
                        temp.setDate_format_end(day + "/" + s_month + "/" + s_day);
                        edt_date_end.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, false);
                break;
        }
    }

    private boolean checNumberNotNull() {
        if (TextUtils.isEmpty(edt_number.getText().toString())) {
            showShortToast("Vui lòng không để trống số ngày cầu chạy.");
            return false;
        } else {
            temp.setDate_count(edt_number.getText().toString());
            return true;
        }
    }

    private boolean checkEndNotNull() {
        if (TextUtils.isEmpty(edt_date_end.getText().toString())) {
            showShortToast("Vui lòng chọn Biên ngày cầu chạy");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
