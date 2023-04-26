package com.serviceapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
import com.serviceapp.new_design.activity.LayoutActivity;

import java.util.HashMap;
import java.util.Objects;


public class Login extends AppCompatActivity {
    TextView create,forgot;

    TextView login_text;

    AppCompatEditText pass, email;
    Button login;


    private RequestNetwork login_api;
    private RequestNetwork.RequestListener _login_api_listener;

    HashMap<String,Object> map = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initialize();

        Logic();

    }


    private void initialize() {

        create = findViewById(R.id.create);
        login = findViewById(R.id.login_btn);
        forgot = findViewById(R.id.forgot);
        pass = findViewById(R.id.password);
        email = findViewById(R.id.email);

        login_api = new RequestNetwork(this);

        _login_api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

                Log.d("login_api", response);
                _telegramLoaderDialog(false);
try {
    map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());

    if(response.contains(":200")) {

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("token", Objects.requireNonNull(map.get("token")).toString());
        myEdit.apply();

        //Toast.makeText(Login.this, Objects.requireNonNull(map.get("token")).toString(), Toast.LENGTH_SHORT).show();
        Util.showMessage(getApplicationContext(), Objects.requireNonNull(map.get("message")).toString());

         startActivity(new Intent(getApplicationContext(), Addressactivity.class));
          finish();


    } else {

        Util.showMessage(getApplicationContext(), Objects.requireNonNull(map.get("message")).toString());

    }
}catch (Exception e)
{
    Util.showMessage(getApplicationContext(), "Error on Login API");
}


            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Util.showMessage(getApplicationContext(), message);
                _telegramLoaderDialog(false);
            }
        };

    }





    public void continue_as_guest(View view) {

        Intent i = new Intent();
  //      i.setClass(getApplicationContext(), HomeActivity.class);
        i.setClass(getApplicationContext(), LayoutActivity.class);
        startActivity(i);
        finish();
    }

    private void Logic() {


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               login_logic();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://gvit.in/Cityneedz/resetpassword.php"));
                startActivity(i);
                //startActivity(new Intent(getApplicationContext(), forgotpasw.class));
            }
        });




    }


    private void login_logic() {

        if(Objects.requireNonNull(pass.getText()).toString().trim().equals("") || !Patterns.EMAIL_ADDRESS.matcher(Objects.requireNonNull(email.getText()).toString().trim()).matches() || Objects.requireNonNull(email.getText()).toString().trim().equals("")) {
            Util.showMessage(getApplicationContext(), "Invalid input on login form");

        } else {
            login_api_request(email.getText().toString().trim(), pass.getText().toString().trim());}
    }



    private void login_api_request(String _email, String _pass) {

        _telegramLoaderDialog(true);
        String api_params = "?customer_emailid=" +   _email.trim() +
                            "&customer_password=" +   _pass.trim();

        login_api.startRequestNetwork(RequestNetworkController.POST,
                getResources().getString(R.string.api_path)+"login.php"+api_params,
                "no tag", _login_api_listener);
        Log.d("login_api",getResources().getString(R.string.api_path)+"login.php"+api_params);

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