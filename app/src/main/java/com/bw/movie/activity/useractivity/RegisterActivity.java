package com.bw.movie.activity.useractivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.userbean.RegisterBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RegisterActivity extends BaseActivity {

    @BindView(R.id.register_edit_name)
    EditText mRname;
    @BindView(R.id.register_edit_date)
    EditText mRdate;
    @BindView(R.id.register_edit_email)
    EditText mRemail;
    @BindView(R.id.register_edit_sex)
    EditText mRsex;
    @BindView(R.id.register_edit_number)
    EditText mRnumber;
    @BindView(R.id.register_edit_pass)
    EditText mRpass;
    @BindView(R.id.register_ok)
    Button mRegister;

    int sexboy;

    @Override
    protected void initData() {
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mRname.getText().toString();
                String date = mRdate.getText().toString();
                String sex = mRsex.getText().toString();
                String emial = mRemail.getText().toString();
                String pass = mRpass.getText().toString();
                String number = mRnumber.getText().toString();
                //MD5进行加密
                String mpass = EncryptUtil.encrypt(pass);

                if(sex.equals("男")){
                    sexboy = 1;
                }else if(sex.equals("女")){
                    sexboy = 2;
                }
                Map<String, String> map = new HashMap<>();
                map.put("nickName", name);
                map.put("phone", number);
                map.put("pwd", mpass);
                map.put("birthday", date);
                map.put("email", emial);
                map.put("sex",sexboy+"");
                map.put("pwd2",mpass);
                doNetPost(Apis.URL_POST_REGISTER,map,RegisterBean.class);
            }
        });
    }

    //加载id
    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void netSuccess(Object data) {
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
    protected void netFail(Object data) {

    }
}
