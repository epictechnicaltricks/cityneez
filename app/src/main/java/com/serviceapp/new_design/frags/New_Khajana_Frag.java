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
import com.serviceapp.activity.Addressactivity;
import com.serviceapp.activity.Book_Service_Activity;
import com.serviceapp.chicken_order_App.Order_chicken;
import com.serviceapp.new_design.activity.LayoutActivity;
import com.serviceapp.new_design.activity.Product_details_activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class New_Khajana_Frag extends  Fragment  {

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

	ImageView menu, back2;


@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.new_frag_khajana, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {

	viewpager1 = _view.findViewById(R.id.viewpager1);
    bookService = _view.findViewById(R.id.book_service);
	back2 = _view.findViewById(R.id.back2);

user_name = _view.findViewById(R.id.user_name);
menu = _view.findViewById(R.id.menu);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		recyclerview2 = _view.findViewById(R.id.recyclerview2);
		back2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
			//	getParentFragmentManager().beginTransaction().replace(R.id.frame, new New_Home_Frag(), null).addToBackStack(null).commit();
				requireActivity().finish();
			}
		});

	}






	private void initializeLogic() {

/*	menu.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			//Util.showMessage(getContext(), "Hello");
			((LayoutActivity) requireActivity()).openDrawer();
		}
	});*/


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
			_item.put("image", "https://thesmmhub.com/NawabiBiryani/img/img/banner.png");
			listmap.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://assets.limetray.com/assets/image_manager/uploads/7639/01-new-bannerwebbanner1920-617_1b.png");
			listmap.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://media.slidesgo.com/storage/15277355/international-hot-spicy-food-day-minitheme1639141970.jpg");
			listmap.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://marketplace.canva.com/EAFanHj4_og/1/0/1600w/canva-yellow-red-modern-food-promotion-banner-landscape-D5j43WWUmtA.jpg");
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
			_item.put("image", "https://b.zmtcdn.com/data/pictures/2/3401492/8f3ea7919e016474ca24bb991bb9ba71.jpg");
			_item.put("sname","Chimmanlal Puri Wale");
			_item.put("sdesc","Chimmanlal Puri Wale North Indian.");
			_item.put("price","₹100");
			array_map_top_services.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/pictures/1/3401611/14222536c2068f5fd1ab59238fe764ae.jpg");
			_item.put("sname","Parata");
			_item.put("sdesc","Odisha Parata");
			_item.put("price","₹60");
			array_map_top_services.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/dish_photos/c56/9ab654776d62e3fc530b175099114c56.jpg?output-format=webp");
			_item.put("sname","Masala Dosa");
			_item.put("sdesc","South Indian, Chinese, Nort");
			_item.put("price","₹100");
			array_map_top_services.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/pictures/1/19141421/09e7e40ef2591f14cf7649dfe2a4fddf_o2_featured_v2.jpg");
			_item.put("sname","Biriyani");
			_item.put("sdesc","Hyderabad Dum Biriyani");
			_item.put("price","₹150");
			array_map_top_services.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/dish_photos/62e/1bc4cb7e22aa217f47476e9f85c7a62e.jpg");
			_item.put("sname","Pizza");
			_item.put("sdesc","Ultimate Pizza");
			_item.put("price","₹330");
			array_map_top_services.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Panta_Ilish.jpg/800px-Panta_Ilish.jpg");
			_item.put("sname","Pakhal");
			_item.put("sdesc","Taste is Best, Odisha Pakhal");
			_item.put("price","₹200");
			array_map_top_services.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/dish_photos/259/0fda922de1a4bb811d947618767a8259.jpg");
			_item.put("sname","Paneer Curry");
			_item.put("sdesc","Butter mix");
			_item.put("price","₹100");
			array_map_top_services.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/dish_photos/b3c/643bf49a78734ae361eac7bcb1d28b3c.jpg?output-format=webp");
			_item.put("sname","Birthday Cake");
			_item.put("sdesc","Birthday Cake");
			_item.put("price","₹570");
			array_map_top_services.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/dish_photos/414/10f3db48b449023be44d8c25a99ac414.jpg");
			_item.put("sname","Jusy Chicken");
			_item.put("sdesc","Chicken juicy ..");
			_item.put("price","₹150");
			array_map_top_services.add(_item);
		}

		Collections.shuffle(array_map_top_services);

		recyclerview1.setAdapter(new Recyclerview1Adapter(array_map_top_services));
		recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));

		recyclerview2.setAdapter(new Recyclerview2Adapter(array_map_top_services));
		recyclerview2.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerview2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, true));




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


							startActivity(new Intent(getContext(), Order_chicken.class));


						/*	SharedPreferences sh = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
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
*/
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

			final CardView cardview1 = _view.findViewById(R.id.cardview1);
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
