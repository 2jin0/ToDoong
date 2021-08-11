package com.jica.todochack.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.jica.todochack.R;


public class ThirdFragment extends Fragment {
    // 1 프래그먼트는 뷰 필수
    private View view;

    // 3 상태저장을 위함
    public static ThirdFragment newInstance() {
        ThirdFragment thirdFragment = new ThirdFragment();
        return thirdFragment;
    }



    // 2 Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_third, container, false);

        return view;
    }
}

