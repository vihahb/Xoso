package com.xtelsolution.xoso.xoso.view.fragment.inf;

import com.xtelsolution.xoso.xoso.model.entity.GridMenu;
import com.xtelsolution.xoso.xoso.view.activity.inf.BasicView;

import java.util.List;

/**
 * Created by vivhp on 9/13/2017.
 */

public interface IFragmentExplore extends BasicView{
    void initGridMenu(List<GridMenu> menus);
}