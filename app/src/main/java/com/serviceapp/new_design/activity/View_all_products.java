package com.serviceapp.new_design.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.serviceapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class View_all_products extends AppCompatActivity {

    RecyclerView recyclerview2;
    ArrayList<HashMap<String, Object>> array_map_top_services = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_products);
        recyclerview2 = findViewById(R.id.recyclerview2);
        top_service_data();
    }


    public void top_service_data()
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



        recyclerview2.setAdapter(new Recyclerview1Adapter(array_map_top_services));
        recyclerview2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }


    public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
        ArrayList<HashMap<String, Object>> _data;
        public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @NonNull
        @Override
        public Recyclerview1Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View _v = _inflater.inflate(R.layout.service_list_custom, null);
            RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            _v.setLayoutParams(_lp);
            return new Recyclerview1Adapter.ViewHolder(_v);
        }

        @Override
        public void onBindViewHolder(Recyclerview1Adapter.ViewHolder _holder, final int _position) {
            View _view = _holder.itemView;
            final CardView card = _view.findViewById(R.id.card);
            final TextView service_name = _view.findViewById(R.id.name);
            final TextView service_desc = _view.findViewById(R.id.desc);
            final TextView price = _view.findViewById(R.id.price);
            final ImageView img = _view.findViewById(R.id.img);






                service_name.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("sname")).toString());

                service_desc.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("sdesc")).toString());

                price.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("price")).toString());

                String img_url = Objects.requireNonNull(array_map_top_services.get(_position).get("image")).toString();
                Glide.with(getApplicationContext())
                        .load(Uri.parse(img_url))
                        .thumbnail(0.01f)
                        .into(img);


                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        startActivity(new Intent(getApplicationContext(), Product_details_activity.class));
                      /*  try {
                            SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
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