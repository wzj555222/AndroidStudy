package com.wzj.android01.com.wzj.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.wzj.android01.R;
import com.wzj.android01.com.wzj.Model.CaculatorModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class CaculatorActivity extends Activity {

    private  CaculatorModel caculator;

    private static Activity caculatorActivity;
    public static Activity getCaculatorActivity() {
        return caculatorActivity;
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.caculator);
        caculatorActivity=this;

        //指向对应的控件
        caculator=new CaculatorModel();
        caculator.setAdd(findViewById(R.id.btadd));
        caculator.setEqual(findViewById(R.id.btequal));
        caculator.setDot(findViewById(R.id.btdot));
        caculator.setBackspace(findViewById(R.id.btback));
        caculator.setClear(findViewById(R.id.btclear));
        caculator.setText(findViewById(R.id.in));
        caculator.setResult(findViewById(R.id.result));
        //利用反射给bt0到bt9赋值
        Button[] bt=new Button[10];
        for(int i=0;i<bt.length;i++) {
            try {
                String name="bt"+i;
                Class<?> clazz=Class.forName("com.wzj.android01.R$id");
                Field btNumber=clazz.getDeclaredField(name);
                btNumber.setAccessible(true);
                com.wzj.android01.R.id id=(R.id)clazz.getDeclaredConstructor().newInstance();
                bt[i]=findViewById(btNumber.getInt(id));
            }
            catch(IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        caculator.setNumber(bt);


        //将各个控件和对应的监听器进行绑定
        for (int i = 0; i <bt.length ; i++) {
            caculator.getNumber()[i].setOnClickListener(caculator.numberClickListener);
        }
        caculator.getEqual().setOnClickListener(caculator.equalClickListener);
        caculator.getAdd().setOnClickListener(caculator.addClickListener);
        caculator.getDot().setOnClickListener(caculator.dotClickListener);
        caculator.getBackspace().setOnClickListener(caculator.backClickListener);
        caculator.getClear().setOnClickListener(caculator.clearClickListener);
    }
}
