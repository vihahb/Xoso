package com.xproject.xoso.xoso.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.xproject.xoso.xoso.view.activity.inf.IDream;
import com.xproject.xoso.xoso.view.adapter.AdapterDream;
import com.xtelsolution.xoso.R;
import com.xproject.xoso.xoso.model.entity.Dream;
import com.xproject.xoso.xoso.presenter.activity.DreamPresenter;

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
        searchView = (EditText) findViewById(R.id.search_dream);
        dreamList = new ArrayList<>();
        rcl_dream = findRecyclerView(R.id.rcl_dream);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DreamActivity.this);
        rcl_dream.setLayoutManager(layoutManager);
        rcl_dream.setHasFixedSize(true);
        adapter = new AdapterDream(DreamActivity.this, dreamList, this);
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
                if (charSequence.length() > 1){
//                    filter(searchView.toString());
                    presenter.findProductByName(charSequence.toString());
                } else {
                    presenter.getDreamList();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void getDreamSuccess(List<Dream> list) {
        adapter.setData(list);
    }

    @Override
    public void getDreamFind(List<Dream> list) {
        adapter.updateList(list);
    }

    @Override
    public void onLoadMore() {

    }

//    void filter(String text){
//        List<Dream> temp = new ArrayList<>();
//        text = TextUtils.getInstance().unicodeToKoDauLowerCase(text);
//        for(Dream d: dreamList){
//            //or use .equal(text) with you want equal match
//            //use .toLowerCase() for better matches
//            if(text.equals(TextUtils.getInstance().unicodeToKoDauLowerCase(d.getDreamed()))){
//                temp.add(d);
//                Log.e("Fill value", "filter: " + d.toString());
//            }
//        }
//        //update recyclerview
//        adapter.updateList(temp);
//    }
}
