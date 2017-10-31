package com.xProject.XosoVIP.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.xProject.XosoVIP.sdk.utils.TextUtils;
import com.xProject.XosoVIP.xoso.model.entity.Dream;
import com.xProject.XosoVIP.xoso.presenter.activity.DreamPresenter;
import com.xProject.XosoVIP.xoso.view.activity.inf.IDream;
import com.xProject.XosoVIP.xoso.view.adapter.AdapterDream;
import com.xProject.XosoVIP.R;

import java.util.ArrayList;
import java.util.List;

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
                if (charSequence.length() >= 1) {
//                    filter(searchView.toString());
                    dreams_tmp = getFillterList(charSequence);
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
        String result = TextUtils.getInstance().unicodeToKoDauLowerCase(charSequence.toString()).trim().replaceAll(" ", "");
        List<Dream> list = new ArrayList<>();
        for (int i = 0; i < dreamList.size(); i++) {
            Log.e("filler", "getFillterList: " + dreamList.get(i).getDreamed_unicoed() + " - " + result);
            if (dreamList.get(i).getDreamed_unicoed() != null && dreamList.get(i).getDreamed_unicoed().contains(result)) {
                list.add(dreamList.get(i));
            }
        }
        return list;
    }

    private List<Dream> getFillterList1(CharSequence charSequence) {
        String searchKey = charSequence.toString();
        List<Dream> list = new ArrayList<>();

        for (Dream dream : dreamList) {
            if (dream.getDreamed_unicoed().contains(searchKey)) {
                list.add(dream);
            }
        }

        return list;
    }

    @Override
    public void getDreamSuccess(List<Dream> list) {
        adapter.setData(list);
        if (dreamList.size() > 0) {
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
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
