package com.bw.movie.fragment.cinemafragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bw.movie.activity.cinemaactivity.CinemaDetailActivity;
import com.bw.movie.adapter.cinemaadapter.RecommendAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.RemmondBean;
import com.bw.movie.mvp.persenter.IPersenter;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 *
 * 推荐影院
 */
public class RecommendFragment extends BaseFragment {

    @BindView(R.id.recommendfragment_xrecy)
    RecyclerView recommendfragmentXrecy;
    Unbinder unbinder;
    private RecommendAdapter mRecommendAdapter;
    private IPersenter mIPersenter;

    //初始化控件
    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        mIPersenter = new IPersenter(this);

        mRecommendAdapter = new RecommendAdapter(getContext());
        recommendfragmentXrecy.setAdapter(mRecommendAdapter);
        recommendfragmentXrecy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        mRecommendAdapter.setGetData(new RecommendAdapter.GetData() {
            @Override
            public void onClick(int id, String logo, String name, String address) {
                Intent intent=new Intent(getContext(),CinemaDetailActivity.class);
                intent.putExtra("id",id+"");
                intent.putExtra("logo",logo);
                intent.putExtra("name",name);
                intent.putExtra("address",address);
                startActivity(intent);
            }


        });
    }

    //请求
    @Override
    protected void initData() {

        mIPersenter.requestGetBack(Apis.URL_GET_REMMOND,RemmondBean.class);
    }

    //布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_recommend;
    }

    //成功的方法
    @Override
    protected void netSuccess(Object data) {
        if (data instanceof RemmondBean){
            RemmondBean remmondBean= (RemmondBean) data;
            mRecommendAdapter.setData(remmondBean.getResult());
        }
    }

    //失败的方法
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
