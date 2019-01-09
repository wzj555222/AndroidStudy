package com.wzj.android01;
import android.widget.Toast;

import java.util.HashMap;
//抽象运算器
public abstract class Arithmetic {
    //用来运算的数
    private String[] number;
    //运算结果
    private String result;
    //操作符
    private String operator;
    //类型
    private HashMap<String, String> hashMap;

    //设置运算符
    public abstract void setOperator();
    //获取数据存入number数组中
    public void setNumber(String str) {
        this.setOperator();
        this.number = str.split(operator);
    }

    //判断number数组里元素数据类型并以 元素:数据类型 形式存在hash表里
    public void judgeDateType() {
        for (String num : number) {
            if (num.contains(".")) {
                hashMap.put(num, "float");
            } else {
                hashMap.put(num, "int");
            }
        }
    }


    //判断输入的数据类型合法性
    public abstract boolean isDateTypeLegitimate();

    //计算结果并返回
    public abstract String caculate();
}
