package com.xtelsolution.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xtelsolution.xoso.R;
import com.xtelsolution.xoso.xoso.model.entity.Dream;
import com.xtelsolution.xoso.xoso.view.activity.inf.IDream;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by vivhp on 9/9/2017.
 */

public class AdapterDream extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Dream> list;
    private IDream interfaces;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private boolean isLoadMore;


    public AdapterDream(Context context, List<Dream> list, IDream interfaces) {
        this.context = context;
        this.list = list;
        this.interfaces = interfaces;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_dream, parent, false);
            return new ItemHolder(view);
        }
        else if (viewType == VIEW_TYPE_LOADING){
            View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_item, parent, false);
            return new LoadingHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder){
            ItemHolder itemHolder = (ItemHolder) holder;
            Dream dream = list.get(position);
            itemHolder.setData(dream);
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

    private class ItemHolder extends RecyclerView.ViewHolder{

        private TextView tv_dream_id, tv_dream_name, tv_dream_number;

        public ItemHolder(View itemView) {
            super(itemView);
            tv_dream_id = itemView.findViewById(R.id.tv_dream_id);
            tv_dream_name = itemView.findViewById(R.id.tv_dream_name);
            tv_dream_number = itemView.findViewById(R.id.tv_dream_number);
        }

        public void setData(Dream data){
            tv_dream_id.setText(String.valueOf(data.getDream_id()));
            tv_dream_name.setText(data.getDreamed());
            tv_dream_number.setText(data.getNumber());
        }
    }

    private class LoadingHolder extends RecyclerView.ViewHolder{

        private ProgressBar progressBar;

        public LoadingHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar1);
        }
    }

    public void setLoadMore(boolean isLoadMore){
        this.isLoadMore = isLoadMore;
    }

    public void setData(List<Dream> data){
        if (list.size() == 0){
            list.addAll(data);
        } else {
            list.addAll(list.size(), data);
        }
        notifyDataSetChanged();
    }
    public void updateList(List<Dream> list){
        list.clear();
        this.list = list;
        Log.e(TAG, "updateList: " + list.size());
        notifyDataSetChanged();
    }

}
