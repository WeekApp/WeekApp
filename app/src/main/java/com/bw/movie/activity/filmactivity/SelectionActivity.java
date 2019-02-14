package com.bw.movie.activity.filmactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.app.App;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.filmbean.details.buyingbean.PaySuccessBean;
import com.bw.movie.bean.filmbean.details.detailsbean.ShopOrderBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.EncryptUtil;
import com.bw.movie.util.ToastUtils;
import com.bw.movie.view.SeatTable;
import com.bw.movie.wxapi.WXPayEntryActivity;
import com.bw.onlymycinema.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectionActivity extends BaseActivity {

    @BindView(R.id.select_film_name)
    TextView mName;
    @BindView(R.id.select_film_address)
    TextView mAddress;
    @BindView(R.id.select_name)
    TextView mSname;
    @BindView(R.id.select_time)
    TextView mTime;
    @BindView(R.id.seatView)
    SeatTable seatTable;
    @BindView(R.id.text_price)
    TextView mprice;
    @BindView(R.id.pay_pay)
    ImageView mPay;
    @BindView(R.id.pay_quxiao)
    ImageView mQuxiao;
    PopupWindow mPop;
    double TotalPrice = 0;
    String price;
    int TotalNum = 0;
    private String hall;
    private Double mPrice;
    private String scheduleId;
    private RadioButton weixin;
    private RadioButton zhifu;
    private TextView mPay1;
    private String orderId;
    private RelativeLayout goPay;

    @Override
    protected void initData() {
        //开始网络请求
        startReuqest();
        //选座
        initSeatTable();
        //创建订单
        initOrder();
    }
    private void initOrder() {
        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map = new HashMap<>();
                map.put("scheduleId",scheduleId);
                map.put("amount",TotalNum+"");
                SharedPreferences sharedPreferences = App.getApplication().getSharedPreferences("userName",MODE_PRIVATE);
                String userId = sharedPreferences.getString("userId", "");
                String sign = userId+scheduleId+TotalNum+"movie";
                String md5 = MD5(sign);
                map.put("sign",md5);
                doNetPost(Apis.URL_SHAPE_ORDER,map,ShopOrderBean.class);
            }
        });
    }

    private void initSeatTable() {
        seatTable.setScreenName(hall);
        //设置屏幕名称
        seatTable.setMaxSelected(2);
        //设置最多选中
        seatTable.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
                TotalNum++;
                mPrice = Double.parseDouble(price);
                TotalPrice+=mPrice;
                mprice.setText(TotalPrice+"");
            }

            @Override
            public void unCheck(int row, int column) {
                TotalNum--;
                mPrice = Double.parseDouble(price);
                TotalPrice-= mPrice;
                mprice.setText(TotalPrice+"");
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }
        });
        seatTable.setData(10,15);
    }

    private void startReuqest() {
        Intent intent = getIntent();
        String moviename = intent.getStringExtra("moviename");
        String address = intent.getStringExtra("address");
        price = intent.getStringExtra("price");
        String string = intent.getStringExtra("string");
        String begintime = intent.getStringExtra("BeginTime");
        String endTime = intent.getStringExtra("EndTime");
        hall = intent.getStringExtra("Hall");
        scheduleId = intent.getStringExtra("scheduleId");
        mTime.setText(begintime+"-"+endTime+"  "+ hall);
        mSname.setText(string);
        mName.setText(moviename);
        mAddress.setText(address);
    }

    @Override
    protected void initView() {
        //使用ButterKnife绑定控件
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_select;
    }

    @Override
    protected void netSuccess(Object data) {
        if(data instanceof ShopOrderBean){
            ShopOrderBean user = (ShopOrderBean) data;
            if(user.getStatus().equals("0000")){
                //下单成功的时候弹出pop进行支付
                showPop();
                ToastUtils.show(this,user.getMessage());
                //打印订单
                orderId = user.getOrderId();
            }
        }

        if(data instanceof PaySuccessBean){
            PaySuccessBean user = (PaySuccessBean) data;
            if(user.getStatus().equals("0000")){
                //TODO:
                Intent intent = new Intent(SelectionActivity.this,WXPayEntryActivity.class);
                intent.putExtra("appId",user.getAppId());
                intent.putExtra("nonceStr",user.getNonceStr());
                intent.putExtra("partnerId",user.getPartnerId());
                intent.putExtra("prepayId",user.getPrepayId());
                intent.putExtra("sign",user.getSign());
                intent.putExtra("timeStamp",user.getTimeStamp());
                intent.putExtra("packageValue",user.getPackageValue());

                startActivity(intent);
            }
        }
    }

    private void showPop() {
        View view = View.inflate(SelectionActivity.this,R.layout.pop_pay,null);
        weixin = view.findViewById(R.id.rg_weixin);
        zhifu = view.findViewById(R.id.rg_zhifubao);
        ImageView quxiao = view.findViewById(R.id.pay_qu);
        mPay1 = view.findViewById(R.id.text_total_price);
        goPay = view.findViewById(R.id.sdfvbg);
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
        mPop.setAnimationStyle(R.style.Popupwindow);
        changeWindowAlfa(0.6f);

        mPop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        mPop.update();
        mPop.showAsDropDown(view, Gravity.BOTTOM,0 ,0 );
        mPay1.setText("微信支付"+TotalPrice+"元");

        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPay1.setText("微信支付"+TotalPrice+"元");
            }
        });

        zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPay1.setText("支付宝支付"+TotalPrice+"元");
            }
        });

        goPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zhifu.isChecked()){
                    ToastUtils.show(SelectionActivity.this,"暂不支持支付宝支付！");
                }else{
                    goPayMoney();
                }
            }
        });

        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
    }

    private void goPayMoney() {
        Map<String,String> map =new HashMap<>();
        map.put("payType",1+"");
        map.put("orderId",orderId);
        doNetPost(Apis._URL_PAG_MONEY,map,PaySuccessBean.class);
        /*Intent intent = new Intent(this,WXPayEntryActivity.class);
        intent.putExtra("",);*/
    }

    private void changeWindowAlfa(float v) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = v;
        getWindow().setAttributes(params);
    }

    @Override
    protected void netFail(Object data) {

    }

    /**
     *  MD5加密
     * @param sourceStr
     * @return
     */
    public  String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
}
