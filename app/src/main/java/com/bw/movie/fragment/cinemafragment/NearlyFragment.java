package com.bw.movie.fragment.cinemafragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.activity.cinemaactivity.CinemaDetailActivity;
import com.bw.movie.adapter.cinemaadapter.NearlyAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.RemmondBean;
import com.bw.movie.bean.userbean.RegisterBean;
import com.bw.movie.mvp.persenter.IPersenter;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * <p>
 * 附近影院
 */
public class NearlyFragment extends BaseFragment {

    @BindView(R.id.NearlyFragment_recy)
    RecyclerView NearlyFragmentRecy;
    Unbinder unbinder;
    private NearlyAdapter mNearlyAdapter;

    //初始化布局
    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        mNearlyAdapter = new NearlyAdapter(getContext());
        NearlyFragmentRecy.setAdapter(mNearlyAdapter);
        NearlyFragmentRecy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        mNearlyAdapter.setGetData(new NearlyAdapter.GetData() {
            @Override
            public void onClick(int id, String logo, String name, String address) {
                Intent intent=new Intent(getContext(),CinemaDetailActivity.class);
                intent.putExtra("id",id+"");
                intent.putExtra("logo",logo);
                intent.putExtra("name",name);
                intent.putExtra("address",address);
                startActivity(intent);
            }

            @Override
            public void onColletion(int id, int followCinema) {
                //关注
                initCollection(id,followCinema);
            }

            @Override
            public void onColletioned(int id) {
                //取消关注
                initCollectioned(id);

            }
        });
    }
    //取消关注
    private void initCollectioned(int id) {
        doNetGet(Apis.URL_GET_CANCLEGUANZHUYINGYUAN+"?cinemaId="+id,RegisterBean.class);

    }

    //关注
    private void initCollection(int id,int fo) {
        doNetGet(Apis.URL_GET_GUANZHUYINGYUAN+"?cinemaId="+id,RegisterBean.class);

    }
    //请求数据
    @Override
    protected void initData() {
        doNetGet(Apis.URL_GET_NEARLY,RemmondBean.class);
    }

    //返回布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_nearly;
    }

    //请求成功
    @Override
    protected void netSuccess(Object data) {
        if (data instanceof RemmondBean){
            RemmondBean remmondBean= (RemmondBean) data;

            mNearlyAdapter.setData(remmondBean.getResult());
        }
    }

    //请求失败
    @Override
    protected void netFail(Object data) {
        Log.i("TTTERROR",data.toString());
    }


    //销毁布局
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
