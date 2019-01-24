package com.bw.movie.activity.useractivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.activity.homeactivity.HomeActivity;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.userbean.LoginBean;
import com.bw.movie.mvp.util.Apis;
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
    @BindView(R.id.login_cb_remaber_pass)
    CheckBox mRpass;
    @BindView(R.id.login_cb_self_login)
    CheckBox mRlogin;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void initData() {
        //去登陆
        toLogin();
        //去注册
        toRegister();
        //自动登录
        initRemeberPass();
    }

    private void toRegister() {
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void initRemeberPass() {
        //取出状态值
        boolean ischeck = sharedPreferences.getBoolean("ischeck",false);
        //判断他为true就记住
        if(ischeck)
        {
            String number = sharedPreferences.getString("login_number", null);
            String pass = sharedPreferences.getString("login_pass", null);

            mLnumber.setText(number);
            mLpass.setText(pass);
            mRpass.setChecked(ischeck);
        }
    }

    private void toLogin() {
        login_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = mLnumber.getText().toString();
                String pass = mLpass.getText().toString();
                Log.i("TTTBLR",pass);
                String encrypt = EncryptUtil.encrypt(pass);
                if(mRpass.isChecked()){
                    editor.putString("login_number",number);
                    editor.putString("login_pass",pass);

                    editor.putBoolean("ischeck",true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }
                Map<String,String> map = new HashMap<>();
                map.put("phone",number);
                map.put("pwd",encrypt);
                Log.i("TTTbLRR",encrypt);
                doNetPost(Apis.URL_POST_LOGIN,map,LoginBean.class);
            }
        });
    }

    @Override
    protected void initView() {
        //绑定ButterKnife控件
        ButterKnife.bind(this);
        //储存方式
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
                //取出个人信息的状态值
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
