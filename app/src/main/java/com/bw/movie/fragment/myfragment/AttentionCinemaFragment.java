package com.bw.movie.fragment.myfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.adapter.myadapter.AttentionCinemaAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.ConrenYingBean;
import com.bw.movie.bean.mybean.MessageBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class AttentionCinemaFragment extends BaseFragment {

    @BindView(R.id.attentioncinema_recy)
    RecyclerView attentioncinemaRecy;
    private AttentionCinemaAdapter mAttentionCinemaAdapter;

    //初始化数据
    @Override
    protected void initData() {


        startRequest();

        initAdapter();

        initManager();

    }

    private void initManager() {
        attentioncinemaRecy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

    }

    private void initAdapter() {
        mAttentionCinemaAdapter = new AttentionCinemaAdapter(getContext());
        attentioncinemaRecy.setAdapter(mAttentionCinemaAdapter);
    }

    private void startRequest() {
        doNetGet(Apis.URL_GET_QUREYMESSAGE,ConrenYingBean.class);
    }

    //初始化控件
    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    //返回布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_attention_cinema;
    }

    //请求成功
    @Override
    protected void netSuccess(Object data) {
        ConrenYingBean messageBean= (ConrenYingBean) data;

        mAttentionCinemaAdapter.setData(messageBean.getResult());
    }

    //请求失败
    @Override
    protected void netFail(Object data) {

    }
}
