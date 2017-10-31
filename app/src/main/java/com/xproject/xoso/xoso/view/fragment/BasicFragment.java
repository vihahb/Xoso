package com.xproject.xoso.xoso.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xproject.xoso.xoso.view.activity.inf.BasicView;
import com.xproject.xoso.R;

import java.io.Serializable;

/**
 * Created by vivhp on 9/6/2017.
 */

public abstract class BasicFragment extends IFragment implements BasicView {

    private Toast toast;
    private Dialog dialogProgress;

    /*
    * Hiển thị tiến trình (đang thực hiện)
    * */
    public void showProgressBar(boolean isTouchOutside, boolean isCancel, String title, String message) {
        try {
            if (dialogProgress != null) {
                dialogProgress.dismiss();
                dialogProgress = null;
            }

            dialogProgress = new Dialog(getContext(), R.style.Theme_Transparents);
            dialogProgress.setContentView(R.layout.dialog_progressview);
            dialogProgress.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
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
        if (dialogProgress != null)
            dialogProgress.dismiss();
    }

//    /*
//    * Khởi tạo fragment vào 1 view layout (FrameLayout)
//    * */
//    public void replaceFragment(int id, Fragment fragment, String tag) {
//
//        if (getChildFragmentManager().findFragmentByTag(tag) == null) {
//            FragmentTransaction fragmentTransaction =getChildFragmentManager().beginTransaction();
//            fragmentTransaction.replace(id, fragment, tag);
//            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//            fragmentTransaction.commit();
//        }
//    }
//
//    public void replaceFragment(int frame_layout, Fragment fragment){
//        FragmentManager manager = getChildFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        //Khi được goi, fragment truyền vào sẽ thay thế vào vị trí FrameLayout trong Activity chính
//        transaction.replace(frame_layout, fragment);
//        transaction.commit();
//    }

    /*
//   * Hiển thị log
//   * */
//    public void debug(String message) {
//        Log.e(this.getClass().getSimpleName(), message);
//    }
//
//    /*
//    * Hiển thị thông báo snackbar 2s
//    * */
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
//        toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
//        toast.show();
//    }

    /*
    * Hiển thị thông báo 2s
    * */
    public void showShortToast(String message) {
        if (toast != null)
            toast.cancel();

        toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }


    public void startActivity(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
    }

    public void startActivity(Class clazz, String key, Object object) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtra(key, (Serializable) object);
        startActivity(intent);
    }

    public void startActivityAndFinish(Class clazz) {
        startActivity(new Intent(getActivity(), clazz));
        getActivity().finish();
    }

    public void startActivityAndFinish(Class clazz, String key, Object object) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtra(key, (Serializable) object);
        startActivity(intent);
        getActivity().finish();
    }

    public void startActivityForResult(Class clazz, int requestCode) {
        startActivityForResult(new Intent(getActivity(), clazz), requestCode);
    }

    public void startActivityForResultWithInteger(Class clazz, String key, int data, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtra(key, data);
        startActivityForResult(intent, requestCode);
    }

    public void startActivityForResult(Class clazz, String key, Object object, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        intent.putExtra(key, (Serializable) object);
        startActivityForResult(intent, requestCode);
    }

//    public void startActivityForResult2(Class clazz, String key, Object object, int requestCode) {
//        Intent intent = new Intent(getActivity(), clazz);
//        intent.putExtra(key, (Serializable) object);
//        getActivity().startActivityForResult(intent, requestCode);
//    }

    @Override
    public void onNoNetwork() {
        showShortToast(getString(R.string.error_no_internet));
    }

    @Override
    public void onRequestFail() {

    }

    @Override
    public void onDataInvalid(int errorCode) {

    }

}
