package bwie.com.a1510dproject.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by peng on 2017/12/8.
 */

public class MyInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().url().toString();
        url+="&source=android";
        Request newRequset = request.newBuilder().url(url).build();
        return chain.proceed(newRequset);
    }
}
