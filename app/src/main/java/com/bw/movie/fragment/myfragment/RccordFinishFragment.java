package com.bw.movie.fragment.myfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.adapter.myadapter.RccordFinishAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.mybean.RccordBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RccordFinishFragment extends BaseFragment {

    @BindView(R.id.rccordfinish_recy)
    RecyclerView rccordfinishRecy;
    Unbinder unbinder;
    private RccordFinishAdapter mRccordFinishAdapter;

    @Override
    protected void initData() {
        doNetGet(Apis.URL_GET_QUERYMONEY + "?page=1&count=5&status=2", RccordBean.class);

        mRccordFinishAdapter = new RccordFinishAdapter(getContext());
        rccordfinishRecy.setAdapter(mRccordFinishAdapter);
        rccordfinishRecy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_rccord_finish;
    }

    @Override
    protected void netSuccess(Object data) {
        if (data instanceof RccordBean){
            RccordBean rccordBean= (RccordBean) data;

            mRccordFinishAdapter.setData(rccordBean.getResult());
        }
    }

    @Override
    protected void netFail(Object data) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
