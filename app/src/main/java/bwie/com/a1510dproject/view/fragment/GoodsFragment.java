package bwie.com.a1510dproject.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bwie.com.a1510dproject.R;
import bwie.com.a1510dproject.bean.GoodsDetailsBean;
import bwie.com.a1510dproject.presenter.GoodsDetailsPresenter;

/**
 * Created by peng on 2017/12/8.
 */

public class GoodsFragment extends BaseFragment implements IGoodsFragment {

    private GoodsDetailsPresenter goodsDetailsPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String pid = bundle.getString("pid");
        goodsDetailsPresenter = new GoodsDetailsPresenter(this);
        View view = inflater.inflate(R.layout.fragment_goods, null);
        //调用接口
        goodsDetailsPresenter.getProductDetail(pid);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        goodsDetailsPresenter.dettach();
    }

    public void show(GoodsDetailsBean goodsDetailsBean) {

    }
}
