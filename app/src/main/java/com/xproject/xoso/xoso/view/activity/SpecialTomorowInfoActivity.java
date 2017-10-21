package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.CalendarUtils;
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.CustomBeginEndSum;
import com.xproject.xoso.xoso.model.entity.Freq_statsEntity;
import com.xproject.xoso.xoso.model.entity.SameDayEntity;
import com.xproject.xoso.xoso.model.entity.SameDow_NextDayEntity;
import com.xproject.xoso.xoso.model.entity.SpecialTomorowEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.view.adapter.AdapterAllDayYear;
import com.xproject.xoso.xoso.view.adapter.AdapterBeginEndSum;
import com.xproject.xoso.xoso.view.adapter.AdapterFreqLoto;
import com.xproject.xoso.xoso.view.adapter.AdapterLotoDb;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SpecialTomorowInfoActivity extends BasicActivity {

    AdapterFreqLoto adapterFreqLoto;
    List<Freq_statsEntity> list_freq;
    AdapterBeginEndSum adapterBegin, adapterEnd, adapterSum;
    List<CustomBeginEndSum> begin_list, end_list, sum_list;
    AdapterAllDayYear adapterAllDayYear;
    List<SameDayEntity> list_same_day;
    SpeedTemp temp;
    SpecialTomorowEntity entity;
    /**
     * Table loto_special
     */
    private TextView tv_title_loto_special, tv_date_loto_special, tv_date_loto_special_visible;
    private RecyclerView rcl_loto_db;
    private List<SameDow_NextDayEntity> list_today;
    private AdapterLotoDb adapterToday;
    /**
     * Table loto_special_freq
     */
    private TextView tv_title_loto_special_freq;
    private RecyclerView rcl_number_freq;
    /**
     * Table loto touch
     */
    private TextView tv_begin, tv_end, tv_sum;
    private RecyclerView rcl_touch_begin, rcl_touch_end, rcl_touch_sum;
    /**
     * Table loto_special_yester_day
     */
    private TextView tv_title_loto_special_yester_day, tv_date_loto_special_yester_day,
            tv_date_loto_special_visible_yester_day;
    private RecyclerView rcl_loto_db_yester_day;
    private List<SameDow_NextDayEntity> list_yester_day;
    private AdapterLotoDb adapterYester_day;
    /**
     * Table special_all_year
     */
    private TextView tv_title_special_all_year;
    private RecyclerView rcl_special_all_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_tomorow_info);
        initToolbar(R.id.toolbar, "Chi tiết chu kỳ dàn ĐB ngày mai");
        initView();
        getData();
    }

    private void getData() {
        temp = (SpeedTemp) getIntent().getSerializableExtra(Constants.TEMP);
        entity = (SpecialTomorowEntity) getIntent().getSerializableExtra(Constants.OBJECT);

        if (entity != null) {
            boolean north_area = false;
            if (entity.getSpecial() != null && entity.getDate() != null) {

                String loto_special_begin, loto_special_end;
                if (temp.getId_cat() == 9) {
                    north_area = true;
                    loto_special_begin = entity.getSpecial().substring(0, 3);
                    loto_special_end = entity.getSpecial().substring(3);
                } else {
                    north_area = false;
                    loto_special_begin = entity.getSpecial().substring(0, 4);
                    loto_special_end = entity.getSpecial().substring(4);
                }

                tv_title_loto_special.setText("Các kết quả mà ngày trước đó cũng có lô tô đặc biệt " + loto_special_end);
                String result = "Ngày <font color='red'>"
                        + TimeUtils.getFormatTimeClient(entity.getDate())
                        + "</font>, Giải đặc biệt "
                        + loto_special_begin
                        + "<font color='red'>"
                        + loto_special_end + "</font>";
                tv_date_loto_special.setText(Html.fromHtml(result));
                tv_date_loto_special_visible.setText("Ngày xuất hiện Loto ĐB " + loto_special_end);


                String result_yester_day = "Xem các kết quả đặc biệt đã về vào "
                        + CalendarUtils.getDayName(TimeUtils.getCalendarFromString(entity.getDate()))
                        + " sau khi ra " + loto_special_end + " vào ngày trước đó";

                tv_title_loto_special_yester_day.setText(result_yester_day);
                tv_date_loto_special_yester_day.setText(Html.fromHtml(result));
                tv_date_loto_special_visible_yester_day.setText("Ngày xuất hiện Loto ĐB " + loto_special_end);

                tv_title_loto_special_freq.setText("Thống kê tần suất loto đặc biệt sau khi giải ĐB xuất hiện " + loto_special_end);
            }

            if (entity.getNext_day() != null) {
                if (north_area) {
                    adapterToday.refreshData(entity.getNext_day(), 1);
                } else {
                    adapterToday.refreshData(entity.getNext_day(), 2);
                }
            }

            if (entity.getSame_dow() != null) {
                if (north_area) {
                    adapterYester_day.refreshData(entity.getSame_dow(), 1);
                } else {
                    adapterYester_day.refreshData(entity.getSame_dow(), 2);
                }
            }

            if (entity.getSame_day() != null) {
                String result_same_day = "Các giải đặc biệt ngày <font color='red'>" + getDayMonth(temp.getDate_begin()) + "</font> hàng năm";
                tv_title_special_all_year.setText(Html.fromHtml(result_same_day));
                if (north_area) {
                    adapterAllDayYear.refreshData(entity.getSame_day(), 1);
                } else {
                    adapterAllDayYear.refreshData(entity.getSame_day(), 2);
                }
            }

            if (entity.getFreq_stats() != null) {
                adapterFreqLoto.refreshData(entity.getFreq_stats());
            }

            if (entity.getStats().getBegin_with() != null) {
                if (entity.getStats().getBegin_with().getB0() != null && entity.getStats().getBegin_with().getB0().size() > 0) {
                    begin_list.add(new CustomBeginEndSum("0", String.valueOf(entity.getStats().getBegin_with().getB0().size())));
                }
                if (entity.getStats().getBegin_with().getB1() != null && entity.getStats().getBegin_with().getB1().size() > 0) {
                    begin_list.add(new CustomBeginEndSum("1", String.valueOf(entity.getStats().getBegin_with().getB1().size())));
                }
                if (entity.getStats().getBegin_with().getB2() != null && entity.getStats().getBegin_with().getB2().size() > 0) {

                    begin_list.add(new CustomBeginEndSum("2", String.valueOf(entity.getStats().getBegin_with().getB2().size())));
                }
                if (entity.getStats().getBegin_with().getB3() != null && entity.getStats().getBegin_with().getB3().size() > 0) {

                    begin_list.add(new CustomBeginEndSum("3", String.valueOf(entity.getStats().getBegin_with().getB3().size())));
                }
                if (entity.getStats().getBegin_with().getB4() != null && entity.getStats().getBegin_with().getB4().size() > 0) {

                    begin_list.add(new CustomBeginEndSum("4", String.valueOf(entity.getStats().getBegin_with().getB4().size())));
                }
                if (entity.getStats().getBegin_with().getB5() != null && entity.getStats().getBegin_with().getB5().size() > 0) {

                    begin_list.add(new CustomBeginEndSum("5", String.valueOf(entity.getStats().getBegin_with().getB5().size())));
                }
                if (entity.getStats().getBegin_with().getB6() != null && entity.getStats().getBegin_with().getB6().size() > 0) {

                    begin_list.add(new CustomBeginEndSum("6", String.valueOf(entity.getStats().getBegin_with().getB6().size())));
                }
                if (entity.getStats().getBegin_with().getB7() != null && entity.getStats().getBegin_with().getB7().size() > 0) {

                    begin_list.add(new CustomBeginEndSum("7", String.valueOf(entity.getStats().getBegin_with().getB7().size())));
                }
                if (entity.getStats().getBegin_with().getB8() != null && entity.getStats().getBegin_with().getB8().size() > 0) {

                    begin_list.add(new CustomBeginEndSum("8", String.valueOf(entity.getStats().getBegin_with().getB8().size())));
                }
                if (entity.getStats().getBegin_with().getB9() != null && entity.getStats().getBegin_with().getB9().size() > 0) {

                    begin_list.add(new CustomBeginEndSum("9", String.valueOf(entity.getStats().getBegin_with().getB9().size())));
                }
                adapterBegin.refreshData(begin_list);
            }

            if (entity.getStats().getEnd_with() != null) {

                if (entity.getStats().getEnd_with().getE0() != null && entity.getStats().getEnd_with().getE0().size() > 0) {
                    end_list.add(new CustomBeginEndSum("0", String.valueOf(entity.getStats().getEnd_with().getE0().size())));
                }
                if (entity.getStats().getEnd_with().getE1() != null && entity.getStats().getEnd_with().getE1().size() > 0) {
                    end_list.add(new CustomBeginEndSum("1", String.valueOf(entity.getStats().getEnd_with().getE1().size())));
                }
                if (entity.getStats().getEnd_with().getE2() != null && entity.getStats().getEnd_with().getE2().size() > 0) {
                    end_list.add(new CustomBeginEndSum("2", String.valueOf(entity.getStats().getEnd_with().getE2().size())));
                }
                if (entity.getStats().getEnd_with().getE3() != null && entity.getStats().getEnd_with().getE3().size() > 0) {
                    end_list.add(new CustomBeginEndSum("3", String.valueOf(entity.getStats().getEnd_with().getE3().size())));
                }
                if (entity.getStats().getEnd_with().getE4() != null && entity.getStats().getEnd_with().getE4().size() > 0) {
                    end_list.add(new CustomBeginEndSum("4", String.valueOf(entity.getStats().getEnd_with().getE4().size())));
                }
                if (entity.getStats().getEnd_with().getE5() != null && entity.getStats().getEnd_with().getE5().size() > 0) {
                    end_list.add(new CustomBeginEndSum("5", String.valueOf(entity.getStats().getEnd_with().getE5().size())));
                }
                if (entity.getStats().getEnd_with().getE6() != null && entity.getStats().getEnd_with().getE6().size() > 0) {
                    end_list.add(new CustomBeginEndSum("6", String.valueOf(entity.getStats().getEnd_with().getE6().size())));
                }
                if (entity.getStats().getEnd_with().getE7() != null && entity.getStats().getEnd_with().getE7().size() > 0) {
                    end_list.add(new CustomBeginEndSum("7", String.valueOf(entity.getStats().getEnd_with().getE7().size())));
                }
                if (entity.getStats().getEnd_with().getE8() != null && entity.getStats().getEnd_with().getE8().size() > 0) {
                    end_list.add(new CustomBeginEndSum("8", String.valueOf(entity.getStats().getEnd_with().getE8().size())));
                }
                if (entity.getStats().getEnd_with().getE9() != null && entity.getStats().getEnd_with().getE9().size() > 0) {
                    end_list.add(new CustomBeginEndSum("9", String.valueOf(entity.getStats().getEnd_with().getE9().size())));
                }
                adapterEnd.refreshData(end_list);
            }

            if (entity.getStats().getSum_equal() != null) {
                if (entity.getStats().getSum_equal().getS0() != null && entity.getStats().getSum_equal().getS0().size() > 0) {
                    sum_list.add(new CustomBeginEndSum("0", String.valueOf(entity.getStats().getSum_equal().getS0().size())));
                }
                if (entity.getStats().getSum_equal().getS1() != null && entity.getStats().getSum_equal().getS1().size() > 0) {
                    sum_list.add(new CustomBeginEndSum("1", String.valueOf(entity.getStats().getSum_equal().getS1().size())));
                }
                if (entity.getStats().getSum_equal().getS2() != null && entity.getStats().getSum_equal().getS2().size() > 0) {
                    sum_list.add(new CustomBeginEndSum("2", String.valueOf(entity.getStats().getSum_equal().getS2().size())));
                }
                if (entity.getStats().getSum_equal().getS3() != null && entity.getStats().getSum_equal().getS3().size() > 0) {
                    sum_list.add(new CustomBeginEndSum("3", String.valueOf(entity.getStats().getSum_equal().getS3().size())));
                }
                if (entity.getStats().getSum_equal().getS4() != null && entity.getStats().getSum_equal().getS4().size() > 0) {
                    sum_list.add(new CustomBeginEndSum("4", String.valueOf(entity.getStats().getSum_equal().getS4().size())));
                }
                if (entity.getStats().getSum_equal().getS5() != null && entity.getStats().getSum_equal().getS5().size() > 0) {
                    sum_list.add(new CustomBeginEndSum("5", String.valueOf(entity.getStats().getSum_equal().getS5().size())));
                }
                if (entity.getStats().getSum_equal().getS6() != null && entity.getStats().getSum_equal().getS6().size() > 0) {
                    sum_list.add(new CustomBeginEndSum("6", String.valueOf(entity.getStats().getSum_equal().getS6().size())));
                }
                if (entity.getStats().getSum_equal().getS7() != null && entity.getStats().getSum_equal().getS7().size() > 0) {
                    sum_list.add(new CustomBeginEndSum("7", String.valueOf(entity.getStats().getSum_equal().getS7().size())));
                }
                if (entity.getStats().getSum_equal().getS8() != null && entity.getStats().getSum_equal().getS8().size() > 0) {
                    sum_list.add(new CustomBeginEndSum("8", String.valueOf(entity.getStats().getSum_equal().getS8().size())));
                }
                if (entity.getStats().getSum_equal().getS9() != null && entity.getStats().getSum_equal().getS9().size() > 0) {
                    sum_list.add(new CustomBeginEndSum("9", String.valueOf(entity.getStats().getSum_equal().getS9().size())));
                }
                adapterSum.refreshData(sum_list);
            }
        }
    }

    private void initView() {
        tv_title_loto_special = findTextView(R.id.tv_title_loto_special);
        tv_date_loto_special = findTextView(R.id.tv_date_loto_special);
        tv_date_loto_special_visible = findTextView(R.id.tv_date_loto_special_visible);

        list_today = new ArrayList<>();

        rcl_loto_db = findRecyclerView(R.id.rcl_loto_db);
        rcl_loto_db.setLayoutManager(new LinearLayoutManager(this));
        adapterToday = new AdapterLotoDb(list_today, this);
        rcl_loto_db.setAdapter(adapterToday);

        /**
         * --------------------------------*/
        list_freq = new ArrayList<>();
        tv_title_loto_special_freq = findTextView(R.id.tv_title_loto_special_freq);
        rcl_number_freq = findRecyclerView(R.id.rcl_number_freq);
        rcl_number_freq.setLayoutManager(new GridLayoutManager(this, 3));
        adapterFreqLoto = new AdapterFreqLoto(list_freq, this);
        rcl_number_freq.setAdapter(adapterFreqLoto);
        /**
         * --------------------------------*/
        begin_list = new ArrayList<>();
        end_list = new ArrayList<>();
        sum_list = new ArrayList<>();

        tv_begin = findTextView(R.id.tv_begin);
        tv_begin.setText(Html.fromHtml("Đã về - <font color='red'>Đầu</font>"));
        tv_end = findTextView(R.id.tv_end);
        tv_end.setText(Html.fromHtml("Đã về - <font color='red'>Đuôi</font>"));
        tv_sum = findTextView(R.id.tv_sum);
        tv_sum.setText(Html.fromHtml("Đã về - <font color='red'>Tổng</font>"));

        rcl_touch_begin = findRecyclerView(R.id.rcl_touch_begin);
        rcl_touch_begin.setLayoutManager(new LinearLayoutManager(this));
        adapterBegin = new AdapterBeginEndSum(this);
        rcl_touch_begin.setAdapter(adapterBegin);

        rcl_touch_end = findRecyclerView(R.id.rcl_touch_end);
        rcl_touch_end.setLayoutManager(new LinearLayoutManager(this));
        adapterEnd = new AdapterBeginEndSum(this);
        rcl_touch_end.setAdapter(adapterEnd);

        rcl_touch_sum = findRecyclerView(R.id.rcl_touch_sum);
        rcl_touch_sum.setLayoutManager(new LinearLayoutManager(this));
        adapterSum = new AdapterBeginEndSum(this);
        rcl_touch_sum.setAdapter(adapterSum);

        /**
         * --------------------------------*/

        tv_title_loto_special_yester_day = findTextView(R.id.tv_title_loto_special_yester_day);
        tv_date_loto_special_yester_day = findTextView(R.id.tv_date_loto_special_yester_day);
        tv_date_loto_special_visible_yester_day = findTextView(R.id.tv_date_loto_special_visible_yester_day);

        list_yester_day = new ArrayList<>();
        rcl_loto_db_yester_day = findRecyclerView(R.id.rcl_loto_db_yester_day);
        rcl_loto_db_yester_day.setLayoutManager(new LinearLayoutManager(this));
        adapterYester_day = new AdapterLotoDb(list_yester_day, this);
        rcl_loto_db_yester_day.setAdapter(adapterYester_day);

        /**
         * --------------------------------*/
        list_same_day = new ArrayList<>();
        tv_title_special_all_year = findTextView(R.id.tv_title_special_all_year);
        rcl_special_all_year = findRecyclerView(R.id.rcl_special_all_year);
        rcl_special_all_year.setLayoutManager(new LinearLayoutManager(this));
        adapterAllDayYear = new AdapterAllDayYear(list_same_day, this);
        rcl_special_all_year.setAdapter(adapterAllDayYear);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public String getDayMonth(String date) {
        Calendar calendar = TimeUtils.getCalendarFromString(date);
        if (calendar != null) {
            return calendar.get(Calendar.DAY_OF_MONTH) + " - " + (calendar.get(Calendar.MONTH) + 1);
        } else {
            return "";
        }
    }
}
