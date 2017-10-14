package com.xproject.xoso.xoso.view.activity;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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

import com.xproject.xoso.xoso.view.activity.inf.BasicView;

/**
 * Created by ViVH
 */

public abstract class IActivity extends AppCompatActivity implements BasicView {

    protected Toolbar findToolbar(int id) {
        return (Toolbar) findViewById(id);
    }

    protected DrawerLayout findDrawerLayout(int id) {
        return (DrawerLayout) findViewById(id);
    }

    protected NavigationView findNavigationView(int id) {
        return (NavigationView) findViewById(id);
    }

    protected BottomNavigationView findBottomNavigationView(int id) {
        return (BottomNavigationView) findViewById(id);
    }

    protected FloatingActionButton findFloatingActionButton(int id) {
        return (FloatingActionButton) findViewById(id);
    }

    protected RecyclerView findRecyclerView(int id) {
        return (RecyclerView) findViewById(id);
    }

    protected EditText findEditText(int id) {
        return (EditText) findViewById(id);
    }

    protected AutoCompleteTextView findAutoCompleteTextView(int id) {
        return (AutoCompleteTextView) findViewById(id);
    }

    protected TextView findTextView(int id) {
        return (TextView) findViewById(id);
    }

    protected Button findButton(int id) {
        return (Button) findViewById(id);
    }

    protected CheckBox findCheckBox(int id) {
        return (CheckBox) findViewById(id);
    }

    protected Spinner findSpinner(int id) {
        return (Spinner) findViewById(id);
    }

    protected ProgressBar findProgressBar(int id) {
        return (ProgressBar) findViewById(id);
    }

    protected SeekBar findSeekBar(int id) {
        return (SeekBar) findViewById(id);
    }

    protected RatingBar findRatingBar(int id) {
        return (RatingBar) findViewById(id);
    }

    protected ImageView findImageView(int id) {
        return (ImageView) findViewById(id);
    }

    protected ImageButton findImageButton(int id) {
        return (ImageButton) findViewById(id);
    }

    protected SwipeRefreshLayout findSwipeRefreshLayout(int id) {
        return (SwipeRefreshLayout) findViewById(id);
    }

    protected LinearLayout findLinearLayout(int id) {
        return (LinearLayout) findViewById(id);
    }

    protected View findView(int id) {
        return findViewById(id);
    }

    protected TabLayout findTabLayout(int id) {
        return (TabLayout) findViewById(id);
    }

    protected ViewPager findViewPager(int id) {
        return (ViewPager) findViewById(id);
    }
}
