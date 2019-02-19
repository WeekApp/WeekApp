package com.bw.movie.activity.cinemaactivity;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bw.movie.activity.filmactivity.FilmBuyingListActivity;
import com.bw.movie.activity.filmactivity.SelectionActivity;
import com.bw.movie.adapter.cinemaadapter.CinemaBannerAdapter;
import com.bw.movie.adapter.cinemaadapter.CinemaScheduleAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.cinemabean.CinemaDetailBannerBean;
import com.bw.movie.bean.cinemabean.CinemaDetailScheduleBean;
import com.bw.movie.bean.cinemabean.CinemaIdBean;
import com.bw.movie.fragment.cinemafragment.filmpopfragent.PopCommentFragment;
import com.bw.movie.fragment.cinemafragment.filmpopfragent.PopDetailsFragment;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
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
    ImageView mIconDetails;
    String mId;
    CinemaBannerAdapter mCinemaBannerAdapter;
    CinemaScheduleAdapter mCinemaScheduleAdapter;
    View mView;
    PopupWindow mPop;
    ImageView mDown;
    RadioGroup mGroup;
    ViewPager mPager;
    List<Fragment> mlist;
    TextView textView_one;
    TextView textView_two;
    private String address;
    private String name;
    private int mId1;

    @Override
    protected void initView() {

        ButterKnife.bind(this);
        initPoPu();

    }

    private void initGo() {
        mCinemaBannerAdapter.setOnItemClick(new CinemaBannerAdapter.OnItemClick() {
            @Override
            public void success(String id) {
                Intent intent = new Intent(CinemaDetailActivity.this,FilmBuyingListActivity.class);
                intent.putExtra("movieId",id);
                intent.putExtra("cinemasId",mId);
                intent.putExtra("address",address);
                intent.putExtra("moviename",name);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mId = intent.getStringExtra("filmid");
        String logo = intent.getStringExtra("logo");
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        cinemadetailSimpleLogo.setImageURI(logo);
        cinemadetailTvName.setText(name);
        cinemadetailTvAddress.setText(address);

        initBanner(mId);

    }

   private void initToGo() {
        mCinemaScheduleAdapter.setOnSuccess(new CinemaScheduleAdapter.OnSuccess() {
            @Override
            public void success(String BeginTime, String EndTime, String Hall, String price,String id) {
                Intent intent = new Intent(CinemaDetailActivity.this,SelectionActivity.class);
                intent.putExtra("BeginTime",BeginTime);
                intent.putExtra("EndTime",EndTime);
                intent.putExtra("Hall",Hall);
                intent.putExtra("price",price);
                intent.putExtra("moviename",name);
                intent.putExtra("address",address);
                intent.putExtra("scheduleId",id);
                startActivity(intent);
            }
        });
    }

    public String getMid(){
        return mId;
    }

    private View pop;
    @Override
    public <T extends View> T findViewById(int id) {
        if (id == R.id.yingyuan_pop_pager && pop !=null){
            return pop.findViewById(id);
        }
        return super.findViewById(id);
    }

    private void initPoPu() {
        mView = View.inflate(CinemaDetailActivity.this, R.layout.cinemadetail_popu_detai, null);
        pop = mView;
        mDown = mView.findViewById(R.id.yingyuan_pop_down);
        mGroup = mView.findViewById(R.id.yingyuan_pop_group);
        mPager = mView.findViewById(R.id.yingyuan_pop_pager);
        textView_one = mView.findViewById(R.id.heng_one);
        textView_two = mView.findViewById(R.id.heng_two);
        mPop = new PopupWindow(mView,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置焦点
        mPop.setFocusable(true);
        //设置是否可以触摸
        mPop.setTouchable(true);
        mPop.setBackgroundDrawable(new BitmapDrawable());
        mPop.setAnimationStyle(R.style.Popupwindow);
        mPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeWindowAlfa(1f);//pop消失，透明度恢复
            }
        });

        mIconDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //改变窗口透明度
                changeWindowAlfa(0.6f);
                mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                mPop.update();
                //mPop.showAsDropDown(v, Gravity.BOTTOM,0 ,0 );
            }
        });

        //关闭
        mDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });

        mlist = new ArrayList<>();
        mlist.add(new PopCommentFragment());
        mlist.add(new PopDetailsFragment());

        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mlist.get(i);
            }

            @Override
            public int getCount() {
                return mlist.size();
            }
        });

        //点击滑动切换
        initButton();
    }

    private void initButton() {
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.yingyuan_but1:
                        mPager.setCurrentItem(0);
                        textView_one.setVisibility(View.VISIBLE);
                        textView_two.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.yingyuan_but2:
                        mPager.setCurrentItem(1);
                        textView_one.setVisibility(View.INVISIBLE);
                        textView_two.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        mGroup.check(R.id.yingyuan_but1);
                        textView_one.setVisibility(View.VISIBLE);
                         textView_two.setVisibility(View.INVISIBLE);
                    break;
                    case 1:
                        mGroup.check(R.id.yingyuan_but2);
                        textView_one.setVisibility(View.INVISIBLE);
                        textView_two.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void changeWindowAlfa(float v) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = v;
        getWindow().setAttributes(params);
    }



    //轮播图啊
    private void initBanner(final String mid) {
        doNetGet(Apis.URL_GET_findMovieListByCinemaId + "?cinemaId=" + mid, CinemaDetailBannerBean.class);

    }

    //排期
    private void initRecy(String mid, int id) {


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_cinema_detail;
    }

    @Override
    protected void netSuccess(Object data) {
        if (data instanceof CinemaDetailBannerBean) {
            CinemaDetailBannerBean cinemaDetailBannerBean = (CinemaDetailBannerBean) data;
            if (cinemaDetailBannerBean.getMessage().equals("无数据")){
                ToastUtils.show(this,"没数据");
            }else{
                mCinemaBannerAdapter = new CinemaBannerAdapter(this);
                cinemadetailRecyBanner.setAdapter(mCinemaBannerAdapter);
                mCinemaBannerAdapter.setData(cinemaDetailBannerBean.getResult());

                 mCinemaScheduleAdapter = new CinemaScheduleAdapter(this);
                cinemadetailRecy.setAdapter(mCinemaScheduleAdapter);
                cinemadetailRecy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

                mId1 = mCinemaBannerAdapter.getId(0);
                doNetGet(Apis.URL_GET_findMovieScheduleList + "?cinemasId=" + mId + "&movieId=" + mId1, CinemaDetailScheduleBean.class);
                cinemadetailRecyBanner.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
                    //滑动监听
                    @Override
                    public void onItemSelected( int position) {
                        mId1 = mCinemaBannerAdapter.getId(position);
                        doNetGet(Apis.URL_GET_findMovieScheduleList + "?cinemasId=" + mId + "&movieId=" + mId1, CinemaDetailScheduleBean.class);
                    }
                });
                initGo();
                //跳转到选择
                initToGo();
                mCinemaBannerAdapter.notifyDataSetChanged();
            }

        } else
            if (data instanceof CinemaDetailScheduleBean) {
            CinemaDetailScheduleBean cinemaDetailScheduleBean = (CinemaDetailScheduleBean) data;

                mCinemaScheduleAdapter.setData(cinemaDetailScheduleBean.getResult());
            mCinemaScheduleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
