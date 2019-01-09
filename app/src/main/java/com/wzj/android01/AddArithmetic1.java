package com.wzj.android01;

import android.widget.Toast;

import com.wzj.android01.com.wzj.Activity.CaculatorActivity;

import java.util.HashMap;

public class AddArithmetic1 extends Arithmetic {
    //用来运算的数
    private String[] number;
    //运算结果
    private String result;
    //操作符
    private String operator;
    //类型
    private HashMap<String,String> hashMap;
    int countFloat;//浮点数个数

    //设置运算符
    @Override
    public void setOperator() {
        this.operator="\\+";
    }

    public void setNumber(String str) {
        this.setOperator();
        this.number = str.split(operator);
    }
    //判断输入的数据类型合法性
    @Override
    public boolean isDateTypeLegitimate(){
        this.judgeDateType();
        if (number.length!=2) {
            Toast.makeText(CaculatorActivity.getCaculatorActivity(),
                    "本计算器只支持两数运算",Toast.LENGTH_LONG).show();
            return false;
        }
        // 两数其中一个数包含.或另一个数包含.并且不是两个数都有.
        for(int i=0;i<number.length;i++) {
            if (hashMap.get(number[i]).equals("float")) {
                countFloat++;
            }
        }
        //如果浮点型个数不为0且不全是浮点型
        if(countFloat!=0 && countFloat!=number.length) {
            Toast.makeText(CaculatorActivity.getCaculatorActivity(),
                    "本计算器要求输入的两个数据类型相同",Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }
    //判断number数组里元素数据类型并以 元素:数据类型 形式存在hash表里
    public void judgeDateType() {
        hashMap = new HashMap<>();
        for (String num : number) {
            if (num.contains(".")) {
                hashMap.put(num, "float");
            } else {
                hashMap.put(num, "int");
            }
        }
    }
    //计算结果并返回
    @Override
    public String caculate(){
        if(isDateTypeLegitimate()) {
            if (countFloat == number.length) {
                float number1 = Float.valueOf(number[0]);
                float number2 = Float.valueOf(number[1]);
                float result = number1 + number2;
                this.result = result + "";
            }
            if (countFloat == 0) {
                int number1 = Integer.valueOf(number[0]);
                int number2 = Integer.valueOf(number[1]);
                int result = number1 + number2;
                this.result = result + "";
            }
            return result;
        }
        else {
            return "";
        }

    }
}
