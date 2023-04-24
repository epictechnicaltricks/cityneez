package com.serviceapp.new_design.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.serviceapp.R;

public class Product_details_activity extends AppCompatActivity {

    CardView plus, minus;
    TextView qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_product_details);

plus =findViewById(R.id.plus);
minus = findViewById(R.id.minus);

qty = findViewById(R.id.qty);

plus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        long val = (long) (Double.parseDouble(qty.getText().toString()) + 1);
        if(val<100)
        {

            qty.setText(String.valueOf(val));

        }

    }
});

minus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        long val = (long) (Double.parseDouble(qty.getText().toString()) - 1);
        if(val>0)
        {

            qty.setText(String.valueOf(val));

        }
    }
});


    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void add_To_cart(View view)
    {
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
        Intent i = new Intent();
        i.setClass(getApplicationContext(), Cart_activity.class);
        startActivity(i);
    }

}