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
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.serviceapp.R;
import com.serviceapp.activity.Login;
import com.serviceapp.fragements.Booking_Fragment;
import com.serviceapp.fragements.MyAccount_Fragment;
import com.serviceapp.fragements.Shop_now_Fragment;

import com.serviceapp.new_design.frags.New_Category_Frag;
import com.serviceapp.new_design.frags.New_Home_Frag;

public class MainActivityhome extends AppCompatActivity {
    NavigationView nav;
    DrawerLayout drawer;
    FrameLayout frame;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    ImageView menu1;
    private BottomNavigationView bottomnavigation1;
    private ViewPager viewpager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activityhome);

        viewpager1 =  findViewById(R.id.viewpager1);
        bottomnavigation1 = findViewById(R.id.bottomnavigation1);
        nav = findViewById(R.id.nav2);
        drawer = findViewById(R.id.drawer2);
        frame = findViewById(R.id.frame);
        toolbar = findViewById(R.id.toolbar2);
        toolbar.setTitle("Cityneedz");
        setSupportActionBar(toolbar);
//        menu1 = findViewById(R.id.menu1);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

       // getSupportFragmentManager().beginTransaction().add(R.id.frame, new New_Home_Frag(), null).addToBackStack(null).commit();


//        nav.getMenu().add(0, 0, 0, "Home").setIcon(R.drawable.home);
//        nav.getMenu().add(0, 1, 0, "My Account").setIcon(R.drawable.profile);
//        nav.getMenu().add(0, 2, 0, "My Booking").setIcon(R.drawable.service);
//        nav.getMenu().add(0, 3, 0, "Notification").setIcon(R.drawable.baseline_notifications_24);
//        nav.getMenu().add(0, 4, 0, "Help &amp; Support").setIcon(R.drawable.support);
//        nav.getMenu().add(0, 5, 0, "Logout").setIcon(R.drawable.logout);

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                final int _itemId = item.getItemId();
                try {
                    viewpager1.setCurrentItem((int)_itemId);
                }catch (Exception e) {}
                return true;

            }
        });




        nav.setNavigationItemSelectedListener(menuIem ->{
            switch (menuIem.getItemId()) {
                case R.id.home:
//                    Intent intent = new Intent(this, New_Home_Frag.class);
//                    startActivity(intent);
                    viewpager1.setCurrentItem(0);
                //  getSupportFragmentManager().beginTransaction().replace(R.id.frame, new  New_Home_Frag(), null).addToBackStack(null).commit();
                    drawer.closeDrawer(GravityCompat.START);
                    break;


              /*
              case R.id.logout:

                    Intent intent1 = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent1);
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                    */

                case R.id.profile:
                    viewpager1.setCurrentItem(4);
                  // getSupportFragmentManager().beginTransaction().replace(R.id.frame, new MyAccount_Fragment(), null).addToBackStack(null).commit();
                    drawer.closeDrawer(GravityCompat.START);
                    break;
                case R.id.cart:
                    Intent intent7 = new Intent(getApplicationContext(), Cart_activity.class);
                    startActivity(intent7);
                    //getSupportFragmentManager().beginTransaction().replace(R.id.frame, new Booking_Fragment(), null).addToBackStack(null).commit();
                    drawer.closeDrawer(GravityCompat.START);
                    break;
                case R.id.notification:
                    Intent intent4 = new Intent(getApplicationContext(), Notification.class);
                    startActivity(intent4);
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                case R.id.coupon:
                    Intent intent5 = new Intent(getApplicationContext(), CouponActivity.class);
                    startActivity(intent5);
                    drawer.closeDrawer(GravityCompat.START);
                    break;

                case R.id.support:
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("tel:1234567890"));
                    startActivity(i);
                    break;


            }
            return true;

        });




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



               //PROJECT BY SUBHAMJIT DASH AND SOUMYA RANJAN BEHERA ON 2023 APRIL MONTH
               // email subhamjeetdash2002@gmail.com


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


        viewpager1.setAdapter(new MainActivityhome.MyFragmentAdapter(getApplicationContext(), getSupportFragmentManager(), 5));
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
        public int getCount() {
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