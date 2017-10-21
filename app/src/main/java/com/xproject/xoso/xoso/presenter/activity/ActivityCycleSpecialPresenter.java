package com.xproject.xoso.xoso.presenter.activity;

import android.util.Log;

import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.sdk.utils.JsonHelper;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.AnalyticsModel;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.model.respond.RESP_CycleSpecial;
import com.xproject.xoso.xoso.view.activity.inf.IActivityCycleSpecial;

import java.util.List;

/**
 * Created by vivhp on 10/10/2017.
 */

public class ActivityCycleSpecialPresenter {

    private IActivityCycleSpecial view;
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int) params[0]) {
                case 1:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    SpeedTemp temp = (SpeedTemp) params[1];
                    AnalyticsModel.getInstance().getCycleSpecial(temp.getDate_begin(), temp.getId_cat(), new ResponseHandle<RESP_CycleSpecial>(RESP_CycleSpecial.class) {
                        @Override
                        public void onSuccess(RESP_CycleSpecial obj) {
                            view.closeProgressBar();
                            view.getCycleSpecial(obj);
                            String result = JsonHelper.toJson(obj);
                            Log.e("result", "onSuccess: " + result);
                        }

                        @Override
                        public void onError(Error error) {
                            view.closeProgressBar();
                            view.getCycleSpecialError(error.getMessage());
                        }
                    });
                    break;
            }
        }
    };

    public ActivityCycleSpecialPresenter(IActivityCycleSpecial view) {
        this.view = view;
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getCycleSpecial(SpeedTemp temp) {
        icmd.excute(1, temp);
    }
}
