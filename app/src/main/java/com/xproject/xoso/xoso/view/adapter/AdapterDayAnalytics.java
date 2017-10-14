package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.xoso.model.entity.AnalyticsSetNumber;
import com.xtelsolution.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 10/9/2017.
 */

public class AdapterDayAnalytics extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<AnalyticsSetNumber> data;
    private Context context;

    public AdapterDayAnalytics(List<AnalyticsSetNumber> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_day_analytics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AnalyticsSetNumber setNumber = data.get(position);
        if (holder instanceof  ViewHolder){
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(setNumber);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_number;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
        }

        public void setData(AnalyticsSetNumber number){
            if (String.valueOf(number.getNumber()).length() > 1){
                String result  = "<font color='red'>" + number.getNumber() +"</font> (" + number.getCount() + " lần),";
                tv_number.setText(Html.fromHtml(result));
            } else {
                String result  = "<font color='red'>0" + number.getNumber() +"</font> (" + number.getCount() + " lần),";
                tv_number.setText(Html.fromHtml(result));
            }
        }
    }

    public void refreshData(List<AnalyticsSetNumber> list){
        this.data.clear();
        this.data.addAll(list);
        notifyDataSetChanged();
    }
}
