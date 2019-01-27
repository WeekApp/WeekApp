package com.bw.movie.fragment.cinemafragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.adapter.cinemaadapter.NearlyAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.RemmondBean;
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
    NearlyAdapter mNearlyAdapter;

    //初始化布局
    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        mNearlyAdapter = new NearlyAdapter(getContext());
        NearlyFragmentRecy.setAdapter(mNearlyAdapter);
        NearlyFragmentRecy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
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
