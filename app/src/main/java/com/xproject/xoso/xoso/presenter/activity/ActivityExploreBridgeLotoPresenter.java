package com.xproject.xoso.xoso.presenter.activity;

import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.entity.SpeedTemp;
import com.xproject.xoso.xoso.view.activity.WebViewActivity;
import com.xproject.xoso.xoso.view.activity.inf.ActivityExploreBridgeLotoView;

import java.util.List;

/**
 * Created by vivhp on 10/18/2017.
 */

public class ActivityExploreBridgeLotoPresenter {

    private ActivityExploreBridgeLotoView view;

    public ActivityExploreBridgeLotoPresenter(ActivityExploreBridgeLotoView view) {
        this.view = view;
    }

    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            SpeedTemp speedTemp = (SpeedTemp) params[1];
            String url = null;
            switch ((int) params[0]) {
                case 1:
                    url = "http://xoso.la/index.php?route=webview/loto&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_format_end() + "&day_count=" + speedTemp.getDate_count();
                    view.returnUrl(url);
                    break;

                case 2:
                    url = "http://xoso.la/index.php?route=webview/loailoto&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_format_end() + "&day_count=" + speedTemp.getDate_count();
                    view.returnUrl(url);
                    break;

                case 3:
                    url = "http://xoso.la/index.php?route=webview/bachthu&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_format_end() + "&day_count=" + speedTemp.getDate_count();
                    view.returnUrl(url);
                    break;

                case 4:
                    url = "http://xoso.la/index.php?route=webview/loaibachthu&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_format_end() + "&day_count=" + speedTemp.getDate_count();
                    view.returnUrl(url);
                    break;

                case 5:
                    url = "http://xoso.la/index.php?route=webview/dacbiet&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_format_end() + "&day_count=" + speedTemp.getDate_count() + "&special=" + speedTemp.isOnly_special();
                    view.returnUrl(url);
                    break;

                case 6:
                    url = "http://xoso.la/index.php?route=webview/lotohainhay&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_format_end() + "&day_count=" + speedTemp.getDate_count();
                    view.returnUrl(url);
                    break;
            }
        }
    };

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getBridgeLoto(SpeedTemp temp) {
        icmd.excute(1, temp);
    }

    public void getTypeLoto(SpeedTemp temp) {
        icmd.excute(2, temp);
    }

    public void getBachthu(SpeedTemp temp) {
        icmd.excute(3, temp);
    }

    public void getSpecialBridge(SpeedTemp temp) {
        icmd.excute(5, temp);
    }

    public void getLoto2Nhay(SpeedTemp temp) {
        icmd.excute(6, temp);
    }

    public void getTypeBachthu(SpeedTemp temp) {
        icmd.excute(4, temp);
    }
}
