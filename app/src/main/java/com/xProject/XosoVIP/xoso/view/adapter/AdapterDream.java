package com.xProject.XosoVIP.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xProject.XosoVIP.xoso.model.entity.Dream;
import com.xProject.XosoVIP.xoso.view.activity.inf.IDream;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by vivhp on 9/9/2017.
 */

public class AdapterDream extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private Context context;
    private List<Dream> list;
    private IDream interfaces;
    private boolean isLoadMore;


    public AdapterDream(Context context, IDream interfaces) {
        this.context = context;
        this.list = new ArrayList<>();
        this.interfaces = interfaces;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_dream, parent, false);
            return new ItemHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_item, parent, false);
            return new LoadingHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            ItemHolder itemHolder = (ItemHolder) holder;
            Dream dream = list.get(position);
            itemHolder.setData(dream);
            if (position == list.size() -1){
                itemHolder.itemView.setBackgroundResource(R.drawable.background_left_right_bot);
            } else {
                itemHolder.itemView.setBackgroundResource(R.drawable.border_without_top_bot);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    public void setLoadMore(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    public void setData(List<Dream> data) {
        if (list.size() == 0) {
            list.addAll(data);
        } else {
            list.addAll(list.size(), data);
        }
        notifyDataSetChanged();
    }

    public void updateList(List<Dream> list) {
        if (this.list.size() > 0) {
            this.list.clear();
        }
        this.list.addAll(list);
        Log.e(TAG, "updateList: " + list.size());
        notifyDataSetChanged();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private TextView tv_dream_id, tv_dream_name, tv_dream_number;

        public ItemHolder(View itemView) {
            super(itemView);
            tv_dream_id = (TextView) itemView.findViewById(R.id.tv_dream_id);
            tv_dream_name = (TextView) itemView.findViewById(R.id.tv_dream_name);
            tv_dream_number = (TextView) itemView.findViewById(R.id.tv_dream_number);
        }

        public void setData(Dream data) {
            tv_dream_id.setText(String.valueOf(data.getDream_id()));
            tv_dream_name.setText(data.getDreamed());
            tv_dream_number.setText(data.getNumber().substring(2));
        }
    }

    private class LoadingHolder extends RecyclerView.ViewHolder {

        private ProgressBar progressBar;

        public LoadingHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }

}
