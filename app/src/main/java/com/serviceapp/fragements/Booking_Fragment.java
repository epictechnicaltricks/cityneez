package com.serviceapp.fragements;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serviceapp.R;
import com.serviceapp.RadialProgressView;
import com.serviceapp.RequestNetwork;
import com.serviceapp.RequestNetworkController;
import com.serviceapp.Util;
import com.serviceapp.activity.Login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;


public class Booking_Fragment extends  Fragment  {



	private RecyclerView recyclerview1;
	private  ArrayList<HashMap<String, Object>> listmap2 = new ArrayList<>();

	private RequestNetwork booked_list_api;
	private RequestNetwork.RequestListener _booked_list_listener;

	LinearLayout msg, login_bg ;

	TextView login, login2;


	private RequestNetwork cancel_booking_api;
	private RequestNetwork.RequestListener _cancel_booking_listener;





@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.frag_booking, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {


		login = _view.findViewById(R.id.login_);
		login2 = _view.findViewById(R.id.login_2);
login_bg = _view.findViewById(R.id.login_bg);


login.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View view) {
		startActivity(new Intent(getActivity(), Login.class));
	}
});
		SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
		if(!sh.getString("token", "").equals("")) {


			login_bg.setVisibility(View.GONE);




		} else
		{
			login_bg.setVisibility(View.VISIBLE);

		}



		msg = _view.findViewById(R.id.msg);

		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		//t1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		//t2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);
		//t3.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.NORMAL);
//
		//textview.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_sans_medium.ttf"), Typeface.BOLD);


	/*	linear1 = _view.findViewById(R.id.linear1);
		swiperefreshlayout1 = _view.findViewById(R.id.swiperefreshlayout1);
		no_internet = _view.findViewById(R.id.no_internet);
		progress = _view.findViewById(R.id.progress);
*/


		booked_list_api = new RequestNetwork(getActivity());

		cancel_booking_api = new RequestNetwork(getActivity());

		_cancel_booking_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
				SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

				if(response.contains("200"))
				{
				Util.showMessage(getActivity(), "Booking Cancelled");
				request_user_book_list_api(sharedPreferences.getString("customer_emailid", "")+"");
				}
			}

			@Override
			public void onErrorResponse(String tag, String message) {
              Util.showMessage(getActivity(), message);
			}
		};

		_booked_list_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

				//_telegramLoaderDialog(false);

				try {
					if(response.contains("200"))
					{
						HashMap<String , Objects> map;
						map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());
						String values = (new Gson()).toJson(map.get("user"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
						listmap2 = new Gson().fromJson(values, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());

						Collections.reverse(listmap2);
						recyclerview1.setAdapter(new Recyclerview1Adapter(listmap2));
						recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
						recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

						recyclerview1.setVisibility(View.VISIBLE);
						msg.setVisibility(View.GONE);
					} else {

						listmap2.clear();
						recyclerview1.setVisibility(View.GONE);
						msg.setVisibility(View.VISIBLE);
						//Util.showMessage(getActivity(), "No bookings found.");
					}


				}catch (Exception e)
				{
					Util.showMessage(getActivity(), e.toString());
				}



			}

			@Override
			public void onErrorResponse(String tag, String message) {
				_telegramLoaderDialog(false);
Util.showMessage(getActivity(), message);
			}
		};


	}





	private void initializeLogic() {

		SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

		if(!sharedPreferences.getString("user_id", "").equals(""))
		{
		//	request_user_book_list_api(sharedPreferences.getString("user_id", ""));
			request_user_book_list_api(sharedPreferences.getString("customer_emailid", "")+"");
		}




/*

	{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "AC CLEANING");
			_item.put("status", "process");
			_item.put("date", "25th March 2023");
			listmap2.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "CAR WASHING");
			_item.put("status", "confirm");
			_item.put("date", "21th March 2023");
			listmap2.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "COMPUTER REPAIR");
			_item.put("status", "done");
			_item.put("date", "9th Feb 2023");
			listmap2.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "AC CLEANING");
			_item.put("status", "");
			_item.put("date", "25th March 2023");
			listmap2.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "CAR WASHING");
			_item.put("status", "confirm");
			_item.put("date", "21th March 2023");
			listmap2.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("name", "COMPUTER REPAIR");
			_item.put("status", "done");
			_item.put("date", "9th Feb 2023");
			listmap2.add(_item);
		}




		recyclerview1.setAdapter(new Recyclerview1Adapter(listmap2));
		recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
*/


	}




	private void request_user_book_list_api(String _user_id)
	{
		//_telegramLoaderDialog(true);

		String api_params = "?user_id=" + _user_id.trim();

		booked_list_api.startRequestNetwork(RequestNetworkController.POST,
				getResources().getString(R.string.api_path)+"getAllBooking_list.php"+api_params,
				"no tag", _booked_list_listener);
		Log.d("booked_list_api",getResources().getString(R.string.api_path)+"getAllBooking_list.php"+api_params);
	}


	private void request_cancel_booking_api(String _bookservice_id)
	{
		//_telegramLoaderDialog(true);

		String api_params = "?bookservice_id=" + _bookservice_id;

		cancel_booking_api.startRequestNetwork(RequestNetworkController.POST,
				getResources().getString(R.string.api_path)+"CancelBooking.php"+api_params,
				"no tag",_cancel_booking_listener );
		Log.d("CancelBooking_api",getResources().getString(R.string.api_path)+"CancelBooking.php"+api_params);
	}


	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@NonNull
		@Override
		public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LayoutInflater _inflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);		View _v = _inflater.inflate(R.layout.my_booking_custom, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}

		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;

            final CardView card = _view.findViewById(R.id.card_bg);
			final TextView service_name = _view.findViewById(R.id.service_name);
			final TextView status = _view.findViewById(R.id.status);
			final TextView date = _view.findViewById(R.id.date);
			final TextView note = _view.findViewById(R.id.note);
			final TextView book_id = _view.findViewById(R.id.book_id);
			final TextView desc = _view.findViewById(R.id.desc);
			final MaterialButton support = _view.findViewById(R.id.support);
			final MaterialButton cancel = _view.findViewById(R.id.cancel);



			try{

				status.setTextColor(0xFFffffff);
				service_name.setTextColor(0xFFFFFFFF);
				date.setTextColor(0xFFFFFFFF);




				date.setText("Booked on " + listmap2.get(_position).get("bookservice_date"));
				service_name.setText(Objects.requireNonNull(listmap2.get(_position).get("bookservice_categoryname")).toString());

				book_id.setText("BOOKED ID: "+ listmap2.get(_position).get("bookservice_id"));


				desc.setText("Service name: "+ listmap2.get(_position).get("bookservice_name")+", Description: "+listmap2.get(_position).get("bookservice_defaultdescription"));

			/*	switch (Objects.requireNonNull(listmap2.get(_position).get("status")).toString())
				{
					case "confirm": status.setText("Confirmed"); status.setTextColor(0xFF226df0);
					break;
					case "done": status.setText("Completed"); status.setTextColor(0xFF23ad0a);
					break;
					default: status.setText("Progress"); status.setTextColor(0xFFe39929);
					break;
				}
*/
				support.setVisibility(View.GONE);

				switch (Objects.requireNonNull(listmap2.get(_position).get("book_status")).toString()) {

					case "confirm": status.setText("Confirmed");
					card.setCardBackgroundColor(0xFF226df0);

					cancel.setVisibility(View.GONE);
						note.setVisibility(View.GONE);
						break;
					case "done": status.setText("Completed");
					   card.setCardBackgroundColor(0xFF23ad0a);
					   support.setVisibility(View.VISIBLE);
					   cancel.setVisibility(View.GONE);
						note.setVisibility(View.GONE);
						break;


					case "cancel": status.setText("Cancelled");
						card.setCardBackgroundColor(0xFFd1431b);

						cancel.setVisibility(View.GONE);
						note.setVisibility(View.GONE);
						break;

					default: status.setText("Progress");
					card.setCardBackgroundColor(0xFFe89920);

					note.setVisibility(View.VISIBLE);
					cancel.setVisibility(View.VISIBLE);

						break;
				}


//support.setVisibility(View.GONE);
				cancel.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {

							//Toast.makeText(getContext(), listmap2.get(_position).get("bookservice_id").toString(), Toast.LENGTH_SHORT).show();
							request_cancel_booking_api(listmap2.get(_position).get("bookservice_id").toString());


						}catch (Exception e){
							SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

							request_user_book_list_api(sharedPreferences.getString("customer_emailid", "")+"");

						}
					}
				});
				support.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {

						String show ="Booked on: "+listmap2.get(_position).get("bookservice_date")+"\n\n"+
								"Booking confirm on\n"+listmap2.get(_position).get("bookservice_confirmdate")+"\n\n"+
								"Service complete on\n" + listmap2.get(_position).get("bookservice_completedate")+"\n\n"+
								"Booked Category\n"+"" + listmap2.get(_position).get("bookservice_categoryname")+"\n\n"+
								"Booked Service\n"+listmap2.get(_position).get("bookservice_name")+"\n\n"+
								"Service description\n" +listmap2.get(_position).get("bookservice_defaultdescription")+" "+listmap2.get(_position).get("bookservice_description")+"\n\n"+
								"Service Location\n"+listmap2.get(_position).get("bookservice_location");


						AlertDialog.Builder al = new AlertDialog.Builder(getContext());
						al.setTitle("Booking details");
						al.setMessage(show);
						al.setCancelable(false);
						al.setPositiveButton("close", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {

							}
						});

						al.setNeutralButton("Need help?", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialogInterface, int i) {
Intent call = new Intent(Intent.ACTION_VIEW);
call.setData(Uri.parse("tel:9040015300"));
startActivity(call);
							}
						});

						al.create().show();
					}
				});




			/*	String img_url = Objects.requireNonNull(api_map3.get("img_url")).toString();

				Glide.with(getApplicationContext())
						.load(Uri.parse(img_url))
						.error(R.drawable.school2)
						.placeholder(R.drawable.school2)
						.thumbnail(0.01f)
						.into(imageview2);*/


				/*ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				ClipData clip = ClipData.newPlainText("Copied Text", img_url );
				clipboard.setPrimaryClip(clip);
				Log.d("img_obj", img_url);*/



			}catch (Exception e)
			{

			}



		}

		@Override
		public int getItemCount() {
			return _data.size();
		}

		public class ViewHolder extends RecyclerView.ViewHolder{
			public ViewHolder(View v){
				super(v);
			}
		}

	}
	public void _telegramLoaderDialog(final boolean _visibility) {

		if (_visibility) {
			if (coreprog == null){
				coreprog = new ProgressDialog(getContext());
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

			RadialProgressView progress = new RadialProgressView(getContext());
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
