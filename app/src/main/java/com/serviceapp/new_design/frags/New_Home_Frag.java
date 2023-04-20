package com.serviceapp.new_design.frags;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.serviceapp.R;
import com.serviceapp.Util;
import com.serviceapp.activity.Addressactivity;
import com.serviceapp.activity.Book_Service_Activity;
import com.serviceapp.new_design.activity.LayoutActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class New_Home_Frag extends  Fragment  {

	ViewPager viewpager1;
    MaterialButton bookService;

	TextView user_name;


	private final Timer _timer = new Timer();

   int count = 0;

   TimerTask srcoll_timer;

   ArrayList<HashMap<String,Object>> listmap = new ArrayList<>();


   ArrayList<HashMap<String, Object>> array_map_top_services = new ArrayList<>();

	//ArrayList<HashMap<String, Object>> array_map_all_Services = new ArrayList<>();

	private RecyclerView recyclerview1;
	private RecyclerView recyclerview2;

	ImageView menu;


@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.new_frag_home, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {

	viewpager1 = _view.findViewById(R.id.viewpager1);
    bookService = _view.findViewById(R.id.book_service);

user_name = _view.findViewById(R.id.user_name);
menu = _view.findViewById(R.id.menu);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		recyclerview2 = _view.findViewById(R.id.recyclerview2);

	}






	private void initializeLogic() {

	menu.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			//Util.showMessage(getContext(), "Hello");
			((LayoutActivity) requireActivity()).openDrawer();
		}
	});


	/*	SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
		if(!sh.getString("customer_name", "").equals(""))
		{
			user_name.setText((sh.getString("customer_name", "")));

		}*/




	 _slider();
	 top_service_data();

	}





	public void _slider () {
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://static.vecteezy.com/system/resources/previews/003/692/287/original/big-sale-discount-promotion-banner-template-with-blank-product-podium-scene-graphic-free-vector.jpg");
			listmap.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://img.freepik.com/premium-vector/10-percent-off-discount-creative-composition-summer-sale-banner-with-kiwi-sale-banner-poster_3482-7313.jpg");
			listmap.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://media.istockphoto.com/id/1297404188/vector/beautiful-stylish-girl-portrait-with-pink-hair-banner-or-flyer-template-for-sale-on.jpg?s=612x612&w=0&k=20&c=DwuKT9vOlWlN1NCQQsU_P3qKq0PMsFTmlSXa103Jq8g=");
			listmap.add(_item);
		}

		final float scaleFactor = 0.96f;
		viewpager1.setPageMargin(-15);
		viewpager1.setOffscreenPageLimit(2);
		viewpager1.setPageTransformer(false, new ViewPager.PageTransformer() { @Override public void transformPage(@NonNull View page1, float position) { page1.setScaleY((1 - Math.abs(position) * (1 - scaleFactor))); page1.setScaleX(scaleFactor + Math.abs(position) * (1 - scaleFactor)); } });
		viewpager1.setAdapter(new Viewpager1Adapter(listmap));





		srcoll_timer = new TimerTask() {
			@Override
			public void run() {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {





						if (count == listmap.size()) {

							count = 0;
						}

						//Toast.makeText(getContext(), ""+coun[count], Toast.LENGTH_SHORT).show();
						viewpager1.setCurrentItem(count);
						count++;


						//viewpager2.setCurrentItem((int)count);
						//viewpager3.setCurrentItem((int)count);

					}
				});
			}
		};
		_timer.scheduleAtFixedRate(srcoll_timer, 4000, 4000);

	}

		public void top_service_data()
	{
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://cdn.shopify.com/s/files/1/0541/1648/3271/files/chnage_ac_filter_nirvanabeing.jpg");
			_item.put("sname","AC Service");
			_item.put("sdesc","Professional maintenance of AC for optimal performance and efficiency.");
			_item.put("price","₹500");
			array_map_top_services.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://m.media-amazon.com/images/I/71d5fMDvq9L._SL1500_.jpg");
			_item.put("sname","TV Service");
			_item.put("sdesc","Professional maintenance of AC for optimal performance and efficiency.");
			_item.put("price","₹500");
			array_map_top_services.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://stimg.cardekho.com/images/carexteriorimages/630x420/Kia/Seltos/6226/1679395459776/front-left-side-47.jpg?impolicy=resize&imwidth=480");
			_item.put("sname","Car Service");
			_item.put("sdesc","Car service includes inspection, maintenance, and repairs for optimal performance..");
			_item.put("price","₹1500");
			array_map_top_services.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://res.cloudinary.com/urbanclap/image/upload/q_auto,f_auto,fl_progressive:steep,w_532/t_high_res_category/categories/home_screen/plumber.jpg");
			_item.put("sname","Plumbers Service");
			_item.put("sdesc","Professional plumbing services for repairs, installations, and maintenance.");
			_item.put("price","₹500");
			array_map_top_services.add(_item);
		}


		recyclerview1.setAdapter(new Recyclerview1Adapter(array_map_top_services));
		recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));

		recyclerview2.setAdapter(new Recyclerview2Adapter(array_map_top_services));
		recyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerview2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));




	}

	@Override
	public void onPause() {

		if(srcoll_timer != null)
		{
			srcoll_timer.cancel();
		}


		super.onPause();
	}

	@Override
	public void onResume() {

		try {
			if(srcoll_timer != null) {
				srcoll_timer.run();
			}

		}catch (Exception e)
		{

		}

		super.onResume();
	}

	public class Recyclerview2Adapter extends RecyclerView.Adapter<Recyclerview2Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@NonNull
		@Override
		public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LayoutInflater _inflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _inflater.inflate(R.layout.top_service_custom, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}

		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;

			final CardView card = _view.findViewById(R.id.card);
			final TextView service_name = _view.findViewById(R.id.name);
		//	final TextView service_desc = _view.findViewById(R.id.desc);
			final TextView price = _view.findViewById(R.id.price);
			final ImageView img = _view.findViewById(R.id.img);



			try{



				service_name.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("sname")).toString());

				//service_desc.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("desc")).toString());

				price.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("price")).toString());

				String img_url = Objects.requireNonNull(array_map_top_services.get(_position).get("image")).toString();
				Glide.with(getActivity())
						.load(Uri.parse(img_url))
						.thumbnail(0.01f)
						.into(img);


				card.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {
							SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
							if(!sh.getString("token", "").equals("")) {


								if(!sh.getString("customer_pincode", "").equals("0"))
								{
									Intent i = new Intent();

									i.setClass(getContext(), Book_Service_Activity.class);
									startActivity(i);

								} else {

									Intent i = new Intent();
									i.putExtra("open", "address");
									i.setClass(getContext(), Addressactivity.class);
									startActivity(i);
								}




							} else
							{
								startActivity(new Intent(getActivity(), Book_Service_Activity.class));

							}

						}catch (Exception e){
							//	SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);


						}
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
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@NonNull
		@Override
		public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LayoutInflater _inflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _inflater.inflate(R.layout.service_list_custom, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}

		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;

           final CardView card = _view.findViewById(R.id.card);
			final TextView service_name = _view.findViewById(R.id.name);
			final TextView service_desc = _view.findViewById(R.id.desc);
			final TextView price = _view.findViewById(R.id.price);
			final ImageView img = _view.findViewById(R.id.img);



			try{



				service_name.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("sname")).toString());

				service_desc.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("sdesc")).toString());

				price.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("price")).toString());

				String img_url = Objects.requireNonNull(array_map_top_services.get(_position).get("image")).toString();
				Glide.with(getContext())
						.load(Uri.parse(img_url))
						.thumbnail(0.01f)
						.into(img);


				card.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						try {
							SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
							if(!sh.getString("token", "").equals("")) {


								if(!sh.getString("customer_pincode", "").equals("0"))
								{
									Intent i = new Intent();

									i.setClass(getContext(), Book_Service_Activity.class);
									startActivity(i);

								} else {

									Intent i = new Intent();
									i.putExtra("open", "address");
									i.setClass(getContext(), Addressactivity.class);
									startActivity(i);
								}




							} else
							{
								startActivity(new Intent(getActivity(), Book_Service_Activity.class));

							}

						}catch (Exception e){
						//	SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);


				}
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
	public class Viewpager1Adapter extends PagerAdapter {
		Context _context;
		ArrayList<HashMap<String, Object>> _data;
		public Viewpager1Adapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
			_context = _ctx;
			_data = _arr;
		}

		public Viewpager1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_context = getContext();
			_data = _arr;
		}

		@Override
		public int getCount() {
			return _data.size();
		}

		@Override
		public boolean isViewFromObject(View _view, Object _object) {
			return _view == _object;
		}

		@Override
		public void destroyItem(ViewGroup _container, int _position, Object _object) {
			_container.removeView((View) _object);
		}

		@Override
		public int getItemPosition(Object _object) {
			return super.getItemPosition(_object);
		}

		@Override
		public CharSequence getPageTitle(int pos) {
			// use the activitiy event (onTabLayoutNewTabAdded) in order to use this method
			return "page " + pos;
		}

		@Override
		public  Object instantiateItem(ViewGroup _container,  final int _position) {
			View _view = LayoutInflater.from(_context).inflate(R.layout.custom_slider, _container, false);

			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);



			cardview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {



				}
			});


			Glide.with(_context)
					.load(Uri.parse(Objects.requireNonNull(listmap.get(_position).get("image")).toString()))
					.thumbnail(0.01f)
					.into(imageview1);

			_container.addView(_view);
			return _view;
		}
	}






}
