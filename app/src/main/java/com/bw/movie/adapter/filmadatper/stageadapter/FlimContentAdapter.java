package com.bw.movie.adapter.filmadatper.stageadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.bean.filmbean.details.detailsbean.CommentBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class FlimContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<CommentBean.ResultBean> mlist;
    Context mContext;

    public FlimContentAdapter(Context context){
        mlist = new ArrayList<>();
        mContext = context;
    }

    public void setMlist(List<CommentBean.ResultBean> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView icon;
        TextView name,title,data,ment,zan;
        ImageView mMnet,mZan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.flim_pop_icon);
            name = itemView.findViewById(R.id.flim_pop_name);
            title = itemView.findViewById(R.id.flim_pop_title);
            data = itemView.findViewById(R.id.film_pop_data);
            ment = itemView.findViewById(R.id.flim_pop_ment_text);
            mMnet = itemView.findViewById(R.id.file_pop_icon_ment);
            zan = itemView.findViewById(R.id.flim_pop_zan_text);
            mZan = itemView.findViewById(R.id.flim_pop_icon_zan);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.flim_pop_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {

        final ViewHolder holder = (ViewHolder) viewHolder;

        long commentTime = mlist.get(i).getCommentTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String time = dateFormat.format(new Date(commentTime));
        holder.data.setText(time);

        holder.icon.setImageURI(mlist.get(i).getCommentHeadPic());
        holder.name.setText(mlist.get(i).getCommentUserName());
        holder.title.setText(mlist.get(i).getCommentContent());
        holder.ment.setText(mlist.get(i).getReplyNum()+"");
        holder.zan.setText(mlist.get(i).getGreatNum()+"");

        int isGreat = mlist.get(i).getIsGreat();
        if(isGreat==1){
            holder.mZan.setBackgroundResource(R.mipmap.com_icon_praise_selected);
        }else{
            holder.mZan.setBackgroundResource(R.mipmap.com_icon_praise_default);
        }

        holder.mZan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mlist.get(i).getIsGreat()==0) {
                    mlist.get(i).setIsGreat(mlist.get(i).getIsGreat()+1);
                    mlist.get(i).setGreatNum(mlist.get(i).getGreatNum()+1);
                }
                onItemClick.success(mlist.get(i).getCommentId() + "", mlist.get(i).getIsGreat() == 0);
                notifyItemChanged(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mlist!=null){
            return mlist.size();
        }
        return 0;
    }

    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void success(String id,boolean isGreat);
    }
}
