package com.xproject.xoso.xoso.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.xoso.model.entity.CycleLotoEntity;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.presenter.activity.ActivityCycleLotoPresenter;
import com.xproject.xoso.xoso.view.activity.inf.IActivityCycleLotoView;
import com.xproject.xoso.xoso.view.adapter.AdapterCycleLoto;
import com.xproject.xoso.xoso.view.adapter.AdapterSpinner;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityLotoCycle extends BasicActivity implements View.OnClickListener, IActivityCycleLotoView {

    private Spinner sp_province;
    private AdapterSpinner adapterSpinner;
    private EditText edt_number;
    private Button btn_result;
    List<ProvinceEntity> provinceEntityList;
    private ActivityCycleLotoPresenter presenter;
    private SpeedTemp temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loto_cycle);
        presenter = new ActivityCycleLotoPresenter(this);
        initToolbar(R.id.toolbar, "Chu kỳ lô tô");
        initView();
    }

    private void initView() {
        temp = new SpeedTemp();
        edt_number = findEditText(R.id.edt_number_set);
        sp_province = findSpinner(R.id.sp_province);
        btn_result = findButton(R.id.btn_result);
        btn_result.setOnClickListener(this);

        provinceEntityList = presenter.getCategory();
        adapterSpinner = new AdapterSpinner(provinceEntityList, this);
        sp_province.setAdapter(adapterSpinner);


        initSpinnerSelect();
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

    private void checkRequirement() {
        String result = edt_number.getText().toString();

        String[] list_result = result.split("\\.");
        Log.e("abc", "onClick: " + list_result.length);

        if (list_result.length == 0) {
            // không nhập
            Log.e("abc", "onClick: 2 " + list_result.length);
            return;
        } else {
            Log.e("abc", "onClick: 3 " + list_result.length);
            for (int i = list_result.length - 1; i >= 0; i--) {
                if (list_result[i].length() > 2) {
                    // Nhập nhiều hơn 2 số
                    showShortToast("Sai định dạng bộ số. Vui lòng nhập đúng định dạng.");
                    return;
                }
            }
        }

        result = result.replace(".", ",");
        temp.setNumber(result);
        presenter.getCycleLoto(temp);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_result){
            checkRequirement();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getCycleLotoSuccess(List<CycleLotoEntity> data) {
        startActivity(CycleLotoListInfo.class, Constants.LIST_SPEED, data, Constants.TEMP, temp);
    }

    @Override
    public void getCycleLotoError(String message) {
        if (message!=null){
            showShortToast(message);
        }
    }
}
