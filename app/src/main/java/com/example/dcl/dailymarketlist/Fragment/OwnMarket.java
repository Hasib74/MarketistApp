package com.example.dcl.dailymarketlist.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.R;
import com.example.dcl.dailymarketlist.Utils.Common;

public class OwnMarket extends Fragment {

    public OwnMarket() {
    }

    MenuItem prevMenuItem;
    public boolean swipeEnabled;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_a,null);
        final ViewPager viewPager =v.findViewById(R.id.viewpager_a);
        final BottomNavigationView bottomNavigationView=v.findViewById(R.id.bottom_navigation);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                    bottomNavigationView.getMenu().getItem(i).setChecked(true);
                    prevMenuItem = bottomNavigationView.getMenu().getItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.android:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_slideshow:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_manage:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });



        setupViewPager(viewPager);





        return v;
    }



    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getFragmentManager());
        viewPager.setAdapter(adapter);
        if (Common.value==1){
            viewPager.setCurrentItem(1);
            Common.value=0;
        }


    }


}
