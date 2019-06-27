package com.example.kothaykhaben;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
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
import java.util.Iterator;
import java.util.List;
import com.google.gson.Gson;

public class RestaurantMenuActivity extends AppCompatActivity {

    private String url = "https://raw.githubusercontent.com/ashrafulais/kothay_khaben/master/data/restaurant_details.json";
    private RecyclerView recyclerView;
    private FoodMenuAdapter myAdapter;
    private List<MyItem> foodItems = new ArrayList<>();
    private ProgressDialog progressDialogue;
    private String resid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);

        recyclerView = findViewById(R.id.foodMenuRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();

        /*resid=getIntent().getStringExtra("RESTAURANT_ID");
        Toast.makeText(RestaurantMenuActivity.this, resid, Toast.LENGTH_SHORT).show();*/

    }



    public void loadData() {

        progressDialogue = new ProgressDialog(this);
        progressDialogue.setMessage("Loading....");
        progressDialogue.show();
        resid = getIntent().getStringExtra("RESTAURANT_ID");


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialogue.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    //Log.d("Debug", "JSON OBJECT:---------------------------------- " + jsonObject.toString());
                    JSONArray array = jsonObject.getJSONArray("Mirpur");

                    //Log.d("Debug", "ARRAY LENGTH:---------------------------------- " + array.getBoolean(0));

                    int newresid = Integer.parseInt(resid);


                    for(int i=0; i<array.length(); i++) {

                        JSONObject receive=array.getJSONObject(i);

                        if(receive.getInt("resId") == newresid) {

                            receive = array.getJSONObject(i).getJSONObject("foodMenu");

                            try {
                                Iterator<String> temp = receive.keys();
                                while (temp.hasNext()) {
                                    String key = temp.next();
                                    Object value = receive.get(key);

                                    String newObj = value.toString();
                                    JSONObject mJSONObject = new JSONObject(newObj);

                                    //Log.d("Debug", "ARRAY DATA:---------------------------------- " + mJSONObject.getString("foodName"));
                                    MyItem fooditems = new MyItem(
                                            mJSONObject.getString("foodName"),
                                            (float)mJSONObject.getDouble("foodRating")
                                    );
                                    foodItems.add(fooditems);
                                }
                            } catch (JSONException e) {
                                Toast.makeText(RestaurantMenuActivity.this, "Error occurred. Sorry :(", Toast.LENGTH_SHORT).show();
                            }

                            break;
                        }


                    }

                    //adding recyclerview with adapter
                    myAdapter = new FoodMenuAdapter(foodItems, getApplicationContext());
                    recyclerView.setAdapter(myAdapter);


                } catch(JSONException e) {
                    //e.printStackTrace();
                    Toast.makeText(RestaurantMenuActivity.this, "Some unknown error. Sorry :(", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RestaurantMenuActivity.this, "Server Error.", Toast.LENGTH_SHORT).show();
                progressDialogue.dismiss();
            }
        });

        //Log.d("Debug", "LIST ITEMS:---------------------------------- " + listItems.toString());
        RequestQueue queue = Volley.newRequestQueue(this);
        //Log.d("Debug", "REQ QUEUE:---------------------------------- " + stringRequest.toString());
        queue.add(stringRequest);

    }

}
