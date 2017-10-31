package com.xProject.XosoVIP.xoso.presenter.activity;

import com.xProject.XosoVIP.sdk.callback.Icmd;
import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.sdk.utils.ResponseHandle;
import com.xProject.XosoVIP.xoso.model.AnalyticsModel;
import com.xProject.XosoVIP.xoso.model.entity.Error;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.model.respond.RESP_Frequency;
import com.xProject.XosoVIP.xoso.view.activity.inf.IAnalyticsFrequencyACtivity;

import java.util.List;

/**
 * Created by vivhp on 10/6/2017
 */

public class AnalyticsFrequencyActivityPresenter {

    private IAnalyticsFrequencyACtivity view;

    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int) params[0]) {
                case 1:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    SpeedTemp temp = (SpeedTemp) params[1];
                    AnalyticsModel.getInstance().getFrequencyAnalytics(temp.getDate_count(), temp.getNumber(), temp.getId_cat(), temp.isOnly_special(), new ResponseHandle<RESP_Frequency>(RESP_Frequency.class) {
                        @Override
                        public void onSuccess(RESP_Frequency obj) {
                            view.closeProgressBar();
                            view.getFrequencysuccess(obj.getData());
                        }

                        @Override
                        public void onError(Error error) {
                            view.closeProgressBar();
                            view.getFrequencyError(error.getMessage());
                        }
                    });
                    break;
            }
        }
    };

    public AnalyticsFrequencyActivityPresenter(IAnalyticsFrequencyACtivity view) {
        this.view = view;
    }

    public void getFrequency(SpeedTemp speedTemp) {
        icmd.excute(1, speedTemp);
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

}
