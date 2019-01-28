package com.bw.movie.adapter.myadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.bean.mybean.RemindBean;
import com.bw.onlymycinema.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * date:2019.1.27
 * author:赵颖冰(lenovo)
 * function:
 */
public class RemindAdapter extends RecyclerView.Adapter<RemindAdapter.ViewHolder> {
    Context mContext;
    List<RemindBean.ResultBean> list;

    public RemindAdapter(Context context) {
        mContext = context;
        list=new ArrayList<>();
    }
    public void setData(List<RemindBean.ResultBean> result) {
        list.addAll(result);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.remindfragment_recy_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String title = list.get(i).getTitle();
        String content = list.get(i).getContent();
        long pushTime = list.get(i).getPushTime();
        int status = list.get(i).getStatus();
        int id = list.get(i).getId();

        viewHolder.remind_item_title.setText(title);
        viewHolder.remind_item_content.setText(content);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String time = dateFormat.format(new Date(pushTime));

        viewHolder.remind_item_time.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView remind_item_title,remind_item_content,remind_item_time,remind_item_unreadremind;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            remind_item_title=itemView.findViewById(R.id.remind_item_title);
            remind_item_content=itemView.findViewById(R.id.remind_item_content);
            remind_item_time=itemView.findViewById(R.id.remind_item_time);
            remind_item_unreadremind=itemView.findViewById(R.id.remind_item_unreadremind);
        }
    }
}
