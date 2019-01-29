package com.bw.movie.adapter.cinemaadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.bean.cinemabean.CinemaSearchBean;
import com.bw.movie.bean.cinemabean.RemmondBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019.1.28
 * author:赵颖冰(lenovo)
 * function:
 */
public class CinemaSearchAdapter extends RecyclerView.Adapter<CinemaSearchAdapter.ViewHolder> {
    Context mContext;
    List<CinemaSearchBean.ResultBean> list;

    public CinemaSearchAdapter(Context context) {
        mContext = context;
        list=new ArrayList<>();
    }
    public void setData(List<CinemaSearchBean.ResultBean> result) {
        list.addAll(result);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.remmend_recy_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final String logo = list.get(i).getLogo();
        final String name = list.get(i).getName();
        final String address = list.get(i).getAddress();
        int distance = list.get(i).getDistance();
        final int id = list.get(i).getId();
        final int followCinema = list.get(i).getFollowCinema();

        viewHolder.remmend_simple_image.setImageURI(logo);
        viewHolder.remmend_tv_name.setText(name);
        viewHolder.remmend_tv_title.setText(address);
        viewHolder.remmend_tv_distance.setText(distance+"m");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetData.onClick(id,logo,name,address);
            }
        });
        viewHolder.remmend_iv_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetData.onColletion(id,followCinema);
                viewHolder.remmend_iv_collection.setVisibility(View.GONE);
                viewHolder.remmend_iv_collectionselect.setVisibility(View.VISIBLE);
            }
        });

        viewHolder.remmend_iv_collectionselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetData.onColletioned(id);
                viewHolder.remmend_iv_collection.setVisibility(View.VISIBLE);
                viewHolder.remmend_iv_collectionselect.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView remmend_simple_image,remmend_iv_collection,remmend_iv_collectionselect;
        TextView remmend_tv_name,remmend_tv_title,remmend_tv_distance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            remmend_simple_image=itemView.findViewById(R.id.remmend_simple_image);
            remmend_tv_name=itemView.findViewById(R.id.remmend_tv_name);
            remmend_tv_title=itemView.findViewById(R.id.remmend_tv_title);
            remmend_tv_distance=itemView.findViewById(R.id.remmend_tv_distance);
            remmend_iv_collection=itemView.findViewById(R.id.remmend_iv_collection);
            remmend_iv_collectionselect=itemView.findViewById(R.id.remmend_iv_collectionselect);
        }
    }
    public interface GetData{
        void onClick(int id, String logo, String name, String address);
        void onColletion(int id,int followCinema);
        void onColletioned(int id);
    }
    private GetData mGetData;

    public void setGetData(GetData getData) {
        mGetData = getData;
    }
}
