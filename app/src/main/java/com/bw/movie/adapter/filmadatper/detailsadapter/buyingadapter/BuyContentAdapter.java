package com.bw.movie.adapter.filmadatper.detailsadapter.buyingadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.bean.cinemabean.RemmondBean;
import com.bw.movie.bean.filmbean.details.buyingbean.BuyBean;
import com.bw.onlymycinema.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class BuyContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<RemmondBean.Result> mlsit;
    Context mContext;

    public BuyContentAdapter(Context context){
        mlsit = new ArrayList<>();
        mContext = context;
    }

    public void setMlsit(List<RemmondBean.Result> lsit) {
        this.mlsit = lsit;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView icon;
        TextView name,title,mi;
        ImageView xin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.but_item_icon);
            name = itemView.findViewById(R.id.but_item_name);
            title =itemView.findViewById(R.id.but_item_title);
            mi = itemView.findViewById(R.id.but_item_mi);
            xin = itemView.findViewById(R.id.but_cb_xin);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.buy_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder,final int i) {

        ViewHolder holder = (ViewHolder) viewHolder;
        holder.icon.setImageURI(mlsit.get(i).getLogo());
        holder.name.setText(mlsit.get(i).getName());
        holder.title.setText(mlsit.get(i).getAddress());
        int distance = mlsit.get(i).getDistance();

        double v = Math.round(distance / 100d) / 10d;
        holder.mi.setText(v+"km");

        if(mlsit.get(i).getFollowCinema()==1){
            holder.xin.setBackgroundResource(R.drawable.com_icon_collection_selected);
        }else{
            holder.xin.setBackgroundResource(R.drawable.com_icon_collection_default);
        }

       holder.xin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(mlsit.get(i).getFollowCinema()==1){
                   mlsit.get(i).setFollowCinema(2);
               }else{
                   mlsit.get(i).setFollowCinema(1);
               }
               onItemClick.success(mlsit.get(i).getId()+"",mlsit.get(i).getFollowCinema()==1);
               notifyItemChanged(i);
           }
       });

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                dianJi.success(mlsit.get(i).getId()+"",mlsit.get(i).getName(),mlsit.get(i).getAddress());
           }
       });
    }

    @Override
    public int getItemCount() {
        if(mlsit!=null){
            return mlsit.size();
        }
        return 0;
    }

    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void success(String id,boolean is);
    }

    DianJi dianJi;

    public void setDianJi(DianJi dianJi) {
        this.dianJi = dianJi;
    }

    public interface DianJi{
        void success(String id,String name,String address);
   }
}
