package com.bw.movie.fragment.filmfragment;


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
 *  电影页面
 */
public class FilmFragment extends BaseFragment {

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
        return R.layout.fragment_film;
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
