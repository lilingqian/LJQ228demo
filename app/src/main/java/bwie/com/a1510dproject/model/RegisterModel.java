package bwie.com.a1510dproject.model;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import bwie.com.a1510dproject.RegisterBean;
import bwie.com.a1510dproject.net.Api;
import bwie.com.a1510dproject.net.OkHttpUtils;
import bwie.com.a1510dproject.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by peng on 2017/12/1.
 */

public class RegisterModel implements IRegisterModel {
    private Handler handler = new Handler(Looper.myLooper());

    /**
     * 真正的网络请求，去注册
     */
    @Override
    public void register(Map<String, String> params, final OnNetListener<RegisterBean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doPost(Api.REGISTER, params, new Callback() {
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
                Log.e("RegisterModel", string);
                //把数据返回到主线程进行处理
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        RegisterBean registerBean = new Gson().fromJson(string, RegisterBean.class);
                        onNetListener.onSuccess(registerBean);
                    }
                });

            }
        });
    }


}
