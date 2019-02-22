package com.bw.movie.fragment.filmfragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.Utils.cinema.RequestCodeInfo;
import com.bw.movie.activity.filmactivity.DetailsActivity;
import com.bw.movie.activity.filmactivity.DetailsMoreActivity;
import com.bw.movie.activity.homeactivity.CityActivity;
import com.bw.movie.adapter.filmadatper.detailsadapter.FilmHotContentAdapter;
import com.bw.movie.adapter.filmadatper.detailsadapter.FilmIngContentAdapter;
import com.bw.movie.adapter.filmadatper.detailsadapter.FilmJijContentAdapter;
import com.bw.movie.adapter.filmadatper.detailsadapter.RecyclerCoverAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.CinemaLocationBean;
import com.bw.movie.bean.filmbean.FilmHotBean;
import com.bw.movie.bean.filmbean.FilmIngBean;
import com.bw.movie.bean.filmbean.FilmJijBean;
import com.bw.movie.bean.userbean.CityBean;
import com.bw.movie.fragment.cinemafragment.CinemaFragment;
import com.bw.movie.fragment.myfragment.MyFragment;
import com.bw.movie.mvp.util.Apis;
import com.bw.onlymycinema.R;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import recycler.coverflow.RecyclerCoverFlow;
/**
 * A simple {@link Fragment} subclass.
 *
 *  电影页面
 */
public class FilmFragment extends BaseFragment {

    RecyclerCoverFlow mContent;
    RecyclerCoverAdapter mCoverAdapter;
    RelativeLayout mRela;
    EditText mTsearch;
    RecyclerView mHotContents;
    RecyclerView mIngContents;
    RecyclerView mJijContents;
    FilmHotContentAdapter mFilmHotContentAdapter;
    FilmJijContentAdapter mFilmJijContentAdapter;
    FilmIngContentAdapter mFilmIngContentAdapter;
    ImageView mHotMore,mIngMore,mJijMore;
    private ImageView mLocationiv;
    private TextView mLocationtv;
    private String locationProvider;
    private double mLatitude;
    private double mLongitude;
    private String mCity1;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Bundle savedInstanceState;
    private String url;

    //初始化数据
    @Override
    protected void initData() {
        //开始网路请求
        startRequest();
        //适配器
        initAdapter();
        //设置布局管理器你
        initManager();
        //弹出搜索框
        initPopup();
        //点击进行跳转到详情
        initDetails();
        //点击箭头跳转到影片更多页面
        initMore();
        //获取经纬度
        initlongitude();

        //获取请求到的定位
        initLocation();
        //发送城市
        initSendCity();
    }


    private void initMore() {
        mHotMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),DetailsMoreActivity.class));
            }
        });
        mIngMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),DetailsMoreActivity.class));
            }
        });
        mJijMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),DetailsMoreActivity.class));
            }
        });
    }

    private void initDetails() {
        mFilmHotContentAdapter.setOnItemClick(new FilmHotContentAdapter.OnItemClick() {
            @Override
            public void success(String id) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        mFilmIngContentAdapter.setOnItemClick(new FilmIngContentAdapter.OnItemClick() {
            @Override
            public void success(String id) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        mFilmJijContentAdapter.setOnItemClick(new FilmJijContentAdapter.OnItemClick() {
            @Override
            public void success(String id) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        mCoverAdapter.setOnItemClick(new RecyclerCoverAdapter.OnItemClick() {
            @Override
            public void success(String id) {
                Intent intent = new Intent(getActivity(),DetailsActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        mLocationiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getContext(), CityActivity.class), RequestCodeInfo.GETCITY);
            }
        });
    }

    private void startRequest() {
        doNetGet(Apis.URL_GET_ING,FilmIngBean.class);
        doNetGet(Apis.URL_GET_BANNER,FilmHotBean.class);
        doNetGet(Apis.URL_GET_JIJ,FilmJijBean.class);
    }

    private void initManager() {
        LinearLayoutManager linearLayoutManagerHot = new LinearLayoutManager(getActivity());
        linearLayoutManagerHot.setOrientation(OrientationHelper.HORIZONTAL);
        mHotContents.setLayoutManager(linearLayoutManagerHot);

        LinearLayoutManager linearLayoutManagerIng = new LinearLayoutManager(getActivity());
        linearLayoutManagerIng.setOrientation(OrientationHelper.HORIZONTAL);
        mIngContents.setLayoutManager(linearLayoutManagerIng);


        LinearLayoutManager linearLayoutManagerJij = new LinearLayoutManager(getActivity());
        linearLayoutManagerJij.setOrientation(OrientationHelper.HORIZONTAL);
        mJijContents.setLayoutManager(linearLayoutManagerJij);
    }

    private void initPopup() {
        mRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    mRela.animate().xBy(-100).setDuration(300).start();
                    mTsearch.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initAdapter() {
        //画廊适配器
        mCoverAdapter = new RecyclerCoverAdapter(getActivity());
        mContent.setAdapter(mCoverAdapter);
        //热门电影
        mFilmHotContentAdapter = new FilmHotContentAdapter(getActivity());
        mHotContents.setAdapter(mFilmHotContentAdapter);

        //正在上映
        mFilmIngContentAdapter = new FilmIngContentAdapter(getActivity());
        mIngContents.setAdapter(mFilmIngContentAdapter);

        //即将上映
        mFilmJijContentAdapter = new FilmJijContentAdapter(getActivity());
        mJijContents.setAdapter(mFilmJijContentAdapter);
    }

    //初始化控件
    @Override
    protected void initView(View view) {

        mContent = view.findViewById(R.id.film_banner_icon);
        mRela = view.findViewById(R.id.mrelative);
        mTsearch = view.findViewById(R.id.film_edit_search);
        mHotContents = view.findViewById(R.id.flem_hot_contents);
        mIngContents = view.findViewById(R.id.flem_inf_contents);
        mJijContents = view.findViewById(R.id.flem_jij_contents);
        mHotMore = view.findViewById(R.id.flem_icon_next_hotmoive);
        mIngMore = view.findViewById(R.id.flem_icon_int_hotmoive);
        mJijMore = view.findViewById(R.id.flem_icon_jij_hotmoive);
        mLocationiv = view.findViewById(R.id.location_icon);
        mLocationtv = view.findViewById(R.id.location_tv);



    }

    //获取布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_film;
    }
    //请求成功
    @Override
    protected void netSuccess(Object data) {
        if(data instanceof FilmHotBean){
            FilmHotBean user = (FilmHotBean) data;
            if(user.getStatus().equals("0000")){
                mCoverAdapter.setMlist(user.getResult());
                mFilmHotContentAdapter.setMlist(user.getResult());
            }
        }
        if(data instanceof FilmIngBean){
            FilmIngBean user = (FilmIngBean) data;
            if(user.getStatus().equals("0000")){
                mFilmIngContentAdapter.setMlist(user.getResult());
            }
        }
        if(data instanceof FilmJijBean){
            FilmJijBean user = (FilmJijBean) data;
            if(user.getStatus().equals("0000")){
                mFilmJijContentAdapter.setMlist(user.getResult());
            }
        }
    }
    //请求失败
    @Override
    protected void netFail(Object data) {

    }

    //回调返回方法
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RequestCodeInfo.GETCITY:
                    String city=data.getExtras().getString("city");
                    if(city!= null) {
                        System.out.println("ccccccctttttt" + city);
                        mLocationtv.setText(city);
                    }
                    break;
            }
        }
    }

    private void initSendCity() {

    }


    //定位
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {

                mLocationtv.setText( mCity1);
            }
        }
    };
    //获取经纬度
    private void initlongitude() {
        LocationManager lm = (LocationManager)getContext(). getSystemService(Context.LOCATION_SERVICE);


        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);//低精度，如果设置为高精度，依然获取不了location。
        criteria.setAltitudeRequired(false);//不要求海拔
        criteria.setBearingRequired(false);//不要求方位
        criteria.setCostAllowed(true);//允许有花费
        criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗

        locationProvider = lm.getBestProvider(criteria, true);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("asd", "onCreate: 没有权限 ");
            return;
        }

        Location location = lm.getLastKnownLocation(locationProvider);

        if (location != null) {
            Log.d("a", "onCreate: location");
            //不为空,显示地理位置经纬度
            showLocation(location);
        }

        //监视地理位置变化
        lm.requestLocationUpdates(locationProvider, 0, 0, locationListener);


        /*Location location = LocationUtils.getInstance( getContext() ).showLocation();
        if (location != null) {
            String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
            System.out.println("精度: "+mLatitude+"");
            System.out.println("精度: "+mLongitude+"");
            Log.d( "FLY.LocationUtils", address );
            Toast.makeText(getContext(), mLatitude+" =qaz= "+mLongitude, Toast.LENGTH_SHORT).show();
        }*/
    }
    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("a", "onProviderEnabled: " + provider + ".." + Thread.currentThread().getName());
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("a", "onProviderDisabled: " + provider + ".." + Thread.currentThread().getName());
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.d("a", "onLocationChanged: " + ".." + Thread.currentThread().getName());
            //如果位置发生变化,重新显示
            showLocation(location);
        }

    };

    private void showLocation(Location location) {
//获取维度信息
        mLatitude = location.getLatitude();
        //获取经度信息
        mLongitude = location.getLongitude();

        Log.i("获取经纬度",  "  维度：" + mLatitude + "  经度：" + mLongitude);

    }
    //获取GPS定位的城市
    private void initLocation() {

        final MyAsyncExtue myAsyncExtue = new MyAsyncExtue();
        new Thread() {
            @Override
            public void run() {
                super.run();
                String s = myAsyncExtue.doInBackground();

                myAsyncExtue.onPostExecute(s);
            }
        }.start();
    }


    //获取当前定位的城市
    private class MyAsyncExtue extends AsyncTask<Location, Void, String> {
        @Override
        protected String doInBackground(Location... params) {


            HttpClient client = new DefaultHttpClient();
            StringBuilder stringBuilder = new StringBuilder();
            HttpGet httpGet = new HttpGet("http://api.map.baidu.com/geocoder?output=json&location="+ mLatitude +","+ mLongitude +"&ak=I3Bm5iocjMlbwGayEm1B3VXkWBmV9t76");
            try {
                HttpResponse response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String b;
                while ((b = bufferedReader.readLine()) != null) {
                    stringBuilder.append(b + "\n");
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String m_list) {
            super.onPostExecute(m_list);
            Log.e("str", m_list.toString());
            String city = "";
//                if (m_list != null && m_list.size() > 0) {
//                    city = m_list.get(0).getLocality();//获取城市
//                }
            city = m_list;
            CinemaLocationBean cinemaLocationBean = new Gson().fromJson(city, CinemaLocationBean.class);
            mCity1 = cinemaLocationBean.getResult().getAddressComponent().getCity();

            Message msg = new Message();

            msg.what = 0;
            handler.sendMessage(msg);

        }
    }

}
