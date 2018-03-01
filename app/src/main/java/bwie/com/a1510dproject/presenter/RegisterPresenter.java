package bwie.com.a1510dproject.presenter;

import java.util.HashMap;

import bwie.com.a1510dproject.RegisterBean;
import bwie.com.a1510dproject.model.IRegisterModel;
import bwie.com.a1510dproject.model.RegisterModel;
import bwie.com.a1510dproject.net.OnNetListener;
import bwie.com.a1510dproject.view.IMainActivity;

/**
 * Created by peng on 2017/12/1.
 */

public class RegisterPresenter {
    private IMainActivity mainActivity;
    private IRegisterModel iRegisterModel;

    public RegisterPresenter(IMainActivity mainActivity) {
        this.mainActivity = mainActivity;
        iRegisterModel = new RegisterModel();
    }

    public void dettach() {
        mainActivity = null;
    }

    public void register() {
        //调用View层的方法，去获取账号密码
        String accout = mainActivity.getAccout();
        String pwd = mainActivity.getPwd();
        HashMap<String, String> params = new HashMap<>();
        params.put("mobile", accout);
        params.put("password", pwd);
        iRegisterModel.register(params, new OnNetListener<RegisterBean>() {


            @Override
            public void onSuccess(RegisterBean registerBean) {
                mainActivity.show(registerBean.getMsg());
                mainActivity.GoLogin();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


}
