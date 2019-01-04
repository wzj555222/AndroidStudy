package com.wzj.android01;

public interface Caculator {
    //判断输入的数据类型合法性
    void judgeDateType(String string);
    //计算并返回结果
    String caculate();
}
