package com.bw.movie.adapter.cinemaadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.bean.cinemabean.CinemaDetailBannerBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {
        String imageUrl = list.get(i).getImageUrl();
        String name = list.get(i).getName();

        viewHolder.simp_cinema_flow.setImageURI(imageUrl);
        viewHolder.text_cinema_flow1.setText(name);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.success(list.get(i).getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simp_cinema_flow;
        TextView text_cinema_flow1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simp_cinema_flow=itemView.findViewById(R.id.simp_cinema_flow);
            text_cinema_flow1=itemView.findViewById(R.id.text_cinema_flow1);
        }
    }
    public interface GetData{
        void onClick(int id);
    }

    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void success(String id);
    }
}