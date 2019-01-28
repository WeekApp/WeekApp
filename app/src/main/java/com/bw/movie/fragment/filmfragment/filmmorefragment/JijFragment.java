package com.bw.movie.fragment.filmfragment.filmmorefragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.adapter.filmadatper.detailsadapter.filmmoreadapter.JijContentAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.filmbean.FilmHotBean;
import com.bw.movie.bean.filmbean.FilmJijBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JijFragment extends BaseFragment {

    @BindView(R.id.jij_fragemnt_cotntes)
    RecyclerView mIngContents;
    JijContentAdapter mJijContentAdapter;

    @Override
    protected void initData() {

        //开始请求数据
        doNetGet(Apis.URL_GET_JIJ,FilmJijBean.class);
        //设置适配器
        initAdapter();
        //设置蒲剧管理器
        initManager();
    }

    private void initManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mIngContents.setLayoutManager(linearLayoutManager);
    }

    private void initAdapter() {
        mJijContentAdapter = new JijContentAdapter(getActivity());
        mIngContents.setAdapter(mJijContentAdapter);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected int getLayout() {
        return R.layout.jij_fragment;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof FilmJijBean) {
            FilmJijBean user = (FilmJijBean) data;
            if(user.getStatus().equals("0000")){
                mJijContentAdapter.setMlist(user.getResult());
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
