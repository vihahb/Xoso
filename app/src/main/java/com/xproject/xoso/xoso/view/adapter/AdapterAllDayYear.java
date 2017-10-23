package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.sdk.utils.TimeUtils;
import com.xproject.xoso.xoso.model.entity.SameDayEntity;
import com.xtelsolution.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 10/12/2017.
 */

public class AdapterAllDayYear extends RecyclerView.Adapter {
    List<SameDayEntity> list;
    Context context;
    private int type = -1;

    public AdapterAllDayYear(List<SameDayEntity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_special_all_year, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            SameDayEntity entity = list.get(position);
            viewHolder.setData(entity);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public String setColorRed(String begin_String) {
        if (begin_String != null && !begin_String.equals("")) {
            String start_char = null;
            String end_char = null;
            if (type == 1) {
                start_char = begin_String.substring(0, 3);
                end_char = begin_String.substring(3);
            } else if (type == 2) {
                if (begin_String.length() == 6){
                    start_char = begin_String.substring(0, 4);
                    end_char = begin_String.substring(4);
                } else {
                    start_char = begin_String.substring(0, 3);
                    end_char = begin_String.substring(3);
                }
            }

            return start_char + "<font color='red'>" + end_char + "</font>";
        } else {
            return "";
        }
    }

    public void refreshData(List<SameDayEntity> entityList, int type) {
        if (list.size() > 0) {
            list.clear();
        }
        this.type = type;

        list.addAll(entityList);
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_day, tv_special_value;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_day = (TextView) itemView.findViewById(R.id.tv_date);
            tv_special_value = (TextView) itemView.findViewById(R.id.tv_special_value);
        }

        public void setData(SameDayEntity data) {
            if (data.getDay() != null)
                tv_day.setText(TimeUtils.getFormatTimeClient(data.getDay()));

            if (data.getSpecial() != null) {
                tv_special_value.setText(Html.fromHtml(setColorRed(data.getSpecial())));
            }
        }
    }
}
