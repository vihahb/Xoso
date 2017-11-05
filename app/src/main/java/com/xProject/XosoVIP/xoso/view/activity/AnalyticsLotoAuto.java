package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.xProject.XosoVIP.sdk.utils.Utils;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterRecyclerViewString;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterStringCustom;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnalyticsLotoAuto extends BasicActivity implements View.OnClickListener {

    LinearLayout ln_table;
    List<String> number_list;
    List<String> listData;
    private Spinner sp_number;
    private EditText edt_number_set;
    private Button btnResult;
    private RecyclerView rcl_loto_auto;
    private TextView tv_auto_1, tv_auto_2, tv_auto_3, tv_auto_4, tv_title_auto;
    private AdapterRecyclerViewString adapter;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.mipmap.background_home);
        setContentView(R.layout.activity_analytics_loto_auto);
        initToolbar(R.id.toolbar, getString(R.string.title_activity_analytics_loto_auto));
        initView();
    }

    private void initView() {
        number_list = new ArrayList<>();
        listData = new ArrayList<>();
        btnResult = findButton(R.id.btn_get_result);
        sp_number = findSpinner(R.id.sp_number);
        edt_number_set = findEditText(R.id.edt_number_set);

        tv_title_auto = findTextView(R.id.tv_title_day);
        btnResult.setOnClickListener(this);
        ln_table = findLinearLayout(R.id.ln_table);
        ln_table.setVisibility(View.GONE);
        initSpinner();
        initList();
        edt_number_set.requestFocus();
        Utils.showKeyBoard(this, edt_number_set);
    }

    private void initList() {
        ln_table = findLinearLayout(R.id.ln_table);
        rcl_loto_auto = findRecyclerView(R.id.rcl_day_27);
        adapter = new AdapterRecyclerViewString(this, listData);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        rcl_loto_auto.setLayoutManager(layoutManager);
        rcl_loto_auto.setAdapter(adapter);
    }

    private void initAutoLotoXien() {


        String result = edt_number_set.getText().toString();
        List<String> auto_list = new ArrayList<>();

        while (result.indexOf("..") >= 0) {
            result = result.replaceAll("\\.\\.", ".");
        }
        while (result.indexOf("00")>=0){
            result = result.replaceAll("00", "0");
        }
        String[] list_result = result.split("\\.");
        Log.e("abc", "onClick: " + list_result.length);
        type = sp_number.getSelectedItemPosition() +2;
        sp_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = i + 2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
            result += listResult.get(i) + ",";
            Log.e("Map String", "get i: " + listResult.get(i));
            Log.e("Map String", "checkRequirement: " + result);
        }

//        result = result.substring(0, result.length()-1);

        if (listResult.size() == 0 || TextUtils.isEmpty(edt_number_set.getText().toString())) {
            // không nhập
            showShortToast("Vui lòng nhập đủ bộ số");
            Log.e("abc", "onClick: 2 " + list_result.length);
            return;
        } else {
            Log.e("abc", "onClick: 3 " + listResult.size());
            for (int i = 0; i < listResult.size(); i++) {
                if (listResult.get(i).length() > 2){
                    showShortToast("Vui lòng đúng định dạng bộ số. Nhỏ hơn hoặc bằng 2 số!");
                    Log.e("abc", "onClick: 4 " + list_result.length);
                    return;
                }
                number_list.add(i, listResult.get(i));
            }

            if (number_list.size() < type) {
                stringMap.clear();
                showShortToast("Vui lòng nhập đủ bộ số");
                listResult.clear();
                number_list.clear();
                return;
            } else {
                switch (type) {
                    case 2:
                        if (auto_list.size() > 0){
                            auto_list.clear();
                        }
                        for (int i = 0; i < number_list.size(); i++) {
                            for (int j = i + 1; j < number_list.size(); j++) {
                                String result_number = number_list.get(i) + ", " + number_list.get(j);
                                auto_list.add(result_number);
                            }
                        }
                        break;
                    case 3:
                        if (auto_list.size() > 0){
                            auto_list.clear();
                        }
                        for (int i = 0; i < number_list.size(); i++) {
                            for (int j = i + 1; j < number_list.size(); j++) {
                                for (int x = j + 1; x < number_list.size(); x++) {
                                    String result_number = number_list.get(i) + ", " + number_list.get(j) + ", " + number_list.get(x);
                                    auto_list.add(i, result_number);
                                }
                            }
                        }
                        break;
                    case 4:
                        if (auto_list.size() > 0){
                            auto_list.clear();
                        }
                        for (int i = 0; i < number_list.size(); i++) {
                            for (int j = i + 1; j < number_list.size(); j++) {
                                for (int x = j + 1; x < number_list.size(); x++) {
                                    for (int z = x + 1; z < number_list.size(); z++) {
                                        String result_number = number_list.get(i) + ", " + number_list.get(j) + ", " + number_list.get(x) + ", " + number_list.get(z);
                                        auto_list.add(i, result_number);
                                    }
                                }
                            }
                        }
                        break;
                }

                adapter.refreshData(auto_list);
                String result_text = "Tổng số lô xiên " + type + " tạo được là: <font color='red'>" + auto_list.size() + "</font>";
                tv_title_auto.setText(Html.fromHtml(result_text));
                number_list.clear();
                ln_table.setVisibility(View.VISIBLE);
            }
            Log.e("De quy ", "List: " + auto_list.size());
            type = 0;
        }
    }

    private void initSpinner() {
        List<String> list_type = new ArrayList<>();
        list_type.add(0, "2 bộ số");
        list_type.add(1, "3 bộ số");
        list_type.add(2, "4 bộ số");
        AdapterStringCustom adapterStringCustom = new AdapterStringCustom(list_type, this);
        sp_number.setAdapter(adapterStringCustom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_result:
                initAutoLotoXien();
                break;
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
