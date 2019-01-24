package com.bw.movie.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    //抽取吐司工具类
    private static Toast toast;
    public static void show(Context context,String msg){
        if(toast==null){
            toast = Toast.makeText(context.getApplicationContext(),msg,Toast.LENGTH_LONG);
        }else{
            toast.setText(msg);
            toast.show();
        }
    }
}
