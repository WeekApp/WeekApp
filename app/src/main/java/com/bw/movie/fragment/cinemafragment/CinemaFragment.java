package com.bw.movie.fragment.cinemafragment;


import android.Manifest;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.activity.homeactivity.CityActivity;
import com.bw.movie.activity.homeactivity.MainActivity;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.bean.cinemabean.CinemaLocationBean;
import com.bw.movie.util.LocationUtils;
import com.bw.movie.Utils.cinema.RequestCodeInfo;
import com.bw.onlymycinema.R;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass
 * <p>
 * 影院页面.
 */
public class CinemaFragment extends BaseFragment {


    @BindView(R.id.cinemaFragment_image_location)
    ImageView cinemaFragmentImageLocation;
    @BindView(R.id.cinemaFragment_tv_location)
    TextView cinemaFragmentTvLocation;
    @BindView(R.id.cinemaFragment_edit_search)
    EditText cinemaFragmentEditSearch;
    @BindView(R.id.cinemaFragment_tv_search)
    TextView cinemaFragmentTvSearch;
    @BindView(R.id.cinemaFragment_radio_recommend)
    RadioButton cinemaFragmentRadioRecommend;
    @BindView(R.id.cinemaFragment_radio_nearby)
    RadioButton cinemaFragmentRadioNearby;
    @BindView(R.id.cinemaFragment_group)
    RadioGroup cinemaFragmentGroup;
    @BindView(R.id.cinemaFragment_vp)
    ViewPager cinemaFragmentVp;
    Unbinder unbinder;
    private String mCity1;
    private LocationManager locationManager;
    private String locationProvider;
    private double mLatitude;
    private double mLongitude;
    private LocationManager mLoctionManager;
    private boolean isGpsEnabled;
    private String locateType;


    //初始化控件
    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        final List<Fragment> lists = new ArrayList();
        lists.add(new RecommendFragment());
        lists.add(new NearlyFragment());
        cinemaFragmentVp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return lists.get(i);
            }

            @Override
            public int getCount() {
                return lists.size();
            }
        });


        cinemaFragmentVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        cinemaFragmentGroup.check(R.id.cinemaFragment_radio_recommend);
                        break;
                    case 1:
                        cinemaFragmentGroup.check(R.id.cinemaFragment_radio_nearby);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        cinemaFragmentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cinemaFragment_radio_recommend:
                        cinemaFragmentVp.setCurrentItem(0);
                        break;
                    case R.id.cinemaFragment_radio_nearby:
                        cinemaFragmentVp.setCurrentItem(1);
                        break;
                }
            }
        });

        //获取定位动态权限
        applypermission();

        //获取经纬度
        initlongitude();

        //获取请求到的定位
        initLocation();

    }

    //动态权限
    private void applypermission() {
        if(Build.VERSION.SDK_INT>=23){
            //检查是否已经给了权限
            int checkpermission= ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION);
            if(checkpermission!=PackageManager.PERMISSION_GRANTED){//没有给权限
                Log.e("permission","动态申请");
                //参数分别是当前活动，权限字符串数组，requestcode
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //grantResults数组与权限字符串数组对应，里面存放权限申请结果
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getContext(),"已授权",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(),"拒绝授权",Toast.LENGTH_SHORT).show();
        }
    }


    //获取经纬度
    private void initlongitude() {
        Location location = LocationUtils.getInstance( getContext() ).showLocation();
        if (location != null) {
            String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
            System.out.println("精度: "+mLatitude+"");
            System.out.println("精度: "+mLongitude+"");
            Log.d( "FLY.LocationUtils", address );

        }

    }


    //请求数据
    @Override
    protected void initData() {


    }

    //
    @Override
    protected int getLayout() {
        return R.layout.fragment_cinema;
    }

    @Override
    protected void netSuccess(Object data) {

    }

    @Override
    protected void netFail(Object data) {

    }


    //点击事件
    @OnClick({R.id.cinemaFragment_image_location, R.id.cinemaFragment_edit_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cinemaFragment_image_location:
                startActivityForResult(new Intent(getContext(),CityActivity.class),RequestCodeInfo.GETCITY);
                break;
            case R.id.cinemaFragment_edit_search:
                ValueAnimator valueAnimator = ValueAnimator.ofInt(cinemaFragmentEditSearch.getLayoutParams().width, 200);
                valueAnimator.setDuration(2000);

                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {

                        int currentValue = (Integer) animator.getAnimatedValue();

                        cinemaFragmentEditSearch.getLayoutParams().width = currentValue;
                        cinemaFragmentEditSearch.requestLayout();

                    }
                });
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        cinemaFragmentEditSearch.setHint("CGV影城");
                        cinemaFragmentTvSearch.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                valueAnimator.start();


                break;
        }
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                cinemaFragmentTvLocation.setText("城市:" + mCity1);
            }
        }
    };
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
            HttpGet httpGet = new HttpGet("http://api.map.baidu.com/geocoder?output=json&location="+mLatitude+","+mLongitude+"&ak=I3Bm5iocjMlbwGayEm1B3VXkWBmV9t76");
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


    //回调返回方法
    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case RequestCodeInfo.GETCITY:
                    String city=data.getExtras().getString("city");
                    if(city!= null) {
                        System.out.println("ccccccctttttt" + city);
                        cinemaFragmentTvLocation.setText(city);
                    }
                    break;
            }
        }
    }

    //销毁布局
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    //销毁


    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance( getContext()).removeLocationUpdatesListener();
    }
}
