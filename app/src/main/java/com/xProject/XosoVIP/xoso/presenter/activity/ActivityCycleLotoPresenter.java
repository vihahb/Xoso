package com.xProject.XosoVIP.xoso.presenter.activity;

import com.xProject.XosoVIP.sdk.callback.Icmd;
import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.sdk.utils.ResponseHandle;
import com.xProject.XosoVIP.xoso.model.AnalyticsModel;
import com.xProject.XosoVIP.xoso.model.entity.Error;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.model.respond.RESP_CycleLoto;
import com.xProject.XosoVIP.xoso.view.activity.inf.IActivityCycleLotoView;

import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class ActivityCycleLotoPresenter {
    private IActivityCycleLotoView view;
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int) params[0]) {
                case 1:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    SpeedTemp temp = (SpeedTemp) params[1];
                    AnalyticsModel.getInstance().getCycleLoto(temp.getNumber(), temp.getId_cat(), new ResponseHandle<RESP_CycleLoto>(RESP_CycleLoto.class) {
                        @Override
                        public void onSuccess(RESP_CycleLoto obj) {
                            view.closeProgressBar();
                            view.getCycleLotoSuccess(obj.getData());
                        }

                        @Override
                        public void onError(Error error) {
                            view.closeProgressBar();
                            view.getCycleLotoError(error.getMessage());
                        }
                    });
                    break;
            }
        }
    };

    public ActivityCycleLotoPresenter(IActivityCycleLotoView view) {
        this.view = view;
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getCycleLoto(SpeedTemp temp) {
        icmd.excute(1, temp);
    }
}
