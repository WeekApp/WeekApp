package com.bw.movie.adapter.cinemaadapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.bean.cinemabean.CinemaDetailBannerBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import recycler.coverflow.RecyclerCoverFlow;

/**
 * date:2019.1.26
 * author:赵颖冰(lenovo)
 * function:
 */
public class CinemaBannerAdapter extends RecyclerView.Adapter<CinemaBannerAdapter.ViewHolder> {

    Context mContext;
    List<CinemaDetailBannerBean.ResultBean> list;

    public CinemaBannerAdapter(Context context) {
        mContext = context;
        list=new ArrayList<>();
    }
    public void setData(List<CinemaDetailBannerBean.ResultBean> result) {
        list.addAll(result);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.cinemadetail_recy_banner_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String imageUrl = list.get(i).getImageUrl();
        String name = list.get(i).getName();
        long releaseTime = list.get(i).getReleaseTime();
        final int id = list.get(i).getId();

        viewHolder.simp_cinema_flow.setImageURI(imageUrl);
        viewHolder.text_cinema_flow1.setText(name);
        viewHolder.text_cinema_flow2.setText(releaseTime+"");




    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simp_cinema_flow;
        TextView text_cinema_flow1,text_cinema_flow2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simp_cinema_flow=itemView.findViewById(R.id.simp_cinema_flow);
            text_cinema_flow1=itemView.findViewById(R.id.text_cinema_flow1);
            text_cinema_flow2=itemView.findViewById(R.id.text_cinema_flow2);
        }
    }
    public interface GetData{
        void onClick(int id);
    }
    private GetData mGetData;

    public void setGetData(GetData getData) {
        mGetData = getData;
    }
}