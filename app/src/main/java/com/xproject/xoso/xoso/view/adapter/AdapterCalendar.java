package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xproject.xoso.xoso.model.entity.MyCalendar;
import com.xproject.xoso.xoso.view.widget.RecyclerTabLayout;
import com.xproject.xoso.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 9/1/2017.
 */

public class AdapterCalendar extends RecyclerTabLayout.Adapter<AdapterCalendar.CalendarHolder> {

    int selectedPosition = -1;
    private Context context;
    private List<MyCalendar> calendarList;
    private ViewPager viewPager;

    public AdapterCalendar(ViewPager viewPager, Context context) {
        super(viewPager);
        this.context = context;
        this.calendarList = new ArrayList<>();
        this.viewPager = viewPager;
    }

    @Override
    public CalendarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false);
        return new CalendarHolder(view);
    }

    @Override
    public void onBindViewHolder(CalendarHolder holder, final int position) {
        if (holder instanceof CalendarHolder) {
            final CalendarHolder calendarHolder = (CalendarHolder) holder;
            final MyCalendar calendar = calendarList.get(position);
            calendarHolder.setData(calendar);
            calendarHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem(position);
                    calendarHolder.itemView.setSelected(true);
                    String time_value = calendar.getYearValue() + "-" + calendar.getMonthValue() + "-" + calendar.getDateValue();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return calendarList.size();
    }

    public void refreshList(List<MyCalendar> calendarList) {
        if (this.calendarList.size() > 0){
            this.calendarList.clear();
        }
        this.calendarList.addAll(calendarList);
        notifyDataSetChanged();
    }

    public void addAllData(List<MyCalendar> calendarList){
        this.calendarList.addAll(calendarList);
        notifyDataSetChanged();
    }

    public class CalendarHolder extends RecyclerView.ViewHolder {

        private TextView tv_date_label, tv_date_value, tv_month_lable;
        private LinearLayout ln_view;

        public CalendarHolder(View itemView) {
            super(itemView);
            tv_date_label = (TextView) itemView.findViewById(R.id.tv_date_label);
            tv_date_value = (TextView) itemView.findViewById(R.id.tv_date_value);
            tv_month_lable = (TextView) itemView.findViewById(R.id.tv_month_value);
            ln_view = (LinearLayout) itemView.findViewById(R.id.ln_view);
        }

        public void setData(MyCalendar data) {
            tv_date_label.setText(data.getDateLabel());
            tv_date_value.setText(String.valueOf(data.getDateValue()));
            tv_month_lable.setText(data.getMonthLabel());
            if (data.getDateLabel().equals("TH 7") || data.getDateLabel().equals("CN")) {
                tv_date_value.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                tv_date_label.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            } else {
                tv_date_value.setTextColor(context.getResources().getColor(R.color.black_85));
                tv_date_label.setTextColor(context.getResources().getColor(R.color.black_85));
            }
        }
    }

}
