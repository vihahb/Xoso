package com.xProject.XosoVIP.xoso.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.R;

import java.util.List;

/**
 * Created by vivhp on 10/4/2017.
 */

public class AdapterSpinner extends BaseAdapter {

    List<ProvinceEntity> provinceList;
    LayoutInflater inflater;
    private Activity context;
    private boolean intro = false;

    public AdapterSpinner(List<ProvinceEntity> provinceList, Activity context) {
        this.provinceList = provinceList;
        this.context = context;
        inflater = context.getLayoutInflater();
    }

    public AdapterSpinner(List<ProvinceEntity> provinceList, Activity context, boolean intro) {
        this.provinceList = provinceList;
        this.context = context;
        inflater = context.getLayoutInflater();
        this.intro = intro;
    }

    @Override
    public int getCount() {
        return provinceList.size();
    }

    @Override
    public Object getItem(int position) {
        return provinceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return provinceList.get(position).getMavung();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_province, parent, false);
        }

        ProvinceEntity entity = provinceList.get(position);
        TextView tv_province_name = (TextView) convertView.findViewById(R.id.tv_province_name);
        TextView tv_icon = (TextView) convertView.findViewById(R.id.tv_icon);
        if (intro){
            tv_icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_up, 0, 0, 0);
            tv_province_name.setTextColor(context.getResources().getColor(android.R.color.white));
        } else {
            tv_icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_select, 0, 0, 0);
        }
        tv_province_name.setText(entity.getTenvung());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_province, parent, false);
        }

        ProvinceEntity entity = provinceList.get(position);
        TextView tv_province_name = (TextView) convertView.findViewById(R.id.tv_province_name);
        TextView tv_icon = (TextView) convertView.findViewById(R.id.tv_icon);

        tv_icon.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        tv_province_name.setText(entity.getTenvung());

        return convertView;
    }
}
