package com.xProject.XosoVIP.sdk.utils;

import android.content.Context;
import android.widget.TextView;

import com.xProject.XosoVIP.R;
import com.xProject.XosoVIP.xoso.ProjectApplication;

import java.util.Random;

/**
 * Created by xtel on 10/31/17.
 */

public class RollerSingleton {
    private static Roller roller;
    private static RollerSingleton instance;
    private static final Context context = ProjectApplication.context;

    public static RollerSingleton getInstance() {
        if (instance == null){
            synchronized (RollerSingleton.class){
                if (instance == null){
                    instance = new RollerSingleton();
                }
            }
        }
        return instance;
    }
    private RollerSingleton() {
    }

    public static void createRoller(TextView textView, int max, int min){
       roller = new Roller(textView, 100000, 100, max, min);
    }

    public static void stopRoller(boolean special){
        if (roller.isRun()){
            roller.shutdownThread(special);
        }
    }

    private static class Roller implements Runnable {
        TextView textRoll;
        private int numTimes;
        private long delayMillis;
        private int max, min;

        private volatile boolean shutdown;
        private boolean running;

        public Roller(TextView textView, int numTimes, long delayMillis, int max, int min) {
            this.textRoll = textView;
            this.numTimes = numTimes;
            this.delayMillis = delayMillis;
            this.max = max;
            this.min = min;
        }

        @Override
        public void run() {
            if (!shutdown) {
                if (textRoll != null) {
                    running = true;
                    textRoll.setTextColor(context.getResources().getColor(android.R.color.holo_blue_dark));
                    Random rn = new Random();
//                    int range = max - min + 1;
                    int randomNum = rn.nextInt((max - min) + 1) + min;
                    String roll = String.valueOf(randomNum);
                    textRoll.setText(roll);

                    numTimes--;

                    if (numTimes > 0) {
                        textRoll.postDelayed(this, delayMillis);
                    }
                }
            }
        }

        public void shutdownThread(boolean isSpecial) {
            if (textRoll != null) {
                if (isSpecial) {
                    textRoll.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                } else {
                    textRoll.setTextColor(context.getResources().getColor(android.R.color.black));
                }
            }
            shutdown = true;
        }

        public boolean isRun() {
            return running;
        }
    }
}
