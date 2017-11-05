package com.xProject.XosoVIP.xoso.view.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ProgressBar;

import com.xProject.XosoVIP.R;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.SharedUtils;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.presenter.activity.AppIntroPresenter;
import com.xProject.XosoVIP.xoso.view.activity.inf.AppIntroView;

import java.util.List;

public class SplashActivity extends Activity implements AppIntroView {

    AppIntroPresenter presenter;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressView);
        presenter = new AppIntroPresenter(this);


        if (!SharedUtils.getInstance().getBooleanValue(Constants.CATEGORY_FLAG)){
            presenter.getProvinceAPI();
        } else {
            navigateToMain();
        }
    }

    private void navigateToMain(){
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void getProvinceListSuccess(List<ProvinceEntity> provinceEntityList) {
        navigateToMain();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void getProvinceListError() {
        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(view, "Tải dữ liệu tỉnh thành thất bại. Vui lòng cài đặt lại trong Cài Đặt.", Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 1500);

    }
}
