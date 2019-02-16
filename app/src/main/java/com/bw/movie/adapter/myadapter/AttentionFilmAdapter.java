package com.bw.movie.adapter.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.bean.cinemabean.ConrenFilmBean;
import com.bw.movie.bean.mybean.MessageBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * date:2019.1.29
 * author:赵颖冰(lenovo)
 * function:
 */
public class AttentionFilmAdapter extends RecyclerView.Adapter<AttentionFilmAdapter.ViewHolder> {
    Context mContext;
    List<ConrenFilmBean.ResultBean> list;

    public AttentionFilmAdapter(Context context) {
        mContext = context;
        list=new ArrayList<>();
    }
    public void setData(List<ConrenFilmBean.ResultBean> movieList) {
        list.addAll(movieList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext, R.layout.attentionfilm_recy_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String imageUrl = list.get(i).getImageUrl();
        String name = list.get(i).getName();
        long releaseTime = list.get(i).getReleaseTime();
        String summary = list.get(i).getSummary();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String time = dateFormat.format(new Date(releaseTime));

        viewHolder.attentionfilm_item_image.setImageURI(imageUrl);
        viewHolder.attentionfilm_item_name.setText(name);
        viewHolder.attentionfilm_item_title.setText(summary);
        viewHolder.attentionfilm_item_tima.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView attentionfilm_item_image;
        TextView attentionfilm_item_name,attentionfilm_item_title,attentionfilm_item_tima;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            attentionfilm_item_image=itemView.findViewById(R.id.attentionfilm_item_image);
            attentionfilm_item_name=itemView.findViewById(R.id.attentionfilm_item_name);
            attentionfilm_item_title=itemView.findViewById(R.id.attentionfilm_item_title);
            attentionfilm_item_tima=itemView.findViewById(R.id.attentionfilm_item_tima);
        }
    }
}