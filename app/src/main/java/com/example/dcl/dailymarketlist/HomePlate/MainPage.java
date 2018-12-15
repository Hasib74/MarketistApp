package com.example.dcl.dailymarketlist.HomePlate;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dcl.dailymarketlist.HomePlate.Fragments.Confirm;
import com.example.dcl.dailymarketlist.HomePlate.Fragments.Dashbord;
import com.example.dcl.dailymarketlist.HomePlate.Fragments.Home;
import com.example.dcl.dailymarketlist.HomePlate.Fragments.List;
import com.example.dcl.dailymarketlist.HomePlate.Fragments.Menu;
import com.example.dcl.dailymarketlist.R;

public class MainPage extends AppCompatActivity {

    BottomNavigationView main_bottom_navigation;

    String intent_value;

    RelativeLayout relativeLayoutHome;
    RelativeLayout relativeLayoutTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        main_bottom_navigation=findViewById(R.id.bottom_navigation_view);
        relativeLayoutHome=findViewById(R.id.relative_homePlate);
        relativeLayoutTest=findViewById(R.id.relative_list);
        intent_value=getIntent().getStringExtra("complete");

       /* if (getIntent().getStringExtra("visibility")!=null){
            relativeLayoutHome.setVisibility(View.GONE);
            relativeLayoutTest.setVisibility(View.VISIBLE);
        }*/

        Toast.makeText(this, ""+intent_value, Toast.LENGTH_SHORT).show();

        if (savedInstanceState == null){
            Fragment home_fragment=new Home();
            android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_view, home_fragment);
            fragmentTransaction.commit();
        }


        if (intent_value != null) {
            if (intent_value.equals("confirm")) {
                Fragment fragment = new Confirm();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_view, fragment);
                fragmentTransaction.commit();
                main_bottom_navigation.setSelectedItemId(R.id.confrom);
                intent_value = null;
            } else if (intent_value.equals("list")) {
                Fragment fragment = List.newInstance(2);
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_view, fragment);
                fragmentTransaction.commit();
                intent_value = null;
                main_bottom_navigation.setSelectedItemId(R.id.list);
            } else if (intent_value.equals("add")) {
                Fragment fragment = List.newInstance(2);
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_view, fragment);
                fragmentTransaction.commit();
                intent_value = null;
                main_bottom_navigation.setSelectedItemId(R.id.list);
            }else if (intent_value.equals("uncomplete")) {
                Fragment fragment = new List();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_view, fragment);
                fragmentTransaction.commit();
                main_bottom_navigation.setSelectedItemId(R.id.list);
                intent_value = null;
            }

            //add
            else {
                return;
            }
        }



        main_bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if (id==R.id.home){
                    Toast.makeText(MainPage.this, ""+intent_value, Toast.LENGTH_SHORT).show();
                    Fragment home_fragment=new Home();
                    android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_view, home_fragment);
                    fragmentTransaction.commit();
                }
                else  if (id==R.id.list){
                    Toast.makeText(MainPage.this, ""+intent_value, Toast.LENGTH_SHORT).show();

                    Fragment fragment=List.newInstance(2);
                    android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_view, fragment);
                    fragmentTransaction.commit();
                } else  if (id==R.id.confrom){
                    Toast.makeText(MainPage.this, ""+intent_value, Toast.LENGTH_SHORT).show();

                    Fragment fragment=new Confirm();
                    android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_view, fragment);
                    fragmentTransaction.commit();
                } else  if (id==R.id.dashbord){
                    Toast.makeText(MainPage.this, ""+intent_value, Toast.LENGTH_SHORT).show();

                    Fragment fragment=new Dashbord();
                    android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_view, fragment);
                    fragmentTransaction.commit();
                } else  if(id==R.id.menu){
                    Toast.makeText(MainPage.this, ""+intent_value, Toast.LENGTH_SHORT).show();

                    Fragment fragment=new Menu();
                    android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content_view, fragment);
                    fragmentTransaction.commit();
                }

                return true;
            }
        });





    }
}
