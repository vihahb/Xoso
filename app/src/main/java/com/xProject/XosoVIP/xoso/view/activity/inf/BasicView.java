package com.xProject.XosoVIP.xoso.view.activity.inf;

import android.app.Activity;

/**
 * Created by vivhp on 9/5/2017.
 */

public interface BasicView {
//    void showShortSnackBar(View view, String message);
//
//    void showLongSnackBar(View view, String message);
//
//    void showLongToast(String message);

    void showShortToast(String message);

    void startActivity(Class clazz);

    void startActivity(Class clazz, String key, Object object);

    void startActivityAndFinish(Class clazz);

    void startActivityAndFinish(Class clazz, String key, Object object);

    void startActivityForResult(Class clazz, int requestCode);

    void startActivityForResultWithInteger(Class clazz, String key, int data, int requestCode);

    void startActivityForResult(Class clazz, String key, Object object, int requestCode);

    void onNoNetwork();

    void onRequestFail();

    void onDataInvalid(int errorCode);

    void showProgressBar(boolean isTouchOutside, boolean isCancel, String title, String message);

    void closeProgressBar();

    Activity getActivity();
}
