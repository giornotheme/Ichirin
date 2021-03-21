package com.example.victortang.ichirin.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.victortang.ichirin.MySingleton;
import com.example.victortang.ichirin.R;
import com.example.victortang.ichirin.async.AsyncDrinks;
import com.example.victortang.ichirin.models.Drink;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mContactUs;
    private Button mInformation;
    private Button mValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.search);
        ListView listView = (ListView) findViewById(R.id.data);

        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);


        new AsyncDrinks(adapter,editText.getText().toString()).execute();

        mContactUs = findViewById(R.id.contactus);
        mInformation = findViewById(R.id.information);
        mValidation = findViewById(R.id.validation);

        mContactUs.setOnClickListener(v -> {
            Intent contactActivity = new Intent(MainActivity.this, ContactUs.class);
            startActivity(contactActivity);
        });

        mInformation.setOnClickListener(v -> {
            Intent informationActivity = new Intent(MainActivity.this, Information.class);
            startActivity(informationActivity);
        });

        mValidation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (listView.getAdapter().getCount() != 0){
                    MyAdapter ad = (MyAdapter) listView.getAdapter();
                    ad.vector.clear();
                    ad.notifyDataSetChanged();
                }
                AsyncDrinks drinks = new AsyncDrinks(adapter,editText.getText().toString());
                drinks.execute();
            }
        });

    }

    public class MyAdapter extends BaseAdapter {
        //a vector that store all url
        List<Drink> vector = new ArrayList<>();

        //return the number of url in vector
        @Override
        public int getCount() {
            return vector.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public List<Drink> getVector() {
            return vector;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if( convertView == null ){
                //We create a View:
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.bitmap_layout, parent, false);
            }

            TextView drinkTitle= convertView.findViewById(R.id.drinkTitle);
            drinkTitle.setText(vector.get(position).getDrinkTitle());

            TextView drinkInstructions= convertView.findViewById(R.id.drinkInstruction);
            drinkInstructions.setText(vector.get(position).getDrinkInstruction());

            final ImageView iv = (ImageView) convertView.findViewById(R.id.drinkImage);
            ImageRequest ir = new ImageRequest(vector.get(position).getDrinkImage(), new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    iv.setImageBitmap(response);

                }

            },0,0, ImageView.ScaleType.CENTER_CROP,null, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this,"Some Thing Goes Wrong", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            });
            MySingleton.getInstance(parent.getContext()).addToRequestQueue(ir);

            return convertView;
        }
        public void dd(Drink drink){
            vector.add(drink);
        }
    }
}