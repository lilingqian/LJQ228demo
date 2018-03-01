package bwie.com.a1510dproject.view;

import java.util.List;

import bwie.com.a1510dproject.bean.CatagoryBean;
import bwie.com.a1510dproject.bean.ProductCatagoryBean;

/**
 * Created by peng on 2017/12/1.
 */

public interface IClassActivity {

    public void showCatagory(List<CatagoryBean.DataBean> list);

    public void showProductCatagory(List<ProductCatagoryBean.DataBean> group, List<List<ProductCatagoryBean.DataBean.ListBean>> child);
}
