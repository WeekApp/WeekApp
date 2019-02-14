package com.bw.movie.activity.filmactivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.base.BaseActivity;
import com.bw.movie.view.SeatTable;
import com.bw.onlymycinema.R;
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

    double TotalPrice = 0;
    String price;
    int TotalNum = 0;
    private String hall;
    private Double mPrice;

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

    }

    @Override
    protected void netFail(Object data) {

    }
}
