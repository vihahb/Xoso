package com.xtelsolution.xoso.xoso.presenter.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.xoso.view.activity.BasicActivity;

public class RandomLotteryActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_lottery);
        initToolbar(R.id.toolbar, getString(R.string.title_activity_random_lottery));
    }

}
