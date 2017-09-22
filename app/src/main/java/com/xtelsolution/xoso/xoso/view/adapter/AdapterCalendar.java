package com.xtelsolution.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.xoso.model.entity.MyCalendar;
import com.xtelsolution.xoso.xoso.view.adapter.inf.ListCalendarView;

import java.util.List;

/**
 * Created by vivhp on 9/1/2017.
 */

public class AdapterCalendar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ListCalendarView calendarView;
    private List<MyCalendar> calendarList;

    public AdapterCalendar(Context context, ListCalendarView calendarView, List<MyCalendar> calendarList) {
        this.context = context;
        this.calendarView = calendarView;
        this.calendarList = calendarList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false);
        return new CalendarHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof CalendarHolder){
            final CalendarHolder calendarHolder = (CalendarHolder) holder;
            final MyCalendar calendar = calendarList.get(position);
            calendarHolder.setData(calendar);
            calendarHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    calendarHolder.itemView.setSelected(true);
                    String time_value = calendar.getYearValue() + "-" + calendar.getMonthValue() + "-" + calendar.getDateValue();
                    calendarView.onSelected(time_value, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return calendarList.size();
    }

    private class CalendarHolder extends RecyclerView.ViewHolder{

        private TextView tv_date_label, tv_date_value, tv_month_lable;
        private View selector;

        public CalendarHolder(View itemView) {
            super(itemView);
            tv_date_label = itemView.findViewById(R.id.tv_date_label);
            tv_date_value = itemView.findViewById(R.id.tv_date_value);
            tv_month_lable = itemView.findViewById(R.id.tv_month_value);
            selector = itemView.findViewById(R.id.selector);
        }

        public void setData(MyCalendar data){
            tv_date_label.setText(data.getDateLabel());
            tv_date_value.setText(String.valueOf(data.getDateValue()));
            tv_month_lable.setText(data.getMonthLabel());
            if (data.isSelectPosition()){
                selector.setVisibility(View.VISIBLE);
            } else {
                selector.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void refreshList(List<MyCalendar> calendarList){
        this.calendarList.addAll(calendarList);
        notifyDataSetChanged();
    }

}
