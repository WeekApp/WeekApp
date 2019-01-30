package com.bw.movie.fragment.cinemafragment.filmpopfragent;


import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.activity.cinemaactivity.CinemaDetailActivity;
import com.bw.movie.adapter.cinemaadapter.PopDetailsAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.PopCommentBean;
import com.bw.movie.bean.cinemabean.PopDetailsBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
public class PopDetailsFragment extends BaseFragment {

    @BindView(R.id.details_pop_contents)
    RecyclerView mCommentContents;
    PopDetailsAdapter mPoDetailsAdapter;
    private String mid;
    @Override
    protected void initData() {
        //开始请求网路
        startRequest();
        //适配器
        initAdapter();
        //管理器
        initManager();
        //评论点在
        initZan();
    }

    private void initZan() {
        mPoDetailsAdapter.setOnItemClick(new PopDetailsAdapter.OnItemClick() {
            @Override
            public void success(String id, boolean isGreat) {

                Map<String,String> map = new HashMap<>();
                map.put("commentId",id);
                if(isGreat){
                    doNetPost(Apis.URL_POST_ZAN,map,PopCommentBean.class);
                }else{
                    doNetPost(Apis.URL_POST_ZAN,map,PopCommentBean.class);
                }
                mPoDetailsAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initManager() {
        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getActivity());
        mCommentContents.setLayoutManager(linearLayoutManager);
        //添加系统分割线
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(),OrientationHelper.VERTICAL);
        mCommentContents.addItemDecoration(decoration);
    }

    private void initAdapter() {
        mPoDetailsAdapter = new PopDetailsAdapter(getActivity());
        mCommentContents.setAdapter(mPoDetailsAdapter);
    }

    private void startRequest() {
        mid = ((CinemaDetailActivity) getActivity()).getMid();
        doNetGet(String.format(Apis.URL_POST_FILMCOMMENT, mid),PopDetailsBean.class);
    }

    @Override
    protected void initView(View view) {
        //使用ButterKnife绑定控件
        ButterKnife.bind(this,view);
    }

    @Override
    protected int getLayout() {
        return R.layout.popcomment_fragment;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof PopDetailsBean){
            PopDetailsBean user = (PopDetailsBean) data;
            if(user.getStatus().equals("0000")){
                mPoDetailsAdapter.setMlist(user.getResult());
            }
        }

        if(data instanceof PopCommentBean){
            PopCommentBean user = (PopCommentBean) data;
            if(user.getStatus().equals("0000")){
                ToastUtils.show(getActivity(),user.getMessage());
                doNetGet(String.format(Apis.URL_POST_FILMCOMMENT, mid),PopDetailsBean.class);
            }else{
                ToastUtils.show(getActivity(),user.getMessage());
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
