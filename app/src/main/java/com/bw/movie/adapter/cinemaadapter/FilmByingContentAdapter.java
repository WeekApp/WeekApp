package com.bw.movie.adapter.cinemaadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.bean.cinemabean.FilmByingBean;
import com.bw.onlymycinema.R;

import java.util.ArrayList;
import java.util.List;

public class FilmByingContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<FilmByingBean.ResultBean> mlist;
    Context mContext;

    public FilmByingContentAdapter(Context context){
        mlist = new ArrayList<>();
        mContext = context;
    }

    public void setMlist(List<FilmByingBean.ResultBean> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,start,end,da;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.cinemadetail_schedule_cinemaname);
            start = itemView.findViewById(R.id.cinemadetail_schedule_cinemastarttime);
            end = itemView.findViewById(R.id.cinemadetail_schedule_cinemaendtime);
            da = itemView.findViewById(R.id.cinemadetail_schedule_cinemapriceint);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.film_buy_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        holder.start.setText(mlist.get(i).getBeginTime());
        holder.name.setText(mlist.get(i).getScreeningHall());
        holder.da.setText("0");
        holder.end.setText(mlist.get(i).getEndTime());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
