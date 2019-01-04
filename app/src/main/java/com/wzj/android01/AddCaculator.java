package com.wzj.android01;

import android.widget.Toast;

public class AddCaculator implements Caculator {

    private Number result;

    public AddCaculator(){
    }

    public Number getResult() {
        return result;
    }

    public void setResult(Number result) {
        this.result = result;
    }

    //判断输入的数据类型合法性
    public void judgeDateType(String str){
        String[] number =str.split("\\+");
        if (number.length!=2) {
            Toast.makeText(CaculatorActivity.getCaculatorActivity(),
                    "本计算器只支持两数运算",Toast.LENGTH_LONG).show();
        }
        else if(number[0].contains(".") && number[1].contains(".")) {
            float number1=Float.valueOf(number[0]);
            float number2=Float.valueOf(number[1]);
            float result=number1+number2;
            setResult(result);
        }
        else if(number[0].contains(".")||number[1].contains(".")) {
            Toast.makeText(CaculatorActivity.getCaculatorActivity(),
                    "本计算器要求输入的两个数据类型相同",Toast.LENGTH_LONG).show();
        }else {
            int number1=Integer.valueOf(number[0]);
            int number2=Integer.valueOf(number[1]);
            int result=number1+number2;
            setResult(result);
        }
    }
    //计算并返回结果
    public String caculate(){
        String result=getResult()+"";
        return result;
    }
}
