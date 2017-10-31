package com.xProject.XosoVIP.xoso.presenter.activity;

import com.xProject.XosoVIP.sdk.callback.Icmd;
import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.sdk.utils.ResponseHandle;
import com.xProject.XosoVIP.xoso.model.AnalyticsModel;
import com.xProject.XosoVIP.xoso.model.entity.Error;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.model.respond.RESP_SpecialTomorow;
import com.xProject.XosoVIP.xoso.view.activity.inf.ISpecialTomorowActivity;

import java.util.List;

/**
 * Created by vivhp on 10/12/2017.
 */

public class SpecialTomorowActivityPresenter {

    private ISpecialTomorowActivity view;
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int) params[0]) {
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

    public SpecialTomorowActivityPresenter(ISpecialTomorowActivity view) {
        this.view = view;
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getSpecialTomorow(SpeedTemp temp) {
        icmd.excute(1, temp);
    }
}
