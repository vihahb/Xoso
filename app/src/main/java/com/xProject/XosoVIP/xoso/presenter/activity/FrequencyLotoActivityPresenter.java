package com.xProject.XosoVIP.xoso.presenter.activity;

import com.xProject.XosoVIP.sdk.callback.Icmd;
import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.sdk.utils.ResponseHandle;
import com.xProject.XosoVIP.xoso.model.AnalyticsModel;
import com.xProject.XosoVIP.xoso.model.entity.Error;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.model.respond.RESP_FrequencyLoto;
import com.xProject.XosoVIP.xoso.view.activity.inf.IFrequencyLotoActivity;

import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class FrequencyLotoActivityPresenter {
    private IFrequencyLotoActivity view;
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            SpeedTemp temp = (SpeedTemp) params[1];
            switch ((int) params[0]) {
                case 1:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    AnalyticsModel.getInstance().getFrequencyLoto(temp.getDate_begin(), temp.getDate_end(), temp.getId_cat(), temp.getNumber(), temp.getDay_of_week(), new ResponseHandle<RESP_FrequencyLoto>(RESP_FrequencyLoto.class) {
                        @Override
                        public void onSuccess(RESP_FrequencyLoto obj) {
                            view.closeProgressBar();
                            view.getFrequencyLoto(obj);
                        }

                        @Override
                        public void onError(Error error) {
                            view.closeProgressBar();
                            view.getFrequencyLotoError(error.getMessage());
                        }
                    });
                    break;
                case 2:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    AnalyticsModel.getInstance().getFrequencyLotoAllDay(temp.getDate_begin(), temp.getDate_end(), temp.getId_cat(), temp.getNumber(), new ResponseHandle<RESP_FrequencyLoto>(RESP_FrequencyLoto.class) {
                        @Override
                        public void onSuccess(RESP_FrequencyLoto obj) {
                            view.closeProgressBar();
                            view.getFrequencyLoto(obj);
                        }

                        @Override
                        public void onError(Error error) {
                            view.closeProgressBar();
                            view.getFrequencyLotoError(error.getMessage());
                        }
                    });
                    break;
            }
        }
    };

    public FrequencyLotoActivityPresenter(IFrequencyLotoActivity view) {
        this.view = view;
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getFrequencyLotoAllDay(SpeedTemp temp) {
        icmd.excute(2, temp);
    }

    public void getFrequencyLotoOneDay(SpeedTemp temp) {
        icmd.excute(1, temp);
    }
}
