package bwie.com.a1510dproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import bwie.com.a1510dproject.R;
import bwie.com.a1510dproject.adapter.ListAdapter;
import bwie.com.a1510dproject.bean.ListBean;
import bwie.com.a1510dproject.presenter.ListPresenter;

/**
 * 列表页面
 */
public class ListActivity extends AppCompatActivity implements IListActivity, View.OnClickListener {

    private RecyclerView rv;
    private List<ListBean.DataBean> list = new ArrayList<>();
    private ListAdapter adapter;
    private ListPresenter presenter;
    /**
     * 综合
     */
    private TextView mTvZhonghe;
    /**
     * 销量
     */
    private TextView mTvXiaoliang;
    /**
     * 价格
     */
    private TextView mTvPrice;
    /**
     * 筛选
     */
    private TextView mTvShaixuan;
    private RecyclerView mRv;
    private String pscid;
    private SpringView mSv;
    private String sort = "0";
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
        //获取pscid
        Intent intent = getIntent();
        pscid = intent.getStringExtra("pscid");
        presenter = new ListPresenter(this);
        presenter.getList(pscid, page + "", "0");
        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAdapter(this, list);
        rv.setAdapter(adapter);

        mSv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //当前是什么状态，比如是综合还是销量
                page = 1;
                presenter.getList(pscid, page + "", sort);
            }

            @Override
            public void onLoadmore() {
                page++;
                presenter.getList(pscid, page + "", sort);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dettach();
    }

    @Override
    public void showList(List<ListBean.DataBean> list) {
        //判断是否是刷新状态
        if (page == 1) {
            this.list.clear();
            this.list.addAll(list);
        } else {
            this.list.addAll(list);
            if(this.list.size()>=20){
//                mSv.setEnable(false);
            }
        }
        mSv.onFinishFreshAndLoad();
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        mTvZhonghe = (TextView) findViewById(R.id.tvZhonghe);
        mTvZhonghe.setOnClickListener(this);
        mTvXiaoliang = (TextView) findViewById(R.id.tvXiaoliang);
        mTvXiaoliang.setOnClickListener(this);
        mTvPrice = (TextView) findViewById(R.id.tvPrice);
        mTvPrice.setOnClickListener(this);
        mTvShaixuan = (TextView) findViewById(R.id.tvShaixuan);
        mTvShaixuan.setOnClickListener(this);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setOnClickListener(this);
        mSv = (SpringView) findViewById(R.id.sv);
        mSv.setHeader(new DefaultHeader(this));
        mSv.setFooter(new DefaultFooter(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tvZhonghe:
                sort = "0";
                presenter.getList(pscid, "1", sort);
                break;
            case R.id.tvXiaoliang:
                sort = "1";
                presenter.getList(pscid, "1", sort);
                break;
            case R.id.tvPrice:
                sort = "2";
                presenter.getList(pscid, "1", sort);
                break;
            case R.id.tvShaixuan:
                break;
            case R.id.rv:
                break;
        }
    }
}
