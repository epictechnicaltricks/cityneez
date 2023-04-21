package com.serviceapp.chicken_order_App;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.serviceapp.R;
import com.serviceapp.RadialProgressView;
import com.serviceapp.RequestNetwork;
import com.serviceapp.RequestNetworkController;
import com.serviceapp.Util;
import com.serviceapp.activity.Login;
import com.serviceapp.new_design.activity.LayoutActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;



public class Order_chicken extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
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

    String ex;
    int  hour1,min1;
   String price_from_api;

    String category_name,category_id;
    String service_name;
    String default_description, default_description_id;
    String payment_mode_name,payment_id="";
    //String service_type="";

    RadioButton yes, no;
    LinearLayout other_person_layout, desc_layout,  other_person_layout_pickup;

    EditText person_address, person_number, person_name, desc, person_pincode, person_email;

    private RequestNetwork category_api;
    private RequestNetwork.RequestListener _category_api_listener;

    private RequestNetwork service_api;
    private RequestNetwork.RequestListener _service_api_listener;

    private RequestNetwork default_desc_api;
    private RequestNetwork.RequestListener _default_desc_listener;

    private RequestNetwork book_now_api;
    private RequestNetwork.RequestListener _book_now__api_listener;

    TextView price;
    String[] courses = { "Kg"};

    String sd="", st="", order_type;

    TextView date_text;


    private RequestNetwork product_api;
    private RequestNetwork.RequestListener _api_listener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_chicken);


        date_text = findViewById(R.id.date_text);
        product_api = new RequestNetwork(this);

        _api_listener= new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                try {

                    if (response.contains("200")) {
                        HashMap<String, Objects> map;
                        map = new Gson().fromJson(response, new TypeToken<HashMap<String, Object>>() {}.getType());
                        String values = (new Gson()).toJson(map.get("user"), new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
                        category_list = new Gson().fromJson(values, new TypeToken<ArrayList<HashMap<String, Object>>>() {}.getType());
                        Collections.reverse(category_list);
                        listview1.setAdapter(new Listview1Adapter(category_list));
                        ((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
                        spinner_category.setAdapter(new Listview1Adapter(category_list));





                    } else {

                       category_list.clear();
                        Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {

                    Log.d("api_error", e.toString());
                    Toast.makeText(getApplicationContext(), "Error on API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Toast.makeText(Order_chicken.this, "No internet!", Toast.LENGTH_SHORT).show();
            }
        };

        API();
        Spinner spino = findViewById(R.id.qty_type_spinner);
        spino.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                courses);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spino.setAdapter(ad);




        desc_layout = findViewById(R.id.description_layout);
        desc_layout.setVisibility(View.GONE);
        person_address = findViewById(R.id.person_address);
        person_number = findViewById(R.id.person_number);
        person_name = findViewById(R.id.person_name);
        desc = findViewById(R.id.desc);
        person_pincode = findViewById(R.id.person_pincode);
        person_email = findViewById(R.id.person_email);


        price = findViewById(R.id.price);

        yes = findViewById(R.id.radio_yes);
        no = findViewById(R.id.radio_no);
        no.setChecked(true);
        other_person_layout = findViewById(R.id.other_person_layout);
        other_person_layout_pickup =findViewById(R.id.other_person_layout_pickup);
        other_person_layout.setVisibility(View.GONE);
        other_person_layout_pickup.setVisibility(View.GONE);

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


        desc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!charSequence.toString().equals(""))
                {
                    price.setText("Price: ₹"+(Double.parseDouble(desc.getText().toString()))*Double.parseDouble(price_from_api)+"");

                 /*   switch (category_name)
                    {
                        case "Chicken": price.setText("Price: ₹"+(Double.parseDouble(charSequence.toString()))*220+"");
                            break;
                        case "Fish": price.setText("Price: ₹"+(Double.parseDouble(charSequence.toString()))*100+"");
                            break;
                        case "Prawn": price.setText("Price: ₹"+(Double.parseDouble(charSequence.toString()))*450+"");
                            break;

                    }*/
                }else {

                    price.setText("Price : ₹0");
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

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


        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                try {
                    // category_id = Objects.requireNonNull(category_list.get(i).get("category_id")).toString();
                  /*  category_name = Objects.requireNonNull(category_list.get(i).get("name")).toString();
                    listview2.setAdapter(new Listview2Adapter(service_list));
                    ((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
                    spinner_service.setAdapter(new Listview2Adapter(service_list));*/
                    desc.setText("1");
                    category_name = Objects.requireNonNull(category_list.get(i).get("product_name")).toString();
                    if(!desc.toString().equals(""))
                    {

                        price.setText("Price: ₹"+(Double.parseDouble(desc.getText().toString()))*Double.parseDouble(price_from_api)+"");

                       /* switch (category_name)
                        {


                            case "Chicken": price.setText("Price: ₹"+(Double.parseDouble(desc.getText().toString()))*220+"");
                                break;
                            case "Fish": price.setText("Price: ₹"+(Double.parseDouble(desc.getText().toString()))*100+"");
                                break;
                            case "Prawn": price.setText("Price: ₹"+(Double.parseDouble(desc.getText().toString()))*450+"");
                                break;

                        }*/
                    } else {

                        price.setText("Price : ₹0");
                    }
                }catch (Exception e)

                {
Util.showMessage(getApplicationContext(), e.toString());
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        desc_layout.setVisibility(View.VISIBLE);
        spinner_service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                service_name = Objects.requireNonNull(service_list.get(i).get("name")).toString();
                if(Objects.requireNonNull(service_list.get(i).get("name")).toString().equals("Other")){

                    desc_layout.setVisibility(View.VISIBLE);
                    //showMessage(class_id);
                } else {

                    //default_description = Objects.requireNonNull(service_type_list.get(_position).get("category_defaultdesc")).toString();
                    //showMessage(year_id)
                    //default_desc_API_request(service_id);
                    desc_layout.setVisibility(View.GONE);
                }
               /* if(!Objects.requireNonNull(service_list.get(i).get("id")).toString().equals("")){

                    //service_id = Objects.requireNonNull(service_list.get(i).get("id")).toString();
                      //showMessage(year_id)
                    //default_desc_API_request(service_id);
                }*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_service_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int _position, long l) {

                order_type = Objects.requireNonNull(service_type_list.get(_position).get("name"))+"";
                if(Objects.requireNonNull(service_type_list.get(_position).get("name")).toString().equals("HOME DELIVERY")) {
                    order_type = "HOME";

                }
                    if(Objects.requireNonNull(service_type_list.get(_position).get("name")).toString().equals("HOME DELIVERY")){

                    other_person_layout.setVisibility(View.VISIBLE);
                    other_person_layout_pickup.setVisibility(View.VISIBLE);
                    //showMessage(class_id);
                } else {

                    //default_description = Objects.requireNonNull(service_type_list.get(_position).get("category_defaultdesc")).toString();
                    //showMessage(year_id)
                    //default_desc_API_request(service_id);
                    other_person_layout.setVisibility(View.GONE);
                    other_person_layout_pickup.setVisibility(View.VISIBLE);

                }
                //Toast.makeText(Order_chicken.this, "selected "+ _position, Toast.LENGTH_SHORT).show();





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


    private void get_all_products_request() {
        // Toast.makeText(this, "LOADING ALL PRODUCTS", Toast.LENGTH_LONG).show();
        product_api.startRequestNetwork(RequestNetworkController.GET,
                "https://cityneedzapi.000webhostapp.com/chicken-api/product_show.php",
                "no tag", _api_listener);
    }


    private void init()
    {
        insert_to_spinner();
        refresh_spinner();
        //category_API_request();
        get_all_products_request();


        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        if(sh.getString("token", "").equals("")) {
            yes.setChecked(true);
           no.setEnabled(false);

        }

    }


    private void API()
    {
       /* category_api = new RequestNetwork(this);
        service_api = new RequestNetwork(this);
        default_desc_api = new RequestNetwork(this);*/



        book_now_api = new RequestNetwork(this);

        ////API LISTENERS

    /*    _category_api_listener = new RequestNetwork.RequestListener() {
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
        };*/

        _book_now__api_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String tag, String response, HashMap<String, Object> responseHeaders) {
                _telegramLoaderDialog(false);
                Log.d("_api_response_booked",response);

                if(order_type.equals("PICKUP"))
                {
                    androidx.appcompat.app.AlertDialog.Builder al = new androidx.appcompat.app.AlertDialog.Builder(Order_chicken.this);
                    al.setTitle("ORDER SUCCESS");

                    al.setCancelable(false);

                    al.setMessage("Your pickup address is \n\nRoom- 208, 2nd Floor BMC Panchadeep Complex\n" +
                            "Unit 4, Bhubaneswar, Odisha, 751001\n" +
                            "Mobile no: 0674-2393123\n" +
                            "\n\n");
                    al.setPositiveButton("View Direction on Map", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse("https://www.google.com/maps/dir//GVI+TECHNOLOGY,+Room+702,+Panchadeep+Complex,+Unit+4,+Bhouma+Nagar,+Bhubaneswar,+Odisha+751001/@20.2767877,85.8317319,17z/data=!4m8!4m7!1m0!1m5!1m1!1s0x3a1909b628cfe7c5:0xb6f72fb5d526cb6!2m2!1d85.8317319!2d20.2767877?hl=en"));
                            startActivity(i);


                        }
                    });
                    al.setNegativeButton("exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            startActivity(new Intent(getApplicationContext(), Order_chicken.class));
                            finish();
                        }
                    });

                    al.create().show();
                }else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Order_chicken.this);

                    builder.setMessage("You have successfully booked we will contact you soon.");


                    builder.setTitle("Booked Successful");

                    // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                    builder.setCancelable(false);

                    // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                    builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {

                        startActivity(new Intent(getApplicationContext(), Order_chicken.class));
                        finish();

                        //Toast.makeText(Order_chicken.this, "SUCCESSFULLY BOOKED", Toast.LENGTH_LONG).show();
                    });

                    builder.create().show();
                }

               // startActivity(new Intent(getApplicationContext(), HomeActivity.class));
               // finish();
                //Util.showMessage(getApplicationContext(), "Booked Successful");
            }

            @Override
            public void onErrorResponse(String tag, String message) {
                Util.showMessage(getApplicationContext(), message);
                _telegramLoaderDialog(false);
            }
        };



    }



/*    private void category_API_request() {

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


    }*/

    private void book_service() {



       // SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);


        String order_id = Util.Generate_Order_id();
        //Toast.makeText(this, order_id +"  "+sd +"  "+st, Toast.LENGTH_SHORT).show();
        if(order_type.equals("HOME"))
        {


        if(!person_address.getText().toString().trim().equals("")
                && !person_name.getText().toString().trim().equals("")
                &&  person_number.getText().toString().length()==10
                &&  person_email.getText().toString().trim().contains("@")
                && !person_pincode.getText().toString().trim().equals("") && person_pincode.getText().toString().length()==6 && !desc.getText().toString().trim().equals(""))
        {
            _telegramLoaderDialog(true);


                ////////////////
            book_now_api.startRequestNetwork(RequestNetworkController.GET,
                    "https://cityneedzapi.000webhostapp.com/chicken-api/bookService.php?booked_user_id=" +
                            "123456&booked_user_type=1" +
                            "&bookservice_categoryid=3" +
                            "&bookservice_categoryname=" + category_name +
                            "&bookservice_qty=" + desc.getText().toString().trim() +"KG"+
                            "&bookservice_defaultdescription=" +  price.getText().toString().trim() +
                            "&bookservice_description=" +  "null" +
                            "&bookservice_location=" + person_address.getText().toString().trim() +
                            "&bookservice_contactperson=" +  person_name.getText().toString().trim() +
                            "&bookservice_mobileno=" + person_number.getText().toString().trim() +
                            "&bookservice_areapincode=" + person_pincode.getText().toString().trim() +
                            "&bookservice_paymentmode=" +payment_mode_name +
                            "&bookservice_emailid=" + person_email.getText().toString().trim() +
                            "&booked_order_id=" + order_id +
                            "&booked_user_time=" +  st +
                            "&booked_user_date=" +  sd +
                            "&bookservice_order_type=" + order_type,
                    "no tag", _book_now__api_listener);



            }

            else {Util.showMessage(getApplicationContext(), "Please fill details correctly!");}

        }

        else {


            if(person_number.getText().toString().length()==10
                    &&  person_email.getText().toString().trim().contains("@"))
            {

                _telegramLoaderDialog(true);
                book_now_api.startRequestNetwork(RequestNetworkController.GET,
                        "https://cityneedzapi.000webhostapp.com/chicken-api/bookService.php?booked_user_id=123456&booked_user_type=1&bookservice_categoryid=3&bookservice_categoryname="+category_name+"&bookservice_qty="+desc.getText().toString().trim()+"KG&bookservice_defaultdescription="+price.getText().toString()+"&bookservice_description=tyrt&bookservice_location=odisha&bookservice_contactperson=GVIT Developer&bookservice_mobileno="+person_number.getText().toString().trim() +"&bookservice_areapincode=761012&bookservice_paymentmode=UPI&bookservice_emailid=" + person_email.getText().toString().trim() +"&booked_order_id="+order_id+"&booked_user_time="+st+"&booked_user_date="+sd+"&bookservice_order_type="+order_type,
                        "no tag", _book_now__api_listener);
            }else {

                Toast.makeText(this, "Invalid email or phone no", Toast.LENGTH_SHORT).show();
            }

        }




    }


    private void refresh_spinner(){
       /* listview1.setAdapter(new Listview1Adapter(category_list));
        ((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
        spinner_category.setAdapter(new Listview1Adapter(category_list));

        listview2.setAdapter(new Listview2Adapter(service_list));
        ((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
        spinner_service.setAdapter(new Listview2Adapter(service_list));*/

        listview3.setAdapter(new Listview3Adapter(payment_mode_list));
        ((BaseAdapter)listview3.getAdapter()).notifyDataSetChanged();
        spinner_payment_mode.setAdapter(new Listview3Adapter(payment_mode_list));

        listview4.setAdapter(new Listview4Adapter(service_type_list));
        ((BaseAdapter)listview4.getAdapter()).notifyDataSetChanged();
        spinner_service_type.setAdapter(new Listview4Adapter(service_type_list));




    }

    private void insert_to_spinner(){


      /*  {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Chicken");
            category_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Fish");
            category_list.add(_item);
        }


        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Prawn");
            category_list.add(_item);
        }
//////////////////////////////////////////////////////////


        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "1Kg");
            service_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "2Kg");
            service_list.add(_item);
        }



        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Other");
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

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "PICKUP");
            service_type_list.add(_item);
        }

        {
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "HOME DELIVERY");
          service_type_list.add(_item);
        }

        /*{
            HashMap<String, Object> _item = new HashMap<>();
            _item.put("name", "Other");
            service_type_list.add(_item);
        }
*/



        //////////

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ((TextView) adapterView.getChildAt(0)).setTextColor(Color.RED);
        ((TextView) adapterView.getChildAt(0)).setTextSize(19);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /////////////////////////////////////////////
    /*
    {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("name", "NEW");
        service_type_list.add(_item);
    }

    {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("name", "REPAIR");
        service_type_list.add(_item);
    }

    {
        HashMap<String, Object> _item = new HashMap<>();
        _item.put("name", "OTHER");
        service_type_list.add(_item);
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
                textview1.setText(Objects.requireNonNull(category_list.get((int) _position).get("product_name")).toString().toUpperCase());
                price_from_api = category_list.get((int) _position).get("product_price")+"";


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
                textview1.setText(Objects.requireNonNull(service_list.get((int) _position).get("name")).toString());


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
                textview1.setText(Objects.requireNonNull(service_type_list.get((int) _position).get("name")).toString());


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
    public void showAlertDialogButtonClicked() {
        // Create an alert builder
        final AlertDialog builder = new AlertDialog.Builder(Order_chicken.this).create();


        LayoutInflater inflater3 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View customLayout = inflater3.inflate(R.layout.pick_date_time_dialog,null);

        builder.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        builder.setView(customLayout);
        builder.setCancelable(false);
        // set the custom layout

        CardView date_card = customLayout.findViewById(R.id.date_card);
        CardView time_card = customLayout.findViewById(R.id.time_card);
        TextView done = customLayout.findViewById(R.id.done);
        TextView close = customLayout.findViewById(R.id.close);
        TextView date1 = customLayout.findViewById(R.id.date_);
        TextView time1 = customLayout.findViewById(R.id.time_);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(!date1.getText().toString().equals("Select Date.") || !time1.getText().toString().equals("Select Time."))
               {
                   sd = date1.getText().toString().trim();
                   st = time1.getText().toString().trim();
                    builder.dismiss();
               } else {

                   Util.showMessage(getApplicationContext(), "Please select date and time");
               }
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.dismiss();
            }
        });

        date_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _DateDialog(date1);
            }
        });


        time_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _TimeDialog(time1);
            }
        });

        // create and show the alert dialog
      builder.show();


    }




    public void book(View view) {
       // book_service();
        //Toast.makeText(this, category_name+"\n"+service_name+"\n"+default_description, Toast.LENGTH_LONG).show();
     //   finish();
        //startActivity(new Intent(getApplicationContext(), Order_chicken.class));



        if(sd.equals("") || st.equals(""))
        {
            showAlertDialogButtonClicked();
        } else {

           // Toast.makeText(this, sd + " - " + st, Toast.LENGTH_SHORT).show();

            book_service();
        }

        /*AlertDialog.Builder builder = new AlertDialog.Builder(Order_chicken.this);

        builder.setMessage("You have successfully ordered");


        builder.setTitle("Ordered Successful");

        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);

        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("OK", (DialogInterface.OnClickListener) (dialog, which) -> {


        });

        builder.create().show();*/
    }

    public void close(View view) {
        //Toast.makeText(this, "Booked Successful", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), LayoutActivity.class));
        finish();
        //startActivity(new Intent(getApplicationContext(), Order_chicken.class));
    }

    public void _DateDialog (TextView _textview) {
        DatePickerDialog.OnDateSetListener datePickerListener = (view, year, month, day) -> {
           month++;

            String final_date ="";
            if(month<10)
            {
                String new_month="0"+month;

                if(day<10)
                {
                    String new_day="0"+day;
                    final_date = year + "-" + new_month + "-" + new_day;

                } else {

                    final_date = year + "-" + new_month + "-" + day;
                }


            } else {

                if(day<10)
                {
                    String new_day="0"+day;
                    final_date = year + "-" + month + "-" + new_day;

                } else {

                    final_date = year + "-" + month + "-" + day;
                }
            }



            _textview.setText(final_date);
            date_text.setText("Selected : "+final_date + "\t" + st +"\n now Click Order now button");
            // sd = day;
        };
        showDatePicker(datePickerListener);
    }

    public void showDatePicker(DatePickerDialog.OnDateSetListener listener) {

        /*Calendar c = Calendar.getInstance();
        c.set(nowyear-21, nowmonth,nowday);*/



        // this will provide the 2001 =  now year(2022) - 21 years
        // it will change by time automatic


        DatePickerDialog datePickerDialog= new DatePickerDialog(Order_chicken.this);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        datePickerDialog.setOnDateSetListener(listener);
        datePickerDialog.show();

       /* DatePickerDialog datePicker = new DatePickerDialog(context);
        // datePicker.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(0));
        datePicker.setOnDateSetListener(listener);
        datePicker.show();*/
    }



    public void _TimeDialog(TextView textView){

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int  mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        textView.setText(hourOfDay + ":" + minute);


                        String am_pm = "";

                        Calendar datetime = Calendar.getInstance();
                        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        datetime.set(Calendar.MINUTE, minute);

                        if (datetime.get(Calendar.AM_PM) == Calendar.AM)
                            am_pm = "AM";
                        else if (datetime.get(Calendar.AM_PM) == Calendar.PM)
                            am_pm = "PM";

                        String strHrsToShow = (datetime.get(Calendar.HOUR) == 0) ?"12":datetime.get(Calendar.HOUR)+"";

                        textView.setText( strHrsToShow+":"+datetime.get(Calendar.MINUTE)+" "+am_pm);

                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }


    public void _telegramLoaderDialog(final boolean _visibility) {

        if (_visibility) {
            if (coreprog == null){
                coreprog = new ProgressDialog(this);
                coreprog.setCancelable(false);
                coreprog.setCanceledOnTouchOutside(false);

                coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                coreprog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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