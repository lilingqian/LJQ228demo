package bwie.com.a1510dproject.presenter;

import java.util.HashMap;
import java.util.Map;

import bwie.com.a1510dproject.bean.ListBean;
import bwie.com.a1510dproject.model.IListModel;
import bwie.com.a1510dproject.model.ListModel;
import bwie.com.a1510dproject.net.OnNetListener;
import bwie.com.a1510dproject.view.IListActivity;

/**
 * Created by peng on 2017/12/6.
 */

public class ListPresenter {
    private IListModel listModel;
    private IListActivity iListActivity;

    public ListPresenter(IListActivity iListActivity) {
        listModel = new ListModel();
        this.iListActivity = iListActivity;
    }

    public void dettach() {
        iListActivity = null;
    }

    public void getList(String pscid, String page, String sort) {
        Map<String, String> params = new HashMap<>();
        params.put("pscid", pscid);
        params.put("page", page);
        params.put("sort", sort);
        listModel.getList(params, new OnNetListener<ListBean>() {
            @Override
            public void onSuccess(ListBean listBean) {
                if (iListActivity != null) {
                    iListActivity.showList(listBean.getData());
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

}
