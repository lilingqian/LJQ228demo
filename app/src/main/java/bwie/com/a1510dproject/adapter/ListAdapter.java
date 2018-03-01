package bwie.com.a1510dproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bwie.com.a1510dproject.R;
import bwie.com.a1510dproject.bean.ListBean;
import bwie.com.a1510dproject.view.ProductDetailsActivity;

/**
 * Created by peng on 2017/12/5.
 */

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<ListBean.DataBean> list;

    public ListAdapter(Context context, List<ListBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ListBean.DataBean dataBean = list.get(position);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.tvName.setText(dataBean.getTitle());
        myViewHolder.tvPrice.setText(dataBean.getPrice() + "");
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(myViewHolder.iv);

        myViewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到详情页面
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("pid", dataBean.getPid() + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;
        private final TextView tvName;
        private final TextView tvPrice;
        private final LinearLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ll = itemView.findViewById(R.id.ll);

        }
    }
}
