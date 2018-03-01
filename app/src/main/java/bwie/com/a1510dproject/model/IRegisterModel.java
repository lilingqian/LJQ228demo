package bwie.com.a1510dproject.model;

import java.util.Map;

import bwie.com.a1510dproject.RegisterBean;
import bwie.com.a1510dproject.net.OnNetListener;

/**
 * Created by peng on 2017/12/1.
 */

public interface IRegisterModel {
    public void register(Map<String, String> params, final OnNetListener<RegisterBean> onNetListener);
}
