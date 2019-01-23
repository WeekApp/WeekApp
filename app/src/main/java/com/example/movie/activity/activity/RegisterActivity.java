package com.example.movie.activity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import com.example.movie.mvp.view.IView;
import com.example.onlymycinema.R;
import butterknife.BindView;

public class RegisterActivity extends AppCompatActivity implements IView {

    @BindView(R.id.register_edit_name)
    EditText mRname;
    @BindView(R.id.register_edit_date)
    EditText mRdate;
    @BindView(R.id.register_edit_sex)
    EditText mRex;
    @BindView(R.id.register_edit_email)
    EditText mRemail;
    @BindView(R.id.register_edit_number)
    EditText mRnumber;
    @BindView(R.id.register_edit_pass)
    EditText mRpass;
    @BindView(R.id.register_ok)
    Button mRegister;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }


    @Override
    public void showRequest(Object data) {

    }

    @Override
    public void showError(Object data) {

    }
}
