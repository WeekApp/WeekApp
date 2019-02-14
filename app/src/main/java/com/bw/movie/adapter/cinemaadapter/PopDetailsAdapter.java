package com.bw.movie.adapter.cinemaadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.adapter.filmadatper.stageadapter.FlimContentAdapter;
import com.bw.movie.bean.cinemabean.PopDetailsBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class PopDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<PopDetailsBean.ResultBean> mlist;
    Context mContext;

    public PopDetailsAdapter(Context context){
        mlist = new ArrayList<>();
        mContext = context;
    }

    public void setMlist(List<PopDetailsBean.ResultBean> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView icon;
        TextView name,title,data,num;
        ImageView mZan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.pop_d_item_icon);
            name = itemView.findViewById(R.id.pop_d_item_name);
            data = itemView.findViewById(R.id.pop_d_item_data);
            title = itemView.findViewById(R.id.pop_D_Item_title);
            num = itemView.findViewById(R.id.pop_d_item_num);
            mZan = itemView.findViewById(R.id.pop_d_item_zan);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_details_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {

        ViewHolder holder  = (ViewHolder) viewHolder;
        holder.icon.setImageURI(mlist.get(i).getCommentHeadPic());
        holder.name.setText(mlist.get(i).getCommentUserName());
        holder.title.setText(mlist.get(i).getHotComment()+"");
        long commentTime = mlist.get(i).getCommentTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String time = dateFormat.format(new Date(commentTime));
        holder.data.setText(time);
        holder.num.setText(mlist.get(i).getGreatNum()+"");


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
        }else{
            return 0;
        }
    }

    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void success(String id,boolean isGreat);
    }
}
