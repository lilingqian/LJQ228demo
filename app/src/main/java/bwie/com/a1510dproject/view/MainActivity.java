package bwie.com.a1510dproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bwie.com.a1510dproject.R;
import bwie.com.a1510dproject.presenter.RegisterPresenter;
import bwie.com.a1510dproject.uitls.MyLogger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMainActivity {

    /**
     * 开始请求
     */
    private Button mBt;

    /**
     * 输入账号
     */
    private EditText mEtAccout;
    /**
     * 输入密码
     */
    private EditText mEtPwd;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyLogger.Log_e("MainActivity", "MainActivity");
        registerPresenter = new RegisterPresenter(this);
    }

    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        mEtAccout = (EditText) findViewById(R.id.etAccout);
        mEtPwd = (EditText) findViewById(R.id.etPwd);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPresenter.dettach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                //注册
                registerPresenter.register();
                break;
        }
    }

    public String getAccout() {
        return mEtAccout.getText().toString().trim();
    }

    public String getPwd() {
        return mEtPwd.getText().toString().trim();
    }

    public void GoLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void show(String str) {
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }
}
