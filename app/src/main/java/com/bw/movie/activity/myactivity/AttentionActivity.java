package com.bw.movie.activity.myactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.base.BaseActivity;
import com.bw.movie.fragment.myfragment.AttentionCinemaFragment;
import com.bw.movie.fragment.myfragment.AttentionFilmFragment;
import com.bw.onlymycinema.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttentionActivity extends BaseActivity {

    @BindView(R.id.attention_radio_film)
    RadioButton attentionRadioFilm;
    @BindView(R.id.attention_radio_cinema)
    RadioButton attentionRadioCinema;
    @BindView(R.id.attention_group)
    RadioGroup attentionGroup;
    @BindView(R.id.attention_vp)
    ViewPager attentionVp;
    @BindView(R.id.linear)
    LinearLayout linear;
    //初始化数据
    @Override
    protected void initData() {

        final List<Fragment> list=new ArrayList<>();
        list.add(new AttentionFilmFragment());
        list.add(new AttentionCinemaFragment());

        attentionVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        attentionVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        attentionGroup.check(R.id.attention_radio_film);
                        break;
                    case 1:
                        attentionGroup.check(R.id.attention_radio_cinema);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        attentionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.attention_radio_film:
                        attentionVp.setCurrentItem(0);
                        break;
                    case R.id.attention_radio_cinema:
                        attentionVp.setCurrentItem(1);
                        break;
                }
            }
        });

    }



    //初始化控件
    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }
    //返回布局
    @Override
    protected int getLayout() {
        return R.layout.activity_attention;
    }
    //请求成功
    @Override
    protected void netSuccess(Object data) {

    }
    //请求失败
    @Override
    protected void netFail(Object data) {

    }
}
