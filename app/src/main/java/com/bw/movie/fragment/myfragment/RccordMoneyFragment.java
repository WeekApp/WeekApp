package com.bw.movie.fragment.myfragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.activity.filmactivity.SelectionActivity;
import com.bw.movie.adapter.myadapter.RccordMoneyAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.CommonBean;
import com.bw.movie.bean.filmbean.details.buyingbean.PaySuccessBean;
import com.bw.movie.bean.mybean.RccordBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
import com.bw.movie.wxapi.WXPayEntryActivity;
import com.bw.onlymycinema.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

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
    RccordMoneyAdapter mRccordMoneyAdapter;

    @Override
    protected void initData() {
        //kaishi wangluo
        StartRequest();
        //设置适配器
        initAdapter();
        //社会布局管理器
        initManager();
        //去付款
        initGoPay();



    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void success(CommonBean commonBean){
        ToastUtils.show(getActivity(),commonBean.getMessage()+"小飞");
        StartRequest();
    }

    private void initGoPay() {
        mRccordMoneyAdapter.setOnImtemClick(new RccordMoneyAdapter.OnImtemClick() {
            @Override
            public void success(String order) {
                Map<String,String> map =new HashMap<>();
                map.put("payType",1+"");
                map.put("orderId",order);
                doNetPost(Apis._URL_PAG_MONEY,map,PaySuccessBean.class);
            }
        });
    }

    private void initManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rccordmoneyRecy.setLayoutManager(linearLayoutManager);
    }

    private void initAdapter() {
        mRccordMoneyAdapter = new RccordMoneyAdapter(getContext());
        rccordmoneyRecy.setAdapter(mRccordMoneyAdapter);
    }

    private void StartRequest() {
        doNetGet(Apis.URL_GET_QUERYMONEY + "?page=1&count=1000&status=1", RccordBean.class);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
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

        if(data instanceof PaySuccessBean){
            PaySuccessBean user = (PaySuccessBean) data;
            if(user.getStatus().equals("0000")){
                //TODO:
                Intent intent = new Intent(getActivity(),WXPayEntryActivity.class);
                intent.putExtra("appId",user.getAppId());
                intent.putExtra("nonceStr",user.getNonceStr());
                intent.putExtra("partnerId",user.getPartnerId());
                intent.putExtra("prepayId",user.getPrepayId());
                intent.putExtra("sign",user.getSign());
                intent.putExtra("timeStamp",user.getTimeStamp());
                intent.putExtra("packageValue",user.getPackageValue());
                startActivity(intent);
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
    }
}
