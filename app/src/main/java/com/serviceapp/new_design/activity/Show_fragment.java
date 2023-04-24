package com.serviceapp.new_design.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.serviceapp.R;
import com.serviceapp.new_design.frags.New_Chicken_Frag;
import com.serviceapp.new_design.frags.New_Khajana_Frag;

public class Show_fragment extends AppCompatActivity {
    FrameLayout chickenframe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_fragments);
        chickenframe = findViewById(R.id.chickenframe);


 //       getSupportFragmentManager().beginTransaction().add(R.id.chickenframe, new New_Chicken_Frag(), null).addToBackStack(null).commit();

        if(getIntent().hasExtra("chicken"))
        {
            if(getIntent().getStringExtra("chicken").equals("true"))
            {
                getSupportFragmentManager().beginTransaction().add(R.id.chickenframe, new New_Chicken_Frag(), null).addToBackStack(null).commit();

            }else
            {
                getSupportFragmentManager().beginTransaction().add(R.id.chickenframe, new New_Khajana_Frag(), null).addToBackStack(null).commit();

            }
        }

    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}