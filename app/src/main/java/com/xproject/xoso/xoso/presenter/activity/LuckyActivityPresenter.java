package com.xproject.xoso.xoso.presenter.activity;

import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.MainModel;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.entity.RESP_LuckyEntity;
import com.xproject.xoso.xoso.model.respond.RESP_Basic;
import com.xproject.xoso.xoso.view.activity.inf.ILuckyActivityView;

/**
 * Created by xtel on 10/25/17.
 */

public class LuckyActivityPresenter {
    ILuckyActivityView view;

    public LuckyActivityPresenter(ILuckyActivityView view) {
        this.view = view;
    }

    public void getLucky(String birth_day){
        view.showProgressBar(false, false, null, "Đang tải...");
        MainModel.getInstance().getLucky(birth_day, new ResponseHandle<RESP_LuckyEntity>(RESP_LuckyEntity.class) {
            @Override
            public void onSuccess(RESP_LuckyEntity obj) {
                view.closeProgressBar();
                view.getLuckySuccess(obj);
            }

            @Override
            public void onError(Error error) {
                view.closeProgressBar();
                if (error.getMessage()!=null)
                view.getLuckyError(error.getMessage());
            }
        });

    }
}
