package bwie.com.a1510dproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import bwie.com.a1510dproject.R;
import bwie.com.a1510dproject.bean.CatagoryBean;

/**
 * Created by peng on 2017/12/1.
 */

public class CatagoryAdapter extends BaseAdapter {
    private Context context;
    private List<CatagoryBean.DataBean> list;
    private final LayoutInflater inflater;

    public CatagoryAdapter(Context context, List<CatagoryBean.DataBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view;
        if (convertView == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.catagory_item, null);
            holder.tv = view.findViewById(R.id.tv);
            holder.ll = view.findViewById(R.id.ll);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        CatagoryBean.DataBean dataBean = list.get(position);
        holder.tv.setText(dataBean.getName());
        if (dataBean.isChecked()) {
            holder.ll.setBackgroundColor(Color.parseColor("#33000000"));
        } else {
            holder.ll.setBackgroundColor(Color.WHITE);
        }
        return view;
    }

    class ViewHolder {
        TextView tv;
        LinearLayout ll;
    }

    public void changeItemSelect(int position) {
        //先还原所有的选中状态
        for (int i = 0; i < list.size(); i++) {
            CatagoryBean.DataBean dataBean = list.get(i);
            dataBean.setChecked(false);
        }
        CatagoryBean.DataBean dataBean = list.get(position);
        dataBean.setChecked(true);
        notifyDataSetChanged();
    }

}
