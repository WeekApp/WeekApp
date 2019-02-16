package com.bw.movie.fragment.myfragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.adapter.myadapter.RccordMoneyAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.mybean.RccordBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * <p>
 * <p>
 * 待付款
 */
public class RccordMoneyFragment extends BaseFragment {
    @BindView(R.id.rccordmoney_recy)
    RecyclerView rccordmoneyRecy;
    Unbinder unbinder;
    private RccordMoneyAdapter mRccordMoneyAdapter;

    @Override
    protected void initData() {
        //请求网络
        initNet();
        //设置适配器
        initAdapter();

    }

    private void initAdapter() {
        mRccordMoneyAdapter = new RccordMoneyAdapter(getContext());
        rccordmoneyRecy.setAdapter(mRccordMoneyAdapter);
        rccordmoneyRecy.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }

    private void initNet() {
        doNetGet(Apis.URL_GET_QUERYMONEY+"?page=1&count=5&status=1" , RccordBean.class);

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_rccord_money;
    }

    @Override
    protected void netSuccess(Object data) {
        if (data instanceof RccordBean){
            RccordBean rccordBean= (RccordBean) data;

            mRccordMoneyAdapter.setData(rccordBean.getResult());
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
