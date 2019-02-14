package com.bw.movie.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bw.movie.util.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    /*@BindView(R.id.pay_result_gotopay)
    Button payResultGotopay;
    @BindView(R.id.pay_result_payprice)
    TextView payResultPayprice;*/
    private IWXAPI api;
    PayReq request = new PayReq();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.pay_result);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        String appId = intent.getStringExtra("appId");
        String nonceStr = intent.getStringExtra("nonceStr");
        String partnerId = intent.getStringExtra("partnerId");
        String prepayId = intent.getStringExtra("prepayId");
        String sign = intent.getStringExtra("sign");
        String timeStamp = intent.getStringExtra("timeStamp");
        String packageValue = intent.getStringExtra("packageValue");
        float price = intent.getFloatExtra("price", 0);
        //payResultPayprice.setText(price+"元");
        api = WXAPIFactory.createWXAPI(this,"wxb3852e6a6b7d9516");
        api.handleIntent(getIntent(), this);
        //
        request.appId = appId;
        request.partnerId  = partnerId;
        request.prepayId = prepayId;
        request.packageValue  = packageValue;
        request.nonceStr = nonceStr;
        request.timeStamp = timeStamp;
        request.sign = sign;
        api.sendReq(request);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }

    @Override
    public void onResp(BaseResp resp) {
        Log.i("TAG",  resp.getType()+"");
        //用户已取消
        if (resp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
            ToastUtils.show(this,"用户已取消");
            finish();
        }else if (resp.errCode == BaseResp.ErrCode.ERR_OK) {
            ToastUtils.show(this,"支付成功");
            //EventBus.getDefault().postSticky(new CommonBean("pay_ok","0000"));
            finish();
        }else if (resp.errCode== BaseResp.ErrCode.ERR_USER_CANCEL) {
            ToastUtils.show(this,"支付错误,请稍后重试");
            finish();
        }
    }
}