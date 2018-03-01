package bwie.com.a1510dproject.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.youth.banner.Banner;

import java.util.List;

import bwie.com.a1510dproject.R;
import bwie.com.a1510dproject.adapter.CatagoryAdapter;
import bwie.com.a1510dproject.adapter.ProductCatagoryAdapter;
import bwie.com.a1510dproject.bean.CatagoryBean;
import bwie.com.a1510dproject.bean.ProductCatagoryBean;
import bwie.com.a1510dproject.presenter.ClassPresenter;

/**
 * 分类列表
 */
public class ClassActivity extends AppCompatActivity implements IClassActivity {

    private ClassPresenter classPresenter;
    private ListView mLv;
    private Banner mBanner;
    private ExpandableListView mElv;
    private ScrollView mSv;
    private CatagoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        initView();
        classPresenter = new ClassPresenter(this);
        //立马加载数据
        classPresenter.getCatagory();

        //设置左侧ListView条目点击事件
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CatagoryBean.DataBean dataBean = (CatagoryBean.DataBean) parent.getItemAtPosition(position);
                adapter.changeItemSelect(position);
                //获取cid
                int cid = dataBean.getCid();
                classPresenter.getProductCatagory(cid + "");
            }
        });
    }

    @Override
    public void showCatagory(List<CatagoryBean.DataBean> list) {
        //就是给ListView或者RecyclerView设置数据
        adapter = new CatagoryAdapter(this, list);
        mLv.setAdapter(adapter);
        adapter.changeItemSelect(0);
    }

    @Override
    public void showProductCatagory(List<ProductCatagoryBean.DataBean> group, List<List<ProductCatagoryBean.DataBean.ListBean>> child) {
        ProductCatagoryAdapter adapter = new ProductCatagoryAdapter(this, group, child);
        mElv.setAdapter(adapter);

        //展开列表
        for (int i = 0; i < group.size(); i++) {
            mElv.expandGroup(i);
        }
    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.lv);
        mBanner = (Banner) findViewById(R.id.banner);
        mElv = (ExpandableListView) findViewById(R.id.elv);
        mSv = (ScrollView) findViewById(R.id.sv);
    }
}
