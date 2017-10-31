package com.xProject.XosoVIP.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xProject.XosoVIP.sdk.utils.TimeUtils;
import com.xProject.XosoVIP.xoso.model.entity.CycleLotoEntity;
import com.xProject.XosoVIP.R;

import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class AdapterCycleLoto extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<CycleLotoEntity> list;
    Context context;

    public AdapterCycleLoto(List<CycleLotoEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loto_cycle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CycleLotoEntity entity = list.get(position);
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(entity);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshData(List<CycleLotoEntity> list_data) {
        if (list.size() > 0) {
            list.clear();
            list.addAll(list_data);
        } else {
            list.addAll(list_data);
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number, tv_cycle_last_append, tv_cycle_not_append;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_cycle_last_append = (TextView) itemView.findViewById(R.id.tv_cycle_last_append);
            tv_cycle_not_append = (TextView) itemView.findViewById(R.id.tv_cycle_not_append);
        }

        public void setData(CycleLotoEntity data) {
            if (String.valueOf(data.getNumber()).length() < 2) {
                tv_number.setText("0" + data.getNumber());
            } else {
                tv_number.setText(String.valueOf(data.getNumber()));
            }
            tv_cycle_last_append.setText(TimeUtils.getFormatTimeClient(data.getLast_appear()));
            tv_cycle_not_append.setText(Html.fromHtml("<font color='#eb2227'>" + TimeUtils.getFormatTimeClient(data.getStart()) + "</font> đến " + "<font color='#eb2227'>" + TimeUtils.getFormatTimeClient(data.getEnd() + "</font>")));
        }
    }
}
