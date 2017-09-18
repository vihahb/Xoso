package com.xtelsolution.xoso.xoso.view.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.xoso.model.entity.MyCalendar;

import java.util.List;

/**
 * Created by vivhp on 9/1/2017.
 */

public class AdapterCalendar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MyCalendar> calendarList;
    private Context context;

    public AdapterCalendar(Context context, List<MyCalendar> calendarList) {
        this.context = context;
        this.calendarList = calendarList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CalendarHolder){
            CalendarHolder calendarHolder = (CalendarHolder) holder;
            MyCalendar calendar = calendarList.get(position);
            calendarHolder.setData(calendar);
        }
    }

    @Override
    public int getItemCount() {
        return calendarList.size();
    }

    private class CalendarHolder extends RecyclerView.ViewHolder{

        private TextView tv_date_label, tv_date_value;

        public CalendarHolder(View itemView) {
            super(itemView);
            tv_date_label = itemView.findViewById(R.id.tv_week_day);
            tv_date_value = itemView.findViewById(R.id.tv_week_value);
        }

        public void setData(MyCalendar data){
            tv_date_label.setText(data.getDateLabel());
            tv_date_value.setText(String.valueOf(data.getDateValue()));
            if (data.isSelectPosition()){
                tv_date_value.setBackgroundResource(R.drawable.selected_day);
            } else {
                tv_date_value.setBackgroundResource(android.R.color.transparent);
            }
        }
    }

    private void refreshList(List<MyCalendar> calendarList){
        this.calendarList.clear();
        this.calendarList.addAll(calendarList);
        notifyDataSetChanged();
    }

}
