package com.serviceapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serviceapp.R;
import com.serviceapp.RadialProgressView;
import com.serviceapp.RequestNetwork;
import com.serviceapp.RequestNetworkController;
import com.serviceapp.Util;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.security.auth.callback.Callback;

public class Signup extends AppCompatActivity {
    TextView login_text;

    Button generateOTPBtn,verify;
    LinearLayout otp;
    AppCompatEditText name, email, otpinput, phone, pass, c_pass;
    Button signup;
    TextInputLayout otp_input;

    String phonenumber;
    String otpid;
    FirebaseAuth mAuth;


    private RequestNetwork signup_api;
    private RequestNetwork.RequestListener _signup_api_listener;

    HashMap<String,Object> map = new HashMap<>();


    private FirebaseAuth fauth;
    private OnCompleteListener<Void> fauth_updateEmailListener;
    private OnCompleteListener<Void> fauth_updatePasswordListener;
    private OnCompleteListener<Void> fauth_emailVerificationSentListener;
    private OnCompleteListener<Void> fauth_deleteUserListener;
    private OnCompleteListener<Void> fauth_updateProfileListener;
    private OnCompleteListener<AuthResult> fauth_phoneAuthListener;
    private OnCompleteListener<AuthResult> fauth_googleSignInListener;
    private OnCompleteListener<AuthResult> _fauth_create_user_listener;
    private OnCompleteListener<AuthResult> _fauth_sign_in_listener;
    private OnCompleteListener<Void> _fauth_reset_password_listener;

    private String codeSent = "";

boolean isVerified= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        init(savedInstanceState);
        Logic();


    }

    private void init(Bundle _savedInstanceState){

        fauth = FirebaseAuth.getInstance();


        login_text=findViewById(R.id.login_text);
        generateOTPBtn=findViewById(R.id.get);
        otp=findViewById(R.id.otp);
        verify=findViewById(R.id.verify);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        c_pass = findViewById(R.id.c_pass);
        signup = findViewById(R.id.signup);
        otpinput = findViewById(R.id.otpinput);


        mAuth = FirebaseAuth.getInstance();


        signup_api = new RequestNetwork(this);

        _signup_api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

                _telegramLoaderDialog(false);

                Log.d("signup_api", response);
                map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());
                if(response.contains(":200")) {

                    Util.showMessage(getApplicationContext(), Objects.requireNonNull(map.get("message")).toString());
                    //LOGIN
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();


                } else {

                    Util.showMessage(getApplicationContext(), Objects.requireNonNull(map.get("message")).toString());

                    /*
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putString("name", Objects.requireNonNull(map.get("name")).toString());
                    myEdit.putString("userId", Objects.requireNonNull(map.get("userId")).toString());
                    myEdit.putString("roleId",  Objects.requireNonNull(map.get("roleId")).toString());
                    myEdit.putString("token", Objects.requireNonNull(map.get("token")).toString());
                    myEdit.putString("role", Objects.requireNonNull(map.get("role")).toString());
                    myEdit.putString("avatar", Objects.requireNonNull(map.get("avatar")).toString());
                    myEdit.putString("API", Objects.requireNonNull(api));
                    myEdit.apply();
                    */


                }
            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Log.d("signup_api", message);
            }
        };

        _fauth_reset_password_listener = _param1 -> {
            final boolean _success = _param1.isSuccessful();

        };


        _fauth_sign_in_listener = _param1 -> {
            final boolean _success = _param1.isSuccessful();
            final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
           // progressbar2.setVisibility(View.GONE);
            _telegramLoaderDialog(false);
            if (_success) {
                Util.showMessage(getApplicationContext(), "Verifications Success ");


                try{
                    generateOTPBtn.setText("Verified ✔");
                    generateOTPBtn.setEnabled(false);
                    phone.setEnabled(false);
                    phone.setAlpha(0.8f);
                    otp.setVisibility(View.GONE);
                    isVerified = true;

                  /*  _register_api_request(
                            "Student",
                            getIntent().getStringExtra("email").substring(0,10),
                            getIntent().getStringExtra("email"),
                            getIntent().getStringExtra("pass"),
                            getIntent().getStringExtra("phone"),
                            "Class 5",
                            "COMPUTER SCIENCE",
                            "2011",
                            "22");



                    showMessage("Creating account..");*/

                }catch(Exception e){
                   // showMessage(e.toString());
                }

            }
            else {

                Util.showMessage(getApplicationContext(), "Invalid OTP - "+ _errorMessage);
            }
        };




    }




    private void Logic()
    {

        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        generateOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is for checking whether the user
                // has entered his mobile number or not.
                if (TextUtils.isEmpty(Objects.requireNonNull(phone.getText()).toString())) {
                    // when mobile number text field is empty
                    // displaying a toast message.
                    Toast.makeText(Signup.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                } else {
                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
                    if(phone.getText().length()==10)
                    {
                        String phone2 = "+91" + phone.getText().toString();
                        _sendotp(phone2);
                        _telegramLoaderDialog(true);


                    } else {
                        Toast.makeText(Signup.this, "Please enter valid no.", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the OTP text field is empty or not.
                if (TextUtils.isEmpty(Objects.requireNonNull(otpinput.getText()).toString())) {
                    // if the OTP text field is empty display
                    // a message to user to enter OTP
                    Toast.makeText(Signup.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {

                    _telegramLoaderDialog(true);
                    _verify(otpinput.getText().toString().trim());
                    // if OTP field is not empty calling
                    // method to verify the OTP.

                }
            }
        });





        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup_logic();
              // signup_api_request("subham","subham@c.com","123456789","subham@123","subham@123","1213");
            }
        });
    }






    private void signup_logic() {

if(Objects.requireNonNull(name.getText()).toString().trim().equals("") ||
        Objects.requireNonNull(phone.getText()).toString().trim().equals("") || Objects.requireNonNull(phone.getText()).toString().trim().length()!=10 ||
        !Objects.requireNonNull(pass.getText()).toString().trim().equals(Objects.requireNonNull(c_pass.getText()).toString().trim()) ||
        pass.getText().toString().trim().equals("") ||
        Objects.requireNonNull(email.getText()).toString().trim().equals("") || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches())
{
    Toast.makeText(this, "Invalid input on signup form", Toast.LENGTH_LONG).show();
} else {

    if(isVerified)
    {
        signup_api_request(name.getText().toString().trim(),
                email.getText().toString().trim(),
                phone.getText().toString().trim(),
                email.getText().toString().trim(),
                c_pass.getText().toString().trim());
    } else {
        generateOTPBtn.performClick();
        Util.showMessage(getApplicationContext(), "Verifying Phone no");
    }

}

    }



    private void signup_api_request(String _name, String _email, String _phone, String _pass, String _c_pass) {
      /*  HashMap<String, Object>api_map = new HashMap<>();
        api_map.put("customer_name", _name.trim());
        api_map.put("customer_emailid", _email.trim());
        api_map.put("customer_password", _c_pass.trim());
        api_map.put("customer_mobileno", _phone.trim());*/

        _telegramLoaderDialog(true);
        String api_params =
                "?customer_name=" +       _name.trim() +
                "&customer_emailid=" +   _email.trim() +
                "&customer_password=" + _c_pass.trim() +
                "&customer_mobileno=" +  _phone.trim() +
                "&customer_address=null" +
                "&customer_pincode=0";

       /* signup_api.setParams(api_map, RequestNetworkController.REQUEST_PARAM);*/
        signup_api.startRequestNetwork(RequestNetworkController.POST,
                getResources().getString(R.string.api_path)+"register.php"+api_params,
                "no tag", _signup_api_listener);
        Log.d("signup_api",getResources().getString(R.string.api_path)+"register.php"+api_params);
       /* api_map.clear();*/
        ///https://cityneedzapi.000webhostapp.com/
    }


////OTP //


    public void _sendotp (String _phone_No) {
        //progressbar1.setVisibility(View.VISIBLE);

        //verify_otp_layout.setVisibility(View.VISIBLE);
        //code for send otp on user mobile


        com.google.firebase.auth.PhoneAuthProvider.getInstance().verifyPhoneNumber(_phone_No, 50, java.util.concurrent.TimeUnit.SECONDS, this, mCallbacks);
    }
    com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(com.google.firebase.auth.PhoneAuthCredential phoneAuthCredential) {
            Util.showMessage(getApplicationContext(), "Phone Number Verified");


        }
        @Override
        public void onVerificationFailed(com.google.firebase.FirebaseException e) {

            Util.showMessage(getApplicationContext(),"Verification failed, Try again later!");
            _telegramLoaderDialog(false);

            //showMessage(e.toString());
            //progressbar1.setVisibility(View.GONE);
        }
        @Override
        public void onCodeSent(String s, com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            otp.setVisibility(View.VISIBLE);
            _telegramLoaderDialog(false);
            Util.showMessage(getApplicationContext(),"OTP sent.");
            codeSent = s;
        }
    };
    {
    }


    public void _verify (String _otp_value) {
        //progressbar2.setVisibility(View.VISIBLE);
        String code = _otp_value.trim();
        //code for verify otp

        com.google.firebase.auth.PhoneAuthCredential credential = com.google.firebase.auth.PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }
    private void signInWithPhoneAuthCredential(com.google.firebase.auth.PhoneAuthCredential credential) {
        fauth.signInWithCredential(credential) .addOnCompleteListener(this, _fauth_sign_in_listener);
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