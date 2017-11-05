package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterSpinner;
import com.xProject.XosoVIP.R;

import java.util.List;
import java.util.Random;

public class RandomSpinActivity extends BasicActivity {

    Spinner sp_province;
    LinearLayout include_north, include_south;
    int type_state = 1;
    int type_spin = 0;
    private TextView tvSpecial, tvFirst, tv_21, tv_22,
            tv_31, tv_32, tv_33, tv_34, tv_35, tv_36,
            tv_41, tv_42, tv_43, tv_44,
            tv_51, tv_52, tv_53, tv_54, tv_55, tv_56,
            tv_61, tv_62, tv_63,
            tv_71, tv_72, tv_73, tv_74;
    private TextView tvSpecial_1, tvFirstValue_1, tvSecondValue_1_1, tv3_1_1,
            tv3_2_1, tv4_1_1, tv4_2_1, tv4_3_1, tv4_4_1, tv4_5_1,
            tv4_6_1, tv4_7_1, tv5_1_1, tv6_1_1,
            tv6_2_1, tv6_3_1, tv7_1_1, tv8_1_1;
    private TextView tv_title;
    private Roller special, first, r71, r72, r73, r74,
            r61, r62, r63,
            r51, r52, r53, r54, r55, r56,
            r41, r42, r43, r44,
            r31, r32, r33, r34, r35, r36,
            r21, r22;
    private Roller special_1, first_1, second_1, r311, r321, r411, r421, r431, r441, r451, r461, r471,
            r511, r611, r621, r631, r711, r811;
    private TableLayout table;
    private Button btn_random;
    private List<ProvinceEntity> provinceEntityList;
    private AdapterSpinner adapterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.mipmap.background_home);
        setContentView(R.layout.activity_random_spin);
        initToolbar(R.id.toolbar, "Quay thử");
        initView();
        initRoller();
        initRollerCentralSouth();
    }

    private void initView() {
        tv_title = findTextView(R.id.tv_title_region);

        table = (TableLayout) findViewById(R.id.table);

        tv_title.setVisibility(View.GONE);

        include_north = findLinearLayout(R.id.include_north);
        include_south = findLinearLayout(R.id.include_south);

        provinceEntityList = getCategory();

        for (int i = 0; i < provinceEntityList.size(); i++) {
            if (provinceEntityList.get(i).getMavung() == 18
                    || provinceEntityList.get(i).getMavung() == 19
                    || provinceEntityList.get(i).getMavung() == 20
                    || provinceEntityList.get(i).getMavung() == 28
                    || provinceEntityList.get(i).getMavung() == 29) {
                provinceEntityList.remove(i);
            }
        }

        sp_province = (Spinner) findViewById(R.id.sp_province);
        adapterSpinner = new AdapterSpinner(provinceEntityList, this);
        sp_province.setAdapter(adapterSpinner);

        sp_province = (Spinner) findViewById(R.id.sp_province);

        tvSpecial = (TextView) findViewById(R.id.tvSpecialValue);
        tvFirst = (TextView) findViewById(R.id.tvFirstValue);
        tv_21 = (TextView) findViewById(R.id.tvSecondValue_1);
        tv_22 = (TextView) findViewById(R.id.tvSecondValue_2);
        tv_31 = (TextView) findViewById(R.id.tv3_1);
        tv_32 = (TextView) findViewById(R.id.tv3_2);
        tv_33 = (TextView) findViewById(R.id.tv3_3);
        tv_34 = (TextView) findViewById(R.id.tv3_4);
        tv_35 = (TextView) findViewById(R.id.tv3_5);
        tv_36 = (TextView) findViewById(R.id.tv3_6);
        tv_41 = (TextView) findViewById(R.id.tv4_1);
        tv_42 = (TextView) findViewById(R.id.tv4_2);
        tv_43 = (TextView) findViewById(R.id.tv4_3);
        tv_44 = (TextView) findViewById(R.id.tv4_4);
        tv_51 = (TextView) findViewById(R.id.tv5_1);
        tv_52 = (TextView) findViewById(R.id.tv5_2);
        tv_53 = (TextView) findViewById(R.id.tv5_3);
        tv_54 = (TextView) findViewById(R.id.tv5_4);
        tv_55 = (TextView) findViewById(R.id.tv5_5);
        tv_56 = (TextView) findViewById(R.id.tv5_6);
        tv_61 = (TextView) findViewById(R.id.tv6_1);
        tv_62 = (TextView) findViewById(R.id.tv6_2);
        tv_63 = (TextView) findViewById(R.id.tv6_3);
        tv_71 = (TextView) findViewById(R.id.tv7_1);
        tv_72 = (TextView) findViewById(R.id.tv7_2);
        tv_73 = (TextView) findViewById(R.id.tv7_3);
        tv_74 = (TextView) findViewById(R.id.tv7_4);

        tvSpecial_1 = findTextView(R.id.tvSpecialValue_1);
        tvFirstValue_1 = findTextView(R.id.tvFirstValue_1);
        tvSecondValue_1_1 = findTextView(R.id.tvSecondValue_1_1);
        tv3_1_1 = findTextView(R.id.tv3_1_1);
        tv3_2_1 = findTextView(R.id.tv3_2_1);
        tv4_1_1 = findTextView(R.id.tv4_1_1);
        tv4_2_1 = findTextView(R.id.tv4_2_1);
        tv4_3_1 = findTextView(R.id.tv4_3_1);
        tv4_4_1 = findTextView(R.id.tv4_4_1);
        tv4_5_1 = findTextView(R.id.tv4_5_1);
        tv4_6_1 = findTextView(R.id.tv4_6_1);
        tv4_7_1 = findTextView(R.id.tv4_7_1);
        tv5_1_1 = findTextView(R.id.tv5_1_1);
        tv6_1_1 = findTextView(R.id.tv6_1_1);
        tv6_2_1 = findTextView(R.id.tv6_2_1);
        tv6_3_1 = findTextView(R.id.tv6_3_1);
        tv7_1_1 = findTextView(R.id.tv7_1_1);
        tv8_1_1 = findTextView(R.id.tv8_1_1);


        btn_random = (Button) findViewById(R.id.btn_random);
        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type_state == 1) {
                    type_state = 2;
                    reStartRoller(type_spin);
                    btn_random.setText("Dừng");
                } else {
                    btn_random.setText("Quay thử");
                    type_state = 1;
                    stopRoller(type_spin);
                }

            }
        });
        initSpinnerSelect();
    }

    private void initSpinnerSelect() {
        sp_province.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                btn_random.setText("Quay thử");
                type_state = 1;
                stopRoller(type_spin);
                return false;
            }
        });
        if (sp_province.isShown()){

        }
        sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type_spin = position;
                if (type_spin == 0) {
                    include_north.setVisibility(View.VISIBLE);
                    include_south.setVisibility(View.GONE);
                } else {
                    include_north.setVisibility(View.GONE);
                    include_south.setVisibility(View.VISIBLE);
                }
                String name_province = "KẾT QUẢ XỔ SỐ " + provinceEntityList.get(position).getTenvung();
                tv_title.setText(name_province);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void reStartRoller(int type_spin) {
        tv_title.setVisibility(View.VISIBLE);
        if (type_spin == 0) {

            special.setRerun();
            first.setRerun();
            r71.setRerun();
            r72.setRerun();
            r73.setRerun();
            r74.setRerun();
            r61.setRerun();
            r62.setRerun();
            r63.setRerun();
            r51.setRerun();
            r52.setRerun();
            r53.setRerun();
            r54.setRerun();
            r55.setRerun();
            r56.setRerun();
            r41.setRerun();
            r42.setRerun();
            r43.setRerun();
            r44.setRerun();
            r31.setRerun();
            r32.setRerun();
            r33.setRerun();
            r34.setRerun();
            r35.setRerun();
            r36.setRerun();
            r21.setRerun();
            r22.setRerun();
        } else if (type_spin > 0) {

            special_1.setRerun();
            first_1.setRerun();
            second_1.setRerun();
            r311.setRerun();
            r321.setRerun();
            r411.setRerun();
            r421.setRerun();
            r431.setRerun();
            r441.setRerun();
            r451.setRerun();
            r461.setRerun();
            r471.setRerun();
            r511.setRerun();
            r611.setRerun();
            r621.setRerun();
            r631.setRerun();
            r711.setRerun();
            r811.setRerun();
        }
    }

    private void stopRoller(int type_spin) {

        if (type_spin == 0) {
            special.shutdownThread(true);
            first.shutdownThread(false);
            r71.shutdownThread(false);
            r72.shutdownThread(false);
            r73.shutdownThread(false);
            r74.shutdownThread(false);
            r61.shutdownThread(false);
            r62.shutdownThread(false);
            r63.shutdownThread(false);
            r51.shutdownThread(false);
            r52.shutdownThread(false);
            r53.shutdownThread(false);
            r54.shutdownThread(false);
            r55.shutdownThread(false);
            r56.shutdownThread(false);
            r41.shutdownThread(false);
            r42.shutdownThread(false);
            r43.shutdownThread(false);
            r44.shutdownThread(false);
            r31.shutdownThread(false);
            r32.shutdownThread(false);
            r33.shutdownThread(false);
            r34.shutdownThread(false);
            r35.shutdownThread(false);
            r36.shutdownThread(false);
            r21.shutdownThread(false);
            r22.shutdownThread(false);
        } else if (type_spin > 0) {
            special_1.shutdownThread(true);
            first_1.shutdownThread(false);
            second_1.shutdownThread(false);
            r311.shutdownThread(false);
            r321.shutdownThread(false);
            r411.shutdownThread(false);
            r421.shutdownThread(false);
            r431.shutdownThread(false);
            r441.shutdownThread(false);
            r451.shutdownThread(false);
            r461.shutdownThread(false);
            r471.shutdownThread(false);
            r511.shutdownThread(false);
            r611.shutdownThread(false);
            r621.shutdownThread(false);
            r631.shutdownThread(false);
            r711.shutdownThread(false);
            r811.shutdownThread(true);
        }
    }


    public void initRoller() {
        if (special == null) {
            special = new Roller(tvSpecial, 100000, 100, 99999, 11000);
        }

        if (first == null) {
            first = new Roller(tvFirst, 100000, 100, 99999, 11000);
        }

        if (r21 == null)
            r21 = new Roller(tv_21, 100000, 100, 99999, 11000);

        if (r22 == null)
            r22 = new Roller(tv_22, 100000, 100, 99999, 11000);

        if (r31 == null)
            r31 = new Roller(tv_31, 100000, 100, 99999, 11000);

        if (r32 == null)
            r32 = new Roller(tv_32, 100000, 100, 99999, 11000);

        if (r33 == null)
            r33 = new Roller(tv_33, 100000, 100, 99999, 11000);

        if (r34 == null)
            r34 = new Roller(tv_34, 100000, 100, 99999, 11000);

        if (r35 == null)
            r35 = new Roller(tv_35, 100000, 100, 99999, 11000);

        if (r36 == null)
            r36 = new Roller(tv_36, 100000, 100, 99999, 11000);

        if (r41 == null)
            r41 = new Roller(tv_41, 100000, 100, 9999, 1100);

        if (r42 == null)
            r42 = new Roller(tv_42, 100000, 100, 9999, 1100);

        if (r43 == null)
            r43 = new Roller(tv_43, 100000, 100, 9999, 1100);

        if (r44 == null)
            r44 = new Roller(tv_44, 100000, 100, 9999, 1100);

        if (r51 == null)
            r51 = new Roller(tv_51, 100000, 100, 9999, 1100);

        if (r52 == null)
            r52 = new Roller(tv_52, 100000, 100, 9999, 1100);

        if (r53 == null)
            r53 = new Roller(tv_53, 100000, 100, 9999, 1100);

        if (r54 == null)
            r54 = new Roller(tv_54, 100000, 100, 9999, 1100);

        if (r55 == null)
            r55 = new Roller(tv_55, 100000, 100, 9999, 1100);

        if (r56 == null)
            r56 = new Roller(tv_56, 100000, 100, 9999, 1100);

        if (r61 == null)
            r61 = new Roller(tv_61, 100000, 100, 999, 110);

        if (r62 == null)
            r62 = new Roller(tv_62, 100000, 100, 999, 110);

        if (r63 == null)
            r63 = new Roller(tv_63, 100000, 100, 999, 110);

        if (r71 == null)
            r71 = new Roller(tv_71, 100000, 100, 99, 11);

        if (r72 == null)
            r72 = new Roller(tv_72, 100000, 100, 99, 11);

        if (r73 == null)
            r73 = new Roller(tv_73, 100000, 100, 99, 11);

        if (r74 == null)
            r74 = new Roller(tv_74, 100000, 100, 99, 11);
    }

    public void initRollerCentralSouth() {
        if (special_1 == null) {
            special_1 = new Roller(tvSpecial_1, 100000, 100, 999999, 111111);
        }

        if (first_1 == null) {
            first_1 = new Roller(tvFirstValue_1, 100000, 100, 99999, 11111);
        }

        if (second_1 == null)
            second_1 = new Roller(tvSecondValue_1_1, 100000, 100, 99999, 11000);

        if (r311 == null)
            r311 = new Roller(tv3_1_1, 100000, 100, 99999, 11000);

        if (r321 == null)
            r321 = new Roller(tv3_2_1, 100000, 100, 99999, 11000);

        if (r411 == null)
            r411 = new Roller(tv4_1_1, 100000, 100, 99999, 11000);

        if (r421 == null)
            r421 = new Roller(tv4_2_1, 100000, 100, 99999, 11000);

        if (r431 == null)
            r431 = new Roller(tv4_3_1, 100000, 100, 99999, 11000);

        if (r441 == null)
            r441 = new Roller(tv4_4_1, 100000, 100, 99999, 11000);

        if (r451 == null)
            r451 = new Roller(tv4_5_1, 100000, 100, 99999, 11000);

        if (r461 == null)
            r461 = new Roller(tv4_6_1, 100000, 100, 99999, 11000);

        if (r471 == null)
            r471 = new Roller(tv4_7_1, 100000, 100, 99999, 11000);

        if (r511 == null)
            r511 = new Roller(tv5_1_1, 100000, 100, 9999, 1100);

        if (r611 == null)
            r611 = new Roller(tv6_1_1, 100000, 100, 9999, 1100);

        if (r621 == null)
            r621 = new Roller(tv6_2_1, 100000, 100, 9999, 1100);

        if (r631 == null)
            r631 = new Roller(tv6_3_1, 100000, 100, 9999, 1100);

        if (r711 == null)
            r711 = new Roller(tv7_1_1, 100000, 100, 999, 110);

        if (r811 == null)
            r811 = new Roller(tv8_1_1, 100000, 100, 99, 11);
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    private class Roller implements Runnable {
        TextView textRoll;
        private int numTimes;
        private long delayMillis;
        private int max, min;

        private volatile boolean shutdown;

        public Roller(TextView textView, int numTimes, long delayMillis, int max, int min) {
            this.textRoll = textView;
            this.numTimes = numTimes;
            this.delayMillis = delayMillis;
            this.max = max;
            this.min = min;
        }

        public void setRerun() {
            shutdown = false;
            run();
        }

        @Override
        public void run() {
            if (!shutdown) {
                if (textRoll != null) {
                    Random rn = new Random();
//                    int range = max - min + 1;
                    int randomNum = rn.nextInt((max - min) + 1) + min;
                    String roll = String.valueOf(randomNum);
                    textRoll.setText(roll);

                    numTimes--;

                    if (numTimes > 0) {
                        textRoll.postDelayed(this, delayMillis);
                    }
                }
            }
        }

        public void shutdownThread(boolean isSpecial) {
            if (textRoll != null) {
//                if (isSpecial) {
//                    textRoll.setTextColor(getResources().getColor(R.color.colorPrimary));
//                } else {
//                    textRoll.setTextColor(getResources().getColor(android.R.color.black));
//                }
            }
            shutdown = true;
        }
    }
}
