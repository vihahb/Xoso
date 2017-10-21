package com.xproject.xoso.xoso.presenter.activity;

import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.xoso.model.AnalyticsModel;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.model.respond.RESP_NumberSet;
import com.xproject.xoso.xoso.view.activity.inf.IAnalyticsSynthetic;

import java.util.List;

/**
 * Created by vivhp on 10/9/2017.
 */

public class AnalyticsSyntheticPresenter {

    private IAnalyticsSynthetic view;
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int) params[0]) {
                case 1:
                    view.showProgressBar(false, false, null, "Đang tải...");
                    SpeedTemp temp = (SpeedTemp) params[1];
                    AnalyticsModel.getInstance().getSynthetic(temp.getType(),
                            temp.getDate_begin(),
                            temp.getDate_end(),
                            temp.getId_cat(),
                            temp.isOnly_special(),
                            new ResponseHandle<RESP_NumberSet>(RESP_NumberSet.class) {
                                @Override
                                public void onSuccess(RESP_NumberSet obj) {
                                    view.closeProgressBar();
                                    view.getSynthetics(obj.getData());
                                }

                                @Override
                                public void onError(Error error) {
                                    view.closeProgressBar();
                                    view.getSyntheticsError(error.getMessage());
                                }
                            }
                    );
                    break;

            }
        }
    };

    public AnalyticsSyntheticPresenter(IAnalyticsSynthetic view) {
        this.view = view;
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getSynthetic(SpeedTemp temp) {
        icmd.excute(1, temp);
    }
}
