package com.wzj.android01;

import android.app.Activity;
import android.widget.Button;

public class Login {
    private final int LOGINSTATE = 1;
    private final int UNLOGINSTATE=0;
    private static int thisState;
    public static int getThisState() {
        return thisState;
    }
    public void login()
    {

        thisState=LOGINSTATE;
    }
}
