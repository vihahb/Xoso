package com.xProject.XosoVIP.xoso.view.fragment;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by vivhp on 9/6/2017.
 */

public class IFragment extends Fragment {
    protected Toolbar findToolbar(int id) {
        return (Toolbar) getView().findViewById(id);
    }

    protected DrawerLayout findDrawerLayout(int id) {
        return (DrawerLayout) getView().findViewById(id);
    }

    protected LinearLayout findLinearLayout(int id) {
        return (LinearLayout) getView().findViewById(id);
    }

    protected NavigationView findNavigationView(int id) {
        return (NavigationView) getView().findViewById(id);
    }

    protected BottomNavigationView findBottomNavigationView(int id) {
        return (BottomNavigationView) getView().findViewById(id);
    }

    protected FloatingActionButton findFloatingActionButton(int id) {
        return (FloatingActionButton) getView().findViewById(id);
    }

    protected RecyclerView findRecyclerView(int id) {
        return (RecyclerView) getView().findViewById(id);
    }

    protected EditText findEditText(int id) {
        return (EditText) getView().findViewById(id);
    }

    protected TextView findTextView(int id) {
        return (TextView) getView().findViewById(id);
    }

    protected Button findButton(int id) {
        return (Button) getView().findViewById(id);
    }

    protected CheckBox findCheckBox(int id) {
        return (CheckBox) getView().findViewById(id);
    }

    protected Spinner findSpinner(int id) {
        return (Spinner) getView().findViewById(id);
    }

    protected ProgressBar findProgressBar(int id) {
        return (ProgressBar) getView().findViewById(id);
    }

    protected SeekBar findSeekBar(int id) {
        return (SeekBar) getView().findViewById(id);
    }

    protected RatingBar findRatingBar(int id) {
        return (RatingBar) getView().findViewById(id);
    }

    protected ImageView findImageView(int id) {
        return (ImageView) getView().findViewById(id);
    }

    protected ImageButton findImageButton(int id) {
        return (ImageButton) getView().findViewById(id);
    }

    protected SwipeRefreshLayout findSwipeRefreshLayout(int id) {
        return (SwipeRefreshLayout) getView().findViewById(id);
    }

    protected View findView(int id) {
        return getView().findViewById(id);
    }

    protected AutoCompleteTextView findAutoCompleteTextView(int id) {
        return (AutoCompleteTextView) getView().findViewById(id);
    }
}
