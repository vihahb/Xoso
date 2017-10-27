package com.xproject.xoso.xoso.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xproject.xoso.xoso.model.entity.DrawerMenu;
import com.xproject.xoso.xoso.view.activity.inf.IHomeView;
import com.xtelsolution.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 9/1/2017.
 */

public class AdapterMenu extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DrawerMenu> menuList;
    private int DEFAULT_TYPE = 0, HEADER_TYPE = 1, ITEM_TYPE = 2;
    private Context context;
    private IHomeView viewHome;
    private int selectedPosition;

    public AdapterMenu(List<DrawerMenu> menuList, Context context, IHomeView view) {
        this.menuList = menuList;
        this.context = context;
        this.viewHome = view;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == DEFAULT_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_default_menu, parent, false);
            return new DefaultHolder(view);
        } else if (viewType == HEADER_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_header_menu, parent, false);
            return new HeaderHolder(view);
        } else if (viewType == ITEM_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_type_menu, parent, false);
            return new ItemHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof DefaultHolder) {
            DefaultHolder defaultHolder = (DefaultHolder) holder;
            DrawerMenu drawerMenu = menuList.get(position);
            defaultHolder.tv_name.setText(drawerMenu.getItemName());
            defaultHolder.item_icon.setImageResource(drawerMenu.getResource());
            Typeface typeface = Typeface.createFromAsset(viewHome.getActivity().getAssets(), "fonts/deftone stylus.ttf");
            defaultHolder.tv_name.setTypeface(typeface);
        }

        if (holder instanceof HeaderHolder) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            DrawerMenu drawerMenu = menuList.get(position);
            headerHolder.tv_name.setText(drawerMenu.getItemName());
        }

        if (holder instanceof ItemHolder) {
            ItemHolder itemHolder = (ItemHolder) holder;
            DrawerMenu drawerMenu = menuList.get(position);
            itemHolder.tv_name.setText(drawerMenu.getItemName());
            Picasso.with(context)
                    .load(drawerMenu.getResource())
                    .into(itemHolder.item_icon);
            if (drawerMenu.getItemTime() != null) {
                itemHolder.tv_time.setText(drawerMenu.getItemTime());
            }
            switch (drawerMenu.getLive_type()) {
                case 1:
                    itemHolder.tv_time.setTextColor(context.getResources().getColor(android.R.color.holo_blue_dark));
                    break;
                case 2:
                    itemHolder.tv_time.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    break;
                case 3:
                    itemHolder.tv_time.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_done, 0);
                    break;
            }
            itemHolder.initItemClick(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (menuList.get(position).getType() == HEADER_TYPE) {
            //return header item
            return HEADER_TYPE;
        } else if (menuList.get(position).getType() == ITEM_TYPE) {
            //return item
            return ITEM_TYPE;
        } else {
            //return logo
            return DEFAULT_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public void addList(List<DrawerMenu> menuList) {
        this.menuList.clear();
        this.menuList.addAll(menuList);
        Log.e("Adapter menu size", "Size " + this.menuList.size());
        notifyDataSetChanged();
    }

    public void changeLiveItem(int position) {
        switch (position) {
            case 1:
                menuList.get(2).setItemTime("LIVE");
                menuList.get(2).setLive_type(2);
                break;
            case 2:
                menuList.get(3).setLive_type(2);
                menuList.get(3).setItemTime("LIVE");
                break;
            case 3:
                menuList.get(4).setLive_type(2);
                menuList.get(4).setItemTime("LIVE");
                break;
        }
        notifyDataSetChanged();
    }

    public void changeLiveDone(int position) {
        switch (position) {
            case 1:
                menuList.get(2).setItemTime("    ");
                menuList.get(2).setLive_type(3);
                break;
            case 2:
                menuList.get(3).setLive_type(3);
                menuList.get(3).setItemTime("    ");
                break;
            case 3:
                menuList.get(4).setLive_type(3);
                menuList.get(4).setItemTime("    ");
                break;
        }
        notifyDataSetChanged();
    }

    public void changeEndLive(int position) {
        switch (position) {
            case 1:
                menuList.get(2).setItemTime("DONE");
                break;
            case 2:
                menuList.get(3).setItemTime("DONE");
                break;
            case 3:
                menuList.get(4).setItemTime("DONE");
                break;
        }
    }

    public void refreshItem() {
        notifyDataSetChanged();
    }

    private class DefaultHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private ImageView item_icon;

        public DefaultHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            item_icon = (ImageView) itemView.findViewById(R.id.item_icon);
        }
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;

        public HeaderHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_header_name);
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private TextView tv_name, tv_time;
        private ImageView item_icon;

        public ItemHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_item_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_item_time);
            item_icon = (ImageView) itemView.findViewById(R.id.ic_item_icon);
        }

        public void initItemClick(final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("MENU Item", "onClickPosition: " + position);
                    selectedPosition = position;
                    notifyDataSetChanged();
                    viewHome.itemDrawerClick(view, position);
                }
            });
            if (selectedPosition == position) {
                setItemClick(itemView);
            } else {
                setItemNonClick(itemView);
            }
        }

        private void setItemClick(View itemHolder) {
            itemView.setBackgroundColor(context.getResources().getColor(R.color.black_10));
        }

        private void setItemNonClick(View itemHolder) {
            itemView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
        }
    }
}
