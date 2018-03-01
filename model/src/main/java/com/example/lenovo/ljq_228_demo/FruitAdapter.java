package com.example.lenovo.ljq_228_demo;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by lenovo on 2018/3/1.
 */

public class FruitAdapter extends ArrayAdapter<Fruit>{

    private int resourceId;




 public  FruitAdapter(Context context,int textViewResourceid,List<Fruit>object){
        super(context,textViewResourceid,object);
     resourceId=textViewResourceid;
 }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //创建一个水果实例
        Fruit fruit=getItem(position);
        ViewHodel viewHodel;
        View view;
        if (convertView==null){
             view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
             viewHodel=new ViewHodel();
             viewHodel.fruitImage=view.findViewById(R.id.fruit_image);
               viewHodel.fruitName=view.findViewById(R.id.fruit_name);
               view.setTag(viewHodel);
        }
        else{
            view=convertView;
            viewHodel= (ViewHodel) view.getTag();
        }

      viewHodel. fruitImage.setImageResource(fruit.getImageid());
     viewHodel.  fruitName.setText(fruit.getName());
       return view;
    }

    class ViewHodel{
     ImageView fruitImage;
     TextView fruitName;
    }
}
