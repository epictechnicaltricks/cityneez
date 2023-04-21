package com.serviceapp.new_design.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.serviceapp.R;
import com.serviceapp.fragements.Booking_Fragment;
import com.serviceapp.fragements.MyAccount_Fragment;
import com.serviceapp.fragements.Shop_now_Fragment;
import com.serviceapp.new_design.frags.New_Account_Frag;
import com.serviceapp.new_design.frags.New_Booking_Frag;
import com.serviceapp.new_design.frags.New_Category_Frag;
import com.serviceapp.new_design.frags.New_Coin_Frag;
import com.serviceapp.new_design.frags.New_Home_Frag;

public class LayoutActivity extends AppCompatActivity {

    private BottomNavigationView bottomnavigation1;
    private ViewPager viewpager1;

    DrawerLayout drawer;
    FrameLayout frame;
    NavigationView nav;
    ActionBarDrawerToggle toggle;



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

        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.nav);
        frame = findViewById(R.id.frame);

        Toolbar toolbar =  findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intent);


        nav.setNavigationItemSelectedListener(menuItem -> {


            return true;
        });


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
        bottomnavigation1.getMenu().add(0, 1, 0, "Shop").setIcon(R.drawable.shop);
        bottomnavigation1.getMenu().add(0, 2, 0, "Categories").setIcon(R.drawable.category);
        bottomnavigation1.getMenu().add(0, 3, 0, "Bookings").setIcon(R.drawable.book);
        bottomnavigation1.getMenu().add(0, 4, 0, "Account").setIcon(R.drawable.user);
    }

    public void openDrawer() {
        drawer.openDrawer(GravityCompat.START);
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
                return new Shop_now_Fragment();
            }
            if (_position22 == 2) {

                return new New_Category_Frag();
            }

            if (_position22 == 3) {

                return new Booking_Fragment();
            }

            if (_position22 == 4) {

                return new MyAccount_Fragment();
            }

            return null;
        }

    }





}