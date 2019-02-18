package com.bw.movie.adapter.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.bean.mybean.RccordBean;
import com.bw.onlymycinema.R;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019.1.30
 * author:赵颖冰(lenovo)
 * function:
 */
public class RccordMoneyAdapter extends RecyclerView.Adapter<RccordMoneyAdapter.ViewHolder> {
    Context mContext;
    List<RccordBean.ResultBean> list;

    public RccordMoneyAdapter(Context context) {
        mContext = context;
        list=new ArrayList<>();
    }
    public void setData(List<RccordBean.ResultBean> result) {
        list.addAll(result);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext, R.layout.reccord_money_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String movieName = list.get(i).getMovieName();
        String orderId = list.get(i).getOrderId();
        String cinemaName = list.get(i).getCinemaName();
        String screeningHall = list.get(i).getScreeningHall();
        String beginTime = list.get(i).getBeginTime();
        String endTime = list.get(i).getEndTime();
        int amount = list.get(i).getAmount();
        double price = list.get(i).getPrice();

        viewHolder.rccordmoney_item_name.setText(movieName);
        viewHolder.rccordmoney_item_code.setText("订单号: "+orderId);
        viewHolder.rccordmoney_item_cinema.setText("影院: "+cinemaName);
        viewHolder.rccordmoney_item_office.setText("影厅: "+screeningHall);
        viewHolder.rccordmoney_item_time.setText("时间: "+beginTime+"——"+endTime);
        viewHolder.rccordmoney_item_num.setText("数量: "+amount+"");
        viewHolder.rccordmoney_item_money.setText("金额: "+price+"");

        viewHolder.rccordmoney_item_gomoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImtemClick.success(list.get(i).getOrderId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rccordmoney_item_name,rccordmoney_item_code,rccordmoney_item_cinema
                ,rccordmoney_item_office,rccordmoney_item_time,rccordmoney_item_num,rccordmoney_item_money;
        Button rccordmoney_item_gomoney;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rccordmoney_item_name=itemView.findViewById(R.id.rccordmoney_item_name);
            rccordmoney_item_gomoney=itemView.findViewById(R.id.rccordmoney_item_gomoney);
            rccordmoney_item_code=itemView.findViewById(R.id.rccordmoney_item_code);
            rccordmoney_item_cinema=itemView.findViewById(R.id.rccordmoney_item_cinema);
            rccordmoney_item_office=itemView.findViewById(R.id.rccordmoney_item_office);
            rccordmoney_item_time=itemView.findViewById(R.id.rccordmoney_item_time);
            rccordmoney_item_num=itemView.findViewById(R.id.rccordmoney_item_num);
            rccordmoney_item_money=itemView.findViewById(R.id.rccordmoney_item_money);
        }
    }

    OnImtemClick onImtemClick;

    public void setOnImtemClick(OnImtemClick onImtemClick) {
        this.onImtemClick = onImtemClick;
    }

    public interface OnImtemClick{
        void success(String order);
    }
}
