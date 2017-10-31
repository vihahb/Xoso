package com.xProject.XosoVIP.xoso.presenter.activity;

import com.xProject.XosoVIP.sdk.callback.Icmd;
import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.sdk.utils.ResponseHandle;
import com.xProject.XosoVIP.xoso.model.AnalyticsModel;
import com.xProject.XosoVIP.xoso.model.entity.Error;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.model.respond.RESP_NumberSet;
import com.xProject.XosoVIP.xoso.view.activity.inf.IAnalyticsDayActivity;

import java.util.List;

/**
 * Created by vivhp on 10/9/2017.
 */

public class AnalyticsDayPresenter {

    private IAnalyticsDayActivity view;
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int) params[0]) {
                case 1:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    SpeedTemp temp = (SpeedTemp) params[1];
                    AnalyticsModel.getInstance().getDay(temp.getWeek(), temp.getDate_end(), temp.getDay_of_week(), temp.getId_cat(), new ResponseHandle<RESP_NumberSet>(RESP_NumberSet.class) {
                        @Override
                        public void onSuccess(RESP_NumberSet obj) {
                            view.closeProgressBar();
                            view.getAnalyticsDay(obj.getData());
                        }

                        @Override
                        public void onError(Error error) {
                            view.closeProgressBar();
                            view.getDayError(error.getMessage());
                        }
                    });
                    break;
            }
        }
    };

    public AnalyticsDayPresenter(IAnalyticsDayActivity view) {
        this.view = view;
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getDay(SpeedTemp temp) {
        icmd.excute(1, temp);
    }
}
