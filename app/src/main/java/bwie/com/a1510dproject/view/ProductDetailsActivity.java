package bwie.com.a1510dproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bwie.com.a1510dproject.R;
import bwie.com.a1510dproject.adapter.VpDetailsAdapter;
import bwie.com.a1510dproject.view.fragment.AppraiseFragment;
import bwie.com.a1510dproject.view.fragment.DetailsFragment;
import bwie.com.a1510dproject.view.fragment.GoodsFragment;

/**
 * 详情页面
 */
public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView mIvBack;
    /**
     * 商品
     */
    private RadioButton mRbGoods;
    /**
     * 详情
     */
    private RadioButton mRbDetails;
    /**
     * 评价
     */
    private RadioButton mRbAppraise;
    private RadioGroup mRg;
    private ImageView mIvShare;
    private ImageView mIvMsg;
    private ViewPager mVp;
    private LinearLayout mLlSupplier;
    private LinearLayout mLlShop;
    private LinearLayout mLlAttention;
    private LinearLayout mLlCard;
    /**
     * 加入购物车
     */
    private TextView mTvAddCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        //接收pid
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");

        initView();
        //给viewPager填充内容
        GoodsFragment goodsFragment = new GoodsFragment();
        DetailsFragment detailsFragment = new DetailsFragment();
        AppraiseFragment appraiseFragment = new AppraiseFragment();
        List<Fragment> list = new ArrayList<>();
        list.add(goodsFragment);
        list.add(detailsFragment);
        list.add(appraiseFragment);
        VpDetailsAdapter adapter = new VpDetailsAdapter(getSupportFragmentManager(), list);
        mVp.setAdapter(adapter);

        //传pid
        Bundle bundle = new Bundle();
        bundle.putString("pid", pid);
        goodsFragment.setArguments(bundle);
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.ivBack);
        mRbGoods = (RadioButton) findViewById(R.id.rbGoods);
        mRbDetails = (RadioButton) findViewById(R.id.rbDetails);
        mRbAppraise = (RadioButton) findViewById(R.id.rbAppraise);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mIvShare = (ImageView) findViewById(R.id.ivShare);
        mIvMsg = (ImageView) findViewById(R.id.ivMsg);
        mVp = (ViewPager) findViewById(R.id.vp);
        mLlSupplier = (LinearLayout) findViewById(R.id.llSupplier);
        mLlShop = (LinearLayout) findViewById(R.id.llShop);
        mLlAttention = (LinearLayout) findViewById(R.id.llAttention);
        mLlCard = (LinearLayout) findViewById(R.id.llCard);
        mTvAddCard = (TextView) findViewById(R.id.tvAddCard);
    }
}
