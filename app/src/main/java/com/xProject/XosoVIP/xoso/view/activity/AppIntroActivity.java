/*
package com.xProject.XosoVIP.xoso.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro2;
import com.xProject.XosoVIP.R;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.SharedUtils;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.presenter.fragment.FragmentPresenter;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterSpinner;
import com.xProject.XosoVIP.xoso.view.fragment.BasicFragment;
import com.xProject.XosoVIP.xoso.view.fragment.inf.FragmentView;

import java.util.List;


public class AppIntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addSlide(new FragmentIntro());
        addSlide(new FragmentIntro1());
        addSlide(new FragmentIntro2());
        //Show Skip button
        showSkipButton(false);
        setProgressButtonEnabled(true);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        if (currentFragment instanceof FragmentIntro){
            FragmentIntro intro = (FragmentIntro) currentFragment;
            intro.onSave();
        }

        if (currentFragment instanceof FragmentIntro1){
            FragmentIntro1 intro = (FragmentIntro1) currentFragment;
            intro.onSave();
        }

        if (currentFragment instanceof FragmentIntro2){
            FragmentIntro2 intro = (FragmentIntro2) currentFragment;
            intro.onSave();
        }
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        if (currentFragment instanceof FragmentIntro){
            FragmentIntro intro = (FragmentIntro) currentFragment;
            intro.onSave();
        }

        if (currentFragment instanceof FragmentIntro1){
            FragmentIntro1 intro = (FragmentIntro1) currentFragment;
            intro.onSave();
        }

        if (currentFragment instanceof FragmentIntro2){
            FragmentIntro2 intro = (FragmentIntro2) currentFragment;
            intro.onSave();
        }
        SharedUtils.getInstance().putBooleanValue(Constants.APP_INTRO, true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        if (oldFragment instanceof FragmentIntro){
            FragmentIntro intro = (FragmentIntro) oldFragment;
            intro.onSave();
        }

        if (oldFragment instanceof FragmentIntro1){
            FragmentIntro1 intro = (FragmentIntro1) oldFragment;
            intro.onSave();
        }

        if (oldFragment instanceof FragmentIntro2){
            FragmentIntro2 intro = (FragmentIntro2) oldFragment;
            intro.onSave();
        }
    }

    public static class FragmentIntro extends BasicFragment implements FragmentView{

        private Spinner sp_province;
        private List<ProvinceEntity> provinceEntityList;
        private FragmentPresenter presenter;
        private AdapterSpinner adapterSpinner;
        private int province_code = 0;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_intro_app_2, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            presenter = new FragmentPresenter(this);
            initView(view);
        }

        private void initView(View view) {
            provinceEntityList = presenter.getCategory();
            sp_province = (Spinner) view.findViewById(R.id.sp_province);
            adapterSpinner = new AdapterSpinner(provinceEntityList, getActivity(), true);
            sp_province.setAdapter(adapterSpinner);
            initSpinnerSelected();
        }

        private void initSpinnerSelected() {
            sp_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   province_code = provinceEntityList.get(i).getMavung();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

        public void onSave(){
            SharedUtils.getInstance().putIntValue(Constants.PROVINCE_FAVORITE_CODE, province_code);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            SharedUtils.getInstance().putIntValue(Constants.PROVINCE_FAVORITE_CODE, province_code);
        }

        @Override
        public void getProvinceList(List<ProvinceEntity> provinceEntityList) {

        }
    }

    public static class FragmentIntro1 extends BasicFragment {

        private RadioButton radio_n, radio_c, radio_s;
        private RadioGroup radio_group_region;
        private int flag_region_radio;


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_intro_app_3, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            initView(view);
        }

        private void initView(View rootView) {
            radio_group_region = (RadioGroup) rootView.findViewById(R.id.radio_group_region);

            radio_n = (RadioButton) rootView.findViewById(R.id.radio_n);
            radio_c = (RadioButton) rootView.findViewById(R.id.radio_c);
            radio_s = (RadioButton) rootView.findViewById(R.id.radio_s);
            radio_n.toggle();
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

        public void onSave(){
            if (initRadioGroupSelected()){
                SharedUtils.getInstance().putIntValue(Constants.FLAG_RADIO_REGION, flag_region_radio);
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            SharedUtils.getInstance().putIntValue(Constants.FLAG_RADIO_REGION, flag_region_radio);
        }
    }

    public static class FragmentIntro2 extends BasicFragment {

        Button btn_yes, btn_no;
        private boolean notSet = true;


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_intro_app_4, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            initView(view);
        }

        private void initView(View rootView) {
            btn_yes = (Button) rootView.findViewById(R.id.btn_yes);
            btn_no = (Button) rootView.findViewById(R.id.btn_no);
            btn_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notSet = false;
                    btn_no.setEnabled(false);
                    btn_yes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_done_check, 0, 0, 0);
                    SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_N_FLAG, true);
                    SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_C_FLAG, true);
                    SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_S_FLAG, true);
                }
            });

            btn_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notSet = false;
                    btn_yes.setEnabled(false);
                    btn_no.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_done_check, 0, 0, 0);
                    SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_N_FLAG, false);
                    SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_C_FLAG, false);
                    SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_S_FLAG, false);
                }
            });
        }

        public void onSave(){
            if (notSet){
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_N_FLAG, true);
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_C_FLAG, true);
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_S_FLAG, true);
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (notSet){
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_N_FLAG, true);
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_C_FLAG, true);
                SharedUtils.getInstance().putBooleanValue(Constants.NOTIFY_S_FLAG, true);
            }
        }
    }
}
*/
