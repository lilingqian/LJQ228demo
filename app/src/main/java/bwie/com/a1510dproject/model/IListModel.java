package bwie.com.a1510dproject.model;

import java.util.Map;

import bwie.com.a1510dproject.bean.ListBean;
import bwie.com.a1510dproject.net.OnNetListener;

/**
 * Created by peng on 2017/12/7.
 */

public interface IListModel {
    void getList(Map<String, String> params, OnNetListener<ListBean> onNetListener);
}
