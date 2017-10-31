package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.xproject.xoso.xoso.view.fragment.BasicFragment;
import com.xproject.xoso.R;

import java.util.List;

public class SettingActivity extends BasicActivity {

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

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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

        } else if (id == android.R.id.home) {
            if (mViewPager.getCurrentItem() == 0) {
                PlaceholderFragment page = (PlaceholderFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.container + ":" + mViewPager.getCurrentItem());
                // based on the current position you can then cast the page to the correct
                // class and call the method:
                page.onSave();
            }
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        PlaceholderFragment page = (PlaceholderFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.container + ":" + mViewPager.getCurrentItem());
        // based on the current position you can then cast the page to the correct
        // class and call the method:
        page.onSave();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mViewPager.getCurrentItem() == 0) {
            PlaceholderFragment page = (PlaceholderFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.container + ":" + mViewPager.getCurrentItem());
            // based on the current position you can then cast the page to the correct
            // class and call the method:
            page.onSave();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends BasicFragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_ACTION_NUMBER = "section_number";
        private int type_action = 0;

        private Spinner sp_province;
        private AdapterSpinner adapterSpinner;
        private List<ProvinceEntity> provinceEntityList;

        private CheckBox chkNotifyNorth, chkNotifyCentral, chkNotifySouth,
                spinVibrate, playMusic;

        private RadioButton radio_n, radio_c, radio_s;
        private RadioGroup radio_group_region;
        private boolean viewCreated = false;

        private TextView tv_content, tv_content_1;


        /**
         * Variable flag get from SharePref
         */
        private int tmp_flag_region_radio = 0, tmp_province_code = 0;
        private boolean tmp_flag_n = true, tmp_flag_c = true, tmp_flag_s = true, firs_save = false;


        /**
         * Variable flag save to SharePref
         */
        private int flag_region_radio = 0;
        private boolean flag_n = false, flag_c = false, flag_s = false, tmp_vibrate = false, tmp_sound = false;
        private int province_code = -1;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int type_action) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_ACTION_NUMBER, type_action);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            type_action = getArguments().getInt(ARG_ACTION_NUMBER);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            if (type_action == 0) {
                View rootView = inflater.inflate(R.layout.fragment_setting_region, container, false);
                initView(rootView);
                return rootView;
            } else if (type_action == 1) {
                View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
                initView2(rootView);
                return rootView;
            }
            return null;
        }


        void initView2(View rootView){
            tv_content = (TextView) rootView.findViewById(R.id.tv_content);
            tv_content_1 = (TextView) rootView.findViewById(R.id.tv_content_1);
            String result = "Vui lòng đọc kỹ Thoả Thuận Sử Dụng trước khi bạn tiến hành tải và cải đặt ứng dụng XosoVIP.\n" +
                    "Bằng việc tải và cài đặt ứng dụng, bạn đã chấp nhận và đồng ý bị ràng buộc bởi các điều khoản của chúng tôi. Trường hợp bạn không đồng ý với bất kỳ điều khoản sử dụng nào của chúng tôi, vui lòng không tải, cài đặt và sử dụng ứng dụng hoặc tháo gỡ ứng dụng khỏi thiết bị di động của bạn." +
                    "<br>" +
                    "<br>" +
                    "<br><b>1.\tCập nhật điều khoản:</b><br>" +
                    "Thoả thuận này có thể được cập nhật thường xuyên bởi công ty quản lý ứng dụng (XTL). Phiên bản cập nhật sẽ hiển thị trên phiên bản ứng dụng mới nhất. Phiên bản cập nhật sẽ thay thế cho các quy định và điều kiện thoản thuận ban đầu. Vui lòng truy cập vào ứng dụng Cài đặt – Điều khoản để xem nội dung chi tiết.<br>" +
                    "<br><b>2.\tGiới thiệu về Ứng dụng</b><br>" +
                    "XosoVIP là ứng dụng tra cứu kết quả xổ số 3 miền (Bắc – Trung - Nam). Dành riêng cho người dùng di động tại Việt Nam. Ứng dụng không yêu cầu bất kỳ thông tin của người dùng ngoài việc sử dụng mạng Internet để truy xuất dữ liệu. Các tính năng chính của ứng dụng là: Thống kê lô tô nhanh, Thống kê lô tô Vip, Truy xuất kết quả 3 miền Bắc – Trung – Nam, Tường thuật trực tiếp khi quay số 3 miền, Soi cầu lô tô, Quay thử, Theo dõi lịch quay thưởng. Ứng dụng hỗ trợ trên các nên tảng là Android, iOS.<br>" +
                    "<br><b>3.\tQuyền sở hữu ứng dụng:</b><br>" +
                    "Ứng dụng này được phát triển và sở hữu bởi XTL, tất cả các quyền sở hữu trí tuệ liên quan đến ứng dụng bao gồm (nhưng không giới hạn): Mã nguồn, hình ảnh, dữ liệu, thông tin, nội dung chứa đựng trong ứng dụng.<br>" +
                    "<br><b>4.\tThông tin dữ liệu:</b><br>" +
                    "Mọi thông tin trên ứng dụng được xây dựng nhằm mục đích tham khảo. Chúng tôi không chịu trách nhiệm khi người dung sử dụng thông tin ứng dụng vào mục đích cá nhân và vi phạm pháp luật. \n";
            tv_content.setText(Html.fromHtml(result));

            String bold = "Lưu ý: Thông tin dữ liệu trong ứng dụng chỉ mang tính chất tham khảo. <br>" +
                    "<br>" +
                    "Trân trọng cảm ơn bạn đã sử dụng sản phẩm và dịch vụ của chúng tôi.";
            tv_content_1.setText(Html.fromHtml(bold));
        }

        private void initView(View rootView) {
            provinceEntityList = getCategory();
            sp_province = (Spinner) rootView.findViewById(R.id.sp_province);
            adapterSpinner = new AdapterSpinner(provinceEntityList, getActivity());
            sp_province.setAdapter(adapterSpinner);
            sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    province_code = provinceEntityList.get(i).getMavung();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            radio_group_region = (RadioGroup) rootView.findViewById(R.id.radio_group_region);

            radio_n = (RadioButton) rootView.findViewById(R.id.radio_n);
            radio_c = (RadioButton) rootView.findViewById(R.id.radio_c);
            radio_s = (RadioButton) rootView.findViewById(R.id.radio_s);

            chkNotifyNorth = (CheckBox) rootView.findViewById(R.id.checkNotifyNorth);
            chkNotifyCentral = (CheckBox) rootView.findViewById(R.id.checkNotifyCentral);
            chkNotifySouth = (CheckBox) rootView.findViewById(R.id.checkNotifySouth);
            chkNotifyNorth.setChecked(true);
            chkNotifyCentral.setChecked(true);
            chkNotifySouth.setChecked(true);

            spinVibrate = (CheckBox) rootView.findViewById(R.id.checkVibrate);
            playMusic = (CheckBox) rootView.findViewById(R.id.checkPlay);
            playMusic.setChecked(true);

            Log.e("Setting fragment", "province_code: " + SharedUtils.getInstance().getIntValue(Constants.PROVINCE_FAVORITE_CODE));
            Log.e("Setting fragment", "tmp_flag_region_radio: " + SharedUtils.getInstance().getIntValue(Constants.FLAG_RADIO_REGION));

            Log.e("Setting fragment", "tmp_flag_n: " + SharedUtils.getInstance().getBooleanValue(Constants.NOTIFY_N_FLAG));
            Log.e("Setting fragment", "tmp_flag_c: " + SharedUtils.getInstance().getBooleanValue(Constants.NOTIFY_C_FLAG));
            Log.e("Setting fragment", "tmp_flag_s: " + SharedUtils.getInstance().getBooleanValue(Constants.NOTIFY_S_FLAG));

            if (!firs_save) {
                tmp_province_code = SharedUtils.getInstance().getIntValue(Constants.PROVINCE_FAVORITE_CODE);
                tmp_flag_region_radio = SharedUtils.getInstance().getIntValue(Constants.FLAG_RADIO_REGION);

                tmp_flag_n = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.NOTIFY_N_FLAG);
                tmp_flag_c = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.NOTIFY_C_FLAG);
                tmp_flag_s = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.NOTIFY_S_FLAG);

                tmp_sound = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.SOUND_FLAG);
                tmp_vibrate = SharedUtils.getInstance().getBooleanValue(Constants.ViBRATE_FLAG);
            }
        }

        private List<ProvinceEntity> getCategory() {
            return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            if (type_action == 0){
                viewCreated = true;
                getData();
            }
        }

        public void onSave() {
            if (viewCreated) {
                if (initRadioGroupSelected()) {
                    initCheckNotify();
                    checkVibrateAndSound();
                    SharedUtils.getInstance().putIntValue(Constants.FLAG_RADIO_REGION, flag_region_radio);
                    SharedUtils.getInstance().putIntValue(Constants.PROVINCE_FAVORITE_CODE, province_code);
                    Log.e("Saving setting...", "saved");
                    firs_save = true;
                }
            }
        }

        private void getData() {
            if (tmp_province_code > 0) {
                for (int i = 0; i < provinceEntityList.size(); i++) {
                    if (provinceEntityList.get(i).getMavung() == tmp_province_code) {
                        sp_province.setSelection(i);
                    }
                }
            }


            /**
             * Radio group modified*/

            if (tmp_flag_region_radio > 0) {
                switch (tmp_flag_region_radio) {
                    case 1:
                        radio_group_region.check(radio_n.getId());
                        break;
                    case 2:
                        radio_group_region.check(radio_c.getId());
                        break;
                    case 3:
                        radio_group_region.check(radio_s.getId());
                        break;
                }
            } else {
                radio_group_region.check(radio_n.getId());
            }

            /**
             * Checkbox notify modified*/
            if (!tmp_flag_n)
                chkNotifyNorth.setChecked(false);
            else
                chkNotifyNorth.setChecked(true);

            if (!tmp_flag_c)
                chkNotifyCentral.setChecked(false);
            else
                chkNotifyCentral.setChecked(true);

            if (!tmp_flag_s)
                chkNotifySouth.setChecked(false);
            else
                chkNotifySouth.setChecked(true);

            if (!tmp_sound)
                playMusic.setChecked(false);
            else
                playMusic.setChecked(true);

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
            if (selectedId != -1) {
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

        @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
            if (type_action == 0){
                onSave();
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (type_action == 0){
                onSave();
            }
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
            if (position == 0) {
                return PlaceholderFragment.newInstance(0);
            } else if (position == 1) {
                return PlaceholderFragment.newInstance(1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
            switch (position) {
                case 0:
                    title = "Cài đặt";
                    break;
                case 1:
                    title = "Điều khoản";
                    break;
            }

            return title;
        }
    }

}
