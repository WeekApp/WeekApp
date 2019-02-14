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
    Unbinder unbinder;
    AttentionFilmAdapter mAttentionFilmAdapter;

    //初始化数据
    @Override
    protected void initData() {
        doNetGet(Apis.URL_GET_QUREYMESSAGE,MessageBean.class);

        mAttentionFilmAdapter = new AttentionFilmAdapter(getContext());
        attentionfilmRecy.setAdapter(mAttentionFilmAdapter);
        attentionfilmRecy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    //初始化控件
    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }
    //返回布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_attention_film;
    }
    //请求成功
    @Override
    protected void netSuccess(Object data) {
            MessageBean messageBean= (MessageBean) data;

            mAttentionFilmAdapter.setData(messageBean.getResult().getMovieList());
    }
    //请求失败
    @Override
    protected void netFail(Object data) {

    }
    //销毁布局
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
