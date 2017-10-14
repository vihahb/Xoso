package com.xproject.xoso.xoso.view.activity.inf;

import android.view.View;

import com.xproject.xoso.xoso.model.entity.DrawerMenu;

import java.util.List;

/**
 * Created by vivhp on 9/5/2017.
 */

public interface IHomeView extends BasicView{

    void initMenuDrawer(List<DrawerMenu> menuList);

    void itemDrawerClick(View view, int position);
}
