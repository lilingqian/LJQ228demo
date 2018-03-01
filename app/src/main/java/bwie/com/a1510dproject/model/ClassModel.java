package bwie.com.a1510dproject.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;

import bwie.com.a1510dproject.bean.CatagoryBean;
import bwie.com.a1510dproject.bean.ProductCatagoryBean;
import bwie.com.a1510dproject.net.Api;
import bwie.com.a1510dproject.net.OkHttpUtils;
import bwie.com.a1510dproject.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by peng on 2017/12/1.
 */

public class ClassModel implements IClassModel {
    private Handler handler = new Handler(Looper.myLooper());

    /**
     * 获取左侧分类列表
     *
     * @param onNetListener
     */
    public void getCatagory(final OnNetListener<CatagoryBean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doGet(Api.CATAGORY, new Callback() {
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
                        CatagoryBean catagoryBean = new Gson().fromJson(string, CatagoryBean.class);
                        onNetListener.onSuccess(catagoryBean);
                    }
                });
            }
        });
    }

    /**
     * 获取右侧分类列表
     *
     * @param cid
     * @param onNetListener
     */
    public void getProductCatagory(String cid, final OnNetListener<ProductCatagoryBean> onNetListener) {
        String url = String.format(Api.PRODUCT_CATAGORY, cid);
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
                        ProductCatagoryBean productCatagoryBean = new Gson().fromJson(string, ProductCatagoryBean.class);
                        onNetListener.onSuccess(productCatagoryBean);
                    }
                });
            }
        });
    }
}
