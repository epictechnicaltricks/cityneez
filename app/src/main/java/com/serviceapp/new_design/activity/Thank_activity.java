package com.serviceapp.new_design.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.serviceapp.R;

import java.util.Objects;

public class Thank_activity extends AppCompatActivity {
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank);
        imageview = findViewById(R.id.im2);
        Glide.with(getApplicationContext())
                .load(Uri.parse(Objects.requireNonNull("https://cdn.dribbble.com/users/1751799/screenshots/5512482/check02.gif").toString()))
                .thumbnail(0.01f)
                .into(imageview);
    }

    public void button(View view) {
        Intent intent= new Intent(getApplicationContext(), LayoutActivity.class);
        startActivity(intent);
    }
}