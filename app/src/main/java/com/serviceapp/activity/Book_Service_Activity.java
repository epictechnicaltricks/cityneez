package com.serviceapp.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serviceapp.R;
import com.serviceapp.RadialProgressView;
import com.serviceapp.RequestNetwork;
import com.serviceapp.RequestNetworkController;
import com.serviceapp.Util;

import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Book_Service_Activity extends AppCompatActivity {
    TextView login;

    private Spinner spinner_category,spinner_service,spinner_payment_mode,spinner_service_type;
            //spinner_description;

    ArrayList<HashMap<String, Object>> category_list = new ArrayList<>();

    ArrayList<HashMap<String, Object>> service_list = new ArrayList<>();

    ArrayList<HashMap<String, Object>> service_type_list = new ArrayList<>();

    ArrayList<HashMap<String, Object>> payment_mode_list = new ArrayList<>();


   // ArrayList<HashMap<String, Object>> default_description_list = new ArrayList<>();


    private ListView listview1;
    private ListView listview2;
    private ListView listview3;
    private ListView listview4;

   // private ListView listview5;


    String category_name,category_id;
    String service_name,service_id;
    String default_description, default_description_id;
    String payment_mode_name,payment_id="";
    //String service_type="";

    RadioButton yes, no;
    LinearLayout other_person_layout, desc_layout;

    EditText person_address, person_number, person_name, desc, person_pincode, person_email;

    private RequestNetwork category_api;
    private RequestNetwork.RequestListener _category_api_listener;

    private RequestNetwork service_api;
    private RequestNetwork.RequestListener _service_api_listener;

    private RequestNetwork default_desc_api;
    private RequestNetwork.RequestListener _default_desc_listener;

    private RequestNetwork book_now_api;
    private RequestNetwork.RequestListener _book_now__api_listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_a_service);

        API();

        desc_layout = findViewById(R.id.description_layout);
        desc_layout.setVisibility(View.GONE);
        person_address = findViewById(R.id.person_address);
        person_number = findViewById(R.id.person_number);
        person_name = findViewById(R.id.person_name);
        desc = findViewById(R.id.desc);
        person_pincode = findViewById(R.id.person_pincode);
        person_email = findViewById(R.id.person_email);


        yes = findViewById(R.id.radio_yes);
        no = findViewById(R.id.radio_no);
        no.setChecked(true);
        other_person_layout = findViewById(R.id.other_person_layout);

        other_person_layout.setVisibility(View.GONE);
        yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(yes.isChecked()) {
                    other_person_layout.setVisibility(View.VISIBLE);
                }else {
                    other_person_layout.setVisibility(View.GONE);
                }
            }
        });

        spinner_category = findViewById(R.id.spinner_categories);
        spinner_service = findViewById(R.id.spinner_services);
        spinner_payment_mode = findViewById(R.id.spinner_payment_mode);
        spinner_service_type = findViewById(R.id.spinner_services_type);
        //spinner_description = findViewById(R.id.spinner_description);

        listview1 =  findViewById(R.id.listview_categories);
        listview2 =  findViewById(R.id.listview_sevices);
        listview3 =  findViewById(R.id.listview_service_type);
        listview4 =  findViewById(R.id.listview_payment_mode);
        //listview5 =  findViewById(R.id.listview_default_desciption);


    /*    spinner_description.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int _position, long l) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });*/

        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                  category_id = Objects.requireNonNull(category_list.get(i).get("category_id")).toString();
                  category_name = Objects.requireNonNull(category_list.get(i).get("category_name")).toString();


                //  Util.showMessage(getApplicationContext(),category_id);
                   service_API_request(category_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!Objects.requireNonNull(service_list.get(i).get("id")).toString().equals("")){

                    service_id = Objects.requireNonNull(service_list.get(i).get("id")).toString();
                    service_name = Objects.requireNonNull(service_list.get(i).get("category_servicename")).toString();
                    //showMessage(year_id)
                    default_desc_API_request(service_id);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_service_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int _position, long l) {
                //Toast.makeText(Book_Service_Activity.this, "selected "+ _position, Toast.LENGTH_SHORT).show();

                try {
                    if(Objects.requireNonNull(service_type_list.get(_position).get("category_defaultdesc")).toString().equals("Other")){

                        desc_layout.setVisibility(View.VISIBLE);
                        //showMessage(class_id);
                    } else {

                        default_description = Objects.requireNonNull(service_type_list.get(_position).get("category_defaultdesc")).toString();
                        //showMessage(year_id)
                        //default_desc_API_request(service_id);
                        desc_layout.setVisibility(View.GONE);
                    }
                }catch (Exception e)
                {
                    Util.showMessage(getApplicationContext(), "Description not available");
                }

                    //showMessage(class_id);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_payment_mode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                payment_mode_name = Objects.requireNonNull(payment_mode_list.get(i).get("name")).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        init();

    }

    private void init()
    {
        insert_to_spinner();
        refresh_spinner();
        category_API_request();



        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        if(sh.getString("token", "").equals("")) {
            yes.setChecked(true);
           no.setEnabled(false);

        }

    }


    private void API()
    {
        category_api = new RequestNetwork(this);
        service_api = new RequestNetwork(this);
        default_desc_api = new RequestNetwork(this);
        book_now_api = new RequestNetwork(this);

        ////API LISTENERS

        _category_api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {

                Log.d("_api_response_cat",response);

                _telegramLoaderDialog(false);

                category_list.clear();
         if(response.contains(":200")) {

        HashMap<String , Objects> map;
        map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());
        String values = (new Gson()).toJson(map.get("user"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
        category_list = new Gson().fromJson(values, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
        listview1.setAdapter(new Listview1Adapter(category_list));
        ((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
        spinner_category.setAdapter(new Listview1Adapter(category_list));
         } else {
             Util.showMessage(getApplicationContext(), "Category not available");
         }

            }

            @Override
            public void onErrorResponse(String tag, String message) {
                _telegramLoaderDialog(false);
             Util.showMessage(getApplicationContext(), message);
            }
        };

        _service_api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                Log.d("_api_response_serv",response);

                _telegramLoaderDialog(false);

                service_list.clear();
                if(response.contains(":200")) {

                    HashMap<String , Objects> map;
                    map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());
                    String values = (new Gson()).toJson(map.get("user"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
                    service_list = new Gson().fromJson(values, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
                    listview2.setAdapter(new Listview2Adapter(service_list));
                    ((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
                    spinner_service.setAdapter(new Listview2Adapter(service_list));

                } else {
                    Util.showMessage(getApplicationContext(), "Service not available");
                }
            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Util.showMessage(getApplicationContext(), message);
                _telegramLoaderDialog(false);
            }
        };


        _default_desc_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                Log.d("_api_response_default_desc",response);
                _telegramLoaderDialog(false);
                service_type_list.clear();
                if(response.contains(":200")) {

                    HashMap<String , Objects> map;
                    map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>(){}.getType());
                    String values = (new Gson()).toJson(map.get("user"), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
                    service_type_list = new Gson().fromJson(values, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
                    listview4.setAdapter(new Listview4Adapter(service_type_list));
                    ((BaseAdapter)listview4.getAdapter()).notifyDataSetChanged();
                    spinner_service_type.setAdapter(new Listview4Adapter(service_type_list));

                } else {
                    Util.showMessage(getApplicationContext(), "Description not available");
                }
            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Util.showMessage(getApplicationContext(), message);
                _telegramLoaderDialog(false);
            }
        };

        _book_now__api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                _telegramLoaderDialog(false);
                Log.d("_api_response_booked",response);
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
                Util.showMessage(getApplicationContext(), "Booked Successful");
            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Util.showMessage(getApplicationContext(), message);
                _telegramLoaderDialog(false);
            }
        };



    }



    private void category_API_request() {

        _telegramLoaderDialog(true);

        category_api.startRequestNetwork(RequestNetworkController.GET,
                getResources().getString(R.string.api_path)+"getCategories.php",
                "no tag", _category_api_listener);
        Log.d("Categories_api",getResources().getString(R.string.api_path)+"getCategories.php");


    }

    private void service_API_request(String _category_id) {

        _telegramLoaderDialog(true);

        service_api.startRequestNetwork(RequestNetworkController.GET,
                getResources().getString(R.string.api_path)+"getService.php?category_id="+_category_id,
                "no tag", _service_api_listener);
        Log.d("getService_api",getResources().getString(R.string.api_path)+"getService.php?category_id="+_category_id);


    }

    private void default_desc_API_request(String _service_id) {

        _telegramLoaderDialog(true);

        default_desc_api.startRequestNetwork(RequestNetworkController.GET,
                getResources().getString(R.string.api_path)+"defaultDesc.php?service_id="+_service_id,
                "no tag", _default_desc_listener);

        Log.d("getService_api",getResources().getString(R.string.api_path)+"defaultDesc.php?service_id="+_service_id);


    }

    private void book_service() {

        _telegramLoaderDialog(true);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);


        if(yes.isChecked() )
        {

            if(!person_address.getText().toString().trim().equals("")
            || !person_name.getText().toString().trim().equals("")
                    || !person_number.getText().toString().trim().equals("")
                    || !person_email.getText().toString().trim().equals("") || !Patterns.EMAIL_ADDRESS.matcher(person_email.getText().toString().trim()).matches()
                    ||!person_pincode.getText().toString().trim().equals(""))
            {
                book_now_api.startRequestNetwork(RequestNetworkController.GET,
                        getResources().getString(R.string.api_path)+"bookService.php?" +
                                "booked_user_id="+ 0 +
                                "&booked_user_type=" + "guest" +
                                "&bookservice_categoryid=" + category_id +
                                "&bookservice_categoryname=" + category_name +
                                "&bookservice_name=car wasing" + service_name +
                                "&bookservice_defaultdescription=" + default_description +
                                "&bookservice_description=" + desc.getText().toString().trim() +
                                "&bookservice_location=" +   person_address.getText().toString().trim() +
                                "&bookservice_contactperson=" + person_name.getText().toString().trim() +
                                "&bookservice_mobileno=" +    person_number.getText().toString().trim() +
                                "&bookservice_areapincode=" + person_pincode.getText().toString().trim() +
                                "&bookservice_emailid=" + person_email.getText().toString().trim() +
                                "&bookservice_paymentmode=" + payment_mode_name,
                        "no tag", _book_now__api_listener);
            }

            else {Util.showMessage(getApplicationContext(), "Please fill all contact person details!");}

        }else
        {
            book_now_api.startRequestNetwork(RequestNetworkController.GET,
                    getResources().getString(R.string.api_path)+"bookService.php?" +
                            "booked_user_id="+ sharedPreferences.getString("user_id", "") +
                            "&booked_user_type=" + "login" +
                            "&bookservice_categoryid=" + category_id +
                            "&bookservice_categoryname=" + category_name +
                            "&bookservice_name=" + service_name +
                            "&bookservice_defaultdescription=" + default_description +
                            "&bookservice_description=" + desc.getText().toString().trim() +
                            "&bookservice_location=" + sharedPreferences.getString("customer_address", "") +
                            "&bookservice_contactperson=" + sharedPreferences.getString("customer_name", "") +
                            "&bookservice_mobileno=" + sharedPreferences.getString("customer_mobileno", "") +
                            "&bookservice_areapincode=" + sharedPreferences.getString("customer_pincode", "") +
                            "&bookservice_emailid=" + sharedPreferences.getString("customer_emailid", "") +
                            "&bookservice_paymentmode=" +payment_mode_name,
                    "no tag", _book_now__api_listener);
        }




    }


    private void refresh_spinner(){
      /*  listview1.setAdapter(new Listview1Adapter(category_list));
        ((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
        spinner_category.setAdapter(new Listview1Adapter(category_list));*/

      /*  listview2.setAdapter(new Listview2Adapter(service_list));
        ((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
        spinner_service.setAdapter(new Listview2Adapter(service_list));*/

        listview3.setAdapter(new Listview3Adapter(payment_mode_list));
        ((BaseAdapter)listview3.getAdapter()).notifyDataSetChanged();
        spinner_payment_mode.setAdapter(new Listview3Adapter(payment_mode_list));

      /*  listview4.setAdapter(new Listview4Adapter(service_type_list));
        ((BaseAdapter)listview4.getAdapter()).notifyDataSetChanged();
        spinner_payment_mode.setAdapter(new Listview4Adapter(service_type_list));*/
/*

        listview5.setAdapter(new Listview5Adapter(default_description_list));
        ((BaseAdapter)listview5.getAdapter()).notifyDataSetChanged();
        spinner_description.setAdapter(new Listview5Adapter(default_description_list));
*/



    }

    private void insert_to_spinner(){


       /* {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "PLUMBER SERVICE");
            category_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "IT SERVICE");
            category_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "AC SERVICE");
            category_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "CAR SERVICE");
            category_list.add(_item);
        }*/
//////////////////////////////////////////////////////////
      /*  {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "AC Cleaning");
            service_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Car Cleaning");
            service_list.add(_item);
        }


        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Computer Repair");
            service_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Bathroom Cleaning");
            service_list.add(_item);
        }


        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Car Cleaning");
            service_list.add(_item);
        }*/
/////////////////////////////////////////////////////////////////////////

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "CASH");
            payment_mode_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "UPI");
            payment_mode_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "ONLINE");
            payment_mode_list.add(_item);
        }



//////////////////////////

       /* {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "BASIC");
            service_type_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "PREMIUM");
          service_type_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Other");
            service_type_list.add(_item);
        }*/




        //////////

    }

    /////////////////////////////////////////////

/*

    {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("name", "NEW");
        default_description_list.add(_item);
    }

    {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("name", "REPAIR");
        default_description_list.add(_item);
    }

    {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("name", "OTHER");
        default_description_list.add(_item);
    }

*/

    ///////////

    ///Spinner adpters

    public class Listview1Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;
        public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }
        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LayoutInflater _inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View _view = _v;
            if (_view == null) {
                _view = _inflater.inflate(R.layout.clg_list_cus, null);
            }

            final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);


            try {
                textview1.setText(Objects.requireNonNull(category_list.get((int) _position).get("category_name")).toString());

            }catch (Exception e)
            {
                textview1.setText("Not available.");


            }





            return _view;
        }
    }

    public class Listview2Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;
        public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }
        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View _view = _v;
            if (_view == null) {
                _view = _inflater.inflate(R.layout.clg_list_cus, null);
            }

            final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);


            try {
                textview1.setText(Objects.requireNonNull(service_list.get((int) _position).get("category_servicename")).toString());


            }catch (Exception e)
            {
                textview1.setText("Not available.");


            }






            return _view;
        }
    }


    public class Listview3Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;
        public Listview3Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }
        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View _view = _v;
            if (_view == null) {
                _view = _inflater.inflate(R.layout.clg_list_cus, null);
            }

            final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);

                textview1.setText(Objects.requireNonNull(payment_mode_list.get((int) _position).get("name")).toString());





            return _view;
        }
    }


    public class Listview4Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;
        public Listview4Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }
        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View _view = _v;
            if (_view == null) {
                _view = _inflater.inflate(R.layout.clg_list_cus, null);
            }

            final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);


            try {
                textview1.setText(Objects.requireNonNull(service_type_list.get((int) _position).get("category_defaultdesc")).toString());


            }catch (Exception e)
            {
                textview1.setText("Not available.");


            }




            return _view;
        }
    }


    public class Listview5Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;
        public Listview5Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }
        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View _view = _v;
            if (_view == null) {
                _view = _inflater.inflate(R.layout.clg_list_cus, null);
            }

            final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);


            textview1.setText(Objects.requireNonNull(service_type_list.get((int) _position).get("name")).toString());



            return _view;
        }
    }






    public void book(View view) {
        book_service();
        //Toast.makeText(this, category_name+"\n"+service_name+"\n"+default_description, Toast.LENGTH_LONG).show();
     //   finish();
        //startActivity(new Intent(getApplicationContext(), Book_Service_Activity.class));
    }

    public void close(View view) {
        Toast.makeText(this, "Booked Successful", Toast.LENGTH_LONG).show();
        //finish();
        //startActivity(new Intent(getApplicationContext(), Book_Service_Activity.class));
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