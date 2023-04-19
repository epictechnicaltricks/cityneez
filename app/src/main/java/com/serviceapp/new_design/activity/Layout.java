package com.serviceapp.new_design.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.serviceapp.R;
import com.serviceapp.activity.HomeActivity;
import com.serviceapp.fragements.Booking_Fragment;
import com.serviceapp.fragements.Home_Fragment;
import com.serviceapp.fragements.MyAccount_Fragment;
import com.serviceapp.fragements.Shop_now_Fragment;
import com.serviceapp.new_design.frags.New_Account_Frag;
import com.serviceapp.new_design.frags.New_Booking_Frag;
import com.serviceapp.new_design.frags.New_Category_Frag;
import com.serviceapp.new_design.frags.New_Coin_Frag;
import com.serviceapp.new_design.frags.New_Home_Frag;

public class Layout extends AppCompatActivity {

    private BottomNavigationView bottomnavigation1;
    private ViewPager viewpager1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_layout);


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


        viewpager1.setAdapter(new MyFragmentAdapter(getApplicationContext(), getSupportFragmentManager(), 5));
        bottomnavigation1.getMenu().add(0, 0, 0, "Home").setIcon(R.drawable.home);
        bottomnavigation1.getMenu().add(0, 1, 0, "Coins").setIcon(R.drawable.coin);
        bottomnavigation1.getMenu().add(0, 2, 0, "Categories").setIcon(R.drawable.category);
        bottomnavigation1.getMenu().add(0, 3, 0, "Bookings").setIcon(R.drawable.book);
        bottomnavigation1.getMenu().add(0, 4, 0, "Account").setIcon(R.drawable.user);
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

        @NonNull
        @Override
        public Fragment getItem(int _position22) {
            if (_position22 == 0) {
                return new New_Home_Frag();
            }
            if (_position22 == 1) {
                return new New_Coin_Frag();
            }
            if (_position22 == 2) {

                return new New_Category_Frag();
            }

            if (_position22 == 3) {

                return new New_Booking_Frag();
            }

            if (_position22 == 4) {

                return new New_Account_Frag();
            }

            return null;
        }

    }





}