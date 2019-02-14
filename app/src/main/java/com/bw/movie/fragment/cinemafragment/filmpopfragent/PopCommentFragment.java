package com.bw.movie.fragment.cinemafragment.filmpopfragent;


import android.view.View;
import android.widget.TextView;

import com.bw.movie.activity.cinemaactivity.CinemaDetailActivity;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.FlimDetailsBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PopCommentFragment extends BaseFragment {

    @BindView(R.id.pop_com_name)
    TextView mName;
    @BindView(R.id.pop_com_phone)
    TextView mPhone;
    @BindView(R.id.pop_com_ditie)
    TextView mluxian;

    @Override
    protected void initData() {
        //开始请求网络数据
        startRequest();
    }

    private void startRequest() {
        String mid = ((CinemaDetailActivity) getActivity()).getMid();
        doNetGet(String.format(Apis.URL_GET_MINGZI,mid),FlimDetailsBean.class);
    }

    @Override
    protected void initView(View view) {
        //使用ButterKnife绑定控件
        ButterKnife.bind(this,view);
    }

    @Override
    protected int getLayout() {
        return R.layout.pop_conment_fragment;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof FlimDetailsBean){
            FlimDetailsBean user = (FlimDetailsBean) data;
            if(user.getStatus().equals("0000")){
                mName.setText(user.getResult().getAddress());
                mPhone.setText(user.getResult().getPhone());
                mluxian.setText(user.getResult().getVehicleRoute());
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
