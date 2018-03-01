package bwie.com.a1510dproject.presenter;

import java.util.ArrayList;
import java.util.List;

import bwie.com.a1510dproject.bean.CatagoryBean;
import bwie.com.a1510dproject.bean.ProductCatagoryBean;
import bwie.com.a1510dproject.model.ClassModel;
import bwie.com.a1510dproject.model.IClassModel;
import bwie.com.a1510dproject.net.OnNetListener;
import bwie.com.a1510dproject.view.IClassActivity;

/**
 * Created by peng on 2017/12/1.
 */

public class ClassPresenter {
    private IClassModel iClassModel;
    private IClassActivity iClassActivity;

    public ClassPresenter(IClassActivity iClassActivity) {
        this.iClassActivity = iClassActivity;
        iClassModel = new ClassModel();
    }

    public void getCatagory() {
        iClassModel.getCatagory(new OnNetListener<CatagoryBean>() {
            @Override
            public void onSuccess(CatagoryBean catagoryBean) {
                iClassActivity.showCatagory(catagoryBean.getData());
                //获取默认第一条京东超市对应的右侧数据
                getProductCatagory(catagoryBean.getData().get(0).getCid() + "");
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getProductCatagory(String cid) {
        iClassModel.getProductCatagory(cid, new OnNetListener<ProductCatagoryBean>() {
            @Override
            public void onSuccess(ProductCatagoryBean productCatagoryBean) {
                List<List<ProductCatagoryBean.DataBean.ListBean>> child = new ArrayList<>();
                List<ProductCatagoryBean.DataBean> group = productCatagoryBean.getData();
                for (int i = 0; i < group.size(); i++) {
                    ProductCatagoryBean.DataBean dataBean = group.get(i);
                    List<ProductCatagoryBean.DataBean.ListBean> list = dataBean.getList();
                    child.add(list);
                }
                iClassActivity.showProductCatagory(group, child);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


}
