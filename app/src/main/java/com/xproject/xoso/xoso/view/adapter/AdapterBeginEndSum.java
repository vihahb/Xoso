package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.xoso.model.entity.CustomBeginEndSum;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivhp on 10/12/2017.
 */

public class AdapterBeginEndSum extends RecyclerView.Adapter {
    List<CustomBeginEndSum> list;
    Context context;

    public AdapterBeginEndSum(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_touch, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            CustomBeginEndSum entity = list.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(entity);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshData(List<CustomBeginEndSum> entityList) {
        if (list.size() > 0) {
            list.clear();
        }
        list.addAll(entityList);
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
        }

        public void setData(CustomBeginEndSum data) {
            tv_number.setText(data.getCount() + " lần");
        }
    }
}
