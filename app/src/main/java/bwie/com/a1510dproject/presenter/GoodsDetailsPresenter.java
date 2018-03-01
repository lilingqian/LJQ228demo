package bwie.com.a1510dproject.presenter;

import bwie.com.a1510dproject.bean.GoodsDetailsBean;
import bwie.com.a1510dproject.model.GoodsDetailsModel;
import bwie.com.a1510dproject.model.IGoodsDetailsModel;
import bwie.com.a1510dproject.net.OnNetListener;
import bwie.com.a1510dproject.view.fragment.IGoodsFragment;

/**
 * Created by peng on 2017/12/8.
 */

public class GoodsDetailsPresenter {

    private IGoodsFragment iGoodsFragment;
    private IGoodsDetailsModel igoodsDetailsModel;

    public GoodsDetailsPresenter(IGoodsFragment iGoodsFragment) {
        this.iGoodsFragment = iGoodsFragment;
        igoodsDetailsModel = new GoodsDetailsModel();
    }

    public void dettach() {
        iGoodsFragment = null;
    }

    public void getProductDetail(String pid) {
        igoodsDetailsModel.getProductDetail(pid, new OnNetListener<GoodsDetailsBean>() {
            @Override
            public void onSuccess(GoodsDetailsBean goodsDetailsBean) {
                if (iGoodsFragment != null) {
                    iGoodsFragment.show(goodsDetailsBean);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
