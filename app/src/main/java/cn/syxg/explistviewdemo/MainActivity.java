package cn.syxg.explistviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private CustomExpandListview listview;
    private MyAdapter myAdapter;


    private ArrayList<String> parent = new ArrayList<>();
    private Map<String, ArrayList<String>> datas = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (CustomExpandListview) findViewById(R.id.listview);

        listview.setGroupIndicator(null);

        //模拟数据
        //for (int i = 0; i < parentSource.length; i++) {
            parent.add("08月");
            parent.add("07月");
            parent.add("06月");
            parent.add("05月");
            parent.add("04月");
        //}

        for (int i = 0; i < parent.size(); i++) {
            String str = parent.get(i);
            ArrayList<String> temp = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                temp.add("" + j);
            }
            datas.put(str, temp);
        }

        //datas.put("03月",new ArrayList<String>());


        myAdapter = new MyAdapter(this, parent, datas, listview);
        listview.setAdapter(myAdapter);

        View view = LayoutInflater.from(this).inflate(R.layout.heard_item,null);

        listview.addHeaderView(view);

        listview.setHeaderView(getLayoutInflater().inflate(
                R.layout.parent_layout, listview, false));

        listview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(MainActivity.this, "点击了第" + (i + 1) + " 类的第" + i1 + "项", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        /*// 默认全部展开
        for (int i = 0; i < parent.size(); i++) {
            listview.expandGroup(i);
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("resultAAman",requestCode+">>"+resultCode);


    }
}
