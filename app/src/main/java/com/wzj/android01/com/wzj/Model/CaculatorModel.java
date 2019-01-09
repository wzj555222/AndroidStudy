package com.wzj.android01.com.wzj.Model;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wzj.android01.AddArithmetic1;
import com.wzj.android01.Arithmetic;

public class CaculatorModel {
    //按钮
    private Button[] number;
    private Button add;
    private Button equal;
    private Button dot;
    private Button backspace;
    private Button clear;
    //显示器
    private TextView result;
    private  TextView text;
    //运算器
    private AddArithmetic1 arithmetic;

    public CaculatorModel() {

    }

    public Button[] getNumber() {
        return number;
    }

    public void setNumber(Button[] number) {
        this.number = number;
    }

    public Button getAdd() {
        return add;
    }

    public void setAdd(Button add) {
        this.add = add;
    }

    public Button getEqual() {
        return equal;
    }

    public void setEqual(Button equal) {
        this.equal = equal;
    }

    public Button getDot() {
        return dot;
    }

    public void setDot(Button dot) {
        this.dot = dot;
    }

    public Button getBackspace() {
        return backspace;
    }

    public void setBackspace(Button backspace) {
        this.backspace = backspace;
    }

    public Button getClear() {
        return clear;
    }

    public void setClear(Button clear) {
        this.clear = clear;
    }

    public TextView getResult() {
        return result;
    }

    public void setResult(TextView result) {
        this.result = result;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }

    //数字按钮
    public View.OnClickListener numberClickListener=(View view)->{
        String value=((Button)view).getText().toString();
        this.getText().append(value);
    };

    //+
    public View.OnClickListener addClickListener=(View view)->{
        //限定为两位
        if(!(this.getText().getText().toString().contains("+")) && this.getText().getText().toString().length()!=0) {//最后一位是.
            if(this.getText().getText().toString().charAt(this.getText().getText().toString().length()-1)=='.') {
                this.getText().append("0+");
            }//最后一位是数字
            else{
                this.getText().append("+");
            }
        }
        // 最后一位是+(运算符)   因为两位所以不用写这个
    };

    //=
    public View.OnClickListener equalClickListener=(View view)->{
        //当输入的字符串长度不为0时按钮才响应
        if (this.getText().getText().toString().length()!=0) {
            //最后一位是.补0
            if (this.getText().getText().toString().substring(this.getText().getText().toString().length() - 1) == ".") {
                this.getText().append("0");
            }
            arithmetic = new AddArithmetic1();
            arithmetic.setNumber(this.getText().getText().toString());
            this.getText().setText("");
            this.getResult().setText("");
            this.getResult().append(arithmetic.caculate());
        }
    };

    //.
    public View.OnClickListener dotClickListener=(View view)->{
        //空
        String[] checkdot = this.getText().getText().toString().split("\\+");
        if(this.getText().getText().toString().length()==0) {
            this.getText().append("0.");
        }//前一位是＋则0. 如果拓展就加||
        else if (this.getText().getText().toString().charAt(this.getText().getText().toString().length()-1)=='+') {
            this.getText().append("0.");
        }
        //如果最后一位数不包含.
        else if(!checkdot[checkdot.length-1].contains(".")) {
            this.getText().append(".");
        }
    };

    //<-
    public View.OnClickListener backClickListener=(View view)->{
        if (this.getText().getText().toString().length()!=0) {
            StringBuilder stre = new StringBuilder();
            stre.append(this.getText().getText().toString());
            stre.deleteCharAt(stre.length() - 1);
            this.getText().setText("");
            this.getText().append(stre.toString());
        }
    };

    //清空
    public View.OnClickListener clearClickListener=(View view)->{
        this.getText().setText("");
        this.getResult().setText("");
    };
}
