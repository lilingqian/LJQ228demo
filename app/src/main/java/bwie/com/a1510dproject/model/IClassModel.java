package bwie.com.a1510dproject.model;

import bwie.com.a1510dproject.bean.CatagoryBean;
import bwie.com.a1510dproject.bean.ProductCatagoryBean;
import bwie.com.a1510dproject.net.OnNetListener;

/**
 * Created by peng on 2017/12/1.
 */

public interface IClassModel {
    public void getCatagory(final OnNetListener<CatagoryBean> onNetListener);

    public void getProductCatagory(String cid, final OnNetListener<ProductCatagoryBean> onNetListener);
}
