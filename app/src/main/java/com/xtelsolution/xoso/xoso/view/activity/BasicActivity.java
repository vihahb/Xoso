package com.xtelsolution.xoso.xoso.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.sdk.callback.DialogListener;

import java.io.Serializable;

/**
 * Created by vivhp on 9/1/2017.
 */

public class BasicActivity extends IActivity {

    private Toast toast;
    private boolean isWaitingForExit = false;

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

    public void startActivity(Class clazz, String key, Object object) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(key, (Serializable) object);
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
