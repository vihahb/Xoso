package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xproject.xoso.xoso.model.entity.LotoEnd;
import com.xproject.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 9/26/2017.
 */

public class AdapterLotoEnd extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "AdapterLotoEnd";
    List<LotoEnd> lotoEnd;
    private Context context;

    public AdapterLotoEnd(List<LotoEnd> lotoEnd, Context context) {
        this.lotoEnd = lotoEnd;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_end, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            LotoEnd data = lotoEnd.get(position);
            viewHolder.setData(data);
        }
    }

    @Override
    public int getItemCount() {
        return lotoEnd.size();
    }

    public void refreshAdapter(List<LotoEnd> endList) {
        Log.e(TAG, "refreshAdapter: End Size " + lotoEnd.size());
        this.lotoEnd.clear();
        this.lotoEnd.addAll(endList);
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_label, tv_value;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_label = (TextView) itemView.findViewById(R.id.tvDuoiLabel);
            tv_value = (TextView) itemView.findViewById(R.id.tvLotoDuoivalue);
        }

        private void setData(LotoEnd data) {
            if (data != null) {
                tv_label.setText(data.getLabel());
                tv_value.setText(data.getValue());
            }
        }
    }
}
