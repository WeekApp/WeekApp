package com.bw.movie.activity.homeactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.bw.movie.fragment.guidefragment.GuideFourFragemnt;
import com.bw.movie.fragment.guidefragment.GuideOneFragment;
import com.bw.movie.fragment.guidefragment.GuideThreeFragment;
import com.bw.movie.fragment.guidefragment.GuideTwoFragment;
import com.bw.onlymycinema.R;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity {

    List<Fragment> mlist;
    @BindView(R.id.guide_pager)
    ViewPager mPager;
    @BindView(R.id.guide_group)
    RadioGroup mGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide);

        ButterKnife.bind(this);
        //
        initData();

        initAdapter();
        //
        initPager();
    }

    private void initPager() {

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

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        mGroup.check(R.id.g_but_one);
                        break;
                    case 1:
                        mGroup.check(R.id.g_but_two);
                        break;
                    case 2:
                        mGroup.check(R.id.g_but_three);
                        break;
                    case 3:
                        mGroup.check(R.id.g_but_four);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.g_but_one:
                        mPager.setCurrentItem(0);
                        break;
                    case R.id.g_but_two:
                        mPager.setCurrentItem(1);
                        break;
                    case R.id.g_but_three:
                        mPager.setCurrentItem(2);
                        break;
                    case R.id.g_but_four:
                        mPager.setCurrentItem(3);
                        break;
                }
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

    private void initData() {

        mlist = new ArrayList<>();
        mlist.add(new GuideOneFragment());
        mlist.add(new GuideTwoFragment());
        mlist.add(new GuideThreeFragment());
        mlist.add(new GuideFourFragemnt());
    }
}
