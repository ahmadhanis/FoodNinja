package com.slumberjer.foodninja;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    ListView lvrest;
    ArrayList<HashMap<String, String>> restlist;
    ArrayList<HashMap<String, String>> cartlist;
    double total;
    Spinner sploc;
    String userid,name,phone;
    Dialog myDialogCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvrest = findViewById(R.id.listviewRest);
        cartlist = new ArrayList<>();
        sploc = findViewById(R.id.spinner);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userid = bundle.getString("userid");
        name = bundle.getString("name");
        phone = bundle.getString("phone");
        loadRestaurant(sploc.getSelectedItem().toString());
        lvrest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, restlist.get(position).get("restid"), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,RestaurantActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("restid",restlist.get(position).get("restid"));
                bundle.putString("name",restlist.get(position).get("name"));
                bundle.putString("phone",restlist.get(position).get("phone"));
                bundle.putString("address",restlist.get(position).get("address"));
                bundle.putString("location",restlist.get(position).get("location"));
                bundle.putString("userid",userid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        sploc.setSelection(0,false);
        sploc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadRestaurant(sploc.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }



    private void loadRestaurant(final String loc) {
        class LoadRestaurant extends AsyncTask<Void,Void,String>{

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("location",loc);
                RequestHandler rh = new RequestHandler();
                restlist = new ArrayList<>();
                String s = rh.sendPostRequest
                        ("http://uumresearch.com/foodninja/php/load_restaurant.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
               // Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                restlist.clear();
                    try{
                        JSONObject jsonObject = new JSONObject(s);
                        JSONArray restarray = jsonObject.getJSONArray("rest");
                        Log.e("HANIS",jsonObject.toString());
                        for (int i=0;i<restarray.length();i++){
                            JSONObject c = restarray.getJSONObject(i);
                            String rid = c.getString("restid");
                            String rname = c.getString("name");
                            String rphone = c.getString("phone");
                            String raddress = c.getString("address");
                            String rlocation = c.getString("location");
                            HashMap<String,String> restlisthash = new HashMap<>();
                            restlisthash.put("restid",rid);
                            restlisthash.put("name",rname);
                            restlisthash.put("phone",rphone);
                            restlisthash.put("address",raddress);
                            restlisthash.put("location",rlocation);
                            restlist.add(restlisthash);
                        }
                    }catch (final JSONException e){
                        Log.e("JSONERROR",e.toString());
                    }

                    ListAdapter adapter = new CustomAdapter(
                            MainActivity.this, restlist,
                            R.layout.cust_list_rest, new String[]
                            {"name","phone","address","location"}, new int[]
                            {R.id.textView,R.id.textView2,R.id.textView3,R.id.textView4});
                    lvrest.setAdapter(adapter);
                }

        }
        LoadRestaurant loadRestaurant = new LoadRestaurant();
        loadRestaurant.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mycart:
                loadCartData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadCartWindow() {
        myDialogCart = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_DeviceDefault_Dialog_NoActionBar
        myDialogCart.setContentView(R.layout.cart_window);
        myDialogCart.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ListView lvcart = myDialogCart.findViewById(R.id.lvmycart);
        TextView tvtotal = myDialogCart.findViewById(R.id.textViewTotal);
        TextView tvorderid = myDialogCart.findViewById(R.id.textOrderId);
        Button btnpay = myDialogCart.findViewById(R.id.btnPay);
        Log.e("HANIS","SIZE:"+cartlist.size());
        lvcart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               dialogDeleteFood(position);
                return false;
            }
        });
        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPay();
            }
        });
        ListAdapter adapter = new CustomAdapterCart(
                    MainActivity.this, cartlist,
                    R.layout.user_cart_list, new String[]
                    {"foodname","foodprice","quantity","status"}, new int[]
                    {R.id.textView,R.id.textView2,R.id.textView3,R.id.textView4});
        lvcart.setAdapter(adapter);
        tvtotal.setText("RM "+total);
        tvorderid.setText(cartlist.get(0).get("orderid"));
        myDialogCart.show();

    }

    private void dialogDeleteFood(final int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete Food "+cartlist.get(position).get("foodname")+"?");
        alertDialogBuilder
                .setMessage("Are you sure")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                       // Toast.makeText(MainActivity.this, cartlist.get(position).get("foodname"), Toast.LENGTH_SHORT).show();
                        deleteCartFood(position);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void deleteCartFood(final int position) {
        class DeleteCartFood extends AsyncTask<Void,Void,String>{

            @Override
            protected String doInBackground(Void... voids) {
                String foodid = cartlist.get(position).get("foodid");
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("foodid",foodid);
                hashMap.put("userid",userid);
                RequestHandler requestHandler = new RequestHandler();
                String s = requestHandler.sendPostRequest("http://uumresearch.com/foodninja/php/delete_cart.php",hashMap);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equalsIgnoreCase("success")){
                    myDialogCart.dismiss();
                    loadCartData();
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }
        DeleteCartFood deleteCartFood = new DeleteCartFood();
        deleteCartFood.execute();
    }

    private void dialogPay() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Proceed with payment?");

        alertDialogBuilder
                .setMessage("Are you sure")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(MainActivity.this,PaymentActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("userid",userid);
                        bundle.putString("name",name);
                        bundle.putString("phone",phone);
                        bundle.putString("total", String.valueOf(total));
                        bundle.putString("orderid", cartlist.get(0).get("orderid"));
                        intent.putExtras(bundle);
                        myDialogCart.dismiss();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void loadCartData() {
        class LoadCartData extends AsyncTask<Void,Void,String>{

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("userid",userid);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest("http://uumresearch.com/foodninja/php/load_cart.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                cartlist.clear();
                total = 0;
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray cartarray = jsonObject.getJSONArray("cart");

                    for (int i=0;i<cartarray .length();i++) {
                        JSONObject c = cartarray .getJSONObject(i);
                        String jfid = c.getString("foodid");
                        String jfn = c.getString("foodname");
                        String jfp = c.getString("foodprice");
                        String jfq = c.getString("quantity");
                        String jst = c.getString("status");
                        String joid = c.getString("orderid");
                        HashMap<String,String> cartlisthash = new HashMap<>();
                        cartlisthash .put("foodid",jfid);
                        cartlisthash .put("foodname",jfn);
                        cartlisthash .put("foodprice","RM "+jfp);
                        cartlisthash .put("quantity",jfq+" set");
                        cartlisthash .put("status",jst);
                        cartlisthash .put("orderid",joid);
                        cartlist.add(cartlisthash);
                        total = total + (Double.parseDouble(jfp) * Double.parseDouble(jfq));
                    }
                }catch (JSONException e){}
                super.onPostExecute(s);
                if (total>0){
                    loadCartWindow();
                }else{
                    Toast.makeText(MainActivity.this, "Cart is feeling empty", Toast.LENGTH_SHORT).show();
                }

            }
        }
        LoadCartData loadCartData = new LoadCartData();
        loadCartData.execute();
    }
}
