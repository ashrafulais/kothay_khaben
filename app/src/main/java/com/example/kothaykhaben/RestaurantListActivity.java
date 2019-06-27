package com.example.kothaykhaben;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {

    //previous: https://api.myjson.com/bins/d412n
    //updated with menus and ratings: https://api.myjson.com/bins/udlm7

    private String url = "https://api.myjson.com/bins/yxhb3";
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<MyItem> listItems;
    private ProgressDialog progressDialogue;

    private String placeName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        loadData();


        //recycler view item click
        recyclerView.addOnItemTouchListener(new RecyclerSetOnItemClickListener(this, new RecyclerSetOnItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                String name = listItems.get(position).getResId();
                final String socialLink = listItems.get(position).getResLink();

                Button button = (Button) findViewById(R.id.resSocialLinkBtn);
                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    //On click function
                    public void onClick(View view) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(socialLink)));
                    }
                });

                Intent intentTour = new Intent(RestaurantListActivity.this, RestaurantMenuActivity.class);
                intentTour.putExtra("RESTAURANT_ID", name);
                startActivity(intentTour);
            }
        }));
    }


    public void loadData() {

        progressDialogue = new ProgressDialog(this);
        progressDialogue.setMessage("Loading....");
        progressDialogue.show();
        placeName2 = getIntent().getStringExtra("PLACE_NAME");


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialogue.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    //Log.d("Debug", "JSON OBJECT:---------------------------------- " + jsonObject.toString());
                    JSONArray array = jsonObject.getJSONArray(placeName2);

                    //Log.d("Debug", "ARRAY LENGTH:---------------------------------- " + array.getBoolean(0));

                    for(int i=0; i<array.length(); i++) {

                        JSONObject receive=array.getJSONObject(i);

                        MyItem item =new MyItem(
                            receive.getString("resId"),
                            receive.getString("resName"),
                            receive.getString("resLocation"),
                            receive.getString("resImage"),
                            receive.getString("resFbLink")
                        );

                        listItems.add(item);
                    }

                    //adding recyclerview with adapter
                    myAdapter = new MyAdapter(listItems, getApplicationContext());
                    recyclerView.setAdapter(myAdapter);


                } catch(JSONException e) {
                    //e.printStackTrace();
                    Toast.makeText(RestaurantListActivity.this, "Location isn't found. Sorry :(", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RestaurantListActivity.this, "Server Error.", Toast.LENGTH_SHORT).show();
                progressDialogue.dismiss();
            }
        });

        //Log.d("Debug", "LIST ITEMS:---------------------------------- " + listItems.toString());
        RequestQueue queue = Volley.newRequestQueue(this);
        //Log.d("Debug", "REQ QUEUE:---------------------------------- " + stringRequest.toString());
        queue.add(stringRequest);

    }
}