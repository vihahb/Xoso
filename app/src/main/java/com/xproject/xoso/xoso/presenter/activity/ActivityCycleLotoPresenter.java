package com.xproject.xoso.xoso.presenter.activity;

import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.AnalyticsModel;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.model.respond.RESP_CycleLoto;
import com.xproject.xoso.xoso.view.activity.inf.IActivityCycleLotoView;

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
