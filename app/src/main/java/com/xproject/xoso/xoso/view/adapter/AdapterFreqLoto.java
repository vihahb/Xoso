package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.xoso.model.entity.Freq_statsEntity;
import com.xtelsolution.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 10/12/2017.
 */

public class AdapterFreqLoto extends RecyclerView.Adapter {

    List<Freq_statsEntity> list;
    Context context;

    public AdapterFreqLoto(List<Freq_statsEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_analy_loto_freq, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            Freq_statsEntity entity = list.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(entity);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshData(List<Freq_statsEntity> entityList) {
        if (list.size() > 0) {
            list.clear();
        }

        list.addAll(entityList);
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_count, tv_number;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
        }

        public void setData(Freq_statsEntity data) {
            tv_number.setText(String.valueOf(data.getNumber()));
            tv_count.setText(String.valueOf(data.getCount()));
        }
    }
}
