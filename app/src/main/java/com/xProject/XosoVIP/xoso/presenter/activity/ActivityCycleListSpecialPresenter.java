package com.xProject.XosoVIP.xoso.presenter.activity;

import com.xProject.XosoVIP.sdk.callback.Icmd;
import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.sdk.utils.ResponseHandle;
import com.xProject.XosoVIP.xoso.model.AnalyticsModel;
import com.xProject.XosoVIP.xoso.model.entity.Error;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.model.respond.RESP_CycleLotoVip;
import com.xProject.XosoVIP.xoso.view.activity.inf.IActivityCycleListSpecial;

import java.util.List;

/**
 * Created by vivhp on 10/11/2017.
 */

public class ActivityCycleListSpecialPresenter {

    private IActivityCycleListSpecial view;
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            SpeedTemp temp = (SpeedTemp) params[1];
            switch ((int) params[0]) {
                case 1:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    AnalyticsModel.getInstance().getCycleListLoto(temp.getNumber(), temp.getDate_begin(), temp.getDate_end(), temp.getId_cat(), temp.getAllOrOne(), new ResponseHandle<RESP_CycleLotoVip>(RESP_CycleLotoVip.class) {
                        @Override
                        public void onSuccess(RESP_CycleLotoVip obj) {
                            view.closeProgressBar();
                            view.getListCycleSpecial(obj);
                        }

                        @Override
                        public void onError(Error error) {
                            view.closeProgressBar();
                            view.getListCycleSpecialError(error.getMessage());
                        }
                    });
                    break;

                case 2:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    AnalyticsModel.getInstance().getCycleListSpecial(temp.getNumber(), temp.getDate_begin(), temp.getDate_end(), temp.getId_cat(), temp.getAllOrOne(), new ResponseHandle<RESP_CycleLotoVip>(RESP_CycleLotoVip.class) {
                        @Override
                        public void onSuccess(RESP_CycleLotoVip obj) {
                            view.closeProgressBar();
                            view.getListCycleSpecial(obj);
                        }

                        @Override
                        public void onError(Error error) {
                            view.closeProgressBar();
                            view.getListCycleSpecialError(error.getMessage());
                        }
                    });
                    break;
            }
        }
    };

    public ActivityCycleListSpecialPresenter(IActivityCycleListSpecial view) {
        this.view = view;
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getCycleListSpecial(SpeedTemp temp) {
        icmd.excute(2, temp);
    }

    public void getCycleListLoto(SpeedTemp temp) {
        icmd.excute(1, temp);
    }
}
