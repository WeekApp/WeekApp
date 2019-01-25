package com.bw.movie.activity.useractivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.bw.movie.activity.homeactivity.HomeActivity;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.userbean.LoginBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.util.NetworkUtils;
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
        //取出记住密码的状态值
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

        //取出自动登录的状态值
        boolean v_ischeck = sharedPreferences.getBoolean("v_ischeck",false);
        if(v_ischeck){
            startActivity(new Intent(this,HomeActivity.class));
        }

        //勾选了自动登录 自动勾选记住密码
        mRlogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(mRlogin.isChecked()){
                    mRpass.setChecked(true);
                }
            }
        });

        //当记住密码的复选框没有勾选 并且取消自动登录的复选框
        mRpass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!mRpass.isChecked()){
                    mRpass.setChecked(false);
                }
            }
        });
    }

    private void toLogin() {
        //登录点击事件
        login_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取出输入框里面的值
                String number = mLnumber.getText().toString();
                String pass = mLpass.getText().toString();
                //使用md5进行加密
                String encrypt = EncryptUtil.encrypt(pass);
                //sp记住密码
                if(mRpass.isChecked()){
                    editor.putString("login_number",number);
                    editor.putString("login_pass",pass);

                    editor.putBoolean("ischeck",true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }

                //记住密码
                if(mRlogin.isChecked()){
                    editor.putBoolean("v_ischeck",true);
                    editor.commit();
                }
                if(!number.equals("")&&!encrypt.equals("")) {
                    if (NetworkUtils.hasNetwork(LoginActivity.this)) {
                        Map<String, String> map = new HashMap<>();
                        map.put("phone", number);
                        map.put("pwd", encrypt);
                        doNetPost(Apis.URL_POST_LOGIN, map, LoginBean.class);
                    } else {
                        ToastUtils.show(LoginActivity.this, "当前网络不可用，请检查网络");
                    }
                } else{
                    ToastUtils.show(LoginActivity.this, "输入的内容不能为空");
                }
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
                Log.i("TTTTT",user.getResult().getUserId()+"");
                Log.i("TTTTT",user.getResult().getSessionId());
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
