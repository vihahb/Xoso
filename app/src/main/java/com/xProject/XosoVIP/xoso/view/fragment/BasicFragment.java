package com.xProject.XosoVIP.xoso.view.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xProject.XosoVIP.sdk.callback.DialogListener;
import com.xProject.XosoVIP.xoso.view.activity.BasicActivity;
import com.xProject.XosoVIP.xoso.view.activity.inf.BasicView;
import com.xProject.XosoVIP.R;

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

    public void showDialogLive(boolean isTouchOutside, boolean isCancelable, int type, final DialogListener dialogListener) {
        try {
            final Dialog dialog = new Dialog(getContext(), R.style.Theme_Transparent);
            dialog.setContentView(R.layout.dialog_material);
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(isTouchOutside);
            dialog.setCanceledOnTouchOutside(isCancelable);

            TextView txt_title = (TextView) dialog.findViewById(R.id.dialog_txt_title);
            TextView txt_message = (TextView) dialog.findViewById(R.id.dialog_txt_message);
            Button btn_negative = (Button) dialog.findViewById(R.id.dialog_btn_negative);
            Button btn_positive = (Button) dialog.findViewById(R.id.dialog_btn_positive);
            ImageView icon_dialog = (ImageView) dialog.findViewById(R.id.icon_dialog);

            switch (type){
                case 1:
                    icon_dialog.setImageResource(R.mipmap.ic_nav_north);
                    txt_message.setText("Đang trực tiếp quay giải miển Bắc.");
                    break;
                case 2:
                    icon_dialog.setImageResource(R.mipmap.ic_nav_central);
                    txt_message.setText("Đang trực tiếp quay giải miển Trung.");
                    break;
                case 3:
                    icon_dialog.setImageResource(R.mipmap.ic_nav_south);
                    txt_message.setText("Đang trực tiếp quay giải miển Nam.");
                    break;
            }

            btn_negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    dialogListener.negativeClicked();
                }
            });

            btn_positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    dialogListener.positiveClicked();
                }
            });

            if (dialog != null)
                dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("showDialogLive", "showDialogLive: " + e);
        }
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
