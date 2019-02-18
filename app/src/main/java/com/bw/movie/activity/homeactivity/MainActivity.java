package com.bw.movie.activity.homeactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.activity.useractivity.LoginActivity;
import com.bw.onlymycinema.R;


public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    int i=5;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            int z = msg.what;
            if(z==0){
                boolean v = preferences.getBoolean("v",false);
                if(v==false){
                    startActivity(new Intent(MainActivity.this,GuideActivity.class));
                    finish();
                }else if(v==true){
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    finish();
                }
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mian);

        preferences = getSharedPreferences("zzz",MODE_PRIVATE);
        editor = preferences.edit();
        //创建一个主线程
        new Thread(){
            @Override
            public void run() {

                while (i>0){
                    try {
                        i--;
                        sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(i);
                }
                super.run();
            }
        }.start();
    }
}
