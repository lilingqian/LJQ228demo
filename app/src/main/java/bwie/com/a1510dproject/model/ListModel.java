package bwie.com.a1510dproject.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import bwie.com.a1510dproject.bean.ListBean;
import bwie.com.a1510dproject.net.Api;
import bwie.com.a1510dproject.net.OkHttpUtils;
import bwie.com.a1510dproject.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by peng on 2017/12/6.
 */

public class ListModel implements IListModel {
    Handler handler = new Handler(Looper.getMainLooper());

    public void getList(Map<String, String> params, final OnNetListener<ListBean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doPost(Api.PRODUCT_CATAGORY_LIST, params, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final ListBean listBean = new Gson().fromJson(string, ListBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(listBean);
                    }
                });

            }
        });
    }
}
