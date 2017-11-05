package com.xProject.XosoVIP.sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Vibrator;
import android.support.v7.widget.Toolbar;
import android.text.*;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.xProject.XosoVIP.xoso.ProjectApplication;

import java.util.ArrayList;

/**
 * Created by vivhp on 10/20/2017.
 */

public class Utils {
    public static int getWidth(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        return width;
    }

    public static void vibrateAction() {
        Vibrator vibrator = (Vibrator) ProjectApplication.context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(50);
    }

    public static void showKeyBoard(Context context, EditText editText){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public static View getToolbarNavigationIcon(Toolbar toolbar) {
        //check if contentDescription previously was set
        boolean hadContentDescription = android.text.TextUtils.isEmpty(toolbar.getNavigationContentDescription());
        String contentDescription = !hadContentDescription ? (String) toolbar.getNavigationContentDescription() : "navigationIcon";
        toolbar.setNavigationContentDescription(contentDescription);
        ArrayList<View> potentialViews = new ArrayList<View>();
        //find the view based on it's content description, set programatically or with android:contentDescription
        toolbar.findViewsWithText(potentialViews, contentDescription, View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
        //Nav icon is always instantiated at this point because calling setNavigationContentDescription ensures its existence
        View navIcon = null;
        if (potentialViews.size() > 0) {
            navIcon = potentialViews.get(0); //navigation icon is ImageButton
        }
        //Clear content description if not previously present
        if (hadContentDescription)
            toolbar.setNavigationContentDescription(null);
        return navIcon;
    }

}
