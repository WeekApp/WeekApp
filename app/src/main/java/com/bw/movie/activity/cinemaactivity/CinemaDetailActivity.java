package com.bw.movie.activity.cinemaactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.adapter.cinemaadapter.CinemaBannerAdapter;
import com.bw.movie.adapter.cinemaadapter.CinemaScheduleAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.cinemabean.CinemaDetailBannerBean;
import com.bw.movie.bean.cinemabean.CinemaDetailScheduleBean;
import com.bw.movie.bean.filmbean.FilmHotBean;
import com.bw.movie.bean.filmbean.FilmIngBean;
import com.bw.movie.mvp.persenter.IPersenter;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

public class CinemaDetailActivity extends BaseActivity {

    @BindView(R.id.cinemadetail_simple_logo)
    SimpleDraweeView cinemadetailSimpleLogo;
    @BindView(R.id.cinemadetail_tv_name)
    TextView cinemadetailTvName;
    @BindView(R.id.cinemadetail_tv_address)
    TextView cinemadetailTvAddress;
    @BindView(R.id.cinemadetail_recy_banner)
    RecyclerCoverFlow cinemadetailRecyBanner;
    @BindView(R.id.cinemadetail_recy)
    RecyclerView cinemadetailRecy;
    @BindView(R.id.cinemadetail_iv_detail)
    ImageView cinemadetailIvDetail;
    private String mId;
    private CinemaBannerAdapter mCinemaBannerAdapter;
    private IPersenter mIPersenter;
    private CinemaScheduleAdapter mCinemaScheduleAdapter;

    @Override
    protected void initView() {


        ButterKnife.bind(this);

    }
    @Override
    protected void initData() {
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        String logo = intent.getStringExtra("logo");
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");

        cinemadetailSimpleLogo.setImageURI(logo);
        cinemadetailTvName.setText(name);
        cinemadetailTvAddress.setText(address);

        initBanner(mId);

    }
    //排期
    private void initRecy(String mid, int id) {
        doNetGet(Apis.URL_GET_findMovieScheduleList+"?cinemasId="+mid+"&movieId="+id,CinemaDetailScheduleBean.class);

        mCinemaScheduleAdapter = new CinemaScheduleAdapter(this);
        cinemadetailRecy.setAdapter(mCinemaScheduleAdapter);
        cinemadetailRecy.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    //轮播图
    private void initBanner(final String mid) {
        doNetGet(Apis.URL_GET_findMovieListByCinemaId+"?cinemaId="+mid,CinemaDetailBannerBean.class);

        mCinemaBannerAdapter = new CinemaBannerAdapter(this);
        cinemadetailRecyBanner.setAdapter(mCinemaBannerAdapter);
        cinemadetailRecyBanner.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {//滑动监听
            @Override
            public void onItemSelected(int position) {

                initRecy(mid,position);
            }
        });

    }


    @Override
    protected int getLayout() {
        return R.layout.activity_cinema_detail;
    }

    @Override
    protected void netSuccess(Object data) {
        if (data instanceof CinemaDetailBannerBean) {
            CinemaDetailBannerBean cinemaDetailBannerBean = (CinemaDetailBannerBean) data;
            mCinemaBannerAdapter.setData(cinemaDetailBannerBean.getResult());
        }else if (data instanceof CinemaDetailScheduleBean){
            CinemaDetailScheduleBean cinemaDetailScheduleBean= (CinemaDetailScheduleBean) data;
            mCinemaScheduleAdapter.setData(cinemaDetailScheduleBean.getResult());
        }
    }

    @Override
    protected void netFail(Object data) {

    }



}
