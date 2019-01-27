package com.bw.movie.adapter.filmadatper.stageadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class StageContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> mlist;
    Context mContext;

    public StageContentAdapter(Context context){
        mlist = new ArrayList<>();
        mContext = context;
    }

    public void setMlist(List<String> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.stage_pop_item_icon);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.stage_pop_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        Glide.with(mContext).load(mlist.get(i)).into(holder.icon);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
