package com.bw.movie.adapter.cinemaadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
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


        if(followCinema==1){
            viewHolder.checkBox.setChecked(true);
        }else{
            viewHolder.checkBox.setChecked(false);
        }

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(i).getFollowCinema()==1){
                    list.get(i).setFollowCinema(2);
                }else{
                    list.get(i).setFollowCinema(1);
                }
                onItemClick.success(list.get(i).getId()+"",list.get(i).getFollowCinema()==1);
                notifyItemChanged(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView remmend_simple_image;
        TextView remmend_tv_name,remmend_tv_title,remmend_tv_distance;
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            remmend_simple_image=itemView.findViewById(R.id.remmend_simple_image);
            remmend_tv_name=itemView.findViewById(R.id.remmend_tv_name);
            remmend_tv_title=itemView.findViewById(R.id.remmend_tv_title);
            remmend_tv_distance=itemView.findViewById(R.id.remmend_tv_distance);
            checkBox = itemView.findViewById(R.id.rey_item_xin);
        }
    }
    public interface GetData{
        void onClick(int id, String logo, String name, String address);
    }
    private GetData mGetData;

    public void setGetData(GetData getData) {
        mGetData = getData;
    }



    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void success(String id,boolean is);
    }
}
