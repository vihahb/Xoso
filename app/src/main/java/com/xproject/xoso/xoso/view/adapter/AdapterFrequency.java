package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.FrequencyEntity;
import com.xtelsolution.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 10/6/2017.
 */

public class AdapterFrequency extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FrequencyEntity> list;
    private Context context;

    public AdapterFrequency(List<FrequencyEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_frequency, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FrequencyEntity setNumber = list.get(position);
        if (holder instanceof Viewholder) {
            Viewholder viewholder = (Viewholder) holder;
            viewholder.setData(setNumber);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshData(List<FrequencyEntity> list) {
        if (list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    class Viewholder extends RecyclerView.ViewHolder {

        private TextView tv_number, tv_count_day, tv_count_time, tv_last_visible;

        public Viewholder(View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_count_day = (TextView) itemView.findViewById(R.id.tv_total_count_day);
            tv_count_time = (TextView) itemView.findViewById(R.id.tv_total_count_time);
            tv_last_visible = (TextView) itemView.findViewById(R.id.tv_last_visible);
        }

        public void setData(FrequencyEntity data) {
            if (data.getNumber().length() < 2) {
                tv_number.setText("0" + data.getNumber());
            } else {
                tv_number.setText(data.getNumber());
            }
            tv_count_day.setText(String.valueOf(data.getCount_day()));
            tv_count_time.setText(String.valueOf(data.getCount_time()));
            tv_last_visible.setText(TimeUtils.getFormatTimeClient(data.getLast_appear()));
        }
    }
}
