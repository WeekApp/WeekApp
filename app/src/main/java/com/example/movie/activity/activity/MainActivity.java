package com.example.movie.activity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.onlymycinema.R;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_viewpager_carousel)
    ViewPager mPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mian);
    }
}
