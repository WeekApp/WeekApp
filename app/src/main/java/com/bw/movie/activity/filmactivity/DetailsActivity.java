package com.bw.movie.activity.filmactivity;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.adapter.filmadatper.stageadapter.FlimContentAdapter;
import com.bw.movie.adapter.filmadatper.stageadapter.StageContentAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.filmbean.details.CommentBean;
import com.bw.movie.bean.filmbean.details.ConcrenBean;
import com.bw.movie.bean.filmbean.details.DetailsBean;
import com.bw.movie.bean.filmbean.details.MovieDetailsBean;
import com.bw.movie.bean.filmbean.details.ZanBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity {

    PopupWindow mPop,sPop,yPop;
    @BindView(R.id.details_text_title)
    TextView title;
    @BindView(R.id.details_text_icon)
    ImageView icon;
    @BindView(R.id.details_black)
    ImageView mBlick;
    @BindView(R.id.details_but_details)
    Button mDetails;
    @BindView(R.id.details_icon_concren)
    ImageView mConcren;
    @BindView(R.id.details_but_juzhao)
    Button mStage;
    @BindView(R.id.details_but_yingping)
    Button mFlim;
    @BindView(R.id.details_yiying)
    ImageView mYicon;

    ImageView mDown;
    ImageView pIocn;
    TextView pLeixing;
    TextView pdaoyan;
    TextView pshicahng;
    TextView pchandi;
    TextView ptitle;
    RecyclerView mStageContents;
    RecyclerView mFlimContents;
    StageContentAdapter mStageContentAdapter;
    ImageView sIcon,yIcon;
    FlimContentAdapter mFlimContentAdapter;
    String id;
    int TTT = 0;

    @Override
    protected void initData() {
        //开始网络请求
        startRequest();
        //返回电影
        startBlack();
        //点击查看详情
        startDetails();
        //点击关注
        initConcren();
        //点击查看剧照
        initStage();
        //打开影评
        initFlim();
        //设置适配器
        initAdapter();
        //设置布局管理器
        initManager();
        //点赞
        initZan();
    }

    private void initZan() {
        mFlimContentAdapter.setOnItemClick(new FlimContentAdapter.OnItemClick() {
            @Override
            public void success(int id, boolean isGreat) {
                Map<String,String> map = new HashMap<>();
                map.put("commentId",id+"");
                if(isGreat){
                   doNetPost(Apis.URL_POST_DIANZAN,map,ZanBean.class);
                }else{
                    doNetPost(Apis.URL_POST_DIANZAN,map,ZanBean.class);
                }
                mFlimContentAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initFlim() {
        View view1 = View.inflate(DetailsActivity.this,R.layout.flim_pop,null);
        mFlimContents = view1.findViewById(R.id.flim_pop_contents);
        yIcon = view1.findViewById(R.id.flim_icon_down);
        yPop = new PopupWindow(view1,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置焦点
        yPop.setFocusable(true);
        //设置是否可以触摸
        yPop.setTouchable(true);
        yPop.setBackgroundDrawable(new BitmapDrawable());

        yPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeWindowAlfa(1f);//pop消失，透明度恢复
            }
        });

        mFlim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改变窗口透明度
                changeWindowAlfa(0.6f);
                yPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                yPop.update();
                yPop.showAsDropDown(v, Gravity.BOTTOM,0 ,0 );
            }
        });
        //关闭
        yIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yPop.dismiss();
            }
        });
    }

    private void initManager() {
        StaggeredGridLayoutManager linearLayoutManagerStage = new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL);
        mStageContents.setLayoutManager(linearLayoutManagerStage);

        LinearLayoutManager linearLayoutManagerFlim = new LinearLayoutManager(this);
        mFlimContents.setLayoutManager(linearLayoutManagerFlim);
    }

    private void initAdapter() {
        mStageContentAdapter = new StageContentAdapter(this);
        mStageContents.setAdapter(mStageContentAdapter);

        mFlimContentAdapter = new FlimContentAdapter(this);
        mFlimContents.setAdapter(mFlimContentAdapter);
    }

    private void initStage() {
        View view = View.inflate(DetailsActivity.this,R.layout.pop_stage,null);
        mStageContents = view.findViewById(R.id.stage_pop_contents);
        sIcon = view.findViewById(R.id.stage_icon_down);
        sPop = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置焦点
        sPop.setFocusable(true);
        //设置是否可以触摸
        sPop.setTouchable(true);
        sPop.setBackgroundDrawable(new BitmapDrawable());

        sPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeWindowAlfa(1f);//pop消失，透明度恢复
            }
        });

        mStage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //改变窗口透明度
                changeWindowAlfa(0.6f);
                sPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                sPop.update();
                sPop.showAsDropDown(v, Gravity.BOTTOM,0 ,0 );
            }
        });
        //关闭
        sIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPop.dismiss();
            }
        });
    }
    private void initConcren() {
        mConcren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TTT==0){
                    TTT = 1;
                    doNetGet(String.format(Apis.URL_GET_GUANZHU,id),ConcrenBean.class);
                }else{
                    TTT = 0;
                    doNetGet(String.format(Apis.URL_GET_QUXIAOGUANZHU,id),ConcrenBean.class);
                }
            }
        });
    }

    private void startDetails() {
        View view = View.inflate(DetailsActivity.this,R.layout.details_pop_details,null);
       mDown = view.findViewById(R.id.pop_Item_down);
       pIocn = view.findViewById(R.id.pop_item_icon);
        pLeixing = view.findViewById(R.id.pop_item_leixing);
        pdaoyan = view.findViewById(R.id.pop_item_daoyan);
        pshicahng = view.findViewById(R.id.pop_item_shicahng);
        pchandi = view.findViewById(R.id.pop_item_chandi);
        ptitle = view.findViewById(R.id.pop_item_title);
       mPop = new PopupWindow(view,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
       //设置焦点
       mPop.setFocusable(true);
        //设置是否可以触摸
       mPop.setTouchable(true);
       mPop.setBackgroundDrawable(new BitmapDrawable());

        mPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeWindowAlfa(1f);//pop消失，透明度恢复
            }
        });

       mDetails.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //改变窗口透明度
               changeWindowAlfa(0.6f);
               mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
               mPop.update();
               mPop.showAsDropDown(v, Gravity.BOTTOM,0 ,0 );
           }
       });
       //关闭
        mDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
    }

    private void changeWindowAlfa(float v) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = v;
        getWindow().setAttributes(params);
    }

    private void startBlack() {
        mBlick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void startRequest() {

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        doNetGet(String.format(Apis.URL_GET_DETI, id),MovieDetailsBean.class);
        doNetGet(String.format(Apis.URL_GET_MOVIEDETAILS, id),DetailsBean.class);
        doNetGet(String.format(Apis.URL_GET_PNGLUN, id),CommentBean.class);
    }

    @Override
    protected void initView() {
        //使用 ButterKnife 绑定控件
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.details_moives;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof MovieDetailsBean){
            MovieDetailsBean user = (MovieDetailsBean) data;
            if(user.getStatus().equals("0000")){
                title.setText(user.getResult().getName());
                Glide.with(this).load(user.getResult().getImageUrl()).into(icon);
                Glide.with(this).load(user.getResult().getImageUrl()).into(pIocn);
                Glide.with(this).load(user.getResult().getImageUrl()).into(mYicon);
                pLeixing.setText(user.getResult().getName());
                pchandi.setText(user.getResult().getPlaceOrigin());
                pshicahng.setText(user.getResult().getDuration());
                pdaoyan.setText(user.getResult().getDirector());
                ptitle.setText(user.getResult().getSummary());
            }
        }
        if(data instanceof DetailsBean){
            DetailsBean user = (DetailsBean) data;
            if(user.getStatus().equals("0000")){
                Glide.with(this).load(user.getResult().getImageUrl()).into(pIocn);
                pLeixing.setText(user.getResult().getMovieTypes());
                pchandi.setText(user.getResult().getPlaceOrigin());
                pshicahng.setText(user.getResult().getDuration());
                pdaoyan.setText(user.getResult().getDirector());
                ptitle.setText(user.getResult().getSummary());
                mStageContentAdapter.setMlist(user.getResult().getPosterList());
                Log.i("TWZY",user.getResult().getPosterList().size()+"");
            }
        }

        if(data instanceof CommentBean){
            CommentBean user = (CommentBean) data;
            if(user.getStatus().equals("0000")){
                mFlimContentAdapter.setMlist(user.getResult());
            }
        }

        if(data instanceof ZanBean){
            ZanBean user = (ZanBean) data;
            if(user.getStatus().equals("0000")){
                ToastUtils.show(this,user.getMessage());
                doNetGet(String.format(Apis.URL_GET_PNGLUN, id),CommentBean.class);
            }else{
                ToastUtils.show(this,user.getMessage());
            }
        }

        if(data instanceof ConcrenBean){
            ConcrenBean user = (ConcrenBean) data;
            if (user.getMessage().equals("关注成功")){
                ToastUtils.show(this,user.getMessage());
                mConcren.setBackgroundResource(R.mipmap.com_icon_collection_selected);
            }else if(user.getMessage().equals("取消关注成功")){
                ToastUtils.show(this,user.getMessage());
                mConcren.setBackgroundResource(R.mipmap.com_icon_collection_default);
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
