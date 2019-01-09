package com.wzj.android01.com.wzj.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wzj.android01.DB;
import com.wzj.android01.R;

public class RegisterActivity extends Activity {
    private EditText username;
    private EditText password;
    private EditText surePassword;
    private Button register;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username =(EditText)findViewById(R.id.login_username);
        password =(EditText)findViewById(R.id.login_password);
        surePassword=(EditText)findViewById(R.id.login_surepassword);
        register=(Button)findViewById(R.id.login_register);
        View.OnClickListener  registerOCL=(View view)->{
            String pwd =password.getText().toString().trim();
            String spwd=surePassword.getText().toString().trim();
            if (pwd.equals(spwd)) {
                DB mydb=new DB(RegisterActivity.this);
                SQLiteDatabase db=mydb.getReadableDatabase();
                Cursor cursor = db.rawQuery("select username from user where username=?",
                        new String[]{username.getText().toString().trim()});
                int count=cursor.getCount();
                cursor.close();
                db.close();
                if (count!=0){
                    Toast.makeText(getApplicationContext(), "用户名已存在请换一个用户名", Toast.LENGTH_LONG).show();
                }
                else {
                        Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
                        SQLiteDatabase sqlDB=mydb.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("username",username.getText().toString().trim());
                        values.put("password",password.getText().toString().trim());
                        sqlDB.insert("user",null,values);
                        sqlDB.close();
                        db.close();
                        Intent intent = new Intent();
                        intent.setClass(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                }
            }else {
                Toast.makeText(getApplicationContext(), "请检查您输入的密码", Toast.LENGTH_LONG).show();
            }
        };
        register.setOnClickListener(registerOCL);
    }
}
