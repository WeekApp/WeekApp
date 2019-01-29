package com.bw.movie.fragment.cinemafragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.activity.cinemaactivity.CinemaDetailActivity;
import com.bw.movie.adapter.cinemaadapter.NearlyAdapter;
import com.bw.movie.adapter.cinemaadapter.RecommendAdapter;
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

        //去关注
        initConcren();
    }

    //请求
    @Override
    protected void initData() {

        mIPersenter.requestGetBack(Apis.URL_GET_REMMOND,RemmondBean.class);

    }

    private void initConcren() {
        mRecommendAdapter.setOnItemClick(new RecommendAdapter.OnItemClick() {
            @Override
            public void success(String id, boolean is) {
                if(is){
                    doNetGet(String.format(Apis.URL_GET_GUANZHUYINGYUANN,id),ConcrenBean.class);
                }else{
                    doNetGet(String.format(Apis.URL_GET_CANCLEGUANZHUYINGYUANN,id),ConcrenBean.class);
                }
                mRecommendAdapter.notifyDataSetChanged();
            }
        });
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
        }else if (data instanceof RegisterBean){
            RegisterBean registerBean= (RegisterBean) data;
            Toast.makeText(getContext(), registerBean.getMessage()+"", Toast.LENGTH_SHORT).show();
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
