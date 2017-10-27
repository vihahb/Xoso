package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.text.Html;
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
import android.widget.TextView;

import com.xproject.xoso.sdk.callback.DateTimePickerListener;
import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.SharedUtils;
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.sdk.utils.Utils;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.model.respond.RESP_CycleLotoVip;
import com.xproject.xoso.xoso.presenter.activity.ActivityCycleListSpecialPresenter;
import com.xproject.xoso.xoso.view.activity.inf.IActivityCycleListSpecial;
import com.xproject.xoso.xoso.view.adapter.AdapterSpinner;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CycleListSpecialActivity extends BasicActivity implements IActivityCycleListSpecial, View.OnClickListener {
    SpeedTemp temp;
    private EditText edt_begin, edt_end, edt_number_set;
    private TextView tv_title_result, tv_number_set, tv_description, tv_gan_score;
    private Spinner sp_province;
    private Button btnResult;
    private AdapterSpinner adapterSpinner;
    private List<ProvinceEntity> provinceEntityList;
    private CheckBox checkAll;
    private ActivityCycleListSpecialPresenter presenter;
    private int local_type = -1;
    private int tmp_province_code = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_list_special);
        initToolbar(R.id.toolbar, "");
        presenter = new ActivityCycleListSpecialPresenter(this);
        initView();
        getData();
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

        checkAll = findCheckBox(R.id.checkbox_all);
        btnResult = findButton(R.id.btn_result);
        btnResult.setOnClickListener(this);

        tv_title_result = findTextView(R.id.tv_title_result);
        tv_description = findTextView(R.id.tv_description);
        tv_number_set = findTextView(R.id.tv_number_set);
        tv_gan_score = findTextView(R.id.tv_gan_score);
        initSpinnerSelect();
        setDefaultTime(edt_begin, edt_end);
        edt_number_set.requestFocus();
        Utils.showKeyBoard(this, edt_number_set);
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
    public void getListCycleSpecial(RESP_CycleLotoVip obj) {
        if (obj.isSuccess() && obj.getMessage() != null) {
            tv_description.setText("");
            tv_gan_score.setText("");
            tv_number_set.setText("");
            tv_title_result.setText(obj.getMessage());
        } else {
            String description;
            if (obj.getData().getStart().equals(obj.getData().getEnd())) {
                if (local_type == 1) {
                    description = "     Chỉ xuất hiện cùng nhau một lần trong ngày " + TimeUtils.getFormatTimeClient(obj.getData().getEnd())
                            + ". \n     Lần cuối cùng xuất hiện dàn số theo khoảng ngày bạn đã chọn là ngày: " + TimeUtils.getFormatTimeClient(obj.getData().getLast_appear());
                } else {
                    description = "     Chỉ xuất hiện cùng nhau một lần trong giải đặc biệt ngày " + TimeUtils.getFormatTimeClient(obj.getData().getEnd())
                            + ". \n     Lần xuất hiện cuối cùng xuất hiện dàn số theo khoảng ngày bạn đã chọn là ngày: " + TimeUtils.getFormatTimeClient(obj.getData().getLast_appear());
                }
            } else {
                description = "     Ngưỡng cực đại không xuất hiện dàn số là " + obj.getData().getMax_gan()
                        + " ngày, tính cả ngày về, từ " + TimeUtils.getFormatTimeClient(obj.getData().getStart())
                        + " đến " + TimeUtils.getFormatTimeClient(obj.getData().getEnd())
                        + ". \n     Lần xuất hiện cuối cùng xuất hiện dàn số theo khoảng ngày bạn đã chọn là ngày: " + TimeUtils.getFormatTimeClient(obj.getData().getLast_appear());
            }
            tv_description.setText(description);

            String gan_score = "     Điểm gan đến nay là " + obj.getData().getCurrent_gan()
                    + " ngày, không tính lần về gần nhất là ngày: " + TimeUtils.getFormatTimeClient(obj.getData().getLast_appear_not_between());
            tv_gan_score.setText(gan_score);

            tv_number_set.setText(Html.fromHtml("Dàn số: <font color='#eb2227'>" + temp.getNumber() + "</font>"));
//            if (temp.getNumber().length() < 2) {
//                tv_number_set.setText(Html.fromHtml("Dàn số: <font color='#eb2227'>0" + temp.getNumber() + "</font>"));
//            } else {
//
//            }
            String title_result = "<b>Dữ liệu kết quả " + temp.getName_cat() + "</b>"
                    + "<br>từ ngày " + temp.getDate_format_begin()
                    + " đến ngày " + temp.getDate_format_end() + ":";
            tv_title_result.setText(Html.fromHtml(title_result));
        }
    }

    @Override
    public void getListCycleSpecialError(String mes) {
        if (mes != null) {
            tv_title_result.setText(mes);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_result:
                if (checkBeginNotNull() && checkEndNotNull() && checkNumberNotNull() && checkBeginEqualEnd()) {
                    if (checkAll.isChecked()) {
                        temp.setAllOrOne("all");
                    } else {
                        temp.setAllOrOne("one");
                    }

                    String result = edt_number_set.getText().toString();

                    while (result.indexOf("..") >= 0) {
                        result = result.replaceAll("\\.\\.", ".");
                    }

                    String[] list_result = result.split("\\.");
                    Log.e("abc", "onClick: " + list_result.length);

                    if (list_result.length > 0) {
                        Log.e("abc", "onClick: 3 " + list_result.length);
                        for (int i = list_result.length - 1; i >= 0; i--) {
                            if (list_result[i].length() > 2) {
                                // Nhập nhiều hơn 2 số
                                showShortToast("Sai định dạng bộ số. Vui lòng nhập đúng định dạng.");
                                return;
                            }
                        }
                    }


                    HashMap<String, Boolean> stringMap = new HashMap<>();
                    List<String> listResult = new ArrayList<>();

                    for (String aList_result : list_result) {
                        if (stringMap.get(aList_result) == null) {
                            stringMap.put(aList_result, true);
                            listResult.add(aList_result);
                        }
                    }

                    result = "";

                    for (int i = 0; i < listResult.size(); i++) {
                        if (listResult.get(i).length() == 1) {
                            result += ("0" + listResult.get(i)) + ",";
                        } else {
                            result += listResult.get(i) + ",";
                        }
                        Log.e("Map String", "get i: " + listResult.get(i));
                        Log.e("Map String", "checkRequirement: " + result);
                    }

                    result = result.substring(0, result.length() - 1);
                    temp.setNumber(result);

                    if (local_type == 1) {
                        presenter.getCycleListLoto(temp);
                    } else if (local_type == 2) {
                        presenter.getCycleListSpecial(temp);
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

    private boolean checkNumberNotNull() {
        if (edt_number_set.getText().toString().length() == 0) {
            // không nhập
            showShortToast("Vui lòng không để trống dàn lô tô.");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
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

    private boolean checkEndNotNull() {
        if (TextUtils.isEmpty(edt_end.getText().toString())) {
            showShortToast("Vui lòng chọn ngày kết thúc");
            return false;
        } else {
            return true;
        }
    }

    public void getData() {
        int type = getIntent().getIntExtra(Constants.ACTION_TYPE, -1);
        if (type > 0) {
            switch (type) {
                case 1:
                    local_type = 1;
                    initToolbar(R.id.toolbar, "Chu kỳ dàn lô tô");
                    break;
                case 2:
                    local_type = 2;
                    initToolbar(R.id.toolbar, "Chu kỳ dàn đặc biệt");
                    checkAll.setVisibility(View.GONE);
                    break;
            }
        }
    }
}
