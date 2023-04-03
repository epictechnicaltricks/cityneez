package com.serviceapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serviceapp.R;
import com.serviceapp.RequestNetwork;
import com.serviceapp.RequestNetworkController;
import com.serviceapp.Util;
import com.serviceapp.fragements.Booking_Fragment;
import com.serviceapp.fragements.Home_Fragment;
import com.serviceapp.fragements.MyAccount_Fragment;
import com.serviceapp.fragements.Shop_now_Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomnavigation1;
    private ViewPager viewpager1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        initialize();

        Logic();




    }


    private void initialize() {

        viewpager1 =  findViewById(R.id.viewpager1);
        bottomnavigation1 = findViewById(R.id.bottomnavigation1);




    }


    private void Logic() {




        viewpager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int _position) {
                bottomnavigation1.getMenu().getItem(_position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomnavigation1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                final int _itemId = item.getItemId();
                try {
                    viewpager1.setCurrentItem((int)_itemId);
                }catch (Exception e) {}
                return true;
            }
        });


        viewpager1.setAdapter(new MyFragmentAdapter(getApplicationContext(), getSupportFragmentManager(), 4));
        bottomnavigation1.getMenu().add(0, 0, 0, "Home").setIcon(R.drawable.home);
        bottomnavigation1.getMenu().add(0, 1, 0, "My Bookings").setIcon(R.drawable.book);
        bottomnavigation1.getMenu().add(0, 2, 0, "My Shop").setIcon(R.drawable.shop);
       bottomnavigation1.getMenu().add(0, 3, 0, "My Account").setIcon(R.drawable.user);
       //bottomnavigation1.getMenu().add(0, 4, 0, "Account").setIcon(R.drawable.user2);
    }



    public static class MyFragmentAdapter extends FragmentStatePagerAdapter {
        Context context;
        int tabCount;

        public MyFragmentAdapter(Context context, FragmentManager fm, int tabCount) {
            super(fm);
            this.context = context;
            this.tabCount = tabCount;
        }

        @Override
        public int getCount(){
            return tabCount;
        }

        @Override
        public CharSequence getPageTitle(int _position) {

            return null;
        }

        @Override
        public Fragment getItem(int _position22) {
            if (_position22 == 0) {
                return new Home_Fragment();
            }
            if (_position22 == 1) {
                return new Booking_Fragment();
            }
            if (_position22 == 2) {

                return new Shop_now_Fragment();
            }

            if (_position22 == 3) {

                return new MyAccount_Fragment();
            }

            return null;
        }

    }






}