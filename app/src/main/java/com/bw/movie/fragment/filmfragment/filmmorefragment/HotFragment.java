package com.bw.movie.fragment.filmfragment.filmmorefragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.adapter.filmadatper.detailsadapter.filmmoreadapter.HotContentAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.filmbean.FilmHotBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotFragment extends BaseFragment {

    @BindView(R.id.hot_fragemnt_cotntes)
    RecyclerView mHotContents;
    HotContentAdapter mHotContentAdapter;

    @Override
    protected void initData() {
        //开始请求数据
        doNetGet(Apis.URL_GET_BANNER,FilmHotBean.class);
        //设置适配器
        initAdapter();
        //设置蒲剧管理器
        initManager();
    }

    private void initManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mHotContents.setLayoutManager(linearLayoutManager);
    }

    private void initAdapter() {
        mHotContentAdapter = new HotContentAdapter(getActivity());
        mHotContents.setAdapter(mHotContentAdapter);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected int getLayout() {
        //加载布局
        return R.layout.hot_fragemnt;
    }

    @Override
    protected void netSuccess(Object data) {
         if(data instanceof FilmHotBean) {
             FilmHotBean user = (FilmHotBean) data;
             if(user.getStatus().equals("0000")){
                 mHotContentAdapter.setMlist(user.getResult());
             }
         }
    }

    @Override
    protected void netFail(Object data) {

    }
}
