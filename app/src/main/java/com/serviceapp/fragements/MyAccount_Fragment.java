package com.serviceapp.fragements;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serviceapp.R;
import com.serviceapp.RequestNetwork;
import com.serviceapp.RequestNetworkController;
import com.serviceapp.SplashScreen;
import com.serviceapp.Util;
import com.serviceapp.activity.Addressactivity;
import com.serviceapp.activity.HomeActivity;
import com.serviceapp.activity.Login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class MyAccount_Fragment extends  Fragment  {

      ImageView edit;
	  TextView address, ratings, help;

      TextView name, logout, phone, login, login2;

	  LinearLayout bg;
	CardView cardimg;
	ImageView imageview;



	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.frag_my_account, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		cardimg = _view.findViewById(R.id.cardimg3);
		imageview = _view.findViewById(R.id.imageview6);
		Glide.with(getContext())
				.load(Uri.parse(Objects.requireNonNull("https://media.tenor.com/X5jRwr6NyRkAAAAC/wink-emoji.gif").toString()))
				.thumbnail(0.01f)
				.into(imageview);
		return _view;

	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
edit = _view.findViewById(R.id.edit);
address = _view.findViewById(R.id.address);
help = _view.findViewById(R.id.support);
name = _view.findViewById(R.id.name);
phone = _view.findViewById(R.id.phone);
bg = _view.findViewById(R.id.bg);
login = _view.findViewById(R.id.login_);
		login2 = _view.findViewById(R.id.login_2);

login.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {
		startActivity(new Intent(getActivity(), Login.class));
	}
});

		SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
		if(!sh.getString("token", "").equals("")) {


			bg.setVisibility(View.VISIBLE);
			login.setVisibility(View.GONE);
			login2.setVisibility(View.GONE);



		} else
		{
			bg.setVisibility(View.GONE);
			login.setVisibility(View.VISIBLE);
			login2.setVisibility(View.VISIBLE);
		}

logout = _view.findViewById(R.id.logout);





edit.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {
Intent i = new Intent();
i.putExtra("open", "profile");
i.setClass(getContext(), Addressactivity.class);
startActivity(i);
	}
});

address.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {
		Intent i = new Intent();
		i.putExtra("open", "address");
		i.setClass(getContext(), Addressactivity.class);
		startActivity(i);
	}
});

help.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {

	}
});

logout.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {

		SharedPreferences sha = getContext().getSharedPreferences("MySharedPref",Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sha.edit();
		editor.clear();
		editor.apply();

		startActivity(new Intent(getContext(), SplashScreen.class));
		getActivity().finishAffinity();
	}
});

	}





	private void initializeLogic() {

		SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
		if(!sharedPreferences.getString("customer_name", "").equals(""))
		{
			name.setText(sharedPreferences.getString("customer_name", ""));
			phone.setText(sharedPreferences.getString("customer_mobileno", ""));
		}


	}


	
}
