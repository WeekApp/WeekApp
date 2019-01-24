package com.example.movie.activity.homeactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.movie.fragment.cinemafragment.CinemaFragment;
import com.example.movie.fragment.filmfragment.FilmFragment;
import com.example.movie.fragment.myfragment.MyFragment;
import com.example.onlymycinema.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.homeactivity_viewpager)
    ViewPager homeactivityViewpager;
    @BindView(R.id.homeactivity_radiobutton_film)
    RadioButton homeactivityRadiobuttonFilm;
    @BindView(R.id.homeactivity_radiobutton_filmTwo)
    RadioButton homeactivityRadiobuttonFilmTwo;
    @BindView(R.id.homeactivity_radiobutton_cinema)
    RadioButton homeactivityRadiobuttonCinema;
    @BindView(R.id.homeactivity_radiobutton_cinemaTwo)
    RadioButton homeactivityRadiobuttonCinemaTwo;
    @BindView(R.id.homeactivity_radiobutton_my)
    RadioButton homeactivityRadiobuttonMy;
    @BindView(R.id.homeactivity_radiobutton_myTwo)
    RadioButton homeactivityRadiobuttonMyTwo;
    @BindView(R.id.homeactivity_radiogroup)
    RadioGroup homeactivityRadiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        final List<Fragment> list=new ArrayList<>();
        list.add(new FilmFragment());
        list.add(new CinemaFragment());
        list.add(new MyFragment());

        homeactivityViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        homeactivityViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        homeactivityRadiogroup.check(R.id.homeactivity_radiobutton_film);
                        break;
                    case 1:
                        homeactivityRadiogroup.check(R.id.homeactivity_radiobutton_cinema);
                        break;
                    case 2:
                        homeactivityRadiogroup.check(R.id.homeactivity_radiobutton_my);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        homeactivityRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.homeactivity_radiobutton_film:
                        homeactivityViewpager.setCurrentItem(0);
                        homeactivityRadiobuttonFilm.setVisibility(View.GONE);
                        homeactivityRadiobuttonFilmTwo.setVisibility(View.VISIBLE);

                        homeactivityRadiobuttonFilm.setBackgroundResource(R.mipmap.com_icon_film_selected);
                        homeactivityRadiobuttonFilmTwo.setBackgroundResource(R.mipmap.com_icon_film_selected);
                        homeactivityRadiobuttonCinema.setBackgroundResource(R.mipmap.com_icon_cinema_default);
                        homeactivityRadiobuttonCinemaTwo.setBackgroundResource(R.mipmap.com_icon_cinema_default);
                        homeactivityRadiobuttonMy.setBackgroundResource(R.mipmap.com_icon_my_default);
                        homeactivityRadiobuttonMyTwo.setBackgroundResource(R.mipmap.com_icon_my_default);
                        break;
                    case R.id.homeactivity_radiobutton_cinema:
                        homeactivityViewpager.setCurrentItem(1);

                        homeactivityRadiobuttonCinema.setVisibility(View.GONE);
                        homeactivityRadiobuttonCinemaTwo.setVisibility(View.VISIBLE);

                        homeactivityRadiobuttonFilm.setBackgroundResource(R.mipmap.com_icon_film_fault);
                        homeactivityRadiobuttonFilmTwo.setBackgroundResource(R.mipmap.com_icon_film_fault);
                        homeactivityRadiobuttonCinema.setBackgroundResource(R.mipmap.com_icon_cinema_selected);
                        homeactivityRadiobuttonCinemaTwo.setBackgroundResource(R.mipmap.com_icon_cinema_selected);
                        homeactivityRadiobuttonMy.setBackgroundResource(R.mipmap.com_icon_my_default);
                        homeactivityRadiobuttonMyTwo.setBackgroundResource(R.mipmap.com_icon_my_default);
                        break;
                    case R.id.homeactivity_radiobutton_my:
                        homeactivityViewpager.setCurrentItem(2);


                        homeactivityRadiobuttonMy.setVisibility(View.GONE);
                        homeactivityRadiobuttonMyTwo.setVisibility(View.VISIBLE);
                        homeactivityRadiobuttonFilm.setBackgroundResource(R.mipmap.com_icon_film_fault);
                        homeactivityRadiobuttonFilmTwo.setBackgroundResource(R.mipmap.com_icon_film_fault);
                        homeactivityRadiobuttonCinema.setBackgroundResource(R.mipmap.com_icon_cinema_default);
                        homeactivityRadiobuttonCinemaTwo.setBackgroundResource(R.mipmap.com_icon_cinema_default);
                        homeactivityRadiobuttonMy.setBackgroundResource(R.mipmap.com_icon_my_selected);
                        homeactivityRadiobuttonMyTwo.setBackgroundResource(R.mipmap.com_icon_my_selected);
                        break;
                }
            }
        });
    }
}
