package com.bw.movie.fragment.myfragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bw.movie.adapter.myadapter.RccordFinishAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.mybean.RccordBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RccordFinishFragment extends BaseFragment {

    @BindView(R.id.rccordfinish_recy)
    RecyclerView rccordfinishRecy;
    RccordFinishAdapter mRccordFinishAdapter;

    @Override
    protected void initData() {
        //开始请求网络数据
        startRequest();
        //设置适配器
        initAdapter();
        //设置布局uanliqi
        initManager();
    }

    private void initManager() {
        rccordfinishRecy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void initAdapter() {
        mRccordFinishAdapter = new RccordFinishAdapter(getContext());
        rccordfinishRecy.setAdapter(mRccordFinishAdapter);
    }

    private void startRequest() {
        doNetGet(Apis.URL_GET_QUERYMONEY + "?page=1&count=10000&status=2", RccordBean.class);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_rccord_finish;
    }

    @Override
    protected void netSuccess(Object data) {
        if (data instanceof RccordBean){
            RccordBean rccordBean= (RccordBean) data;
            if(rccordBean.getStatus().equals("0000")) {
                mRccordFinishAdapter.setData(rccordBean.getResult());
                Log.i("TTTTTTW",rccordBean.getResult()+"");
            }else{
                Log.i("TTTTTTA",rccordBean.getResult()+"");
                ToastUtils.show(getActivity(),rccordBean.getMessage());
            }
        }
    }

    @Override
    protected void netFail(Object data) {
        Log.i("TTTTTTD",data.toString());
    }
}
