package com.xtelsolution.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xtelsolution.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 9/12/2017.
 */

public class AdapterLoto extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> lotoList;
    Context context;

    public AdapterLoto(List<String> lotoList, Context context) {
        this.lotoList = lotoList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loto_analytics, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            String loto_value = lotoList.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            ((ViewHolder) holder).tv_loto.setText(loto_value);
        }
    }

    @Override
    public int getItemCount() {
        return lotoList.size();
    }

    public void refreshLotoList(List<String> loto_list) {
        lotoList.clear();
        lotoList.addAll(loto_list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_loto;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_loto = itemView.findViewById(R.id.tv_loto);
        }
    }
}
