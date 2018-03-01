package com.example.lenovo.ljq_228_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     FruitAdapter adapter=new FruitAdapter(MainActivity.this,R.layout.fruit_item,fruitList);
     ListView listView=findViewById(R.id.list);



       listView.setAdapter(adapter);
        //实例化一个水果
        initFruits();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fruit=fruitList.get(i);
                Toast.makeText(MainActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initFruits() {
        for (int i=0;i<2;i++){
            Fruit apple=new Fruit("Apple",R.mipmap.ic_launcher);
            fruitList.add(apple);
            Fruit banner=new Fruit("abnner",R.mipmap.ic_launcher);
            fruitList.add(banner);
        }
    }
}
