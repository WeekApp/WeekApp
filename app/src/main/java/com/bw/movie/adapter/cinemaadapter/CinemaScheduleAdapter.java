package com.bw.movie.adapter.cinemaadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.bean.cinemabean.CinemaDetailScheduleBean;
import com.bw.onlymycinema.R;


import java.util.ArrayList;
import java.util.List;

/**
 * date:2019.1.26
 * author:赵颖冰(lenovo)
 * function:
 */
public class CinemaScheduleAdapter extends RecyclerView.Adapter<CinemaScheduleAdapter.ViewHolder> {

    Context mContext;
    List<CinemaDetailScheduleBean.ResultBean> list;

    public CinemaScheduleAdapter(Context context) {
        mContext = context;
        list=new ArrayList<>();
    }
    public void setData(List<CinemaDetailScheduleBean.ResultBean> result) {
        list.addAll(result);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.cinemadetail_recy_schedule_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String screeningHall = list.get(i).getScreeningHall();
        String beginTime = list.get(i).getBeginTime();
        String endTime = list.get(i).getEndTime();

        viewHolder.cinemadetail_schedule_cinemaname.setText(screeningHall);
        viewHolder.cinemadetail_schedule_cinemastarttime.setText(beginTime);
        viewHolder.cinemadetail_schedule_cinemaendtime.setText(endTime);
        double price = list.get(i).getPrice();
        SpannableString spannableString = new SpannableString(price+"");
        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.4f);
        spannableString.setSpan(sizeSpan01, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        viewHolder.cinemadetail_schedule_cinemapriceint.setText(spannableString);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String beginTime = list.get(i).getBeginTime();
                String endTime = list.get(i).getEndTime();
                String hall = list.get(i).getScreeningHall();
                double price = list.get(i).getPrice();
                String id = list.get(i).getId()+"";
                onSuccess.success(beginTime,endTime,hall,price+"",id);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cinemadetail_schedule_next;
        TextView cinemadetail_schedule_cinemaname,cinemadetail_schedule_cinemastarttime,cinemadetail_schedule_cinemaendtime
                ,cinemadetail_schedule_cinemapriceint;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemadetail_schedule_cinemaname=itemView.findViewById(R.id.cinemadetail_schedule_cinemaname);
            cinemadetail_schedule_cinemastarttime=itemView.findViewById(R.id.cinemadetail_schedule_cinemastarttime);
            cinemadetail_schedule_cinemaendtime=itemView.findViewById(R.id.cinemadetail_schedule_cinemaendtime);
            cinemadetail_schedule_cinemapriceint=itemView.findViewById(R.id.cinemadetail_schedule_cinemapriceint);
            cinemadetail_schedule_next=itemView.findViewById(R.id.cinemadetail_schedule_next);
        }
    }

    OnSuccess onSuccess;

    public void setOnSuccess(OnSuccess onSuccess) {
        this.onSuccess = onSuccess;
    }

    public interface OnSuccess{
        void success(String BeginTime,String EndTime,String Hall,String price,String id);
    }
}
