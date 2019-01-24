package com.example.movie.activity.useractivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.movie.bean.userbean.RegisterBean;
import com.example.movie.mvp.persenter.IPersenter;
import com.example.movie.mvp.util.Apis;
import com.example.movie.mvp.view.IView;
import com.example.movie.util.EncryptUtil;
import com.example.movie.util.ToastUtils;
import com.example.onlymycinema.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements IView {

    @BindView(R.id.register_edit_name)
    EditText mRname;
    @BindView(R.id.register_edit_date)
    EditText mRdate;
    @BindView(R.id.register_edit_email)
    EditText mRemail;
    @BindView(R.id.register_edit_number)
    EditText mRnumber;
    @BindView(R.id.register_edit_pass)
    EditText mRpass;
    @BindView(R.id.register_cb_nan)
    RadioButton mBoy;
    @BindView(R.id.register_cb_nv)
    RadioButton mGirls;
    @BindView(R.id.register_ok)
    Button mRegister;

    IPersenter mPersenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        //实现P层
        mPersenter = new IPersenter(this);
        //开始请求网络
        startRequest();
    }

    private void startRequest() {

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mRname.getText().toString();
                String date = mRdate.getText().toString();
                String emial = mRemail.getText().toString();
                String pass = mRpass.getText().toString();
                String number = mRnumber.getText().toString();
                //MD5进行加密
                String mpass = EncryptUtil.encrypt(pass);

                ///asfdghjuhkl
                //SADFBGNBM
                Map<String, String> map = new HashMap<>();
                        map.put("nickName", name);
                        map.put("phone", number);
                        map.put("pwd", mpass);
                        map.put("birthday", date);
                        map.put("email", emial);
                        map.put("sex",2+"");
                        map.put("pwd2",mpass);
                      mPersenter.requestPostBack(Apis.URL_POST_REGISTER,map,RegisterBean.class);
            }
        });
    }

    @Override
    public void showRequest(Object data) {
        if(data instanceof RegisterBean){
            RegisterBean user = (RegisterBean) data;
            ToastUtils.show(this,user.getMessage());
            if(user.getStatus().equals("0000")){
                ToastUtils.show(this,user.getMessage());
                startActivity(new Intent(this,LoginActivity.class));
            }else{
                ToastUtils.show(this,user.getMessage());
            }
        }
    }

    @Override
    public void showError(Object data) {
        ToastUtils.show(this,data.toString());
    }
}
