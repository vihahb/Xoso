package com.xProject.XosoVIP.xoso.presenter.activity;

import android.util.Log;

import com.xProject.XosoVIP.sdk.callback.Icmd;
import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.entity.SpeedTemp;
import com.xProject.XosoVIP.xoso.view.activity.inf.ActivityExploreBridgeLotoView;

import java.util.List;

/**
 * Created by vivhp on 10/18/2017.
 */

public class ActivityExploreBridgeLotoPresenter {

    private ActivityExploreBridgeLotoView view;
    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            SpeedTemp speedTemp = (SpeedTemp) params[1];
            String url = null;
            switch ((int) params[0]) {
                case 1:
                    url = "http://xoso.la/index.php?route=webview/loto&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_end() + "&day_count=" + speedTemp.getDate_count();
                    view.returnUrl(url);
                    break;
                case 2:
                    url = "http://xoso.la/index.php?route=webview/loailoto&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_end() + "&day_count=" + speedTemp.getDate_count();
                    view.returnUrl(url);
                    break;
                case 3:
                    url = "http://xoso.la/index.php?route=webview/bachthu&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_end() + "&day_count=" + speedTemp.getDate_count();
                    view.returnUrl(url);
                    break;
                case 4:
                    url = "http://xoso.la/index.php?route=webview/loaibachthu&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_end() + "&day_count=" + speedTemp.getDate_count();
                    view.returnUrl(url);
                    break;
                case 5:
                    url = "http://xoso.la/index.php?route=webview/dacbiet&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_end() + "&day_count=" + speedTemp.getDate_count() + "&special=" + speedTemp.isOnly_special();
                    view.returnUrl(url);
                    break;
                case 6:
                    url = "http://xoso.la/index.php?route=webview/lotohainhay&proviceCode=" + speedTemp.getId_cat() + "&endDate=" + speedTemp.getDate_end() + "&day_count=" + speedTemp.getDate_count();
                    view.returnUrl(url);
                    break;
                case 9:
                    if (speedTemp.isOnly_special()){
                        url = "http://xoso.la/index.php?route=webview/kiemtralsc&proviceCode=" + speedTemp.getId_cat() + "&start_date="+ speedTemp.getDate_begin() + "&end_date=" + speedTemp.getDate_end() + "&start=" + speedTemp.getPosition_1() + "&end=" + speedTemp.getPosition_2() + "&type=bach_thu";
                    } else {
                        url = "http://xoso.la/index.php?route=webview/kiemtralsc&proviceCode=" + speedTemp.getId_cat() + "&start_date="+ speedTemp.getDate_begin() + "&end_date=" + speedTemp.getDate_end() + "&start=" + speedTemp.getPosition_1() + "&end=" + speedTemp.getPosition_2() + "&type=loto";
                    }
                    Log.e("TAG", "excute: LSC " + url);
                    view.returnUrl(url);
                    break;
            }
        }
    };

    public ActivityExploreBridgeLotoPresenter(ActivityExploreBridgeLotoView view) {
        this.view = view;
    }

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

    public void getHistory(SpeedTemp temp){
        icmd.excute(9, temp);
    }
}
