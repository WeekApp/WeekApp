package com.bw.movie.activity.useractivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.activity.homeactivity.HomeActivity;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.userbean.LoginBean;
import com.bw.movie.mvp.persenter.IPersenter;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.mvp.view.IView;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_text_fast)
    TextView mRegister;
    @BindView(R.id.login_sure)
    Button login_sure;
    @BindView(R.id.login_edit_number)
    EditText mLnumber;
    @BindView(R.id.login_edit_pass)
    EditText mLpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void initData() {
        //去登陆
        toLogin();
        //自动登录
        initRemeberPass();
    }

    private void initRemeberPass() {

    }

    private void toLogin() {
        login_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = mLnumber.getText().toString();
                String pass = mLpass.getText().toString();
                String encrypt = EncryptUtil.encrypt(pass);
                Map<String,String> map = new HashMap<>();
                map.put("phone",number);
                map.put("pwd",encrypt);
                //doNetPost(Apis.URL_POST_LOGIN,map,LoginBean.class);

                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //加载布局
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof LoginBean){
            LoginBean user = (LoginBean) data;
            if(user.getStatus().equals("0000")){

                //取出个人账户的状态值
                editor.putString("userId",user.getResult().getUserId()+"")
                        .putString("sessionId",user.getResult().getSessionId()).commit();
                startActivity(new Intent(this,HomeActivity.class));
            }else{
                ToastUtils.show(this,user.getMessage());
            }

        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
