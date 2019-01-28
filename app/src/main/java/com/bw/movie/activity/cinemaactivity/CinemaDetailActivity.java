package com.bw.movie.activity.cinemaactivity;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bw.movie.adapter.cinemaadapter.CinemaBannerAdapter;
import com.bw.movie.adapter.cinemaadapter.CinemaDerailPoPuPagerAdapter;
import com.bw.movie.adapter.cinemaadapter.CinemaScheduleAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.cinemabean.CinemaDetailBannerBean;
import com.bw.movie.bean.cinemabean.CinemaDetailScheduleBean;
import com.bw.movie.fragment.cinemafragment.CinemaDetailPopuCommentFragment;
import com.bw.movie.fragment.cinemafragment.CinemaDetailPopuDetailFragment;
import com.bw.movie.fragment.filmfragment.FilmFragment;
import com.bw.movie.mvp.persenter.IPersenter;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

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

    private CinemaScheduleAdapter mCinemaScheduleAdapter;
    private TabLayout mCinemadetail_popu_tab;
    private View mView;
    private ViewPager mCinemadetail_popu_viewpager;
    private ImageView mCinemadetail_popu_down;
    private List<Fragment> mFragmentList;
    private PopupWindow mPopupWindow;

    @Override
    protected void initView() {


        ButterKnife.bind(this);
        initPoPu();
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

    private void initPoPu() {
        mView = View.inflate(CinemaDetailActivity.this, R.layout.cinemadetail_popu_detai, null);
        mCinemadetail_popu_tab = mView.findViewById(R.id.cinemadetail_popu_tab);
        mCinemadetail_popu_viewpager = mView.findViewById(R.id.cinemadetail_popu_viewpager);
        mCinemadetail_popu_down = mView.findViewById(R.id.cinemadetail_popu_down);

        mFragmentList = new ArrayList<>();

        String[] mTabNames = new String[]{"详情", "评论"};
        TabLayout.Tab tab1 = mCinemadetail_popu_tab.newTab()
                //设置tab项显示的文字
                .setText("详情");
        TabLayout.Tab tab2 = mCinemadetail_popu_tab.newTab().setText("评论");
        mCinemadetail_popu_tab.addTab(tab1);
        mCinemadetail_popu_tab.addTab(tab2);
//
        Fragment fragment = new CinemaDetailPopuCommentFragment();
        mFragmentList.add(new FilmFragment());
//        mFragmentList.add(new CinemaDetailPopuDetailFragment());
        CinemaDerailPoPuPagerAdapter cinemaDerailPoPuPagerAdapter = new CinemaDerailPoPuPagerAdapter(getSupportFragmentManager(), mFragmentList, mTabNames);
        mCinemadetail_popu_viewpager.setAdapter(cinemaDerailPoPuPagerAdapter);
        mCinemadetail_popu_tab.setupWithViewPager(mCinemadetail_popu_viewpager);


        mPopupWindow = new PopupWindow(mView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置焦点
        mPopupWindow.setFocusable(true);
        //设置是否可以触摸
        mPopupWindow.setTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        mCinemadetail_popu_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

        cinemadetailIvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                // mPopupWindow.update();
                mPopupWindow.showAsDropDown(v, Gravity.BOTTOM, 0, 0);
            }
        });
    }


    //排期
    private void initRecy(String mid, int id) {
        doNetGet(Apis.URL_GET_findMovieScheduleList + "?cinemasId=" + mid + "&movieId=" + id, CinemaDetailScheduleBean.class);

        mCinemaScheduleAdapter = new CinemaScheduleAdapter(this);
        cinemadetailRecy.setAdapter(mCinemaScheduleAdapter);
        cinemadetailRecy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    //轮播图
    private void initBanner(final String mid) {
        doNetGet(Apis.URL_GET_findMovieListByCinemaId + "?cinemaId=" + mid, CinemaDetailBannerBean.class);

        mCinemaBannerAdapter = new CinemaBannerAdapter(this);
        cinemadetailRecyBanner.setAdapter(mCinemaBannerAdapter);
        cinemadetailRecyBanner.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {//滑动监听
            @Override
            public void onItemSelected(int position) {

                initRecy(mid, position);
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
        } else if (data instanceof CinemaDetailScheduleBean) {
            CinemaDetailScheduleBean cinemaDetailScheduleBean = (CinemaDetailScheduleBean) data;
            mCinemaScheduleAdapter.setData(cinemaDetailScheduleBean.getResult());
        }
    }

    @Override
    protected void netFail(Object data) {

    }


}
