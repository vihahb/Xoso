package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 10/9/2017.
 */

public class AdapterRecyclerViewString extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> list;

    public AdapterRecyclerViewString(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loto_auto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            String number = list.get(position);
            viewHolder.setDat(number);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshData(List<String> data) {
        if (list.size() > 0) {
            list.clear();
            list.addAll(data);
        } else {
            list.addAll(data);
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_number;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
        }

        public void setDat(String message) {
            if (message != null) {
                tv_number.setText(message);
            }
        }
    }
}
