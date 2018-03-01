package bwie.com.a1510dproject.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import bwie.com.a1510dproject.bean.GoodsDetailsBean;
import bwie.com.a1510dproject.net.Api;
import bwie.com.a1510dproject.net.OkHttpUtils;
import bwie.com.a1510dproject.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by peng on 2017/12/8.
 */

public class GoodsDetailsModel implements IGoodsDetailsModel {
    private Handler handler = new Handler(Looper.getMainLooper());

    public void getProductDetail(String pid, final OnNetListener<GoodsDetailsBean> onNetListener) {
        String url = String.format(Api.PRODUCT_DETAIL, pid);
        OkHttpUtils.getOkHttpUtils().doGet(url, new Callback() {
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
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        GoodsDetailsBean goodsDetailsBean = new Gson().fromJson(string, GoodsDetailsBean.class);
                        onNetListener.onSuccess(goodsDetailsBean);
                    }
                });
            }
        });
    }
}
