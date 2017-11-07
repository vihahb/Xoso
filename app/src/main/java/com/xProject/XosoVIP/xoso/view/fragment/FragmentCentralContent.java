package com.xProject.XosoVIP.xoso.view.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.xProject.XosoVIP.R;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.AudioPlay;
import com.xProject.XosoVIP.sdk.utils.CalendarUtils;
import com.xProject.XosoVIP.sdk.utils.SharedUtils;
import com.xProject.XosoVIP.sdk.utils.TextUtils;
import com.xProject.XosoVIP.sdk.utils.TimeUtils;
import com.xProject.XosoVIP.xoso.model.entity.BeginResult;
import com.xProject.XosoVIP.xoso.model.entity.ResultLottery;
import com.xProject.XosoVIP.xoso.model.respond.RESP_LiveLoto;
import com.xProject.XosoVIP.xoso.model.respond.RESP_NewResult;
import com.xProject.XosoVIP.xoso.model.respond.RESP_Result;
import com.xProject.XosoVIP.xoso.presenter.fragment.FragmentCentralContentPresenter;
import com.xProject.XosoVIP.xoso.view.activity.MainActivity;
import com.xProject.XosoVIP.xoso.view.fragment.inf.IFragmentCentralContent;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by vivhp on 9/12/2017.
 */

public class FragmentCentralContent extends BasicFragment implements IFragmentCentralContent {

    private static final String TAG = "FragmentCentralContent";
    private static final String KEY_DATE = "date";
    int count = 0;
    long millis;
    View view;
    private Context context;
    private FragmentCentralContentPresenter presenter;
    private String getDateTime;
    private TextView tvContent;
    private NestedScrollView scroll_central;
    private TableLayout table_1, table_2, table_3;
    private boolean vibrate = false, sound = true, check_done = false, spined = false;

    private String tmp_b0 = "", tmp_b1 = "", tmp_b2 = "", tmp_b3 = "", tmp_b4 = "", tmp_b5 = "", tmp_b6 = "", tmp_b7 = "", tmp_b8 = "", tmp_b9 = "";
    private String tmp1_b0 = "", tmp1_b1 = "", tmp1_b2 = "", tmp1_b3 = "", tmp1_b4 = "", tmp1_b5 = "", tmp1_b6 = "", tmp1_b7 = "", tmp1_b8 = "", tmp1_b9 = "";
    private String tmp2_b0 = "", tmp2_b1 = "", tmp2_b2 = "", tmp2_b3 = "", tmp2_b4 = "", tmp2_b5 = "", tmp2_b6 = "", tmp2_b7 = "", tmp2_b8 = "", tmp2_b9 = "";

    /**
     * Value table result lottery
     */
    private TextView tv_title, tv_not_yet;
    private ImageView img_mute;
    /**
     * Table 1
     */
    private TextView tvResgion_1, tv8_1, tv7_1,
            tv6_1, tv6_2, tv6_3,
            tv5_1, tv4_1, tv4_2, tv4_3, tv4_4, tv4_5, tv4_6, tv4_7,
            tv3_1, tv3_2,
            tv2_1, tv1_1, tvDb,
            tvLotoTitle1, tvLoto0_1, tvLoto1_1, tvLoto2_1, tvLoto3_1,
            tvLoto4_1, tvLoto5_1, tvLoto6_1, tvLoto7_1, tvLoto8_1, tvLoto9_1;
    /**
     * Table 2
     */
    private TextView tvResgion_2, tv8_2, tv7_2,
            tv6_1_2, tv6_2_2, tv6_3_2,
            tv5_2, tv4_1_2, tv4_2_2, tv4_3_2, tv4_4_2, tv4_5_2, tv4_6_2, tv4_7_2,
            tv3_1_2, tv3_2_2,
            tv2_2, tv1_2, tvDb_2,
            tvLotoTitle2, tvLoto0_2, tvLoto1_2, tvLoto2_2, tvLoto3_2,
            tvLoto4_2, tvLoto5_2, tvLoto6_2, tvLoto7_2, tvLoto8_2, tvLoto9_2;
    /**
     * Table 3
     */
    private TextView tvResgion_3, tv8_3, tv7_3,
            tv6_1_3, tv6_2_3, tv6_3_3,
            tv5_3, tv4_1_3, tv4_2_3, tv4_3_3, tv4_4_3, tv4_5_3, tv4_6_3, tv4_7_3,
            tv3_1_3, tv3_2_3,
            tv2_3, tv1_3, tvDb_3,
            tvLotoTitle3, tvLoto0_3, tvLoto1_3, tvLoto2_3, tvLoto3_3,
            tvLoto4_3, tvLoto5_3, tvLoto6_3, tvLoto7_3, tvLoto8_3, tvLoto9_3;
    /**
     * Roller Table 1
     */
    private Roller rl81, rl71, rl611, rl612, rl613, rl51,
            rl41, rl412, rl413, rl414, rl415, rl416, rl417,
            rl311, rl312, rl_second_1, rl_first_1, rl_special_1;
    /**
     * Roller Table 2
     */
    private Roller rl82, rl72, rl621, rl622, rl623, rl52,
            rl42, rl422, rl423, rl424, rl425, rl426, rl427,
            rl321, rl322, rl_second_2, rl_first_2, rl_special_2;
    /**
     * Roller Table 3
     */
    private Roller rl83, rl73, rl631, rl632, rl633, rl53,
            rl43, rl432, rl433, rl434, rl435, rl436, rl437,
            rl331, rl332, rl_second_3, rl_first_3, rl_special_3;
    private boolean toDay, mute = false;
    private AudioManager audioManager;
    private boolean isLive = false;
    //    private ViewStub viewStub;
    private boolean isExistsBegin, isExistsBegin_2, isExistsBegin_3;
    private String LotoSpecial_1, LotoSpecial_2, LotoSpecial_3;
    private LinearLayout loadingView;
    private TextView tv_title_region;

    public static FragmentCentralContent newInstance(long date) {
        FragmentCentralContent fragmentFirst = new FragmentCentralContent();
        Bundle args = new Bundle();
        args.putLong(KEY_DATE, date);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FragmentCentralContentPresenter(this);
        millis = getArguments().getLong(KEY_DATE);

        if (millis > 0) {
            getDateTime = TimeUtils.getFormattedDate(getActivity(), millis);
            String todays = TimeUtils.getToday();

            toDay = todays.trim().equals(getDateTime.trim());
//            Log.e(TAG, "onViewCreated: " + todays + " " + getDateTime);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_central_content, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        check_done = SharedUtils.getInstance().getBooleanValue(Constants.CHECK_DONE_C);
        vibrate = SharedUtils.getInstance().getBooleanValue(Constants.ViBRATE_FLAG);
        sound = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.SOUND_FLAG);
        loadingView = (LinearLayout) view.findViewById(R.id.loadingView);
        tv_title_region = (TextView) view.findViewById(R.id.tv_title_region);
        initView(view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (toDay && TimeUtils.checkTimeInMilisecondNorth(17, 10, 23, 59)) {
                    tv_not_yet.setVisibility(View.GONE);
                } else if (toDay && TimeUtils.checkTimeInMilisecondNorth(0, 0, 17, 9)) {
                    tv_not_yet.setVisibility(View.VISIBLE);
                } else {
                    tv_not_yet.setVisibility(View.GONE);
                }

                if (toDay && TimeUtils.checkTimeInMilisecondNorth(0, 0, 17, 40)){
                    tv_title_region.setText("Trực tiếp xổ số Miền Trung");
                } else {
                    tv_title_region.setText("Kết quả xổ số Miền Trung");
                }

                if (toDay && TimeUtils.checkTimeInMilisecondNorth(17, 10, 17, 45)) {
                    if (!check_done) {
                        tv_title_region.setText("Trực tiếp xổ số Miền Trung");
                        presenter.connectSocket();
                    } else {
                        presenter.getResultLottery(getDateTime);
                    }
                } else {
                    presenter.getResultLottery(getDateTime);
                }
            }
        }, 100);
    }

    private void initView(View view) {
        scroll_central = (NestedScrollView) view.findViewById(R.id.scroll_central_content);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        img_mute = (ImageView) view.findViewById(R.id.img_mute);
        tv_not_yet = findTextView(R.id.tv_not_yet);
        getTitle();
        setMute();
        initTable1(view);
        initTable2(view);
        initTable3(view);

        initRandomRolling();
    }

    public void startLive() {
        if (presenter != null) {
            presenter.connectSocket();
        } else
            isLive = true;
    }

    private void getTitle() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        String date_label = CalendarUtils.getDayName(calendar);
        if (date_label != null && !date_label.equals("")){
            tv_title.setText(date_label + ", ngày " + calendar.get(Calendar.DAY_OF_MONTH) + " tháng " + (calendar.get(Calendar.MONTH) + 1) + " năm " + calendar.get(Calendar.YEAR));
        } else {
            tv_title.setText("Ngày " + calendar.get(Calendar.DAY_OF_MONTH) + " tháng " + (calendar.get(Calendar.MONTH) + 1) + " năm " + calendar.get(Calendar.YEAR));
        }
    }

    private void setMute() {
        if (toDay) {
            img_mute.setVisibility(View.VISIBLE);
        } else {
            img_mute.setVisibility(View.GONE);
        }

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

    private void initTable1(View view) {
        table_1 = (TableLayout) view.findViewById(R.id.central_region_1);
        tvResgion_1 = (TextView) view.findViewById(R.id.tvRegionTitle1);
        tv8_1 = (TextView) view.findViewById(R.id.tv8_1);
        tv7_1 = (TextView) view.findViewById(R.id.tv7_1);
        tv6_1 = (TextView) view.findViewById(R.id.tv6_1);
        tv6_2 = (TextView) view.findViewById(R.id.tv6_2);
        tv6_3 = (TextView) view.findViewById(R.id.tv6_3);
        tv5_1 = (TextView) view.findViewById(R.id.tv5_1);
        tv4_1 = (TextView) view.findViewById(R.id.tv4_1);
        tv4_2 = (TextView) view.findViewById(R.id.tv4_2);
        tv4_3 = (TextView) view.findViewById(R.id.tv4_3);
        tv4_4 = (TextView) view.findViewById(R.id.tv4_4);
        tv4_5 = (TextView) view.findViewById(R.id.tv4_5);
        tv4_6 = (TextView) view.findViewById(R.id.tv4_6);
        tv4_7 = (TextView) view.findViewById(R.id.tv4_7);
        tv3_1 = (TextView) view.findViewById(R.id.tv3_1);
        tv3_2 = (TextView) view.findViewById(R.id.tv3_2);
        tv2_1 = (TextView) view.findViewById(R.id.tv2_1);
        tv1_1 = (TextView) view.findViewById(R.id.tv1);
        tvDb = (TextView) view.findViewById(R.id.tvDb);

        tvLotoTitle1 = (TextView) view.findViewById(R.id.tvLotoTitle1);
        tvLoto0_1 = (TextView) view.findViewById(R.id.tvLoto0_1);
        tvLoto1_1 = (TextView) view.findViewById(R.id.tvLoto1_1);
        tvLoto2_1 = (TextView) view.findViewById(R.id.tvLoto2_1);
        tvLoto3_1 = (TextView) view.findViewById(R.id.tvLoto3_1);
        tvLoto4_1 = (TextView) view.findViewById(R.id.tvLoto4_1);
        tvLoto5_1 = (TextView) view.findViewById(R.id.tvLoto5_1);
        tvLoto6_1 = (TextView) view.findViewById(R.id.tvLoto6_1);
        tvLoto7_1 = (TextView) view.findViewById(R.id.tvLoto7_1);
        tvLoto8_1 = (TextView) view.findViewById(R.id.tvLoto8_1);
        tvLoto9_1 = (TextView) view.findViewById(R.id.tvLoto9_1);
    }

    private void initTable2(View view) {
        table_2 = (TableLayout) view.findViewById(R.id.central_region_2);
        tvResgion_2 = (TextView) view.findViewById(R.id.tvRegionTitle2);
        tv8_2 = (TextView) view.findViewById(R.id.tv8_2);
        tv7_2 = (TextView) view.findViewById(R.id.tv7_2);
        tv6_1_2 = (TextView) view.findViewById(R.id.tv6_1_2);
        tv6_2_2 = (TextView) view.findViewById(R.id.tv6_2_2);
        tv6_3_2 = (TextView) view.findViewById(R.id.tv6_3_2);
        tv5_2 = (TextView) view.findViewById(R.id.tv5_2);
        tv4_1_2 = (TextView) view.findViewById(R.id.tv4_1_2);
        tv4_2_2 = (TextView) view.findViewById(R.id.tv4_2_2);
        tv4_3_2 = (TextView) view.findViewById(R.id.tv4_3_2);
        tv4_4_2 = (TextView) view.findViewById(R.id.tv4_4_2);
        tv4_5_2 = (TextView) view.findViewById(R.id.tv4_5_2);
        tv4_6_2 = (TextView) view.findViewById(R.id.tv4_6_2);
        tv4_7_2 = (TextView) view.findViewById(R.id.tv4_7_2);
        tv3_1_2 = (TextView) view.findViewById(R.id.tv3_1_2);
        tv3_2_2 = (TextView) view.findViewById(R.id.tv3_2_2);
        tv2_2 = (TextView) view.findViewById(R.id.tv2_2);
        tv1_2 = (TextView) view.findViewById(R.id.tv1_2);
        tvDb_2 = (TextView) view.findViewById(R.id.tvDb_2);

        tvLotoTitle2 = (TextView) view.findViewById(R.id.tvLotoTitle2);
        tvLoto0_2 = (TextView) view.findViewById(R.id.tvLoto0_2);
        tvLoto1_2 = (TextView) view.findViewById(R.id.tvLoto1_2);
        tvLoto2_2 = (TextView) view.findViewById(R.id.tvLoto2_2);
        tvLoto3_2 = (TextView) view.findViewById(R.id.tvLoto3_2);
        tvLoto4_2 = (TextView) view.findViewById(R.id.tvLoto4_2);
        tvLoto5_2 = (TextView) view.findViewById(R.id.tvLoto5_2);
        tvLoto6_2 = (TextView) view.findViewById(R.id.tvLoto6_2);
        tvLoto7_2 = (TextView) view.findViewById(R.id.tvLoto7_2);
        tvLoto8_2 = (TextView) view.findViewById(R.id.tvLoto8_2);
        tvLoto9_2 = (TextView) view.findViewById(R.id.tvLoto9_2);
    }

    private void initTable3(View view) {
        table_3 = (TableLayout) view.findViewById(R.id.central_region_3);
        tvResgion_3 = (TextView) view.findViewById(R.id.tvRegionTitle3);
        tv8_3 = (TextView) view.findViewById(R.id.tv8_3);
        tv7_3 = (TextView) view.findViewById(R.id.tv7_3);
        tv6_1_3 = (TextView) view.findViewById(R.id.tv6_1_3);
        tv6_2_3 = (TextView) view.findViewById(R.id.tv6_2_3);
        tv6_3_3 = (TextView) view.findViewById(R.id.tv6_3_3);
        tv5_3 = (TextView) view.findViewById(R.id.tv5_3);
        tv4_1_3 = (TextView) view.findViewById(R.id.tv4_1_3);
        tv4_2_3 = (TextView) view.findViewById(R.id.tv4_2_3);
        tv4_3_3 = (TextView) view.findViewById(R.id.tv4_3_3);
        tv4_4_3 = (TextView) view.findViewById(R.id.tv4_4_3);
        tv4_5_3 = (TextView) view.findViewById(R.id.tv4_5_3);
        tv4_6_3 = (TextView) view.findViewById(R.id.tv4_6_3);
        tv4_7_3 = (TextView) view.findViewById(R.id.tv4_7_3);
        tv3_1_3 = (TextView) view.findViewById(R.id.tv3_1_3);
        tv3_2_3 = (TextView) view.findViewById(R.id.tv3_2_3);
        tv2_3 = (TextView) view.findViewById(R.id.tv2_3);
        tv1_3 = (TextView) view.findViewById(R.id.tv1_3);
        tvDb_3 = (TextView) view.findViewById(R.id.tvDb_3);

        tvLotoTitle3 = (TextView) view.findViewById(R.id.tvLotoTitle3);
        tvLoto0_3 = (TextView) view.findViewById(R.id.tvLoto0_3);
        tvLoto1_3 = (TextView) view.findViewById(R.id.tvLoto1_3);
        tvLoto2_3 = (TextView) view.findViewById(R.id.tvLoto2_3);
        tvLoto3_3 = (TextView) view.findViewById(R.id.tvLoto3_3);
        tvLoto4_3 = (TextView) view.findViewById(R.id.tvLoto4_3);
        tvLoto5_3 = (TextView) view.findViewById(R.id.tvLoto5_3);
        tvLoto6_3 = (TextView) view.findViewById(R.id.tvLoto6_3);
        tvLoto7_3 = (TextView) view.findViewById(R.id.tvLoto7_3);
        tvLoto8_3 = (TextView) view.findViewById(R.id.tvLoto8_3);
        tvLoto9_3 = (TextView) view.findViewById(R.id.tvLoto9_3);
    }

    @Override
    public void setDataSocket(RESP_Result resp_result) {
        Log.e(TAG, "setDataSocket: " + toDay);
        loadingView.setVisibility(View.GONE);
        tvContent.setVisibility(View.GONE);
        tv_not_yet.setVisibility(View.GONE);
        switch (resp_result.getData().size()) {
            case 1:
                setTable2Hidden();
                setTable3Hidden();
                checkRandomTable1(resp_result.getData().get(0).getRes_special(),
                        resp_result.getData().get(0).getRes_first(),
                        resp_result.getData().get(0).getRes_second(),
                        resp_result.getData().get(0).getRes_third(),
                        resp_result.getData().get(0).getRes_fourth(),
                        resp_result.getData().get(0).getRes_fifth(),
                        resp_result.getData().get(0).getRes_sixth(),
                        resp_result.getData().get(0).getRes_seventh(),
                        resp_result.getData().get(0).getRes_eight());
                setResultLotteryTable1(
                        resp_result.getData().get(0).getArea(),
                        resp_result.getData().get(0).getRes_special(),
                        resp_result.getData().get(0).getRes_first(),
                        resp_result.getData().get(0).getRes_second(),
                        resp_result.getData().get(0).getRes_third(),
                        resp_result.getData().get(0).getRes_fourth(),
                        resp_result.getData().get(0).getRes_fifth(),
                        resp_result.getData().get(0).getRes_sixth(),
                        resp_result.getData().get(0).getRes_seventh(),
                        resp_result.getData().get(0).getRes_eight(),
                        resp_result.getData().get(0).getBegin_with());
                break;
            case 2:
                /**
                 * Table 1*/
                checkRandomTable1(resp_result.getData().get(0).getRes_special(),
                        resp_result.getData().get(0).getRes_first(),
                        resp_result.getData().get(0).getRes_second(),
                        resp_result.getData().get(0).getRes_third(),
                        resp_result.getData().get(0).getRes_fourth(),
                        resp_result.getData().get(0).getRes_fifth(),
                        resp_result.getData().get(0).getRes_sixth(),
                        resp_result.getData().get(0).getRes_seventh(),
                        resp_result.getData().get(0).getRes_eight());
                setResultLotteryTable1(
                        resp_result.getData().get(0).getArea(),
                        resp_result.getData().get(0).getRes_special(),
                        resp_result.getData().get(0).getRes_first(),
                        resp_result.getData().get(0).getRes_second(),
                        resp_result.getData().get(0).getRes_third(),
                        resp_result.getData().get(0).getRes_fourth(),
                        resp_result.getData().get(0).getRes_fifth(),
                        resp_result.getData().get(0).getRes_sixth(),
                        resp_result.getData().get(0).getRes_seventh(),
                        resp_result.getData().get(0).getRes_eight(),
                        resp_result.getData().get(0).getBegin_with());

                /**
                 * Table 2*/
                checkRandomTable2(resp_result.getData().get(1).getRes_special(),
                        resp_result.getData().get(1).getRes_first(),
                        resp_result.getData().get(1).getRes_second(),
                        resp_result.getData().get(1).getRes_third(),
                        resp_result.getData().get(1).getRes_fourth(),
                        resp_result.getData().get(1).getRes_fifth(),
                        resp_result.getData().get(1).getRes_sixth(),
                        resp_result.getData().get(1).getRes_seventh(),
                        resp_result.getData().get(1).getRes_eight());
                setResultLotteryTable2(
                        resp_result.getData().get(1).getArea(),
                        resp_result.getData().get(1).getRes_special(),
                        resp_result.getData().get(1).getRes_first(),
                        resp_result.getData().get(1).getRes_second(),
                        resp_result.getData().get(1).getRes_third(),
                        resp_result.getData().get(1).getRes_fourth(),
                        resp_result.getData().get(1).getRes_fifth(),
                        resp_result.getData().get(1).getRes_sixth(),
                        resp_result.getData().get(1).getRes_seventh(),
                        resp_result.getData().get(1).getRes_eight(),
                        resp_result.getData().get(1).getBegin_with());
                setTable3Hidden();
                break;
            case 3:
                checkRandomTable1(resp_result.getData().get(0).getRes_special(),
                        resp_result.getData().get(0).getRes_first(),
                        resp_result.getData().get(0).getRes_second(),
                        resp_result.getData().get(0).getRes_third(),
                        resp_result.getData().get(0).getRes_fourth(),
                        resp_result.getData().get(0).getRes_fifth(),
                        resp_result.getData().get(0).getRes_sixth(),
                        resp_result.getData().get(0).getRes_seventh(),
                        resp_result.getData().get(0).getRes_eight());
                setResultLotteryTable1(
                        resp_result.getData().get(0).getArea(),
                        resp_result.getData().get(0).getRes_special(),
                        resp_result.getData().get(0).getRes_first(),
                        resp_result.getData().get(0).getRes_second(),
                        resp_result.getData().get(0).getRes_third(),
                        resp_result.getData().get(0).getRes_fourth(),
                        resp_result.getData().get(0).getRes_fifth(),
                        resp_result.getData().get(0).getRes_sixth(),
                        resp_result.getData().get(0).getRes_seventh(),
                        resp_result.getData().get(0).getRes_eight(),
                        resp_result.getData().get(0).getBegin_with());

                checkRandomTable2(resp_result.getData().get(1).getRes_special(),
                        resp_result.getData().get(1).getRes_first(),
                        resp_result.getData().get(1).getRes_second(),
                        resp_result.getData().get(1).getRes_third(),
                        resp_result.getData().get(1).getRes_fourth(),
                        resp_result.getData().get(1).getRes_fifth(),
                        resp_result.getData().get(1).getRes_sixth(),
                        resp_result.getData().get(1).getRes_seventh(),
                        resp_result.getData().get(1).getRes_eight());
                setResultLotteryTable2(
                        resp_result.getData().get(1).getArea(),
                        resp_result.getData().get(1).getRes_special(),
                        resp_result.getData().get(1).getRes_first(),
                        resp_result.getData().get(1).getRes_second(),
                        resp_result.getData().get(1).getRes_third(),
                        resp_result.getData().get(1).getRes_fourth(),
                        resp_result.getData().get(1).getRes_fifth(),
                        resp_result.getData().get(1).getRes_sixth(),
                        resp_result.getData().get(1).getRes_seventh(),
                        resp_result.getData().get(1).getRes_eight(),
                        resp_result.getData().get(1).getBegin_with());


                /**
                 * Table 3*/
                checkRandomTable3(resp_result.getData().get(2).getRes_special(),
                        resp_result.getData().get(2).getRes_first(),
                        resp_result.getData().get(2).getRes_second(),
                        resp_result.getData().get(2).getRes_third(),
                        resp_result.getData().get(2).getRes_fourth(),
                        resp_result.getData().get(2).getRes_fifth(),
                        resp_result.getData().get(2).getRes_sixth(),
                        resp_result.getData().get(2).getRes_seventh(),
                        resp_result.getData().get(2).getRes_eight());
                setResultLotteryTable3(
                        resp_result.getData().get(2).getArea(),
                        resp_result.getData().get(2).getRes_special(),
                        resp_result.getData().get(2).getRes_first(),
                        resp_result.getData().get(2).getRes_second(),
                        resp_result.getData().get(2).getRes_third(),
                        resp_result.getData().get(2).getRes_fourth(),
                        resp_result.getData().get(2).getRes_fifth(),
                        resp_result.getData().get(2).getRes_sixth(),
                        resp_result.getData().get(2).getRes_seventh(),
                        resp_result.getData().get(2).getRes_eight(),
                        resp_result.getData().get(2).getBegin_with());
                break;
        }
        count++;
//        Log.e("Count get", "setDataSocket: Total" + count);
    }

    @Override
    public void setTable3Hidden() {
        table_3.setVisibility(View.GONE);
        tvLotoTitle3.setVisibility(View.GONE);
        tvLoto0_3.setVisibility(View.GONE);
        tvLoto1_3.setVisibility(View.GONE);
        tvLoto2_3.setVisibility(View.GONE);
        tvLoto3_3.setVisibility(View.GONE);
        tvLoto4_3.setVisibility(View.GONE);
        tvLoto5_3.setVisibility(View.GONE);
        tvLoto6_3.setVisibility(View.GONE);
        tvLoto7_3.setVisibility(View.GONE);
        tvLoto8_3.setVisibility(View.GONE);
        tvLoto9_3.setVisibility(View.GONE);
    }

    @Override
    public void setTable2Hidden() {
        table_2.setVisibility(View.GONE);
        tvLotoTitle2.setVisibility(View.GONE);
        tvLoto0_2.setVisibility(View.GONE);
        tvLoto1_2.setVisibility(View.GONE);
        tvLoto2_2.setVisibility(View.GONE);
        tvLoto3_2.setVisibility(View.GONE);
        tvLoto4_2.setVisibility(View.GONE);
        tvLoto5_2.setVisibility(View.GONE);
        tvLoto6_2.setVisibility(View.GONE);
        tvLoto7_2.setVisibility(View.GONE);
        tvLoto8_2.setVisibility(View.GONE);
        tvLoto9_2.setVisibility(View.GONE);
    }

    @Override
    public void setVisibleTable(boolean isVisible) {
        if (isVisible) {
            loadingView.setVisibility(View.GONE);
            tvContent.setVisibility(View.GONE);
        } else {
            loadingView.setVisibility(View.VISIBLE);
            tvContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void clearOldData() {

    }

    private void initRandomRolling() {
        /**
         * 8
         * Random number table all*/


        if (rl81 == null) {
            rl81 = new Roller(tv8_1, 100000, 100, 99, 10);
        }
        if (rl82 == null){
            rl82 = new Roller(tv8_2, 100000, 100, 99, 10);
        }
        if (rl83 == null){
            rl83 = new Roller(tv8_3, 100000, 100, 99, 10);
        }

        /**
         * 7
         * Random number table all*/
        if (rl71 == null) {
            rl71 = new Roller(tv7_1, 10000, 100, 999, 100);
        }
        if (rl72 == null) {
            rl72 = new Roller(tv7_2, 10000, 100, 999, 100);
        }
        if (rl73 == null) {
            rl73 = new Roller(tv7_3, 10000, 100, 999, 100);
        }


        /**
         * 6
         * Random number table 1*/


        if (rl611 == null) {
            rl611 = new Roller(tv6_1, 10000, 100, 9999, 1000);
        }
        if (rl612 == null) {
            rl612 = new Roller(tv6_2, 10000, 100, 9999, 1000);
        }
        if (rl613 == null) {
            rl613 = new Roller(tv6_3, 10000, 100, 9999, 1000);

        }


        /**
         * 6
         * Random number table 2*/
        if (rl621 == null) {
            rl621 = new Roller(tv6_1_2, 10000, 100, 9999, 1000);
        }
        if (rl622 == null) {
            rl622 = new Roller(tv6_2_2, 10000, 100, 9999, 1000);

        }
        if (rl623 == null) {
            rl623 = new Roller(tv6_3_2, 10000, 100, 9999, 1000);
        }


        /**
         * 6
         * Random number table 3*/
        if (rl631 == null) {
            rl631 = new Roller(tv6_1_3, 10000, 100, 9999, 1000);
        }
        if (rl632 == null) {
            rl632 = new Roller(tv6_2_3, 10000, 100, 9999, 1000);
        }
        if (rl633 == null) {
            rl633 = new Roller(tv6_3_3, 10000, 100, 9999, 1000);
        }


        /**
         * 5
         * Random number table all*/

        if (rl51 == null) {
            rl51 = new Roller(tv5_1, 10000, 100, 9999, 1000);
        }

        if (rl52 == null) {
            rl52 = new Roller(tv5_2, 10000, 100, 9999, 1000);
        }

        if (rl53 == null) {
            rl53 = new Roller(tv5_3, 10000, 100, 9999, 1000);
        }


        /**
         * 4
         * Random number table 1*/
        if (rl41 == null) {
            rl41 = new Roller(tv4_1, 10000, 100, 99999, 10000);
        }
        if (rl412 == null) {
            rl412 = new Roller(tv4_2, 10000, 100, 99999, 10000);
        }
        if (rl413 == null) {
            rl413 = new Roller(tv4_3, 10000, 100, 99999, 10000);
        }
        if (rl414 == null) {
            rl414 = new Roller(tv4_4, 10000, 100, 99999, 10000);
        }
        if (rl415 == null) {
            rl415 = new Roller(tv4_5, 10000, 100, 99999, 10000);
        }
        if (rl416 == null) {
            rl416 = new Roller(tv4_6, 10000, 100, 99999, 10000);
        }
        if (rl417 == null) {
            rl417 = new Roller(tv4_7, 10000, 100, 99999, 10000);
        }

        /**
         * 4
         * Random number table 2*/

        if (rl42 == null) {
            rl42 = new Roller(tv4_1_2, 10000, 100, 99999, 10000);
        }
        if (rl422 == null) {
            rl422 = new Roller(tv4_2_2, 10000, 100, 99999, 10000);
        }
        if (rl423 == null) {
            rl423 = new Roller(tv4_3_2, 10000, 100, 99999, 10000);
        }
        if (rl424 == null) {
            rl424 = new Roller(tv4_4_2, 10000, 100, 99999, 10000);
        }
        if (rl425 == null) {
            rl425 = new Roller(tv4_5_2, 10000, 100, 99999, 10000);
        }
        if (rl426 == null) {
            rl426 = new Roller(tv4_6_2, 10000, 100, 99999, 10000);
        }
        if (rl427 == null) {
            rl427 = new Roller(tv4_7_2, 10000, 100, 99999, 10000);
        }


        /**
         * 4
         * Random number table 3*/
        if (rl43 == null) {
            rl43 = new Roller(tv4_1_3, 10000, 100, 99999, 10000);
        }
        if (rl432 == null) {
            rl432 = new Roller(tv4_2_3, 10000, 100, 99999, 10000);
        }
        if (rl433 == null) {
            rl433 = new Roller(tv4_3_3, 10000, 100, 99999, 10000);
        }
        if (rl434 == null) {
            rl434 = new Roller(tv4_4_3, 10000, 100, 99999, 10000);
        }
        if (rl435 == null) {
            rl435 = new Roller(tv4_5_3, 10000, 100, 99999, 10000);
        }
        if (rl436 == null) {
            rl436 = new Roller(tv4_6_3, 10000, 100, 99999, 10000);
        }
        if (rl437 == null) {
            rl437 = new Roller(tv4_7_3, 10000, 100, 99999, 10000);
        }

        /**
         * 3
         * Random number table 1*/
        if (rl311 == null) {
            rl311 = new Roller(tv3_1, 10000, 100, 99999, 10000);
        }
        if (rl312 == null) {
            rl312 = new Roller(tv3_2, 10000, 100, 99999, 10000);
        }

        /**
         * 3
         * Random number table 2*/
        if (rl321 == null) {
            rl321 = new Roller(tv3_1_2, 10000, 100, 99999, 10000);
        }
        if (rl322 == null) {
            rl322 = new Roller(tv3_2_2, 10000, 100, 99999, 10000);
        }


        /**
         * 3
         * Random number table 3*/
        if (rl331 == null) {
            rl331 = new Roller(tv3_1_3, 10000, 100, 99999, 10000);
        }
        if (rl332 == null) {
            rl332 = new Roller(tv3_2_3, 10000, 100, 99999, 10000);
        }


        /**
         * 2
         * Random number table all*/

        if (rl_second_1 == null) {
            rl_second_1 = new Roller(tv2_1, 10000, 100, 99999, 10000);
        }
        if (rl_second_2 == null) {
            rl_second_2 = new Roller(tv2_2, 10000, 100, 99999, 10000);
        }
        if (rl_second_3 == null) {
            rl_second_3 = new Roller(tv2_3, 10000, 100, 99999, 10000);
        }

        /**
         * 1
         * Random number table all*/

        if (rl_first_1 == null) {
            rl_first_1 = new Roller(tv1_1, 10000, 100, 99999, 10000);
        }
        if (rl_first_2 == null) {
            rl_first_2 = new Roller(tv1_2, 10000, 100, 99999, 10000);
        }
        if (rl_first_3 == null) {
            rl_first_3 = new Roller(tv1_3, 10000, 100, 99999, 10000);
        }

        /**
         * DB
         * Random number table all*/
        if (rl_special_1 == null) {
            rl_special_1 = new Roller(tvDb, 10000, 100, 999999, 100000);
        }
        if (rl_special_2 == null) {
            rl_special_2 = new Roller(tvDb_2, 10000, 100, 999999, 100000);
        }
        if (rl_special_3 == null) {
            rl_special_3 = new Roller(tvDb_3, 10000, 100, 999999, 100000);
        }
    }

    @Override
    public void random(int random_table) {
        switch (random_table) {
            case 1:
                rl81.run();
                break;
            case 2:
                rl81.run();
                rl82.run();
                break;
            case 3:
                rl81.run();
                rl82.run();
                rl83.run();
                break;
        }
    }

    @Override
    public void setLiveLoto(RESP_LiveLoto liveLoto, int positionTable) {
        switch (positionTable) {
            case 1:
                setBeginLive(liveLoto.getBegin_with());
                break;
            case 2:
                setBeginLive2(liveLoto.getBegin_with());
                break;
            case 3:
                setBeginLive3(liveLoto.getBegin_with());
                break;
        }
    }

    @Override
    public void setEndLive() {
        presenter.checkSocket();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setEndLive(2);
        SharedUtils.getInstance().putBooleanValue(Constants.CHECK_DONE_C, true);
    }

    @Override
    public void setNewResult(RESP_NewResult newResult, int position_table) {
        AudioPlay.getInstance().play(getContext());
        spined = true;
        switch (position_table) {
            /**
             * Table 1*/
            case 1:
                setNewResultTable1(newResult);
                break;
            /**
             * Table 2*/
            case 2:
                setNewResultTable2(newResult);
                break;
            /**
             * Table 3*/
            case 3:
                setNewResultTable3(newResult);
                break;
        }
    }

    private void setNewResultTable3(RESP_NewResult newResult) {
        switch (newResult.getResult_name()) {
            case "res_eight":
                if (newResult.getValue() != null) {
                    if (rl83 != null) {
                        if (rl83.isRun())
                            rl83.shutdownThread(true);
                    }
                    if (!rl73.isRun())
                        rl73.run();
                    tv8_3.setText(newResult.getValue());
                }
                break;
            case "res_seventh":
                if (newResult.getValue() != null) {
                    if (rl73 != null) {
                        if (rl73.isRun())
                            rl73.shutdownThread(false);
                    }
                    if (!rl631.isRun())
                        rl631.run();
                    tv7_3.setText(newResult.getValue());
                }
                break;
            case "res_sixth":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (rl631 != null) {
                                if (rl631.isRun())
                                    rl631.shutdownThread(false);
                            }
                            if (!rl632.isRun())
                                rl632.run();
                            tv6_1_3.setText(newResult.getValue());
                            break;
                        case "1":
                            if (rl632 != null) {
                                if (rl632.isRun())
                                    rl632.shutdownThread(false);
                            }
                            if (rl633.isRun())
                                rl633.run();
                            tv6_2_3.setText(newResult.getValue());
                            break;
                        case "2":
                            if (rl633 != null) {
                                if (rl633.isRun())
                                    rl633.shutdownThread(false);
                            }
                            if (!rl53.isRun())
                                rl53.run();
                            tv6_3_3.setText(newResult.getValue());
                            break;
                    }

                }
                break;
            case "res_fifth":
                if (rl53 != null) {
                    if (rl53.isRun())
                        rl53.shutdownThread(false);
                }
                if (!rl43.isRun())
                    rl43.run();
                tv5_3.setText(newResult.getValue());
                break;
            case "res_fourth":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (rl43 != null) {
                                if (rl43.isRun())
                                    rl43.shutdownThread(false);
                            }
                            if (!rl432.isRun())
                                rl432.run();
                            tv4_1_3.setText(newResult.getValue());
                            break;
                        case "1":
                            if (rl432 != null) {
                                if (rl432.isRun())
                                    rl432.shutdownThread(false);
                            }
                            if (!rl433.isRun())
                                rl433.run();
                            tv4_2_3.setText(newResult.getValue());
                            break;
                        case "2":
                            if (rl433 != null) {
                                if (rl433.isRun())
                                    rl433.shutdownThread(false);
                            }
                            if (!rl434.isRun())
                                rl434.run();
                            tv4_3_3.setText(newResult.getValue());
                            break;

                        case "3":
                            if (rl434 != null) {
                                if (rl434.isRun())
                                    rl434.shutdownThread(false);
                            }
                            if (!rl435.isRun())
                                rl435.run();
                            tv4_4_3.setText(newResult.getValue());
                            break;
                        case "4":
                            if (rl435 != null) {
                                if (rl435.isRun())
                                    rl435.shutdownThread(false);
                            }
                            if (!rl436.isRun())
                                rl436.run();
                            tv4_5_3.setText(newResult.getValue());
                            break;
                        case "5":
                            if (rl436 != null) {
                                if (rl436.isRun())
                                    rl436.shutdownThread(false);
                            }
                            if (rl437.isRun())
                                rl437.run();
                            tv4_6_3.setText(newResult.getValue());
                            break;
                        case "6":
                            if (rl437 != null) {
                                if (rl437.isRun())
                                    rl437.shutdownThread(false);
                            }
                            if (!rl331.isRun())
                                rl331.run();
                            tv4_7_3.setText(newResult.getValue());
                            break;
                    }

                }
                break;
            case "res_third":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (rl331 != null) {
                                if (rl331.isRun())
                                    rl331.shutdownThread(false);
                            }
                            if (!rl332.isRun())
                                rl332.run();
                            tv3_1_3.setText(newResult.getValue());
                            break;
                        case "1":
                            if (rl332 != null) {
                                if (rl332.isRun())
                                    rl332.shutdownThread(false);
                            }
                            if (!rl_second_3.isRun())
                                rl_second_3.run();
                            tv3_2_3.setText(newResult.getValue());
                            break;
                    }
                }
                break;
            case "res_second":
                if (rl_second_3 != null) {
                    if (rl_second_3.isRun())
                        rl_second_3.shutdownThread(false);
                }
                if (!rl_first_3.isRun())
                    rl_first_3.run();
                tv2_3.setText(newResult.getValue());
                break;
            case "res_first":
                if (rl_first_3 != null) {
                    if (rl_first_3.isRun())
                        rl_first_3.shutdownThread(false);
                }
                if (!rl_special_3.isRun())
                    rl_special_3.run();
                tv1_3.setText(newResult.getValue());
                break;
            case "res_special":
                if (rl_special_3 != null) {
                    if (rl_special_3.isRun())
                        rl_special_3.shutdownThread(true);
                }
                LotoSpecial_3 = newResult.getValue();
                tvDb_3.setText(newResult.getValue());
                break;
        }
    }

    private void setNewResultTable2(RESP_NewResult newResult) {
        switch (newResult.getResult_name()) {
            case "res_eight":
                if (newResult.getValue() != null) {
                    if (rl82 != null) {
                        if (rl82.isRun())
                            rl82.shutdownThread(true);
                    }
                    if (!rl72.isRun())
                        rl72.run();
                    tv8_2.setText(newResult.getValue());
                }
                break;
            case "res_seventh":
                if (newResult.getValue() != null) {
                    if (rl72 != null) {
                        if (rl72.isRun())
                            rl72.shutdownThread(false);
                    }
                    if (!rl621.isRun())
                        rl621.run();
                    tv7_2.setText(newResult.getValue());
                }
                break;
            case "res_sixth":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (rl621 != null) {
                                if (rl621.isRun())
                                    rl621.shutdownThread(false);
                            }
                            if (!rl622.isRun())
                                rl622.run();
                            tv6_1_2.setText(newResult.getValue());
                            break;
                        case "1":
                            if (rl622 != null) {
                                if (rl622.isRun())
                                    rl622.shutdownThread(false);
                            }
                            if (!rl623.isRun())
                                rl623.run();
                            tv6_2_2.setText(newResult.getValue());
                            break;
                        case "2":
                            if (rl623 != null) {
                                if (rl623.isRun())
                                    rl623.shutdownThread(false);
                            }
                            if (!rl52.isRun())
                                rl52.run();
                            tv6_3_2.setText(newResult.getValue());
                            break;
                    }

                }
                break;
            case "res_fifth":
                if (rl52 != null) {
                    if (rl52.isRun())
                        rl52.shutdownThread(false);
                }
                if (!rl42.isRun())
                    rl42.run();
                tv5_2.setText(newResult.getValue());
                break;
            case "res_fourth":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (rl42 != null) {
                                if (rl42.isRun())
                                    rl42.shutdownThread(false);
                            }
                            if (!rl422.isRun())
                                rl422.run();
                            tv4_1_2.setText(newResult.getValue());
                            break;
                        case "1":
                            if (rl422 != null) {
                                if (rl422.isRun())
                                    rl422.shutdownThread(false);
                            }
                            if (!rl423.isRun())
                                rl423.run();
                            tv4_2_2.setText(newResult.getValue());
                            break;
                        case "2":
                            if (rl423 != null) {
                                if (rl423.isRun())
                                    rl423.shutdownThread(false);
                            }
                            if (!rl424.isRun())
                                rl424.run();
                            tv4_3_2.setText(newResult.getValue());
                            break;

                        case "3":
                            if (rl424 != null) {
                                if (rl424.isRun())
                                    rl424.shutdownThread(false);
                            }
                            if (!rl425.isRun())
                                rl425.run();
                            tv4_4_2.setText(newResult.getValue());
                            break;
                        case "4":
                            if (rl425 != null) {
                                if (rl425.isRun())
                                    rl425.shutdownThread(false);
                            }
                            if (!rl426.isRun())
                                rl426.run();
                            tv4_5_2.setText(newResult.getValue());
                            break;
                        case "5":
                            if (rl426 != null) {
                                if (rl426.isRun())
                                    rl426.shutdownThread(false);
                            }
                            if (!rl427.isRun())
                                rl427.run();
                            tv4_6_2.setText(newResult.getValue());
                            break;
                        case "6":
                            if (rl427 != null) {
                                if (rl427.isRun())
                                    rl427.shutdownThread(false);
                            }
                            if (!rl321.isRun())
                                rl321.run();
                            tv4_7_2.setText(newResult.getValue());
                            break;
                    }

                }
                break;
            case "res_third":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (rl321 != null) {
                                if (rl321.isRun())
                                    rl321.shutdownThread(false);
                            }
                            if (!rl322.isRun())
                                rl322.run();
                            tv3_1_2.setText(newResult.getValue());
                            break;
                        case "1":
                            if (rl322 != null) {
                                if (rl322.isRun())
                                    rl322.shutdownThread(false);
                            }
                            if (!rl_second_2.isRun())
                                rl_second_2.run();
                            tv3_2_2.setText(newResult.getValue());
                            break;
                    }
                }
                break;
            case "res_second":
                if (rl_second_2 != null) {
                    if (rl_second_2.isRun())
                        rl_second_2.shutdownThread(false);
                }
                if (!rl_first_2.isRun())
                    rl_first_2.run();
                tv2_2.setText(newResult.getValue());
                break;
            case "res_first":
                if (rl_first_2 != null) {
                    if (rl_first_2.isRun())
                        rl_first_2.shutdownThread(false);
                }
                if (!rl_special_2.isRun())
                    rl_special_2.run();
                tv1_2.setText(newResult.getValue());
                break;
            case "res_special":
                if (rl_special_2 != null) {
                    if (rl_special_2.isRun())
                        rl_special_2.shutdownThread(true);
                }
                LotoSpecial_2 = newResult.getValue();
                tvDb_2.setText(newResult.getValue());
                break;
        }
    }

    private void setNewResultTable1(RESP_NewResult newResult) {
        switch (newResult.getResult_name()) {
            case "res_eight":
                if (newResult.getValue() != null) {
                    if (rl81 != null) {
                        if (rl81.isRun())
                            rl81.shutdownThread(true);
                    }
                    if (!rl71.isRun())
                        rl71.run();
                    tv8_1.setText(newResult.getValue());
                }
                break;
            case "res_seventh":
                if (newResult.getValue() != null) {
                    if (rl71 != null) {
                        if (rl71.isRun())
                            rl71.shutdownThread(false);
                    }
                    if (!rl611.isRun())
                        rl611.run();
                    tv7_1.setText(newResult.getValue());
                }
                break;
            case "res_sixth":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (rl611 != null) {
                                if (rl611.isRun())
                                    rl611.shutdownThread(false);
                            }
                            if (!rl612.isRun())
                                rl612.run();
                            tv6_1.setText(newResult.getValue());
                            break;
                        case "1":
                            if (rl612 != null) {
                                if (rl612.isRun())
                                    rl612.shutdownThread(false);
                            }
                            if (!rl613.isRun())
                                rl613.run();
                            tv6_2.setText(newResult.getValue());
                            break;
                        case "2":
                            if (rl613 != null) {
                                if (rl613.isRun())
                                    rl613.shutdownThread(false);
                            }
                            if (!rl51.isRun())
                                rl51.run();
                            tv6_3.setText(newResult.getValue());
                            break;
                    }

                }
                break;
            case "res_fifth":
                if (rl51 != null) {
                    if (rl51.isRun())
                        rl51.shutdownThread(false);
                }
                if (!rl41.isRun())
                    rl41.run();
                tv5_1.setText(newResult.getValue());
                break;
            case "res_fourth":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (rl41 != null) {
                                if (rl41.isRun())
                                    rl41.shutdownThread(false);
                            }
                            if (!rl412.isRun())
                                rl412.run();
                            tv4_1.setText(newResult.getValue());
                            break;
                        case "1":
                            if (rl412 != null) {
                                if (rl412.isRun())
                                    rl412.shutdownThread(false);
                            }
                            if (!rl413.isRun())
                                rl413.run();
                            tv4_2.setText(newResult.getValue());
                            break;
                        case "2":
                            if (rl413 != null) {
                                if (rl413.isRun())
                                    rl413.shutdownThread(false);
                            }
                            if (!rl414.isRun())
                                rl414.run();
                            tv4_3.setText(newResult.getValue());
                            break;

                        case "3":
                            if (rl414 != null) {
                                if (rl414.isRun())
                                    rl414.shutdownThread(false);
                            }
                            if (!rl415.isRun())
                                rl415.run();
                            tv4_4.setText(newResult.getValue());
                            break;
                        case "4":
                            if (rl415 != null) {
                                if (rl415.isRun())
                                    rl415.shutdownThread(false);
                            }
                            if (!rl416.isRun())
                                rl416.run();
                            tv4_5.setText(newResult.getValue());
                            break;
                        case "5":
                            if (rl416 != null) {
                                if (rl416.isRun())
                                    rl416.shutdownThread(false);
                            }
                            if (!rl417.isRun())
                                rl417.run();
                            tv4_6.setText(newResult.getValue());
                            break;
                        case "6":
                            if (rl417 != null) {
                                if (rl417.isRun())
                                    rl417.shutdownThread(false);
                            }
                            if (!rl311.isRun())
                                rl311.run();
                            tv4_7.setText(newResult.getValue());
                            break;
                    }

                }
                break;
            case "res_third":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (rl311 != null) {
                                if (rl311.isRun())
                                    rl311.shutdownThread(false);
                            }
                            if (!rl312.isRun())
                                rl312.run();
                            tv3_1.setText(newResult.getValue());
                            break;
                        case "1":
                            if (rl312 != null) {
                                if (rl312.isRun())
                                    rl312.shutdownThread(false);
                            }
                            if (!rl_second_1.isRun())
                                rl_second_1.run();
                            tv3_2.setText(newResult.getValue());
                            break;
                    }
                }
                break;
            case "res_second":
                if (rl_second_1 != null) {
                    if (rl_second_1.isRun())
                        rl_second_1.shutdownThread(false);
                }
                if (!rl_first_1.isRun())
                    rl_first_1.run();
                tv2_1.setText(newResult.getValue());
                break;
            case "res_first":
                if (rl_first_1 != null) {
                    if (rl_first_1.isRun())
                        rl_first_1.shutdownThread(false);
                }
                if (!rl_special_1.isRun())
                    rl_special_1.run();
                tv1_1.setText(newResult.getValue());
                break;
            case "res_special":
                if (rl_special_1 != null) {
                    if (rl_special_1.isRun())
                        rl_special_1.shutdownThread(true);
                }
                LotoSpecial_1 = newResult.getValue();
                tvDb.setText(newResult.getValue());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isExistsBegin = false;
        isExistsBegin_2 = false;
        isExistsBegin_3 = false;
        if (isLive) {
            isLive = false;
            presenter.connectSocket();
        }

        sound = SharedUtils.getInstance().getBooleanDefaultTrueValue(Constants.SOUND_FLAG);
        if (!sound){
            mute = false;
            if (audioManager!=null){
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            }
            if (img_mute !=null){
                img_mute.setImageResource(R.mipmap.ic_mute_on);
            }
        } else {
            mute = true;
            if (img_mute != null){
                img_mute.setImageResource(R.mipmap.ic_mute);
            }
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (toDay && TimeUtils.checkTimeInMilisecondNorth(17, 10, 17, 45)) {
//            presenter.disconnectSocket();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        presenter.disconnectSocket();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void checkRandomTable1(List<String> special, List<String> first, List<String> second, List<String> third, List<String> fourd, List<String> five, List<String> six, List<String> seven, List<String> eight) {
        if (toDay) {
            if (eight != null) {
                if (eight.size() > 0) {
                    if (rl81.isRun())
                        rl81.shutdownThread(true);
                } else {
                    if (!rl81.isRun())
                        rl81.run();
                }
            }

            if (eight != null && seven != null && eight.size() == 1) {
                int size = seven.size();
                if (size > 0) {
                    if (seven.get(0) != null && !seven.get(0).isEmpty()) {
                        if (rl71.isRun())
                            rl71.shutdownThread(false);
                    }
                } else {
                    if (!rl71.isRun())
                        rl71.run();
                }
            }

            /**
             * Six*/
            if (seven != null && six != null && seven.size() == 1) {
                switch (six.size()) {
                    case 0:
                        if (!rl611.isRun())
                            rl611.run();
                        break;
                    case 1:
                        if (rl611.isRun())
                            rl611.shutdownThread(false);

                        if (!rl612.isRun())
                            rl612.run();
                        break;
                    case 2:
                        if (rl612.isRun())
                            rl612.shutdownThread(false);

                        if (!rl613.isRun()) {
                            rl613.run();
                        }
                        break;
                    case 3:
                        if (rl613.isRun())
                            rl613.shutdownThread(false);
                        break;
                }
            }

            if (six != null && five != null && six.size() == 3) {
                if (five.size() > 0) {
                    if (rl51.isRun()) {
                        rl51.shutdownThread(false);
                    }
                } else {
                    if (!rl51.isRun())
                        rl51.run();
                }
            }

            /**
             * Fourd*/
            if (five != null && fourd != null && five.size() == 1) {
                switch (fourd.size()) {
                    case 0:
                        if (!rl41.isRun())
                            rl41.run();
                        break;
                    case 1:
                        if (rl41.isRun())
                            rl41.shutdownThread(false);

                        if (!rl412.isRun())
                            rl412.run();
                        break;
                    case 2:
                        if (rl412.isRun())
                            rl412.shutdownThread(false);

                        if (!rl413.isRun())
                            rl413.run();
                        break;
                    case 3:
                        if (rl413.isRun())
                            rl413.shutdownThread(false);

                        if (!rl414.isRun())
                            rl414.run();
                        break;
                    case 4:
                        if (rl414.isRun())
                            rl414.shutdownThread(false);

                        if (!rl415.isRun())
                            rl415.run();
                        break;
                    case 5:
                        if (rl415.isRun())
                            rl415.shutdownThread(false);

                        if (!rl416.isRun())
                            rl416.run();
                        break;
                    case 6:
                        if (rl416.isRun())
                            rl416.shutdownThread(false);

                        if (!rl417.isRun())
                            rl417.run();
                        break;
                    case 7:
                        if (rl417.isRun())
                            rl417.shutdownThread(false);

                        break;
                }
            }

            if (fourd != null && third != null && fourd.size() == 7) {
                switch (third.size()) {
                    case 0:
                        if (!rl311.isRun())
                            rl311.run();
                        break;
                    case 1:
                        if (rl311.isRun())
                            rl311.shutdownThread(false);

                        if (!rl312.isRun())
                            rl312.run();
                        break;
                    case 2:
                        if (rl312.isRun())
                            rl312.shutdownThread(false);
                        break;
                }
            }

            if (third != null && second != null && third.size() == 2) {
                if (second.size() > 0) {
                    if (rl_second_1.isRun())
                        rl_second_1.shutdownThread(false);
                } else {
                    if (!rl_second_1.isRun())
                        rl_second_1.run();
                }
            }


            if (second != null && first != null && second.size() == 1) {
                if (first.size() > 0) {
                    if (rl_first_1.isRun())
                        rl_first_1.shutdownThread(false);

                } else {
                    if (!rl_first_1.isRun())
                        rl_first_1.run();
                }
            }

            if (first != null && special != null && first.size() == 1) {
                if (special.size() > 0) {
                    if (rl_special_1.isRun())
                        rl_special_1.shutdownThread(true);
                } else {
                    if (!rl_special_1.isRun())
                        rl_special_1.run();
                }
            }
        }
    }

    private void checkRandomTable2(List<String> special, List<String> first, List<String> second, List<String> third, List<String> fourd, List<String> five, List<String> six, List<String> seven, List<String> eight) {
        if (toDay) {

            if (eight != null) {
                if (eight.size() > 0) {
                    if (rl82.isRun())
                        rl82.shutdownThread(true);
                } else {
                    if (!rl82.isRun())
                        rl82.run();
                }
            }

            if (eight != null && seven != null && eight.size() == 1) {
                if (seven.size() > 0) {
                    if (rl72.isRun())
                        rl72.shutdownThread(false);
                } else {
                    if (!rl72.isRun())
                        rl72.run();
                }
            }


            /**
             * Six*/
            if (seven != null && six != null && seven.size() == 1) {
                switch (six.size()) {
                    case 0:
                        if (!rl621.isRun())
                            rl621.run();
                        break;
                    case 1:
                        if (rl621.isRun())
                            rl621.shutdownThread(false);

                        if (!rl622.isRun())
                            rl622.run();
                        break;
                    case 2:
                        if (rl622.isRun())
                            rl622.shutdownThread(false);

                        if (!rl623.isRun()) {
                            rl623.run();
                        }
                        break;
                    case 3:
                        if (rl623.isRun())
                            rl623.shutdownThread(false);
                        break;
                }
            }

            if (five != null && six.size() == 3) {
                if (five.size() > 0) {
                    if (rl52.isRun()) {
                        rl52.shutdownThread(false);
                    }
                } else {
                    if (!rl52.isRun())
                        rl52.run();
                }
            }

            /**
             * Fourd*/
            if (five != null && fourd != null && five.size() == 1) {
                switch (fourd.size()) {
                    case 0:
                        if (!rl42.isRun())
                            rl42.run();
                        break;
                    case 1:
                        if (rl42.isRun())
                            rl42.shutdownThread(false);

                        if (!rl422.isRun())
                            rl422.run();
                        break;
                    case 2:
                        if (rl422.isRun())
                            rl422.shutdownThread(false);

                        if (!rl423.isRun())
                            rl423.run();
                        break;
                    case 3:
                        if (rl423.isRun())
                            rl423.shutdownThread(false);

                        if (!rl424.isRun())
                            rl424.run();
                        break;
                    case 4:
                        if (rl424.isRun())
                            rl424.shutdownThread(false);

                        if (!rl425.isRun())
                            rl425.run();
                        break;
                    case 5:
                        if (rl425.isRun())
                            rl425.shutdownThread(false);

                        if (!rl426.isRun())
                            rl426.run();
                        break;
                    case 6:
                        if (rl426.isRun())
                            rl426.shutdownThread(false);

                        if (!rl427.isRun())
                            rl427.run();
                        break;
                    case 7:
                        if (rl427.isRun())
                            rl427.shutdownThread(false);

                        break;
                }
            }

            if (fourd != null && third != null && fourd.size() == 7) {
                switch (third.size()) {
                    case 0:
                        if (!rl321.isRun())
                            rl321.run();
                        break;
                    case 1:
                        if (rl321.isRun())
                            rl321.shutdownThread(false);

                        if (!rl322.isRun())
                            rl322.run();
                        break;
                    case 2:
                        if (rl322.isRun())
                            rl322.shutdownThread(false);
                        break;
                }
            }
            if (third != null && second != null && third.size() == 2) {
                if (second.size() > 0) {
                    if (rl_second_2.isRun())
                        rl_second_2.shutdownThread(false);
                } else {
                    if (!rl_second_2.isRun())
                        rl_second_2.run();
                }
            }

            if (second != null && first != null && second.size() == 1) {
                if (first.size() > 0) {
                    if (rl_first_2.isRun())
                        rl_first_2.shutdownThread(false);

                } else {
                    if (!rl_first_2.isRun())
                        rl_first_2.run();
                }
            }
            if (first != null && special != null && first.size() == 1) {
                if (special.size() > 0) {
                    if (rl_special_1.isRun())
                        rl_special_1.shutdownThread(true);
                } else {
                    if (!rl_special_1.isRun())
                        rl_special_1.run();
                }
            }
        }
    }

    private void checkRandomTable3(List<String> special, List<String> first, List<String> second, List<String> third, List<String> fourd, List<String> five, List<String> six, List<String> seven, List<String> eight) {

        if (toDay) {
            if (eight != null) {
                if (eight.size() > 0) {
                    if (rl83.isRun())
                        rl83.shutdownThread(true);
                } else {
                    if (!rl83.isRun())
                        rl83.run();
                }
            }

            if (eight != null && seven != null && eight.size() == 1) {
                if (seven.size() > 0) {
                    if (rl73.isRun())
                        rl73.shutdownThread(false);
                } else {
                    if (!rl73.isRun())
                        rl73.run();
                }
            }

            /**
             * Six*/
            if (seven != null && six != null && seven.size() == 1) {
                switch (six.size()) {
                    case 0:
                        if (!rl631.isRun())
                            rl631.run();
                        break;
                    case 1:
                        if (rl631.isRun())
                            rl631.shutdownThread(false);

                        if (!rl632.isRun())
                            rl632.run();
                        break;
                    case 2:
                        if (rl632.isRun())
                            rl632.shutdownThread(false);

                        if (!rl633.isRun()) {
                            rl633.run();
                        }
                        break;
                    case 3:
                        if (rl633.isRun())
                            rl633.shutdownThread(false);
                        break;
                }
            }


            if (six != null && five != null && six.size() == 3) {
                if (five.size() > 0) {
                    if (rl53.isRun()) {
                        rl53.shutdownThread(false);
                    }
                } else {
                    if (!rl53.isRun())
                        rl53.run();
                }
            }

            /**
             * Fourd*/
            if (five != null && fourd != null && five.size() == 1) {
                switch (fourd.size()) {
                    case 0:
                        if (!rl43.isRun())
                            rl43.run();
                        break;
                    case 1:
                        if (rl43.isRun())
                            rl43.shutdownThread(false);

                        if (!rl432.isRun())
                            rl432.run();
                        break;
                    case 2:
                        if (rl432.isRun())
                            rl432.shutdownThread(false);

                        if (!rl433.isRun())
                            rl433.run();
                        break;
                    case 3:
                        if (rl433.isRun())
                            rl433.shutdownThread(false);

                        if (!rl434.isRun())
                            rl434.run();
                        break;
                    case 4:
                        if (rl434.isRun())
                            rl434.shutdownThread(false);

                        if (!rl435.isRun())
                            rl435.run();
                        break;
                    case 5:
                        if (rl435.isRun())
                            rl435.shutdownThread(false);

                        if (!rl436.isRun())
                            rl436.run();
                        break;
                    case 6:
                        if (rl436.isRun())
                            rl436.shutdownThread(false);

                        if (!rl437.isRun())
                            rl437.run();
                        break;
                    case 7:
                        if (rl437.isRun())
                            rl437.shutdownThread(false);
                        break;
                }
            }

            if (fourd != null && third != null && fourd.size() == 7) {
                switch (third.size()) {
                    case 0:
                        if (!rl331.isRun())
                            rl331.run();
                        break;
                    case 1:
                        if (rl331.isRun())
                            rl331.shutdownThread(false);

                        if (!rl332.isRun())
                            rl332.run();
                        break;
                    case 2:
                        if (rl332.isRun())
                            rl332.shutdownThread(false);
                        break;
                }
            }
            if (third != null && second != null && third.size() == 2) {
                if (second.size() > 0) {
                    if (rl_second_3.isRun())
                        rl_second_3.shutdownThread(false);
                } else {
                    if (!rl_second_3.isRun())
                        rl_second_3.run();
                }
            }
            if (second != null && first != null && second.size() == 1) {
                if (first.size() > 0) {
                    if (rl_first_3.isRun())
                        rl_first_3.shutdownThread(false);

                } else {
                    if (!rl_first_3.isRun())
                        rl_first_3.run();
                }
            }

            if (first != null && special != null && first.size() == 1) {
                if (special.size() > 0) {
                    if (rl_special_3.isRun())
                        rl_special_3.shutdownThread(true);
                } else {
                    if (!rl_special_3.isRun())
                        rl_special_3.run();
                }
            }
        }
    }


    private void setResultLotteryTable1(String area, List<String> special,
                                        List<String> first,
                                        List<String> second,
                                        List<String> third,
                                        List<String> fourd,
                                        List<String> five,
                                        List<String> six,
                                        List<String> seven,
                                        List<String> eight, BeginResult beginResult) {
        tvResgion_1.setText(area);
        tvLotoTitle1.setText(area);

        if (special.size() > 0) {
            tvDb.setText(special.get(0));
            LotoSpecial_1 = special.get(0).substring(4);
        }

        if (eight.size() > 0)
            tv8_1.setText(eight.get(0));

        if (seven.size() > 0)
            tv7_1.setText(seven.get(0));

        switch (six.size()) {
            case 1:
                tv6_1.setText(six.get(0));
                break;
            case 2:
                tv6_1.setText(six.get(0));
                tv6_2.setText(six.get(1));
                break;
            case 3:
                tv6_1.setText(six.get(0));
                tv6_2.setText(six.get(1));
                tv6_3.setText(six.get(2));
                break;
        }

        if (five.size() > 0)
            tv5_1.setText(five.get(0));

        if (fourd.size() > 0) {

            switch (fourd.size()) {
                case 1:
                    tv4_1.setText(fourd.get(0));
                    break;
                case 2:
                    tv4_1.setText(fourd.get(0));
                    tv4_2.setText(fourd.get(1));
                    break;
                case 3:
                    tv4_1.setText(fourd.get(0));
                    tv4_2.setText(fourd.get(1));
                    tv4_3.setText(fourd.get(2));
                    break;
                case 4:
                    tv4_1.setText(fourd.get(0));
                    tv4_2.setText(fourd.get(1));
                    tv4_3.setText(fourd.get(2));
                    tv4_4.setText(fourd.get(3));
                    break;
                case 5:
                    tv4_1.setText(fourd.get(0));
                    tv4_2.setText(fourd.get(1));
                    tv4_3.setText(fourd.get(2));
                    tv4_4.setText(fourd.get(3));
                    tv4_5.setText(fourd.get(4));
                    break;
                case 6:
                    tv4_1.setText(fourd.get(0));
                    tv4_2.setText(fourd.get(1));
                    tv4_3.setText(fourd.get(2));
                    tv4_4.setText(fourd.get(3));
                    tv4_5.setText(fourd.get(4));
                    tv4_6.setText(fourd.get(5));
                    break;
                case 7:
                    tv4_1.setText(fourd.get(0));
                    tv4_2.setText(fourd.get(1));
                    tv4_3.setText(fourd.get(2));
                    tv4_4.setText(fourd.get(3));
                    tv4_5.setText(fourd.get(4));
                    tv4_6.setText(fourd.get(5));
                    tv4_7.setText(fourd.get(6));
                    break;
            }
        }

        if (third.size() > 0) {
            switch (third.size()) {
                case 1:
                    tv3_1.setText(third.get(0));
                    break;
                case 2:
                    tv3_1.setText(third.get(0));
                    tv3_2.setText(third.get(1));
                    break;
            }
        }
        if (second.size() > 0)
            tv2_1.setText(second.get(0));
        if (first.size() > 0)
            tv1_1.setText(first.get(0));
        /**
         * Dau loto*/
        setBegin1(beginResult);
    }
    private void setResultLotteryTable2(String area, List<String> special,
                                        List<String> first,
                                        List<String> second,
                                        List<String> third,
                                        List<String> fourd,
                                        List<String> five,
                                        List<String> six,
                                        List<String> seven,
                                        List<String> eight, BeginResult beginResult) {

        tvResgion_2.setText(area);
        tvLotoTitle2.setText(area);

        if (special.size() > 0) {
            tvDb_2.setText(special.get(0));
            LotoSpecial_2 = special.get(0).substring(4);
        }

        if (eight.size() > 0)
            tv8_2.setText(eight.get(0));

        if (seven.size() > 0)
            tv7_2.setText(seven.get(0));

        if (six.size() > 0) {
            switch (six.size()) {
                case 1:
                    tv6_1_2.setText(six.get(0));
                    break;
                case 2:
                    tv6_1_2.setText(six.get(0));
                    tv6_2_2.setText(six.get(1));
                    break;
                case 3:
                    tv6_1_2.setText(six.get(0));
                    tv6_2_2.setText(six.get(1));
                    tv6_3_2.setText(six.get(2));
                    break;
            }
        }

        if (five.size() > 0)
            tv5_2.setText(five.get(0));
        if (fourd.size() > 0) {
            switch (fourd.size()) {
                case 1:
                    tv4_1_2.setText(fourd.get(0));
                    break;
                case 2:
                    tv4_1_2.setText(fourd.get(0));
                    tv4_2_2.setText(fourd.get(1));
                    break;
                case 3:
                    tv4_1_2.setText(fourd.get(0));
                    tv4_2_2.setText(fourd.get(1));
                    tv4_3_2.setText(fourd.get(2));
                    break;
                case 4:
                    tv4_1_2.setText(fourd.get(0));
                    tv4_2_2.setText(fourd.get(1));
                    tv4_3_2.setText(fourd.get(2));
                    tv4_4_2.setText(fourd.get(3));
                    break;
                case 5:
                    tv4_1_2.setText(fourd.get(0));
                    tv4_2_2.setText(fourd.get(1));
                    tv4_3_2.setText(fourd.get(2));
                    tv4_4_2.setText(fourd.get(3));
                    tv4_5_2.setText(fourd.get(4));
                    break;
                case 6:
                    tv4_1_2.setText(fourd.get(0));
                    tv4_2_2.setText(fourd.get(1));
                    tv4_3_2.setText(fourd.get(2));
                    tv4_4_2.setText(fourd.get(3));
                    tv4_5_2.setText(fourd.get(4));
                    tv4_6_2.setText(fourd.get(5));
                    break;
                case 7:
                    tv4_1_2.setText(fourd.get(0));
                    tv4_2_2.setText(fourd.get(1));
                    tv4_3_2.setText(fourd.get(2));
                    tv4_4_2.setText(fourd.get(3));
                    tv4_5_2.setText(fourd.get(4));
                    tv4_6_2.setText(fourd.get(5));
                    tv4_7_2.setText(fourd.get(6));
                    break;
            }
        }

        if (third.size() > 0) {
            switch (third.size()) {
                case 1:
                    tv3_1_2.setText(third.get(0));
                    break;
                case 2:
                    tv3_1_2.setText(third.get(0));
                    tv3_2_2.setText(third.get(1));
                    break;
            }
        }
        if (second.size() > 0)
            tv2_2.setText(second.get(0));
        if (first.size() > 0)
            tv1_2.setText(first.get(0));
        /**
         * Dau loto*/
        setBegin2(beginResult);
    }
    private void setResultLotteryTable3(String area, List<String> special,
                                        List<String> first,
                                        List<String> second,
                                        List<String> third,
                                        List<String> fourd,
                                        List<String> five,
                                        List<String> six,
                                        List<String> seven,
                                        List<String> eight, BeginResult beginResult) {

        tvResgion_3.setText(area);
        tvLotoTitle3.setText(area);

        if (special.size() > 0) {
            tvDb_3.setText(special.get(0));
            LotoSpecial_3 = special.get(0).substring(4);
        }

        if (eight.size() > 0)
            tv8_3.setText(eight.get(0));
        if (seven.size() > 0)
            tv7_3.setText(seven.get(0));

        if (six.size() > 0) {
            switch (six.size()) {
                case 1:
                    tv6_1_3.setText(six.get(0));
                    break;
                case 2:
                    tv6_1_3.setText(six.get(0));
                    tv6_2_3.setText(six.get(1));
                    break;
                case 3:
                    tv6_1_3.setText(six.get(0));
                    tv6_2_3.setText(six.get(1));
                    tv6_3_3.setText(six.get(2));
                    break;
            }
        }

        if (five.size() > 0)
            tv5_3.setText(five.get(0));

        if (fourd.size() > 0) {
            switch (fourd.size()) {
                case 1:
                    tv4_1_3.setText(fourd.get(0));
                    break;
                case 2:
                    tv4_1_3.setText(fourd.get(0));
                    tv4_2_3.setText(fourd.get(1));
                    break;
                case 3:
                    tv4_1_3.setText(fourd.get(0));
                    tv4_2_3.setText(fourd.get(1));
                    tv4_3_3.setText(fourd.get(2));
                    break;
                case 4:
                    tv4_1_3.setText(fourd.get(0));
                    tv4_2_3.setText(fourd.get(1));
                    tv4_3_3.setText(fourd.get(2));
                    tv4_4_3.setText(fourd.get(3));
                    break;
                case 5:
                    tv4_1_3.setText(fourd.get(0));
                    tv4_2_3.setText(fourd.get(1));
                    tv4_3_3.setText(fourd.get(2));
                    tv4_4_3.setText(fourd.get(3));
                    tv4_5_3.setText(fourd.get(4));
                    break;
                case 6:
                    tv4_1_3.setText(fourd.get(0));
                    tv4_2_3.setText(fourd.get(1));
                    tv4_3_3.setText(fourd.get(2));
                    tv4_4_3.setText(fourd.get(3));
                    tv4_5_3.setText(fourd.get(4));
                    tv4_6_3.setText(fourd.get(5));
                    break;
                case 7:
                    tv4_1_3.setText(fourd.get(0));
                    tv4_2_3.setText(fourd.get(1));
                    tv4_3_3.setText(fourd.get(2));
                    tv4_4_3.setText(fourd.get(3));
                    tv4_5_3.setText(fourd.get(4));
                    tv4_6_3.setText(fourd.get(5));
                    tv4_7_3.setText(fourd.get(6));
                    break;

            }
        }

        if (third.size() > 0) {
            switch (third.size()) {
                case 1:
                    tv3_1_3.setText(third.get(0));
                    break;
                case 2:
                    tv3_1_3.setText(third.get(0));
                    tv3_2_3.setText(third.get(1));
                    break;
            }
        }

        if (second.size() > 0)
            tv2_3.setText(second.get(0));

        if (first.size() > 0)
            tv1_3.setText(first.get(0));
        /**
         * Dau loto*/
        setBegin3(beginResult);
    }
    private boolean getExistsBegin() {
        return isExistsBegin;
    }

    private void setExistsBegin(boolean isExists) {
        this.isExistsBegin = isExists;
    }

    private boolean getExistsBegin2() {
        return isExistsBegin_2;
    }

    private void setExistsBegin2(boolean isExists) {
        this.isExistsBegin_2 = isExists;
    }

    private boolean getExistsBegin3() {
        return isExistsBegin_3;
    }

    private void setExistsBegin3(boolean isExists) {
        this.isExistsBegin_3 = isExists;
    }

    private boolean checkExitstInBegin1(String s) {
        if (LotoSpecial_1 != null) {
            if (s.equals(LotoSpecial_1) && !getExistsBegin()) {
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

    private boolean checkExitstInBegin2(String s) {
        if (LotoSpecial_2 != null) {
            if (s.equals(LotoSpecial_2) && !getExistsBegin2()) {
                setExistsBegin2(true);
                return true;
            } else {
                return false;
            }
        } else {
            setExistsBegin2(false);
            return false;
        }
    }

    private boolean checkExitstInBegin3(String s) {
        if (LotoSpecial_3 != null) {
            if (s.equals(LotoSpecial_3) && !getExistsBegin3()) {
                setExistsBegin3(true);
                return true;
            } else {
                return false;
            }
        } else {
            setExistsBegin3(false);
            return false;
        }
    }

    public void setBegin1(BeginResult beginResult) {
        isExistsBegin = false;
        Log.e(TAG, "setBegin1: go to.....");
        if (beginResult != null) {
            if (beginResult.getB0().size() > 0) {
                String begin_0 = "";
                for (int i = 0; i < beginResult.getB0().size(); i++) {
                    if (checkExitstInBegin1(beginResult.getB0().get(i))) {
                        begin_0 += "<font color='red'>" + beginResult.getB0().get(i) + "</font>" + " - ";
                    } else {
                        begin_0 += beginResult.getB0().get(i) + " - ";
                    }
                }
                if (begin_0.length() > 0) {
                    begin_0 = begin_0.substring(0, begin_0.length() - 3);
                }
                tvLoto0_1.setText(Html.fromHtml(begin_0));
            }


            if (beginResult.getB1().size() > 0) {
                String begin_1 = "";
                for (int i = 0; i < beginResult.getB1().size(); i++) {
                    if (checkExitstInBegin1(beginResult.getB1().get(i))) {
                        begin_1 += "<font color='red'>" + beginResult.getB1().get(i) + "</font>" + " - ";
                    } else {
                        begin_1 += beginResult.getB1().get(i) + " - ";
                    }
                }
                if (begin_1.length() > 0) {
                    begin_1 = begin_1.substring(0, begin_1.length() - 2);
                }
                tvLoto1_1.setText(Html.fromHtml(begin_1));
            }

            if (beginResult.getB2().size() > 0) {
                String begin_2 = "";
                for (int i = 0; i < beginResult.getB2().size(); i++) {
                    if (checkExitstInBegin1(beginResult.getB2().get(i))) {
                        begin_2 += "<font color='red'>" + beginResult.getB2().get(i) + "</font>" + " - ";
                    } else {
                        begin_2 += beginResult.getB2().get(i) + " - ";
                    }
                }
                if (begin_2.length() > 0) {
                    begin_2 = begin_2.substring(0, begin_2.length() - 2);
                }
                tvLoto2_1.setText(Html.fromHtml(begin_2));
            }

            if (beginResult.getB3().size() > 0) {
                String begin_3 = "";
                for (int i = 0; i < beginResult.getB3().size(); i++) {
                    if (checkExitstInBegin1(beginResult.getB3().get(i))) {
                        begin_3 += "<font color='red'>" + beginResult.getB3().get(i) + "</font>" + " - ";
                    } else {
                        begin_3 += beginResult.getB3().get(i) + " - ";
                    }
                }
                if (begin_3.length() > 0) {
                    begin_3 = begin_3.substring(0, begin_3.length() - 2);
                }
                tvLoto3_1.setText(Html.fromHtml(begin_3));
            }

            if (beginResult.getB4().size() > 0) {
                String begin_4 = "";
                for (int i = 0; i < beginResult.getB4().size(); i++) {
                    if (checkExitstInBegin1(beginResult.getB4().get(i))) {
                        begin_4 += "<font color='red'>" + beginResult.getB4().get(i) + "</font>" + " - ";
                    } else {
                        begin_4 += beginResult.getB4().get(i) + " - ";
                    }
                }
                if (begin_4.length() > 0) {
                    begin_4 = begin_4.substring(0, begin_4.length() - 2);
                }

                tvLoto4_1.setText(Html.fromHtml(begin_4));
            }

            if (beginResult.getB5().size() > 0) {
                String begin_5 = "";
                for (int i = 0; i < beginResult.getB5().size(); i++) {
                    if (checkExitstInBegin1(beginResult.getB5().get(i))) {
                        begin_5 += "<font color='red'>" + beginResult.getB5().get(i) + "</font>" + " - ";
                    } else {
                        begin_5 += beginResult.getB5().get(i) + " - ";
                    }
                }
                if (begin_5.length() > 0) {
                    begin_5 = begin_5.substring(0, begin_5.length() - 2);
                }
                tvLoto5_1.setText(Html.fromHtml(begin_5));
            }

            if (beginResult.getB6().size() > 0) {
                String begin_6 = "";
                for (int i = 0; i < beginResult.getB6().size(); i++) {
                    if (checkExitstInBegin1(beginResult.getB6().get(i))) {
                        begin_6 += "<font color='red'>" + beginResult.getB6().get(i) + "</font>" + " - ";
                    } else {
                        begin_6 += beginResult.getB6().get(i) + " - ";
                    }
                }
                if (begin_6.length() > 0) {
                    begin_6 = begin_6.substring(0, begin_6.length() - 2);
                }
                tvLoto6_1.setText(Html.fromHtml(begin_6));
            }

            if (beginResult.getB7().size() > 0) {
                String begin_7 = "";
                for (int i = 0; i < beginResult.getB7().size(); i++) {
                    if (checkExitstInBegin1(beginResult.getB7().get(i))) {
                        begin_7 += "<font color='red'>" + beginResult.getB7().get(i) + "</font>" + " - ";
                    } else {
                        begin_7 += beginResult.getB7().get(i) + " - ";
                    }
                }
                if (begin_7.length() > 0) {
                    begin_7 = begin_7.substring(0, begin_7.length() - 2);
                }

                tvLoto7_1.setText(Html.fromHtml(begin_7));
            }

            if (beginResult.getB8().size() > 0) {
                String begin_8 = "";
                for (int i = 0; i < beginResult.getB8().size(); i++) {
                    if (checkExitstInBegin1(beginResult.getB8().get(i))) {
                        begin_8 += "<font color='red'>" + beginResult.getB8().get(i) + "</font>" + " - ";
                    } else {
                        begin_8 += beginResult.getB8().get(i) + " - ";
                    }
                }
                if (begin_8.length() > 0) {
                    begin_8 = begin_8.substring(0, begin_8.length() - 2);
                }
                tvLoto8_1.setText(Html.fromHtml(begin_8));
            }

            if (beginResult.getB9().size() > 0) {
                String begin_9 = "";
                for (int i = 0; i < beginResult.getB9().size(); i++) {
                    if (checkExitstInBegin1(beginResult.getB9().get(i))) {
                        begin_9 += "<font color='red'>" + beginResult.getB9().get(i) + "</font>" + " - ";
                    } else {
                        begin_9 += beginResult.getB9().get(i) + " - ";
                    }
                }
                if (begin_9.length() > 0) {
                    begin_9 = begin_9.substring(0, begin_9.length() - 2);
                }
                tvLoto9_1.setText(Html.fromHtml(begin_9));
            }

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
                    TextUtils.getInstance().setAnimationTextView(tvLoto0_1);
                } else {
                    if (tmp_b0.length() < begin_0.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto0_1);
                        tmp_b0 = begin_0;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 0 - " + begin_0);
            tvLoto0_1.setText(Html.fromHtml(begin_0));
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
                    TextUtils.getInstance().setAnimationTextView(tvLoto1_1);
                } else {
                    if (tmp_b1.length() < begin_1.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto1_1);
                        tmp_b1 = begin_1;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 1 - " + begin_1);
            tvLoto1_1.setText(Html.fromHtml(begin_1));
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
                    TextUtils.getInstance().setAnimationTextView(tvLoto2_1);
                } else {
                    if (tmp_b2.length() < begin_2.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto2_1);
                        tmp_b2 = begin_2;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 2 - " + begin_2);
            tvLoto2_1.setText(Html.fromHtml(begin_2));
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
                    TextUtils.getInstance().setAnimationTextView(tvLoto3_1);
                } else {
                    if (tmp_b3.length() < begin_3.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto3_1);
                        tmp_b3 = begin_3;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 3 - " + begin_3);
            tvLoto3_1.setText(Html.fromHtml(begin_3));
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
                    TextUtils.getInstance().setAnimationTextView(tvLoto4_1);
                } else {
                    if (tmp_b4.length() < begin_4.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto4_1);
                        tmp_b4 = begin_4;
                    }
                }
            }
            tvLoto4_1.setText(Html.fromHtml(begin_4));
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
                    TextUtils.getInstance().setAnimationTextView(tvLoto5_1);
                } else {
                    if (tmp_b5.length() < begin_5.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto5_1);
                        tmp_b5 = begin_5;
                    }
                }
            }

            tvLoto5_1.setText(Html.fromHtml(begin_5));
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
                    TextUtils.getInstance().setAnimationTextView(tvLoto6_1);
                } else {
                    if (tmp_b6.length() < begin_6.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto6_1);
                        tmp_b6 = begin_6;
                    }
                }
            }
            tvLoto6_1.setText(Html.fromHtml(begin_6));
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
                    TextUtils.getInstance().setAnimationTextView(tvLoto7_1);
                } else {
                    if (tmp_b7.length() < begin_7.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto7_1);
                        tmp_b7 = begin_7;
                    }
                }
            }

            tvLoto7_1.setText(Html.fromHtml(begin_7));
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
                    TextUtils.getInstance().setAnimationTextView(tvLoto8_1);
                } else {
                    if (tmp_b8.length() < begin_8.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto8_1);
                        tmp_b8 = begin_8;
                    }
                }
            }
            tvLoto8_1.setText(Html.fromHtml(begin_8));
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
                    TextUtils.getInstance().setAnimationTextView(tvLoto9_1);
                } else {
                    if (tmp_b9.length() < begin_9.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto9_1);
                        tmp_b9 = begin_9;
                    }
                }
            }
            tvLoto9_1.setText(Html.fromHtml(begin_9));
        }
    }

    public void setBegin2(BeginResult beginResult) {
        isExistsBegin_2 = false;
        Log.e(TAG, "setBegin2: go to.....");
        if (beginResult != null) {
            if (beginResult.getB0().size() > 0) {
                String begin_0 = "";
                for (int i = 0; i < beginResult.getB0().size(); i++) {
                    if (checkExitstInBegin2(beginResult.getB0().get(i))) {
                        begin_0 += "<font color='red'>" + beginResult.getB0().get(i) + "</font>" + " - ";
                        Log.e(TAG, "setBegin2: " + begin_0);
                    } else {
                        begin_0 += beginResult.getB0().get(i) + " - ";
                    }

                }
                if (begin_0.length() > 0) {
                    begin_0 = begin_0.substring(0, begin_0.length() - 3);
                }
                Log.e(TAG, "setBegin2: " + begin_0);
                tvLoto0_2.setText(Html.fromHtml(begin_0));
            }


            if (beginResult.getB1().size() > 0) {
                String begin_1 = "";
                for (int i = 0; i < beginResult.getB1().size(); i++) {
                    if (checkExitstInBegin2(beginResult.getB1().get(i))) {
                        begin_1 += "<font color='red'>" + beginResult.getB1().get(i) + "</font>" + " - ";
                    } else {
                        begin_1 += beginResult.getB1().get(i) + " - ";
                    }
                }
                if (begin_1.length() > 0) {
                    begin_1 = begin_1.substring(0, begin_1.length() - 2);
                }
                tvLoto1_2.setText(Html.fromHtml(begin_1));
            }

            if (beginResult.getB2().size() > 0) {
                String begin_2 = "";
                for (int i = 0; i < beginResult.getB2().size(); i++) {
                    if (checkExitstInBegin2(beginResult.getB2().get(i))) {
                        begin_2 += "<font color='red'>" + beginResult.getB2().get(i) + "</font>" + " - ";
                    } else {
                        begin_2 += beginResult.getB2().get(i) + " - ";
                    }
                }
                if (begin_2.length() > 0) {
                    begin_2 = begin_2.substring(0, begin_2.length() - 2);
                }
                tvLoto2_2.setText(Html.fromHtml(begin_2));
            }

            if (beginResult.getB3().size() > 0) {
                String begin_3 = "";
                for (int i = 0; i < beginResult.getB3().size(); i++) {
                    if (checkExitstInBegin2(beginResult.getB3().get(i))) {
                        begin_3 += "<font color='red'>" + beginResult.getB3().get(i) + "</font>" + " - ";
                    } else {
                        begin_3 += beginResult.getB3().get(i) + " - ";
                    }
                }
                if (begin_3.length() > 0) {
                    begin_3 = begin_3.substring(0, begin_3.length() - 2);
                }
                tvLoto3_2.setText(Html.fromHtml(begin_3));
            }

            if (beginResult.getB4().size() > 0) {
                String begin_4 = "";
                for (int i = 0; i < beginResult.getB4().size(); i++) {
                    if (checkExitstInBegin2(beginResult.getB4().get(i))) {
                        begin_4 += "<font color='red'>" + beginResult.getB4().get(i) + "</font>" + " - ";
                    } else {
                        begin_4 += beginResult.getB4().get(i) + " - ";
                    }
                }
                if (begin_4.length() > 0) {
                    begin_4 = begin_4.substring(0, begin_4.length() - 2);
                }

                tvLoto4_2.setText(Html.fromHtml(begin_4));
            }

            if (beginResult.getB5().size() > 0) {
                String begin_5 = "";
                for (int i = 0; i < beginResult.getB5().size(); i++) {
                    if (checkExitstInBegin2(beginResult.getB5().get(i))) {
                        begin_5 += "<font color='red'>" + beginResult.getB5().get(i) + "</font>" + " - ";
                    } else {
                        begin_5 += beginResult.getB5().get(i) + " - ";
                    }
                }
                if (begin_5.length() > 0) {
                    begin_5 = begin_5.substring(0, begin_5.length() - 2);
                }
                tvLoto5_2.setText(Html.fromHtml(begin_5));
            }

            if (beginResult.getB6().size() > 0) {
                String begin_6 = "";
                for (int i = 0; i < beginResult.getB6().size(); i++) {
                    if (checkExitstInBegin2(beginResult.getB6().get(i))) {
                        begin_6 += "<font color='red'>" + beginResult.getB6().get(i) + "</font>" + " - ";
                    } else {
                        begin_6 += beginResult.getB6().get(i) + " - ";
                    }
                }
                if (begin_6.length() > 0) {
                    begin_6 = begin_6.substring(0, begin_6.length() - 2);
                }
                tvLoto6_2.setText(Html.fromHtml(begin_6));
            }

            if (beginResult.getB7().size() > 0) {
                String begin_7 = "";
                for (int i = 0; i < beginResult.getB7().size(); i++) {
                    if (checkExitstInBegin2(beginResult.getB7().get(i))) {
                        begin_7 += "<font color='red'>" + beginResult.getB7().get(i) + "</font>" + " - ";
                    } else {
                        begin_7 += beginResult.getB7().get(i) + " - ";
                    }
                }
                if (begin_7.length() > 0) {
                    begin_7 = begin_7.substring(0, begin_7.length() - 2);
                }

                tvLoto7_2.setText(Html.fromHtml(begin_7));
            }

            if (beginResult.getB8().size() > 0) {
                String begin_8 = "";
                for (int i = 0; i < beginResult.getB8().size(); i++) {
                    if (checkExitstInBegin2(beginResult.getB8().get(i))) {
                        begin_8 += "<font color='red'>" + beginResult.getB8().get(i) + "</font>" + " - ";
                    } else {
                        begin_8 += beginResult.getB8().get(i) + " - ";
                    }
                }
                if (begin_8.length() > 0) {
                    begin_8 = begin_8.substring(0, begin_8.length() - 2);
                }
                tvLoto8_2.setText(Html.fromHtml(begin_8));
            }

            if (beginResult.getB9().size() > 0) {
                String begin_9 = "";
                for (int i = 0; i < beginResult.getB9().size(); i++) {
                    if (checkExitstInBegin2(beginResult.getB9().get(i))) {
                        begin_9 += "<font color='red'>" + beginResult.getB9().get(i) + "</font>" + " - ";
                    } else {
                        begin_9 += beginResult.getB9().get(i) + " - ";
                    }
                }
                if (begin_9.length() > 0) {
                    begin_9 = begin_9.substring(0, begin_9.length() - 2);
                }
                tvLoto9_2.setText(Html.fromHtml(begin_9));
            }
        }
    }

    private void setBeginLive2(BeginResult beginResult) {
        /**
         * Dau loto*/
        if (beginResult.getB0().size() > 0) {
            String begin_0 = "";
            for (int i = 0; i < beginResult.getB0().size(); i++) {
                begin_0 += beginResult.getB0().get(i) + " - ";
            }
            if (begin_0.length() > 0) {
                begin_0 = begin_0.substring(0, begin_0.length() - 3);
                if (android.text.TextUtils.isEmpty(tmp1_b0)) {
                    tmp1_b0 = begin_0;
                    TextUtils.getInstance().setAnimationTextView(tvLoto0_2);
                } else {
                    if (tmp1_b0.length() < begin_0.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto0_2);
                        tmp1_b0 = begin_0;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 0 - " + begin_0);
            tvLoto0_2.setText(Html.fromHtml(begin_0));
        }


        if (beginResult.getB1().size() > 0) {
            String begin_1 = "";
            for (int i = 0; i < beginResult.getB1().size(); i++) {
                begin_1 += beginResult.getB1().get(i) + " - ";
            }
            if (begin_1.length() > 0) {
                begin_1 = begin_1.substring(0, begin_1.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp1_b1)) {
                    tmp1_b1 = begin_1;
                    TextUtils.getInstance().setAnimationTextView(tvLoto1_2);
                } else {
                    if (tmp1_b1.length() < begin_1.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto1_2);
                        tmp1_b1 = begin_1;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 1 - " + begin_1);
            tvLoto1_2.setText(Html.fromHtml(begin_1));
        }

        if (beginResult.getB2().size() > 0) {
            String begin_2 = "";
            for (int i = 0; i < beginResult.getB2().size(); i++) {
                begin_2 += beginResult.getB2().get(i) + " - ";
            }
            if (begin_2.length() > 0) {
                begin_2 = begin_2.substring(0, begin_2.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp1_b2)) {
                    tmp1_b2 = begin_2;
                    TextUtils.getInstance().setAnimationTextView(tvLoto2_2);
                } else {
                    if (tmp1_b2.length() < begin_2.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto2_2);
                        tmp1_b2 = begin_2;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 2 - " + begin_2);
            tvLoto2_2.setText(Html.fromHtml(begin_2));
        }

        if (beginResult.getB3().size() > 0) {
            String begin_3 = "";
            for (int i = 0; i < beginResult.getB3().size(); i++) {
                begin_3 += beginResult.getB3().get(i) + " - ";
            }
            if (begin_3.length() > 0) {
                begin_3 = begin_3.substring(0, begin_3.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp1_b3)) {
                    tmp1_b3 = begin_3;
                    TextUtils.getInstance().setAnimationTextView(tvLoto3_2);
                } else {
                    if (tmp1_b3.length() < begin_3.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto3_2);
                        tmp1_b3 = begin_3;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 3 - " + begin_3);
            tvLoto3_2.setText(Html.fromHtml(begin_3));
        }

        if (beginResult.getB4().size() > 0) {
            String begin_4 = "";
            for (int i = 0; i < beginResult.getB4().size(); i++) {
                begin_4 += beginResult.getB4().get(i) + " - ";
            }
            if (begin_4.length() > 0) {
                begin_4 = begin_4.substring(0, begin_4.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp1_b4)) {
                    tmp1_b4 = begin_4;
                    TextUtils.getInstance().setAnimationTextView(tvLoto4_2);
                } else {
                    if (tmp1_b4.length() < begin_4.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto4_2);
                        tmp1_b4 = begin_4;
                    }
                }
            }
            tvLoto4_2.setText(Html.fromHtml(begin_4));
        }


        if (beginResult.getB5().size() > 0) {
            String begin_5 = "";
            for (int i = 0; i < beginResult.getB5().size(); i++) {
                begin_5 += beginResult.getB5().get(i) + " - ";
            }
            if (begin_5.length() > 0) {
                begin_5 = begin_5.substring(0, begin_5.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp1_b5)) {
                    tmp1_b5 = begin_5;
                    TextUtils.getInstance().setAnimationTextView(tvLoto5_2);
                } else {
                    if (tmp1_b5.length() < begin_5.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto5_2);
                        tmp1_b5 = begin_5;
                    }
                }
            }

            tvLoto5_2.setText(Html.fromHtml(begin_5));
        }

        if (beginResult.getB6().size() > 0) {
            String begin_6 = "";
            for (int i = 0; i < beginResult.getB6().size(); i++) {
                begin_6 += beginResult.getB6().get(i) + " - ";
            }
            if (begin_6.length() > 0) {
                begin_6 = begin_6.substring(0, begin_6.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp1_b6)) {
                    tmp1_b6 = begin_6;
                    TextUtils.getInstance().setAnimationTextView(tvLoto6_2);
                } else {
                    if (tmp1_b6.length() < begin_6.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto6_2);
                        tmp1_b6 = begin_6;
                    }
                }
            }
            tvLoto6_2.setText(Html.fromHtml(begin_6));
        }

        if (beginResult.getB7().size() > 0) {
            String begin_7 = "";
            for (int i = 0; i < beginResult.getB7().size(); i++) {
                begin_7 += beginResult.getB7().get(i) + " - ";
            }
            if (begin_7.length() > 0) {
                begin_7 = begin_7.substring(0, begin_7.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp1_b7)) {
                    tmp1_b7 = begin_7;
                    TextUtils.getInstance().setAnimationTextView(tvLoto7_2);
                } else {
                    if (tmp1_b7.length() < begin_7.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto7_2);
                        tmp1_b7 = begin_7;
                    }
                }
            }

            tvLoto7_2.setText(Html.fromHtml(begin_7));
        }

        if (beginResult.getB8().size() > 0) {
            String begin_8 = "";
            for (int i = 0; i < beginResult.getB8().size(); i++) {
                begin_8 += beginResult.getB8().get(i) + " - ";
            }
            if (begin_8.length() > 0) {
                begin_8 = begin_8.substring(0, begin_8.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp1_b8)) {
                    tmp1_b8 = begin_8;
                    TextUtils.getInstance().setAnimationTextView(tvLoto8_2);
                } else {
                    if (tmp1_b8.length() < begin_8.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto8_2);
                        tmp1_b8 = begin_8;
                    }
                }
            }
            tvLoto8_2.setText(Html.fromHtml(begin_8));
        }

        if (beginResult.getB9().size() > 0) {
            String begin_9 = "";
            for (int i = 0; i < beginResult.getB9().size(); i++) {
                begin_9 += beginResult.getB9().get(i) + " - ";
            }
            if (begin_9.length() > 0) {
                begin_9 = begin_9.substring(0, begin_9.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp1_b9)) {
                    tmp1_b9 = begin_9;
                    TextUtils.getInstance().setAnimationTextView(tvLoto9_2);
                } else {
                    if (tmp1_b9.length() < begin_9.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto9_2);
                        tmp1_b9 = begin_9;
                    }
                }
            }
            tvLoto9_2.setText(Html.fromHtml(begin_9));
        }
    }

    public void setBegin3(BeginResult beginResult) {
        isExistsBegin_3 = false;
        Log.e(TAG, "setBegin3: go to.....");
        if (beginResult != null) {
            if (beginResult.getB0().size() > 0) {
                String begin_0 = "";
                for (int i = 0; i < beginResult.getB0().size(); i++) {
                    if (checkExitstInBegin3(beginResult.getB0().get(i))) {
                        begin_0 += "<font color='red'>" + beginResult.getB0().get(i) + "</font>" + " - ";
                    } else {
                        begin_0 += beginResult.getB0().get(i) + " - ";
                    }
                }
                if (begin_0.length() > 0) {
                    begin_0 = begin_0.substring(0, begin_0.length() - 3);
                }
                tvLoto0_3.setText(Html.fromHtml(begin_0));
            }


            if (beginResult.getB1().size() > 0) {
                String begin_1 = "";
                for (int i = 0; i < beginResult.getB1().size(); i++) {
                    if (checkExitstInBegin3(beginResult.getB1().get(i))) {
                        begin_1 += "<font color='red'>" + beginResult.getB1().get(i) + "</font>" + " - ";
                    } else {
                        begin_1 += beginResult.getB1().get(i) + " - ";
                    }
                }
                if (begin_1.length() > 0) {
                    begin_1 = begin_1.substring(0, begin_1.length() - 2);
                }
                tvLoto1_3.setText(Html.fromHtml(begin_1));
            }

            if (beginResult.getB2().size() > 0) {
                String begin_2 = "";
                for (int i = 0; i < beginResult.getB2().size(); i++) {
                    if (checkExitstInBegin3(beginResult.getB2().get(i))) {
                        begin_2 += "<font color='red'>" + beginResult.getB2().get(i) + "</font>" + " - ";
                    } else {
                        begin_2 += beginResult.getB2().get(i) + " - ";
                    }
                }
                if (begin_2.length() > 0) {
                    begin_2 = begin_2.substring(0, begin_2.length() - 2);
                }
                tvLoto2_3.setText(Html.fromHtml(begin_2));
            }

            if (beginResult.getB3().size() > 0) {
                String begin_3 = "";
                for (int i = 0; i < beginResult.getB3().size(); i++) {
                    if (checkExitstInBegin3(beginResult.getB3().get(i))) {
                        begin_3 += "<font color='red'>" + beginResult.getB3().get(i) + "</font>" + " - ";
                    } else {
                        begin_3 += beginResult.getB3().get(i) + " - ";
                    }
                }
                if (begin_3.length() > 0) {
                    begin_3 = begin_3.substring(0, begin_3.length() - 2);
                }
                tvLoto3_3.setText(Html.fromHtml(begin_3));
            }

            if (beginResult.getB4().size() > 0) {
                String begin_4 = "";
                for (int i = 0; i < beginResult.getB4().size(); i++) {
                    if (checkExitstInBegin3(beginResult.getB4().get(i))) {
                        begin_4 += "<font color='red'>" + beginResult.getB4().get(i) + "</font>" + " - ";
                    } else {
                        begin_4 += beginResult.getB4().get(i) + " - ";
                    }
                }
                if (begin_4.length() > 0) {
                    begin_4 = begin_4.substring(0, begin_4.length() - 2);
                }

                tvLoto4_3.setText(Html.fromHtml(begin_4));
            }

            if (beginResult.getB5().size() > 0) {
                String begin_5 = "";
                for (int i = 0; i < beginResult.getB5().size(); i++) {
                    if (checkExitstInBegin3(beginResult.getB5().get(i))) {
                        begin_5 += "<font color='red'>" + beginResult.getB5().get(i) + "</font>" + " - ";
                    } else {
                        begin_5 += beginResult.getB5().get(i) + " - ";
                    }
                }
                if (begin_5.length() > 0) {
                    begin_5 = begin_5.substring(0, begin_5.length() - 2);
                }
                tvLoto5_3.setText(Html.fromHtml(begin_5));
            }

            if (beginResult.getB6().size() > 0) {
                String begin_6 = "";
                for (int i = 0; i < beginResult.getB6().size(); i++) {
                    if (checkExitstInBegin3(beginResult.getB6().get(i))) {
                        begin_6 += "<font color='red'>" + beginResult.getB6().get(i) + "</font>" + " - ";
                    } else {
                        begin_6 += beginResult.getB6().get(i) + " - ";
                    }
                }
                if (begin_6.length() > 0) {
                    begin_6 = begin_6.substring(0, begin_6.length() - 2);
                }
                tvLoto6_3.setText(Html.fromHtml(begin_6));
            }

            if (beginResult.getB7().size() > 0) {
                String begin_7 = "";
                for (int i = 0; i < beginResult.getB7().size(); i++) {
                    if (checkExitstInBegin3(beginResult.getB7().get(i))) {
                        begin_7 += "<font color='red'>" + beginResult.getB7().get(i) + "</font>" + " - ";
                    } else {
                        begin_7 += beginResult.getB7().get(i) + " - ";
                    }
                }
                if (begin_7.length() > 0) {
                    begin_7 = begin_7.substring(0, begin_7.length() - 2);
                }

                tvLoto7_3.setText(Html.fromHtml(begin_7));
            }

            if (beginResult.getB8().size() > 0) {
                String begin_8 = "";
                for (int i = 0; i < beginResult.getB8().size(); i++) {
                    if (checkExitstInBegin3(beginResult.getB8().get(i))) {
                        begin_8 += "<font color='red'>" + beginResult.getB8().get(i) + "</font>" + " - ";
                    } else {
                        begin_8 += beginResult.getB8().get(i) + " - ";
                    }
                }
                if (begin_8.length() > 0) {
                    begin_8 = begin_8.substring(0, begin_8.length() - 2);
                }
                tvLoto8_3.setText(Html.fromHtml(begin_8));
            }

            if (beginResult.getB9().size() > 0) {
                String begin_9 = "";
                for (int i = 0; i < beginResult.getB9().size(); i++) {
                    if (checkExitstInBegin3(beginResult.getB9().get(i))) {
                        begin_9 += "<font color='red'>" + beginResult.getB9().get(i) + "</font>" + " - ";
                    } else {
                        begin_9 += beginResult.getB9().get(i) + " - ";
                    }
                }
                if (begin_9.length() > 0) {
                    begin_9 = begin_9.substring(0, begin_9.length() - 2);
                }
                tvLoto9_3.setText(Html.fromHtml(begin_9));
            }
        }
    }

    private void setBeginLive3(BeginResult beginResult) {
        /**
         * Dau loto*/
        if (beginResult.getB0().size() > 0) {
            String begin_0 = "";
            for (int i = 0; i < beginResult.getB0().size(); i++) {
                begin_0 += beginResult.getB0().get(i) + " - ";
            }
            if (begin_0.length() > 0) {
                begin_0 = begin_0.substring(0, begin_0.length() - 3);
                if (android.text.TextUtils.isEmpty(tmp1_b0)) {
                    tmp2_b0 = begin_0;
                    TextUtils.getInstance().setAnimationTextView(tvLoto0_3);
                } else {
                    if (tmp2_b0.length() < begin_0.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto0_3);
                        tmp2_b0 = begin_0;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 0 - " + begin_0);
            tvLoto0_3.setText(Html.fromHtml(begin_0));
        }


        if (beginResult.getB1().size() > 0) {
            String begin_1 = "";
            for (int i = 0; i < beginResult.getB1().size(); i++) {
                begin_1 += beginResult.getB1().get(i) + " - ";
            }
            if (begin_1.length() > 0) {
                begin_1 = begin_1.substring(0, begin_1.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp2_b1)) {
                    tmp2_b1 = begin_1;
                    TextUtils.getInstance().setAnimationTextView(tvLoto1_3);
                } else {
                    if (tmp2_b1.length() < begin_1.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto1_3);
                        tmp2_b1 = begin_1;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 1 - " + begin_1);
            tvLoto1_3.setText(Html.fromHtml(begin_1));
        }

        if (beginResult.getB2().size() > 0) {
            String begin_2 = "";
            for (int i = 0; i < beginResult.getB2().size(); i++) {
                begin_2 += beginResult.getB2().get(i) + " - ";
            }
            if (begin_2.length() > 0) {
                begin_2 = begin_2.substring(0, begin_2.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp2_b2)) {
                    tmp2_b2 = begin_2;
                    TextUtils.getInstance().setAnimationTextView(tvLoto2_3);
                } else {
                    if (tmp2_b2.length() < begin_2.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto2_3);
                        tmp2_b2 = begin_2;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 2 - " + begin_2);
            tvLoto2_3.setText(Html.fromHtml(begin_2));
        }

        if (beginResult.getB3().size() > 0) {
            String begin_3 = "";
            for (int i = 0; i < beginResult.getB3().size(); i++) {
                begin_3 += beginResult.getB3().get(i) + " - ";
            }
            if (begin_3.length() > 0) {
                begin_3 = begin_3.substring(0, begin_3.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp2_b3)) {
                    tmp2_b3 = begin_3;
                    TextUtils.getInstance().setAnimationTextView(tvLoto3_3);
                } else {
                    if (tmp2_b3.length() < begin_3.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto3_3);
                        tmp2_b3 = begin_3;
                    }
                }
            }
            Log.e(TAG, "setBeginEndLoto: 3 - " + begin_3);
            tvLoto3_3.setText(Html.fromHtml(begin_3));
        }

        if (beginResult.getB4().size() > 0) {
            String begin_4 = "";
            for (int i = 0; i < beginResult.getB4().size(); i++) {
                begin_4 += beginResult.getB4().get(i) + " - ";
            }
            if (begin_4.length() > 0) {
                begin_4 = begin_4.substring(0, begin_4.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp2_b4)) {
                    tmp2_b4 = begin_4;
                    TextUtils.getInstance().setAnimationTextView(tvLoto4_3);
                } else {
                    if (tmp2_b4.length() < begin_4.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto4_3);
                        tmp2_b4 = begin_4;
                    }
                }
            }
            tvLoto4_3.setText(Html.fromHtml(begin_4));
        }


        if (beginResult.getB5().size() > 0) {
            String begin_5 = "";
            for (int i = 0; i < beginResult.getB5().size(); i++) {
                begin_5 += beginResult.getB5().get(i) + " - ";
            }
            if (begin_5.length() > 0) {
                begin_5 = begin_5.substring(0, begin_5.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp2_b5)) {
                    tmp2_b5 = begin_5;
                    TextUtils.getInstance().setAnimationTextView(tvLoto5_3);
                } else {
                    if (tmp2_b5.length() < begin_5.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto5_3);
                        tmp2_b5 = begin_5;
                    }
                }
            }

            tvLoto5_3.setText(Html.fromHtml(begin_5));
        }

        if (beginResult.getB6().size() > 0) {
            String begin_6 = "";
            for (int i = 0; i < beginResult.getB6().size(); i++) {
                begin_6 += beginResult.getB6().get(i) + " - ";
            }
            if (begin_6.length() > 0) {
                begin_6 = begin_6.substring(0, begin_6.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp2_b6)) {
                    tmp2_b6 = begin_6;
                    TextUtils.getInstance().setAnimationTextView(tvLoto6_3);
                } else {
                    if (tmp2_b6.length() < begin_6.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto6_3);
                        tmp2_b6 = begin_6;
                    }
                }
            }
            tvLoto6_3.setText(Html.fromHtml(begin_6));
        }

        if (beginResult.getB7().size() > 0) {
            String begin_7 = "";
            for (int i = 0; i < beginResult.getB7().size(); i++) {
                begin_7 += beginResult.getB7().get(i) + " - ";
            }
            if (begin_7.length() > 0) {
                begin_7 = begin_7.substring(0, begin_7.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp2_b7)) {
                    tmp2_b7 = begin_7;
                    TextUtils.getInstance().setAnimationTextView(tvLoto7_3);
                } else {
                    if (tmp2_b7.length() < begin_7.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto7_3);
                        tmp2_b7 = begin_7;
                    }
                }
            }

            tvLoto7_3.setText(Html.fromHtml(begin_7));
        }

        if (beginResult.getB8().size() > 0) {
            String begin_8 = "";
            for (int i = 0; i < beginResult.getB8().size(); i++) {
                begin_8 += beginResult.getB8().get(i) + " - ";
            }
            if (begin_8.length() > 0) {
                begin_8 = begin_8.substring(0, begin_8.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp2_b8)) {
                    tmp2_b8 = begin_8;
                    TextUtils.getInstance().setAnimationTextView(tvLoto8_3);
                } else {
                    if (tmp2_b8.length() < begin_8.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto8_3);
                        tmp2_b8 = begin_8;
                    }
                }
            }
            tvLoto8_3.setText(Html.fromHtml(begin_8));
        }

        if (beginResult.getB9().size() > 0) {
            String begin_9 = "";
            for (int i = 0; i < beginResult.getB9().size(); i++) {
                begin_9 += beginResult.getB9().get(i) + " - ";
            }
            if (begin_9.length() > 0) {
                begin_9 = begin_9.substring(0, begin_9.length() - 3);

                if (android.text.TextUtils.isEmpty(tmp2_b9)) {
                    tmp2_b9 = begin_9;
                    TextUtils.getInstance().setAnimationTextView(tvLoto9_3);
                } else {
                    if (tmp2_b9.length() < begin_9.length()) {
                        TextUtils.getInstance().setAnimationTextView(tvLoto9_3);
                        tmp2_b9 = begin_9;
                    }
                }
            }
            tvLoto9_3.setText(Html.fromHtml(begin_9));
        }
    }

    @Override
    public void getResultLotteryError(String error) {
        loadingView.setVisibility(View.GONE);
        tvContent.setText(error);
        tvContent.setVisibility(View.VISIBLE);
        setVisibleTable(false);
    }

    @Override
    public void setTableRegion1(ResultLottery data) {
        setResultLotteryTable1(data.getArea(),
                data.getRes_special(),
                data.getRes_first(),
                data.getRes_second(),
                data.getRes_third(),
                data.getRes_fourth(),
                data.getRes_fifth(),
                data.getRes_sixth(),
                data.getRes_seventh(), data.getRes_eight(), data.getBegin_with());
    }

    @Override
    public void setTableRegion2(ResultLottery data) {
        setResultLotteryTable2(data.getArea(),
                data.getRes_special(),
                data.getRes_first(),
                data.getRes_second(),
                data.getRes_third(),
                data.getRes_fourth(),
                data.getRes_fifth(),
                data.getRes_sixth(),
                data.getRes_seventh(), data.getRes_eight(), data.getBegin_with());
    }

    @Override
    public void setTableRegion3(ResultLottery data) {
        setResultLotteryTable3(data.getArea(),
                data.getRes_special(),
                data.getRes_first(),
                data.getRes_second(),
                data.getRes_third(),
                data.getRes_fourth(),
                data.getRes_fifth(),
                data.getRes_sixth(),
                data.getRes_seventh(), data.getRes_eight(), data.getBegin_with());
    }


    private class Roller implements Runnable {
        TextView textRoll;
        private int numTimes;
        private long delayMillis;
        private int max, min;

        private boolean shutdown;
        private boolean running;

        Roller(TextView textView, int numTimes, long delayMillis, int max, int min) {
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
