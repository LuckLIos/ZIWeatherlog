package com.example.lucklios.shi;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;//-----------------------------------------------
    private  Button buttonlog;
    private  Button buttonregest;
    private TextView textViewName;
    private  TextView textViewpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDatabaseHelper(this,"Person.db",null,1);//-----------------------------------
        buttonlog=(Button)findViewById(R.id.logon);
        buttonregest=(Button)findViewById(R.id.regest);
        textViewName=(TextView)findViewById(R.id.name);
        textViewpassword=(TextView)findViewById(R.id.password);
        buttonlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textViewName.getText().toString().equals("")||textViewpassword.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"姓名和密码不能为空",Toast.LENGTH_LONG).show();
                }else {
                    Boolean ok=false;
                    SQLiteDatabase db=dbHelper.getWritableDatabase();//----------------------------------------------------------
                    Cursor cursor=db.query("Person",null,null,null,null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            String name=cursor.getString(cursor.getColumnIndex("name"));
                            String password=cursor.getString(cursor.getColumnIndex("password"));

                          if(name.equals(textViewName.getText().toString())&&password.equals(textViewpassword.getText().toString())){
                              ok=true;
                            }

                        }while (cursor.moveToNext());
                    }
                    cursor.close();

                    if(ok){
                        textViewName.setText("");
                        textViewpassword.setText("");
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"用户名和密码不匹配",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        buttonregest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Regestctivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
