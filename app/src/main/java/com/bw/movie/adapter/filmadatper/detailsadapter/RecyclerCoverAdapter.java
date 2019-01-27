package com.bw.movie.adapter.filmadatper.detailsadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.bean.filmbean.FilmHotBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerCoverAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<FilmHotBean.ResultBean> mlist;
    Context mContext;

    public RecyclerCoverAdapter(Context context){
        mlist = new ArrayList<>();
        mContext = context;
    }

    public void setMlist(List<FilmHotBean.ResultBean> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView icon;
        TextView title,time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.simp_cinema_flow);
            time = itemView.findViewById(R.id.text_cinema_flow2);
            title = itemView.findViewById(R.id.text_cinema_flow1);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.cover_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        holder.icon.setImageURI(mlist.get(i).getImageUrl());
        holder.title.setText(mlist.get(i).getName());
        holder.time.setText(mlist.get(i).getFollowMovie()+"分钟");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.success(mlist.get(i).getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void success(String id);
    }
}
