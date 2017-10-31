package com.xProject.XosoVIP.xoso.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xProject.XosoVIP.sdk.utils.TimeUtils;
import com.xProject.XosoVIP.xoso.model.entity.ImportantEntity;
import com.xProject.XosoVIP.R;

import java.util.List;

/**
 * Created by vivhp on 10/6/2017
 */

public class AdapterImportant extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ImportantEntity> entityList;
    private int type = -1;

    public AdapterImportant(Context context, List<ImportantEntity> entityList) {
        this.context = context;
        this.entityList = entityList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_important, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ImportantEntity entity = entityList.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.setData(entity, type);
        }
    }

    @Override
    public int getItemCount() {
        return entityList.size();
    }

    public void refresh(List<ImportantEntity> list, int type) {
        if (list.size() > 0) {
            this.entityList.clear();
        }
        this.type = type;
        this.entityList.addAll(list);
        notifyDataSetChanged();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_number, tv_date_not_appen, tv_date_last_append, tv_total_append;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            tv_date_not_appen = (TextView) itemView.findViewById(R.id.tv_date_not_appen);
            tv_date_last_append = (TextView) itemView.findViewById(R.id.tv_date_last_append);
            tv_total_append = (TextView) itemView.findViewById(R.id.tv_total_append);
        }

        public void setData(ImportantEntity data, int type) {
            if (String.valueOf(data.getNumber()).length() < 2) {
                tv_number.setText("0" + String.valueOf(data.getNumber()));
            } else {
                tv_number.setText(String.valueOf(data.getNumber()));
            }

            switch (type) {
                case 0:
                    tv_date_not_appen.setText(String.valueOf(data.getNo_last()));
                    break;
                case 1:
                    tv_date_not_appen.setText(String.valueOf(data.getNo_last()));
                    break;
                case 2:
                    tv_date_not_appen.setText(String.valueOf(data.getNo_last()));
                    break;
                case 3:
                    tv_date_not_appen.setText(String.valueOf(data.getMax_streak()));
                    break;
                case 4:
                    tv_date_not_appen.setText(String.valueOf(data.getMax_streak()));
                    break;
            }


            tv_date_last_append.setText(TimeUtils.getFormatTimeClient(data.getLast_appear()));
            tv_total_append.setText(String.valueOf(data.getCount()));
        }
    }
}
