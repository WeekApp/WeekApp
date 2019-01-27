package com.bw.movie.fragment.cinemafragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.onlymycinema.R;


/**
 * A simple {@link Fragment} subclass.
 *
 *  影院详情里的详情
 */
public class CinemaDetailPopuDetailFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cinema_detail_popu_detail, container, false);
    }

}
