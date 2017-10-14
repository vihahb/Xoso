package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.SameDow_NextDayEntity;
import com.xtelsolution.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 10/12/2017.
 */

public class AdapterLotoDb extends RecyclerView.Adapter {

    List<SameDow_NextDayEntity> list;
    Context context;

    public AdapterLotoDb(List<SameDow_NextDayEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_table_loto_db, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            SameDow_NextDayEntity entity = list.get(position);
            viewHolder.setData(entity);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_day, tv_special_value, tv_next_day, tv_next_special_value;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_day = (TextView) itemView.findViewById(R.id.tv_day);
            tv_special_value = (TextView) itemView.findViewById(R.id.tv_special_value);
            tv_next_day = (TextView) itemView.findViewById(R.id.tv_next_day);
            tv_next_special_value = (TextView) itemView.findViewById(R.id.tv_next_special_value);
        }

        public void setData(SameDow_NextDayEntity data) {
            if (data.getToday() != null)
                tv_day.setText(TimeUtils.getFormatTimeClient(data.getToday()));

            if (data.getSpecial() != null) {
                tv_special_value.setText(Html.fromHtml(setColorRed(data.getSpecial())));
            }

            if (data.getNext_day() != null)
                tv_next_day.setText(TimeUtils.getFormatTimeClient(data.getNext_day()));

            if (data.getNext_special() != null) {
                tv_next_special_value.setText(Html.fromHtml(setColorRed(data.getNext_special())));
            }
        }
    }

    public String setColorRed(String begin_String) {
        if (begin_String != null && !begin_String.equals("")) {
            String start_char = begin_String.substring(0, 3);
            String end_char = begin_String.substring(3);
            return start_char + "<font color='red'>" + end_char + "</font>";
        } else {
            return "";
        }
    }

    public void refreshData(List<SameDow_NextDayEntity> entityList){
        if (list.size() > 0){
            list.clear();
        }

        list.addAll(entityList);
        notifyDataSetChanged();
    }
}
