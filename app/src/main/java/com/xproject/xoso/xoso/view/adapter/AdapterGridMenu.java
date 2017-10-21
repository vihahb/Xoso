package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xproject.xoso.xoso.model.entity.GridMenu;
import com.xproject.xoso.xoso.view.adapter.inf.ViewGrid;
import com.xtelsolution.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 9/7/2017.
 */

public class AdapterGridMenu extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GridMenu> menuList;
    private Context context;
    private ViewGrid gridView;

    public AdapterGridMenu(List<GridMenu> menuList, Context context, ViewGrid gridView) {
        this.menuList = menuList;
        this.context = context;
        this.gridView = gridView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            GridMenu menu = menuList.get(position);
            viewHolder.setData(menu);
            viewHolder.setOnCLick(position);
        }
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public void refreshData(List<GridMenu> menuList) {
        if (this.menuList.size() > 0) {
            this.menuList.clear();
        }
        this.menuList.addAll(menuList);
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private ImageView img_icon;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.item_title);
            img_icon = (ImageView) itemView.findViewById(R.id.item_image);
        }

        public void setData(GridMenu data) {
            if (data.getName() != null) {
                tv_name.setText(data.getName());
            }

            if (data.getResource() != -1) {
                img_icon.setImageResource(data.getResource());
            }
        }

        public void setOnCLick(final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gridView.onClickItem(position);
                }
            });
        }
    }
}
