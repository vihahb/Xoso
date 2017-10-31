package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.SpecialCycleEntity;
import com.xproject.xoso.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class AdapterCycleSpecial extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<SpecialCycleEntity> list;
    Context context;

    public AdapterCycleSpecial(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cycle_special, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            SpecialCycleEntity cycleEntity = list.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(position, cycleEntity);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshData(List<SpecialCycleEntity> list_data) {
        if (list.size() > 0) {
            list.clear();
        }

        list.addAll(list_data);
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number, tv_last_append, tv_not_append, tv_max_gan;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_last_append = (TextView) itemView.findViewById(R.id.tv_last_append);
            tv_not_append = (TextView) itemView.findViewById(R.id.tv_not_append);
            tv_max_gan = (TextView) itemView.findViewById(R.id.tv_max_gan);
        }

        public void setData(int position, SpecialCycleEntity data) {
            tv_number.setText(String.valueOf(position));

            tv_not_append.setText(data.getDay_count() + " ngày");

            tv_last_append.setText(TimeUtils.getFormatTimeClient(data.getDate_appear()));
            tv_max_gan.setText(data.getMax_gan() + " ngày");
        }
    }
}
