package com.bw.movie.adapter.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.bean.mybean.MessageBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019.1.29
 * author:赵颖冰(lenovo)
 * function:
 */
public class AttentionCinemaAdapter extends RecyclerView.Adapter<AttentionCinemaAdapter.ViewHolder> {

    Context mContext;
    List<MessageBean.ResultBean.CinemasListBean> list;

    public AttentionCinemaAdapter(Context context) {
        mContext = context;
        list=new ArrayList<>();
    }
    public void setData(List<MessageBean.ResultBean.CinemasListBean> cinemasList) {
        list.addAll(cinemasList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext, R.layout.attentioncinema_recy_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String logo = list.get(i).getLogo();
        String address = list.get(i).getAddress();
        String name = list.get(i).getName();

        viewHolder.attentioncinema_item_image.setImageURI(logo);
        viewHolder.attentioncinema_item_name.setText(name);
        viewHolder.attentioncinema_item_title.setText(address);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView attentioncinema_item_image;
        TextView attentioncinema_item_name,attentioncinema_item_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            attentioncinema_item_image=itemView.findViewById(R.id.attentioncinema_item_image);
            attentioncinema_item_name=itemView.findViewById(R.id.attentioncinema_item_name);
            attentioncinema_item_title=itemView.findViewById(R.id.attentioncinema_item_title);
        }
    }
}
