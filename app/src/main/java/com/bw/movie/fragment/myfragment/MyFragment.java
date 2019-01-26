package com.bw.movie.fragment.myfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.base.BaseFragment;
import com.bw.onlymycinema.R;

/**
 * A simple {@link Fragment} subclass.
 *
 *  我的页面
 */
public class MyFragment extends BaseFragment {


    //初始化数据
    @Override
    protected void initData() {

    }
    //初始化控件
    @Override
    protected void initView() {

    }
    //获取布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }
    //请求成功
    @Override
    protected void netSuccess(Object data) {

    }
    //请求失败
    @Override
    protected void netFail(Object data) {

    }

}
