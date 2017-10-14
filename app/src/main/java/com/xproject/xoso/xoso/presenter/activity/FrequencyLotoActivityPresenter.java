package com.xproject.xoso.xoso.presenter.activity;

import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.AnalyticsModel;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.model.respond.RESP_Basic;
import com.xproject.xoso.xoso.model.respond.RESP_FrequencyLoto;
import com.xproject.xoso.xoso.view.activity.inf.IFrequencyLotoActivity;

import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class FrequencyLotoActivityPresenter {
    private IFrequencyLotoActivity view;

    public FrequencyLotoActivityPresenter(IFrequencyLotoActivity view) {
        this.view = view;
    }

    private Icmd icmd= new Icmd() {
        @Override
        public void excute(Object... params) {
            SpeedTemp temp = (SpeedTemp) params[1];
            switch ((int)params[0]){
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

    public List<ProvinceEntity> getCategory(){
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getFrequencyLotoAllDay(SpeedTemp temp){
        icmd.excute(2, temp);
    }
    public void getFrequencyLotoOneDay(SpeedTemp temp){
        icmd.excute(1, temp);
    }
}
