package com.slumberjer.foodninja;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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

public class MainActivity extends AppCompatActivity {
    ListView lvrest;
    Dialog myDialogWindow;
    ArrayList<HashMap<String, String>> restlist;
    Spinner sploc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvrest = findViewById(R.id.listviewRest);
        sploc = findViewById(R.id.spinner);
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

    private void loadWindow(int p) {
        myDialogWindow = new Dialog(this, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_DeviceDefault_Dialog_NoActionBar
        myDialogWindow.setContentView(R.layout.dialog_window);
        Button btnpress = myDialogWindow.findViewById(R.id.button2);
        TextView myname,myphone,myaddress,myloc;
        myname = myDialogWindow.findViewById(R.id.textView1);
        myphone = myDialogWindow.findViewById(R.id.textView2);
        myaddress = myDialogWindow.findViewById(R.id.textView3);
        myloc = myDialogWindow.findViewById(R.id.textView4);
        myname.setText(restlist.get(p).get("name"));
        myphone.setText(restlist.get(p).get("phone"));
        myaddress.setText(restlist.get(p).get("address"));
        myloc.setText(restlist.get(p).get("location"));

        btnpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialogWindow.dismiss();
            }
        });
        myDialogWindow.show();
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
}
