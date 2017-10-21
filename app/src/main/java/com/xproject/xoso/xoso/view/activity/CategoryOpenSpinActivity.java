package com.xproject.xoso.xoso.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xtelsolution.xoso.R;

public class CategoryOpenSpinActivity extends AppCompatActivity {

    private TextView tv_north, tv_central, tv_south;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_open_spin);
    }
}
