package com.xtelsolution.xoso.xoso.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.xtelsolution.xoso.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
