package com.bw.movie.activity.filmactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.app.App;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.filmbean.details.detailsbean.ShopOrderBean;
import com.bw.movie.mvp.util.Apis;
import com.bw.movie.util.ToastUtils;
import com.bw.movie.view.SeatTable;
import com.bw.onlymycinema.R;

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

    double TotalPrice = 0;
    String price;
    int TotalNum = 0;
    private String hall;
    private Double mPrice;
    private String scheduleId;

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
                Log.i("SIGN",sign);
                map.put("sign",sign);
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
                Log.i("WWWW",user.getOrderId());
                ToastUtils.show(this,user.getMessage());
            }
        }
    }

    @Override
    protected void netFail(Object data) {

    }
}
