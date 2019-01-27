package com.bw.movie.fragment.filmfragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bw.movie.activity.filmactivity.DetailsActivity;
import com.bw.movie.adapter.filmadatper.detailsadapter.FilmHotContentAdapter;
import com.bw.movie.adapter.filmadatper.detailsadapter.FilmIngContentAdapter;
import com.bw.movie.adapter.filmadatper.detailsadapter.FilmJijContentAdapter;
import com.bw.movie.adapter.filmadatper.detailsadapter.RecyclerCoverAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.filmbean.FilmHotBean;
import com.bw.movie.bean.filmbean.FilmIngBean;
import com.bw.movie.bean.filmbean.FilmJijBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;

import recycler.coverflow.RecyclerCoverFlow;
/**
 * A simple {@link Fragment} subclass.
 *
 *  电影页面
 */
public class FilmFragment extends BaseFragment {

    RecyclerCoverFlow mContent;
    RecyclerCoverAdapter mCoverAdapter;
    RelativeLayout mRela;
    EditText mTsearch;
    RecyclerView mHotContents;
    RecyclerView mIngContents;
    RecyclerView mJijContents;
    FilmHotContentAdapter mFilmHotContentAdapter;
    FilmJijContentAdapter mFilmJijContentAdapter;
    FilmIngContentAdapter mFilmIngContentAdapter;
    //初始化数据
    @Override
    protected void initData() {
        //开始网路请求
        startRequest();
        //适配器
        initAdapter();
        //设置布局管理器你
        initManager();
        //弹出搜索框
        initPopup();
        //点击进行跳转到详情
        initDetails();
    }

    private void initDetails() {
        mFilmHotContentAdapter.setOnItemClick(new FilmHotContentAdapter.OnItemClick() {
            @Override
            public void success(String id) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        mFilmIngContentAdapter.setOnItemClick(new FilmIngContentAdapter.OnItemClick() {
            @Override
            public void success(String id) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        mFilmJijContentAdapter.setOnItemClick(new FilmJijContentAdapter.OnItemClick() {
            @Override
            public void success(String id) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        mCoverAdapter.setOnItemClick(new RecyclerCoverAdapter.OnItemClick() {
            @Override
            public void success(String id) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    private void startRequest() {
        doNetGet(Apis.URL_GET_ING,FilmIngBean.class);
        doNetGet(Apis.URL_GET_BANNER,FilmHotBean.class);
        doNetGet(Apis.URL_GET_JIJ,FilmJijBean.class);
    }

    private void initManager() {
        LinearLayoutManager linearLayoutManagerHot = new LinearLayoutManager(getActivity());
        linearLayoutManagerHot.setOrientation(OrientationHelper.HORIZONTAL);
        mHotContents.setLayoutManager(linearLayoutManagerHot);

        LinearLayoutManager linearLayoutManagerIng = new LinearLayoutManager(getActivity());
        linearLayoutManagerIng.setOrientation(OrientationHelper.HORIZONTAL);
        mIngContents.setLayoutManager(linearLayoutManagerIng);


        LinearLayoutManager linearLayoutManagerJij = new LinearLayoutManager(getActivity());
        linearLayoutManagerJij.setOrientation(OrientationHelper.HORIZONTAL);
        mJijContents.setLayoutManager(linearLayoutManagerJij);
    }

    private void initPopup() {
        mRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mRela.animate().xBy(-100).setDuration(300).start();
                    mTsearch.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initAdapter() {
        //画廊适配器
        mCoverAdapter = new RecyclerCoverAdapter(getActivity());
        mContent.setAdapter(mCoverAdapter);
        //热门电影
        mFilmHotContentAdapter = new FilmHotContentAdapter(getActivity());
        mHotContents.setAdapter(mFilmHotContentAdapter);

        //正在上映
        mFilmIngContentAdapter = new FilmIngContentAdapter(getActivity());
        mIngContents.setAdapter(mFilmIngContentAdapter);

        //即将上映
        mFilmJijContentAdapter = new FilmJijContentAdapter(getActivity());
        mJijContents.setAdapter(mFilmJijContentAdapter);
    }

    //初始化控件
    @Override
    protected void initView(View view) {

        mContent = view.findViewById(R.id.film_banner_icon);
        mRela = view.findViewById(R.id.mrelative);
        mTsearch = view.findViewById(R.id.film_edit_search);
        mHotContents = view.findViewById(R.id.flem_hot_contents);
        mIngContents = view.findViewById(R.id.flem_inf_contents);
        mJijContents = view.findViewById(R.id.flem_jij_contents);

    }

    //获取布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_film;
    }
    //请求成功
    @Override
    protected void netSuccess(Object data) {
        if(data instanceof FilmHotBean){
            FilmHotBean user = (FilmHotBean) data;
            if(user.getStatus().equals("0000")){
                mCoverAdapter.setMlist(user.getResult());
                mFilmHotContentAdapter.setMlist(user.getResult());
            }
        }
        if(data instanceof FilmIngBean){
            FilmIngBean user = (FilmIngBean) data;
            if(user.getStatus().equals("0000")){
                mFilmIngContentAdapter.setMlist(user.getResult());
            }
        }
        if(data instanceof FilmJijBean){
            FilmJijBean user = (FilmJijBean) data;
            if(user.getStatus().equals("0000")){
                mFilmJijContentAdapter.setMlist(user.getResult());
            }
        }
    }
    //请求失败
    @Override
    protected void netFail(Object data) {

    }
}
