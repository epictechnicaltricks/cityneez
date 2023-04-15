package com.serviceapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serviceapp.R;
import com.serviceapp.RadialProgressView;
import com.serviceapp.RequestNetwork;
import com.serviceapp.RequestNetworkController;
import com.serviceapp.Util;

import java.util.HashMap;
import java.util.Objects;

public class Addressactivity extends AppCompatActivity {

    LinearLayout profile_layout, address_layout, bg;
    TextView title;
    Button save;

    EditText name, mobile, email, addr, pincode;

    private RequestNetwork update_profile;
    private RequestNetwork.RequestListener _update_profile_api_listener;

    private RequestNetwork get_user_api;
    private RequestNetwork.RequestListener _get_user_api_listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressactivity);

        name = findViewById(R.id.name_);
        mobile = findViewById(R.id.mobile_);
        email = findViewById(R.id.email_);
        addr = findViewById(R.id.addr_);

        pincode = findViewById(R.id.pin_code_);
        profile_layout = findViewById(R.id.profile_layout);
        address_layout = findViewById(R.id.address_layout);
        title = findViewById(R.id.textview1);
        save = findViewById(R.id.save);

        bg = findViewById(R.id.bg);
        //bg.setVisibility(View.GONE);

        save.setEnabled(false);


        if(getIntent().hasExtra("open"))
        {
            if(getIntent().getStringExtra("open").equals("profile"))
            {
            address_layout.setVisibility(View.GONE);
            save.setText("SAVE PROFILE");
            title.setText("My Profile");

            } else {

                profile_layout.setVisibility(View.GONE);
                save.setText("SAVE ADDRESS");
                title.setText("My Address");
            }
        }


        API(); // LISTENER + API INITIALIZATION


        //API CALL
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        if(!sh.getString("token", "").equals("")) {
         //   Toast.makeText(this, "this is call 1 ", Toast.LENGTH_SHORT).show();
            getUser_api_request(sh.getString("token", ""));
        }


        if(!sh.getString("customer_pincode", "").equals("0"))
        {
            name.setText((sh.getString("customer_name", "")));
            email.setText((sh.getString("customer_emailid", "")));
            mobile.setText((sh.getString("customer_mobileno", "")));
            addr.setText((sh.getString("customer_address", "")));
            pincode.setText((sh.getString("customer_pincode", "")));
        } else {

            name.setText((sh.getString("customer_name", "")));
            email.setText((sh.getString("customer_emailid", "")));
            mobile.setText((sh.getString("customer_mobileno", "")));
        }




    }

    private void API()
    {
        update_profile = new RequestNetwork(this);
        _update_profile_api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

                _telegramLoaderDialog(false);


                Log.d("update_api",response);
                if(response.contains("200"))
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("customer_name", name.getText().toString().trim());
                    myEdit.putString("customer_emailid", email.getText().toString().trim());
                    myEdit.putString("customer_address",  addr.getText().toString().trim());
                    myEdit.putString("customer_mobileno", mobile.getText().toString().trim());
                    myEdit.putString("customer_pincode", pincode.getText().toString().trim());
                    myEdit.apply();

                    Util.showMessage(getApplicationContext(),"Updated.");
                    finish();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));


                }else {
                    Util.showMessage(getApplicationContext(),"Failed to update - " +response);
                }

            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Util.showMessage(getApplicationContext(),message);
                _telegramLoaderDialog(false);
            }
        };





        /////////////


        get_user_api = new RequestNetwork(this);

        _get_user_api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

                save.setEnabled(true);

                _telegramLoaderDialog(false);

                Log.d("getUser_api", response);
                HashMap<String, Objects> map;
                map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());

                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();


                    try {

                        //Toast.makeText(Addressactivity.this, "this is call 2", Toast.LENGTH_SHORT).show();

                           myEdit.putString("customer_name",map.get("customer_name")+"");
                        myEdit.putString("customer_pincode", map.get("customer_pincode")+"");
                        myEdit.putString("customer_emailid", map.get("customer_emailid")+"");
                        myEdit.putString("customer_address",  map.get("customer_address")+"");
                        myEdit.putString("customer_mobileno", map.get("customer_mobileno")+"");
                        myEdit.putString("customer_registerdate", map.get("customer_registerdate")+"");
                        myEdit.putString("user_id",map.get("id")+"");
                        myEdit.apply();

                if(!getIntent().hasExtra("open"))
                {

                    if(Objects.equals(map.get("customer_pincode"), "0"))
                    {

                        profile_layout.setVisibility(View.GONE);
                        save.setText("SAVE ADDRESS");
                        title.setText("My Address");
                        name.setText(map.get("customer_name")+"");
                        email.setText(map.get("customer_emailid")+"");
                        mobile.setText(map.get("customer_mobileno")+"");

                    } else {

                       startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                       finish();

                    }

                    name.setText(map.get("customer_name")+"");
                    email.setText(map.get("customer_emailid")+"");
                    mobile.setText(map.get("customer_mobileno")+"");
                }


                    }catch (Exception e) {
                        Util.showMessage(getApplicationContext(), e.toString());
                        Log.d("getUser_api", e.toString());
                   }

            }

            @Override
            public void onErrorResponse(String tag, String message) {
               Util.showMessage(getApplicationContext(), message);
                _telegramLoaderDialog(false);
            }
        };







    }


    private void update_profile(String _name, String _email, String _phone, String _addr, String pin_code)
    {
        _telegramLoaderDialog(true);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String api_params =
                           "?customer_name=" +    _name.trim() +
                        "&customer_emailid=" +   _email.trim() +
                        "&customer_mobileno=" + _phone.trim() +
                        "&customer_address=" +  _addr.trim() +
                        "&id=" +  sh.getString("user_id", "") +
                        "&customer_pincode="+ pin_code;

        /* signup_api.setParams(api_map, RequestNetworkController.REQUEST_PARAM);*/
        update_profile.startRequestNetwork(RequestNetworkController.POST,
                getResources().getString(R.string.api_path)+"profileUpdate.php"+api_params,
                "no tag", _update_profile_api_listener);
        Log.d("profile_api",getResources().getString(R.string.api_path)+"profileUpdate.php"+api_params);

    }



    public void go_check(View view) {
        if(!name.getText().toString().trim().equals("") &&
                !addr.getText().toString().trim().equals("") &&
                !mobile.getText().toString().trim().equals("") && mobile.getText().toString().length()==10
                && !pincode.getText().toString().trim().equals("") && pincode.getText().toString().length()==6
                && !email.getText().toString().trim().equals("") && email.getText().toString().contains("@")) {

            update_profile(
                    name.getText().toString().trim(),
                    email.getText().toString().trim(),
                    mobile.getText().toString().trim(),
                    addr.getText().toString().trim(),
                    pincode.getText().toString().trim());

        }else {

            Util.showMessage(getApplicationContext(), "Fields are invalid.");
        }
    }

    public void close(View view) {
       finish();
    }

    private void getUser_api_request(String _token) {

        _telegramLoaderDialog(true);

        Log.d("getUser_api"," Bearer "+_token);
        //Toast.makeText(getApplicationContext(), _token, Toast.LENGTH_SHORT).show();


        //authorization.clear();
        //authorization.put("Authorization"," Bearer "+_token);
        //get_user_api.setHeaders(authorization);
        get_user_api.startRequestNetwork(RequestNetworkController.POST,
                getResources().getString(R.string.api_path)+"getUser.php"+"?Authorization="+_token,
                "no tag", _get_user_api_listener);
        Log.d("getUser_api",getResources().getString(R.string.api_path)+"getUser.php"+"Authorization="+_token);

        ///https://cityneedzapi.000webhostapp.com/
    }

    public void _telegramLoaderDialog(final boolean _visibility) {

        if (_visibility) {
            if (coreprog == null){
                coreprog = new ProgressDialog(this);
                coreprog.setCancelable(false);
                coreprog.setCanceledOnTouchOutside(false);

                coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));

            }
            coreprog.show();
            coreprog.setContentView(R.layout.loading);


            LinearLayout linear2 = (LinearLayout)coreprog.findViewById(R.id.linear2);

            LinearLayout back = (LinearLayout)coreprog.findViewById(R.id.background);

            LinearLayout layout_progress = (LinearLayout)coreprog.findViewById(R.id.layout_progress);

            android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
            gd.setColor(Color.parseColor("#FFFFFF")); /* color */
            gd.setCornerRadius(45); /* radius */
            gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
            linear2.setBackground(gd);

            RadialProgressView progress = new RadialProgressView(this);
            layout_progress.addView(progress);
        }
        else {
            if (coreprog != null){
                coreprog.dismiss();
            }
        }
    }
    private ProgressDialog coreprog;



}