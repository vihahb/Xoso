package com.xtelsolution.xoso.xoso.view.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.sdk.utils.TimeUtils;
import com.xtelsolution.xoso.xoso.model.entity.BeginResult;
import com.xtelsolution.xoso.xoso.model.entity.ResultLottery;
import com.xtelsolution.xoso.xoso.model.respond.RESP_NewResult;
import com.xtelsolution.xoso.xoso.model.respond.RESP_Result;
import com.xtelsolution.xoso.xoso.presenter.fragment.FragmentSouthContentPresenter;
import com.xtelsolution.xoso.xoso.view.fragment.inf.IFragmentSouthContent;

import java.util.List;
import java.util.Random;

/**
 * Created by vivhp on 9/13/2017.
 */

public class FragmentSouthContent extends BasicFragment implements IFragmentSouthContent {

    private FragmentSouthContentPresenter presenter;

    private String getDateTime;

    private TextView tvContent, tv_title;

    private ScrollView scroll_south;

    private TableLayout table_1, table_2, table_3, table_4;
    /**
     * Value table result lottery
     */
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
     * Table 4
     */
    private TextView tvResgion_4, tv8_4, tv7_4,
            tv6_1_4, tv6_2_4, tv6_3_4,
            tv5_4, tv4_1_4, tv4_2_4, tv4_3_4, tv4_4_4, tv4_5_4, tv4_6_4, tv4_7_4,
            tv3_1_4, tv3_2_4,
            tv2_4, tv1_4, tvDb_4,
            tvLotoTitle4, tvLoto0_4, tvLoto1_4, tvLoto2_4, tvLoto3_4,
            tvLoto4_4, tvLoto5_4, tvLoto6_4, tvLoto7_4, tvLoto8_4, tvLoto9_4;


//    private List<String> eight_list_1, sevent_list_1, sixth_list_1, fiveth_list_1, fourth_list_1, thrid_list_1, second_list_1, first_list_1, special_list_1;
//    private List<String> eight_list_2, sevent_list_2, sixth_list_2, fiveth_list_2, fourth_list_2, thrid_list_2, second_list_2, first_list_2, special_list_2;
//    private List<String> eight_list_3, sevent_list_3, sixth_list_3, fiveth_list_3, fourth_list_3, thrid_list_3, second_list_3, first_list_3, special_list_3;

    long millis;

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

    /**
     * Roller Table 4
     */
    private Roller rl84, rl74, rl641, rl642, rl643, rl54,
            rl44, rl442, rl443, rl444, rl445, rl446, rl447,
            rl341, rl342, rl_second_4, rl_first_4, rl_special_4;

    boolean isToday;

    private static final String KEY_DATE = "date";
    private MediaPlayer player;
    private boolean toDay;

    public static FragmentSouthContent newInstance(long date) {
        FragmentSouthContent fragmentFirst = new FragmentSouthContent();
        Bundle args = new Bundle();
        args.putLong(KEY_DATE, date);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        return inflater.inflate(R.layout.fragment_south_content, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FragmentSouthContentPresenter(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                player = MediaPlayer.create(getContext(), Settings.System.DEFAULT_NOTIFICATION_URI);
                scroll_south = view.findViewById(R.id.scroll_south);

                tvContent = view.findViewById(R.id.tvContent);
                tv_title = view.findViewById(R.id.tv_title);
                tv_title.setText("kết quả xổ số miền Nam");

                initTable1(view);
                initTable2(view);
                initTable3(view);
                initTable4(view);
                initRandomRolling();

                if (toDay) {
                    presenter.connectSocket();
                } else {
                    presenter.getResultLottery(getDateTime);
                }
            }
        }, 100);
    }

    private void initTable1(View view) {
        table_1 = view.findViewById(R.id.south_region_1);
        tvResgion_1 = view.findViewById(R.id.tvRegionTitle1);
        tv8_1 = view.findViewById(R.id.tv8_1);
        tv7_1 = view.findViewById(R.id.tv7_1);
        tv6_1 = view.findViewById(R.id.tv6_1);
        tv6_2 = view.findViewById(R.id.tv6_2);
        tv6_3 = view.findViewById(R.id.tv6_3);
        tv5_1 = view.findViewById(R.id.tv5_1);
        tv4_1 = view.findViewById(R.id.tv4_1);
        tv4_2 = view.findViewById(R.id.tv4_2);
        tv4_3 = view.findViewById(R.id.tv4_3);
        tv4_4 = view.findViewById(R.id.tv4_4);
        tv4_5 = view.findViewById(R.id.tv4_5);
        tv4_6 = view.findViewById(R.id.tv4_6);
        tv4_7 = view.findViewById(R.id.tv4_7);
        tv3_1 = view.findViewById(R.id.tv3_1);
        tv3_2 = view.findViewById(R.id.tv3_2);
        tv2_1 = view.findViewById(R.id.tv2_1);
        tv1_1 = view.findViewById(R.id.tv1);
        tvDb = view.findViewById(R.id.tvDb);

        tvLotoTitle1 = view.findViewById(R.id.tvLotoTitle1);
        tvLoto0_1 = view.findViewById(R.id.tvLoto0_1);
        tvLoto1_1 = view.findViewById(R.id.tvLoto1_1);
        tvLoto2_1 = view.findViewById(R.id.tvLoto2_1);
        tvLoto3_1 = view.findViewById(R.id.tvLoto3_1);
        tvLoto4_1 = view.findViewById(R.id.tvLoto4_1);
        tvLoto5_1 = view.findViewById(R.id.tvLoto5_1);
        tvLoto6_1 = view.findViewById(R.id.tvLoto6_1);
        tvLoto7_1 = view.findViewById(R.id.tvLoto7_1);
        tvLoto8_1 = view.findViewById(R.id.tvLoto8_1);
        tvLoto9_1 = view.findViewById(R.id.tvLoto9_1);
    }

    private void initTable2(View view) {
        table_2 = view.findViewById(R.id.south_region_2);
        tvResgion_2 = view.findViewById(R.id.tvRegionTitle2);
        tv8_2 = view.findViewById(R.id.tv8_2);
        tv7_2 = view.findViewById(R.id.tv7_2);
        tv6_1_2 = view.findViewById(R.id.tv6_1_2);
        tv6_2_2 = view.findViewById(R.id.tv6_2_2);
        tv6_3_2 = view.findViewById(R.id.tv6_3_2);
        tv5_2 = view.findViewById(R.id.tv5_2);
        tv4_1_2 = view.findViewById(R.id.tv4_1_2);
        tv4_2_2 = view.findViewById(R.id.tv4_2_2);
        tv4_3_2 = view.findViewById(R.id.tv4_3_2);
        tv4_4_2 = view.findViewById(R.id.tv4_4_2);
        tv4_5_2 = view.findViewById(R.id.tv4_5_2);
        tv4_6_2 = view.findViewById(R.id.tv4_6_2);
        tv4_7_2 = view.findViewById(R.id.tv4_7_2);
        tv3_1_2 = view.findViewById(R.id.tv3_1_2);
        tv3_2_2 = view.findViewById(R.id.tv3_2_2);
        tv2_2 = view.findViewById(R.id.tv2_2);
        tv1_2 = view.findViewById(R.id.tv1_2);
        tvDb_2 = view.findViewById(R.id.tvDb_2);

        tvLotoTitle2 = view.findViewById(R.id.tvLotoTitle2);
        tvLoto0_2 = view.findViewById(R.id.tvLoto0_2);
        tvLoto1_2 = view.findViewById(R.id.tvLoto1_2);
        tvLoto2_2 = view.findViewById(R.id.tvLoto2_2);
        tvLoto3_2 = view.findViewById(R.id.tvLoto3_2);
        tvLoto4_2 = view.findViewById(R.id.tvLoto4_2);
        tvLoto5_2 = view.findViewById(R.id.tvLoto5_2);
        tvLoto6_2 = view.findViewById(R.id.tvLoto6_2);
        tvLoto7_2 = view.findViewById(R.id.tvLoto7_2);
        tvLoto8_2 = view.findViewById(R.id.tvLoto8_2);
        tvLoto9_2 = view.findViewById(R.id.tvLoto9_2);
    }

    private void initTable3(View view) {
        table_3 = view.findViewById(R.id.south_region_3);
        tvResgion_3 = view.findViewById(R.id.tvRegionTitle3);
        tv8_3 = view.findViewById(R.id.tv8_3);
        tv7_3 = view.findViewById(R.id.tv7_3);
        tv6_1_3 = view.findViewById(R.id.tv6_1_3);
        tv6_2_3 = view.findViewById(R.id.tv6_2_3);
        tv6_3_3 = view.findViewById(R.id.tv6_3_3);
        tv5_3 = view.findViewById(R.id.tv5_3);
        tv4_1_3 = view.findViewById(R.id.tv4_1_3);
        tv4_2_3 = view.findViewById(R.id.tv4_2_3);
        tv4_3_3 = view.findViewById(R.id.tv4_3_3);
        tv4_4_3 = view.findViewById(R.id.tv4_4_3);
        tv4_5_3 = view.findViewById(R.id.tv4_5_3);
        tv4_6_3 = view.findViewById(R.id.tv4_6_3);
        tv4_7_3 = view.findViewById(R.id.tv4_7_3);
        tv3_1_3 = view.findViewById(R.id.tv3_1_3);
        tv3_2_3 = view.findViewById(R.id.tv3_2_3);
        tv2_3 = view.findViewById(R.id.tv2_3);
        tv1_3 = view.findViewById(R.id.tv1_3);
        tvDb_3 = view.findViewById(R.id.tvDb_3);

        tvLotoTitle3 = view.findViewById(R.id.tvLotoTitle3);
        tvLoto0_3 = view.findViewById(R.id.tvLoto0_3);
        tvLoto1_3 = view.findViewById(R.id.tvLoto1_3);
        tvLoto2_3 = view.findViewById(R.id.tvLoto2_3);
        tvLoto3_3 = view.findViewById(R.id.tvLoto3_3);
        tvLoto4_3 = view.findViewById(R.id.tvLoto4_3);
        tvLoto5_3 = view.findViewById(R.id.tvLoto5_3);
        tvLoto6_3 = view.findViewById(R.id.tvLoto6_3);
        tvLoto7_3 = view.findViewById(R.id.tvLoto7_3);
        tvLoto8_3 = view.findViewById(R.id.tvLoto8_3);
        tvLoto9_3 = view.findViewById(R.id.tvLoto9_3);
    }

    private void initTable4(View view) {
        table_4 = view.findViewById(R.id.south_region_4);
        tvResgion_4 = view.findViewById(R.id.tvRegionTitle4);
        tv8_4 = view.findViewById(R.id.tv8_4);
        tv7_4 = view.findViewById(R.id.tv7_4);
        tv6_1_4 = view.findViewById(R.id.tv6_1_4);
        tv6_2_4 = view.findViewById(R.id.tv6_2_4);
        tv6_3_4 = view.findViewById(R.id.tv6_3_4);
        tv5_4 = view.findViewById(R.id.tv5_4);
        tv4_1_4 = view.findViewById(R.id.tv4_1_4);
        tv4_2_4 = view.findViewById(R.id.tv4_2_4);
        tv4_3_4 = view.findViewById(R.id.tv4_3_4);
        tv4_4_4 = view.findViewById(R.id.tv4_4_4);
        tv4_5_4 = view.findViewById(R.id.tv4_5_4);
        tv4_6_4 = view.findViewById(R.id.tv4_6_4);
        tv4_7_4 = view.findViewById(R.id.tv4_7_4);
        tv3_1_4 = view.findViewById(R.id.tv3_1_4);
        tv3_2_4 = view.findViewById(R.id.tv3_2_4);
        tv2_4 = view.findViewById(R.id.tv2_4);
        tv1_4 = view.findViewById(R.id.tv1_4);
        tvDb_4 = view.findViewById(R.id.tvDb_4);

        tvLotoTitle4 = view.findViewById(R.id.tvLotoTitle4);
        tvLoto0_4 = view.findViewById(R.id.tvLoto0_4);
        tvLoto1_4 = view.findViewById(R.id.tvLoto1_4);
        tvLoto2_4 = view.findViewById(R.id.tvLoto2_4);
        tvLoto3_4 = view.findViewById(R.id.tvLoto3_4);
        tvLoto4_4 = view.findViewById(R.id.tvLoto4_4);
        tvLoto5_4 = view.findViewById(R.id.tvLoto5_4);
        tvLoto6_4 = view.findViewById(R.id.tvLoto6_4);
        tvLoto7_4 = view.findViewById(R.id.tvLoto7_4);
        tvLoto8_4 = view.findViewById(R.id.tvLoto8_4);
        tvLoto9_4 = view.findViewById(R.id.tvLoto9_4);
    }

    private void initRandomRolling() {
        /**
         * 8
         * Random number table all*/
        if (rl81 == null) {
            rl81 = new Roller(tv8_1, 100000, 80, 99, 10);
        }
        rl82 = new Roller(tv8_2, 100000, 80, 99, 10);
        rl83 = new Roller(tv8_3, 100000, 80, 99, 10);
        rl84 = new Roller(tv8_4, 100000, 80, 99, 10);


        /**
         * 7
         * Random number table all*/
        rl71 = new Roller(tv7_1, 10000, 80, 999, 100);
        rl72 = new Roller(tv7_2, 10000, 80, 999, 100);
        rl73 = new Roller(tv7_3, 10000, 80, 999, 100);
        rl74 = new Roller(tv7_4, 10000, 80, 999, 100);

        /**
         * 6
         * Random number table 1*/
        rl611 = new Roller(tv6_1, 10000, 80, 9999, 1000);
        rl612 = new Roller(tv6_2, 10000, 80, 9999, 1000);
        rl613 = new Roller(tv6_3, 10000, 80, 9999, 1000);


        /**
         * 6
         * Random number table 2*/
        rl621 = new Roller(tv6_1_2, 10000, 80, 9999, 1000);
        rl622 = new Roller(tv6_2_2, 10000, 80, 9999, 1000);
        rl623 = new Roller(tv6_3_2, 10000, 80, 9999, 1000);


        /**
         * 6
         * Random number table 3*/
        rl631 = new Roller(tv6_1_3, 10000, 80, 9999, 1000);
        rl632 = new Roller(tv6_2_3, 10000, 80, 9999, 1000);
        rl633 = new Roller(tv6_3_3, 10000, 80, 9999, 1000);

        /**
         * 6
         * Random number table 4*/
        rl641 = new Roller(tv6_1_4, 10000, 80, 9999, 1000);
        rl642 = new Roller(tv6_2_4, 10000, 80, 9999, 1000);
        rl643 = new Roller(tv6_3_4, 10000, 80, 9999, 1000);

        /**
         * 5
         * Random number table all*/
        rl51 = new Roller(tv5_1, 10000, 80, 9999, 1000);
        rl52 = new Roller(tv5_2, 10000, 80, 9999, 1000);
        rl53 = new Roller(tv5_3, 10000, 80, 9999, 1000);
        rl54 = new Roller(tv5_4, 10000, 80, 9999, 1000);

        /**
         * 4
         * Random number table 1*/
        rl41 = new Roller(tv4_1, 10000, 80, 99999, 10000);
        rl412 = new Roller(tv4_2, 10000, 80, 99999, 10000);
        rl413 = new Roller(tv4_3, 10000, 80, 99999, 10000);
        rl414 = new Roller(tv4_4, 10000, 80, 99999, 10000);
        rl415 = new Roller(tv4_5, 10000, 80, 99999, 10000);
        rl416 = new Roller(tv4_6, 10000, 80, 99999, 10000);
        rl417 = new Roller(tv4_7, 10000, 80, 99999, 10000);

        /**
         * 4
         * Random number table 2*/
        rl42 = new Roller(tv4_1_2, 10000, 80, 99999, 10000);
        rl422 = new Roller(tv4_2_2, 10000, 80, 99999, 10000);
        rl423 = new Roller(tv4_3_2, 10000, 80, 99999, 10000);
        rl424 = new Roller(tv4_4_2, 10000, 80, 99999, 10000);
        rl425 = new Roller(tv4_5_2, 10000, 80, 99999, 10000);
        rl426 = new Roller(tv4_6_2, 10000, 80, 99999, 10000);
        rl427 = new Roller(tv4_7_2, 10000, 80, 99999, 10000);

        /**
         * 4
         * Random number table 3*/
        rl43 = new Roller(tv4_1_3, 10000, 80, 99999, 10000);
        rl432 = new Roller(tv4_2_3, 10000, 80, 99999, 10000);
        rl433 = new Roller(tv4_3_3, 10000, 80, 99999, 10000);
        rl434 = new Roller(tv4_4_3, 10000, 80, 99999, 10000);
        rl435 = new Roller(tv4_5_3, 10000, 80, 99999, 10000);
        rl436 = new Roller(tv4_6_3, 10000, 80, 99999, 10000);
        rl437 = new Roller(tv4_7_3, 10000, 80, 99999, 10000);

        /**
         * 4
         * Random number table 4*/
        rl44 = new Roller(tv4_1_4, 10000, 80, 99999, 10000);
        rl442 = new Roller(tv4_2_4, 10000, 80, 99999, 10000);
        rl443 = new Roller(tv4_3_4, 10000, 80, 99999, 10000);
        rl444 = new Roller(tv4_4_4, 10000, 80, 99999, 10000);
        rl445 = new Roller(tv4_5_4, 10000, 80, 99999, 10000);
        rl446 = new Roller(tv4_6_4, 10000, 80, 99999, 10000);
        rl447 = new Roller(tv4_7_4, 10000, 80, 99999, 10000);

        /**
         * 3
         * Random number table 1*/
        rl311 = new Roller(tv3_1, 10000, 80, 99999, 10000);
        rl312 = new Roller(tv3_2, 10000, 80, 99999, 10000);

        /**
         * 3
         * Random number table 2*/
        rl321 = new Roller(tv3_1_2, 10000, 80, 99999, 10000);
        rl322 = new Roller(tv3_2_2, 10000, 80, 99999, 10000);

        /**
         * 3
         * Random number table 3*/
        rl331 = new Roller(tv3_1_3, 10000, 80, 99999, 10000);
        rl332 = new Roller(tv3_2_3, 10000, 80, 99999, 10000);

        /**
         * 3
         * Random number table 4*/
        rl341 = new Roller(tv3_1_4, 10000, 80, 99999, 10000);
        rl342 = new Roller(tv3_2_4, 10000, 80, 99999, 10000);

        /**
         * 2
         * Random number table all*/
        rl_second_1 = new Roller(tv2_1, 10000, 80, 99999, 10000);
        rl_second_2 = new Roller(tv2_2, 10000, 80, 99999, 10000);
        rl_second_3 = new Roller(tv2_3, 10000, 80, 99999, 10000);
        rl_second_4 = new Roller(tv2_4, 10000, 80, 99999, 10000);

        /**
         * 1
         * Random number table all*/
        rl_first_1 = new Roller(tv1_1, 10000, 80, 99999, 10000);
        rl_first_2 = new Roller(tv1_2, 10000, 80, 99999, 10000);
        rl_first_3 = new Roller(tv1_3, 10000, 80, 99999, 10000);
        rl_first_4 = new Roller(tv1_4, 10000, 80, 99999, 10000);

        /**
         * DB
         * Random number table all*/
        rl_special_1 = new Roller(tvDb, 10000, 80, 99999, 10000);
        rl_special_2 = new Roller(tvDb_2, 10000, 80, 99999, 10000);
        rl_special_3 = new Roller(tvDb_3, 10000, 80, 99999, 10000);
        rl_special_4 = new Roller(tvDb_4, 10000, 80, 99999, 10000);
    }

    private void checkRandomTable1(List<String> special, List<String> first, List<String> second, List<String> third, List<String> fourd, List<String> five, List<String> six, List<String> seven, List<String> eight) {
        if (eight.size() > 0){
            if (rl81!=null){
                rl81.shutdownThread();
            }
        }
        if (seven.size() > 0){
            if (rl71!=null){
                rl71.shutdownThread();
            }
        }

        if (six.size() > 0){
            if (rl611!=null){
                rl611.shutdownThread();
            }
        } else if (six.size() > 1){
            if (rl612!=null){
                rl612.shutdownThread();
            }
        } else if (six.size() > 2){
            if (rl613!=null){
                rl613.shutdownThread();
            }
        }

        if (five.size() > 0){
            if (rl51!=null){
                rl51.shutdownThread();
            }
        }

        if (fourd.size() > 0){
            if (rl41!=null){
                rl41.shutdownThread();
            }
        } else if (fourd.size() > 1){
            if (rl412!=null){
                rl412.shutdownThread();
            }
        } else if (fourd.size() > 2){
            if (rl413!=null){
                rl413.shutdownThread();
            }
        }else if (fourd.size() > 3){
            if (rl414!=null){
                rl414.shutdownThread();
            }
        }else if (fourd.size() > 4){
            if (rl415!=null){
                rl415.shutdownThread();
            }
        }else if (fourd.size() > 5){
            if (rl416!=null){
                rl416.shutdownThread();
            }
        }else if (fourd.size() > 6){
            if (rl417!=null){
                rl417.shutdownThread();
            }
        }

        if (third.size() > 0){
            if (rl311!=null){
                rl311.shutdownThread();
            }
        } else if (third.size() > 1){
            if (rl312!=null){
                rl312.shutdownThread();
            }
        }

        if (second.size() > 0){
            if (rl_second_1!=null){
                rl_second_1.shutdownThread();
            }
        }

        if (first.size() > 0){
            if (rl_first_1!=null){
                rl_first_1.shutdownThread();
            }
        }
        if (special.size() > 0){
            if (rl_special_1!=null){
                rl_special_1.shutdownThread();
            }
        }
    }
    private void checkRandomTable2(List<String> special, List<String> first, List<String> second, List<String> third, List<String> fourd, List<String> five, List<String> six, List<String> seven, List<String> eight) {
        if (eight.size() > 0){
            if (rl82!=null){
                rl82.shutdownThread();
            }
        }
        if (seven.size() > 0){
            if (rl72!=null){
                rl72.shutdownThread();
            }
        }

        if (six.size() > 0){
            if (rl621!=null){
                rl621.shutdownThread();
            }
        } else if (six.size() > 1){
            if (rl622!=null){
                rl622.shutdownThread();
            }
        } else if (six.size() > 2){
            if (rl623!=null){
                rl623.shutdownThread();
            }
        }

        if (five.size() > 0){
            if (rl52!=null){
                rl52.shutdownThread();
            }
        }

        if (fourd.size() > 0){
            if (rl42!=null){
                rl42.shutdownThread();
            }
        } else if (fourd.size() > 1){
            if (rl422!=null){
                rl422.shutdownThread();
            }
        } else if (fourd.size() > 2){
            if (rl423!=null){
                rl423.shutdownThread();
            }
        }else if (fourd.size() > 3){
            if (rl424!=null){
                rl424.shutdownThread();
            }
        }else if (fourd.size() > 4){
            if (rl425!=null){
                rl425.shutdownThread();
            }
        }else if (fourd.size() > 5){
            if (rl426!=null){
                rl426.shutdownThread();
            }
        }else if (fourd.size() > 6){
            if (rl427!=null){
                rl427.shutdownThread();
            }
        }

        if (third.size() > 0){
            if (rl321!=null){
                rl321.shutdownThread();
            }
        } else if (third.size() > 1){
            if (rl322!=null){
                rl322.shutdownThread();
            }
        }

        if (second.size() > 0){
            if (rl_second_2!=null){
                rl_second_2.shutdownThread();
            }
        }

        if (first.size() > 0){
            if (rl_first_2!=null){
                rl_first_2.shutdownThread();
            }
        }
        if (special.size() > 0){
            if (rl_special_2!=null){
                rl_special_2.shutdownThread();
            }
        }
    }
    private void checkRandomTable3(List<String> special, List<String> first, List<String> second, List<String> third, List<String> fourd, List<String> five, List<String> six, List<String> seven, List<String> eight) {
        if (eight.size() > 0){
            if (rl83!=null){
                rl83.shutdownThread();
            }
        }
        if (seven.size() > 0){
            if (rl73!=null){
                rl73.shutdownThread();
            }
        }

        if (six.size() > 0){
            if (rl631!=null){
                rl631.shutdownThread();
            }
        } else if (six.size() > 1){
            if (rl632!=null){
                rl632.shutdownThread();
            }
        } else if (six.size() > 2){
            if (rl633!=null){
                rl633.shutdownThread();
            }
        }

        if (five.size() > 0){
            if (rl53!=null){
                rl53.shutdownThread();
            }
        }

        if (fourd.size() > 0){
            if (rl43!=null){
                rl43.shutdownThread();
            }
        } else if (fourd.size() > 1){
            if (rl432!=null){
                rl432.shutdownThread();
            }
        } else if (fourd.size() > 2){
            if (rl433!=null){
                rl433.shutdownThread();
            }
        }else if (fourd.size() > 3){
            if (rl434!=null){
                rl434.shutdownThread();
            }
        }else if (fourd.size() > 4){
            if (rl435!=null){
                rl435.shutdownThread();
            }
        }else if (fourd.size() > 5){
            if (rl436!=null){
                rl436.shutdownThread();
            }
        }else if (fourd.size() > 6){
            if (rl437!=null){
                rl437.shutdownThread();
            }
        }

        if (third.size() > 0){
            if (rl331!=null){
                rl331.shutdownThread();
            }
        } else if (third.size() > 1){
            if (rl332!=null){
                rl332.shutdownThread();
            }
        }

        if (second.size() > 0){
            if (rl_second_3!=null){
                rl_second_3.shutdownThread();
            }
        }

        if (first.size() > 0){
            if (rl_first_3!=null){
                rl_first_3.shutdownThread();
            }
        }
        if (special.size() > 0){
            if (rl_special_3!=null){
                rl_special_3.shutdownThread();
            }
        }
    }
    private void checkRandomTable4(List<String> special, List<String> first, List<String> second, List<String> third, List<String> fourd, List<String> five, List<String> six, List<String> seven, List<String> eight) {
        if (eight.size() > 0){
            if (rl84!=null){
                rl84.shutdownThread();
            }
        }
        if (seven.size() > 0){
            if (rl74!=null){
                rl74.shutdownThread();
            }
        }

        if (six.size() > 0){
            if (rl641!=null){
                rl641.shutdownThread();
            }
        } else if (six.size() > 1){
            if (rl642!=null){
                rl642.shutdownThread();
            }
        } else if (six.size() > 2){
            if (rl643!=null){
                rl643.shutdownThread();
            }
        }

        if (five.size() > 0){
            if (rl43!=null){
                rl43.shutdownThread();
            }
        }

        if (fourd.size() > 0){
            if (rl44!=null){
                rl44.shutdownThread();
            }
        } else if (fourd.size() > 1){
            if (rl442!=null){
                rl442.shutdownThread();
            }
        } else if (fourd.size() > 2){
            if (rl443!=null){
                rl443.shutdownThread();
            }
        }else if (fourd.size() > 3){
            if (rl444!=null){
                rl444.shutdownThread();
            }
        }else if (fourd.size() > 4){
            if (rl445!=null){
                rl445.shutdownThread();
            }
        }else if (fourd.size() > 5){
            if (rl446!=null){
                rl446.shutdownThread();
            }
        }else if (fourd.size() > 6){
            if (rl447!=null){
                rl447.shutdownThread();
            }
        }

        if (third.size() > 0){
            if (rl341!=null){
                rl341.shutdownThread();
            }
        } else if (third.size() > 1){
            if (rl342!=null){
                rl342.shutdownThread();
            }
        }

        if (second.size() > 0){
            if (rl_second_4!=null){
                rl_second_4.shutdownThread();
            }
        }

        if (first.size() > 0){
            if (rl_first_4!=null){
                rl_first_4.shutdownThread();
            }
        }
        if (special.size() > 0){
            if (rl_special_4!=null){
                rl_special_4.shutdownThread();
            }
        }
    }
    @Override
    public void setDataSocket(RESP_Result resp_result) {
        switch (resp_result.getData().size()) {
            case 1:
                setTable2Hidden();
                setTable3Hidden();
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
                setTable4Hidden();
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
            case 4:
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
                setResultLotteryTable4(
                        resp_result.getData().get(3).getArea(),
                        resp_result.getData().get(3).getRes_special(),
                        resp_result.getData().get(3).getRes_first(),
                        resp_result.getData().get(3).getRes_second(),
                        resp_result.getData().get(3).getRes_third(),
                        resp_result.getData().get(3).getRes_fourth(),
                        resp_result.getData().get(3).getRes_fifth(),
                        resp_result.getData().get(3).getRes_sixth(),
                        resp_result.getData().get(3).getRes_seventh(),
                        resp_result.getData().get(3).getRes_eight(),
                        resp_result.getData().get(3).getBegin_with());
                break;
        }
    }

    @Override
    public void setTable4Hidden() {
        table_4.setVisibility(View.GONE);
        tvLotoTitle4.setVisibility(View.GONE);
        tvLoto0_4.setVisibility(View.GONE);
        tvLoto1_4.setVisibility(View.GONE);
        tvLoto2_4.setVisibility(View.GONE);
        tvLoto3_4.setVisibility(View.GONE);
        tvLoto4_4.setVisibility(View.GONE);
        tvLoto5_4.setVisibility(View.GONE);
        tvLoto6_4.setVisibility(View.GONE);
        tvLoto7_4.setVisibility(View.GONE);
        tvLoto8_4.setVisibility(View.GONE);
        tvLoto9_4.setVisibility(View.GONE);
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
            scroll_south.setVisibility(View.VISIBLE);
        } else {
            scroll_south.setVisibility(View.GONE);
        }
    }

    @Override
    public void random(int random_table) {
        switch (random_table){
            case 1:
                rl81 = new Roller(tv8_1, 100000, 80, 99, 10);
                rl81.run();
                break;
            case 2:
                rl81 = new Roller(tv8_1, 100000, 80, 99, 10);
                rl81.run();

                rl82 = new Roller(tv8_2, 100000, 80, 99, 10);
                rl82.run();
                break;
            case 3:
                rl81 = new Roller(tv8_1, 100000, 80, 99, 10);
                rl81.run();

                rl82 = new Roller(tv8_2, 100000, 80, 99, 10);
                rl82.run();

                rl83 = new Roller(tv8_3, 100000, 80, 99, 10);
                rl83.run();
                break;
            case 4:
                rl81 = new Roller(tv8_1, 100000, 80, 99, 10);
                rl81.run();

                rl82 = new Roller(tv8_2, 100000, 80, 99, 10);
                rl82.run();

                rl83 = new Roller(tv8_3, 100000, 80, 99, 10);
                rl83.run();

                rl84 = new Roller(tv8_4, 100000, 80, 99, 10);
                rl84.run();
                break;
        }
    }

    @Override
    public void setNewResult(RESP_NewResult newResult, int position_table) {
        player.start();
        switch (position_table) {
            /**
             * Table 1*/
            case 1:
                switch (newResult.getResult_name()) {
                    case "res_eight":
                        if (newResult.getValue() != null) {
                            if (rl81 != null) {
                                rl81.shutdownThread();
                            }
                            rl71.run();
                            tv8_1.setText(newResult.getValue());
                        }
                        break;
                    case "res_seventh":
                        if (newResult.getValue() != null) {
                            if (rl71 != null) {
                                rl71.shutdownThread();
                            }
                            rl611.run();
                            tv7_1.setText(newResult.getValue());
                        }
                        break;
                    case "res_sixth":
                        if (newResult.getValue() != null) {
                            switch (newResult.getIndex()) {
                                case "0":
                                    if (rl611 != null) {
                                        rl611.shutdownThread();
                                    }
                                    rl612.run();
                                    tv6_1.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl612 != null) {
                                        rl612.shutdownThread();
                                    }
                                    rl613.run();
                                    tv6_2.setText(newResult.getValue());
                                    break;
                                case "2":
                                    if (rl613 != null) {
                                        rl613.shutdownThread();
                                    }
                                    rl51.run();
                                    tv6_3.setText(newResult.getValue());
                                    break;
                            }

                        }
                        break;
                    case "res_fifth":
                        if (rl51 != null) {
                            rl51.shutdownThread();
                        }
                        rl41.run();
                        tv5_1.setText(newResult.getValue());
                        break;
                    case "res_fourth":
                        if (newResult.getValue() != null) {
                            switch (newResult.getIndex()) {
                                case "0":
                                    if (rl41 != null) {
                                        rl41.shutdownThread();
                                    }
                                    rl412.run();
                                    tv4_1.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl412 != null) {
                                        rl412.shutdownThread();
                                    }
                                    rl413.run();
                                    tv4_2.setText(newResult.getValue());
                                    break;
                                case "2":
                                    if (rl413 != null) {
                                        rl413.shutdownThread();
                                    }
                                    rl414.run();
                                    tv4_3.setText(newResult.getValue());
                                    break;

                                case "3":
                                    if (rl414 != null) {
                                        rl414.shutdownThread();
                                    }
                                    rl415.run();
                                    tv4_4.setText(newResult.getValue());
                                    break;
                                case "4":
                                    if (rl415 != null) {
                                        rl415.shutdownThread();
                                    }
                                    rl416.run();
                                    tv4_5.setText(newResult.getValue());
                                    break;
                                case "5":
                                    if (rl416 != null) {
                                        rl416.shutdownThread();
                                    }
                                    rl417.run();
                                    tv4_6.setText(newResult.getValue());
                                    break;
                                case "6":
                                    if (rl417 != null) {
                                        rl417.shutdownThread();
                                    }
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
                                        rl311.shutdownThread();
                                    }
                                    rl312.run();
                                    tv3_1.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl312 != null) {
                                        rl312.shutdownThread();
                                    }
                                    rl_second_1.run();
                                    tv3_2.setText(newResult.getValue());
                                    break;
                            }
                        }
                        break;
                    case "res_second":
                        if (rl_second_1 != null) {
                            rl_second_1.shutdownThread();
                        }
                        rl_first_1.run();
                        tv2_1.setText(newResult.getValue());
                        break;
                    case "res_first":
                        if (rl_first_1 != null) {
                            rl_first_1.shutdownThread();
                        }
                        rl_special_1.run();
                        tv1_1.setText(newResult.getValue());
                        break;
                    case "res_special":
                        if (rl_special_1 != null) {
                            rl_special_1.shutdownThread();
                        }
                        tvDb.setText(newResult.getValue());
                        break;
                }
                break;

            /**
             * Table 2*/
            case 2:
                switch (newResult.getResult_name()) {
                    case "res_eight":
                        if (newResult.getValue() != null) {
                            if (rl82 != null) {
                                rl82.shutdownThread();
                            }
                            rl72.run();
                            tv8_2.setText(newResult.getValue());
                        }
                        break;
                    case "res_seventh":
                        if (newResult.getValue() != null) {
                            if (rl72 != null) {
                                rl72.shutdownThread();
                            }
                            rl621.run();
                            tv7_2.setText(newResult.getValue());
                        }
                        break;
                    case "res_sixth":
                        if (newResult.getValue() != null) {
                            switch (newResult.getIndex()) {
                                case "0":
                                    if (rl621 != null) {
                                        rl621.shutdownThread();
                                    }
                                    rl622.run();
                                    tv6_1_2.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl622 != null) {
                                        rl622.shutdownThread();
                                    }
                                    rl623.run();
                                    tv6_2_2.setText(newResult.getValue());
                                    break;
                                case "2":
                                    if (rl623 != null) {
                                        rl623.shutdownThread();
                                    }
                                    rl52.run();
                                    tv6_3_2.setText(newResult.getValue());
                                    break;
                            }

                        }
                        break;
                    case "res_fifth":
                        if (rl52 != null) {
                            rl52.shutdownThread();
                        }
                        rl42.run();
                        tv5_2.setText(newResult.getValue());
                        break;
                    case "res_fourth":
                        if (newResult.getValue() != null) {
                            switch (newResult.getIndex()) {
                                case "0":
                                    if (rl42 != null) {
                                        rl42.shutdownThread();
                                    }
                                    rl422.run();
                                    tv4_1_2.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl422 != null) {
                                        rl422.shutdownThread();
                                    }
                                    rl423.run();
                                    tv4_2_2.setText(newResult.getValue());
                                    break;
                                case "2":
                                    if (rl423 != null) {
                                        rl423.shutdownThread();
                                    }
                                    rl424.run();
                                    tv4_3_2.setText(newResult.getValue());
                                    break;

                                case "3":
                                    if (rl424 != null) {
                                        rl424.shutdownThread();
                                    }
                                    rl425.run();
                                    tv4_4_2.setText(newResult.getValue());
                                    break;
                                case "4":
                                    if (rl425 != null) {
                                        rl425.shutdownThread();
                                    }
                                    rl426.run();
                                    tv4_5_2.setText(newResult.getValue());
                                    break;
                                case "5":
                                    if (rl426 != null) {
                                        rl426.shutdownThread();
                                    }
                                    rl427.run();
                                    tv4_6_2.setText(newResult.getValue());
                                    break;
                                case "6":
                                    if (rl427 != null) {
                                        rl427.shutdownThread();
                                    }
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
                                        rl321.shutdownThread();
                                    }
                                    rl322.run();
                                    tv3_1_2.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl322 != null) {
                                        rl322.shutdownThread();
                                    }
                                    rl_second_2.run();
                                    tv3_2_2.setText(newResult.getValue());
                                    break;
                            }
                        }
                        break;
                    case "res_second":
                        if (rl_second_2 != null) {
                            rl_second_2.shutdownThread();
                        }
                        rl_first_2.run();
                        tv2_2.setText(newResult.getValue());
                        break;
                    case "res_first":
                        if (rl_first_2 != null) {
                            rl_first_2.shutdownThread();
                        }
                        rl_special_2.run();
                        tv1_2.setText(newResult.getValue());
                        break;
                    case "res_special":
                        if (rl_special_2 != null) {
                            rl_special_2.shutdownThread();
                        }
                        tvDb_2.setText(newResult.getValue());
                        break;
                }
                break;

            /**
             * Table 3*/
            case 3:
                switch (newResult.getResult_name()) {
                    case "res_eight":
                        if (newResult.getValue() != null) {
                            if (rl83 != null) {
                                rl83.shutdownThread();
                            }
                            rl73.run();
                            tv8_3.setText(newResult.getValue());
                        }
                        break;
                    case "res_seventh":
                        if (newResult.getValue() != null) {
                            if (rl73 != null) {
                                rl73.shutdownThread();
                            }
                            rl631.run();
                            tv7_3.setText(newResult.getValue());
                        }
                        break;
                    case "res_sixth":
                        if (newResult.getValue() != null) {
                            switch (newResult.getIndex()) {
                                case "0":
                                    if (rl631 != null) {
                                        rl631.shutdownThread();
                                    }
                                    rl632.run();
                                    tv6_1_3.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl632 != null) {
                                        rl632.shutdownThread();
                                    }
                                    rl633.run();
                                    tv6_2_3.setText(newResult.getValue());
                                    break;
                                case "2":
                                    if (rl633 != null) {
                                        rl633.shutdownThread();
                                    }
                                    rl53.run();
                                    tv6_3_3.setText(newResult.getValue());
                                    break;
                            }

                        }
                        break;
                    case "res_fifth":
                        if (rl53 != null) {
                            rl53.shutdownThread();
                        }
                        rl43.run();
                        tv5_3.setText(newResult.getValue());
                        break;
                    case "res_fourth":
                        if (newResult.getValue() != null) {
                            switch (newResult.getIndex()) {
                                case "0":
                                    if (rl43 != null) {
                                        rl43.shutdownThread();
                                    }
                                    rl432.run();
                                    tv4_1_3.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl432 != null) {
                                        rl432.shutdownThread();
                                    }
                                    rl433.run();
                                    tv4_2_3.setText(newResult.getValue());
                                    break;
                                case "2":
                                    if (rl433 != null) {
                                        rl433.shutdownThread();
                                    }
                                    rl434.run();
                                    tv4_3_3.setText(newResult.getValue());
                                    break;

                                case "3":
                                    if (rl434 != null) {
                                        rl434.shutdownThread();
                                    }
                                    rl435.run();
                                    tv4_4_3.setText(newResult.getValue());
                                    break;
                                case "4":
                                    if (rl435 != null) {
                                        rl435.shutdownThread();
                                    }
                                    rl436.run();
                                    tv4_5_3.setText(newResult.getValue());
                                    break;
                                case "5":
                                    if (rl436 != null) {
                                        rl436.shutdownThread();
                                    }
                                    rl437.run();
                                    tv4_6_3.setText(newResult.getValue());
                                    break;
                                case "6":
                                    if (rl437 != null) {
                                        rl437.shutdownThread();
                                    }
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
                                        rl331.shutdownThread();
                                    }
                                    rl332.run();
                                    tv3_1_3.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl332 != null) {
                                        rl332.shutdownThread();
                                    }
                                    rl_second_3.run();
                                    tv3_2_3.setText(newResult.getValue());
                                    break;
                            }
                        }
                        break;
                    case "res_second":
                        if (rl_second_3 != null) {
                            rl_second_3.shutdownThread();
                        }
                        rl_first_3.run();
                        tv2_3.setText(newResult.getValue());
                        break;
                    case "res_first":
                        if (rl_first_3 != null) {
                            rl_first_3.shutdownThread();
                        }
                        rl_special_3.run();
                        tv1_3.setText(newResult.getValue());
                        break;
                    case "res_special":
                        if (rl_special_3 != null) {
                            rl_special_3.shutdownThread();
                        }
                        tvDb_3.setText(newResult.getValue());
                        break;
                }
                break;


            /**
             * Table 4*/
            case 4:
                switch (newResult.getResult_name()) {
                    case "res_eight":
                        if (newResult.getValue() != null) {
                            if (rl84 != null) {
                                rl84.shutdownThread();
                            }
                            rl74.run();
                            tv8_4.setText(newResult.getValue());
                        }
                        break;
                    case "res_seventh":
                        if (newResult.getValue() != null) {
                            if (rl74 != null) {
                                rl74.shutdownThread();
                            }
                            rl641.run();
                            tv7_4.setText(newResult.getValue());
                        }
                        break;
                    case "res_sixth":
                        if (newResult.getValue() != null) {
                            switch (newResult.getIndex()) {
                                case "0":
                                    if (rl641 != null) {
                                        rl641.shutdownThread();
                                    }
                                    rl642.run();
                                    tv6_1_4.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl642 != null) {
                                        rl642.shutdownThread();
                                    }
                                    rl643.run();
                                    tv6_2_4.setText(newResult.getValue());
                                    break;
                                case "2":
                                    if (rl642 != null) {
                                        rl642.shutdownThread();
                                    }
                                    rl54.run();
                                    tv6_3_4.setText(newResult.getValue());
                                    break;
                            }

                        }
                        break;
                    case "res_fifth":
                        if (rl54 != null) {
                            rl54.shutdownThread();
                        }
                        rl44.run();
                        tv5_4.setText(newResult.getValue());
                        break;
                    case "res_fourth":
                        if (newResult.getValue() != null) {
                            switch (newResult.getIndex()) {
                                case "0":
                                    if (rl44 != null) {
                                        rl44.shutdownThread();
                                    }
                                    rl442.run();
                                    tv4_1_4.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl442 != null) {
                                        rl442.shutdownThread();
                                    }
                                    rl443.run();
                                    tv4_2_4.setText(newResult.getValue());
                                    break;
                                case "2":
                                    if (rl443 != null) {
                                        rl443.shutdownThread();
                                    }
                                    rl444.run();
                                    tv4_3_4.setText(newResult.getValue());
                                    break;

                                case "3":
                                    if (rl444 != null) {
                                        rl444.shutdownThread();
                                    }
                                    rl445.run();
                                    tv4_4_4.setText(newResult.getValue());
                                    break;
                                case "4":
                                    if (rl445 != null) {
                                        rl445.shutdownThread();
                                    }
                                    rl446.run();
                                    tv4_5_4.setText(newResult.getValue());
                                    break;
                                case "5":
                                    if (rl446 != null) {
                                        rl446.shutdownThread();
                                    }
                                    rl447.run();
                                    tv4_6_4.setText(newResult.getValue());
                                    break;
                                case "6":
                                    if (rl447 != null) {
                                        rl447.shutdownThread();
                                    }
                                    rl341.run();
                                    tv4_7_4.setText(newResult.getValue());
                                    break;
                            }

                        }
                        break;
                    case "res_third":
                        if (newResult.getValue() != null) {
                            switch (newResult.getIndex()) {
                                case "0":
                                    if (rl341 != null) {
                                        rl341.shutdownThread();
                                    }
                                    rl342.run();
                                    tv3_1_4.setText(newResult.getValue());
                                    break;
                                case "1":
                                    if (rl342 != null) {
                                        rl342.shutdownThread();
                                    }
                                    rl_second_4.run();
                                    tv3_2_4.setText(newResult.getValue());
                                    break;
                            }
                        }
                        break;
                    case "res_second":
                        if (rl_second_4 != null) {
                            rl_second_4.shutdownThread();
                        }
                        rl_first_4.run();
                        tv2_4.setText(newResult.getValue());
                        break;
                    case "res_first":
                        if (rl_first_4 != null) {
                            rl_first_4.shutdownThread();
                        }
                        rl_special_4.run();
                        tv1_4.setText(newResult.getValue());
                        break;
                    case "res_special":
                        if (rl_special_4 != null) {
                            rl_special_4.shutdownThread();
                        }
                        tvDb_4.setText(newResult.getValue());
                        break;
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.disconnectSocket();
        destroyView();
    }

    private void destroyView() {

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


        checkRandomTable1(special, first, second, third, fourd, five, six, seven, eight);

        tvResgion_1.setText(area);
        tvLotoTitle1.setText(area);

        if (special.size() > 0)
            tvDb.setText(special.get(0));

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

        if (beginResult != null) {
            if (beginResult.getB0().size() > 0) {
                String begin_0 = "";
                for (int i = 0; i < beginResult.getB0().size(); i++) {
                    begin_0 += " " + beginResult.getB0().get(i);
                }
                tvLoto0_1.setText(begin_0);
            }


            if (beginResult.getB1().size() > 0) {
                String begin_1 = "";
                for (int i = 0; i < beginResult.getB1().size(); i++) {
                    begin_1 += " " + beginResult.getB1().get(i);
                }
                tvLoto1_1.setText(begin_1);
            }

            if (beginResult.getB2().size() > 0) {
                String begin_2 = "";
                for (int i = 0; i < beginResult.getB2().size(); i++) {
                    begin_2 += " " + beginResult.getB2().get(i);
                }
                tvLoto2_1.setText(begin_2);
            }

            if (beginResult.getB3().size() > 0) {
                String begin_3 = "";
                for (int i = 0; i < beginResult.getB3().size(); i++) {
                    begin_3 += " " + beginResult.getB3().get(i);
                }
                tvLoto3_1.setText(begin_3);
            }

            if (beginResult.getB4().size() > 0) {
                String begin_4 = "";
                for (int i = 0; i < beginResult.getB4().size(); i++) {
                    begin_4 += " " + beginResult.getB4().get(i);
                }
                tvLoto4_1.setText(begin_4);
            }

            if (beginResult.getB5().size() > 0) {
                String begin_5 = "";
                for (int i = 0; i < beginResult.getB5().size(); i++) {
                    begin_5 += " " + beginResult.getB5().get(i);
                }
                tvLoto5_1.setText(begin_5);
            }

            if (beginResult.getB6().size() > 0) {
                String begin_6 = "";
                for (int i = 0; i < beginResult.getB6().size(); i++) {
                    begin_6 += " " + beginResult.getB6().get(i);
                }
                tvLoto6_1.setText(begin_6);
            }

            if (beginResult.getB7().size() > 0) {
                String begin_7 = "";
                for (int i = 0; i < beginResult.getB7().size(); i++) {
                    begin_7 += " " + beginResult.getB7().get(i);
                }
                tvLoto7_1.setText(begin_7);
            }

            if (beginResult.getB8().size() > 0) {
                String begin_8 = "";
                for (int i = 0; i < beginResult.getB8().size(); i++) {
                    begin_8 += " " + beginResult.getB8().get(i);
                }
                tvLoto8_1.setText(begin_8);
            }

            if (beginResult.getB9().size() > 0) {
                String begin_9 = "";
                for (int i = 0; i < beginResult.getB9().size(); i++) {
                    begin_9 += " " + beginResult.getB9().get(i);
                }
                tvLoto9_1.setText(begin_9);
            }

        }
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


        checkRandomTable2(special, first, second, third, fourd, five, six, seven, eight);

        tvResgion_2.setText(area);
        tvLotoTitle2.setText(area);

        if (special.size() > 0)
            tvDb_2.setText(special.get(0));

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
        if (beginResult != null) {
            if (beginResult.getB0().size() > 0) {
                String begin_0 = "";
                for (int i = 0; i < beginResult.getB0().size(); i++) {
                    begin_0 += " " + beginResult.getB0().get(i);
                }
                tvLoto0_2.setText(begin_0);
            }


            if (beginResult.getB1().size() > 0) {
                String begin_1 = "";
                for (int i = 0; i < beginResult.getB1().size(); i++) {
                    begin_1 += " " + beginResult.getB1().get(i);
                }
                tvLoto1_2.setText(begin_1);
            }

            if (beginResult.getB2().size() > 0) {
                String begin_2 = "";
                for (int i = 0; i < beginResult.getB2().size(); i++) {
                    begin_2 += " " + beginResult.getB2().get(i);
                }
                tvLoto2_2.setText(begin_2);
            }

            if (beginResult.getB3().size() > 0) {
                String begin_3 = "";
                for (int i = 0; i < beginResult.getB3().size(); i++) {
                    begin_3 += " " + beginResult.getB3().get(i);
                }
                tvLoto3_2.setText(begin_3);
            }

            if (beginResult.getB4().size() > 0) {
                String begin_4 = "";
                for (int i = 0; i < beginResult.getB4().size(); i++) {
                    begin_4 += " " + beginResult.getB4().get(i);
                }
                tvLoto4_2.setText(begin_4);
            }

            if (beginResult.getB5().size() > 0) {
                String begin_5 = "";
                for (int i = 0; i < beginResult.getB5().size(); i++) {
                    begin_5 += " " + beginResult.getB5().get(i);
                }
                tvLoto5_2.setText(begin_5);
            }

            if (beginResult.getB6().size() > 0) {
                String begin_6 = "";
                for (int i = 0; i < beginResult.getB6().size(); i++) {
                    begin_6 += " " + beginResult.getB6().get(i);
                }
                tvLoto6_2.setText(begin_6);
            }

            if (beginResult.getB7().size() > 0) {
                String begin_7 = "";
                for (int i = 0; i < beginResult.getB7().size(); i++) {
                    begin_7 += " " + beginResult.getB7().get(i);
                }
                tvLoto7_2.setText(begin_7);
            }

            if (beginResult.getB8().size() > 0) {
                String begin_8 = "";
                for (int i = 0; i < beginResult.getB8().size(); i++) {
                    begin_8 += " " + beginResult.getB8().get(i);
                }
                tvLoto8_2.setText(begin_8);
            }

            if (beginResult.getB9().size() > 0) {
                String begin_9 = "";
                for (int i = 0; i < beginResult.getB9().size(); i++) {
                    begin_9 += " " + beginResult.getB9().get(i);
                }
                tvLoto9_2.setText(begin_9);
            }
        }
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
        checkRandomTable3(special, first, second, third, fourd, five, six, seven, eight);

        tvResgion_3.setText(area);
        tvLotoTitle3.setText(area);

        if (special.size() > 0)
            tvDb_3.setText(special.get(0));

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

        if (beginResult != null) {
            if (beginResult.getB0().size() > 0) {
                String begin_0 = "";
                for (int i = 0; i < beginResult.getB0().size(); i++) {
                    begin_0 += " " + beginResult.getB0().get(i);
                }
                tvLoto0_3.setText(begin_0);
            }


            if (beginResult.getB1().size() > 0) {
                String begin_1 = "";
                for (int i = 0; i < beginResult.getB1().size(); i++) {
                    begin_1 += " " + beginResult.getB1().get(i);
                }
                tvLoto1_3.setText(begin_1);
            }

            if (beginResult.getB2().size() > 0) {
                String begin_2 = "";
                for (int i = 0; i < beginResult.getB2().size(); i++) {
                    begin_2 += " " + beginResult.getB2().get(i);
                }
                tvLoto2_3.setText(begin_2);
            }

            if (beginResult.getB3().size() > 0) {
                String begin_3 = "";
                for (int i = 0; i < beginResult.getB3().size(); i++) {
                    begin_3 += " " + beginResult.getB3().get(i);
                }
                tvLoto3_3.setText(begin_3);
            }

            if (beginResult.getB4().size() > 0) {
                String begin_4 = "";
                for (int i = 0; i < beginResult.getB4().size(); i++) {
                    begin_4 += " " + beginResult.getB4().get(i);
                }
                tvLoto4_3.setText(begin_4);
            }

            if (beginResult.getB5().size() > 0) {
                String begin_5 = "";
                for (int i = 0; i < beginResult.getB5().size(); i++) {
                    begin_5 += " " + beginResult.getB5().get(i);
                }
                tvLoto5_3.setText(begin_5);
            }

            if (beginResult.getB6().size() > 0) {
                String begin_6 = "";
                for (int i = 0; i < beginResult.getB6().size(); i++) {
                    begin_6 += " " + beginResult.getB6().get(i);
                }
                tvLoto6_3.setText(begin_6);
            }

            if (beginResult.getB7().size() > 0) {
                String begin_7 = "";
                for (int i = 0; i < beginResult.getB7().size(); i++) {
                    begin_7 += " " + beginResult.getB7().get(i);
                }
                tvLoto7_3.setText(begin_7);
            }

            if (beginResult.getB8().size() > 0) {
                String begin_8 = "";
                for (int i = 0; i < beginResult.getB8().size(); i++) {
                    begin_8 += " " + beginResult.getB8().get(i);
                }
                tvLoto8_3.setText(begin_8);
            }

            if (beginResult.getB9().size() > 0) {
                String begin_9 = "";
                for (int i = 0; i < beginResult.getB9().size(); i++) {
                    begin_9 += " " + beginResult.getB9().get(i);
                }
                tvLoto9_3.setText(begin_9);
            }
        }
    }


    private void setResultLotteryTable4(String area, List<String> special,
                                        List<String> first,
                                        List<String> second,
                                        List<String> third,
                                        List<String> fourd,
                                        List<String> five,
                                        List<String> six,
                                        List<String> seven,
                                        List<String> eight, BeginResult beginResult) {
        checkRandomTable4(special, first, second, third, fourd, five, six, seven, eight);

        tvResgion_4.setText(area);
        tvLotoTitle4.setText(area);

        if (special.size() > 0)
        tvDb_4.setText(special.get(0));

        if (eight.size() > 0)
        tv8_4.setText(eight.get(0));

        if (seven.size() > 0)
        tv7_4.setText(seven.get(0));

        if (six.size() > 0) {
            switch (six.size()) {
                case 1:
                    tv6_1_4.setText(six.get(0));
                    break;
                case 2:
                    tv6_1_4.setText(six.get(0));
                    tv6_2_4.setText(six.get(1));
                    break;
                case 3:
                    tv6_1_4.setText(six.get(0));
                    tv6_2_4.setText(six.get(1));
                    tv6_3_4.setText(six.get(2));
                    break;
            }
        }

        if (five.size() > 0)
        tv5_4.setText(five.get(0));

        if (fourd.size() > 0) {
            switch (fourd.size()) {
                case 1:
                    tv4_1_4.setText(fourd.get(0));
                    break;
                case 2:
                    tv4_1_4.setText(fourd.get(0));
                    tv4_2_4.setText(fourd.get(1));
                    break;
                case 3:
                    tv4_1_4.setText(fourd.get(0));
                    tv4_2_4.setText(fourd.get(1));
                    tv4_3_4.setText(fourd.get(2));
                    break;
                case 4:
                    tv4_1_4.setText(fourd.get(0));
                    tv4_2_4.setText(fourd.get(1));
                    tv4_3_4.setText(fourd.get(2));
                    tv4_4_4.setText(fourd.get(3));
                    break;
                case 5:
                    tv4_1_4.setText(fourd.get(0));
                    tv4_2_4.setText(fourd.get(1));
                    tv4_3_4.setText(fourd.get(2));
                    tv4_4_4.setText(fourd.get(3));
                    tv4_5_4.setText(fourd.get(4));
                    break;
                case 6:
                    tv4_1_4.setText(fourd.get(0));
                    tv4_2_4.setText(fourd.get(1));
                    tv4_3_4.setText(fourd.get(2));
                    tv4_4_4.setText(fourd.get(3));
                    tv4_5_4.setText(fourd.get(4));
                    tv4_6_4.setText(fourd.get(5));
                    break;
                case 7:
                    tv4_1_4.setText(fourd.get(0));
                    tv4_2_4.setText(fourd.get(1));
                    tv4_3_4.setText(fourd.get(2));
                    tv4_4_4.setText(fourd.get(3));
                    tv4_5_4.setText(fourd.get(4));
                    tv4_6_4.setText(fourd.get(5));
                    tv4_7_4.setText(fourd.get(6));
                    break;

            }
        }

        if (third.size() > 0) {
            switch (third.size()) {
                case 1:
                    tv3_1_4.setText(third.get(0));
                    break;
                case 2:
                    tv3_1_4.setText(third.get(0));
                    tv3_2_4.setText(third.get(1));
                    break;
            }
        }

        if (second.size() > 0)
        tv2_4.setText(second.get(0));

        if (first.size() > 0)
        tv1_4.setText(first.get(0));
        /**
         * Dau loto*/

        if (beginResult != null){
            if (beginResult.getB0().size() > 0) {
                String begin_0 = "";
                for (int i = 0; i < beginResult.getB0().size(); i++) {
                    begin_0 += " " + beginResult.getB0().get(i);
                }
                tvLoto0_4.setText(begin_0);
            }


            if (beginResult.getB1().size() > 0) {
                String begin_1 = "";
                for (int i = 0; i < beginResult.getB1().size(); i++) {
                    begin_1 += " " + beginResult.getB1().get(i);
                }
                tvLoto1_4.setText(begin_1);
            }

            if (beginResult.getB2().size() > 0) {
                String begin_2 = "";
                for (int i = 0; i < beginResult.getB2().size(); i++) {
                    begin_2 += " " + beginResult.getB2().get(i);
                }
                tvLoto2_4.setText(begin_2);
            }

            if (beginResult.getB3().size() > 0) {
                String begin_3 = "";
                for (int i = 0; i < beginResult.getB3().size(); i++) {
                    begin_3 += " " + beginResult.getB3().get(i);
                }
                tvLoto3_4.setText(begin_3);
            }

            if (beginResult.getB4().size() > 0) {
                String begin_4 = "";
                for (int i = 0; i < beginResult.getB4().size(); i++) {
                    begin_4 += " " + beginResult.getB4().get(i);
                }
                tvLoto4_4.setText(begin_4);
            }

            if (beginResult.getB5().size() > 0) {
                String begin_5 = "";
                for (int i = 0; i < beginResult.getB5().size(); i++) {
                    begin_5 += " " + beginResult.getB5().get(i);
                }
                tvLoto5_4.setText(begin_5);
            }

            if (beginResult.getB6().size() > 0) {
                String begin_6 = "";
                for (int i = 0; i < beginResult.getB6().size(); i++) {
                    begin_6 += " " + beginResult.getB6().get(i);
                }
                tvLoto6_4.setText(begin_6);
            }

            if (beginResult.getB7().size() > 0) {
                String begin_7 = "";
                for (int i = 0; i < beginResult.getB7().size(); i++) {
                    begin_7 += " " + beginResult.getB7().get(i);
                }
                tvLoto7_4.setText(begin_7);
            }

            if (beginResult.getB8().size() > 0) {
                String begin_8 = "";
                for (int i = 0; i < beginResult.getB8().size(); i++) {
                    begin_8 += " " + beginResult.getB8().get(i);
                }
                tvLoto8_4.setText(begin_8);
            }

            if (beginResult.getB9().size() > 0) {
                String begin_9 = "";
                for (int i = 0; i < beginResult.getB9().size(); i++) {
                    begin_9 += " " + beginResult.getB9().get(i);
                }
                tvLoto9_4.setText(begin_9);
            }
        }
    }

    @Override
    public void getResultLotteryError(String error) {
        tvContent.setText(error);
        tvContent.setVisibility(View.VISIBLE);
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

    @Override
    public void setTableRegion4(ResultLottery data) {
        setResultLotteryTable4(data.getArea(),
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
        private int numTimes;
        private long delayMillis;
        TextView textRoll;

        private int max, min;

        private volatile boolean shutdown;

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
                    Random rn = new Random();
                    int range = max - min + 1;
                    int randomNum = rn.nextInt(range);
                    String roll = String.valueOf(randomNum);
                    textRoll.setText(roll);

                    numTimes--;

                    if (numTimes > 0) {
                        textRoll.postDelayed(this, delayMillis);
                    }
                }
            }
        }

        public void shutdownThread() {
            shutdown = true;
        }
    }
}

