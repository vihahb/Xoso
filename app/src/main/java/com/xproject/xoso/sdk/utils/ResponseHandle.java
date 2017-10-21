package com.xproject.xoso.sdk.utils;

import android.text.TextUtils;
import android.util.Log;

import com.xproject.xoso.xoso.ProjectApplication;
import com.xproject.xoso.xoso.model.entity.Error;
import com.xproject.xoso.xoso.model.respond.RESP_Basic;
import com.xtelsolution.xoso.R;

public abstract class ResponseHandle<T extends RESP_Basic> {
    private Class<T> clazz;

    protected ResponseHandle(Class<T> clazz) {
        this.clazz = clazz;
    }

    void onSuccess(String result) {
//        Log.e("ResponseHandle", "null: " + result);

        if (TextUtils.isEmpty(result)) {
            onSuccess((T) null);
        } else if (result.equals("\"\"")) {
            onError(new Error(ProjectApplication.context.getString(R.string.error_try_again)));
        } else {
            Log.e("ResponseHandle", "before result " + result);
            result = getNewJson(result);
            Log.e("ResponseHandle", "new result " + result);

            T t = JsonHelper.getObjectNoException(result, clazz);
            Log.e("ResponseHandle", "object " + JsonHelper.toJson(t));

            if (t == null)
                onError(new Error(ProjectApplication.context.getString(R.string.error_try_again)));
            else if (t.isSuccess() || t.getMessage() != null) {
                if (t.isSuccess())
                    onSuccess(t);
                else {
                    if (t.getMessage() == null) {
                        onError(new Error(ProjectApplication.context.getString(R.string.error_try_again)));
                    } else
                        onError(new Error(t.getMessage()));
                }
            } else {
                onSuccess(t);
            }
        }
    }

    private String getNewJson(String result) {
        if (result.charAt(0) == '[' && result.charAt((result.length() - 1)) == ']') {
            return "{ \"data\": " + result + "}";
        }
        return result;
    }

    public abstract void onSuccess(T obj);

    public abstract void onError(Error error);
}
