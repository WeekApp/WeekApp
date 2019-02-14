package com.bw.movie.fragment.filmfragment.filmmorefragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.adapter.filmadatper.detailsadapter.filmmoreadapter.HotContentAdapter;
import com.bw.movie.adapter.filmadatper.detailsadapter.filmmoreadapter.IngContentAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.filmbean.FilmHotBean;
import com.bw.movie.bean.filmbean.FilmIngBean;
import com.bw.movie.bean.filmbean.details.detailsbean.ConcrenBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngFragment extends BaseFragment {

    @BindView(R.id.ing_fragemnt_cotntes)
    RecyclerView mIngContents;

    IngContentAdapter mIngContentAdapter;

    @Override
    protected void initData() {
        //开始请求数据
        doNetGet(Apis.URL_GET_ING,FilmIngBean.class);
        //设置适配器
        initAdapter();
        //设置蒲剧管理器
        initManager();
        //qu滚去
        initConcren();
    }

    private void initConcren() {

        mIngContentAdapter.setOnItemClick(new IngContentAdapter.OnItemClick() {
            @Override
            public void succuess(String id, boolean isMovie) {
                if(isMovie){
                    doNetGet(String.format(Apis.URL_GET_GUANZHU, id), ConcrenBean.class);
                }else{
                    doNetGet(String.format(Apis.URL_GET_QUXIAOGUANZHU, id), ConcrenBean.class);
                }
                mIngContentAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initManager() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mIngContents.setLayoutManager(linearLayoutManager);

    }

    private void initAdapter() {

        mIngContentAdapter = new IngContentAdapter(getActivity());
        mIngContents.setAdapter(mIngContentAdapter);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected int getLayout() {
        return R.layout.ing_fragment;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof FilmIngBean) {
            FilmIngBean user = (FilmIngBean) data;
            if(user.getStatus().equals("0000")){
                mIngContentAdapter.setMlist(user.getResult());
            }
        }

        if(data instanceof ConcrenBean) {
            ConcrenBean user = (ConcrenBean) data;
            if(user.getStatus().equals("0000")){
                ToastUtils.show(getActivity(),user.getMessage());
                doNetGet(Apis.URL_GET_BANNER,FilmHotBean.class);
            }else{
                ToastUtils.show(getActivity(),user.getMessage());
                doNetGet(Apis.URL_GET_BANNER,FilmHotBean.class);
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
