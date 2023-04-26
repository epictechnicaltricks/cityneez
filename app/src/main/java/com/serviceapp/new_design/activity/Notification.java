package com.serviceapp.new_design.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.serviceapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Notification extends AppCompatActivity {


    RecyclerView recyclerview2;
    ArrayList<HashMap<String, Object>> array_map_top_services = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_notification_activity);
        recyclerview2 = findViewById(R.id.recyclerview2);
        category_data();

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    public void add_To_cart(View view)
    {
        finish();
    }

    public void category_data()
    {


        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/ba21abee-4250-e9b5-0c64-05d9c1eefd1a/original/Rich-Lamb-Curry-Cut-(Small,-16---20-Pieces)-Hero-Shot_(1).jpg");
            _item.put("sname","On the Way");
            _item.put("sdesc","Your payment has been received and processed.");
            _item.put("price","02:03 PM, January 2nd 2023");
            array_map_top_services.add(_item);
        }
        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/54ea3781-7d18-5ee5-fdd9-1a41b34fdd52/original/p2_tile_images_6th_folder-06_(3).jpg");
            _item.put("sname","Order Delivered");
            _item.put("sdesc","Your account balance is below the minimum threshold.");
            _item.put("price","05:26 AM, August 19th 2023");
            array_map_top_services.add(_item);
        }
        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/3f2a1744-28d0-534f-dde7-6121d6b033f8/original/p2_tile_images_6th_folder-15.jpg");
            _item.put("sname","Password Changed ");
            _item.put("sdesc","Your password has been changed successfully.");
            _item.put("price","03:10 AM, November 17th 2023");
            array_map_top_services.add(_item);
        }
        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/a917c576-c350-375a-afad-4882c7fd85a8/original/Classic-Eggs---Pack-of-12-Hero-Shot.jpg");
            _item.put("sname","Flight Delay");
            _item.put("sdesc","Your flight has been delayed by 2 hours.");
            _item.put("price","11:47 AM, April 28th 2023");
            array_map_top_services.add(_item);
        }
        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("image", "https://dao54xqhg9jfa.cloudfront.net/OMS-ProductMerchantdising/5a25a5e9-6463-636b-002f-899fb943fb03/original/Cage-Free-Eggs---Pack-Of-6---pr_mokg6lllq2---Hero-Shot.jpg");
            _item.put("sname","Assignment Submission");
            _item.put("sdesc","Don't forget to submit your assignment by midnight.");
            _item.put("price","09:54 PM, February 5th 2023");
            array_map_top_services.add(_item);
        }
        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("image", "https://dao54xqhg9jfa.cloudfront.net/ProductMerchantdising/4f003ba8-d470-247b-435d-422a1a57f984/original/1591960259.2414--2020-06-1216_40_59--738.jpeg");
            _item.put("sname","Package Delivered ");
            _item.put("sdesc","Your package has been delivered successfully.");
            _item.put("price","03:21 PM, June 15th 2023");
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
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View _v = _inflater.inflate(R.layout.notification_custom, null);
            RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            _v.setLayoutParams(_lp);
            return new ViewHolder(_v);
        }

        @Override
        public void onBindViewHolder(ViewHolder _holder, final int _position) {
            View _view = _holder.itemView;

            final CardView card = _view.findViewById(R.id.card);
            final TextView name = _view.findViewById(R.id.name);
            final TextView desc = _view.findViewById(R.id.desc);
            final TextView time = _view.findViewById(R.id.time);


            try{






                name.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("sname")).toString());
                desc.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("sdesc")).toString());

                time.setText(Objects.requireNonNull(array_map_top_services.get(_position).get("price")).toString());
/*
                String img_url = Objects.requireNonNull(array_map_top_services.get(_position).get("image")).toString();

                Glide.with(getApplicationContext())
                        .load(Uri.parse(img_url))
                        .thumbnail(0.01f)
                        .into(img);*/

         /*       plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                   *//*     long val = (long) (Double.parseDouble(qty.getText().toString()) + 1);
                        if(val<100)
                        {

                            qty.setText(String.valueOf(val));

                        }
*//*
                    }
                });

                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       *//* long val = (long) (Double.parseDouble(qty.getText().toString()) - 1);
                        if(val>0)
                        {

                            qty.setText(String.valueOf(val));

                        }*//*
                    }
                });
*/


                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


startActivity(new Intent(getApplicationContext(), Checkout_activity.class));

                      /*  try {
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
                Toast.makeText(Notification.this, e.toString(), Toast.LENGTH_SHORT).show();
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