package com.bw.movie.activity.homeactivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bw.movie.bean.cinemabean.ToastUtil;
import com.bw.movie.fragment.cinemafragment.CinemaFragment;
import com.bw.movie.fragment.filmfragment.FilmFragment;
import com.bw.movie.fragment.myfragment.MyFragment;
import com.bw.movie.util.ToastUtils;
import com.bw.onlymycinema.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.homeactivity_viewpager)
    ViewPager homeactivityViewpager;
    @BindView(R.id.home_film)
    RadioButton homeFilm;
    @BindView(R.id.home_cinema)
    RadioButton homeCinema;
    @BindView(R.id.home_my)
    RadioButton homeMy;
    @BindView(R.id.home_group)
    RadioGroup homeGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        //全屏沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        final List<Fragment> list=new ArrayList<>();
        list.add(new FilmFragment());
        list.add(new CinemaFragment());
        list.add(new MyFragment());

        homeactivityViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new FilmFragment();
                    case 1:
                        return new CinemaFragment();
                    case 2:
                        return new MyFragment();
                    default:
                        return null;
                }
            }
            @Override
            public int getCount() {
                return 3;
            }
        });

        homeactivityViewpager.setCurrentItem(0);
        homeGroup.check(R.id.home_film);
    }

    //点击放大
    private void setAddAnimator(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.17f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.17f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(0);
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.start();
    }

    //点击缩小
    private void setCutAnimator(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(0);
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.start();
    }

    @OnClick({R.id.home_film, R.id.home_cinema, R.id.home_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_film:
                homeactivityViewpager.setCurrentItem(0);
                setAddAnimator(view);
                setCutAnimator(homeCinema);
                setCutAnimator(homeMy);
                break;
            case R.id.home_cinema:
                homeactivityViewpager.setCurrentItem(1);
                setAddAnimator(view);
                setCutAnimator(homeFilm);
                setCutAnimator(homeMy);
                break;
            case R.id.home_my:
                homeactivityViewpager.setCurrentItem(2);
                setAddAnimator(view);
                setCutAnimator(homeCinema);
                setCutAnimator(homeFilm);
                break;
            default:break;
        }
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.show(this, "再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        //拦截MENU按钮点击事件，让它无任何操作
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
