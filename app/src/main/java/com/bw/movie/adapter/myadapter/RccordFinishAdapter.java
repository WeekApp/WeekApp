package com.bw.movie.adapter.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
 * date:2019.2.12
 * author:赵颖冰(lenovo)
 * function:
 */
public class RccordFinishAdapter extends RecyclerView.Adapter<RccordFinishAdapter.ViewHolder> {
    Context mContext;
    List<RccordBean.ResultBean> list;

    public RccordFinishAdapter(Context context) {
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
        View view=View.inflate(mContext, R.layout.reccord_finish_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String movieName = list.get(i).getMovieName();
        int beginTime = list.get(i).getBeginTime();
        int endTime = list.get(i).getEndTime();
        String orderId = list.get(i).getOrderId();
        long createTime = list.get(i).getCreateTime();
        String cinemaName = list.get(i).getCinemaName();
        String screeningHall = list.get(i).getScreeningHall();


        int amount = list.get(i).getAmount();
        double price = list.get(i).getPrice();

        viewHolder.rccordfinish_item_name.setText(movieName);
        viewHolder.rccordfinish_item_code.setText( orderId);
        viewHolder.rccordfinish_item_cine.setText(cinemaName);
        viewHolder.rccordfinish_item_moviehall.setText(screeningHall);
        viewHolder.rccordfinish_item_starttime.setText(beginTime+"");
        viewHolder.rccordfinish_item_endtime.setText(endTime+"");
        viewHolder.rccordfinish_item_num.setText(amount+"");
        viewHolder.rccordfinish_item_money.setText(price+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rccordfinish_item_name,rccordfinish_item_starttime,rccordfinish_item_endtime,
                rccordfinish_item_code,rccordfinish_item_ordertime,rccordfinish_item_cine,
                rccordfinish_item_moviehall,rccordfinish_item_num,rccordfinish_item_money;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rccordfinish_item_name=itemView.findViewById(R.id.rccordfinish_item_name);
            rccordfinish_item_starttime=itemView.findViewById(R.id.rccordfinish_item_starttime);
            rccordfinish_item_endtime=itemView.findViewById(R.id.rccordfinish_item_endtime);
            rccordfinish_item_code=itemView.findViewById(R.id.rccordfinish_item_code);
            rccordfinish_item_ordertime=itemView.findViewById(R.id.rccordfinish_item_ordertime);
            rccordfinish_item_cine=itemView.findViewById(R.id.rccordfinish_item_cine);
            rccordfinish_item_moviehall=itemView.findViewById(R.id.rccordfinish_item_moviehall);
            rccordfinish_item_num=itemView.findViewById(R.id.rccordfinish_item_num);
            rccordfinish_item_money=itemView.findViewById(R.id.rccordfinish_item_money);
        }
    }
}
