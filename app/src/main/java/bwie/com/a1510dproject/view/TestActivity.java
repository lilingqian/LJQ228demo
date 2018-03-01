package bwie.com.a1510dproject.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import bwie.com.a1510dproject.R;
import bwie.com.a1510dproject.net.LoggingInterceptor;
import bwie.com.a1510dproject.presenter.MyInterceptor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
//                NetworkIntercepter();
                addParamsIntoIntercepter();
                break;
        }

    }

    private void addParamsIntoIntercepter() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .build();

        FormBody formBody = new FormBody.Builder().add("pid", "7").build();
        final Request request = new Request.Builder()
                .url("https://www.zhaoapi.cn/product/getProductDetail")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("aaaa","onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.e("aaaa",string);
            }
        });
    }

    /**
     * 网络拦截器
     */
    private void NetworkIntercepter() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();

        final Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 应用拦截器
     */
    private void ApplicationIntercepter() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        final Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
