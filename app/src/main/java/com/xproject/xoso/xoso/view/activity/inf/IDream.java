package com.xproject.xoso.xoso.view.activity.inf;

import com.xproject.xoso.xoso.model.entity.Dream;

import java.util.List;

/**
 * Created by vivhp on 9/9/2017.
 */

public interface IDream extends BasicView{

    void getDreamSuccess(List<Dream> list);
    void getDreamFind(List<Dream> list);
    void onLoadMore();
}
