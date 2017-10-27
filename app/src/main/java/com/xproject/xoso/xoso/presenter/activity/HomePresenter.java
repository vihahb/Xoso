package com.xproject.xoso.xoso.presenter.activity;

import android.util.Log;

import com.xproject.xoso.sdk.callback.Icmd;
import com.xproject.xoso.sdk.callback.RealmListener;
import com.xproject.xoso.sdk.common.Constants;
import com.xproject.xoso.sdk.utils.DatabaseHelper;
import com.xproject.xoso.sdk.utils.JsonHelper;
import com.xproject.xoso.sdk.utils.NetworkUtils;
import com.xproject.xoso.sdk.utils.ResponseHandle;
import com.xproject.xoso.sdk.utils.SharedUtils;
import com.xproject.xoso.sdk.utils.TextUtils;
import com.xproject.xoso.xoso.model.MainModel;
import com.xproject.xoso.xoso.model.entity.DrawerMenu;
import com.xproject.xoso.xoso.model.entity.Dream;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.entity.ProvinceEntity;
import com.xproject.xoso.xoso.model.respond.RESP_Category;
import com.xproject.xoso.xoso.model.respond.RESP_Dream;
import com.xproject.xoso.xoso.view.activity.inf.IHomeView;
import com.xtelsolution.xoso.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vivhp on 9/5/2017.
 */

public class HomePresenter {

    private static final String TAG = "HomePresenter";
    private IHomeView view;

    private Icmd icmd = new Icmd() {
        @Override
        public void excute(Object... params) {
            switch ((int) params[0]) {
                case 1:
                    MainModel.getInstance().getListDream(new ResponseHandle<RESP_Dream>(RESP_Dream.class) {
                        @Override
                        public void onSuccess(RESP_Dream obj) {
                            Log.e(TAG, "Dream size: " + obj.getData().size());
                            List<Dream> dreamList = new ArrayList<>();
                            for (int i = 0; i < obj.getData().size(); i++) {
                                dreamList.add(i, new Dream(obj.getData().get(i).getDream_id(),
                                        obj.getData().get(i).getDreamed(),
                                        obj.getData().get(i).getNumber(),
                                        TextUtils.getInstance().unicodeToKoDauLowerCase(obj.getData().get(i).getDreamed().trim().replaceAll(" ", ""))));
                            }
                            if (dreamList.size() > 0) {
                                DatabaseHelper.getInstance().saveOrUpdateListOfObject(dreamList, new RealmListener() {
                                    @Override
                                    public void onSuccess() {
                                        Log.e(TAG, "onSuccess: Save List dream object success");
                                        SharedUtils.getInstance().putBooleanValue(Constants.DREAM_FLAG, true);
                                    }

                                    @Override
                                    public void onError() {
                                        Log.e(TAG, "onError: Save List dream object error");
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(Error error) {
                            Log.e(TAG, "onError: " + error.toString());
                        }
                    });
                    break;
                case 2:
                    MainModel.getInstance().getCategory(new ResponseHandle<RESP_Category>(RESP_Category.class) {
                        @Override
                        public void onSuccess(RESP_Category obj) {
                            List<ProvinceEntity> provinceList = new ArrayList<>();
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
                                    }

                                    @Override
                                    public void onError() {
                                        Log.e(TAG, "onError: Save List category object error");
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

    public HomePresenter(IHomeView view) {
        this.view = view;
    }

    public void initDrawerMenu() {
        List<DrawerMenu> menus = new ArrayList<>();
        menus.add(0, new DrawerMenu(0, view.getActivity().getString(R.string.app_name), R.mipmap.ic_launcher, null));
        menus.add(1, new DrawerMenu(1, "Kết quả", -1, null));
        menus.add(2, new DrawerMenu(2, "Miền Bắc", R.mipmap.ic_nav_north, "18:15"));
        menus.add(3, new DrawerMenu(2, "Miền Trung", R.mipmap.ic_nav_central, "17:15"));
        menus.add(4, new DrawerMenu(2, "Miền Nam", R.mipmap.ic_nav_south, "16:15"));
        menus.add(5, new DrawerMenu(1, "Thống kê", -1, null));
        menus.add(6, new DrawerMenu(2, "Chu kỳ lô tô", R.mipmap.ic_nav_loto, null));
        menus.add(7, new DrawerMenu(2, "Chu kỳ đặc biệt", R.mipmap.ic_nav_special, null));
        menus.add(8, new DrawerMenu(2, "Mở rộng", R.mipmap.ic_more_nav, null));
        menus.add(9, new DrawerMenu(1, "Soi cầu", -1, null));
        menus.add(10, new DrawerMenu(2, "Cầu lô tô", R.mipmap.ic_nav_explore, null));
        menus.add(11, new DrawerMenu(2, "Giải đặc biệt", R.mipmap.ic_nav_special_prize, null));
        menus.add(12, new DrawerMenu(2, "Mở rộng", R.mipmap.ic_more_nav, null));
        menus.add(13, new DrawerMenu(1, "Thêm", -1, null));
        menus.add(14, new DrawerMenu(2, "Sổ mơ", R.mipmap.ic_nav_dream, null));
        menus.add(15, new DrawerMenu(2, "Quay thử", R.mipmap.ic_nav_random, null));
        menus.add(16, new DrawerMenu(2, "Cài đặt", R.mipmap.ic_setting, null));
        menus.add(17, new DrawerMenu(2, "Mở rộng", R.mipmap.ic_more_nav, null));
        view.initMenuDrawer(menus);
    }

    public void getDream() {
        if (NetworkUtils.getInstance().isOnline(view.getActivity())) {
            if (!SharedUtils.getInstance().getBooleanValue(Constants.DREAM_FLAG)) {
                icmd.excute(1);
            }
        }
    }

    public void saveProvince() {
        if (NetworkUtils.getInstance().isConnected(view.getActivity())) {
            if (!SharedUtils.getInstance().getBooleanValue(Constants.CATEGORY_FLAG)) {
                icmd.excute(2);
            }
        }
    }
}
