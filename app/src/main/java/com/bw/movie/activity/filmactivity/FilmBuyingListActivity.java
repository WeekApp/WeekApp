package com.bw.movie.activity.filmactivity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.adapter.cinemaadapter.FilmByingContentAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.cinemabean.FilmByingBean;
import com.bw.movie.bean.filmbean.details.detailsbean.DetailsBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilmBuyingListActivity extends BaseActivity {

    @BindView(R.id.fb_name)
    TextView name;
    @BindView(R.id.fb_title)
    TextView title;
    @BindView(R.id.fb_icon)
    SimpleDraweeView icon;
    @BindView(R.id.fb_r_name)
    TextView mName;
    @BindView(R.id.fb_r_leixing)
    TextView mLeixing;
    @BindView(R.id.fb_r_daoyan)
    TextView mDaoyan;
    @BindView(R.id.fb_r_shichang)
    TextView mShichang;
    @BindView(R.id.fb_r_chandi)
    TextView mChandi;
    @BindView(R.id.fb_contents)
    RecyclerView mFilmContentes;
    @BindView(R.id.buying_icon_black)
    ImageView mBlack;
    FilmByingContentAdapter filmByingContentAdapter;
    String moviename;
    String address;
    private String movieId;
    private String cinemasId;
    private String string;

    @Override
    protected void initData() {
        //开始请求
        startRequest();
        //创建适配器
        initAdapter();
        //布局管理器你
        initManager();
        //返回键
        initBlack();
        //点击跳转到选座页面
        initGo();
    }

    private void initGo() {
        filmByingContentAdapter.setOnItemClick(new FilmByingContentAdapter.OnItemClick() {
            @Override
            public void success(String BeginTime, String EndTime, String Hall, String price,String id) {
                Intent intent = new Intent(FilmBuyingListActivity.this,SelectionActivity.class);
                intent.putExtra("BeginTime",BeginTime);
                intent.putExtra("EndTime",EndTime);
                intent.putExtra("Hall",Hall);
                intent.putExtra("price",price);
                intent.putExtra("moviename",moviename);
                intent.putExtra("address",address);
                intent.putExtra("string",string);
                intent.putExtra("scheduleId",id);
                startActivity(intent);
            }
        });
    }

    private void initBlack() {
        mBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mFilmContentes.setLayoutManager(linearLayoutManager);
    }

    private void initAdapter() {
        filmByingContentAdapter = new FilmByingContentAdapter(this);
        mFilmContentes.setAdapter(filmByingContentAdapter);
    }

    private void startRequest() {
        Intent intent = getIntent();
        moviename = intent.getStringExtra("moviename");
        cinemasId = intent.getStringExtra("cinemasId");
        movieId = intent.getStringExtra("movieId");
        address = intent.getStringExtra("address");
        title.setText(address);
        doNetGet(String.format(Apis.URL_GET_MOVIEDETAILS, movieId),DetailsBean.class);
        doNetGet(String.format(Apis.URL_GET_YINGYUAN, cinemasId, movieId),FilmByingBean.class);
        name.setText(moviename);
    }

    @Override
    protected void initView() {
        //使用ButterKnife绑定控件
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.filmbuying_activity;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof DetailsBean){
            DetailsBean user = (DetailsBean) data;
            if(user.getStatus().equals("0000")){
                String imageUrl = user.getResult().getImageUrl();
                icon.setImageURI(imageUrl);
                string = user.getResult().getName();
                mLeixing.setText(user.getResult().getMovieTypes());
                mDaoyan.setText(user.getResult().getDirector());
                mShichang.setText(user.getResult().getDuration());
                mChandi.setText(user.getResult().getPlaceOrigin());
                mName.setText(user.getResult().getName());
            }
        }

        if(data instanceof FilmByingBean){
            FilmByingBean user = (FilmByingBean) data;
            if(user.getStatus().equals("0000")){
                filmByingContentAdapter.setMlist(user.getResult());
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
