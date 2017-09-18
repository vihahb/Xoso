package com.xtelsolution.xoso.xoso;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;


/**
 * Created by vivhp on 9/7/2017.
 */

public class ProjectApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initLeakCanary();
    }

    private void initLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//             This process is dedicated to LeakCanary for heap analysis.
//             You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
//         Normal app init code...
    }
}
