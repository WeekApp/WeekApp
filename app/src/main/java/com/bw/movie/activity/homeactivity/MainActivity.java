package com.bw.movie.activity.homeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bw.movie.activity.useractivity.LoginActivity;
import com.bw.onlymycinema.R;


public class MainActivity extends AppCompatActivity {

    int i=1;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            int z = msg.what;
            if(z==0){
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mian);

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
