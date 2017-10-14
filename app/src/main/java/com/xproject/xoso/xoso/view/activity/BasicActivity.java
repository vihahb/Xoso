package com.xproject.xoso.xoso.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xtelsolution.xoso.R;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by vivhp on 9/1/2017.
 */

public class BasicActivity extends IActivity {

    private Toast toast;
    private boolean isWaitingForExit = false;
    private Dialog dialogProgress;

    public void initToolbar(int id, String title) {
        Toolbar toolbar = (Toolbar) findViewById(id);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (title != null)
            actionBar.setTitle(title);
    }

    /*
    * Hiển thị tiến trình (đang thực hiện)
    * */
    public void showProgressBar(boolean isTouchOutside, boolean isCancel, String title, String message) {
        if (getActivity() == null)
            return;

        try {
            if (dialogProgress != null) {
                dialogProgress.dismiss();
                dialogProgress = null;
            }

            dialogProgress = new Dialog(BasicActivity.this, R.style.Theme_Transparents);
            dialogProgress.setContentView(R.layout.dialog_progressview);
            dialogProgress.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialogProgress.setCanceledOnTouchOutside(isTouchOutside);
            dialogProgress.setCancelable(isCancel);

            TextView txt_message = (TextView) dialogProgress.findViewById(R.id.tv_message);
//            Button btn_negative = (Button) dialog.findViewById(R.id.dialog_btn_negative);

            if (message == null)
                txt_message.setVisibility(View.GONE);
            else
                txt_message.setText(message);

            if (dialogProgress != null)
                dialogProgress.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * Kết thúc hiển thị tiến trình (đang thực hiện)
    * */
    public void closeProgressBar() {
        try {
            if (dialogProgress != null && dialogProgress.isShowing()){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialogProgress.dismiss();
                    }
                }, 1500);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
    * Khởi tạo fragment vào 1 view layout (FrameLayout)
    * */
    public void replaceFragment(int id, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(id, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
            fragmentTransaction.commit();
        }
    }

    public void replaceFragment(int id, Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
        transaction.replace(id, fragment);
        transaction.commit();
    }

//    @Override
//    public void showShortSnackBar(View view, String message) {
//        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
//    }
//
//    /*
//    * Hiển thị thông báo snackbar 3.5s
//    * */
//    public void showLongSnackBar(View view, String message) {
//        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
//    }
//
//    /*
//    * Hiển thị thông báo 3.5s
//    * */
//    public void showLongToast(String message) {
//        if (toast != null)
//            toast.cancel();
//
//        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
//        toast.show();
//    }

    /*
    * Hiển thị thông báo 2s
    * */
    public void showShortToast(String message) {
        if (toast != null)
            toast.cancel();

        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }


    public void startActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }

    @Override
    public void startActivity(Class clazz, String key, Object object) {

    }

    public void startActivity(Class clazz, String key_1, Object object_1, String key_2, Object object_2) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key_1, (Serializable) object_1);
        intent.putExtra(key_2, (Serializable) object_2);
        startActivity(intent);
    }

    public void startActivity(Class clazz, String key_1, Object object_1, String key_2, Object object_2, String key_3, Object object_3, String key_4, Object object_4) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key_1, (Serializable) object_1);
        intent.putExtra(key_2, (Serializable) object_2);
        intent.putExtra(key_3, (Serializable) object_3);
        intent.putExtra(key_4, (Serializable) object_4);
        startActivity(intent);
    }

    public void startActivity(Class clazz, String key_1, Object object_1, String key_2, Object object_2, String key_3, int value) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key_1, (Serializable) object_1);
        intent.putExtra(key_2, (Serializable) object_2);
        intent.putExtra(key_3, value);
        startActivity(intent);
    }

    public void startActivityAndFinish(Class clazz) {
        startActivity(new Intent(this, clazz));
        finish();
    }

    public void startActivityAndFinish(Class clazz, String key, Object object) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, (Serializable) object);
        startActivity(intent);
        finish();
    }

    public void startActivityForResult(Class clazz, int requestCode) {
        startActivityForResult(new Intent(this, clazz), requestCode);
    }

    public void startActivityForResultWithInteger(Class clazz, String key, int data, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, data);
        startActivityForResult(intent, requestCode);
    }

    public void startActivityForResult(Class clazz, String key, Object object, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, (Serializable) object);
        startActivityForResult(intent, requestCode);
    }

    public void showConfirmExitApp() {

        if (isWaitingForExit) {
            finish();
        } else {
            new AsyncTask<Object, Object, Object>() {

                @Override
                public void onPreExecute() {
                    super.onPreExecute();
                    isWaitingForExit = true;
                    showShortToast(getString(R.string.message_back_press_to_exit));

                }

                @Override
                public Object doInBackground(Object... params) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                public void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    isWaitingForExit = false;
                }
            }.execute();
        }
    }

    @Override
    public void onNoNetwork() {

    }

    @Override
    public void onRequestFail() {

    }

    @Override
    public void onDataInvalid(int errorCode) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
