package com.xproject.xoso.xoso.presenter.activity;

import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.AnalyticsModel;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.model.respond.RESP_SpecialTomorow;
import com.xproject.xoso.xoso.view.activity.inf.ISpecialTomorowActivity;

import java.util.List;

/**
 * Created by vivhp on 10/12/2017.
 */

public class SpecialTomorowActivityPresenter {

    private ISpecialTomorowActivity view;

    public SpecialTomorowActivityPresenter(ISpecialTomorowActivity view) {
        this.view = view;
    }

    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int)params[0]){
                case 1:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    SpeedTemp temp = (SpeedTemp) params[1];
                    AnalyticsModel.getInstance().getSpecialTomorow(temp.getDate_begin(), temp.getId_cat(), new ResponseHandle<RESP_SpecialTomorow>(RESP_SpecialTomorow.class) {
                        @Override
                        public void onSuccess(RESP_SpecialTomorow obj) {
                            view.closeProgressBar();
                            view.getSpecialTomorow(obj.getData());
                        }

                        @Override
                        public void onError(Error error) {
                            view.closeProgressBar();
                            view.getSpecialTomorowError(error.getMessage());
                        }
                    });
                    break;
            }
        }
    };

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getSpecialTomorow(SpeedTemp temp){
        icmd.excute(1, temp);
    }
}
