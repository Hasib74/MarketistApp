package com.example.dcl.dailymarketlist.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dcl.dailymarketlist.R;

public class Send extends Fragment {

    public Send() {
    }

    MenuItem prevMenuItem;
    //public boolean swipeEnabled;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_b,null);
        final ViewPager viewPager =v.findViewById(R.id.viewpager_b);
        final BottomNavigationView bottomNavigationView=v.findViewById(R.id.bottom_navigation_b);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
               if (prevMenuItem!=null){
                   prevMenuItem.setCheckable(false);
               }else{
                   bottomNavigationView.getMenu().getItem(0).setChecked(false);
                   bottomNavigationView.getMenu().getItem(i).setChecked(true);
                   prevMenuItem = bottomNavigationView.getMenu().getItem(i);
               }

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
                    }
                    return true;

            }
        });

        setUpView(viewPager);

        return v;
    }

    public  void  setUpView(ViewPager viewPager){
        SenderViewPager senderViewPager=new SenderViewPager(this.getFragmentManager());
        viewPager.setAdapter(senderViewPager);

    }
}
