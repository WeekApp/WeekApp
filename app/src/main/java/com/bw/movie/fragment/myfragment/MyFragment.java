package com.bw.movie.fragment.myfragment;


import android.animation.Animator;
import android.animation.ObjectAnimator;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.activity.myactivity.AttentionActivity;
import com.bw.movie.activity.myactivity.FeedBackActivity;
import com.bw.movie.activity.myactivity.MyMessageActivity;
import com.bw.movie.activity.myactivity.RccordActivity;
import com.bw.movie.activity.myactivity.RemindActivity;
import com.bw.movie.activity.myactivity.VersionActivity;
import com.bw.movie.activity.useractivity.LoginActivity;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.mybean.MessageBean;
import com.bw.movie.bean.mybean.VersionBean;
import com.bw.movie.bean.userbean.RegisterBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ActivityCollectorUtil;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * 我的页面
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.myfragment_Simple_remind)
    SimpleDraweeView myfragmentSimpleRemind;
    @BindView(R.id.myfragment_btn_signin)
    Button myfragmentBtnSignin;
    @BindView(R.id.myfragment_btn_signined)
    Button myfragmentBtnSignined;
    @BindView(R.id.myfragment_myimage)
    SimpleDraweeView myfragmentMyimage;
    @BindView(R.id.myfragment_myname)
    TextView myfragmentMyname;
    @BindView(R.id.myfragment_iv_messiage)
    ImageView myfragmentIvMessiage;
    @BindView(R.id.myfragment_tv_messiage)
    TextView myfragmentTvMessiage;
    @BindView(R.id.myfragment_iv_attention)
    ImageView myfragmentIvAttention;
    @BindView(R.id.myfragment_tv_attention)
    TextView myfragmentTvAttention;
    @BindView(R.id.myfragment_iv_feedback)
    ImageView myfragmentIvFeedback;
    @BindView(R.id.myfragment_tv_feedback)
    TextView myfragmentTvFeedback;
    @BindView(R.id.myfragment_iv_rccord)
    ImageView myfragmentIvRccord;
    @BindView(R.id.myfragment_tv_rccord)
    TextView myfragmentTvRccord;
    @BindView(R.id.myfragment_iv_version)
    ImageView myfragmentIvVersion;
    @BindView(R.id.myfragment_tv_version)
    TextView myfragmentTvVersion;
    @BindView(R.id.myfragment_iv_logout)
    ImageView myfragmentIvLogout;
    @BindView(R.id.myfragment_tv_logout)
    TextView myfragmentTvLogout;
    Unbinder unbinder;


    //初始化控件
    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        ActivityCollectorUtil.addActivity(getActivity());
    }
    //获取布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }



    //初始化数据
    @Override
    protected void initData() {
        //获取用户信息
        initMesage();

    }
    //获取用户信息
    private void initMesage() {
        doNetGet(Apis.URL_GET_QUREYMESSAGE,MessageBean.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        //获取用户信息
        initMesage();
    }

    //点击事件
    @OnClick({R.id.myfragment_Simple_remind,R.id.myfragment_btn_signined,R.id.myfragment_btn_signin, R.id.myfragment_iv_messiage, R.id.myfragment_tv_messiage, R.id.myfragment_iv_attention, R.id.myfragment_tv_attention, R.id.myfragment_iv_feedback, R.id.myfragment_tv_feedback,R.id.myfragment_iv_rccord,R.id.myfragment_tv_rccord, R.id.myfragment_iv_version, R.id.myfragment_tv_version, R.id.myfragment_iv_logout, R.id.myfragment_tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //系统消息
            case R.id.myfragment_Simple_remind:
                startActivity(new Intent(getContext(),RemindActivity.class));
                break;
            //签到
            case R.id.myfragment_btn_signin:
                //签到
                initSign();
                break;
                //已签到
            case R.id.myfragment_btn_signined:
                //签到
                initSign();
                break;
                //我的信息
            case R.id.myfragment_iv_messiage:
                startActivity(new Intent(getContext(),MyMessageActivity.class));
                break;
                //我的信息
            case R.id.myfragment_tv_messiage:
                startActivity(new Intent(getContext(),MyMessageActivity.class));
                break;
                //我的关注
            case R.id.myfragment_iv_attention:
                startActivity(new Intent(getContext(),AttentionActivity.class));
                break;
                //我的关注
            case R.id.myfragment_tv_attention:
                startActivity(new Intent(getContext(),AttentionActivity.class));
                break;
                //购买记录
            case R.id.myfragment_iv_rccord:
                startActivity(new Intent(getContext(),RccordActivity.class));
                break;
                //购买记录
            case R.id.myfragment_tv_rccord:
                startActivity(new Intent(getContext(),RccordActivity.class));
                break;
                //意见反馈
            case R.id.myfragment_iv_feedback:
                startActivity(new Intent(getContext(),FeedBackActivity.class));
                break;
                //意见反馈
            case R.id.myfragment_tv_feedback:
                startActivity(new Intent(getContext(),FeedBackActivity.class));
                break;
                //最新版本
            case R.id.myfragment_iv_version:

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(myfragmentIvVersion, "rotation", 360f);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
                objectAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        doNetGet(Apis.URL_GET_VERSION,VersionBean.class);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                //startActivity(new Intent(getContext(),VersionActivity.class));
                break;
            //最新版本
            case R.id.myfragment_tv_version:
                ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(myfragmentIvVersion, "rotation", 360f);
                objectAnimator1.setDuration(2000);
                objectAnimator1.start();
                objectAnimator1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        doNetGet(Apis.URL_GET_VERSION,VersionBean.class);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                doNetGet(Apis.URL_GET_VERSION,VersionBean.class);
                //startActivity(new Intent(getContext(),VersionActivity.class));
                break;
                //返回登录
            case R.id.myfragment_iv_logout:

                ActivityCollectorUtil.finishAllActivity();

                break;
                //返回登录
            case R.id.myfragment_tv_logout:
                ActivityCollectorUtil.finishAllActivity();
                break;
        }
    }

    private void initSign() {
        doNetGet(Apis.URL_GET_USERSIGNIN,RegisterBean.class);
    }
    //请求成功
    @Override
    protected void netSuccess(Object data) {
        if (data instanceof MessageBean) {
            MessageBean messageBean= (MessageBean) data;

            //获取头像和昵称
            String headPic = messageBean.getResult().getHeadPic();
            String nickName = messageBean.getResult().getNickName();
            myfragmentMyimage.setImageURI(headPic);
            myfragmentMyname.setText(nickName);

            //判断是否签到
            int userSignStatus = messageBean.getResult().getUserSignStatus();
            if (userSignStatus==1){
                myfragmentBtnSignined.setVisibility(View.GONE);
                myfragmentBtnSignin.setVisibility(View.VISIBLE);
            }else{
                myfragmentBtnSignined.setVisibility(View.VISIBLE);
                myfragmentBtnSignin.setVisibility(View.GONE);
            }

        }else if (data instanceof RegisterBean){
            //签到
            RegisterBean registerBean= (RegisterBean) data;
            String message = registerBean.getMessage();
            Toast.makeText(getContext(), message+"", Toast.LENGTH_SHORT).show();
            initMesage();
        }else if (data instanceof VersionBean){
            //更新版本

            VersionBean versionBean= (VersionBean) data;

            int flag = versionBean.getFlag();
            if (flag==1){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("有新版本");
                builder.setMessage("是否升级");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getContext(),VersionActivity.class));
                    }
                });
                builder.setNegativeButton("取消",null);
                builder.show();
            }else if (flag==2){
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("已是最新版本");
                builder.setMessage("是否取消");
                builder.setPositiveButton("是", null);
                builder.setNegativeButton("否",null);
                builder.show();
            }
        }
    }


    //请求失败
    @Override
    protected void netFail(Object data) {

    }

    //销毁布局
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(getActivity());
    }
}
