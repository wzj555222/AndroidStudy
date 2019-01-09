package com.wzj.android01.com.wzj.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wzj.android01.R;

public class MainActivity extends AppCompatActivity {
    private ImageView caculator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //点击图片显示计算器界面
        caculator=(ImageView)findViewById(R.id.caculator);
        View.OnClickListener caculatorListener= (View view)->{
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,CaculatorActivity.class);
            startActivity(intent);
        };
        caculator.setOnClickListener(caculatorListener);
    }
}
