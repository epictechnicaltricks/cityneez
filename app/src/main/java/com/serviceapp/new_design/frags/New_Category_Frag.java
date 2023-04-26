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
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.serviceapp.new_design.activity.Show_fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class New_Category_Frag extends  Fragment  {





	private RecyclerView recyclerview2, recyclerview3, recyclerview4;


	ArrayList<HashMap<String, Object>> array_map_top_services = new ArrayList<>();
	ArrayList<HashMap<String, Object>> array_map_top_services2 = new ArrayList<>();
	ArrayList<HashMap<String, Object>> array_map_top_services3 = new ArrayList<>();

@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.new_frag_category, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {

		recyclerview2 = _view.findViewById(R.id.recyclerview2);
		recyclerview3 = _view.findViewById(R.id.recyclerview3);
		recyclerview4 = _view.findViewById(R.id.recyclerview4);


	}






	private void initializeLogic() {

	category_data();
	foody();
	khana_khajana();
	}


	public void category_data()
	{
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://www.centralac.in/wp-content/uploads/2021/12/Window-AC-Maintenance.jpg");
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





////////////////////


		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://cache.careers360.mobi/media/article_images/2023/2/13/18-online-courses-for-electrical-engineering.jpg");
			_item.put("sname","Electrical Services");
			_item.put("sdesc","Professional maintenance of AC for optimal performance and efficiency.");
			_item.put("price","₹500");
			array_map_top_services.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://homerepairservices.in/wp-content/uploads/2021/07/We-do-Laptop-MacBook-motherboard-repairing-in-Bhubaneswar.jpg");
			_item.put("sname","Computer Services");
			_item.put("sdesc","Professional maintenance of AC for optimal performance and efficiency.");
			_item.put("price","₹500");
			array_map_top_services.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://www.ozoneinfomedia.com/wp-content/uploads/2021/03/cctv-camera-installation-and-maintenance-in-Patna-by-Ozone-Infomedia.jpg");
			_item.put("sname","CCTV Camera Service");
			_item.put("sdesc","Car service includes inspection, maintenance, and repairs for optimal performance..");
			_item.put("price","₹1500");
			array_map_top_services.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQFpsWAzS1csxEqHizn0pMsaHwxrWZSPZDgIQIRMk5x8hcI9LIV_YESyQNXFgfp4xSyh0&usqp=CAU");
			_item.put("sname","Network Services");
			_item.put("sdesc","Professional plumbing services for repairs, installations, and maintenance.");
			_item.put("price","₹500");
			array_map_top_services.add(_item);
		}


		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://kgntraininginstitute.in/wp-content/uploads/2020/10/screen-0-1-1200x720.jpg");
			_item.put("sname","Mobile Services");
			_item.put("sdesc","Car service includes inspection, maintenance, and repairs for optimal performance..");
			_item.put("price","₹1500");
			array_map_top_services.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://3.imimg.com/data3/FR/NB/MY-8973450/printer-repairing-services-500x500.jpeg");
			_item.put("sname","Printer services");
			_item.put("sdesc","Professional plumbing services for repairs, installations, and maintenance.");
			_item.put("price","₹500");
			array_map_top_services.add(_item);
		}



		recyclerview2.setAdapter(new Recyclerview2Adapter(array_map_top_services));
		recyclerview2.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));






	}


	private void khana_khajana()
	{
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/pictures/2/3401492/8f3ea7919e016474ca24bb991bb9ba71.jpg");
			_item.put("sname","Chimmanlal Puri Wale");
			_item.put("sdesc","Chimmanlal Puri Wale North Indian.");
			_item.put("price","₹100");
			array_map_top_services2.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/pictures/1/3401611/14222536c2068f5fd1ab59238fe764ae.jpg");
			_item.put("sname","Parata");
			_item.put("sdesc","Odisha Parata");
			_item.put("price","₹60");
			array_map_top_services2.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/dish_photos/c56/9ab654776d62e3fc530b175099114c56.jpg?output-format=webp");
			_item.put("sname","Masala Dosa");
			_item.put("sdesc","South Indian, Chinese, Nort");
			_item.put("price","₹100");
			array_map_top_services2.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/pictures/1/19141421/09e7e40ef2591f14cf7649dfe2a4fddf_o2_featured_v2.jpg");
			_item.put("sname","Biriyani");
			_item.put("sdesc","Hyderabad Dum Biriyani");
			_item.put("price","₹150");
			array_map_top_services2.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/dish_photos/62e/1bc4cb7e22aa217f47476e9f85c7a62e.jpg");
			_item.put("sname","Pizza");
			_item.put("sdesc","Ultimate Pizza");
			_item.put("price","₹330");
			array_map_top_services2.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Panta_Ilish.jpg/800px-Panta_Ilish.jpg");
			_item.put("sname","Pakhal");
			_item.put("sdesc","Taste is Best, Odisha Pakhal");
			_item.put("price","₹200");
			array_map_top_services2.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/dish_photos/259/0fda922de1a4bb811d947618767a8259.jpg");
			_item.put("sname","Paneer Curry");
			_item.put("sdesc","Butter mix");
			_item.put("price","₹100");
			array_map_top_services2.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://b.zmtcdn.com/data/dish_photos/b3c/643bf49a78734ae361eac7bcb1d28b3c.jpg?output-format=webp");
			_item.put("sname","Birthday Cake");
			_item.put("sdesc","Birthday Cake");
			_item.put("price","₹570");
			array_map_top_services2.add(_item);
		}

		recyclerview3.setAdapter(new Recyclerview3Adapter(array_map_top_services2));
		recyclerview3.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));



	}


	private void foody()
	{

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/4cad2ee9-9257-9109-62a1-da4082b2cf36/original/sho.jpg");
			_item.put("sname","Chicken Curry Cut");
			_item.put("sdesc","The ideal pack for a big, delicious curry feast.");
			_item.put("price","₹230");
			array_map_top_services3.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/cbede953-c94c-3c48-a9a2-bad815fa1c3a/original/ChickenLegCurryCutpiecesHeroShot.jpg");
			_item.put("sname","Chicken Leg Curry Cut");
			_item.put("sdesc","Juicy bone-in leg pieces for delicious Curries or Biryanis.");
			_item.put("price","₹250");
			array_map_top_services3.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/9e8eaa76-7195-009a-7094-05b50bf1d41e/original/Goat-Boneless-Hero-Shot.jpg");
			_item.put("sname","Goat Boneless");
			_item.put("sdesc","25-34 pieces of cleaned, boneless Goat..");
			_item.put("price","₹600");
			array_map_top_services3.add(_item);
		}

		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/ba21abee-4250-e9b5-0c64-05d9c1eefd1a/original/Rich-Lamb-Curry-Cut-(Small,-16---20-Pieces)-Hero-Shot_(1).jpg");
			_item.put("sname","Lamb Curry Cut");
			_item.put("sdesc","15-22 bone-in & boneless pieces of trimmed lamb..");
			_item.put("price","₹630");
			array_map_top_services3.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/54ea3781-7d18-5ee5-fdd9-1a41b34fdd52/original/p2_tile_images_6th_folder-06_(3).jpg");
			_item.put("sname","Premium Mackerel");
			_item.put("sdesc","Premium Mackerel (Bangda) Medium - Whole, Cleaned\n" +
					"Also called Aiyla, Ayila, Ayala, Bangda..");
			_item.put("price","₹130");
			array_map_top_services3.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/3f2a1744-28d0-534f-dde7-6121d6b033f8/original/p2_tile_images_6th_folder-15.jpg");
			_item.put("sname","Rohu (Rui/Kannadi Kendai)");
			_item.put("sdesc","Rohu (Rui/Kannadi Kendai) Medium - Bengali Cut, No Head");
			_item.put("price","₹230");
			array_map_top_services3.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/a917c576-c350-375a-afad-4882c7fd85a8/original/Classic-Eggs---Pack-of-12-Hero-Shot.jpg");
			_item.put("sname","Classic Eggs - Pack Of 12");
			_item.put("sdesc","White shell eggs laid by healthy hens 12 Pieces");
			_item.put("price","₹130");
			array_map_top_services3.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/5a25a5e9-6463-636b-002f-899fb943fb03/original/Cage-Free-Eggs---Pack-Of-6---pr_mokg6lllq2---Hero-Shot.jpg");
			_item.put("sname","Cage Free Eggs");
			_item.put("sdesc","Cage Free Eggs - Pack Of 6");
			_item.put("price","₹70");
			array_map_top_services3.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("image", "https://dao54xqhg9jfa.cloudfront.net/ProductMerchantdising/4f003ba8-d470-247b-435d-422a1a57f984/original/1591960259.2414--2020-06-1216_40_59--738.jpeg");
			_item.put("sname","Freshwater/White Prawns");
			_item.put("sdesc","Freshwater prawns are mildly sweet and juicy ..");
			_item.put("price","₹150");
			array_map_top_services3.add(_item);
		}


		recyclerview4.setAdapter(new Recyclerview4Adapter(array_map_top_services3));
		recyclerview4.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));

	}


	@Override
	public void onPause() {

		super.onPause();
	}

	@Override
	public void onResume() {


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
			LayoutInflater _inflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);		View _v = _inflater.inflate(R.layout.top_service_custom, null);
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


price.setVisibility(View.GONE);
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


	public class Recyclerview3Adapter extends RecyclerView.Adapter<Recyclerview3Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview3Adapter(ArrayList<HashMap<String, Object>> _arr) {
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


				price.setVisibility(View.GONE);
				service_name.setText(Objects.requireNonNull(array_map_top_services2.get(_position).get("sname")).toString());

				//service_desc.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("desc")).toString());

				price.setText(Objects.requireNonNull(array_map_top_services2.get(_position).get("price")).toString());

				String img_url = Objects.requireNonNull(array_map_top_services2.get(_position).get("image")).toString();
				Glide.with(getActivity())
						.load(Uri.parse(img_url))
						.thumbnail(0.01f)
						.into(img);


				card.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {


						Intent i = new Intent();
						i.putExtra("chicken","false");
						i.setClass(getContext(), Show_fragment.class);
						startActivity(i);


					/*	try {
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


						}*/
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

	public class Recyclerview4Adapter extends RecyclerView.Adapter<Recyclerview4Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview4Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}

		@NonNull
		@Override
		public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LayoutInflater _inflater = (LayoutInflater) requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);		View _v = _inflater.inflate(R.layout.top_service_custom, null);
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


				price.setVisibility(View.GONE);
				service_name.setText(Objects.requireNonNull(array_map_top_services3.get(_position).get("sname")).toString());

				//service_desc.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("desc")).toString());

				price.setText(Objects.requireNonNull(array_map_top_services3.get(_position).get("price")).toString());

				String img_url = Objects.requireNonNull(array_map_top_services3.get(_position).get("image")).toString();
				Glide.with(getActivity())
						.load(Uri.parse(img_url))
						.thumbnail(0.01f)
						.into(img);


				card.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {


						Intent i = new Intent();
						i.putExtra("chicken","true");
						i.setClass(getContext(),  Show_fragment.class);
						startActivity(i);


					/*	try {
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


						}*/
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


}
