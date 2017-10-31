package com.xProject.XosoVIP.xoso.presenter.activity;

import android.util.Log;

import com.xProject.XosoVIP.sdk.callback.Icmd;
import com.xProject.XosoVIP.sdk.callback.RealmListener;
import com.xProject.XosoVIP.sdk.common.Constants;
import com.xProject.XosoVIP.sdk.utils.DatabaseHelper;
import com.xProject.XosoVIP.sdk.utils.JsonHelper;
import com.xProject.XosoVIP.sdk.utils.NetworkUtils;
import com.xProject.XosoVIP.sdk.utils.ResponseHandle;
import com.xProject.XosoVIP.sdk.utils.SharedUtils;
import com.xProject.XosoVIP.sdk.utils.TextUtils;
import com.xProject.XosoVIP.xoso.model.MainModel;
import com.xProject.XosoVIP.xoso.model.entity.Error;
import com.xProject.XosoVIP.xoso.model.entity.ProvinceEntity;
import com.xProject.XosoVIP.xoso.model.respond.RESP_Category;
import com.xProject.XosoVIP.xoso.view.activity.inf.AppIntroView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xtel on 10/31/17.
 */

public class AppIntroPresenter {
    AppIntroView view;
    private static final String TAG = "AppIntroPresenter";
    public AppIntroPresenter(AppIntroView view) {
        this.view = view;
    }

    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int)params[0]){
                case 1:
                    MainModel.getInstance().getCategory(new ResponseHandle<RESP_Category>(RESP_Category.class) {
                        @Override
                        public void onSuccess(RESP_Category obj) {
                            final List<ProvinceEntity> provinceList = new ArrayList<>();
                            Log.e(TAG, "onSuccess: province list FIRST" + obj.getData().toString());
                            ProvinceEntity truyen_thong = new ProvinceEntity();

                            List<ProvinceEntity> central_list = new ArrayList<>();
                            List<ProvinceEntity> south_list = new ArrayList<>();
                            for (int i = 0; i < obj.getData().size(); i++) {
                                if (obj.getData().get(i).getMavung() == 9) {
                                    truyen_thong = obj.getData().get(i);
                                    truyen_thong.setTenvungUnicode(TextUtils.getInstance().unicodeToKoDauLowerCase(obj.getData().get(i).getTenvung()));
                                }
                                if (obj.getData().get(i).getMamien().equals("24")) {
                                    central_list.add(new ProvinceEntity(
                                            obj.getData().get(i).getMavung(),
                                            obj.getData().get(i).getTenvung(),
                                            obj.getData().get(i).getMamien(),
                                            TextUtils.getInstance().unicodeToKoDauLowerCase(obj.getData().get(i).getTenvung())));
                                }
                                if (obj.getData().get(i).getMamien().equals("10")) {
                                    south_list.add(new ProvinceEntity(
                                            obj.getData().get(i).getMavung(),
                                            obj.getData().get(i).getTenvung(),
                                            obj.getData().get(i).getMamien(),
                                            TextUtils.getInstance().unicodeToKoDauLowerCase(obj.getData().get(i).getTenvung())));
                                }
                            }
                            central_list = sorthList(central_list);
                            provinceList.addAll(central_list);
                            south_list = sorthList(south_list);
                            provinceList.addAll(south_list);
                            provinceList.add(0, truyen_thong);

                            Log.e(TAG, "onSuccess: province list" + JsonHelper.toJson(Arrays.toString(provinceList.toArray())));
                            if (provinceList.size() > 0) {
                                DatabaseHelper.getInstance().saveOrUpdateListOfObject(provinceList, new RealmListener() {
                                    @Override
                                    public void onSuccess() {
                                        Log.e(TAG, "onSuccess: Save List category object success");
                                        SharedUtils.getInstance().putBooleanValue(Constants.CATEGORY_FLAG, true);
                                        view.getProvinceListSuccess(provinceList);
                                    }

                                    @Override
                                    public void onError() {
                                        Log.e(TAG, "onError: Save List category object error");
                                        view.getProvinceListError();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(Error error) {

                        }
                    });
                    break;
            }
        }
    };

    private List<ProvinceEntity> sorthList(List<ProvinceEntity> list) {
        if (list.size() > 0) {
            Collections.sort(list, new Comparator<ProvinceEntity>() {
                @Override
                public int compare(ProvinceEntity s1, ProvinceEntity s2) {
                    return s1.getTenvungUnicode().compareToIgnoreCase(s2.getTenvungUnicode());
                }
            });
            return list;
        } else {
            return null;
        }
    }

    public List<ProvinceEntity> getCategory() {
        return DatabaseHelper.getInstance().getListOfObjects(ProvinceEntity.class);
    }

    public void getProvinceAPI() {
        if (NetworkUtils.getInstance().isConnected(view.getActivity())) {
            if (!SharedUtils.getInstance().getBooleanValue(Constants.CATEGORY_FLAG)) {
                icmd.excute(1);
            }
        } else{
            view.getProvinceListError();
        }
    }
}
