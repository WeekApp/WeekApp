package com.bw.movie.fragment.myfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bw.movie.adapter.myadapter.AttentionFilmAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.ConrenFilmBean;
import com.bw.movie.bean.mybean.MessageBean;
import com.bw.movie.bean.mybean.MyMessageBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * <p>
 * 我的关注电影
 */
public class AttentionFilmFragment extends BaseFragment {

    @BindView(R.id.attentionfilm_recy)
    RecyclerView attentionfilmRecy;
    AttentionFilmAdapter mAttentionFilmAdapter;

    //初始化数据
    @Override
    protected void initData() {

        startRequest();

        initAdapter();

        initManager();
    }

    private void initManager() {
        attentionfilmRecy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void initAdapter() {
        mAttentionFilmAdapter = new AttentionFilmAdapter(getContext());
        attentionfilmRecy.setAdapter(mAttentionFilmAdapter);
    }

    private void startRequest() {
        doNetGet(Apis.URL_GET_WFMEF,ConrenFilmBean.class);
    }

    //初始化控件
    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }
    //返回布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_attention_film;
    }
    //请求成功
    @Override
    protected void netSuccess(Object data) {
            ConrenFilmBean messageBean= (ConrenFilmBean) data;
            if(messageBean.getStatus().equals("0000")) {
                mAttentionFilmAdapter.setData(messageBean.getResult());
            }
    }
    //请求失败
    @Override
    protected void netFail(Object data) {

    }
}
