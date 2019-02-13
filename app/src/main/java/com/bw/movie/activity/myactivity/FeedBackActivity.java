package com.bw.movie.activity.myactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.userbean.RegisterBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.feed)
    TextView feed;
    @BindView(R.id.feedback_title)
    EditText feedbackTitle;
    @BindView(R.id.feedback_commit)
    Button feedbackCommit;
    @BindView(R.id.rela)
    RelativeLayout rela;
    @BindView(R.id.feedback_back)
    ImageView feedbackback;


    @Override
    protected void initData() {
        feedbackback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void netSuccess(Object data) {
        if (data instanceof RegisterBean){
            RegisterBean registerBean= (RegisterBean) data;
            ToastUtils.show(this,registerBean.getMessage());

            if (registerBean.getStatus().equals("0000")){
                rela.setVisibility(View.VISIBLE);
                feed.setVisibility(View.GONE);
                feedbackTitle.setVisibility(View.GONE);
                feedbackCommit.setVisibility(View.GONE);
            }

        }
    }

    @Override
    protected void netFail(Object data) {

    }

    @OnClick(R.id.feedback_commit)
    public void onViewClicked() {
        String trim = feedbackTitle.getText().toString().trim();
        Map<String,String> map=new HashMap<>();
        map.put("content",trim);
        doNetPost(Apis.URL_GET_FEEDBACK,map,RegisterBean.class);
    }
}
