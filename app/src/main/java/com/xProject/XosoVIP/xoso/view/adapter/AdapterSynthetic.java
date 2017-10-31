package com.xProject.XosoVIP.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xProject.XosoVIP.sdk.utils.TimeUtils;
import com.xProject.XosoVIP.xoso.model.entity.AnalyticsSetNumber;
import com.xProject.XosoVIP.R;

import java.util.List;

/**
 * Created by vivhp on 10/9/2017.
 */

public class AdapterSynthetic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<AnalyticsSetNumber> list;
    private Context context;

    public AdapterSynthetic(List<AnalyticsSetNumber> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_synthetic, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AnalyticsSetNumber setNumber = list.get(position);
        if (holder instanceof Viewholder) {
            Viewholder viewholder = (Viewholder) holder;
            viewholder.setData(setNumber);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshData(List<AnalyticsSetNumber> list) {
        if (list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    private class Viewholder extends RecyclerView.ViewHolder {

        private TextView tv_number, tv_count, tv_last_visible, tv_not_append;

        public Viewholder(View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_count = (TextView) itemView.findViewById(R.id.tv_count);
            tv_last_visible = (TextView) itemView.findViewById(R.id.tv_last_visible);
            tv_not_append = (TextView) itemView.findViewById(R.id.tv_not_append);
        }

        public void setData(AnalyticsSetNumber data) {
            if (data.getLast_appear() != null || data.getLast_appear() != "") {
                if (data.getNumber().length() > 1) {
                    tv_number.setText(data.getNumber());
                } else if (data.getNumber().length() > 0 && data.getNumber().length() == 1) {
                    tv_number.setText("0" + data.getNumber());
                }
                tv_count.setText(String.valueOf(data.getCount()));
                tv_last_visible.setText(TimeUtils.getFormatTimeClient(data.getLast_appear()));
                int not_append_count = TimeUtils.subtractionDay(TimeUtils.getToday(), data.getLast_appear());
                if (not_append_count > 0) {
                    tv_not_append.setText(String.valueOf(not_append_count));
                }
            }
        }

    }
//
//    private void checkItemEmptyLastAppend(int position) {
//        if (list.get(position).getLast_appear().equals("") || list.get(position).getLast_appear() == null) {
//            list.remove(position);
//            notifyItemRemoved(position);
//            notifyItemRangeRemoved(position, list.size()-1);
//        }
//    }
}
