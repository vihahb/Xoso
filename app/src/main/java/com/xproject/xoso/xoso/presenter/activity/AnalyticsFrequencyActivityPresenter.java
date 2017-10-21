package com.xproject.xoso.xoso.presenter.activity;

import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.AnalyticsModel;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.model.respond.RESP_Frequency;
import com.xproject.xoso.xoso.view.activity.inf.IAnalyticsFrequencyACtivity;

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
