package com.serviceapp.new_design.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.serviceapp.R;

public class Checkout_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    public void thankyouu(View v)
    {
        startActivity(new Intent(getApplicationContext(), Thank_activity.class));
    }
}