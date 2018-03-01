package bwie.com.a1510dproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import bwie.com.a1510dproject.R;
import bwie.com.a1510dproject.bean.ProductCatagoryBean;
import bwie.com.a1510dproject.view.ListActivity;

/**
 * Created by peng on 2017/12/1.
 */

public class ProductCatagoryAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<ProductCatagoryBean.DataBean> group;
    private List<List<ProductCatagoryBean.DataBean.ListBean>> child;
    private LayoutInflater inflater;

    public ProductCatagoryAdapter(Context context, List<ProductCatagoryBean.DataBean> group, List<List<ProductCatagoryBean.DataBean.ListBean>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        View view;
        if (convertView == null) {
            holder = new GroupViewHolder();
            view = inflater.inflate(R.layout.product_catagory_parent, null);
            holder.tv = view.findViewById(R.id.tv);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        ProductCatagoryBean.DataBean dataBean = group.get(groupPosition);
        holder.tv.setText(dataBean.getName());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        View view;
        if (convertView == null) {
            holder = new ChildViewHolder();
            view = inflater.inflate(R.layout.product_catagory_child, null);
            holder.gv = view.findViewById(R.id.gv);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        final List<ProductCatagoryBean.DataBean.ListBean> listBeen = child.get(groupPosition);
        GvAdapter adapter = new GvAdapter(context, listBeen);
        holder.gv.setAdapter(adapter);
        holder.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra("pscid", listBeen.get(position).getPscid() + "");
                context.startActivity(intent);

            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupViewHolder {
        TextView tv;
    }

    class ChildViewHolder {
        GridView gv;
    }
}
