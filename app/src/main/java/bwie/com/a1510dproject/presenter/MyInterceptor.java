package bwie.com.a1510dproject.presenter;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by peng on 2017/12/7.
 */

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        FormBody formBody = (FormBody) request.body();
        FormBody.Builder builder = new FormBody.Builder();
        for (int i = 0; i < formBody.size(); i++) {
            String name = formBody.name(i);
            String value = formBody.value(i);
            builder.add(name, value);
        }
        builder.add("source", "android");
        FormBody newBody = builder.build();
        Request newRequest = request.newBuilder().post(newBody).build();
        return chain.proceed(newRequest);
    }

    /*Request request = chain.request();
    String s = request.url().url().toString();
    String url = s + "&source=android";

    Request newRequest = request.newBuilder().url(url).build();
    HttpUrl url1 = newRequest.url();*/
}
