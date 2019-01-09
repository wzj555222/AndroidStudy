package com.wzj.android01.com.wzj.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wzj.android01.DB;
import com.wzj.android01.Login;
import com.wzj.android01.R;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {
    private EditText username;
    private EditText password;
    private TextView register;
    private Button login;
    private CheckBox showPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        //注册
        register=(TextView)findViewById(R.id.login_Register);
        View.OnClickListener registerOCL =(View v)->{
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        };
        register.setOnClickListener(registerOCL);

        //显示密码
        password=(EditText) findViewById(R.id.login_password);
        showPassword=(CheckBox)findViewById(R.id.showpassword);
        CompoundButton.OnCheckedChangeListener occl=(CompoundButton buttonView,boolean isChecked)->
        {
            if(isChecked) {
                password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
            else {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        };
        showPassword.setOnCheckedChangeListener(occl);

        //登陆
        username=(EditText)findViewById(R.id.login_username);
        login=(Button)findViewById(R.id.login_Btton);
        View.OnClickListener buttonOCL=(View v) ->{
            DB mydb=new DB(LoginActivity.this);
            SQLiteDatabase db=mydb.getReadableDatabase();
            Cursor cursor = db.rawQuery("select username,password from user",null);
            Map<String,String> map = new HashMap<>();
            while (cursor.moveToNext()) {
                map.put(cursor.getString(0),cursor.getString(1));
            }
            cursor.close();
            db.close();
            mydb.close();
            String user=username.getText().toString().trim();
            String pwd=password.getText().toString().trim();
            if (map.get(user)==null)
            {
                Toast.makeText(getApplicationContext(), "您输入的用户名不存在请重新输入", Toast.LENGTH_LONG).show();
            }
            else if (map.get(user).equals(pwd)) {//登陆成功
                Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_LONG).show();
                new Login().login();
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
            else{//登陆失败
                Toast.makeText(getApplicationContext(), "用户或密码错误", Toast.LENGTH_LONG).show();
            }
        };
        login.setOnClickListener(buttonOCL);
    }
}

