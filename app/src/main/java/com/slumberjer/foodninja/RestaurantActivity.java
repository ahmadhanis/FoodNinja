package com.slumberjer.foodninja;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RestaurantActivity extends AppCompatActivity {
TextView tvrname,tvrphone,tvraddress,tvrloc;
ImageView imgRest;
ListView lvfood;
ArrayList<HashMap<String, String>> foodlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String restid = bundle.getString("restid");
        String rname = bundle.getString("name");
        String rphone = bundle.getString("phone");
        String raddress = bundle.getString("address");
        String rlocation = bundle.getString("location");
        initView();
        tvrname.setText(rname);
        tvraddress.setText(raddress);
        tvrphone.setText(rphone);
        tvrloc.setText(rlocation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Picasso.with(this).load("http://uumresearch.com/foodninja/images/"+restid+".jpg")
                .memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE)
                .fit().into(imgRest);

        lvfood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(RestaurantActivity.this, foodlist.get(position).get("foodname"), Toast.LENGTH_SHORT).show();
                showFoodDetail(position);
            }
        });
        loadFoods(restid);

    }

    private void showFoodDetail(int p) {
            Dialog myDialogWindow = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_DeviceDefault_Dialog_NoActionBar
            myDialogWindow.setContentView(R.layout.dialog_window);
            myDialogWindow.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView tvfname,tvfprice,tvfquan;
            tvfname= myDialogWindow.findViewById(R.id.textView12);
            tvfprice = myDialogWindow.findViewById(R.id.textView13);
            tvfquan = myDialogWindow.findViewById(R.id.textView14);
            tvfname.setText(foodlist.get(p).get("foodname"));
            tvfprice.setText(foodlist.get(p).get("foodprice"));
            tvfquan.setText(foodlist.get(p).get("foodquantity"));
            myDialogWindow.show();
    }

    private void loadFoods(final String restid) {
        class LoadFood extends AsyncTask<Void,Void,String>{

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("restid",restid);
                RequestHandler requestHandler = new RequestHandler();
                String s = requestHandler.sendPostRequest("http://uumresearch.com/foodninja/php/load_foods.php",hashMap);
                return s;
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                foodlist.clear();
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray foodarray = jsonObject.getJSONArray("food");
                    for (int i = 0; i < foodarray.length(); i++) {
                        JSONObject c = foodarray.getJSONObject(i);
                        String jsid = c.getString("foodid");
                        String jsfname = c.getString("foodname");
                        String jsfprice = c.getString("foodprice");
                        String jsquan = c.getString("quantity");
                        HashMap<String,String> foodlisthash = new HashMap<>();
                        foodlisthash.put("foodid",jsid);
                        foodlisthash.put("foodname",jsfname);
                        foodlisthash.put("foodprice","Price:RM"+jsfprice);
                        foodlisthash.put("foodquantity","Available:"+jsquan);
                        foodlist.add(foodlisthash);
                    }
                }catch(JSONException e){}
                ListAdapter adapter = new SimpleAdapter(
                        RestaurantActivity.this, foodlist,
                        R.layout.food_list_rest, new String[]
                        {"foodname","foodprice","foodquantity"}, new int[]
                        {R.id.textView,R.id.textView2,R.id.textView3});
                lvfood.setAdapter(adapter);

            }
        }
        LoadFood loadFood = new LoadFood();
        loadFood.execute();
    }

    private void initView() {
        imgRest = findViewById(R.id.imageView3);
        tvrname = findViewById(R.id.textView6);
        tvrphone = findViewById(R.id.textView7);
        tvraddress = findViewById(R.id.textView8);
        tvrloc = findViewById(R.id.textView9);
        lvfood = findViewById(R.id.listviewfood);
        foodlist = new ArrayList<>();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(RestaurantActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
