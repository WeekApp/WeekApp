package com.bw.movie.activity.myactivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.adapter.myadapter.RemindAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.mybean.RemindBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RemindActivity extends BaseActivity {
    @BindView(R.id.remindfragment_message)
    TextView remindfragmentMessage;
    @BindView(R.id.remindfragment_recy)
    RecyclerView remindfragmentRecy;
    @BindView(R.id.remindfragment_back)
    ImageView remindfragmentBack;
    Unbinder unbinder;

    private RemindAdapter mRemindAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_remind;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        doNetGet(Apis.URL_GET_REMIND, RemindBean.class);

        mRemindAdapter = new RemindAdapter(this);
        remindfragmentRecy.setAdapter(mRemindAdapter);
        remindfragmentRecy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        remindfragmentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void netSuccess(Object data) {
        if (data instanceof RemindBean) {
            RemindBean remindBean = (RemindBean) data;

            mRemindAdapter.setData(remindBean.getResult());
        }
    }

    @Override
    protected void netFail(Object data) {

    }


}
