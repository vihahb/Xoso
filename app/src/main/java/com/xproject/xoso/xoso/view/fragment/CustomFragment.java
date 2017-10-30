package com.xproject.xoso.xoso.view.fragment;

import android.view.View;

/**
 * Created by ViVH on 5/5/2017
 */

public abstract class CustomFragment extends BasicFragment {

    protected View view;
    protected boolean isWatched = false;

    protected View onCreateView(View view) {
        if (this.view == null)
            this.view = view;

        return this.view;

    }

    protected void onViewCreated() {
        if (!isWatched) {
            onCreateViewFirst(view);
            isWatched = true;
        } else
            onCreateViewAgain(view);
    }

    protected abstract void onCreateViewFirst(View view);

    protected abstract void onCreateViewAgain(View view);
}