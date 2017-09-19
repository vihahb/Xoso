package com.xtelsolution.xoso.xoso.view.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.sdk.utils.JsonHelper;
import com.xtelsolution.xoso.sdk.utils.TimeUtils;
import com.xtelsolution.xoso.xoso.model.entity.BeginResult;
import com.xtelsolution.xoso.xoso.model.entity.EndResult;
import com.xtelsolution.xoso.xoso.model.entity.ResultLottery;
import com.xtelsolution.xoso.xoso.model.respond.RESP_NewResult;
import com.xtelsolution.xoso.xoso.model.respond.RESP_Result;
import com.xtelsolution.xoso.xoso.presenter.fragment.FragmentNorthContentPresenter;
import com.xtelsolution.xoso.xoso.view.adapter.AdapterLoto;
import com.xtelsolution.xoso.xoso.view.fragment.inf.IFragmentNorthContentView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by vivhp on 9/8/2017.
 */

public class FragmentNorthContent extends BasicFragment implements IFragmentNorthContentView {

    private static final String TAG = "FragmentNorthContent";

    private FragmentNorthContentPresenter presenter;

    private String getDateTime;

    private TextView tvContent;

    private LinearLayout ln_data;

    private List<String> loto_live;

    private RecyclerView rcl_loto_live;

    private AdapterLoto adapterLoto;

    /**
     * Value table result lottery
     */
    private TextView tvSpecial, tvFirst, tv_21, tv_22,
            tv_31, tv_32, tv_33, tv_34, tv_35, tv_36,
            tv_41, tv_42, tv_43, tv_44,
            tv_51, tv_52, tv_53, tv_54, tv_55, tv_56,
            tv_61, tv_62, tv_63,
            tv_71, tv_72, tv_73, tv_74;
    private TextView tvLoto0, tvLoto1, tvLoto2, tvLoto3, tvLoto4, tvLoto5, tvLoto6, tvLoto7, tvLoto8, tvLoto9,
            tvLotoDuoi0, tvLotoDuoi1, tvLotoDuoi2, tvLotoDuoi3, tvLotoDuoi4, tvLotoDuoi5, tvLotoDuoi6, tvLotoDuoi7, tvLotoDuoi8, tvLotoDuoi9;

    long millis;

    private MediaPlayer player;

    private static final String KEY_DATE = "date";
    private boolean toDay;

    private Roller special, first, r71, r72, r73, r74,
            r61, r62, r63,
            r51, r52, r53, r54, r55, r56,
            r41, r42, r43, r44,
            r31, r32, r33, r34, r35, r36,
            r21, r22;

    public static FragmentNorthContent newInstance(long date) {
        FragmentNorthContent fragmentFirst = new FragmentNorthContent();
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
        presenter = new FragmentNorthContentPresenter(this);
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                player = MediaPlayer.create(getContext(), Settings.System.DEFAULT_NOTIFICATION_URI);
                rcl_loto_live = view.findViewById(R.id.rcl_loto_live);
                loto_live = new ArrayList<>();
                adapterLoto = new AdapterLoto(loto_live, getContext());
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 9);
                layoutManager.setAutoMeasureEnabled(true);
                rcl_loto_live.setLayoutManager(layoutManager);
                rcl_loto_live.setAdapter(adapterLoto);

                ln_data = view.findViewById(R.id.ln_data);

                tvContent = view.findViewById(R.id.tvContent);

                tvSpecial = view.findViewById(R.id.tvSpecialValue);
                tvFirst = view.findViewById(R.id.tvFirstValue);
                tv_21 = view.findViewById(R.id.tvSecondValue_1);
                tv_22 = view.findViewById(R.id.tvSecondValue_2);
                tv_31 = view.findViewById(R.id.tv3_1);
                tv_32 = view.findViewById(R.id.tv3_2);
                tv_33 = view.findViewById(R.id.tv3_3);
                tv_34 = view.findViewById(R.id.tv3_4);
                tv_35 = view.findViewById(R.id.tv3_5);
                tv_36 = view.findViewById(R.id.tv3_6);
                tv_41 = view.findViewById(R.id.tv4_1);
                tv_42 = view.findViewById(R.id.tv4_2);
                tv_43 = view.findViewById(R.id.tv4_3);
                tv_44 = view.findViewById(R.id.tv4_4);
                tv_51 = view.findViewById(R.id.tv5_1);
                tv_52 = view.findViewById(R.id.tv5_2);
                tv_53 = view.findViewById(R.id.tv5_3);
                tv_54 = view.findViewById(R.id.tv5_4);
                tv_55 = view.findViewById(R.id.tv5_5);
                tv_56 = view.findViewById(R.id.tv5_6);
                tv_61 = view.findViewById(R.id.tv6_1);
                tv_62 = view.findViewById(R.id.tv6_2);
                tv_63 = view.findViewById(R.id.tv6_3);
                tv_71 = view.findViewById(R.id.tv7_1);
                tv_72 = view.findViewById(R.id.tv7_2);
                tv_73 = view.findViewById(R.id.tv7_3);
                tv_74 = view.findViewById(R.id.tv7_4);

                tvLoto0 = view.findViewById(R.id.tvLoto0);
                tvLoto1 = view.findViewById(R.id.tvLoto1);
                tvLoto2 = view.findViewById(R.id.tvLoto2);
                tvLoto3 = view.findViewById(R.id.tvLoto3);
                tvLoto4 = view.findViewById(R.id.tvLoto4);
                tvLoto5 = view.findViewById(R.id.tvLoto5);
                tvLoto6 = view.findViewById(R.id.tvLoto6);
                tvLoto7 = view.findViewById(R.id.tvLoto7);
                tvLoto8 = view.findViewById(R.id.tvLoto8);
                tvLoto9 = view.findViewById(R.id.tvLoto9);

                tvLotoDuoi0 = view.findViewById(R.id.tvLotoDuoi0);
                tvLotoDuoi1 = view.findViewById(R.id.tvLotoDuoi1);
                tvLotoDuoi2 = view.findViewById(R.id.tvLotoDuoi2);
                tvLotoDuoi3 = view.findViewById(R.id.tvLotoDuoi3);
                tvLotoDuoi4 = view.findViewById(R.id.tvLotoDuoi4);
                tvLotoDuoi5 = view.findViewById(R.id.tvLotoDuoi5);
                tvLotoDuoi6 = view.findViewById(R.id.tvLotoDuoi6);
                tvLotoDuoi7 = view.findViewById(R.id.tvLotoDuoi7);
                tvLotoDuoi8 = view.findViewById(R.id.tvLotoDuoi8);
                tvLotoDuoi9 = view.findViewById(R.id.tvLotoDuoi9);


                if (toDay && TimeUtils.checkTimeInMilisecondNorth(18, 12, 18, 40)) {
                    presenter.socketConnect();
                } else {
                    presenter.getResultLottery(getDateTime);
                }
            }
        }, 100);
    }

    @Override
    public void cleaOldData() {
        tvSpecial.setText("");
        tvFirst.setText("");
        tv_21.setText("");
        tv_22.setText("");
        tv_31.setText("");
        tv_32.setText("");
        tv_33.setText("");
        tv_34.setText("");
        tv_35.setText("");
        tv_36.setText("");
        tv_41.setText("");
        tv_42.setText("");
        tv_43.setText("");
        tv_44.setText("");
        tv_51.setText("");
        tv_52.setText("");
        tv_53.setText("");
        tv_54.setText("");
        tv_55.setText("");
        tv_56.setText("");
        tv_61.setText("");
        tv_62.setText("");
        tv_63.setText("");
        tv_71.setText("");
        tv_72.setText("");
        tv_73.setText("");
        tv_74.setText("");
        r71 = new Roller(tv_71, 100000, 80, 99, 10);
        r71.run();
    }

    @Override
    public void setDataSocket(RESP_Result resp_result) {
        setResultLottery(
                resp_result.getData().get(0).getRes_special(),
                resp_result.getData().get(0).getRes_first(),
                resp_result.getData().get(0).getRes_second(),
                resp_result.getData().get(0).getRes_third(),
                resp_result.getData().get(0).getRes_fourth(),
                resp_result.getData().get(0).getRes_fifth(),
                resp_result.getData().get(0).getRes_sixth(),
                resp_result.getData().get(0).getRes_seventh());

        List<String> loto_list = new ArrayList<>();
        loto_list.addAll(resp_result.getData().get(0).getLoto());
        sortList(loto_list);
    }

    @Override
    public void setNewResult(RESP_NewResult newResult) {
        player.start();
        switch (newResult.getResult_name()) {
            case "res_special":
                if (newResult.getValue() != null) {
                    if (special != null)
                        special.shutdownThread();
                    tvSpecial.setText(newResult.getValue());
                }
                break;
            case "res_first":
                if (newResult.getValue() != null) {
                    if (first != null)
                        first.shutdownThread();
                    special = new Roller(tv_21, 100000, 80, 99999, 10000);
                    special.run();
                    tvFirst.setText(newResult.getValue());
                }
                break;
            case "res_second":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (r21 != null)
                                r21.shutdownThread();
                            r22 = new Roller(tv_22, 100000, 80, 99999, 10000);
                            r22.run();
                            tv_21.setText(newResult.getValue());
                            break;
                        case "1":
                            if (r22 != null)
                                r22.shutdownThread();
                            tv_22.setText(newResult.getValue());
                            first = new Roller(tvFirst, 100000, 80, 99999, 10000);
                            first.run();
                            break;
                    }
                }
                break;
            case "res_third":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (r31 != null)
                                r31.shutdownThread();
                            r32 = new Roller(tv_32, 100000, 80, 99999, 10000);
                            r32.run();
                            tv_31.setText(newResult.getValue());
                            break;
                        case "1":
                            if (r32 != null)
                                r32.shutdownThread();
                            r33 = new Roller(tv_33, 100000, 80, 99999, 10000);
                            r33.run();
                            tv_32.setText(newResult.getValue());
                            break;
                        case "2":
                            if (r33 != null)
                                r33.shutdownThread();
                            r34 = new Roller(tv_34, 100000, 80, 99999, 10000);
                            r34.run();
                            tv_33.setText(newResult.getValue());
                            break;
                        case "3":
                            if (r34 != null)
                                r34.shutdownThread();
                            r35 = new Roller(tv_35, 100000, 80, 99999, 10000);
                            r35.run();
                            tv_34.setText(newResult.getValue());
                            break;
                        case "4":
                            if (r35 != null)
                                r35.shutdownThread();
                            r36 = new Roller(tv_36, 100000, 80, 99999, 10000);
                            r36.run();
                            tv_35.setText(newResult.getValue());
                            break;
                        case "5":
                            if (r36 != null)
                                r36.shutdownThread();
                            r21 = new Roller(tv_21, 100000, 80, 99999, 10000);
                            r21.run();
                            tv_36.setText(newResult.getValue());
                            break;
                    }
                }
                break;
            case "res_fourth":
                if (newResult.getValue() != null) {
                    switch (newResult.getIndex()) {
                        case "0":
                            if (r41 != null)
                                r41.shutdownThread();
                            r42 = new Roller(tv_42, 100000, 80, 9999, 1000);
                            r55.run();
                            tv_41.setText(newResult.getValue());
                            break;
                        case "1":
                            if (r22 != null)
                                r42.shutdownThread();
                            r43 = new Roller(tv_43, 100000, 80, 9999, 1000);
                            r43.run();
                            tv_42.setText(newResult.getValue());
                            break;
                        case "2":
                            if (r43 != null)
                                r43.shutdownThread();
                            r44 = new Roller(tv_44, 100000, 80, 9999, 1000);
                            r44.run();
                            tv_43.setText(newResult.getValue());
                            break;
                        case "3":
                            if (r44 != null)
                                r44.shutdownThread();
                            r31 = new Roller(tv_31, 100000, 80, 99999, 10000);
                            r31.run();
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
                                r51.shutdownThread();
                            }
                            r52 = new Roller(tv_52, 100000, 80, 9999, 1000);
                            r52.run();
                            tv_51.setText(newResult.getValue());
                            break;
                        case "1":
                            if (r52 != null)
                                r52.shutdownThread();
                            r53 = new Roller(tv_53, 100000, 80, 9999, 1000);
                            r53.run();
                            tv_52.setText(newResult.getValue());
                            break;
                        case "2":
                            if (r53 != null)
                                r53.shutdownThread();
                            r54 = new Roller(tv_54, 100000, 80, 9999, 1000);
                            r54.run();
                            tv_53.setText(newResult.getValue());
                            break;
                        case "3":
                            if (r54 != null)
                                r54.shutdownThread();
                            r55 = new Roller(tv_55, 100000, 80, 9999, 1000);
                            r55.run();
                            tv_54.setText(newResult.getValue());
                            break;
                        case "4":
                            if (r55 != null)
                                r55.shutdownThread();
                            r56 = new Roller(tv_56, 100000, 80, 9999, 1000);
                            r56.run();
                            tv_55.setText(newResult.getValue());
                            break;
                        case "5":
                            if (r56 != null)
                                r56.shutdownThread();
                            r41 = new Roller(tv_41, 100000, 80, 9999, 1000);
                            r41.run();
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
                                r61.shutdownThread();
                            }
                            r62 = new Roller(tv_62, 100000, 80, 999, 100);
                            r62.run();
                            tv_61.setText(newResult.getValue());
                            break;
                        case "1":
                            if (r62 != null) {
                                r62.shutdownThread();
                            }
                            r63 = new Roller(tv_63, 100000, 80, 999, 100);
                            r63.run();
                            tv_62.setText(newResult.getValue());
                            break;
                        case "2":
                            if (r63 != null) {
                                r63.shutdownThread();
                            }
                            r51 = new Roller(tv_51, 100000, 80, 9999, 1000);
                            r51.run();
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
                                r71.shutdownThread();
                            }
                            tv_71.setText(newResult.getValue());
                            r72 = new Roller(tv_72, 100000, 80, 99, 10);
                            r72.run();
                            break;
                        case "1":
                            if (r72 != null) {
                                r72.shutdownThread();
                            }
                            r73 = new Roller(tv_73, 100000, 80, 99, 10);
                            r73.run();
                            tv_72.setText(newResult.getValue());
                            break;
                        case "2":
                            if (r73 != null) {
                                r73.shutdownThread();
                            }
                            r74 = new Roller(tv_74, 100000, 80, 99, 10);
                            r74.run();
                            tv_73.setText(newResult.getValue());
                            break;
                        case "3":
                            if (r74 != null) {
                                r74.shutdownThread();
                            }
                            r61 = new Roller(tv_61, 100000, 80, 999, 100);
                            r61.run();
                            tv_74.setText(newResult.getValue());
                            break;
                    }
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
//        setLotoList(data.getLoto());
        setResultLottery(
                data.getRes_special(),
                data.getRes_first(),
                data.getRes_second(),
                data.getRes_third(),
                data.getRes_fourth(),
                data.getRes_fifth(),
                data.getRes_sixth(),
                data.getRes_seventh());
        sortList(data.getLoto());

        Log.e(TAG, "getResultLotterySuccess: " + JsonHelper.toJson(data));

        setBeginEndLoto(data.getBegin_with(), data.getEnd_with());
    }

    private void setBeginEndLoto(BeginResult beginResult, EndResult end_with) {
        /**
         * Dau loto*/
        if (beginResult.getB0().size() > 0) {
            String begin_0 = "";
            for (int i = 0; i < beginResult.getB0().size(); i++) {
                begin_0 += " " + beginResult.getB0().get(i);
            }
            tvLoto0.setText(begin_0);
        }


        if (beginResult.getB1().size() > 0) {
            String begin_1 = "";
            for (int i = 0; i < beginResult.getB1().size(); i++) {
                begin_1 += " " + beginResult.getB1().get(i);
            }
            tvLoto1.setText(begin_1);
        }

        if (beginResult.getB2().size() > 0) {
            String begin_2 = "";
            for (int i = 0; i < beginResult.getB2().size(); i++) {
                begin_2 += " " + beginResult.getB2().get(i);
            }
            tvLoto2.setText(begin_2);
        }

        if (beginResult.getB3().size() > 0) {
            String begin_3 = "";
            for (int i = 0; i < beginResult.getB3().size(); i++) {
                begin_3 += " " + beginResult.getB3().get(i);
            }
            tvLoto3.setText(begin_3);
        }

        if (beginResult.getB4().size() > 0) {
            String begin_4 = "";
            for (int i = 0; i < beginResult.getB4().size(); i++) {
                begin_4 += " " + beginResult.getB4().get(i);
            }
            tvLoto4.setText(begin_4);
        }

        if (beginResult.getB5().size() > 0) {
            String begin_5 = "";
            for (int i = 0; i < beginResult.getB5().size(); i++) {
                begin_5 += " " + beginResult.getB5().get(i);
            }
            tvLoto5.setText(begin_5);
        }

        if (beginResult.getB6().size() > 0) {
            String begin_6 = "";
            for (int i = 0; i < beginResult.getB6().size(); i++) {
                begin_6 += " " + beginResult.getB6().get(i);
            }
            tvLoto6.setText(begin_6);
        }

        if (beginResult.getB7().size() > 0) {
            String begin_7 = "";
            for (int i = 0; i < beginResult.getB7().size(); i++) {
                begin_7 += " " + beginResult.getB7().get(i);
            }
            tvLoto7.setText(begin_7);
        }

        if (beginResult.getB8().size() > 0) {
            String begin_8 = "";
            for (int i = 0; i < beginResult.getB8().size(); i++) {
                begin_8 += " " + beginResult.getB8().get(i);
            }
            tvLoto8.setText(begin_8);
        }

        if (beginResult.getB9().size() > 0) {
            String begin_9 = "";
            for (int i = 0; i < beginResult.getB9().size(); i++) {
                begin_9 += " " + beginResult.getB9().get(i);
            }
            tvLoto9.setText(begin_9);
        }


        /**
         * Duoi loto*/
        if (end_with.getE0().size() > 0) {
            String begin_0 = "";
            for (int i = 0; i < end_with.getE0().size(); i++) {
                begin_0 += " " + end_with.getE0().get(i);
            }
            tvLotoDuoi0.setText(begin_0);
        }

        if (end_with.getE1().size() > 0) {
            String begin_1 = "";
            for (int i = 0; i < end_with.getE1().size(); i++) {
                begin_1 += " " + end_with.getE1().get(i);
            }
            tvLotoDuoi1.setText(begin_1);
        }

        if (end_with.getE2().size() > 0) {
            String begin_2 = "";
            for (int i = 0; i < end_with.getE2().size(); i++) {
                begin_2 += " " + end_with.getE2().get(i);
            }
            tvLotoDuoi2.setText(begin_2);
        }

        if (end_with.getE3().size() > 0) {
            String begin_3 = "";
            for (int i = 0; i < end_with.getE3().size(); i++) {
                begin_3 += " " + end_with.getE3().get(i);
            }
            tvLotoDuoi3.setText(begin_3);
        }

        if (end_with.getE4().size() > 0) {
            String begin_4 = "";
            for (int i = 0; i < end_with.getE4().size(); i++) {
                begin_4 += " " + end_with.getE4().get(i);
            }
            tvLotoDuoi4.setText(begin_4);
        }

        if (end_with.getE5().size() > 0) {
            String begin_5 = "";
            for (int i = 0; i < end_with.getE5().size(); i++) {
                begin_5 += " " + end_with.getE5().get(i);
            }
            tvLotoDuoi5.setText(begin_5);
        }

        if (end_with.getE6().size() > 0) {
            String begin_6 = "";
            for (int i = 0; i < end_with.getE6().size(); i++) {
                begin_6 += " " + end_with.getE6().get(i);
            }
            tvLotoDuoi6.setText(begin_6);
        }

        if (end_with.getE7().size() > 0) {
            String begin_7 = "";
            for (int i = 0; i < end_with.getE7().size(); i++) {
                begin_7 += " " + end_with.getE7().get(i);
            }
            tvLotoDuoi7.setText(begin_7);
        }

        if (end_with.getE8().size() > 0) {
            String begin_8 = "";
            for (int i = 0; i < end_with.getE8().size(); i++) {
                begin_8 += " " + end_with.getE8().get(i);
            }
            tvLotoDuoi8.setText(begin_8);
        }

        if (end_with.getE9().size() > 0) {
            String begin_9 = "";
            for (int i = 0; i < end_with.getE9().size(); i++) {
                begin_9 += " " + end_with.getE9().get(i);
            }
            tvLotoDuoi9.setText(begin_9);
        }
    }

    @Override
    public void getResultLotteryError(String error) {
        tvContent.setText(error);
        tvContent.setVisibility(View.VISIBLE);
        ln_data.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.disconnectSocket();
        destroyView();
    }

    private void destroyView() {
        if (player != null) {
            player.stop();
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

        if (special.size() > 0) {
            tvSpecial.setText(special.get(0));
        }

        if (first.size() > 0) {
            tvFirst.setText(first.get(0));
        }

        if (second.size() > 0 && second.size() == 2) {
            tv_21.setText(second.get(0));
            tv_22.setText(second.get(1));
        }

        if (third.size() > 0 && third.size() == 6) {
            tv_31.setText(third.get(0));
            tv_32.setText(third.get(1));
            tv_33.setText(third.get(2));
            tv_34.setText(third.get(3));
            tv_35.setText(third.get(4));
            tv_36.setText(third.get(5));
        }

        if (fourd.size() > 0 && fourd.size() == 4) {
            tv_41.setText(fourd.get(0));
            tv_42.setText(fourd.get(1));
            tv_43.setText(fourd.get(2));
            tv_44.setText(fourd.get(3));
        }

        if (five.size() > 0 && five.size() == 6) {
            tv_51.setText(five.get(0));
            tv_52.setText(five.get(1));
            tv_53.setText(five.get(2));
            tv_54.setText(five.get(3));
            tv_55.setText(five.get(4));
            tv_56.setText(five.get(5));

        }

        if (six.size() > 0 && six.size() == 3) {
            tv_61.setText(six.get(0));
            tv_62.setText(six.get(1));
            tv_63.setText(six.get(2));
        }

        if (seven.size() > 0 && seven.size() == 4) {
            tv_71.setText(seven.get(0));
            tv_72.setText(seven.get(1));
            tv_73.setText(seven.get(2));
            tv_74.setText(seven.get(3));
        }
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
