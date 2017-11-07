package com.xProject.XosoVIP.sdk.utils;

import com.xProject.XosoVIP.sdk.callback.RealmListener;
import com.xProject.XosoVIP.xoso.ProjectApplication;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

public class DatabaseHelper {
    private static DatabaseHelper instance;
    private Realm realm;

    private DatabaseHelper() {
        Realm.init(ProjectApplication.context);
        realm = Realm.getDefaultInstance();
    }

    public static synchronized DatabaseHelper getInstance() {
        if (instance == null)
            instance = new DatabaseHelper();

        return instance;
    }

//    public static <T extends RealmObject> int getMaxIdOfTable(Class<T> tClass) {
//        Field[] fields = tClass.getDeclaredFields();
//        for (Field field : fields) {
//            for (Annotation annotation : field.getAnnotations()) {
//                Log.e("Annotation", annotation.toString());
//                if (annotation instanceof PrimaryKey) {
//                    Log.e("Annotation FIELDS ", field.getName());
//                }
//            }
//        }
//        return -1;
//    }
//
//    public static void main(String[] agrg) {
//        DatabaseHelper.getMaxIdOfTable(Catalog.class);
//    }

    /**
     * Lấy ra 1 object
     */
    public <T extends RealmObject> T getObject(Class<T> tClass) {
        return realm.where(tClass).findFirst();
    }

    public <T extends RealmObject> T getObject(Class<T> tClass, String key, int value) {
        return realm.where(tClass).equalTo(key, value).findFirst();
    }

    public <T extends RealmObject> T getObject(Class<T> tClass, String key, String value) {
        return realm.where(tClass).equalTo(key, value).findFirst();
    }

    public <T extends RealmObject> T getObject(Class<T> tClass, String key, float value) {
        return realm.where(tClass).equalTo(key, value).findFirst();
    }

    public <T extends RealmObject> T getObject(Class<T> tClass, String key, double value) {
        return realm.where(tClass).equalTo(key, value).findFirst();
    }

    public <T extends RealmObject> T getObject(Class<T> tClass, String key, long value) {
        return realm.where(tClass).equalTo(key, value).findFirst();
    }

    public <T extends RealmObject> T getObjectWithTwoKey(Class<T> tClass, String key1, int value1, String key2, int value2) {
        return realm.where(tClass).equalTo(key1, value1).equalTo(key2, value2).findFirst();
    }

    /**
     * Lấy toàn bộ object trong 1 bảng
     */
    public <T extends RealmObject> RealmResults<T> getRealmResults(Class<T> tClass) {
        return realm.where(tClass).findAll();
    }

    public <T extends RealmObject> RealmResults<T> getRealmResults(Class<T> tClass, String key, int value) {
        return realm.where(tClass).equalTo(key, value).findAll();
    }


    public <T extends RealmObject> ArrayList<T> getListOfObjects(Class<T> tClass) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).findAll());
    }

    public <T extends RealmObject> List<T> findObjectByName(Class<T> tClass, String key, String value) {
        return realm.copyFromRealm(realm.where(tClass).like(key, "*" + value + "*").findAll());
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjects(Class<T> tClass, String sort_key) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).findAllSorted(sort_key, Sort.DESCENDING));
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjects(Class<T> tClass, String key, int value) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key, value).findAll());
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjects(Class<T> tClass, String key, int value, String sort_key) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key, value).findAllSorted(sort_key, Sort.DESCENDING));
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjects(Class<T> tClass, String key, String value) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key, value).findAll());
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjects(Class<T> tClass, String key, long value) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key, value).findAll());
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjects(Class<T> tClass, String key, boolean value) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key, value).findAll());
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjects(Class<T> tClass, String key, String value, String sort_key) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key, value).findAllSorted(sort_key, Sort.DESCENDING));
    }


    public <T extends RealmObject> ArrayList<T> getListOfObjects(Class<T> tClass, String key1, String value1, String key2, String value2, String sort_key) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key1, value1).equalTo(key2, value2).findAllSorted(sort_key, Sort.DESCENDING));
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjectsNotEqual(Class<T> tClass, String key1, String value1, String key2, String value2) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).notEqualTo(key1, value1).notEqualTo(key2, value2).findAll());
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjectsNotEqual(Class<T> tClass, String key1, String value1, String[] fieldName, Sort[] sortOrders) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).notEqualTo(key1, value1).findAllSorted(fieldName, sortOrders));
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjectsByTwoKey(Class<T> tClass, String key1, String value1, String key2, String value2) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key1, value1).or().equalTo(key2, value2).findAll());
    }


    public <T extends RealmObject> ArrayList<T> getListOfObjectsByTwoKey(Class<T> tClass, String key1, String value1, String key2, String value2, String sort) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key1, value1).or().equalTo(key2, value2).findAllSorted(sort));
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjectsByTwoKey(Class<T> tClass, String key1, int value1, String key2, boolean value2) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key1, value1).or().equalTo(key2, value2).findAll());
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjectsWithTwoKey(Class<T> tClass, String key1, int value1, String key2, boolean value2) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).equalTo(key1, value1).equalTo(key2, value2).findAll());
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjectsNotByTwoKey(Class<T> tClass, String key1, String value1, String key2, String value2) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).notEqualTo(key1, value1).notEqualTo(key2, value2).findAll());
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjectsNotByTwoKey(Class<T> tClass, String key1, String value1, String key2, String value2, String sort_key) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).notEqualTo(key1, value1).notEqualTo(key2, value2).findAllSorted(sort_key, Sort.DESCENDING));
    }

    public <T extends RealmObject> ArrayList<T> getListOfObjectsNotByTwoKey(Class<T> tClass, String key1, String value1, String key2, String value2, String[] fieldName, Sort[] sortOrders) {
        return (ArrayList<T>) realm.copyFromRealm(realm.where(tClass).notEqualTo(key1, value1).notEqualTo(key2, value2).findAllSorted(fieldName, sortOrders));
    }

//    public <T extends RealmObject> List<T> findObjectByKey(Class<T> tClass, String key1, String value1, String key2, String value2){
//
//        return (ArrayList<T>) realm.copyFromRealm(realm.where())
//    }

    /**
     * Lấy tổng object trong 1 bảng
     */
    public <T extends RealmObject> Long getListCount(Class<T> tClass) {
        return realm.where(tClass).count();
    }

    public <T extends RealmObject> Long getListCount(Class<T> tClass, String key, int value) {
        return realm.where(tClass).equalTo(key, value).count();
    }


    public <T extends RealmObject> Long getListCount(Class<T> tClass, String key, int value, String key1, boolean value1) {
        return realm.where(tClass).equalTo(key, value).equalTo(key1, value1).count();
    }

    public <T extends RealmObject> Long getListCount(Class<T> tClass, String key1, boolean value1) {
        return realm.where(tClass).equalTo(key1, value1).count();
    }

    /**
     * Thêm, sửa 1 object
     */
    public <T extends RealmObject> void saveOrUpdateObject(final T object, final RealmListener listener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(object);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                listener.onError();
            }
        });
    }

    /**
     * Thêm, sửa nhiều object
     */
    public <T extends RealmObject> void saveOrUpdateListOfObject(final List<T> realmList, final RealmListener listener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.copyToRealmOrUpdate(realmList);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                listener.onError();
                error.printStackTrace();
            }
        });
    }

    /**
     * Xóa 1 object
     */
    public <T extends RealmObject> void deleteObject(final Class<T> tClass, final String key, final String value, final RealmListener listener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.where(tClass).equalTo(key, value).findFirst().deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                listener.onError();
            }
        });
    }

    public <T extends RealmObject> void deleteObject(final T object, final RealmListener listener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                object.deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                listener.onError();
            }
        });
    }

    /**
     * Xóa 1 bảng
     */
    public <T extends RealmObject> void deleteObjectTable(final Class<T> tClass, final RealmListener listener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.delete(tClass);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                listener.onError();
            }
        });
    }

    /**
     * Xóa nhiều object
     */
    public <T extends RealmObject> void deleteObjectList(final RealmResults<T> realmResults, final RealmListener listener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                realmResults.deleteAllFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                listener.onError();
            }
        });
    }

//    public <T extends RealmObject> void deleteList(Class<T> tClass, String key, int value) {
//        final RealmResults<tClass> catalogs = realm.where(Catalog.class).equalTo(key, value).findAll();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                catalogs.deleteAllFromRealm();
//            }
//        });
//    }


    public <T extends RealmObject> int getMaxIdOfTable(Class<T> tClass, String IDFieldName) {
        Number max = realm.where(tClass).max(IDFieldName);
        if (max == null)
            return 0;

        int maxID = max.intValue();
        return maxID;
    }

    public void deleteAllTable(final RealmListener realmListener) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                bgRealm.deleteAll();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                realmListener.onSuccess();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                realmListener.onError();
            }
        });
    }
}