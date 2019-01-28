package com.bw.movie.adapter.filmadatper.detailsadapter.filmmoreadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.bean.filmbean.FilmHotBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class HotContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<FilmHotBean.ResultBean> mlist;
    Context mContext;

    public HotContentAdapter(Context context){
        mlist = new ArrayList<>();
        mContext = context;
    }

    public void setMlist(List<FilmHotBean.ResultBean> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView icon;
        ImageView mXin;
        TextView title,name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.hot_fragment_item_icon);
            mXin = itemView.findViewById(R.id.hot_fragment_item_xin);
            title = itemView.findViewById(R.id.hot_fragment_item_title);
            name = itemView.findViewById(R.id.got_fragment_item_title);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.hot_fragment_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        holder.icon.setImageURI(mlist.get(i).getImageUrl());
        holder.title.setText(mlist.get(i).getSummary());
        holder.name.setText(mlist.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
