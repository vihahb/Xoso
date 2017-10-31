package com.xProject.XosoVIP.xoso;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.FirebaseApp;
import com.xProject.XosoVIP.sdk.utils.TypefaceUtil;

import io.fabric.sdk.android.Fabric;


/**
 * Created by vivhp on 9/7/2017.
 */

public class ProjectApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(context);
        Fabric.with(this, new Crashlytics());
        context = this;
        initTypeFace();
//        initLeakCanary();
    }

    private void initTypeFace() {
        TypefaceUtil.overrideFont(context, "NORMAL", "fonts/OpenSans-Light.ttf");
    }

//    private void initLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
////             This process is dedicated to LeakCanary for heap analysis.
////             You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
////         Normal app init code...
//    }
}
