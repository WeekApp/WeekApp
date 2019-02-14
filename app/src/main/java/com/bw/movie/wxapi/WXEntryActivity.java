package com.bw.movie.wxapi;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.bw.movie.Utils.WeiXinUtil;
import com.bw.movie.activity.homeactivity.MainActivity;
import com.bw.movie.app.App;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.login.WXBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : FangShiKang
 * @date : 2019/01/25.
 * email : fangshikang@outlook.com
 * desc :   微信第三方登录
 */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private static final String TAG = "WXEntryActivity";
    private String mCode;
    private SharedPreferences mSP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        WeiXinUtil.reg(WXEntryActivity.this).handleIntent(getIntent(), this);

    }



    @Override
    protected void initData() {
        //创建SharedPreferences储存数据
        mSP = getSharedPreferences("config", MODE_PRIVATE);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wxentry;
    }

    @Override
    protected void netSuccess(Object object) {
        if (object instanceof WXBean) {
            WXBean wXbean = (WXBean) object;
            if (wXbean.getStatus().equals("0000")) {
                ToastUtils.show(this,wXbean.getMessage());
                mSP.edit()
                        .putString("userId", wXbean.getResult().getUserId() + "")
                        .putString("sessionId", wXbean.getResult().getSessionId())
                        .commit();
                Intent intent=new Intent(WXEntryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        App.api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(final BaseResp baseResp) {

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                //主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //得到code
                        mCode = ((SendAuth.Resp) baseResp).code;
                        ToastUtils.show(WXEntryActivity.this,mCode);
                        Map<String, String> map = new HashMap<>();
                        map.put("code", mCode);
                        doNetPost(Apis.LOGIN_WX_URL, map, WXBean.class);
                        Log.e(TAG, mCode);
                    }
                });
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:

                break;
        }
    }


}
