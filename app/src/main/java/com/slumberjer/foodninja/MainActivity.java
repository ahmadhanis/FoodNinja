package com.slumberjer.foodninja;

import android.app.Dialog;
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
    String userid;
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

                    ListAdapter adapter = new SimpleAdapter(
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
        Dialog myDialogCart = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_DeviceDefault_Dialog_NoActionBar
        myDialogCart.setContentView(R.layout.cart_window);
        myDialogCart.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ListView lvcart = myDialogCart.findViewById(R.id.lvmycart);
        TextView tvtotal = myDialogCart.findViewById(R.id.textViewTotal);
        Log.e("HANIS","SIZE:"+cartlist.size());
        lvcart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, cartlist.get(position).get("foodname"), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, cartlist,
                    R.layout.user_cart_list, new String[]
                    {"foodname","foodprice","quantity","status"}, new int[]
                    {R.id.textView,R.id.textView2,R.id.textView3,R.id.textView4});
        lvcart.setAdapter(adapter);
        tvtotal.setText("RM "+total);

        myDialogCart.show();

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
                try{
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray cartarray = jsonObject.getJSONArray("cart");
                    total = 0;
                    for (int i=0;i<cartarray .length();i++) {
                        JSONObject c = cartarray .getJSONObject(i);
                        String jid = c.getString("foodid");
                        String jfn = c.getString("foodname");
                        String jfp = c.getString("foodprice");
                        String jfq = c.getString("quantity");
                        String jst = c.getString("status");
                        HashMap<String,String> cartlisthash = new HashMap<>();
                        cartlisthash .put("foodid",jid);
                        cartlisthash .put("foodname",jfn);
                        cartlisthash .put("foodprice","RM "+jfp);
                        cartlisthash .put("quantity",jfq+" unit");
                        cartlisthash .put("status",jst);
                        cartlist.add(cartlisthash);
                        total = total + (Double.parseDouble(jfp) * Double.parseDouble(jfq));
                    }
                }catch (JSONException e){}
                super.onPostExecute(s);
                loadCartWindow();
            }
        }
        LoadCartData loadCartData = new LoadCartData();
        loadCartData.execute();
    }
}
