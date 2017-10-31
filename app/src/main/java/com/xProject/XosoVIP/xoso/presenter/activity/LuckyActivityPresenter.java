package com.xProject.XosoVIP.xoso.presenter.activity;

import com.xProject.XosoVIP.sdk.utils.ResponseHandle;
import com.xProject.XosoVIP.xoso.model.MainModel;
import com.xProject.XosoVIP.xoso.model.entity.Error;
import com.xProject.XosoVIP.xoso.model.entity.RESP_LuckyEntity;
import com.xProject.XosoVIP.xoso.view.activity.inf.ILuckyActivityView;

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
