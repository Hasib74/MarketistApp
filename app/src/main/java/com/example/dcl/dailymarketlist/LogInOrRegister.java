package com.example.dcl.dailymarketlist;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dcl.dailymarketlist.Adepter.SliderAdapter;

public class LogInOrRegister extends AppCompatActivity {


    private ViewPager viewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button signInBtn,signUpBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_or_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Button btnregistation=findViewById(R.id.Registation);
        init();

        onClick();


   /*     btnregistation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInOrRegister.this,MainActivity.class));
            }
        });*/
    }

    private void onClick()
    {
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInOrRegister.this,LoginActivity.class));

                // finish();
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LogInOrRegister.this,MainActivity.class));
                // finish();
            }
        });
    }

    private void init()
    {
        signInBtn = findViewById(R.id.signInBtnId);
        signUpBtn = findViewById(R.id.singUpBtnId);
        viewPager = findViewById(R.id.viewPage);
        mDotLayout = findViewById(R.id.linearLayout);
        // Theme_one = findViewById(R.id.singInBTNId);
        // Theme_two = findViewById(R.id.singUpBTNId);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDotsIndicator(1);
        viewPager.addOnPageChangeListener(viewListener);

    }



    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int i)
        {

        }
    };



    public void addDotsIndicator(int postion)
    {
        // mDots =null;
        mDots = new TextView[4];
        mDotLayout.removeAllViews();
        for(int i = 0 ; i<mDots.length ; i++)
        {
            mDots[i]  = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorWhite));
            mDotLayout.addView(mDots[i]);
        }

        if(mDots.length >0)
        {
            mDots[postion].setTextColor(getResources().getColor(R.color.colorGreen));
        }

    }


}
