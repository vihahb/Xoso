package com.xproject.xoso.xoso.presenter.activity;

import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.AnalyticsModel;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.model.respond.RESP_Important;
import com.xproject.xoso.xoso.view.activity.inf.IAnalyticsImportantActivity;

import java.util.List;

/**
 * Created by vivhp on 10/6/2017.
 */

public class AnalyticsImportantActivityPresenter {

    private IAnalyticsImportantActivity view;
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int) params[0]) {
                case 1:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    SpeedTemp temp = (SpeedTemp) params[1];
                    AnalyticsModel.getInstance().getImportantAnalytics(temp.getType(), temp.getId_cat(), new ResponseHandle<RESP_Important>(RESP_Important.class) {
                        @Override
                        public void onSuccess(RESP_Important obj) {
                            view.closeProgressBar();
                            view.getImportantSuccess(obj.getData());
                        }

                        @Override
                        public void onError(Error error) {
                            view.closeProgressBar();
                            view.getImportantError(error.getMessage());
                        }
                    });
                    break;
            }
        }
    };

    public AnalyticsImportantActivityPresenter(IAnalyticsImportantActivity view) {
        this.view = view;
    }

    public void getImportant(SpeedTemp temp) {
        icmd.excute(1, temp);
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

}
