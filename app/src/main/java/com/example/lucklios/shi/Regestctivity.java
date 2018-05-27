package com.example.lucklios.shi;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Regestctivity extends AppCompatActivity {

    private Button button;
    private TextView regestName;
    private  TextView regestPassword;
    private  TextView regestPasswordAgain;
    private  MyDatabaseHelper dbHelper;//--------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestctivity);
        button=(Button)findViewById(R.id.regestregest);
        regestName=(TextView)findViewById(R.id.rsgestname);
        regestPassword=(TextView)findViewById(R.id.rsgestpassword);
        regestPasswordAgain=(TextView)findViewById(R.id.regestpasswordagain);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(regestName.getText().toString().equals("")||regestPassword.getText().toString().equals("")||regestPasswordAgain.getText().toString().equals("")){
                    Toast.makeText(Regestctivity.this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();

                }else if(!(regestPassword.getText().toString().equals(regestPasswordAgain.getText().toString()))){
                    Toast.makeText(Regestctivity.this,"输入密码不一致",Toast.LENGTH_SHORT).show();
                }
                else {
                    dbHelper=new MyDatabaseHelper(Regestctivity.this,"Person.db",null,1);//sql----------------
                    SQLiteDatabase db=dbHelper.getWritableDatabase();//------------------------------------------------
                    ContentValues values =new ContentValues();
                    values.put("name",regestName.getText().toString());
                    values.put("password",regestPassword.getText().toString());
                    db.insert("Person",null,values);
                    Intent intent=new Intent(Regestctivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(Regestctivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
