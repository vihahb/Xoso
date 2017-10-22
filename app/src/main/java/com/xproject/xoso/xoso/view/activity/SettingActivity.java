package com.xproject.xoso.xoso.view.activity;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.sdk.utils.SharedUtils;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.view.adapter.AdapterSpinner;
import com.xproject.xoso.xoso.view.fragment.inf.OnCompleteListener;
import com.xtelsolution.xoso.R;

import java.util.List;

public class SettingActivity extends BasicActivity implements OnCompleteListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Cài đặt và điều khoản");
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                showShortToast("" + position);
                switch (position) {
                    case 0:
                        if (item != null)
                            item.setVisible(true);
                        break;
                    case 1:
                        if (item != null)
                            item.setVisible(false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        item = menu.findItem(R.id.action_save);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            PlaceholderFragment fragment = (PlaceholderFragment) mSectionsPagerAdapter.getItem(0);
            fragment.onSave();
            showShortToast("Đã lưu cài đặt.");
        } else if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onComplete() {
//        ((PlaceholderFragment) mSectionsPagerAdapter.getItem(0)).onSave();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private int section;

        private Button btnResult;
        private Spinner sp_province;
        private AdapterSpinner adapterSpinner;
        private List<ProvinceEntity> provinceEntityList;

        private CheckBox chkNotifyNorth, chkNotifyCentral, chkNotifySouth,
                spinVibrate, playMusic;

        private RadioButton radio_n, radio_c, radio_s;
        private RadioGroup radio_group_region;
        private boolean viewCreated = false;

        private OnCompleteListener mListener;

        /**
         * Variable flag get from SharePref
         */
        private int tmp_flag_region_radio = 0;
        private boolean tmp_flag_n = false, tmp_flag_c = false, tmp_flag_s = false;


        /**
         * Variable flag save to SharePref
         */
        private int flag_region_radio = 0;
        private boolean flag_n = false, flag_c = false, flag_s = false;
        private int province_code = -1;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance() {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            try {
                this.mListener = (OnCompleteListener)activity;
            }
            catch (final ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
            }
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_setting_region, container, false);
            initView(rootView);
            return rootView;
        }

        private void initView(View rootView) {
            provinceEntityList = getCategory();
            sp_province = (Spinner) rootView.findViewById(R.id.sp_province);
            adapterSpinner = new AdapterSpinner(provinceEntityList, getActivity());
            sp_province.setAdapter(adapterSpinner);

            radio_group_region = (RadioGroup) rootView.findViewById(R.id.radio_group_region);

            radio_n = (RadioButton) rootView.findViewById(R.id.radio_n);
            radio_c = (RadioButton) rootView.findViewById(R.id.radio_c);
            radio_s = (RadioButton) rootView.findViewById(R.id.radio_s);

            chkNotifyNorth = (CheckBox) rootView.findViewById(R.id.checkNotifyNorth);
            chkNotifyCentral = (CheckBox) rootView.findViewById(R.id.checkNotifyCentral);
            chkNotifySouth = (CheckBox) rootView.findViewById(R.id.checkNotifySouth);

            spinVibrate = (CheckBox) rootView.findViewById(R.id.checkVibrate);
            playMusic = (CheckBox) rootView.findViewById(R.id.checkPlay);

            btnResult = (Button) rootView.findViewById(R.id.btn_result);
        }

        private List<ProvinceEntity> getCategory() {
            return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
                viewCreated = true;
                mListener.onComplete();
                getData();
        }

        public void onSave() {
                if (viewCreated) {
                    if (initRadioGroupSelected()) {
                        initCheckNotify();
                        checkVibrateAndSound();
                    }
                }
        }

        private void getData() {

            province_code = SharedUtils.getInstance().getIntValue(Constants.PROVINCE_FAVORITE_CODE);
            if (province_code > 0) {
                for (int i = 0; i < provinceEntityList.size(); i++) {
                    if (provinceEntityList.get(i).getMavung() == province_code) {
                        sp_province.setSelection(i);
                    }
                }
            }


            /**
             * Radio group modified*/
            tmp_flag_region_radio = SharedUtils.getInstance().getIntValue(Constants.FLAG_RADIO_REGION);
            if (tmp_flag_region_radio > 0) {
                switch (flag_region_radio) {
                    case 1:
                        radio_n.isChecked();
                        break;
                    case 2:
                        radio_c.isChecked();
                        break;
                    case 3:
                        radio_s.isChecked();
                        break;
                }
            }

            /**
             * Checkbox notify modified*/
            tmp_flag_n = SharedUtils.getInstance().getBooleanValue(Constants.NOTIFY_N_FLAG);
            if (flag_n)
                chkNotifyNorth.isChecked();

            tmp_flag_c = SharedUtils.getInstance().getBooleanValue(Constants.NOTIFY_C_FLAG);
            if (flag_c)
                chkNotifyCentral.isChecked();

            tmp_flag_s = SharedUtils.getInstance().getBooleanValue(Constants.NOTIFY_S_FLAG);
            if (flag_s)
                chkNotifySouth.isChecked();
        }

        private void initCheckNotify() {
            if (chkNotifyNorth.isChecked())
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_N_FLAG, true);
            else
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_N_FLAG, false);

            if (chkNotifyCentral.isChecked())
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_C_FLAG, true);
            else
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_C_FLAG, false);

            if (chkNotifySouth.isChecked())
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_S_FLAG, true);
            else
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_S_FLAG, false);
        }

        private void checkVibrateAndSound() {
            if (spinVibrate.isChecked())
                SharedUtils.getInstance().putBooleanValue(Constants.ViBRATE_FLAG, true);
            else
                SharedUtils.getInstance().putBooleanValue(Constants.ViBRATE_FLAG, false);

            if (playMusic.isChecked())
                SharedUtils.getInstance().putBooleanValue(Constants.SOUND_FLAG, true);
            else
                SharedUtils.getInstance().putBooleanValue(Constants.SOUND_FLAG, false);
        }

        private boolean initRadioGroupSelected() {
            int selectedId = radio_group_region.getCheckedRadioButtonId();
            if (selectedId !=-1) {
                switch (selectedId) {
                    case R.id.radio_n:
                        flag_region_radio = 1;
                        break;
                    case R.id.radio_c:
                        flag_region_radio = 2;
                        break;
                    case R.id.radio_s:
                        flag_region_radio = 3;
                        break;
                }
                return true;
            } else {
                Toast.makeText(getContext(), "Vui lòng chọn vùng miền yêu thích!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

    }

    public static class PlaceholderFragmentTwo extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private int section;

        /**
         * Variable flag get from SharePref
         */
        private int tmp_flag_region_radio = 0;
        private boolean tmp_flag_n = false, tmp_flag_c = false, tmp_flag_s = false;


        /**
         * Variable flag save to SharePref
         */
        private int flag_region_radio = 0;
        private boolean flag_n = false, flag_c = false, flag_s = false;
        private int province_code = -1;

        public PlaceholderFragmentTwo() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragmentTwo newInstance(int sectionNumber) {
            PlaceholderFragmentTwo fragment = new PlaceholderFragmentTwo();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            section = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0){
                return PlaceholderFragment.newInstance();
            } else if (position == 1){
                return PlaceholderFragmentTwo.newInstance(position + 1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

}
