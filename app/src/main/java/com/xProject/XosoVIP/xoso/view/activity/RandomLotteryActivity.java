package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;

import com.xProject.XosoVIP.R;

public class RandomLotteryActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_lottery);
        initToolbar(R.id.toolbar, getString(R.string.title_activity_random_lottery));
    }

}
