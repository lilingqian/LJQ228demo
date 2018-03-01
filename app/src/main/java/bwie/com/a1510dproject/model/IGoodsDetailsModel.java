package bwie.com.a1510dproject.model;

import bwie.com.a1510dproject.bean.GoodsDetailsBean;
import bwie.com.a1510dproject.net.OnNetListener;

/**
 * Created by peng on 2017/12/8.
 */

public interface IGoodsDetailsModel {
    void getProductDetail(String pid, final OnNetListener<GoodsDetailsBean> onNetListener);
}
