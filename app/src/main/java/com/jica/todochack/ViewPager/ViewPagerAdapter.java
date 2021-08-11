package com.jica.todochack.ViewPager;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jica.todochack.CalendarActivity;
import com.jica.todochack.MainActivity;
import com.jica.todochack.ViewPagerActivity;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    // 프래그먼트를 보여주는 처리를 구현하는 곳
    @NonNull
    @Override
    public Fragment getItem(int position) {



        switch (position) {
            case 0 : return FirstFragment.newInstance();
            case 1 : return SecondFragment.newInstance();
            case 2 : return ThirdFragment.newInstance();

            default:return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }


}
