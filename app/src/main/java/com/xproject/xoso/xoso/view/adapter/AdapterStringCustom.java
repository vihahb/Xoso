package com.xproject.xoso.xoso.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xtelsolution.xoso.R;

import java.util.List;

/**
 * Created by vivhp on 10/6/2017.
 */

public class AdapterStringCustom extends BaseAdapter {
    List<String> list;
    Activity activity;
    LayoutInflater inflater;

    public AdapterStringCustom(List<String> list, Activity activity) {
        this.list = list;
        this.activity = activity;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_province, parent, false);
        }

        String result = list.get(position);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_province_name);
        TextView tv_icon = (TextView) convertView.findViewById(R.id.tv_icon);
        tv_icon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_action_select, 0, 0, 0);
        tv_name.setText(result);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_province, parent, false);
        }

        String result = list.get(position);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_province_name);
        TextView tv_icon = (TextView) convertView.findViewById(R.id.tv_icon);
        tv_icon.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        tv_name.setText(result);
        return convertView;
    }
}
