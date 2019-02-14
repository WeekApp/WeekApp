package com.bw.movie.adapter.filmadatper.detailsadapter.filmmoreadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.bean.filmbean.FilmHotBean;
import com.bw.movie.bean.filmbean.FilmIngBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class IngContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<FilmIngBean.ResultBean> mlist;
    Context mContext;


    public IngContentAdapter(Context context){
        mlist = new ArrayList<>();
        mContext = context;
    }

    public void setMlist(List<FilmIngBean.ResultBean> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView icon;
        CheckBox mXin;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        holder.icon.setImageURI(mlist.get(i).getImageUrl());
        holder.title.setText(mlist.get(i).getSummary());
        holder.name.setText(mlist.get(i).getName());

        int followMovie = mlist.get(i).getFollowMovie();
        if(followMovie==1){
            holder.mXin.setChecked(true);
        }else{
            holder.mXin.setChecked(false);
        }

        holder.mXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mlist.get(i).getFollowMovie()==1){
                    mlist.get(i).setFollowMovie(2);
                }else{
                    mlist.get(i).setFollowMovie(1);
                }
                onItemClick.succuess(mlist.get(i).getId()+"",mlist.get(i).getFollowMovie()==1);
                notifyItemChanged(i);
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
        void succuess(String id,boolean isMovie);
    }
}
