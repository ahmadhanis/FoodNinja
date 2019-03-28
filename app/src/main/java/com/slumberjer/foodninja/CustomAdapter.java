package com.slumberjer.foodninja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter extends SimpleAdapter {

    private Context mContext;
    public LayoutInflater inflater=null;
    public CustomAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
        mContext = context;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        try{
            if(convertView==null)
                vi = inflater.inflate(R.layout.cust_list_rest, null);
            HashMap<String, Object> data = (HashMap<String, Object>) getItem(position);
            TextView tvrestname = vi.findViewById(R.id.textView);
            TextView tvphone = vi.findViewById(R.id.textView2);
            TextView tvadd = vi.findViewById(R.id.textView3);
            TextView tvloc = vi.findViewById(R.id.textView4);
            CircleImageView imgrest =vi.findViewById(R.id.imageView2);
            String dname = (String) data.get("name");//hilang
            String dphone =(String) data.get("phone");
            String dadd =(String) data.get("address");
            String dloc =(String) data.get("location");
            String drid=(String) data.get("restid");
            tvrestname.setText(dname);
            tvphone.setText(dphone);
            tvadd.setText(dadd);
            tvloc.setText(dloc);
            String image_url = "http://uumresearch.com/foodninja/images/"+drid+".jpg";
            Picasso.with(mContext).load(image_url)
                    .fit().into(imgrest);

        }catch (IndexOutOfBoundsException e){

        }

        return vi;
    }
}