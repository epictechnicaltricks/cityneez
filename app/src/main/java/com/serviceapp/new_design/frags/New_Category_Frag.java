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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class New_Category_Frag extends  Fragment  {





	private RecyclerView recyclerview2, recyclerview3, recyclerview4;


	ArrayList<HashMap<String, Object>> array_map_top_services = new ArrayList<>();

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
		recyclerview3.setAdapter(new Recyclerview2Adapter(array_map_top_services));
		recyclerview3.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
		recyclerview4.setAdapter(new Recyclerview2Adapter(array_map_top_services));
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

}
