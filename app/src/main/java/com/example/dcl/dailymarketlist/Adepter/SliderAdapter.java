package com.example.dcl.dailymarketlist.Adepter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dcl.dailymarketlist.R;


public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context)
    {
        this.context  = context;
    }

    public int[] slide_images =
            {
                    R.drawable.slide_show_1,
                    R.drawable.slide_show_2,
                    R.drawable.slide_show_3,
                    R.drawable.slide_show_4,
                    //R.drawable.more,

            };





    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view ==(RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

       // ImageView slideLogloImageView = view.findViewById(R.id.imageSliderLogoId);
        ImageView slideShowImageView = view.findViewById(R.id.imageSliderShowId);

        slideShowImageView.setImageResource(slide_images[position]);
     //   slideLogloImageView.setImageResource(R.drawable.slider_logo);


        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //  super.destroyItem(container, position, object);
        container.removeView((RelativeLayout) object);
    }
}
