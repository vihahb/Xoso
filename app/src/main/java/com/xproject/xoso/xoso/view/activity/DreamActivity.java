package com.xproject.xoso.xoso.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import com.xproject.xoso.sdk.utils.TextUtils;
import com.xproject.xoso.xoso.model.entity.Dream;
import com.xproject.xoso.xoso.presenter.activity.DreamPresenter;
import com.xproject.xoso.xoso.view.activity.inf.IDream;
import com.xproject.xoso.xoso.view.adapter.AdapterDream;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DreamActivity extends BasicActivity implements IDream {


    EditText searchView;
    private RecyclerView rcl_dream;
    private List<Dream> dreamList;
    private AdapterDream adapter;
    private DreamPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dream);
        presenter = new DreamPresenter(this);
        initToolbar(R.id.toolbar, getString(R.string.title_activity_dream));
        initWidget();
    }

    private void initWidget() {
        searchView = (EditText) findViewById(R.id.edt_search_dream);
        dreamList = new ArrayList<>();
        rcl_dream = findRecyclerView(R.id.rcl_dream);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DreamActivity.this);
        rcl_dream.setLayoutManager(layoutManager);
        rcl_dream.setHasFixedSize(true);
        adapter = new AdapterDream(DreamActivity.this, this);
        rcl_dream.setAdapter(adapter);
        presenter.getDreamList();
        initFilter();
    }

    private void initFilter() {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<Dream> dreams_tmp = new ArrayList<>();
                if (charSequence.length() >= 3) {
//                    filter(searchView.toString());
                    dreams_tmp = getFillterList1(charSequence);
                    adapter.updateList(dreams_tmp);
                } else {
                    presenter.getDreamList();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private List<Dream> getFillterList(CharSequence charSequence) {
        List<Dream> list = new ArrayList<>();
        for (int i = 0; i < dreamList.size(); i++){
            if(dreamList.get(i).getDreamed_unicoed() != null && dreamList.get(i).getDreamed_unicoed().contains(charSequence.toString())) {
                list.add(dreamList.get(i));
            }
        }

        Set<Dream> dreamSet = new HashSet<>();
        dreamSet.addAll(list);


        return list;
    }

    private List<Dream> getFillterList1(CharSequence charSequence) {
        String searchKey =charSequence.toString();
        List<Dream> list = new ArrayList<>();

        for (Dream dream : dreamList){
            if (dream.getDreamed_unicoed().contains(searchKey)){
                list.add(dream);
            }
        }

        return list;
    }

    @Override
    public void getDreamSuccess(List<Dream> list) {
        adapter.setData(list);
        if (dreamList.size() > 0){
            dreamList.clear();
        }
        dreamList = list;
    }

    @Override
    public void getDreamFind(List<Dream> list) {
        adapter.updateList(list);
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
