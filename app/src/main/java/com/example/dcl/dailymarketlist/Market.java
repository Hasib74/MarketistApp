package com.example.dcl.dailymarketlist;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.dcl.dailymarketlist.Utils.Common;


public class Market extends AppCompatActivity {


    public boolean  swipeEnabled=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.view);
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.beginFakeDrag();

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });

        /*ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(!swipeEnabled) {
                    if (viewPager.getAdapter().getCount()>1) {
                        viewPager.setCurrentItem(2);
                        viewPager.setCurrentItem(1);
                        viewPager.setCurrentItem(0);
                    }
                }
            }
            public void onPageScrollStateChanged(int state) {}
            public void onPageSelected(int position) {}
        };
        viewPager.addOnPageChangeListener(onPageChangeListener);*/




        TabLayout tabLayout=findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    /*    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/


    if (Common.value1==1){
        viewPager.setCurrentItem(1);
        Common.value1=0;
    }
    }
}
