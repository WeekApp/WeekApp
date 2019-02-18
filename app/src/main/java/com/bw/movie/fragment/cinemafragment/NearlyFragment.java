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
import com.bw.movie.bean.filmbean.details.buyingbean.ConcrenBean;
import com.bw.movie.bean.userbean.RegisterBean;
import com.bw.movie.mvp.persenter.IPersenter;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
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

        mNearlyAdapter.setGetData(new NearlyAdapter.GetData() {
            @Override
            public void onClick(int id, String logo, String name, String address) {
                Intent intent=new Intent(getContext(),CinemaDetailActivity.class);
                intent.putExtra("filmid",id+"");
                intent.putExtra("logo",logo);
                intent.putExtra("name",name);
                intent.putExtra("address",address);
                startActivity(intent);
            }
        });
        //去关注
        initConcren();
    }

    //请求数据
    @Override
    protected void initData() {
        doNetGet(Apis.URL_GET_NEARLY,RemmondBean.class);
    }

    private void initConcren() {
        mNearlyAdapter.setOnItemClick(new NearlyAdapter.OnItemClick() {
            @Override
            public void success(String id, boolean is) {
                if(is){
                    doNetGet(String.format(Apis.URL_GET_GUANZHUYINGYUAN,id),ConcrenBean.class);
                }else{
                    doNetGet(String.format(Apis.URL_GET_CANCLEGUANZHUYINGYUAN,id),ConcrenBean.class);
                }
                mNearlyAdapter.notifyDataSetChanged();
            }
        });
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

        if(data instanceof  ConcrenBean){
            ConcrenBean user = (ConcrenBean) data;
            if(user.getStatus().equals("0000")){
                ToastUtils.show(getActivity(),user.getMessage());
                doNetGet(Apis.URL_GET_NEARLY,RemmondBean.class);
            }else{
                ToastUtils.show(getActivity(),user.getMessage());
                doNetGet(Apis.URL_GET_NEARLY,RemmondBean.class);
            }
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
