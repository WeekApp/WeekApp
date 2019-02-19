package com.bw.movie.activity.filmactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.activity.useractivity.LoginActivity;
import com.bw.movie.adapter.filmadatper.detailsadapter.buyingadapter.BuyContentAdapter;
import com.bw.movie.app.App;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.cinemabean.RemmondBean;
import com.bw.movie.bean.filmbean.details.buyingbean.ConcrenBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyingListActivity extends BaseActivity {

    @BindView(R.id.buying_name)
    TextView mName;
    @BindView(R.id.buying_contents)
    RecyclerView mBuyContents;
    @BindView(R.id.buying_bliack)
    ImageView icon;

    BuyContentAdapter mBuyContentAdapter;
    String sid;
    private String name;

    @Override
    protected void initData() {
        //开始请求
        staryRequest();
        //设施适配器
        initAdapter();
        //设置布局管理器
        initManager();
        //返回当前页面
        initBliack();
        //关注与否
        initConcren();
        //电影ID和影院ID查询电影排期列表
        initGo();
    }

    private void initGo() {
        mBuyContentAdapter.setDianJi(new BuyContentAdapter.DianJi() {
            @Override
            public void success(String id,String nname,String address) {
                Intent intent = new Intent(BuyingListActivity.this,FilmBuyingListActivity.class);
                intent.putExtra("cinemasId",id);
                intent.putExtra("movieId",sid);
                intent.putExtra("moviename",nname);
                intent.putExtra("address",address);
                startActivity(intent);
            }
        });
    }

    private void initConcren() {
        mBuyContentAdapter.setOnItemClick(new BuyContentAdapter.OnItemClick() {
            @Override
            public void success(String id, boolean is) {

                SharedPreferences sharedPreferences = App.getApplication().getSharedPreferences("userName",Context.MODE_PRIVATE);
                String userId = sharedPreferences.getString("userId", "");
                String sessionId = sharedPreferences.getString("sessionId","");
                if(userId.equals("")&&sessionId.equals(""))
                {
                    ToastUtils.show(BuyingListActivity.this,"请先登陆");
                    startActivity(new Intent(BuyingListActivity.this,LoginActivity.class));
                }else {
                    if (is) {
                        doNetGet(String.format(Apis.URL_GET_GUANZHUYINGYUAN, id), ConcrenBean.class);
                    } else {
                        doNetGet(String.format(Apis.URL_GET_CANCLEGUANZHUYINGYUAN, id), ConcrenBean.class);
                    }
                    mBuyContentAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initBliack() {
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mBuyContents.setLayoutManager(linearLayoutManager);
    }

    private void initAdapter() {
        mBuyContentAdapter = new BuyContentAdapter(this);
        mBuyContents.setAdapter(mBuyContentAdapter);
    }

    private void staryRequest() {

        Intent intent = getIntent();
        name = intent.getStringExtra("buying");
        sid = intent.getStringExtra("id");
        mName.setText(name);
        doNetGet(Apis.URL_GET_NEARLY,RemmondBean.class);
    }

    @Override
    protected void initView() {
        //使用ButterKnife加载控件
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.buying_aitivty;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof RemmondBean){
            RemmondBean user = (RemmondBean) data;
            if(user.getStatus().equals("0000")){
                mBuyContentAdapter.setMlsit(user.getResult());
            }
        }

        if(data instanceof ConcrenBean){
            ConcrenBean user = (ConcrenBean) data;
            if(user.getStatus().equals("0000")){
                ToastUtils.show(this,user.getMessage());
                doNetGet(Apis.URL_GET_NEARLY,RemmondBean.class);
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
