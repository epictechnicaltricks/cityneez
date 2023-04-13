package com.serviceapp.coinsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serviceapp.R;
import com.serviceapp.RequestNetwork;
import com.serviceapp.RequestNetworkController;
import com.serviceapp.Util;
import com.serviceapp.fragements.Booking_Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;

public class CoinActivity extends AppCompatActivity {


     RequestNetwork get_coin_count_api;
     RequestNetwork.RequestListener _get_coin_count_api_listener;

     RequestNetwork update_coin_api;
     RequestNetwork.RequestListener _update_coin_api_listener_ADD;
    RequestNetwork.RequestListener _update_coin_api_listener_USE;

     RequestNetwork coin_used_info_api;
     RequestNetwork.RequestListener _coin_used_info_api_listener;

     RequestNetwork coin_added_info_api;
     RequestNetwork.RequestListener _coin_add_info_api_listener;

     ArrayList<HashMap<String, Object>> result = new ArrayList<>();
     EditText add_qty, use_qty;
     TextView coin,add_coin, use_coin;

     double coin_value;

     String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        add_qty = findViewById(R.id.add_qty);
        use_qty = findViewById(R.id.use_qty);

        coin = findViewById(R.id.coin);

        add_coin = findViewById(R.id.add_coin);

        use_coin = findViewById(R.id.use_coin);

        get_coin_count_api = new RequestNetwork(this);
        update_coin_api = new RequestNetwork(this);
        coin_used_info_api = new RequestNetwork(this);
        coin_added_info_api = new RequestNetwork(this);


        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        user_id =sharedPreferences.getString("user_id", "");
        if(user_id.equals(""))
        {
            finish();
            Toast.makeText(this, "No user id found", Toast.LENGTH_SHORT).show();
        }



        _get_coin_count_api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                try {
                    if(response.contains("200"))
                    {
                        HashMap<String , Objects> map;
                        map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());
                        String values = (new Gson()).toJson(map.get("user"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
                        result = new Gson().fromJson(values, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

                        coin.setText("You have "+result.get(0).get("coins")+" coins");
                        coin_value = Double.parseDouble(Objects.requireNonNull(result.get(0).get("coins")).toString().trim());
                        add_coin.setEnabled(true);
                        use_coin.setEnabled(true);

                    } else {
                        coin.setText("Failed to fetch\n\n"+response);
                    }
                }catch (Exception e)
                {
                    Util.showMessage(getApplicationContext(), e.toString());
                }

            }

            @Override
            public void onErrorResponse(String tag, String message) {
Util.showMessage(getApplicationContext(), message);
            }
        };

        _update_coin_api_listener_ADD = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
            if(response.contains("200"))
            {
                Util.showMessage(getApplicationContext(), add_qty.getText().toString()+" coin added");
                request_added_coin_info(user_id,add_qty.getText().toString()+"",add_qty.getText().toString()+" added coin");
                request_get_coin_count(user_id);
            }


            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Util.showMessage(getApplicationContext(), message);
            }
        };

        _update_coin_api_listener_USE = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                if(response.contains("200"))
                {
                    Util.showMessage(getApplicationContext(), use_qty.getText().toString()+" coin used");
                    request_used_coin_info(user_id,use_qty.getText().toString()+"",use_qty.getText().toString()+" used coin");
                    request_get_coin_count(user_id);
                }


            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Util.showMessage(getApplicationContext(), message);
            }
        };

        _coin_used_info_api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                if(response.contains("200"))
                {
                    Util.showMessage(getApplicationContext(), "Added to COIN USED History");
                }
            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Util.showMessage(getApplicationContext(), message);
            }
        };

        _coin_add_info_api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                if(response.contains("200"))
                {
                    Util.showMessage(getApplicationContext(), "Added to COIN ADDED History");
                }
            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Util.showMessage(getApplicationContext(), message);
            }
        };




        add_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    //	request_user_book_list_api(sharedPreferences.getString("user_id", ""));
                    if(!add_qty.getText().toString().trim().equals("")&&!add_qty.getText().toString().trim().equals("0"))
                    {
                        request_update_coin("add",user_id,""+(coin_value+Double.parseDouble(add_qty.getText().toString())));
                        // request_user_book_list_api(sharedPreferences.getString("customer_emailid", "")+"");

                    } else {

                        Toast.makeText(CoinActivity.this, "enter valid value", Toast.LENGTH_SHORT).show();
                    }


            }
        });

        use_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!use_qty.getText().toString().trim().equals("")&&!use_qty.getText().toString().trim().equals("0"))
                {
                    if(Double.parseDouble(use_qty.getText().toString())<=coin_value)
                    {
                        request_update_coin("use",user_id,""+(coin_value-Double.parseDouble(use_qty.getText().toString())));

                    }else {
                        Toast.makeText(CoinActivity.this, "Not have enough coins to use.", Toast.LENGTH_LONG).show();
                    }
                      // request_user_book_list_api(sharedPreferences.getString("customer_emailid", "")+"");

                } else {
////
                    Toast.makeText(CoinActivity.this, "enter valid value", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        request_get_coin_count(user_id);


    }



    private void request_get_coin_count(String _user_id)
    {

        add_coin.setEnabled(false);
        use_coin.setEnabled(false);
        String api_params = "?user_id=" + _user_id.trim();
        get_coin_count_api.startRequestNetwork(RequestNetworkController.POST,
                getResources().getString(R.string.api_path)+"Coin_count.php"+api_params,
                "no tag", _get_coin_count_api_listener);
        Log.d("get_coin_count_api",getResources().getString(R.string.api_path)+"Coin_count.php"+api_params);

    }

    private void request_update_coin(String type,String _user_id, String _coin)
    {
        add_coin.setEnabled(false);
        use_coin.setEnabled(false);
        String api_params = "?user_id="+_user_id.trim()+"&update_coin_to="+_coin.trim();
        switch (type)
        {
            case "add" :   update_coin_api.startRequestNetwork(RequestNetworkController.POST,
                    getResources().getString(R.string.api_path)+"Coin_update.php"+api_params,
                    "no tag", _update_coin_api_listener_ADD);
                break;

            case "use":   update_coin_api.startRequestNetwork(RequestNetworkController.POST,
                    getResources().getString(R.string.api_path)+"Coin_update.php"+api_params,
                    "no tag", _update_coin_api_listener_USE);
                break;
        }


        Log.d("request_update_coin_api",getResources().getString(R.string.api_path)+"Coin_update.php"+api_params);

    }


    private void request_added_coin_info(String _user_id, String _coin_added, String _description)
    {

        String api_params = "?user_id="+_user_id.trim()+"&coins_added="+_coin_added.trim()+"&description="+_description.trim();
        coin_added_info_api.startRequestNetwork(RequestNetworkController.POST,
                getResources().getString(R.string.api_path)+"Coin_added_info.php"+api_params,
                "no tag", _coin_add_info_api_listener);
        Log.d("request_added_coin_info",getResources().getString(R.string.api_path)+"Coin_added_info.php"+api_params);

    }

    private void request_used_coin_info(String _user_id, String _coin_used, String _description)
    {

        String api_params = "?user_id="+_user_id.trim()+"&coins_used="+_coin_used.trim()+"&description="+_description.trim();
        coin_used_info_api.startRequestNetwork(RequestNetworkController.POST,
                getResources().getString(R.string.api_path)+"Coin_used_info.php"+api_params,
                "no tag", _coin_used_info_api_listener);
        Log.d("request_added_coin_info",getResources().getString(R.string.api_path)+"Coin_used_info.php"+api_params);

    }

}