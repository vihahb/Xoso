package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;

import com.xproject.xoso.R;

public class ListSpinActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_spin);
        initToolbar(R.id.toolbar, "Lịch quay thưởng");
    }
}
