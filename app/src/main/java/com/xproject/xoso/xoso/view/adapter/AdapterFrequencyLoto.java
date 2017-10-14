package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.sdk.utils.CalendarUtils;
import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.FrequencyLotoEntity;
import com.xtelsolution.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 10/11/2017.
 */

public class AdapterFrequencyLoto extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<FrequencyLotoEntity> entityList;
    Context context;

    public AdapterFrequencyLoto(List<FrequencyLotoEntity> entityList, Context context) {
        this.entityList = entityList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_frequency_loto_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            FrequencyLotoEntity entity = entityList.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            if (entity.getDate_between()!=null){
                viewHolder.setData(entity);
            }
        }
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_day, tv_day_of_week, tv_count_append, tv_count_visible;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_day = (TextView) itemView.findViewById(R.id.tv_day);
            tv_day_of_week = (TextView) itemView.findViewById(R.id.day_of_week);
            tv_count_append = (TextView) itemView.findViewById(R.id.tv_count_append);
            tv_count_visible = (TextView) itemView.findViewById(R.id.tv_count_visible);
        }

        public void setData(FrequencyLotoEntity data) {
            tv_day.setText(TimeUtils.getFormatTimeClient(data.getDate()));
            tv_count_visible.setText(String.valueOf(data.getDate_between()));
            tv_count_append.setText(String.valueOf(data.getCount()));

            if (TimeUtils.getCalendarFromString(data.getDate())!=null){
                tv_day_of_week.setText(CalendarUtils.getDayName(TimeUtils.getCalendarFromString(data.getDate())));
            }
        }
    }

    public void refreshData(List<FrequencyLotoEntity> frequencyLotoEntityList) {
        if (entityList.size() > 0) {
            entityList.clear();
        }
        entityList.addAll(frequencyLotoEntityList);
        notifyDataSetChanged();
    }
}
