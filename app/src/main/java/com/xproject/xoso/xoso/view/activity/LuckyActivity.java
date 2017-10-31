package com.xproject.xoso.xoso.view.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xproject.xoso.R;
import com.xproject.xoso.sdk.callback.DateTimePickerListener;
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.RESP_LuckyEntity;
import com.xproject.xoso.xoso.presenter.activity.LuckyActivityPresenter;
import com.xproject.xoso.xoso.view.activity.inf.ILuckyActivityView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class LuckyActivity extends BasicActivity implements ILuckyActivityView, View.OnClickListener {

    LuckyActivityPresenter presenter;
    private TextView tv_date, tv_month, tv_year, tv_title, tv_result, tv_number, tv_result_1;
    private Button btn_result;
    private String birth_day;
    private List<String> number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky);
        number = new ArrayList<>();
        presenter = new LuckyActivityPresenter(this);
        initToolbar(R.id.toolbar, "Dự đoán con số may mắn");
        initView();
    }

    private void initView() {
        tv_date = findTextView(R.id.tv_date_lucky);
        tv_month = findTextView(R.id.tv_month_lucky);
        tv_year = findTextView(R.id.tv_year_lucky);
        tv_title = findTextView(R.id.tv_title);
        tv_result = findTextView(R.id.tv_result);
        tv_number = findTextView(R.id.tv_number);
        tv_result_1 = findTextView(R.id.tv_result_1);

        tv_title.setVisibility(View.INVISIBLE);
        tv_result.setVisibility(View.INVISIBLE);
        tv_number.setVisibility(View.INVISIBLE);
        tv_result_1.setVisibility(View.INVISIBLE);

        tv_date.setOnClickListener(this);
        tv_month.setOnClickListener(this);
        tv_year.setOnClickListener(this);

        Calendar calendar = TimeUtils.getCalendarFromString(TimeUtils.getToday());
        if (calendar != null) {
            tv_date.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
            tv_month.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
            tv_year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        }

        btn_result = findButton(R.id.btn_result);
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (String.valueOf(tv_month.getText().toString()).length() == 1) {
                    birth_day = tv_date.getText().toString()
                            + "-0" + tv_month.getText().toString()
                            + "-" + tv_year.getText().toString();
                } else {
                    birth_day = tv_date.getText().toString()
                            + "-" + tv_month.getText().toString()
                            + "-" + tv_year.getText().toString();
                }
                presenter.getLucky(birth_day);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getLuckySuccess(RESP_LuckyEntity luckyEntity) {
        tv_title.setText("Kết quả");
        tv_title.setVisibility(View.VISIBLE);
        tv_result.setVisibility(View.VISIBLE);
        tv_number.setVisibility(View.VISIBLE);
        tv_result_1.setVisibility(View.VISIBLE);

        if (number.size() > 0) {
            number.clear();
        }
//        String result = "Ngày sinh: "
//                + tv_date.getText().toString() + "/"
//                + tv_month.getText().toString() + "/"
//                + tv_year.getText().toString()
//                + ". Ngày " + luckyEntity.getCanchingay()
//                + ", tháng " + luckyEntity.getCanchithang()
//                + ", năm " + luckyEntity.getCanchi()
//                + "\nTheo ngũ hành tương sinh (Kim - mộc - thuỷ - hoả - thổ) các số may mắn của bạn là:";

        String result = "Ngày sinh: "
                + tv_date.getText().toString() + "/"
                + tv_month.getText().toString() + "/"
                + tv_year.getText().toString() + ". \n" +
                "Theo ngũ hành tương sinh (Kim - mộc - thuỷ - hoả - thổ) các số may mắn của bạn là:";
        tv_result.setText(result);

        number.addAll(luckyEntity.getData());
        Collections.sort(number);
        if (number.size() > 0) {
            StringBuilder number_reult = new StringBuilder();
            for (int i = 0; i < number.size(); i++) {
                number_reult.append(number.get(i)).append(" - ");
            }
            number_reult = new StringBuilder(number_reult.substring(0, number_reult.length() - 3));
            tv_number.setText(number_reult.toString());
        }
    }

    @Override
    public void getLuckyError(String mes) {
        tv_title.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tv_title.setGravity(View.TEXT_ALIGNMENT_CENTER);
        }
        tv_title.setText(mes);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_date_lucky
                || view.getId() == R.id.tv_month_lucky
                || view.getId() == R.id.tv_year_lucky) {
            TimeUtils.showDateTimePickerDialog(this, new DateTimePickerListener() {
                @Override
                public void onDateTimePickerListener(int year, int month, int day) {
                    if (String.valueOf(day).length() == 1) {
                        tv_date.setText("0" + String.valueOf(day));
                    } else {
                        tv_date.setText(String.valueOf(day));
                    }

                    tv_month.setText(String.valueOf(month + 1));
                    tv_year.setText(String.valueOf(year));
                }
            }, false);
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
