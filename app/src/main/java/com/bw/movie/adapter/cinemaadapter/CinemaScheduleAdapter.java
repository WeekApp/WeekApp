package com.bw.movie.adapter.cinemaadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.bean.cinemabean.CinemaDetailScheduleBean;
import com.bw.onlymycinema.R;

import org.w3c.dom.Text;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String screeningHall = list.get(i).getScreeningHall();
        String beginTime = list.get(i).getBeginTime();
        String endTime = list.get(i).getEndTime();

        viewHolder.cinemadetail_schedule_cinemaname.setText(screeningHall);
        viewHolder.cinemadetail_schedule_cinemastarttime.setText(beginTime);
        viewHolder.cinemadetail_schedule_cinemaendtime.setText(endTime);
        viewHolder.cinemadetail_schedule_cinemapriceint.setText("0");
        viewHolder.cinemadetail_schedule_cinemapricefloat.setText(".2");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cinemadetail_schedule_next;
        TextView cinemadetail_schedule_cinemaname,cinemadetail_schedule_cinemastarttime,cinemadetail_schedule_cinemaendtime
                ,cinemadetail_schedule_cinemapriceint,cinemadetail_schedule_cinemapricefloat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cinemadetail_schedule_cinemaname=itemView.findViewById(R.id.cinemadetail_schedule_cinemaname);
            cinemadetail_schedule_cinemastarttime=itemView.findViewById(R.id.cinemadetail_schedule_cinemastarttime);
            cinemadetail_schedule_cinemaendtime=itemView.findViewById(R.id.cinemadetail_schedule_cinemaendtime);
            cinemadetail_schedule_cinemapriceint=itemView.findViewById(R.id.cinemadetail_schedule_cinemapriceint);
            cinemadetail_schedule_cinemapricefloat=itemView.findViewById(R.id.cinemadetail_schedule_cinemapricefloat);
            cinemadetail_schedule_next=itemView.findViewById(R.id.cinemadetail_schedule_next);
        }
    }
}
