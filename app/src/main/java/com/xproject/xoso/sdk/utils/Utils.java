package com.xproject.xoso.sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Vibrator;
import android.view.Display;

import com.xproject.xoso.xoso.ProjectApplication;

/**
 * Created by vivhp on 10/20/2017.
 */

public class Utils {
    public static int getWidth(Activity context) {
        Display display =context.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        return width;
    }

    public static void vibrateAction(){
        long[] longs = new long[] {50, 50};
        Vibrator vibrator = (Vibrator) ProjectApplication.context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(longs, 2);
    }


}
