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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        String title = list.get(i).getTitle();
        String content = list.get(i).getContent();
        long pushTime = list.get(i).getPushTime();
        final int status = list.get(i).getStatus();
        final int id = list.get(i).getId();

        viewHolder.remind_item_title.setText(title);
        viewHolder.remind_item_content.setText(content);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String time = dateFormat.format(new Date(pushTime));

        viewHolder.remind_item_time.setText(time);

        if (status==0){
            viewHolder.remind_item_unreadremind.setVisibility(View.VISIBLE);
        }else if (status==1){
            viewHolder.remind_item_unreadremind.setVisibility(View.GONE);
        }

        viewHolder.remind_item_unreadremind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status==0){
                    list.get(i).setStatus(1);
                    mGetData.onClick(id);
                    notifyDataSetChanged();
                }


            }
        });
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
    public interface GetData{
        void onClick(int id);
    }
    private GetData mGetData;

    public void setGetData(GetData getData) {
        mGetData = getData;
    }
}
