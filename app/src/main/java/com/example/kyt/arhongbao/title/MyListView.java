package com.example.kyt.arhongbao.title;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kyt.arhongbao.R;

/**
 * Created by kyt on 2017/2/20.
 */
public class MyListView extends AppCompatActivity {

        private static final String[] strs = new String[] {
        "first", "second", "third", "fourth", "fifth"
        };//定义一个String数组用来显示ListView的内容private ListView lv;/** Called when the activity is first created. */

        public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditu);

        ListView lv = (ListView) findViewById(R.id.lv);//得到ListView对象的引用 /*为ListView设置Adapter来绑定数据*/
        lv.setAdapter(new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1, strs));

        }
        }
