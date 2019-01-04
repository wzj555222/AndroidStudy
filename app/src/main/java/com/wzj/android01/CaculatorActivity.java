package com.wzj.android01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class CaculatorActivity extends Activity {

    private Button[] bt=new Button[10];//0到9的数字
    private Button add;
    private Button equal;
    private Button dot;
    private Button backspace;
    private Button clear;
    private TextView result;
    private TextView text;
    private static Activity caculatorActivity;
//    private StringBuilder text=new StringBuilder();//用于存放输入


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caculator);
        caculatorActivity=this;
        add=findViewById(R.id.btadd);
        equal=findViewById(R.id.btequal);
        dot=findViewById(R.id.btdot);
        backspace=findViewById(R.id.btback);
        clear=findViewById(R.id.btclear);
        text=findViewById(R.id.in);
        result=findViewById(R.id.result);

//用反射给bt0到bt9赋值
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
        //数字按钮
        View.OnClickListener numberClickListener=(View view)->{
            String value=((Button)view).getText().toString();
            text.append(value);
        };
        //+
        View.OnClickListener addClickListener=(View view)->{
            //限定为两位
            if(!(text.getText().toString().contains("+")) && text.getText().toString().length()!=0) {//最后一位是.
                if(text.getText().toString().charAt(text.getText().toString().length()-1)=='.') {
                    text.append("0+");
                }//最后一位是数字
                else{
                   text.append("+");
                }
            }
            // 最后一位是+(运算符)   因为两位所以不用写这个
        };
        //=
        View.OnClickListener equalClickListener=(View view)->{
            //最后一位是.补0
            if(text.getText().toString().substring(text.getText().toString().length()-1)==".") {
                text.append("0");
            }
            Caculator caculator =new AddCaculator();
            caculator.judgeDateType(text.getText().toString());
            text.setText("");
            result.setText("");
            result.append(caculator.caculate());
        };
        //.
        View.OnClickListener dotClickListener=(View view)->{
            //空
            String[] checkdot = text.getText().toString().split("\\+");
            if(text.getText().toString().length()==0) {
                text.append("0.");
            }//前一位是＋则0. 如果拓展就加||
            else if (text.getText().toString().charAt(text.getText().toString().length()-1)=='+') {
                text.append("0.");
            }
            //如果最后一位数不包含.
            else if(!checkdot[checkdot.length-1].contains(".")) {
                text.append(".");
            }
        };
        //<-
        View.OnClickListener backClickListener=(View view)->{
            if (text.getText().toString().length()!=0) {
                StringBuilder stre = new StringBuilder();
                stre.append(text.getText().toString());
                stre.deleteCharAt(stre.length() - 1);
                text.setText("");
                text.append(stre.toString());
            }
        };
        //清空
        View.OnClickListener clearClickListener=(View view)->{
            text.setText("");
            result.setText("");
        };
        for (int i = 0; i <bt.length ; i++) {
            bt[i].setOnClickListener(numberClickListener);
        }
        equal.setOnClickListener(equalClickListener);
        add.setOnClickListener(addClickListener);
        dot.setOnClickListener(dotClickListener);
        backspace.setOnClickListener(backClickListener);
        clear.setOnClickListener(clearClickListener);
    }
    public static Activity getCaculatorActivity() {
        return caculatorActivity;
    }
}
