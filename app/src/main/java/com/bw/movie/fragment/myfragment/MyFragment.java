package com.bw.movie.fragment.myfragment;


import android.animation.Animator;
import android.animation.ObjectAnimator;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.activity.myactivity.AttentionActivity;
import com.bw.movie.activity.myactivity.FeedBackActivity;
import com.bw.movie.activity.myactivity.MyMessageActivity;
import com.bw.movie.activity.myactivity.RccordActivity;
import com.bw.movie.activity.myactivity.RemindActivity;
import com.bw.movie.activity.myactivity.VersionActivity;
import com.bw.movie.activity.useractivity.LoginActivity;
import com.bw.movie.app.App;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.ToastUtil;
import com.bw.movie.bean.login.UserBean;
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
    @BindView(R.id.xixi)
    RelativeLayout mLogin;
    @BindView(R.id.tuichudengl)
    RelativeLayout tuichulogin;
    Unbinder unbinder;
    SharedPreferences sharedPreferences;

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
        //去登陆
        initGo();
        initToGo();
        //退出登录
        initGoBack();
    }

    private void initGoBack() {
        tuichulogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = App.getApplication().getSharedPreferences("userName",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear().commit();
                initMesage();
            }
        });
    }

    private void initToGo() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = App.getApplication().getSharedPreferences("userName",Context.MODE_PRIVATE);
                String userId = sharedPreferences.getString("userId", "");
                String sessionId = sharedPreferences.getString("sessionId","");
                Log.i("TTTTTT",userId+"xiaofei"+sessionId);
                if(userId.equals("")&&sessionId.equals(""))
                {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                    ToastUtils.show(getActivity(),"请先登陆");
                }else{
                    startActivity(new Intent(getActivity(),MyMessageActivity.class));
                }
            }
        });
    }

    private void initGo() {
        myfragmentMyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = App.getApplication().getSharedPreferences("userName",Context.MODE_PRIVATE);
                String userId = sharedPreferences.getString("userId", "");
                String sessionId = sharedPreferences.getString("sessionId","");
                if(userId.equals("")&&sessionId.equals(""))
                {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }else{

                }
            }
        });
    }

    //获取用户信息
    private void initMesage() {
        doNetGet(Apis.URL_GET_AWFK,UserBean.class);
        doNetGet(Apis.URL_GET_QUAI,MessageBean.class);
    }

    //点击事件
    @OnClick({R.id.myfragment_Simple_remind,R.id.myfragment_btn_signined,R.id.myfragment_btn_signin, R.id.myfragment_iv_attention, R.id.myfragment_tv_attention, R.id.myfragment_iv_feedback, R.id.myfragment_tv_feedback,R.id.myfragment_iv_rccord,R.id.myfragment_tv_rccord, R.id.myfragment_iv_version, R.id.myfragment_tv_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //系统消息
            case R.id.myfragment_Simple_remind:
                startActivity(new Intent(getContext(),RemindActivity.class));
                break;
            //签到
            case R.id.myfragment_btn_signin:
                SharedPreferences sharedPreferences = App.getApplication().getSharedPreferences("userName",Context.MODE_PRIVATE);
                String userId = sharedPreferences.getString("userId", "");
                String sessionId = sharedPreferences.getString("sessionId","");
                Log.i("TTTTTT",userId+"xiaofei"+sessionId);
                if(userId.equals("")&&sessionId.equals(""))
                {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                    ToastUtils.show(getActivity(),"请先登陆");
                }else{
                    //签到
                    initSign();
                }



                break;
                //已签到
            case R.id.myfragment_btn_signined:
                SharedPreferences sharedPreferences1 = App.getApplication().getSharedPreferences("userName",Context.MODE_PRIVATE);
                String userId1 = sharedPreferences1.getString("userId", "");
                String sessionId1 = sharedPreferences1.getString("sessionId","");
                Log.i("TTTTTT",userId1+"xiaofei"+sessionId1);
                if(userId1.equals("")&&sessionId1.equals(""))
                {
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                    ToastUtils.show(getActivity(),"请先登陆");
                }else{
                    //签到
                    initSign();
                }
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
                //startActivity(new Intent(getContext(),VersionActivity.class));
                break;
        }
    }

    private void initSign() {
        doNetGet(Apis.URL_GET_USERSIGNIN,RegisterBean.class);
    }
    //请求成功
    @Override
    protected void netSuccess(Object data) {

        if(data instanceof UserBean){
            UserBean messageBean = (UserBean) data;
            if(messageBean.getStatus().equals("0000")){
                //获取头像和昵称
                String headPic = messageBean.getResult().getHeadPic();
                Log.i("TTTTTTT",headPic);
                String nickName = messageBean.getResult().getNickName();
                Log.i("TTTTTTT",nickName);
                myfragmentMyimage.setImageURI(headPic);
                myfragmentMyname.setText(nickName);
            }else{
                myfragmentMyimage.setBackgroundResource(R.mipmap.zanwu);
                myfragmentMyname.setText("登录/注册");
                ToastUtils.show(getActivity(),"退出账号成功");
                initMesage();
            }
        }

        if (data instanceof MessageBean) {
            MessageBean messageBean = (MessageBean) data;
            //判断是否签到
            int userSignStatus = messageBean.getResult().getUserSignStatus();
            if (userSignStatus==1){
                myfragmentBtnSignin.setText("签到");
            }else if(userSignStatus==2){
                myfragmentBtnSignin.setText("已签到");
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
            ToastUtils.show(getContext(),flag+"");
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
