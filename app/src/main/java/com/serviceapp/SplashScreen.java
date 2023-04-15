package com.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.serviceapp.activity.HomeActivity;
import com.serviceapp.activity.Login;
import com.serviceapp.coinsystem.CoinActivity;

public class SplashScreen extends AppCompatActivity {

/*
     ░█████╗░██████╗░██╗      ██╗░░░░░██╗███╗░░██╗██╗░░██╗
     ██╔══██╗██╔══██╗██║      ██║░░░░░██║████╗░██║██║░██╔╝
     ███████║██████╔╝██║      ██║░░░░░██║██╔██╗██║█████═╝░
     ██╔══██║██╔═══╝░██║      ██║░░░░░██║██║╚████║██╔═██╗░
     ██║░░██║██║░░░░░██║      ███████╗██║██║░╚███║██║░╚██╗
     ╚═╝░░╚═╝╚═╝░░░░░╚═╝      ╚══════╝╚═╝╚═╝░░╚══╝╚═╝░░╚═╝


You find the api link on values/string.xml folder

*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent i = new Intent();
                //API CALL
                SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                if(!sh.getString("token", "").equals("")) {

                    i.setClass(getApplicationContext(), HomeActivity.class);
                    //i.setClass(getApplicationContext(), CoinActivity.class);


                } else
                {
                    i.setClass(getApplicationContext(), Login.class);
                }


                startActivity(i);
                finish();
            }
        }, 2000);
    }
}