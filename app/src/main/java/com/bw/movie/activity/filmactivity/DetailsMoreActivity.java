package com.bw.movie.activity.filmactivity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bw.movie.base.BaseActivity;
import com.bw.movie.fragment.filmfragment.filmmorefragment.HotFragment;
import com.bw.movie.fragment.filmfragment.filmmorefragment.IngFragment;
import com.bw.movie.fragment.filmfragment.filmmorefragment.JijFragment;
import com.bw.onlymycinema.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsMoreActivity extends BaseActivity {

    @BindView(R.id.d_more_group)
    RadioGroup mGroup;
    @BindView(R.id.d_more_pager)
    ViewPager mPager;
    @BindView(R.id.details_more_black)
    ImageView mBlack;
    List<Fragment> mlist;
    @Override
    protected void initData() {

        //实例化
        mlist = new ArrayList<>();
        mlist.add(new HotFragment());
        mlist.add(new IngFragment());
        mlist.add(new JijFragment());
        //适配器
        initAdapter();
        //切换
        initPager();
        //推出当前页面
        initBlack();
    }

    private void initBlack() {
        mBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initPager() {

        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.d_more_but_hot:
                        mPager.setCurrentItem(0);
                        break;
                    case R.id.d_more_but_ing:
                        mPager.setCurrentItem(1);
                        break;
                    case R.id.d_more_but_jij:
                        mPager.setCurrentItem(2);
                        break;
                }
            }
        });

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        mGroup.check(R.id.d_more_but_hot);
                        break;
                    case 1:
                        mGroup.check(R.id.d_more_but_ing);
                        break;
                    case 2:
                        mGroup.check(R.id.d_more_but_jij);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    private void initAdapter() {
        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mlist.get(i);
            }

            @Override
            public int getCount() {
                return mlist.size();
            }
        });
    }

    @Override
    protected void initView() {
        //使用ButterKnife绑定控件
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.details_more_activity;
    }

    @Override
    protected void netSuccess(Object data) {

    }

    @Override
    protected void netFail(Object data) {

    }
}
