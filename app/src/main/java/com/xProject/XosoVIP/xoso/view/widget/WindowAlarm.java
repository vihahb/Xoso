package com.xProject.XosoVIP.xoso.view.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xProject.XosoVIP.R;
import com.xProject.XosoVIP.sdk.callback.DialogListener;

public class WindowAlarm {

    private Context context;

    private View rootView;

    private WindowManager windowManager;
    ImageView icon_dialog;
    TextView txt_message;
    private Button btn_negative, btn_positive;
    private boolean isShowing = false;

    public WindowAlarm(Context context) {
        this.context = context;
        initView();
    }

    @SuppressLint("InflateParams")
    private void initView() {
        LayoutInflater layoutInflater = LayoutInflater.from(context.getApplicationContext());
        rootView = layoutInflater.inflate(R.layout.window_dialog, null);

        TextView txt_title = (TextView) rootView.findViewById(R.id.dialog_txt_title);
        txt_message = (TextView) rootView.findViewById(R.id.dialog_txt_message);
        icon_dialog = (ImageView) rootView.findViewById(R.id.icon_dialog);
        btn_negative = (Button) rootView.findViewById(R.id.dialog_btn_negative);
        btn_positive = (Button) rootView.findViewById(R.id.dialog_btn_positive);
    }

//    private void initBackgroundColor() {
//        CardView cardView = (CardView) rootView.findViewById(R.id.cardView);
//
//        cardView.setCardBackgroundColor(ColorUtils.getBackgroundColor());
//    }

    public void setType(int type){
        switch (type) {
            case 1:
                icon_dialog.setImageResource(R.mipmap.ic_nav_north);
                txt_message.setText("Đang trực tiếp quay giải miền Bắc.");
                break;
            case 2:
                icon_dialog.setImageResource(R.mipmap.ic_nav_central);
                txt_message.setText("Đang trực tiếp quay giải miền Trung.");
                break;
            case 3:
                icon_dialog.setImageResource(R.mipmap.ic_nav_south);
                txt_message.setText("Đang trực tiếp quay giải miền Nam.");
                break;
        }
    }

    public void setActionListener(final DialogListener actionListener) {

        btn_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeWindows();
            }
        });

        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeWindows();
                actionListener.positiveClicked();
            }
        });
    }

    public void closeWindows() {
        windowManager.removeView(rootView);
    }

    public void show() {
        isShowing = true;
        WindowManager.LayoutParams p = new WindowManager.LayoutParams(
                // Shrink the window to wrap the content rather than filling the screen
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                // Display it on top of other application windows, but only for the current user
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                // Don't let it grab the input focus
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                // Make the underlying application window visible through any transparent parts
                PixelFormat.TRANSLUCENT);

        // Define the position of the window within the screen
//        p.gravity = Gravity.TOP | Gravity.RIGHT;
//        p.x = 0;
//        p.y = 100;

        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.addView(rootView, p);
        }
    }

    public boolean isShowing() {
        return isShowing;
    }
}