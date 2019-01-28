package com.bw.movie.fragment.filmfragment.filmmorefragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.adapter.filmadatper.detailsadapter.filmmoreadapter.HotContentAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.filmbean.FilmHotBean;
import com.bw.movie.bean.filmbean.details.detailsbean.ConcrenBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
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
        //关注
        initGuanzhu();
    }

    private void initGuanzhu() {

      mHotContentAdapter.setOnItemClick(new HotContentAdapter.OnItemClick() {
          @Override
          public void succuess(String id, boolean isMovie) {
              if(isMovie){
                  doNetGet(String.format(Apis.URL_GET_GUANZHU, id), ConcrenBean.class);
              }else{
                  doNetGet(String.format(Apis.URL_GET_QUXIAOGUANZHU, id), ConcrenBean.class);
              }
              mHotContentAdapter.notifyDataSetChanged();
          }
      });
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
