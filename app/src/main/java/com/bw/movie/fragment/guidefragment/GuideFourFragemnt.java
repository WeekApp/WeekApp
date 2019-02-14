package com.bw.movie.fragment.guidefragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bw.movie.activity.homeactivity.GuideActivity;
import com.bw.movie.activity.homeactivity.HomeActivity;
import com.bw.movie.activity.useractivity.LoginActivity;
import com.bw.onlymycinema.R;

public class GuideFourFragemnt extends Fragment {

    RelativeLayout relativeLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        relativeLayout = getActivity().findViewById(R.id.jixu);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),HomeActivity.class));
                ((GuideActivity)getActivity()).finish();
            }
        });
    }
}
