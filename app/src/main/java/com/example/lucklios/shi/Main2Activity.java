package com.example.lucklios.shi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    public String[] data={"dshdj","dsjhdks","dsjd","dsdgsh","dsdhsk","dshdk","ds","dsd","ds","dskhdk","dskj","djsjds","dskj","ds","djksd","djskjd","djskhd"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1,data);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Main2Activity.this,i+":"+data[i],Toast.LENGTH_SHORT).show();
            }
        });
    }
}
