package com.xProject.XosoVIP.xoso.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xProject.XosoVIP.R;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.AudioPlay;
import com.xProject.XosoVIP.sdk.utils.CalendarUtils;
import com.xProject.XosoVIP.sdk.utils.SharedUtils;
import com.xProject.XosoVIP.sdk.utils.TextUtils;
import com.xProject.XosoVIP.sdk.utils.TimeUtils;
import com.xProject.XosoVIP.xoso.model.entity.BeginResult;
import com.xProject.XosoVIP.xoso.model.entity.CurentResult;
import com.xProject.XosoVIP.xoso.model.entity.EndResult;
import com.xProject.XosoVIP.xoso.model.entity.ResultLottery;
import com.xProject.XosoVIP.xoso.model.respond.RESP_LiveLoto;
import com.xProject.XosoVIP.xoso.model.respond.RESP_NewResult;
import com.xProject.XosoVIP.xoso.presenter.fragment.FragmentNorthContentPresenter;
import com.xProject.XosoVIP.xoso.view.activity.MainActivity;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterLoto;
import com.xProject.XosoVIP.xoso.view.fragment.inf.IFragmentNorthContentView;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by vivhp on 9/8/2017.
 */

public class FragmentNorthContent extends BasicFragment implements IFragmentNorthContentView {

    private static final String TAG = "FragmentNorthContent";
    private static final String KEY_DATE = "date";
    private static FragmentNorthContent instance;
    long millis;
    String getDateTime;
    View rootView;
    private FragmentNorthContentPresenter presenter;
    private TextView tvContent;
    private AdapterLoto adapterLoto;
    private LinearLayout loadingView;
    private ImageView img_mute;
    private boolean isExistsBegin = false;
    private boolean isExistsEnd = false;
    /**
     * Value table result lottery
     */

    private TextView tv_title, tv_not_yet;
    private TextView tvSpecial, tvFirst, tv_21, tv_22,
            tv_31, tv_32, tv_33, tv_34, tv_35, tv_36,
            tv_41, tv_42, tv_43, tv_44,
            tv_51, tv_52, tv_53, tv_54, tv_55, tv_56,
            tv_61, tv_62, tv_63,
            tv_71, tv_72, tv_73, tv_74;


    private TextView tvLoto0, tvLoto1, tvLoto2, tvLoto3, tvLoto4, tvLoto5, tvLoto6, tvLoto7, tvLoto8, tvLoto9,
            tvLotoDuoi0, tvLotoDuoi1, tvLotoDuoi2, tvLotoDuoi3, tvLotoDuoi4, tvLotoDuoi5, tvLotoDuoi6, tvLotoDuoi7, tvLotoDuoi8, tvLotoDuoi9;

    private boolean toDay, mute = false;
    private AudioManager audioManager;
    private Roller special, first, r71, r72, r73, r74,
            r61, r62, r63,
            r51, r52, r53, r54, r55, r56,
            r41, r42, r43, r44,
            r31, r32, r33, r34, r35, r36,
            r21, r22;
    private boolean isLive = false, sound = true;
    private Context context;
    private String LotoSpecial;
    private String tmp_b0 = "", tmp_b1 = "", tmp_b2 = "", tmp_b3 = "", tmp_b4 = "", tmp_b5 = "", tmp_b6 = "", tmp_b7 = "", tmp_b8 = "", tmp_b9 = "";
    private String tmp_e0 = "", tmp_e1 = "", tmp_e2 = "", tmp_e3 = "", tmp_e4 = "", tmp_e5 = "", tmp_e6 = "", tmp_e7 = "", tmp_e8 = "", tmp_e9 = "";
    private TextView tv_title_region;

    public static FragmentNorthContent newInstance(long date) {
        instance = new FragmentNorthContent();
        Bundle args = new Bundle();
        args.putLong(KEY_DATE, date);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FragmentNorthContentPresenter(this);
        millis = getArguments().getLong(KEY_DATE);

        if (millis > 0) {
            final Context context = getActivity();
            getDateTime = TimeUtils.getFormattedDate(context, millis);
            String todays = TimeUtils.getToday();
            toDay = todays.trim().equals(getDateTime.trim());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_north_content, container, false);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(final View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        context = getContext();
        boolean check_done = SharedUtils.getInstance().getBooleanValue(Constants.CHECK_DONE_N);
        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        tv_not_yet = findTextView(R.id.tv_not_yet);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_title_region = (TextView) rootView.findViewById(R.id.tv_title_region);
                initTextView(rootView);
                initOtherView(rootView);
                initRoller();

                if (toDay && TimeUtils.checkTimeInMilisecondNorth(18, 10, 23, 59)) {
                    tv_not_yet.setVisibility(View.GONE);
                } else if (toDay && TimeUtils.checkTimeInMilisecondNorth(0, 0, 18, 9)) {
                    tv_not_yet.setVisibility(View.VISIBLE);
                } else {
                    tv_not_yet.setVisibility(View.GONE);
                }

                if (toDay && TimeUtils.checkTimeInMilisecondNorth(0,0,18,40)){
                    tv_title_region.setText("Trực tiếp xổ số Truyền thống");
                } else {
                    tv_title_region.setText("Kết quả xổ số Truyền thống");
                }

                if (toDay && TimeUtils.checkTimeInMilisecondNorth(18, 10, 18, 40)) {
                    isLive = true;
                    tv_title_region.setText("Trực tiếp xổ số Truyền thống");
                    presenter.socketConnect(toDay);
                } else {
                    presenter.getResultLottery(getDateTime);
                }
            }

        }, 100);
    }

    private void initOtherView(View rootView) {
        img_mute = (ImageView) rootView.findViewById(R.id.img_mute);
        RecyclerView rcl_loto_live = (RecyclerView) rootView.findViewById(R.id.rcl_loto_live);
        loadingView = (LinearLayout) rootView.findViewById(R.id.loadingView);
        tvContent = (TextView) rootView.findViewById(R.id.tvContent);
        tv_title = (TextView) rootView.findViewById(R.id.tv_title);

        List<String> loto_live = new ArrayList<>();
        adapterLoto = new AdapterLoto(loto_live, getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 9);
        layoutManager.setAutoMeasureEnabled(true);
        rcl_loto_live.setLayoutManager(layoutManager);
        rcl_loto_live.setAdapter(adapterLoto);

        getTitle();
        setMute();
    }

    private void initTextView(View view) {

        tvSpecial = (TextView) view.findViewById(R.id.tvSpecialValue);
        tvFirst = (TextView) view.findViewById(R.id.tvFirstValue);
        tv_21 = (TextView) view.findViewById(R.id.tvSecondValue_1);
        tv_22 = (TextView) view.findViewById(R.id.tvSecondValue_2);
        tv_31 = (TextView) view.findViewById(R.id.tv3_1);
        tv_32 = (TextView) view.findViewById(R.id.tv3_2);
        tv_33 = (TextView) view.findViewById(R.id.tv3_3);
        tv_34 = (TextView) view.findViewById(R.id.tv3_4);
        tv_35 = (TextView) view.findViewById(R.id.tv3_5);
        tv_36 = (TextView) view.findViewById(R.id.tv3_6);
        tv_41 = (TextView) view.findViewById(R.id.tv4_1);
        tv_42 = (TextView) view.findViewById(R.id.tv4_2);
        tv_43 = (TextView) view.findViewById(R.id.tv4_3);
        tv_44 = (TextView) view.findViewById(R.id.tv4_4);
        tv_51 = (TextView) view.findViewById(R.id.tv5_1);
        tv_52 = (TextView) view.findViewById(R.id.tv5_2);
        tv_53 = (TextView) view.findViewById(R.id.tv5_3);
        tv_54 = (TextView) view.findViewById(R.id.tv5_4);
        tv_55 = (TextView) view.findViewById(R.id.tv5_5);
        tv_56 = (TextView) view.findViewById(R.id.tv5_6);
        tv_61 = (TextView) view.findViewById(R.id.tv6_1);
        tv_62 = (TextView) view.findViewById(R.id.tv6_2);
        tv_63 = (TextView) view.findViewById(R.id.tv6_3);
        tv_71 = (TextView) view.findViewById(R.id.tv7_1);
        tv_72 = (TextView) view.findViewById(R.id.tv7_2);
        tv_73 = (TextView) view.findViewById(R.id.tv7_3);
        tv_74 = (TextView) view.findViewById(R.id.tv7_4);

        tvLoto0 = (TextView) view.findViewById(R.id.tvLoto0);
        tvLoto1 = (TextView) view.findViewById(R.id.tvLoto1);
        tvLoto2 = (TextView) view.findViewById(R.id.tvLoto2);
        tvLoto3 = (TextView) view.findViewById(R.id.tvLoto3);
        tvLoto4 = (TextView) view.findViewById(R.id.tvLoto4);
        tvLoto5 = (TextView) view.findViewById(R.id.tvLoto5);
        tvLoto6 = (TextView) view.findViewById(R.id.tvLoto6);
        tvLoto7 = (TextView) view.findViewById(R.id.tvLoto7);
        tvLoto8 = (TextView) view.findViewById(R.id.tvLoto8);
        tvLoto9 = (TextView) view.findViewById(R.id.tvLoto9);

        tvLotoDuoi0 = (TextView) view.findViewById(R.id.tvLotoDuoi0);
        tvLotoDuoi1 = (TextView) view.findViewById(R.id.tvLotoDuoi1);
        tvLotoDuoi2 = (TextView) view.findViewById(R.id.tvLotoDuoi2);
        tvLotoDuoi3 = (TextView) view.findViewById(R.id.tvLotoDuoi3);
        tvLotoDuoi4 = (TextView) view.findViewById(R.id.tvLotoDuoi4);
        tvLotoDuoi5 = (TextView) view.findViewById(R.id.tvLotoDuoi5);
        tvLotoDuoi6 = (TextView) view.findViewById(R.id.tvLotoDuoi6);
        tvLotoDuoi7 = (TextView) view.findViewById(R.id.tvLotoDuoi7);
        tvLotoDuoi8 = (TextView) view.findViewById(R.id.tvLotoDuoi8);
        tvLotoDuoi9 = (TextView) view.findViewById(R.id.tvLotoDuoi9);
    }

    private void setMute() {
        if (toDay) {
            img_mute.setVisibility(View.VISIBLE);
        } else {
            img_mute.setVisibility(View.GONE);
        }

        sound = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.SOUND_FLAG);
        if (sound) {
            mute = false;
            img_mute.setImageResource(R.mipmap.ic_mute);
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        } else {
            mute = true;
            img_mute.setImageResource(R.mipmap.ic_mute_on);
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        }

        img_mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mute) {
                    mute = true;
                    img_mute.setImageResource(R.mipmap.ic_mute_on);
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                } else {
                    mute = false;
                    img_mute.setImageResource(R.mipmap.ic_mute);
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                }
            }
        });
    }

    private void getTitle() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        String date_label = CalendarUtils.getDayName(calendar);
        if (date_label != null && !date_label.equals("")) {
            tv_title.setText(date_label + ", ngày " + calendar.get(Calendar.DAY_OF_MONTH) + " tháng " + (calendar.get(Calendar.MONTH) + 1) + " năm " + calendar.get(Calendar.YEAR));
        } else {
            tv_title.setText("Ngày " + calendar.get(Calendar.DAY_OF_MONTH) + " tháng " + (calendar.get(Calendar.MONTH) + 1) + " năm " + calendar.get(Calendar.YEAR));
        }
    }

    @Override
    public void cleaOldData() {
    }

    @Override
    public void setLiveLoto(RESP_LiveLoto liveLoto) {
        if (liveLoto.getLoto() != null) {
            adapterLoto.refreshLotoList(liveLoto.getLoto());
        }
        if (toDay) {
            setBeginLive(liveLoto.getBegin_with());
            setEndResultLive(liveLoto.getEnd_with());
        }
    }

    private void setBeginLive(BeginResult beginResult) {
        /**
         * Dau loto*/
        if (beginResult.getB0().size() > 0) {
            String begin_0 = "";
            for (int i = 0; i < beginResult.getB0().size(); i++) {
                begin_0 += beginResult.getB0().get(i) + " - ";
            }
            if (begin_0.length() > 0) {
                begin_0 = begin_0.substring(0, begin_0.length() - 3);
                if (android.text.TextUtils.isEmpty(tmp_b0)) {
                    tmp_b0 = begin_0;
                    TextUtils.getInstance().setAnimationTextView(tvLoto0);
                } else {
                    if (tmp_b0.length() < begin_0.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto0);
                        tmp_b0 = begin_0;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 0 - " + begin_0);
            tvLoto0.setText(Html.fromHtml(begin_0));
        }


        if (beginResult.getB1().size() > 0) {
            String begin_1 = "";
            for (int i = 0; i < beginResult.getB1().size(); i++) {
                begin_1 += beginResult.getB1().get(i) + " - ";
            }
            if (begin_1.length() > 0) {
                begin_1 = begin_1.substring(0, begin_1.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp_b1)) {
                    tmp_b1 = begin_1;
                    TextUtils.getInstance().setAnimationTextView(tvLoto1);
                } else {
                    if (tmp_b1.length() < begin_1.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto1);
                        tmp_b1 = begin_1;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 1 - " + begin_1);
            tvLoto1.setText(Html.fromHtml(begin_1));
        }

        if (beginResult.getB2().size() > 0) {
            String begin_2 = "";
            for (int i = 0; i < beginResult.getB2().size(); i++) {
                begin_2 += beginResult.getB2().get(i) + " - ";
            }
            if (begin_2.length() > 0) {
                begin_2 = begin_2.substring(0, begin_2.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp_b2)) {
                    tmp_b2 = begin_2;
                    TextUtils.getInstance().setAnimationTextView(tvLoto2);
                } else {
                    if (tmp_b2.length() < begin_2.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto2);
                        tmp_b2 = begin_2;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 2 - " + begin_2);
            tvLoto2.setText(Html.fromHtml(begin_2));
        }

        if (beginResult.getB3().size() > 0) {
            String begin_3 = "";
            for (int i = 0; i < beginResult.getB3().size(); i++) {
                begin_3 += beginResult.getB3().get(i) + " - ";
            }
            if (begin_3.length() > 0) {
                begin_3 = begin_3.substring(0, begin_3.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp_b3)) {
                    tmp_b3 = begin_3;
                    TextUtils.getInstance().setAnimationTextView(tvLoto3);
                } else {
                    if (tmp_b3.length() < begin_3.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto3);
                        tmp_b3 = begin_3;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 3 - " + begin_3);
            tvLoto3.setText(Html.fromHtml(begin_3));
        }

        if (beginResult.getB4().size() > 0) {
            String begin_4 = "";
            for (int i = 0; i < beginResult.getB4().size(); i++) {
                begin_4 += beginResult.getB4().get(i) + " - ";
            }
            if (begin_4.length() > 0) {
                begin_4 = begin_4.substring(0, begin_4.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp_b4)) {
                    tmp_b4 = begin_4;
                    TextUtils.getInstance().setAnimationTextView(tvLoto4);
                } else {
                    if (tmp_b4.length() < begin_4.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto4);
                        tmp_b4 = begin_4;
                    }
                }
            }
            tvLoto4.setText(Html.fromHtml(begin_4));
        }


        if (beginResult.getB5().size() > 0) {
            String begin_5 = "";
            for (int i = 0; i < beginResult.getB5().size(); i++) {
                begin_5 += beginResult.getB5().get(i) + " - ";
            }
            if (begin_5.length() > 0) {
                begin_5 = begin_5.substring(0, begin_5.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp_b5)) {
                    tmp_b5 = begin_5;
                    TextUtils.getInstance().setAnimationTextView(tvLoto5);
                } else {
                    if (tmp_b5.length() < begin_5.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto5);
                        tmp_b5 = begin_5;
                    }
                }
            }

            tvLoto5.setText(Html.fromHtml(begin_5));
        }

        if (beginResult.getB6().size() > 0) {
            String begin_6 = "";
            for (int i = 0; i < beginResult.getB6().size(); i++) {
                begin_6 += beginResult.getB6().get(i) + " - ";
            }
            if (begin_6.length() > 0) {
                begin_6 = begin_6.substring(0, begin_6.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp_b6)) {
                    tmp_b6 = begin_6;
                    TextUtils.getInstance().setAnimationTextView(tvLoto6);
                } else {
                    if (tmp_b6.length() < begin_6.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto6);
                        tmp_b6 = begin_6;
                    }
                }
            }
            tvLoto6.setText(Html.fromHtml(begin_6));
        }

        if (beginResult.getB7().size() > 0) {
            String begin_7 = "";
            for (int i = 0; i < beginResult.getB7().size(); i++) {
                begin_7 += beginResult.getB7().get(i) + " - ";
            }
            if (begin_7.length() > 0) {
                begin_7 = begin_7.substring(0, begin_7.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp_b7)) {
                    tmp_b7 = begin_7;
                    TextUtils.getInstance().setAnimationTextView(tvLoto7);
                } else {
                    if (tmp_b7.length() < begin_7.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto7);
                        tmp_b7 = begin_7;
                    }
                }
            }

            tvLoto7.setText(Html.fromHtml(begin_7));
        }

        if (beginResult.getB8().size() > 0) {
            String begin_8 = "";
            for (int i = 0; i < beginResult.getB8().size(); i++) {
                begin_8 += beginResult.getB8().get(i) + " - ";
            }
            if (begin_8.length() > 0) {
                begin_8 = begin_8.substring(0, begin_8.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp_b8)) {
                    tmp_b8 = begin_8;
                    TextUtils.getInstance().setAnimationTextView(tvLoto8);
                } else {
                    if (tmp_b8.length() < begin_8.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto8);
                        tmp_b8 = begin_8;
                    }
                }
            }
            tvLoto8.setText(Html.fromHtml(begin_8));
        }

        if (beginResult.getB9().size() > 0) {
            String begin_9 = "";
            for (int i = 0; i < beginResult.getB9().size(); i++) {
                begin_9 += beginResult.getB9().get(i) + " - ";
            }
            if (begin_9.length() > 0) {
                begin_9 = begin_9.substring(0, begin_9.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp_b9)) {
                    tmp_b9 = begin_9;
                    TextUtils.getInstance().setAnimationTextView(tvLoto9);
                } else {
                    if (tmp_b9.length() < begin_9.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto9);
                        tmp_b9 = begin_9;
                    }
                }
            }
            tvLoto9.setText(Html.fromHtml(begin_9));
        }
    }

    private void setEndResultLive(EndResult end_with) {
        /**
         * Duoi loto*/
        if (end_with.getE0() != null) {
            String end_0 = "";
            for (int i = 0; i < end_with.getE0().size(); i++) {
                end_0 += end_with.getE0().get(i) + " - ";
            }
            if (end_0.length() > 0) {
                end_0 = end_0.substring(0, end_0.length() - 2);

                if (android.text.TextUtils.isEmpty(tmp_e0)) {
                    tmp_e0 = end_0;
                    TextUtils.getInstance().setAnimationTextView(tvLotoDuoi0);
                } else {
                    if (tmp_e0.length() < end_0.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLotoDuoi0);
                        tmp_e0 = end_0;
                    }
                }
            }
            tvLotoDuoi0.setText(Html.fromHtml(end_0));
        }

        if (end_with.getE1() != null) {
            String end_1 = "";
            for (int i = 0; i < end_with.getE1().size(); i++) {
                end_1 += end_with.getE1().get(i) + " - ";
            }
            if (end_1.length() > 0) {
                end_1 = end_1.substring(0, end_1.length() - 2);
                if (android.text.TextUtils.isEmpty(tmp_e1)) {
                    tmp_e1 = end_1;
                    TextUtils.getInstance().setAnimationTextView(tvLotoDuoi1);
                } else {
                    if (tmp_e1.length() < end_1.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLotoDuoi1);
                        tmp_e1 = end_1;
                    }
                }
            }

            tvLotoDuoi1.setText(Html.fromHtml(end_1));
        }

        if (end_with.getE2() != null) {
            String end_2 = "";
            for (int i = 0; i < end_with.getE2().size(); i++) {
                end_2 += end_with.getE2().get(i) + " - ";
            }
            if (end_2.length() > 0) {
                end_2 = end_2.substring(0, end_2.length() - 2);
                if (android.text.TextUtils.isEmpty(tmp_e2)) {
                    tmp_e2 = end_2;
                    TextUtils.getInstance().setAnimationTextView(tvLotoDuoi2);
                } else {
                    if (tmp_e2.length() < end_2.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLotoDuoi2);
                        tmp_e2 = end_2;
                    }
                }
            }
            tvLotoDuoi2.setText(Html.fromHtml(end_2));
        }

        if (end_with.getE3() != null) {
            String end_3 = "";
            for (int i = 0; i < end_with.getE3().size(); i++) {
                end_3 += end_with.getE3().get(i) + " - ";
            }
            if (end_3.length() > 0) {
                end_3 = end_3.substring(0, end_3.length() - 2);
                if (android.text.TextUtils.isEmpty(tmp_e3)) {
                    tmp_e3 = end_3;
                    TextUtils.getInstance().setAnimationTextView(tvLotoDuoi3);
                } else {
                    if (tmp_e3.length() < end_3.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLotoDuoi3);
                        tmp_e3 = end_3;
                    }
                }
            }
            tvLotoDuoi3.setText(Html.fromHtml(end_3));
        }

        if (end_with.getE4() != null) {
            String end_4 = "";
            for (int i = 0; i < end_with.getE4().size(); i++) {
                end_4 += end_with.getE4().get(i) + " - ";
            }
            if (end_4.length() > 0) {
                end_4 = end_4.substring(0, end_4.length() - 2);
                if (android.text.TextUtils.isEmpty(tmp_e4)) {
                    tmp_e4 = end_4;
                    TextUtils.getInstance().setAnimationTextView(tvLotoDuoi4);
                } else {
                    if (tmp_e4.length() < end_4.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLotoDuoi4);
                        tmp_e4 = end_4;
                    }
                }
            }
            tvLotoDuoi4.setText(Html.fromHtml(end_4));
        }

        if (end_with.getE5() != null) {
            String end_5 = "";
            for (int i = 0; i < end_with.getE5().size(); i++) {
                end_5 += end_with.getE5().get(i) + " - ";
            }
            if (end_5.length() > 0) {
                end_5 = end_5.substring(0, end_5.length() - 2);
                if (android.text.TextUtils.isEmpty(tmp_e5)) {
                    tmp_e5 = end_5;
                    TextUtils.getInstance().setAnimationTextView(tvLotoDuoi5);
                } else {
                    if (tmp_e5.length() < end_5.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLotoDuoi5);
                        tmp_e5 = end_5;
                    }
                }
            }
            tvLotoDuoi5.setText(Html.fromHtml(end_5));
        }

        if (end_with.getE6() != null) {
            String end_6 = "";
            for (int i = 0; i < end_with.getE6().size(); i++) {
                end_6 += end_with.getE6().get(i) + " - ";
            }
            if (end_6.length() > 0) {
                end_6 = end_6.substring(0, end_6.length() - 2);
                if (android.text.TextUtils.isEmpty(tmp_e6)) {
                    tmp_e6 = end_6;
                    TextUtils.getInstance().setAnimationTextView(tvLotoDuoi6);
                } else {
                    if (tmp_e6.length() < end_6.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLotoDuoi6);
                        tmp_e6 = end_6;
                    }
                }
            }
            tvLotoDuoi6.setText(Html.fromHtml(end_6));
        }

        if (end_with.getE7() != null) {
            String end_7 = "";
            for (int i = 0; i < end_with.getE7().size(); i++) {
                end_7 += end_with.getE7().get(i) + " - ";
            }

            if (end_7.length() > 0) {
                end_7 = end_7.substring(0, end_7.length() - 2);
                if (android.text.TextUtils.isEmpty(tmp_e7)) {
                    tmp_e7 = end_7;
                    TextUtils.getInstance().setAnimationTextView(tvLotoDuoi7);
                } else {
                    if (tmp_e7.length() < end_7.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLotoDuoi7);
                        tmp_e7 = end_7;
                    }
                }
            }
            tvLotoDuoi7.setText(Html.fromHtml(end_7));
        }

        if (end_with.getE8() != null) {
            String end_8 = "";
            for (int i = 0; i < end_with.getE8().size(); i++) {
                end_8 += end_with.getE8().get(i) + " - ";
            }
            if (end_8.length() > 0) {
                end_8 = end_8.substring(0, end_8.length() - 2);
                if (android.text.TextUtils.isEmpty(tmp_e8)) {
                    tmp_e8 = end_8;
                    TextUtils.getInstance().setAnimationTextView(tvLotoDuoi8);
                } else {
                    if (tmp_e8.length() < end_8.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLotoDuoi8);
                        tmp_e8 = end_8;
                    }
                }
            }
            tvLotoDuoi8.setText(Html.fromHtml(end_8));
        }

        if (end_with.getE9() != null) {
            String end_9 = "";
            for (int i = 0; i < end_with.getE9().size(); i++) {
                end_9 += end_with.getE9().get(i) + " - ";
            }
            if (end_9.length() > 0) {
                end_9 = end_9.substring(0, end_9.length() - 2);
                if (android.text.TextUtils.isEmpty(tmp_e9)) {
                    tmp_e9 = end_9;
                    TextUtils.getInstance().setAnimationTextView(tvLotoDuoi9);
                } else {
                    if (tmp_e9.length() < end_9.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLotoDuoi9);
                        tmp_e9 = end_9;
                    }
                }
            }
            tvLotoDuoi9.setText(Html.fromHtml(end_9));
        }
    }

    @Override
    public void setEndLive() {
        presenter.checkSocket();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setEndLive(1);
        SharedUtils.getInstance().putBooleanValue(Constants.CHECK_DONE_N, true);
        tv_title_region.setText("Kết quả xổ số Truyền thống");
    }

    private void initRoller() {
        if (toDay) {
            if (special == null) {
                special = new Roller(tvSpecial, 100000, 100, 99999, 10000);
            }

            if (first == null) {
                first = new Roller(tvFirst, 100000, 100, 99999, 10000);
            }

            if (r21 == null)
                r21 = new Roller(tv_21, 100000, 100, 99999, 10000);

            if (r22 == null)
                r22 = new Roller(tv_22, 100000, 100, 99999, 10000);

            if (r31 == null)
                r31 = new Roller(tv_31, 100000, 100, 99999, 10000);

            if (r32 == null)
                r32 = new Roller(tv_32, 100000, 100, 99999, 10000);

            if (r33 == null)
                r33 = new Roller(tv_33, 100000, 100, 99999, 10000);

            if (r34 == null)
                r34 = new Roller(tv_34, 100000, 100, 99999, 10000);

            if (r35 == null)
                r35 = new Roller(tv_35, 100000, 100, 99999, 10000);

            if (r36 == null)
                r36 = new Roller(tv_36, 100000, 100, 99999, 10000);

            if (r41 == null)
                r41 = new Roller(tv_41, 100000, 100, 9999, 1000);

            if (r42 == null)
                r42 = new Roller(tv_42, 100000, 100, 9999, 1000);

            if (r43 == null)
                r43 = new Roller(tv_43, 100000, 100, 9999, 1000);

            if (r44 == null)
                r44 = new Roller(tv_44, 100000, 100, 9999, 1000);

            if (r51 == null)
                r51 = new Roller(tv_51, 100000, 100, 9999, 1000);

            if (r52 == null)
                r52 = new Roller(tv_52, 100000, 100, 9999, 1000);

            if (r53 == null)
                r53 = new Roller(tv_53, 100000, 100, 9999, 1000);

            if (r54 == null)
                r54 = new Roller(tv_54, 100000, 100, 9999, 1000);

            if (r55 == null)
                r55 = new Roller(tv_55, 100000, 100, 9999, 1000);

            if (r56 == null)
                r56 = new Roller(tv_56, 100000, 100, 9999, 1000);

            if (r61 == null)
                r61 = new Roller(tv_61, 100000, 100, 999, 100);

            if (r62 == null)
                r62 = new Roller(tv_62, 100000, 100, 999, 100);

            if (r63 == null)
                r63 = new Roller(tv_63, 100000, 100, 999, 100);

            if (r71 == null)
                r71 = new Roller(tv_71, 100000, 100, 99, 10);

            if (r72 == null)
                r72 = new Roller(tv_72, 100000, 100, 99, 10);

            if (r73 == null)
                r73 = new Roller(tv_73, 100000, 100, 99, 10);

            if (r74 == null)
                r74 = new Roller(tv_74, 100000, 100, 99, 10);
        }
    }

    @SuppressLint("NewApi")
    private void checkRandomTable(List<String> res_special, List<String> first,
                                  List<String> second,
                                  List<String> third,
                                  List<String> fourd,
                                  List<String> five,
                                  List<String> six,
                                  List<String> seven) {

        if (toDay) {
            if (first != null) {
                int size = first.size();
                if (size > 0) {
                    if (first.get(0) != null && !first.get(0).isEmpty()) {
                        if (this.first.isRun())
                            this.first.shutdownThread(false);
                    }
                } else {
                    if (!this.first.isRun())
                        this.first.run();
                }
            }


            /**
             * Second*/
            if (second != null) {
                int size = second.size();
                if (size == 0) {
                    if (first != null && first.size() > 0) {
                        if (!r21.isRun())
                            r21.run();
                    }
                }
                if (size == 1) {
                    if (r21.isRun()) {
                        r21.shutdownThread(false);
                    }

                    if (!r22.isRun())
                        r22.run();
                }
                if (size == 2) {
                    if (r22.isRun()) {
                        r22.shutdownThread(false);
                    }
                }
            }


            /**
             * Third*/
            if (second != null && third != null && second.size() == 2) {
                switch (third.size()) {
                    case 0:
                        if (!r31.isRun())
                            r31.run();
                        break;
                    case 1:
                        if (r31.isRun()) {
                            r31.shutdownThread(false);
                        }
                        if (!r32.isRun())
                            r32.run();
                        break;
                    case 2:
                        if (r32.isRun()) {
                            r32.shutdownThread(false);
                        }
                        if (!r33.isRun())
                            r33.run();
                        break;
                    case 3:
                        if (r33.isRun()) {
                            r33.shutdownThread(false);
                        }
                        if (!r34.isRun())
                            r34.run();
                        break;
                    case 4:
                        if (r34.isRun()) {
                            r34.shutdownThread(false);
                        }
                        if (!r35.isRun())
                            r35.run();
                        break;
                    case 5:
                        if (r35.isRun()) {
                            r35.shutdownThread(false);
                        }
                        if (!r36.isRun())
                            r36.run();
                        break;
                    case 6:
                        if (r36.isRun()) {
                            r36.shutdownThread(false);
                        }
                        break;
                }
            }

            /**
             * Fourd*/

            if (third != null && fourd != null && third.size() == 6) {
                switch (fourd.size()) {
                    case 0:
                        if (!r41.isRun())
                            r41.run();
                        break;
                    case 1:
                        if (r41.isRun()) {
                            r41.shutdownThread(false);
                        }
                        if (!r42.isRun())
                            r42.run();
                        break;
                    case 2:
                        if (r42.isRun()) {
                            r42.shutdownThread(false);
                        }
                        if (!r43.isRun())
                            r43.run();
                        break;
                    case 3:
                        if (r43.isRun()) {
                            r43.shutdownThread(false);
                        }
                        if (!r44.isRun())
                            r44.run();
                        break;
                    case 4:
                        if (r44.isRun()) {
                            r44.shutdownThread(false);
                        }
                        break;
                }
            }

            /**
             * Five*/
            if (fourd != null && five != null && fourd.size() == 4) {
                int size = five.size();
                switch (size) {
                    case 0:
                        if (!r51.isRun())
                            r51.run();
                        break;
                    case 1:
                        if (r51.isRun()) {
                            r51.shutdownThread(false);
                        }
                        if (!r52.isRun())
                            r52.run();
                        break;
                    case 2:
                        if (r52.isRun()) {
                            r52.shutdownThread(false);
                        }
                        if (!r53.isRun())
                            r53.run();
                        break;
                    case 3:
                        if (r53.isRun()) {
                            r53.shutdownThread(false);
                        }
                        if (!r54.isRun())
                            r54.run();
                        break;
                    case 4:
                        if (r54.isRun()) {
                            r54.shutdownThread(false);
                        }
                        if (!r55.isRun())
                            r55.run();
                        break;
                    case 5:
                        if (r55.isRun()) {
                            r55.shutdownThread(false);
                        }
                        if (!r56.isRun())
                            r56.run();
                        break;
                    case 6:
                        if (r56.isRun()) {
                            r56.shutdownThread(false);
                        }
                        break;
                }
            }
            /**
             * Six*/
            if (five != null && six != null && five.size() == 6) {
                switch (six.size()) {
                    case 0:
                        if (!r61.isRun())
                            r61.run();
                        break;
                    case 1:
                        if (r61.isRun()) {
                            r61.shutdownThread(false);
                        }
                        if (!r62.isRun())
                            r62.run();
                        break;
                    case 2:
                        if (r62.isRun()) {
                            r62.shutdownThread(false);
                        }
                        if (!r63.isRun())
                            r63.run();
                        break;
                    case 3:
                        if (r63.isRun()) {
                            r63.shutdownThread(false);
                        }
                        break;
                }
            }

            /**
             * Sevent*/
            if (six != null && seven != null && six.size() == 3) {

                switch (seven.size()) {
                    case 0:
                        if (!r71.isRun())
                            r71.run();
                        break;
                    case 1:
                        if (r71.isRun()) {
                            r71.shutdownThread(false);
                        }
                        if (!r72.isRun())
                            r72.run();
                        break;
                    case 2:
                        if (r72.isRun()) {
                            r72.shutdownThread(false);
                        }
                        if (!r73.isRun())
                            r73.run();
                        break;
                    case 3:
                        if (r73.isRun()) {
                            r73.shutdownThread(false);
                        }
                        if (!r74.isRun())
                            r74.run();
                        break;
                    case 4:
                        if (r74.isRun()) {
                            r74.shutdownThread(false);
                        }
                        break;
                }
            }
        }

        if (seven != null && res_special != null && seven.size() == 4) {
            switch (res_special.size()) {
                case 0:
                    if (!this.special.isRun())
                        this.special.run();
                    break;
                case 1:
                    if (this.special.isRun()) {
                        this.special.shutdownThread(true);
                    }
                    break;
            }
        }
    }

    @Override
    public void setDataSocket(CurentResult data) {
        loadingView.setVisibility(View.GONE);
        tvContent.setVisibility(View.GONE);
        checkRandomTable(data.getRes_special(), data.getRes_first(),
                data.getRes_second(),
                data.getRes_third(),
                data.getRes_fourth(),
                data.getRes_fifth(),
                data.getRes_sixth(),
                data.getRes_seventh());
        setResultLottery(
                data.getRes_special(),
                data.getRes_first(),
                data.getRes_second(),
                data.getRes_third(),
                data.getRes_fourth(),
                data.getRes_fifth(),
                data.getRes_sixth(),
                data.getRes_seventh());
        List<String> loto_list = new ArrayList<>();
        loto_list.addAll(data.getLoto());
        adapterLoto.refreshLotoList(loto_list);
        if (data.getBegin_with() != null) {
            setBeginResult(data.getBegin_with());
        }
        if (data.getEnd_with() != null) {
            setEndResult(data.getEnd_with());
        }
    }

    @Override
    public void setNewResult(RESP_NewResult newResult) {
        AudioPlay.getInstance().play(getContext());
        configRoller(newResult);
    }

    private void configRoller(RESP_NewResult newResult) {
        switch (newResult.getResult_name()) {
            case "res_first":
                if (newResult.getValue() != null) {
                    if (first != null) {
                        if (first.isRun())
                            first.shutdownThread(false);
                    }
                    if (!r21.isRun())
                        r21.run();
                    tvFirst.setText(newResult.getValue());
                }
                break;
            case "res_second":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (r21 != null) {
                                if (r21.isRun())
                                    r21.shutdownThread(false);
                            }
                            if (!r22.isRun())
                                r22.run();
                            tv_21.setText(newResult.getValue());
                            break;
                        case "1":
                            if (r22 != null) {
                                if (r22.isRun())
                                    r22.shutdownThread(false);
                            }
                            tv_22.setText(newResult.getValue());
                            if (!r31.isRun())
                                r31.run();
                            break;
                    }
                }
                break;
            case "res_third":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (r31 != null) {
                                if (r31.isRun())
                                    r31.shutdownThread(false);
                            }
                            if (!r32.isRun())
                                r32.run();
                            tv_31.setText(newResult.getValue());
                            break;
                        case "1":
                            if (r32 != null) {
                                if (r32.isRun())
                                    r32.shutdownThread(false);
                            }
                            if (!r33.isRun())
                                r33.run();
                            tv_32.setText(newResult.getValue());
                            break;
                        case "2":
                            if (r33 != null) {
                                if (r33.isRun())
                                    r33.shutdownThread(false);
                            }
                            if (!r34.isRun())
                                r34.run();
                            tv_33.setText(newResult.getValue());
                            break;
                        case "3":
                            if (r34 != null) {
                                if (r34.isRun())
                                    r34.shutdownThread(false);
                            }
                            if (!r35.isRun())
                                r35.run();
                            tv_34.setText(newResult.getValue());
                            break;
                        case "4":
                            if (r35 != null) {
                                if (r35.isRun())
                                    r35.shutdownThread(false);
                            }
                            if (!r36.isRun())
                                r36.run();
                            tv_35.setText(newResult.getValue());
                            break;
                        case "5":
                            if (r36 != null) {
                                if (r36.isRun())
                                    r36.shutdownThread(false);
                            }
                            if (!r41.isRun())
                                r41.run();
                            tv_36.setText(newResult.getValue());
                            break;
                    }
                }
                break;
            case "res_fourth":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (r41 != null) {
                                if (r41.isRun())
                                    r41.shutdownThread(false);
                            }
                            if (!r42.isRun())
                                r42.run();
                            tv_41.setText(newResult.getValue());
                            break;
                        case "1":
                            if (r42 != null) {
                                if (r42.isRun())
                                    r42.shutdownThread(false);
                            }
                            if (!r43.isRun())
                                r43.run();
                            tv_42.setText(newResult.getValue());
                            break;
                        case "2":
                            if (r43 != null) {
                                if (r43.isRun())
                                    r43.shutdownThread(false);
                            }
                            if (!r44.isRun())
                                r44.run();
                            tv_43.setText(newResult.getValue());
                            break;
                        case "3":
                            if (r44 != null) {
                                if (r44.isRun())
                                    r44.shutdownThread(false);
                            }
                            if (!r51.isRun())
                                r51.run();
                            tv_44.setText(newResult.getValue());
                            break;
                    }
                }
                break;
            case "res_fifth":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (r51 != null) {
                                if (r51.isRun())
                                    r51.shutdownThread(false);
                            }
                            if (!r52.isRun())
                                r52.run();
                            tv_51.setText(newResult.getValue());
                            break;
                        case "1":
                            if (r52 != null) {
                                if (r52.isRun())
                                    r52.shutdownThread(false);
                            }
                            if (!r53.isRun())
                                r53.run();
                            tv_52.setText(newResult.getValue());
                            break;
                        case "2":
                            if (r53 != null) {
                                if (r53.isRun())
                                    r53.shutdownThread(false);
                            }
                            if (!r54.isRun())
                                r54.run();
                            tv_53.setText(newResult.getValue());
                            break;
                        case "3":
                            if (r54 != null) {
                                if (r54.isRun())
                                    r54.shutdownThread(false);
                            }
                            if (!r55.isRun())
                                r55.run();
                            tv_54.setText(newResult.getValue());
                            break;
                        case "4":
                            if (r55 != null) {
                                if (r55.isRun())
                                    r55.shutdownThread(false);
                            }
                            if (!r56.isRun())
                                r56.run();
                            tv_55.setText(newResult.getValue());
                            break;
                        case "5":
                            if (r56 != null) {
                                if (r56.isRun())
                                    r56.shutdownThread(false);
                            }
                            if (!r61.isRun())
                                r61.run();
                            tv_56.setText(newResult.getValue());
                            break;
                    }
                }
                break;
            case "res_sixth":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (r61 != null) {
                                if (r61.isRun())
                                    r61.shutdownThread(false);
                            }
                            if (!r62.isRun())
                                r62.run();
                            tv_61.setText(newResult.getValue());
                            break;
                        case "1":
                            if (r62 != null) {
                                if (r62.isRun())
                                    r62.shutdownThread(false);
                            }
                            if (!r63.isRun())
                                r63.run();
                            tv_62.setText(newResult.getValue());
                            break;
                        case "2":
                            if (r63 != null) {
                                if (r63.isRun())
                                    r63.shutdownThread(false);
                            }
                            if (!r71.isRun())
                                r71.run();
                            tv_63.setText(newResult.getValue());
                            break;
                    }
                }
                break;
            case "res_seventh":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (r71 != null) {
                                if (r71.isRun())
                                    r71.shutdownThread(false);
                            }
                            tv_71.setText(newResult.getValue());
                            if (!r72.isRun())
                                r72.run();
                            break;
                        case "1":
                            if (r72 != null) {
                                if (r72.isRun())
                                    r72.shutdownThread(false);
                            }
                            if (!r73.isRun())
                                r73.run();
                            tv_72.setText(newResult.getValue());
                            break;
                        case "2":
                            if (r73 != null) {
                                if (r73.isRun())
                                    r73.shutdownThread(false);
                            }
                            if (!r74.isRun())
                                r74.run();
                            tv_73.setText(newResult.getValue());
                            break;
                        case "3":
                            if (r74 != null) {
                                if (r74.isRun())
                                    r74.shutdownThread(false);
                            }
                            if (!special.isRun())
                                special.run();
                            tv_74.setText(newResult.getValue());
                            break;
                    }
                }
                break;
            case "res_special":
                if (newResult.getValue() != null) {
                    if (special != null) {
                        if (special.isRun())
                            special.shutdownThread(true);
                    }
                    LotoSpecial = newResult.getValue().substring(3);
                    tvSpecial.setText(newResult.getValue());
                }
                break;
        }
    }

    private void sortList(List<String> loto_list) {
        Collections.sort(loto_list);
        adapterLoto.refreshLotoList(loto_list);
    }

    @Override
    public void getResultLotterySuccess(ResultLottery data) {
        loadingView.setVisibility(View.GONE);
        tvContent.setVisibility(View.GONE);
        setResultLottery(
                data.getRes_special(),
                data.getRes_first(),
                data.getRes_second(),
                data.getRes_third(),
                data.getRes_fourth(),
                data.getRes_fifth(),
                data.getRes_sixth(),
                data.getRes_seventh());
        adapterLoto.refreshLotoList(data.getLoto());
        if (data.getBegin_with() != null) {
            setBeginResult(data.getBegin_with());
        }

        if (data.getEnd_with() != null) {
            setEndResult(data.getEnd_with());
        }
    }

    private synchronized void setBeginResult(BeginResult beginResult) {
        isExistsBegin = false;
        /**
         * Dau loto*/
        if (beginResult.getB0().size() > 0) {
            synchronized (beginResult.getB0()) {
                String begin_0 = "";
                for (int i = 0; i < beginResult.getB0().size(); i++) {
                    if (checkExitstInBegin(beginResult.getB0().get(i))) {
                        begin_0 += "<font color='red'>" + beginResult.getB0().get(i) + "</font>" + " - ";
                    } else {
                        begin_0 += beginResult.getB0().get(i) + " - ";
                    }
                }
                if (begin_0.length() > 0) {
                    begin_0 = begin_0.substring(0, begin_0.length() - 3);
                }
                Log.e(TAG, "setBeginEndLoto: 0 - " + begin_0);
                tvLoto0.setText(Html.fromHtml(begin_0));
            }
        }

        if (beginResult.getB1().size() > 0) {
            synchronized (beginResult.getB1()) {
                String begin_1 = "";
                for (int i = 0; i < beginResult.getB1().size(); i++) {
                    if (checkExitstInBegin(beginResult.getB1().get(i))) {
                        begin_1 += "<font color='red'>" + beginResult.getB1().get(i) + "</font>" + " - ";
                    } else {
                        begin_1 += beginResult.getB1().get(i) + " - ";
                    }
                }
                if (begin_1.length() > 0) {
                    begin_1 = begin_1.substring(0, begin_1.length() - 3);
                }
                Log.e(TAG, "setBeginEndLoto: 1 - " + begin_1);
                tvLoto1.setText(Html.fromHtml(begin_1));
            }
        }

        if (beginResult.getB2().size() > 0) {
            synchronized (beginResult.getB2()) {
                String begin_2 = "";
                for (int i = 0; i < beginResult.getB2().size(); i++) {
                    if (checkExitstInBegin(beginResult.getB2().get(i))) {
                        begin_2 += "<font color='red'>" + beginResult.getB2().get(i) + "</font>" + " - ";
                    } else {
                        begin_2 += beginResult.getB2().get(i) + " - ";
                    }
                }
                if (begin_2.length() > 0) {
                    begin_2 = begin_2.substring(0, begin_2.length() - 3);
                }
                Log.e(TAG, "setBeginEndLoto: 2 - " + begin_2);
                tvLoto2.setText(Html.fromHtml(begin_2));
            }
        }

        if (beginResult.getB3().size() > 0) {
            synchronized (beginResult.getB3()) {
                String begin_3 = "";
                for (int i = 0; i < beginResult.getB3().size(); i++) {
                    if (checkExitstInBegin(beginResult.getB3().get(i))) {
                        begin_3 += "<font color='red'>" + beginResult.getB3().get(i) + "</font>" + " - ";
                    } else {
                        begin_3 += beginResult.getB3().get(i) + " - ";
                    }
                }
                if (begin_3.length() > 0) {
                    begin_3 = begin_3.substring(0, begin_3.length() - 3);
                }
                Log.e(TAG, "setBeginEndLoto: 3 - " + begin_3);
//                b3 = begin_3;
                tvLoto3.setText(Html.fromHtml(begin_3));
            }
        }

        if (beginResult.getB4().size() > 0) {
            synchronized (beginResult.getB4()) {
                String begin_4 = "";
                for (int i = 0; i < beginResult.getB4().size(); i++) {
                    if (checkExitstInBegin(beginResult.getB4().get(i))) {
                        begin_4 += "<font color='red'>" + beginResult.getB4().get(i) + "</font>" + " - ";
                    } else {
                        begin_4 += beginResult.getB4().get(i) + " - ";
                    }
                }
                if (begin_4.length() > 0) {
                    begin_4 = begin_4.substring(0, begin_4.length() - 3);
                }
//                b4 = begin_4;
                tvLoto4.setText(Html.fromHtml(begin_4));
            }
        }


        if (beginResult.getB5().size() > 0) {
            synchronized (beginResult.getB5()) {
                String begin_5 = "";
                for (int i = 0; i < beginResult.getB5().size(); i++) {
                    if (checkExitstInBegin(beginResult.getB5().get(i))) {
                        begin_5 += "<font color='red'>" + beginResult.getB5().get(i) + "</font>" + " - ";
                    } else {
                        begin_5 += beginResult.getB5().get(i) + " - ";
                    }
                }
                if (begin_5.length() > 0) {
                    begin_5 = begin_5.substring(0, begin_5.length() - 3);
                }
//                b5 = begin_5;
                tvLoto5.setText(Html.fromHtml(begin_5));
            }

        }

        if (beginResult.getB6().size() > 0) {
            synchronized (beginResult.getB6()) {
                String begin_6 = "";
                for (int i = 0; i < beginResult.getB6().size(); i++) {
                    if (checkExitstInBegin(beginResult.getB6().get(i))) {
                        begin_6 += "<font color='red'>" + beginResult.getB6().get(i) + "</font>" + " - ";
                    } else {
                        begin_6 += beginResult.getB6().get(i) + " - ";
                    }
                }
                if (begin_6.length() > 0) {
                    begin_6 = begin_6.substring(0, begin_6.length() - 3);
                }
//                b6 = begin_6;
                tvLoto6.setText(Html.fromHtml(begin_6));
            }

        }

        if (beginResult.getB7().size() > 0) {
            synchronized (beginResult.getB7()) {
                String begin_7 = "";
                for (int i = 0; i < beginResult.getB7().size(); i++) {
                    if (checkExitstInBegin(beginResult.getB7().get(i))) {
                        begin_7 += "<font color='red'>" + beginResult.getB7().get(i) + "</font>" + " - ";
                    } else {
                        begin_7 += beginResult.getB7().get(i) + " - ";
                    }
                }
                if (begin_7.length() > 0) {
                    begin_7 = begin_7.substring(0, begin_7.length() - 3);
                }

//                b7 = begin_7;
//
                tvLoto7.setText(Html.fromHtml(begin_7));
            }
        }

        if (beginResult.getB8().size() > 0) {
            synchronized (beginResult.getB8()) {
                String begin_8 = "";
                for (int i = 0; i < beginResult.getB8().size(); i++) {
                    if (checkExitstInBegin(beginResult.getB8().get(i))) {
                        begin_8 += "<font color='red'>" + beginResult.getB8().get(i) + "</font>" + " - ";
                    } else {
                        begin_8 += beginResult.getB8().get(i) + " - ";
                    }
                }
                if (begin_8.length() > 0) {
                    begin_8 = begin_8.substring(0, begin_8.length() - 3);
                }
//                b8 = begin_8;
                tvLoto8.setText(Html.fromHtml(begin_8));
            }
        }

        if (beginResult.getB9().size() > 0) {
            synchronized (beginResult.getB9()) {
                String begin_9 = "";
                for (int i = 0; i < beginResult.getB9().size(); i++) {
                    if (checkExitstInBegin(beginResult.getB9().get(i))) {
                        begin_9 += "<font color='red'>" + beginResult.getB9().get(i) + "</font>" + " - ";
                    } else {
                        begin_9 += beginResult.getB9().get(i) + " - ";
                    }
                }
                if (begin_9.length() > 0) {
                    begin_9 = begin_9.substring(0, begin_9.length() - 3);
                }
//                b9 = begin_9;
                tvLoto9.setText(Html.fromHtml(begin_9));
            }
        }
    }

    private synchronized void setEndResult(EndResult end_with) {
        isExistsEnd = false;
        /**
         * Duoi loto*/
        if (end_with.getE0() != null) {
            String end_0 = "";
            for (int i = 0; i < end_with.getE0().size(); i++) {
                if (checkExitstInEnd(end_with.getE0().get(i))) {
                    end_0 += "<font color='red'>" + end_with.getE0().get(i) + "</font>" + " - ";
                } else {
                    end_0 += end_with.getE0().get(i) + " - ";
                }
            }
            if (end_0.length() > 0) {
                end_0 = end_0.substring(0, end_0.length() - 2);
            }
            tvLotoDuoi0.setText(Html.fromHtml(end_0));
        }

        if (end_with.getE1() != null) {
            String end_1 = "";
            for (int i = 0; i < end_with.getE1().size(); i++) {
                if (checkExitstInEnd(end_with.getE1().get(i))) {
                    end_1 += "<font color='red'>" + end_with.getE1().get(i) + "</font>" + " - ";
                } else {
                    end_1 += end_with.getE1().get(i) + " - ";
                }
            }
            if (end_1.length() > 0) {
                end_1 = end_1.substring(0, end_1.length() - 2);
            }

            tvLotoDuoi1.setText(Html.fromHtml(end_1));
        }

        if (end_with.getE2() != null) {
            String end_2 = "";
            for (int i = 0; i < end_with.getE2().size(); i++) {
                if (checkExitstInEnd(end_with.getE2().get(i))) {
                    end_2 += "<font color='red'>" + end_with.getE2().get(i) + "</font>" + " - ";
                } else {
                    end_2 += end_with.getE2().get(i) + " - ";
                }
            }
            if (end_2.length() > 0) {
                end_2 = end_2.substring(0, end_2.length() - 2);
            }
            tvLotoDuoi2.setText(Html.fromHtml(end_2));
        }

        if (end_with.getE3() != null) {
            String end_3 = "";
            for (int i = 0; i < end_with.getE3().size(); i++) {
                if (checkExitstInEnd(end_with.getE3().get(i))) {
                    end_3 += "<font color='red'>" + end_with.getE3().get(i) + "</font>" + " - ";
                } else {
                    end_3 += end_with.getE3().get(i) + " - ";
                }
            }
            if (end_3.length() > 0) {
                end_3 = end_3.substring(0, end_3.length() - 2);
            }
            tvLotoDuoi3.setText(Html.fromHtml(end_3));
        }

        if (end_with.getE4() != null) {
            String end_4 = "";
            for (int i = 0; i < end_with.getE4().size(); i++) {
                if (checkExitstInEnd(end_with.getE4().get(i))) {
                    end_4 += "<font color='red'>" + end_with.getE4().get(i) + "</font>" + " - ";
                } else {
                    end_4 += end_with.getE4().get(i) + " - ";
                }
            }
            if (end_4.length() > 0) {
                end_4 = end_4.substring(0, end_4.length() - 2);
            }
            tvLotoDuoi4.setText(Html.fromHtml(end_4));
        }

        if (end_with.getE5() != null) {
            String end_5 = "";
            for (int i = 0; i < end_with.getE5().size(); i++) {
                if (checkExitstInEnd(end_with.getE5().get(i))) {
                    end_5 += "<font color='red'>" + end_with.getE5().get(i) + "</font>" + " - ";
                } else {
                    end_5 += end_with.getE5().get(i) + " - ";
                }
            }
            if (end_5.length() > 0) {
                end_5 = end_5.substring(0, end_5.length() - 2);
            }
            tvLotoDuoi5.setText(Html.fromHtml(end_5));
        }

        if (end_with.getE6() != null) {
            String end_6 = "";
            for (int i = 0; i < end_with.getE6().size(); i++) {
                if (checkExitstInEnd(end_with.getE6().get(i))) {
                    end_6 += "<font color='red'>" + end_with.getE6().get(i) + "</font>" + " - ";
                } else {
                    end_6 += end_with.getE6().get(i) + " - ";
                }
            }
            if (end_6.length() > 0) {
                end_6 = end_6.substring(0, end_6.length() - 2);
            }
            tvLotoDuoi6.setText(Html.fromHtml(end_6));
        }

        if (end_with.getE7() != null) {
            String end_7 = "";
            for (int i = 0; i < end_with.getE7().size(); i++) {
                if (checkExitstInEnd(end_with.getE7().get(i))) {
                    end_7 += "<font color='red'>" + end_with.getE7().get(i) + "</font>" + " - ";
                } else {
                    end_7 += end_with.getE7().get(i) + " - ";
                }
            }

            if (end_7.length() > 0) {
                end_7 = end_7.substring(0, end_7.length() - 2);
            }
            tvLotoDuoi7.setText(Html.fromHtml(end_7));
        }

        if (end_with.getE8() != null) {
            String end_8 = "";
            for (int i = 0; i < end_with.getE8().size(); i++) {
                if (checkExitstInEnd(end_with.getE8().get(i))) {
                    end_8 += "<font color='red'>" + end_with.getE8().get(i) + "</font>" + " - ";
                } else {
                    end_8 += end_with.getE8().get(i) + " - ";
                }
            }
            if (end_8.length() > 0) {
                end_8 = end_8.substring(0, end_8.length() - 2);
            }
            tvLotoDuoi8.setText(Html.fromHtml(end_8));
        }

        if (end_with.getE9() != null) {
            String end_9 = "";
            for (int i = 0; i < end_with.getE9().size(); i++) {
                if (checkExitstInEnd(end_with.getE9().get(i))) {
                    end_9 += "<font color='red'>" + end_with.getE9().get(i) + "</font>" + " - ";
                } else {
                    end_9 += end_with.getE9().get(i) + " - ";
                }
            }
            if (end_9.length() > 0) {
                end_9 = end_9.substring(0, end_9.length() - 2);
            }
            tvLotoDuoi9.setText(Html.fromHtml(end_9));
        }
        loadingView.setVisibility(View.GONE);
        tvContent.setVisibility(View.GONE);
    }

    /**
     * Check Begin - End
     */
    private boolean checkExitstInBegin(String s) {
        if (LotoSpecial != null) {
            if (s.equals(LotoSpecial) && !getExistsBegin()) {
                setExistsBegin(true);
                return true;
            } else {
                return false;
            }
        } else {
            setExistsBegin(false);
            return false;
        }
    }

    private boolean checkExitstInEnd(String s) {
        if (LotoSpecial != null) {
            if (s.equals(LotoSpecial) && !getExistsEnd()) {
                setExistsEnd(true);
                return true;
            } else {
                return false;
            }
        } else {
            setExistsEnd(false);
            return false;
        }
    }

    /**
     * Set Begin
     */
    private boolean getExistsBegin() {
        return isExistsBegin;
    }

    private void setExistsBegin(boolean isExists) {
        this.isExistsBegin = isExists;
    }

    /**
     * Set - End
     */
    @Contract(pure = true)
    private boolean getExistsEnd() {
        return isExistsEnd;
    }

    private void setExistsEnd(boolean isExists) {
        this.isExistsEnd = isExists;
    }


    @Override
    public void getResultLotteryError(String error) {
        loadingView.setVisibility(View.GONE);
        tvContent.setText(error);
        tvContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isLive) {
            isLive = false;
            presenter.socketConnect(toDay);
        }

        sound = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.SOUND_FLAG);
        if (!sound) {
            if (audioManager != null) {
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            }
            if (img_mute != null) {
                img_mute.setImageResource(R.mipmap.ic_mute_on);
            }
        } else {
            if (img_mute != null) {
                img_mute.setImageResource(R.mipmap.ic_mute);
            }
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (toDay) {
            presenter.disconnectSocket();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (toDay) {
            presenter.disconnectSocket();
        }
    }

    private void setResultLottery(List<String> special,
                                  List<String> first,
                                  List<String> second,
                                  List<String> third,
                                  List<String> fourd,
                                  List<String> five,
                                  List<String> six,
                                  List<String> seven) {
        if (first.size() > 0) {
            tvFirst.setText(first.get(0));
        }

        if (second != null) {
            switch (second.size()) {
                case 1:
                    tv_21.setText(second.get(0));
                    break;
                case 2:
                    tv_21.setText(second.get(0));
                    tv_22.setText(second.get(1));
                    break;
            }
        }

        if (third.size() > 0) {
            switch (third.size()) {
                case 1:
                    tv_31.setText(third.get(0));
                    break;
                case 2:
                    tv_31.setText(third.get(0));
                    tv_32.setText(third.get(1));
                    break;
                case 3:
                    tv_31.setText(third.get(0));
                    tv_32.setText(third.get(1));
                    tv_33.setText(third.get(2));
                    break;
                case 4:
                    tv_31.setText(third.get(0));
                    tv_32.setText(third.get(1));
                    tv_33.setText(third.get(2));
                    tv_34.setText(third.get(3));
                    break;
                case 5:
                    tv_31.setText(third.get(0));
                    tv_32.setText(third.get(1));
                    tv_33.setText(third.get(2));
                    tv_34.setText(third.get(3));
                    tv_35.setText(third.get(4));
                    break;
                case 6:
                    tv_31.setText(third.get(0));
                    tv_32.setText(third.get(1));
                    tv_33.setText(third.get(2));
                    tv_34.setText(third.get(3));
                    tv_35.setText(third.get(4));
                    tv_36.setText(third.get(5));
                    break;
            }
        }

        if (fourd.size() > 0) {
            switch (fourd.size()) {
                case 1:
                    tv_41.setText(fourd.get(0));
                    break;
                case 2:
                    tv_41.setText(fourd.get(0));
                    tv_42.setText(fourd.get(1));
                    break;
                case 3:
                    tv_41.setText(fourd.get(0));
                    tv_42.setText(fourd.get(1));
                    tv_43.setText(fourd.get(2));
                    break;
                case 4:
                    tv_41.setText(fourd.get(0));
                    tv_42.setText(fourd.get(1));
                    tv_43.setText(fourd.get(2));
                    tv_44.setText(fourd.get(3));
                    break;
            }

        }

        if (five.size() > 0) {
            switch (five.size()) {
                case 1:
                    tv_51.setText(five.get(0));
                    break;
                case 2:
                    tv_51.setText(five.get(0));
                    tv_52.setText(five.get(1));
                    break;
                case 3:
                    tv_51.setText(five.get(0));
                    tv_52.setText(five.get(1));
                    tv_53.setText(five.get(2));
                    break;
                case 4:
                    tv_51.setText(five.get(0));
                    tv_52.setText(five.get(1));
                    tv_53.setText(five.get(2));
                    tv_54.setText(five.get(3));
                    break;
                case 5:
                    tv_51.setText(five.get(0));
                    tv_52.setText(five.get(1));
                    tv_53.setText(five.get(2));
                    tv_54.setText(five.get(3));
                    tv_55.setText(five.get(4));
                    break;
                case 6:
                    tv_51.setText(five.get(0));
                    tv_52.setText(five.get(1));
                    tv_53.setText(five.get(2));
                    tv_54.setText(five.get(3));
                    tv_55.setText(five.get(4));
                    tv_56.setText(five.get(5));
                    break;
            }
        }


        if (six.size() > 0) {
            switch (six.size()) {
                case 1:
                    tv_61.setText(six.get(0));
                    break;
                case 2:
                    tv_61.setText(six.get(0));
                    tv_62.setText(six.get(1));
                    break;
                case 3:
                    tv_61.setText(six.get(0));
                    tv_62.setText(six.get(1));
                    tv_63.setText(six.get(2));
                    break;

            }

        }

        if (seven.size() > 0) {
            switch (seven.size()) {
                case 1:
                    tv_71.setText(seven.get(0));
                    break;
                case 2:
                    tv_71.setText(seven.get(0));
                    tv_72.setText(seven.get(1));
                    break;
                case 3:
                    tv_71.setText(seven.get(0));
                    tv_72.setText(seven.get(1));
                    tv_73.setText(seven.get(2));
                    break;
                case 4:
                    tv_71.setText(seven.get(0));
                    tv_72.setText(seven.get(1));
                    tv_73.setText(seven.get(2));
                    tv_74.setText(seven.get(3));
                    break;
            }
        }

        if (special.size() > 0) {
            LotoSpecial = special.get(0).substring(3);
            tvSpecial.setText(special.get(0));
        }
    }

    public void startLive() {
        if (presenter != null) {
            tv_not_yet.setVisibility(View.GONE);
            presenter.socketConnect(toDay);
        } else
            tv_not_yet.setVisibility(View.GONE);
        isLive = true;
    }

    private class Roller implements Runnable {
        TextView textRoll;
        private int numTimes;
        private long delayMillis;
        private int max, min;

        private boolean shutdown;
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
            running = false;
        }

        public boolean isRun() {
            return running;
        }
    }
}
