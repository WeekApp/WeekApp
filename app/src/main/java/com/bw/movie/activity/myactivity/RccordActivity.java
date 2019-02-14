package com.bw.movie.activity.myactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.base.BaseActivity;
import com.bw.movie.fragment.myfragment.AttentionCinemaFragment;
import com.bw.movie.fragment.myfragment.AttentionFilmFragment;
import com.bw.movie.fragment.myfragment.RccordFinishFragment;
import com.bw.movie.fragment.myfragment.RccordMoneyFragment;
import com.bw.onlymycinema.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RccordActivity extends BaseActivity {

    @BindView(R.id.rccord_radio_film)
    RadioButton rccordRadioFilm;
    @BindView(R.id.rccord_radio_cinema)
    RadioButton rccordRadioCinema;
    @BindView(R.id.rccord_group)
    RadioGroup rccordGroup;
    @BindView(R.id.rccord_vp)
    ViewPager rccordVp;
    @BindView(R.id.linear)
    LinearLayout linear;
    //初始化数据
    @Override
    protected void initData() {

        final List<Fragment> list=new ArrayList<>();
        list.add(new RccordMoneyFragment());
        list.add(new RccordFinishFragment());

        rccordVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        rccordVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        rccordGroup.check(R.id.rccord_radio_film);
                        break;
                    case 1:
                        rccordGroup.check(R.id.rccord_radio_cinema);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        rccordGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rccord_radio_film:
                        rccordVp.setCurrentItem(0);
                        break;
                    case R.id.rccord_radio_cinema:
                        rccordVp.setCurrentItem(1);
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
        return R.layout.activity_rccord;
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
